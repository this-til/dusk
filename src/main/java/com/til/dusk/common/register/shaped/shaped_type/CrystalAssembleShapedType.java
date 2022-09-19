package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.mana_level.ManaLevelItem;
import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.register.ore.OreBlock;
import com.til.dusk.common.register.ore.OreItem;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.util.pack.DataPack;
import net.minecraft.world.item.ItemStack;

public class CrystalAssembleShapedType extends ShapedType {

    public CrystalAssembleShapedType() {
        super("crystal_assemble", () -> ManaLevelBlock.crystalAssemble);
    }

    @Override
    public void registerShaped() {
        for (ManaLevel manaLevel : ManaLevel.LEVEL.get()) {
            if (manaLevel.hasSet(ManaLevel.OPERATION)) {
                DataPack.ManaLevelDataPack manaLevelDataPack = manaLevel.getSet(ManaLevel.OPERATION).get();
                if (manaLevel.up != null) {
                    new ShapedOre(this, ShapedDrive.get(0), manaLevel)
                            .addInItem(manaLevel.itemMap.get(ManaLevelItem.operationBasics.integrate).itemTag(), 1)
                            .addOutItem(new ItemStack(manaLevel.itemMap.get(ManaLevelItem.operation.integrate).item(), 1), 1d)
                            .runThis(s -> manaLevelDataPack.run(s, manaLevel))
                            .addMultipleSurplusTime(2048)
                            .addMultipleConsumeMana(16);
                }
                new ShapedOre(this, ShapedDrive.get(0), manaLevel)
                        .addInItem(manaLevel.itemMap.get(ManaLevelItem.operationBasics).itemTag(), 1)
                        .addInItem(manaLevel.getRelationTagPack(OreBlock.bracket).itemTagKey(), 1)
                        .addOutItem(new ItemStack(manaLevel.itemMap.get(ManaLevelItem.operation).item(), 1), 1d)
                        .runThis(s -> {
                            manaLevelDataPack.run(s, manaLevel);
                            manaLevelDataPack.run(s, manaLevel);
                        })
                        .addMultipleSurplusTime(4096)
                        .addMultipleConsumeMana(32);
                if (manaLevel.next != null) {
                    new ShapedOre(this, ShapedDrive.get(0), manaLevel)
                            .addInItem(manaLevel.itemMap.get(ManaLevelItem.operationBasics.host).itemTag(), 1)
                            .addInItem(manaLevel.getRelationTagPack(OreBlock.bracket).itemTagKey(), 4)
                            .addOutItem(new ItemStack(manaLevel.itemMap.get(ManaLevelItem.operation.host).item(), 1), 1d)
                            .runThis(s -> {
                                manaLevelDataPack.run(s, manaLevel);
                                manaLevelDataPack.run(s, manaLevel);
                                manaLevelDataPack.run(s, manaLevel);
                                manaLevelDataPack.run(s, manaLevel);
                            })
                            .addMultipleSurplusTime(8192)
                            .addMultipleConsumeMana(42);
                }
            }
            if (manaLevel.hasSet(ManaLevel.FORMING)) {
                DataPack.ManaLevelDataPack manaLevelDataPack = manaLevel.getSet(ManaLevel.FORMING).get();
                if (manaLevel.up != null) {
                    new ShapedOre(this, ShapedDrive.get(1), manaLevel)
                            .addInItem(manaLevel.itemMap.get(ManaLevelItem.operation.integrate).itemTag(), 1)
                            .addOutItem(new ItemStack(manaLevel.itemMap.get(ManaLevelItem.forming.integrate).item(), 1), 1d)
                            .runThis(s -> manaLevelDataPack.run(s, manaLevel))
                            .addMultipleSurplusTime(2048)
                            .addMultipleConsumeMana(16);
                }
                new ShapedOre(this, ShapedDrive.get(1), manaLevel)
                        .addInItem(manaLevel.itemMap.get(ManaLevelItem.operation).itemTag(), 1)
                        .addOutItem(new ItemStack(manaLevel.itemMap.get(ManaLevelItem.forming).item(), 1), 1d)
                        .runThis(s -> {
                            manaLevelDataPack.run(s, manaLevel);
                            manaLevelDataPack.run(s, manaLevel);
                        })
                        .addMultipleSurplusTime(4096)
                        .addMultipleConsumeMana(32);
                if (manaLevel.next != null) {
                    new ShapedOre(this, ShapedDrive.get(1), manaLevel)
                            .addInItem(manaLevel.itemMap.get(ManaLevelItem.operation.host).itemTag(), 1)
                            .addOutItem(new ItemStack(manaLevel.itemMap.get(ManaLevelItem.forming.host).item(), 1), 1d)
                            .runThis(s -> {
                                manaLevelDataPack.run(s, manaLevel);
                                manaLevelDataPack.run(s, manaLevel);
                                manaLevelDataPack.run(s, manaLevel);
                                manaLevelDataPack.run(s, manaLevel);
                            })
                            .addMultipleSurplusTime(8192)
                            .addMultipleConsumeMana(42);
                }
            }
            if (manaLevel.hasSet(ManaLevel.DESTRUCTION)) {
                DataPack.ManaLevelDataPack manaLevelDataPack = manaLevel.getSet(ManaLevel.DESTRUCTION).get();
                if (manaLevel.up != null) {
                    new ShapedOre(this, ShapedDrive.get(2), manaLevel)
                            .addInItem(manaLevel.itemMap.get(ManaLevelItem.operation.integrate).itemTag(), 1)
                            .addOutItem(new ItemStack(manaLevel.itemMap.get(ManaLevelItem.destruction.integrate).item(), 1), 1d)
                            .runThis(s -> manaLevelDataPack.run(s, manaLevel))
                            .addMultipleSurplusTime(2048)
                            .addMultipleConsumeMana(16);
                }
                new ShapedOre(this, ShapedDrive.get(2), manaLevel)
                        .addInItem(manaLevel.itemMap.get(ManaLevelItem.operation).itemTag(), 1)
                        .addOutItem(new ItemStack(manaLevel.itemMap.get(ManaLevelItem.destruction).item(), 1), 1d)
                        .runThis(s -> {
                            manaLevelDataPack.run(s, manaLevel);
                            manaLevelDataPack.run(s, manaLevel);
                        })
                        .addMultipleSurplusTime(4096)
                        .addMultipleConsumeMana(32);
                if (manaLevel.next != null) {
                    new ShapedOre(this, ShapedDrive.get(2), manaLevel)
                            .addInItem(manaLevel.itemMap.get(ManaLevelItem.operation.host).itemTag(), 1)
                            .addOutItem(new ItemStack(manaLevel.itemMap.get(ManaLevelItem.destruction.host).item(), 1), 1d)
                            .runThis(s -> {
                                manaLevelDataPack.run(s, manaLevel);
                                manaLevelDataPack.run(s, manaLevel);
                                manaLevelDataPack.run(s, manaLevel);
                                manaLevelDataPack.run(s, manaLevel);
                            })
                            .addMultipleSurplusTime(8192)
                            .addMultipleConsumeMana(42);
                }
            }
            if (manaLevel.hasSet(ManaLevel.GATHER)) {
                DataPack.ManaLevelDataPack manaLevelDataPack = manaLevel.getSet(ManaLevel.GATHER).get();
                if (manaLevel.up != null) {
                    new ShapedOre(this, ShapedDrive.get(3), manaLevel)
                            .addInItem(manaLevel.itemMap.get(ManaLevelItem.operation.integrate).itemTag(), 1)
                            .addOutItem(new ItemStack(manaLevel.itemMap.get(ManaLevelItem.gather.integrate).item(), 1), 1d)
                            .runThis(s -> manaLevelDataPack.run(s, manaLevel))
                            .addMultipleSurplusTime(2048)
                            .addMultipleConsumeMana(16);
                }
                new ShapedOre(this, ShapedDrive.get(3), manaLevel)
                        .addInItem(manaLevel.itemMap.get(ManaLevelItem.operation).itemTag(), 1)
                        .addOutItem(new ItemStack(manaLevel.itemMap.get(ManaLevelItem.gather).item(), 1), 1d)
                        .runThis(s -> {
                            manaLevelDataPack.run(s, manaLevel);
                            manaLevelDataPack.run(s, manaLevel);
                        })
                        .addMultipleSurplusTime(4096)
                        .addMultipleConsumeMana(32);
                if (manaLevel.next != null) {
                    new ShapedOre(this, ShapedDrive.get(3), manaLevel)
                            .addInItem(manaLevel.itemMap.get(ManaLevelItem.operation.host).itemTag(), 1)
                            .addOutItem(new ItemStack(manaLevel.itemMap.get(ManaLevelItem.gather.host).item(), 1), 1d)
                            .runThis(s -> {
                                manaLevelDataPack.run(s, manaLevel);
                                manaLevelDataPack.run(s, manaLevel);
                                manaLevelDataPack.run(s, manaLevel);
                                manaLevelDataPack.run(s, manaLevel);
                            })
                            .addMultipleSurplusTime(8192)
                            .addMultipleConsumeMana(42);
                }
            }
            if (manaLevel.hasSet(ManaLevel.SPREAD)) {
                DataPack.ManaLevelDataPack manaLevelDataPack = manaLevel.getSet(ManaLevel.SPREAD).get();
                if (manaLevel.up != null) {
                    new ShapedOre(this, ShapedDrive.get(4), manaLevel)
                            .addInItem(manaLevel.itemMap.get(ManaLevelItem.operation.integrate).itemTag(), 1)
                            .addOutItem(new ItemStack(manaLevel.itemMap.get(ManaLevelItem.spread.integrate).item(), 1), 1d)
                            .runThis(s -> manaLevelDataPack.run(s, manaLevel))
                            .addMultipleSurplusTime(2048)
                            .addMultipleConsumeMana(16);
                }
                new ShapedOre(this, ShapedDrive.get(4), manaLevel)
                        .addInItem(manaLevel.itemMap.get(ManaLevelItem.operation).itemTag(), 1)
                        .addOutItem(new ItemStack(manaLevel.itemMap.get(ManaLevelItem.spread).item(), 1), 1d)
                        .runThis(s -> {
                            manaLevelDataPack.run(s, manaLevel);
                            manaLevelDataPack.run(s, manaLevel);
                        })
                        .addMultipleSurplusTime(4096)
                        .addMultipleConsumeMana(32);
                if (manaLevel.next != null) {
                    new ShapedOre(this, ShapedDrive.get(4), manaLevel)
                            .addInItem(manaLevel.itemMap.get(ManaLevelItem.operation.host).itemTag(), 1)
                            .addOutItem(new ItemStack(manaLevel.itemMap.get(ManaLevelItem.spread.host).item(), 1), 1d)
                            .runThis(s -> {
                                manaLevelDataPack.run(s, manaLevel);
                                manaLevelDataPack.run(s, manaLevel);
                                manaLevelDataPack.run(s, manaLevel);
                                manaLevelDataPack.run(s, manaLevel);
                            })
                            .addMultipleSurplusTime(8192)
                            .addMultipleConsumeMana(42);
                }
            }
            if (manaLevel.hasSet(ManaLevel.POWER)) {
                DataPack.ManaLevelDataPack manaLevelDataPack = manaLevel.getSet(ManaLevel.POWER).get();
                if (manaLevel.up != null) {
                    new ShapedOre(this, ShapedDrive.get(5), manaLevel)
                            .addInItem(manaLevel.itemMap.get(ManaLevelItem.operation.integrate).itemTag(), 1)
                            .addOutItem(new ItemStack(manaLevel.itemMap.get(ManaLevelItem.power.integrate).item(), 1), 1d)
                            .runThis(s -> manaLevelDataPack.run(s, manaLevel))
                            .addMultipleSurplusTime(2048)
                            .addMultipleConsumeMana(16);
                }
                new ShapedOre(this, ShapedDrive.get(5), manaLevel)
                        .addInItem(manaLevel.itemMap.get(ManaLevelItem.operation).itemTag(), 1)
                        .addOutItem(new ItemStack(manaLevel.itemMap.get(ManaLevelItem.power).item(), 1), 1d)
                        .runThis(s -> {
                            manaLevelDataPack.run(s, manaLevel);
                            manaLevelDataPack.run(s, manaLevel);
                        })
                        .addMultipleSurplusTime(4096)
                        .addMultipleConsumeMana(32);
                if (manaLevel.next != null) {
                    new ShapedOre(this, ShapedDrive.get(5), manaLevel)
                            .addInItem(manaLevel.itemMap.get(ManaLevelItem.operation.host).itemTag(), 1)
                            .addOutItem(new ItemStack(manaLevel.itemMap.get(ManaLevelItem.power.host).item(), 1), 1d)
                            .runThis(s -> {
                                manaLevelDataPack.run(s, manaLevel);
                                manaLevelDataPack.run(s, manaLevel);
                                manaLevelDataPack.run(s, manaLevel);
                                manaLevelDataPack.run(s, manaLevel);
                            })
                            .addMultipleSurplusTime(8192)
                            .addMultipleConsumeMana(42);
                }
            }
            if (manaLevel.hasSet(ManaLevel.INSTRUCTIONS)) {
                DataPack.ManaLevelDataPack manaLevelDataPack = manaLevel.getSet(ManaLevel.INSTRUCTIONS).get();
                if (manaLevel.up != null) {
                    new ShapedOre(this, ShapedDrive.get(5), manaLevel)
                            .addInItem(manaLevel.itemMap.get(ManaLevelItem.operation.integrate).itemTag(), 1)
                            .addOutItem(new ItemStack(manaLevel.itemMap.get(ManaLevelItem.instructions.integrate).item(), 1), 1d)
                            .runThis(s -> manaLevelDataPack.run(s, manaLevel))
                            .addMultipleSurplusTime(2048)
                            .addMultipleConsumeMana(16);
                }
                new ShapedOre(this, ShapedDrive.get(5), manaLevel)
                        .addInItem(manaLevel.itemMap.get(ManaLevelItem.operation).itemTag(), 1)
                        .addOutItem(new ItemStack(manaLevel.itemMap.get(ManaLevelItem.instructions).item(), 1), 1d)
                        .runThis(s -> {
                            manaLevelDataPack.run(s, manaLevel);
                            manaLevelDataPack.run(s, manaLevel);
                        })
                        .addMultipleSurplusTime(4096)
                        .addMultipleConsumeMana(32);
                if (manaLevel.next != null) {
                    new ShapedOre(this, ShapedDrive.get(5), manaLevel)
                            .addInItem(manaLevel.itemMap.get(ManaLevelItem.operation.host).itemTag(), 1)
                            .addOutItem(new ItemStack(manaLevel.itemMap.get(ManaLevelItem.instructions.host).item(), 1), 1d)
                            .runThis(s -> {
                                manaLevelDataPack.run(s, manaLevel);
                                manaLevelDataPack.run(s, manaLevel);
                                manaLevelDataPack.run(s, manaLevel);
                                manaLevelDataPack.run(s, manaLevel);
                            })
                            .addMultipleSurplusTime(8192)
                            .addMultipleConsumeMana(42);
                }
            }
        }
    }
}
