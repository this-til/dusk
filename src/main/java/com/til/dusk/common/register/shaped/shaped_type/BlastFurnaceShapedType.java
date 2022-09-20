package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreBlock;
import com.til.dusk.common.register.ore.OreItem;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import net.minecraft.world.item.ItemStack;

import java.util.Map;

/**
 * @author til
 */
public class BlastFurnaceShapedType extends ShapedType {
    public BlastFurnaceShapedType() {
        super("blast_furnace", () -> ManaLevelBlock.blastFurnace);
    }


    @Override
    public void registerShaped() {
        for (Ore ore : Ore.screen(Ore.IS_METAL, Ore.HAS_DUST)) {
            new ShapedOre(this, ShapedDrive.get(0), ore.manaLevel).addInItem(ore.itemMap.get(OreItem.dust).itemTag(), 1)
                    .addOutItem(new ItemStack(ore.itemMap.get(OreItem.ingot).item(), 1), 1d)
                    .addMultipleSurplusTime((long) (1024L * ore.strength))
                    .addMultipleConsumeMana((long) (32L * ore.consume));
        }
    }
}
