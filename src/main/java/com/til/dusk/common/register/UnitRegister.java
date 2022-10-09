package com.til.dusk.common.register;

import com.til.dusk.Dusk;
import com.til.dusk.common.data.tag.BlockTag;
import com.til.dusk.common.data.tag.FluidTag;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.util.ResourceLocationUtil;
import com.til.dusk.util.Util;
import com.til.dusk.util.pack.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

/**
 * @author til
 */
public abstract class UnitRegister<T extends UnitRegister<T, ITEM, BLOCK, FLUID>, ITEM extends ItemUnitRegister<?, T>, BLOCK extends BlockUnitRegister<?, T>, FLUID extends FluidUnitRegister<?, T>> extends TagPackSupplierRegister<T> {

    protected final Map<ITEM, ItemPack> itemMap = new HashMap<>();
    protected final Map<BLOCK, BlockPack> blockMap = new HashMap<>();
    protected final Map<FLUID, FluidPack> fluidMap = new HashMap<>();

    protected TagPack tagPack;

    public UnitRegister(ResourceLocation name, Supplier<IForgeRegistry<T>> iForgeRegistrySupplier) {
        super(name, iForgeRegistrySupplier);
    }

    @Override
    protected void registerBack() {
        if (getCellRegistry().item() != null) {
            for (ITEM item : getCellRegistry().item().get()) {
                ItemPack itemPack = item.create(Util.forcedConversion(this));
                if (itemPack == null) {
                    continue;
                }
                itemMap.put(item, itemPack);
                registerItem(item, itemPack);
            }
        }
        if (getCellRegistry().block() != null) {
            for (BLOCK block : getCellRegistry().block().get()) {
                BlockPack blockPack = block.create(Util.forcedConversion(this));
                if (blockPack == null) {
                    continue;
                }
                blockMap.put(block, blockPack);
                registerBlock(block, blockPack);
            }
        }
        if (getCellRegistry().fluid() != null) {
            for (FLUID fluid : getCellRegistry().fluid().get()) {
                FluidPack fluidPack = fluid.create(Util.forcedConversion(this));
                if (fluidPack == null) {
                    continue;
                }
                fluidMap.put(fluid, fluidPack);
                registerFluid(fluid, fluidPack);
            }
        }
    }

    protected void registerItem(ITEM item, ItemPack itemPack) {
        ForgeRegistries.ITEMS.register(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{"item", name.getPath(), item.name.getPath()}), itemPack.item());
        ItemTag.addTag(itemPack.itemTag(), itemPack.item());
        ItemTag.addTag(Dusk.instance.MOD_ITEM, itemPack.item());
        ItemTag.addTag(tagPackSupplier.getTagPack().itemTagKey(), itemPack.item());
        ItemTag.addTag(item.tagPackSupplier.getTagPack().itemTagKey(), itemPack.item());
    }

    protected void registerBlock(BLOCK block, BlockPack blockPack) {
        ForgeRegistries.BLOCKS.register(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{"block", name.getPath(), block.name.getPath()}), blockPack.block());
        BlockTag.addTag(blockPack.blockTag(), blockPack.block());
        BlockTag.addTag(Dusk.instance.MOD_BLOCK, blockPack.block());
        BlockTag.addTag(tagPackSupplier.getTagPack().blockTagKey(), blockPack.block());
        BlockTag.addTag(block.tagPackSupplier.getTagPack().blockTagKey(), blockPack.block());
        ForgeRegistries.ITEMS.register(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{"block", name.getPath(), block.name.getPath()}), blockPack.blockItem());
        ItemTag.addTag(blockPack.blockItemTag(), blockPack.blockItem());
        ItemTag.addTag(Dusk.instance.MOD_ITEM, blockPack.blockItem());
        ItemTag.addTag(tagPackSupplier.getTagPack().itemTagKey(), blockPack.blockItem());
        ItemTag.addTag(block.tagPackSupplier.getTagPack().itemTagKey(), blockPack.blockItem());
    }

    protected void registerFluid(FLUID fluid, FluidPack fluidPack) {
        ForgeRegistries.FLUID_TYPES.get().register(ResourceLocationUtil.fuseName(this, fluid), fluidPack.fluidType());
        ForgeRegistries.FLUIDS.register(ResourceLocationUtil.fuseName(name.getNamespace(), "_", new String[]{name.getPath(), fluid.name.getPath(), "source"}), fluidPack.source());
        ForgeRegistries.FLUIDS.register(ResourceLocationUtil.fuseName(name.getNamespace(), "_", new String[]{name.getPath(), fluid.name.getPath(), "flowing"}), fluidPack.flowing());
        FluidTag.addTag(fluidPack.fluidTag(), fluidPack.source());
        //FluidTag.addTag(fluidPack.fluidTag(), fluidPack.flowing());
        FluidTag.addTag(Dusk.instance.MOD_FLUID, fluidPack.source());
        //FluidTag.addTag(Dusk.instance.MOD_FLUID, fluidPack.flowing());
        FluidTag.addTag(tagPackSupplier.getTagPack().fluidTagKey(), fluidPack.source());
        //FluidTag.addTag(getTagPack().fluidTagKey(), fluidPack.flowing());
        FluidTag.addTag(fluid.tagPackSupplier.getTagPack().fluidTagKey(), fluidPack.source());
        //FluidTag.addTag(fluid.getTagPack().fluidTagKey(), fluidPack.flowing());
        if (fluidPack.liquidBlock() != null) {
            ForgeRegistries.BLOCKS.register(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{"fluid", name.getPath(), fluid.name.getPath()}), fluidPack.liquidBlock());
            if (fluidPack.liquidBlockTag() != null) {
                BlockTag.addTag(fluidPack.liquidBlockTag(), fluidPack.liquidBlock());
                BlockTag.addTag(Dusk.instance.MOD_BLOCK, fluidPack.liquidBlock());
                BlockTag.addTag(tagPackSupplier.getTagPack().blockTagKey(), fluidPack.liquidBlock());
                BlockTag.addTag(fluid.tagPackSupplier.getTagPack().blockTagKey(), fluidPack.liquidBlock());
            }
        }
        if (fluidPack.bucketItem() != null) {
            ForgeRegistries.ITEMS.register(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{"fluid", name.getPath(), fluid.name.getPath()}), fluidPack.bucketItem());
            if (fluidPack.bucketItemTag() != null) {
                ItemTag.addTag(fluidPack.bucketItemTag(), fluidPack.bucketItem());
                ItemTag.addTag(Dusk.instance.MOD_ITEM, fluidPack.bucketItem());
                ItemTag.addTag(tagPackSupplier.getTagPack().itemTagKey(), fluidPack.bucketItem());
                ItemTag.addTag(fluid.tagPackSupplier.getTagPack().itemTagKey(), fluidPack.bucketItem());
            }
        }
    }

    public abstract RegistryPack<T, ITEM, BLOCK, FLUID> getCellRegistry();

    public ItemPack get(ITEM item) {
        return itemMap.get(item);
    }

    public BlockPack get(BLOCK block) {
        return blockMap.get(block);
    }

    public FluidPack get(FLUID fluid) {
        return fluidMap.get(fluid);
    }

    public boolean has(ITEM item) {
        return itemMap.containsKey(item);
    }

    public boolean has(BLOCK block) {
        return blockMap.containsKey(block);
    }

    public boolean has(FLUID fluid) {
        return fluidMap.containsKey(fluid);
    }

    public Set<Map.Entry<ITEM, ItemPack>> itemEntrySet() {
        return itemMap.entrySet();
    }

    public Set<Map.Entry<BLOCK, BlockPack>> blockEntrySet() {
        return blockMap.entrySet();
    }

    public Set<Map.Entry<FLUID, FluidPack>> fluidEntrySet() {
        return fluidMap.entrySet();
    }

}
