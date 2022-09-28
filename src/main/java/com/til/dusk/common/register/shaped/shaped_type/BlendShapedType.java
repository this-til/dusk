package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreItem;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.util.pack.DataPack;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.Map;


/**
 * @author til
 */
public class BlendShapedType extends ShapedType {

    public BlendShapedType() {
        super("blend", () -> ManaLevelBlock.blend);
    }

    @Override
    public void registerShaped() {
        /*for (Ore ore : Ore.screen(Ore.IS_CRYSTA)) {
            new ShapedOre(this, ShapedDrive.get(0), ore.manaLevel)
                    .addInItem(ore.itemMap.get(OreItem.dust).itemTag(), 1)
                    .addInItem(ItemTags.SAND, 1)
                    .addOutItem(new ItemStack(ore.itemMap.get(OreItem.crystalSeed).item(), 2), 1d)
                    .addMultipleSurplusTime(512L)
                    .addMultipleConsumeMana(12L);
        }*/
    }
}
