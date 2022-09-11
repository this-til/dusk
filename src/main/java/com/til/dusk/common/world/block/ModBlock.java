package com.til.dusk.common.world.block;

import com.til.dusk.Dusk;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.world.item.ModItem;
import com.til.dusk.util.prefab.JsonPrefab;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author til
 */
public class ModBlock {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Dusk.MOD_ID);

    public interface IHasCustomColor {
        void blockColorBlack(ColorProxy.BlockColorPack blockColorPack);
    }

    public interface ICustomModel extends ModItem.ICustomModel {
        ResourceLocation blockModelName();

        default String blockStateJson() {
            return JsonPrefab.BLOCK_STATE_MODEL;
        }
    }

}
