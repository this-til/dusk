package com.til.dusk.common.register.shaped.shaped_type;


import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.util.pack.DataPack;
import net.minecraft.world.item.ItemStack;

/**
 * @author til
 */
public class ScreenShapedType extends ShapedType {

    public ScreenShapedType() {
        super("screen", () -> ManaLevelBlock.screen);
    }

    @Override
    public void registerShaped() {
        for (Ore ore : Ore.screen(Ore.IS_CRYSTA, Ore.MINERAL_BLOCK_DATA)) {
            new ShapedOre(this, ShapedDrive.get(0), ore.manaLevel)
                    .addInItem(ore.get(OreItem.crushedPurified).itemTag(), 1)
                    .addOutItem(new ItemStack(ore.get(OreItem.damagedCrystal).item(), 1), 0.4)
                    .addOutItem(new ItemStack(ore.get(OreItem.crystal).item(), 1), 0.5)
                    .addOutItem(new ItemStack(ore.get(OreItem.delicateCrystal).item(), 1), 0.05)
                    .addOutItem(new ItemStack(ore.get(OreItem.perfectCrystal).item(), 1), 0.01)
                    .addOutItem(new ItemStack(ore.get(OreItem.crystalSeed).item(), 1), 0.3)
                    .addOutItem(new ItemStack(ore.get(OreItem.dustTiny).item(), 1), 0.6)
                    .addOutItem(new ItemStack(ore.get(OreItem.dust).item(), 1), 0.2)
                    .runThis(s -> {
                        DataPack.OreDataPack centrifugeByproduct = ore.getSet(Ore.MINERAL_BLOCK_DATA).screenByproduct;
                        if (centrifugeByproduct != null) {
                            centrifugeByproduct.run(s, null);
                        }
                    })
                    .addMultipleSurplusTime((long) (128 * ore.strength))
                    .addMultipleConsumeMana((long) (4 * ore.consume));
        }
    }
}
