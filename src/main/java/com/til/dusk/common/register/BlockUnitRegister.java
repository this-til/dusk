package com.til.dusk.common.register;

import com.google.gson.JsonObject;
import com.til.dusk.Dusk;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.world.block.DuskBlock;
import com.til.dusk.common.world.item.DuskItem;
import com.til.dusk.util.Lang;
import com.til.dusk.util.ModelJsonUtil;
import com.til.dusk.util.pack.BlockPack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
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
public abstract class BlockUnitRegister<T extends BlockUnitRegister<T, O>, O extends TagPackSupplierRegister<?>> extends TagPackSupplierRegister<T> implements DuskBlock.ICustomModel<O> {
    public BlockUnitRegister(ResourceLocation name, Supplier<IForgeRegistry<T>> iForgeRegistrySupplier) {
        super(name, iForgeRegistrySupplier);
    }

    @Nullable
    protected BlockPack create(O o) {
        Block block = createBlock(o);
        if (block == null) {
            return null;
        }
        BlockItem blockItem = createBlockItem(o, block);
        return new BlockPack(block, createBlockTag(o), blockItem, createBlockItemTag(o));
    }

    @Nullable
    protected abstract Block createBlock(O o);

    protected final TagKey<Block> createBlockTag(O o) {
        return o.tagPackSupplier.getTagPack(this).blockTagKey();
    }

    protected BlockItem createBlockItem(O o, Block block) {
        return new BlockItem(block, new Item.Properties().tab(Dusk.TAB)) {
            @Override
            public @NotNull Component getName(@NotNull ItemStack stack) {
                return Lang.getLang(o, com.til.dusk.common.register.BlockUnitRegister.this);
            }
        };
    }

    protected final TagKey<Item> createBlockItemTag(O o) {
        return o.tagPackSupplier.getTagPack(this).itemTagKey();
    }

    @Override
    public JsonObject createBlockModel(Block block, O o) {
        return ModelJsonUtil.createBlockState(name);
    }

    @Override
    public JsonObject createModel(Item item, O o) {
        return ModelJsonUtil.createBlockFather(name);
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
