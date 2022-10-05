package com.til.dusk.common.register;

import com.til.dusk.Dusk;
import com.til.dusk.util.Lang;
import com.til.dusk.util.pack.FluidPack;
import com.til.dusk.util.pack.TagPack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.IForgeRegistry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public abstract class FluidUnitRegister<T extends FluidUnitRegister<T, O>, O extends RegisterBasics<?>> extends RegisterBasics<T> {

    public final Map<O, Fluid> sourceMap = new HashMap<>();
    public final Map<O, Fluid> flowingMap = new HashMap<>();

    protected TagPack tagPack;

    public FluidUnitRegister(ResourceLocation name, Supplier<IForgeRegistry<T>> iForgeRegistrySupplier) {
        super(name, iForgeRegistrySupplier);
    }

    public FluidPack create(O o) {
        FluidType fluidType = createFluidType(o);
        ForgeFlowingFluid.Properties properties = createProperties(o, fluidType);
        FlowingFluid source = createSourceFluid(o, properties);
        sourceMap.put(o, source);
        FlowingFluid flowing = createFlowingFluid(o, properties);
        flowingMap.put(o, flowing);
        LiquidBlock liquidBlock = createLiquidBlock(o, source);
        if (liquidBlock != null) {
            properties.block(() -> liquidBlock);
        }
        BucketItem item = createBanner(o, source);
        if (item != null) {
            properties.bucket(() -> item);
        }
        return new FluidPack(fluidType, source, flowing, createFluidTag(o), liquidBlock, createBlockTag(o), item, createBlockItemTag(o));
    }

    public abstract FluidType createFluidType(O ore);

    public ForgeFlowingFluid.Properties createProperties(O ore, FluidType fluidType) {
        return new ForgeFlowingFluid.Properties(() -> fluidType, () -> sourceMap.get(ore), () -> flowingMap.get(ore));
    }

    public FlowingFluid createFlowingFluid(O o, ForgeFlowingFluid.Properties properties) {
        return new ForgeFlowingFluid.Flowing(properties);
    }

    public FlowingFluid createSourceFluid(O o, ForgeFlowingFluid.Properties properties) {
        return new ForgeFlowingFluid.Source(properties);
    }

    public TagKey<Fluid> createFluidTag(O o) {
        return FluidTags.create(fuseName("/", new String[]{"fluid", o.name.getPath(), name.getPath()}));
    }

    @Nullable
    public LiquidBlock createLiquidBlock(O o, FlowingFluid source) {
        return new LiquidBlock(() -> source, BlockBehaviour.Properties.of(Material.WATER).noCollission().strength(100.0F).noLootTable());
    }

    @Nullable
    public TagKey<Block> createBlockTag(O o) {
        return BlockTags.create(fuseName("/", new String[]{"fluid", o.name.getPath(), name.getPath()}));
    }

    @Nullable
    public BucketItem createBanner(O o, FlowingFluid source) {
        return new BucketItem(() -> source, new Item.Properties().tab(Dusk.TAB)) {
            @Override
            public @NotNull Component getName(@NotNull ItemStack stack) {
                return Lang.getLang(o, com.til.dusk.common.register.FluidUnitRegister.this);
            }
        };
    }

    @Nullable
    public TagKey<Item> createBlockItemTag(O o) {
        return ItemTags.create(fuseName("/", new String[]{"fluid", o.name.getPath(), name.getPath()}));
    }

    public TagPack getTagPack() {
        if (tagPack == null) {
            tagPack = new TagPack(Dusk.instance.ITEM_TAG.createTagKey(name), Dusk.instance.BLOCK_TAG.createTagKey(name), Dusk.instance.FLUID_TAG.createTagKey(name));
        }
        return tagPack;
    }
}
