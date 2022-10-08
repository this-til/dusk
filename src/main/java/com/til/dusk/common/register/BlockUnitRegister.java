package com.til.dusk.common.register;

import com.til.dusk.Dusk;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.world.block.ModBlock;
import com.til.dusk.util.Lang;
import com.til.dusk.util.ResourceLocationUtil;
import com.til.dusk.util.pack.BlockPack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.IForgeRegistry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

/**
 * @author til
 */
public abstract class BlockUnitRegister<T extends BlockUnitRegister<T, O>, O extends TagPackSupplierRegister<?>> extends TagPackSupplierRegister<T> {
    public BlockUnitRegister(ResourceLocation name, Supplier<IForgeRegistry<T>> iForgeRegistrySupplier) {
        super(name, iForgeRegistrySupplier);
    }

    @Nullable
    public BlockPack create(O o) {
        Block block = createBlock(o);
        if (block == null) {
            return null;
        }
        BlockItem blockItem = createBlockItem(o, block);
        return new BlockPack(block, createBlockTag(o), blockItem, createBlockItemTag(o));
    }

    @Nullable
    public abstract Block createBlock(O o);

    public TagKey<Block> createBlockTag(O o) {
        return BlockTags.create(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{"item", o.name.getPath(), name.getPath()}));
    }

    public BlockItem createBlockItem(O o, Block block) {
        return new BlockItem(block, new Item.Properties().tab(Dusk.TAB)) {
            @Override
            public @NotNull Component getName(@NotNull ItemStack stack) {
                return Lang.getLang(o, com.til.dusk.common.register.BlockUnitRegister.this);
            }
        };
    }

    public TagKey<Item> createBlockItemTag(O o) {
        return ItemTags.create(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{"block", o.name.getPath(), name.getPath()}));
    }

    /***
     * 获取模型映射
     */
    public ModBlock.ICustomModel getBlockModelMapping(O o) {
        return () -> name;
    }

    /***
     * 染色回调
     */
    public abstract void dyeBlack(O o, ColorProxy.ItemColorPack itemColorPack);

    /***
     * 染色回调
     */
    public abstract void dyeBlack(O o, ColorProxy.BlockColorPack itemColorPack);
}
