package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreBlock;
import com.til.dusk.common.register.ore.OreItem;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.util.pack.BlockPack;
import net.minecraft.world.item.ItemStack;

import java.util.Map;

/**
 * @author til
 */
public class GrindShapedType extends ShapedType {

    public GrindShapedType() {
        super("grind", () -> ManaLevelBlock.grind);
    }

    @Override
    public void registerShaped() {
        for (Ore ore : Ore.screen(Ore.MINERAL_BLOCK_DATA)) {
            for (Map.Entry<OreBlock, BlockPack> entry : ore.blockMap.entrySet()) {
                if (!(entry.getKey() instanceof OreBlock.MineralOreBlock)) {
                    continue;
                }
                new ShapedOre(this, ShapedDrive.get(0), ore.manaLevel)
                        .addInItem(ore.blockMap.get(entry.getKey()).blockItemTag(), 1)
                        .addOutItem(new ItemStack(ore.itemMap.get(OreItem.crushed).item(), 2), 1d)
                        .addMultipleSurplusTime((long) (640 * ore.strength))
                        .addMultipleConsumeMana((long) (16 * ore.consume));
            }
        }

        for (Ore ore : Ore.screen(Ore.IS_METAL)) {
            new ShapedOre(this, ShapedDrive.get(1), ore.manaLevel)
                    .addInItem(ore.itemMap.get(OreItem.ingot).itemTag(), 1)
                    .addOutItem(new ItemStack(ore.itemMap.get(OreItem.dust).item(), 1), 1d)
                    .addMultipleSurplusTime((long) (320 * ore.strength))
                    .addMultipleConsumeMana((long) (8 * ore.consume));
        }

        for (Ore ore : Ore.screen(Ore.IS_CRYSTA)) {
            new ShapedOre(this, ShapedDrive.get(2), ore.manaLevel).addInItem(ore.itemMap.get(OreItem.crystal).itemTag(), 1)
                    .addOutItem(new ItemStack(ore.itemMap.get(OreItem.dust).item(), 1), 1d)
                    .addMultipleSurplusTime((long) (320 * ore.strength))
                    .addMultipleConsumeMana((long) (12 * ore.consume));
            new ShapedOre(this, ShapedDrive.get(3), ore.manaLevel).addInItem(ore.itemMap.get(OreItem.damagedCrystal).itemTag(), 1)
                    .addOutItem(new ItemStack(ore.itemMap.get(OreItem.dust).item(), 1), 1d)
                    .addMultipleSurplusTime((long) (320 * ore.strength))
                    .addMultipleConsumeMana((long) (18 * ore.consume));
            new ShapedOre(this, ShapedDrive.get(4), ore.manaLevel).addInItem(ore.itemMap.get(OreItem.delicateCrystal).itemTag(), 1)
                    .addOutItem(new ItemStack(ore.itemMap.get(OreItem.dust).item(), 2), 1d)
                    .addMultipleSurplusTime((long) (320 * ore.strength))
                    .addMultipleConsumeMana((long) (24 * ore.consume));
            new ShapedOre(this, ShapedDrive.get(5), ore.manaLevel)
                    .addInItem(ore.itemMap.get(OreItem.perfectCrystal).itemTag(), 1)
                    .addOutItem(new ItemStack(ore.itemMap.get(OreItem.dust).item(), 3), 1d)
                    .addMultipleSurplusTime((long) (320 * ore.strength))
                    .addMultipleConsumeMana((long) (24 * ore.consume));
        }

        for (Ore ore : Ore.screen(Ore.DECORATE_BLOCK_DATA)) {
            new ShapedOre(this, ShapedDrive.get(6), ore.manaLevel)
                    .addInItem(ore.blockMap.get(OreBlock.block).blockItemTag(), 1)
                    .addOutItem(new ItemStack(ore.itemMap.get(OreItem.dust).item(), 9), 1d)
                    .addMultipleSurplusTime((long) (5000 * ore.strength))
                    .addMultipleConsumeMana((long) (16 * ore.consume));
            new ShapedOre(this, ShapedDrive.get(7), ore.manaLevel)
                    .addInItem(ore.blockMap.get(OreBlock.slab).blockItemTag(), 1)
                    .addOutItem(new ItemStack(ore.itemMap.get(OreItem.dust).item(), 3), 1d)
                    .addMultipleSurplusTime((long) (2000 * ore.strength))
                    .addMultipleConsumeMana((long) (16 * ore.consume));
            new ShapedOre(this, ShapedDrive.get(8), ore.manaLevel)
                    .addInItem(ore.blockMap.get(OreBlock.stairs).blockItemTag(), 1)
                    .addOutItem(new ItemStack(ore.itemMap.get(OreItem.dust).item(), 7), 1d)
                    .addMultipleSurplusTime((long) (5000 * ore.strength))
                    .addMultipleConsumeMana((long) (16 * ore.consume));
            new ShapedOre(this, ShapedDrive.get(9), ore.manaLevel)
                    .addInItem(ore.blockMap.get(OreBlock.wall).blockItemTag(), 1)
                    .addOutItem(new ItemStack(ore.itemMap.get(OreItem.dust).item(), 6), 1d)
                    .addMultipleSurplusTime((long) (5000 * ore.strength))
                    .addMultipleConsumeMana((long) (16 * ore.consume));
        }

    }
}
