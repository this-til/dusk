package com.til.dusk.common.world.block;

import com.google.gson.JsonObject;
import com.til.dusk.Dusk;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.world.item.DuskItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

/**
 * @author til
 */
public class DuskBlock {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Dusk.MOD_ID);

    public interface IHasCustomColor {
        void blockColorBlack(ColorProxy.BlockColorPack blockColorPack);
    }

    public interface ICustomModel<D> extends DuskItem.ICustomModel<D> {
        @Nullable
        JsonObject createBlockModel(Block block, D d);
    }

}
