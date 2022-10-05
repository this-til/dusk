package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.ore.block.OreBlock;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import net.minecraft.world.item.ItemStack;

/**
 * @author til
 */
public class UnPackShapedType extends ShapedType {

    public UnPackShapedType() {
        super("unpack", () -> ManaLevelBlock.unpack);
    }

    @Override
    public void registerShaped() {
        for (Ore ore : Ore.screen(Ore.DECORATE_BLOCK_DATA, Ore.IS_METAL)) {
            new ShapedOre(this, ShapedDrive.get(0), ore.manaLevel)
                    .addInItem(ore.get(OreBlock.block).blockItemTag(), 1)
                    .addOutItem(new ItemStack(ore.get(OreItem.ingot).item(), 9), 1d)
                    .addMultipleSurplusTime((long) (128 * ore.strength))
                    .addMultipleConsumeMana((long) (4 * ore.consume));
        }

        for (Ore ore : Ore.screen(Ore.DECORATE_BLOCK_DATA, Ore.IS_CRYSTA)) {
            new ShapedOre(this, ShapedDrive.get(0), ore.manaLevel)
                    .addInItem(ore.get(OreBlock.block).blockItemTag(), 1)
                    .addOutItem(new ItemStack(ore.get(OreItem.crystal).item(), 9), 1d)
                    .addMultipleSurplusTime((long) (128 * ore.strength))
                    .addMultipleConsumeMana((long) (4 * ore.consume));
        }

        for (Ore ore : Ore.screen(Ore.IS_METAL)) {
            new ShapedOre(this, ShapedDrive.get(1), ore.manaLevel)
                    .addInItem(ore.get(OreItem.ingot).itemTag(), 1)
                    .addOutItem(new ItemStack(ore.get(OreItem.nuggets).item(), 9), 1d)
                    .addMultipleSurplusTime((long) (128 * ore.strength))
                    .addMultipleConsumeMana((long) (4 * ore.consume));
        }

        for (Ore ore : Ore.screen(Ore.HAS_DUST)) {
            new ShapedOre(this, ShapedDrive.get(2), ore.manaLevel)
                    .addInItem(ore.get(OreItem.dust).itemTag(), 1)
                    .addOutItem(new ItemStack(ore.get(OreItem.dustTiny).item(), 9), 1d)
                    .addMultipleSurplusTime((long) (128 * ore.strength))
                    .addMultipleConsumeMana((long) (4 * ore.consume));
        }


    }

}
