package com.til.dusk.common.world.block;

import com.til.dusk.Dusk;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.world.item.DuskItem;
import com.til.dusk.util.prefab.JsonPrefab;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.text.MessageFormat;

/**
 * @author til
 */
public class DuskBlock {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Dusk.MOD_ID);

    public interface IHasCustomColor {
        void blockColorBlack(ColorProxy.BlockColorPack blockColorPack);
    }

    public interface ICustomModel extends DuskItem.ICustomModel {
        ResourceLocation blockModelName();

        @Override
        default ResourceLocation itemModelName() {
            return blockModelName();
        }

        default String blockStateJson() {
            ResourceLocation resourceLocation = blockModelName();
            return MessageFormat.format(blockJsonBasics(), resourceLocation.getNamespace(), resourceLocation.getPath());
        }

        @Override
        default String itemJsonBasics() {
            return JsonPrefab.ITEM_BLOCK_FATHER;
        }

        default String blockJsonBasics() {
            return JsonPrefab.BLOCK_STATE_MODEL;
        }
    }

}
