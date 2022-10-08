package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.mana_level.item.ManaLevelItem;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.util.pack.DataPack;
import net.minecraft.world.item.ItemStack;

/**
 * @author til
 */
public class CarvingShapedType extends ShapedType {

    public CarvingShapedType() {
        super("carving", () -> ManaLevelBlock.carving);
    }

    @Override
    public void registerShaped() {
        for (ManaLevel manaLevel : ManaLevel.MANA_LEVEL.get()) {
            if (manaLevel.hasSet(ManaLevel.operationBasics)) {
                DataPack.ManaLevelDataPack manaLevelDataPack = manaLevel.getSet(ManaLevel.operationBasics);
                if (manaLevel.up != null) {
                    new ShapedOre(this, ShapedDrive.get(0), manaLevel)
                            .addInItem(manaLevel.getAcceptableTagPack(OreItem.crystal).itemTagKey(), 1)
                            .addOutItem(new ItemStack(manaLevel.get(ManaLevelItem.operationBasics.integrate).item(), 1), 1d)
                            .runThis(s -> manaLevelDataPack.run(s, manaLevel))
                            .addMultipleSurplusTime(1024)
                            .addMultipleConsumeMana(16);
                }
                new ShapedOre(this, ShapedDrive.get(0), manaLevel)
                        .addInItem(manaLevel.getAcceptableTagPack(OreItem.delicateCrystal).itemTagKey(), 1)
                        .addOutItem(new ItemStack(manaLevel.get(ManaLevelItem.operationBasics.processor).item(), 1), 1d)
                        .runThis(s -> {
                            manaLevelDataPack.run(s, manaLevel);
                            manaLevelDataPack.run(s, manaLevel);
                        })
                        .addMultipleSurplusTime(2048)
                        .addMultipleConsumeMana(32);
                if (manaLevel.next != null) {
                    new ShapedOre(this, ShapedDrive.get(0), manaLevel)
                            .addInItem(manaLevel.getAcceptableTagPack(OreItem.perfectCrystal).itemTagKey(), 1)
                            .addOutItem(new ItemStack(manaLevel.get(ManaLevelItem.operationBasics.host).item(), 1), 1d)
                            .runThis(s -> {
                                manaLevelDataPack.run(s, manaLevel);
                                manaLevelDataPack.run(s, manaLevel);
                                manaLevelDataPack.run(s, manaLevel);
                                manaLevelDataPack.run(s, manaLevel);
                            })
                            .addMultipleSurplusTime(4096)
                            .addMultipleConsumeMana(42);
                }
            }
        }
    }
}
