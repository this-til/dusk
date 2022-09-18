package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreItem;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.util.pack.DataPack;
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
        for (Ore ore : Ore.ORE.get()) {
            if (ore.hasSet(Ore.BLEND_BYPRODUCT)) {
                DataPack.OreDataPack dataPack = ore.getSet(Ore.BLEND_BYPRODUCT).get();
                new ShapedOre(this, ShapedDrive.get(0), ore.manaLevel)
                        .addOutItem(new ItemStack(ore.itemMap.get(OreItem.dust).item(), dataPack.getSet(DataPack.AMOUNT) == 0 ? 1 : dataPack.getSet(DataPack.AMOUNT)), 1d)
                        .runThis(s -> dataPack.run(s, null))
                        .addMultipleSurplusTime((long) (ore.strength * 512L))
                        .addMultipleOutMana((long) (ore.consume * 24L));
            }

        }
    }
}
