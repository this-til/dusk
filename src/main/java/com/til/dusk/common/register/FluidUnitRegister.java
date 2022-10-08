package com.til.dusk.common.register;

import com.til.dusk.Dusk;
import com.til.dusk.util.Extension;
import com.til.dusk.util.Lang;
import com.til.dusk.util.ResourceLocationUtil;
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

public abstract class FluidUnitRegister<T extends FluidUnitRegister<T, O>, O extends TagPackSupplierRegister<?>> extends TagPackSupplierRegister<T> {

    public FluidUnitRegister(ResourceLocation name, Supplier<IForgeRegistry<T>> iForgeRegistrySupplier) {
        super(name, iForgeRegistrySupplier);
    }

    public FluidPack create(O o) {
        FluidType fluidType = createFluidType(o);
        Extension.VariableData<ForgeFlowingFluid.Source> sourceSupplier = new Extension.VariableData<>(null);
        Extension.VariableData<ForgeFlowingFluid.Flowing> flowingSupplier = new Extension.VariableData<>(null);
        ForgeFlowingFluid.Properties properties = createProperties(o, fluidType, sourceSupplier, flowingSupplier);
        ForgeFlowingFluid.Source source = createSourceFluid(o, properties);
        ForgeFlowingFluid.Flowing flowing = createFlowingFluid(o, properties);
        sourceSupplier.d1 = source;
        flowingSupplier.d1 = flowing;
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

    public ForgeFlowingFluid.Properties createProperties(O ore, FluidType fluidType, Supplier<ForgeFlowingFluid.Source> sourceSupplier, Supplier<ForgeFlowingFluid.Flowing> flowingSupplier) {
        return new ForgeFlowingFluid.Properties(() -> fluidType, sourceSupplier, flowingSupplier);
    }

    public ForgeFlowingFluid.Flowing createFlowingFluid(O o, ForgeFlowingFluid.Properties properties) {
        return new ForgeFlowingFluid.Flowing(properties);
    }

    public ForgeFlowingFluid.Source createSourceFluid(O o, ForgeFlowingFluid.Properties properties) {
        return new ForgeFlowingFluid.Source(properties);
    }

    public TagKey<Fluid> createFluidTag(O o) {
        return FluidTags.create(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{"fluid", o.name.getPath(), name.getPath()}));
    }

    @Nullable
    public LiquidBlock createLiquidBlock(O o, FlowingFluid source) {
        return new LiquidBlock(() -> source, BlockBehaviour.Properties.of(Material.WATER).noCollission().strength(100.0F).noLootTable());
    }

    @Nullable
    public TagKey<Block> createBlockTag(O o) {
        return BlockTags.create(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{"fluid", o.name.getPath(), name.getPath()}));
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
        return ItemTags.create(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{"fluid", o.name.getPath(), name.getPath()}));
    }

}
