package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.mana_level.item.ManaLevelItem;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.block.OreBlock;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.util.pack.DataPack;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;

public class CrystalAssembleShapedType extends ShapedType {

    public CrystalAssembleShapedType() {
        super("crystal_assemble", () -> ManaLevelBlock.crystalAssemble);
    }

    @Override
    public void registerRuleShaped(Consumer<Shaped> shapedConsumer) {
        for (ManaLevel manaLevel : ManaLevel.MANA_LEVEL.get()) {
            if (manaLevel.hasSet(ManaLevel.operation)) {
                DataPack.ManaLevelDataPack manaLevelDataPack = manaLevel.getSet(ManaLevel.operation);
                if (manaLevel.up != null) {
                    new ShapedOre(this, ShapedDrive.get(0), manaLevel)
                            .addInItem(manaLevel.get(ManaLevelItem.operationBasics.integrate).itemTag(), 1)
                            .addOutItem(new ItemStack(manaLevel.get(ManaLevelItem.operation.integrate).item(), 1), 1d)
                            .runThis(s -> manaLevelDataPack.run(s, manaLevel))
                            .addMultipleSurplusTime(2048)
                            .addMultipleConsumeMana(16);
                }
                new ShapedOre(this, ShapedDrive.get(0), manaLevel)
                        .addInItem(manaLevel.get(ManaLevelItem.operationBasics.processor).itemTag(), 1)
                        .addOutItem(new ItemStack(manaLevel.get(ManaLevelItem.operation.processor).item(), 1), 1d)
                        .runThis(s -> {
                            manaLevelDataPack.run(s, manaLevel);
                            manaLevelDataPack.run(s, manaLevel);
                        })
                        .addMultipleSurplusTime(4096)
                        .addMultipleConsumeMana(32);
                if (manaLevel.next != null) {
                    new ShapedOre(this, ShapedDrive.get(0), manaLevel)
                            .addInItem(manaLevel.get(ManaLevelItem.operationBasics.host).itemTag(), 1)
                            .addInItem(manaLevel.getAcceptableTagPack(OreBlock.bracket).itemTagKey(), 1)
                            .addOutItem(new ItemStack(manaLevel.get(ManaLevelItem.operation.host).item(), 1), 1d)
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
            if (manaLevel.hasSet(ManaLevel.forming)) {
                DataPack.ManaLevelDataPack manaLevelDataPack = manaLevel.getSet(ManaLevel.forming);
                if (manaLevel.up != null) {
                    new ShapedOre(this, ShapedDrive.get(1), manaLevel)
                            .addInItem(manaLevel.get(ManaLevelItem.operationBasics.integrate).itemTag(), 1)
                            .addOutItem(new ItemStack(manaLevel.get(ManaLevelItem.forming.integrate).item(), 1), 1d)
                            .runThis(s -> manaLevelDataPack.run(s, manaLevel))
                            .addMultipleSurplusTime(2048)
                            .addMultipleConsumeMana(16);
                }
                new ShapedOre(this, ShapedDrive.get(1), manaLevel)
                        .addInItem(manaLevel.get(ManaLevelItem.operationBasics.processor).itemTag(), 1)
                        .addOutItem(new ItemStack(manaLevel.get(ManaLevelItem.forming.processor).item(), 1), 1d)
                        .runThis(s -> {
                            manaLevelDataPack.run(s, manaLevel);
                            manaLevelDataPack.run(s, manaLevel);
                        })
                        .addMultipleSurplusTime(4096)
                        .addMultipleConsumeMana(32);
                if (manaLevel.next != null) {
                    new ShapedOre(this, ShapedDrive.get(1), manaLevel)
                            .addInItem(manaLevel.get(ManaLevelItem.operationBasics.host).itemTag(), 1)
                            .addOutItem(new ItemStack(manaLevel.get(ManaLevelItem.forming.host).item(), 1), 1d)
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
            if (manaLevel.hasSet(ManaLevel.destruction)) {
                DataPack.ManaLevelDataPack manaLevelDataPack = manaLevel.getSet(ManaLevel.destruction);
                if (manaLevel.up != null) {
                    new ShapedOre(this, ShapedDrive.get(2), manaLevel)
                            .addInItem(manaLevel.get(ManaLevelItem.operationBasics.integrate).itemTag(), 1)
                            .addOutItem(new ItemStack(manaLevel.get(ManaLevelItem.destruction.integrate).item(), 1), 1d)
                            .runThis(s -> manaLevelDataPack.run(s, manaLevel))
                            .addMultipleSurplusTime(2048)
                            .addMultipleConsumeMana(16);
                }
                new ShapedOre(this, ShapedDrive.get(2), manaLevel)
                        .addInItem(manaLevel.get(ManaLevelItem.operationBasics.processor).itemTag(), 1)
                        .addOutItem(new ItemStack(manaLevel.get(ManaLevelItem.destruction.processor).item(), 1), 1d)
                        .runThis(s -> {
                            manaLevelDataPack.run(s, manaLevel);
                            manaLevelDataPack.run(s, manaLevel);
                        })
                        .addMultipleSurplusTime(4096)
                        .addMultipleConsumeMana(32);
                if (manaLevel.next != null) {
                    new ShapedOre(this, ShapedDrive.get(2), manaLevel)
                            .addInItem(manaLevel.get(ManaLevelItem.operationBasics.host).itemTag(), 1)
                            .addOutItem(new ItemStack(manaLevel.get(ManaLevelItem.destruction.host).item(), 1), 1d)
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
            if (manaLevel.hasSet(ManaLevel.gather)) {
                DataPack.ManaLevelDataPack manaLevelDataPack = manaLevel.getSet(ManaLevel.gather);
                if (manaLevel.up != null) {
                    new ShapedOre(this, ShapedDrive.get(3), manaLevel)
                            .addInItem(manaLevel.get(ManaLevelItem.operationBasics.integrate).itemTag(), 1)
                            .addOutItem(new ItemStack(manaLevel.get(ManaLevelItem.gather.integrate).item(), 1), 1d)
                            .runThis(s -> manaLevelDataPack.run(s, manaLevel))
                            .addMultipleSurplusTime(2048)
                            .addMultipleConsumeMana(16);
                }
                new ShapedOre(this, ShapedDrive.get(3), manaLevel)
                        .addInItem(manaLevel.get(ManaLevelItem.operationBasics.processor).itemTag(), 1)
                        .addOutItem(new ItemStack(manaLevel.get(ManaLevelItem.gather.processor).item(), 1), 1d)
                        .runThis(s -> {
                            manaLevelDataPack.run(s, manaLevel);
                            manaLevelDataPack.run(s, manaLevel);
                        })
                        .addMultipleSurplusTime(4096)
                        .addMultipleConsumeMana(32);
                if (manaLevel.next != null) {
                    new ShapedOre(this, ShapedDrive.get(3), manaLevel)
                            .addInItem(manaLevel.get(ManaLevelItem.operationBasics.host).itemTag(), 1)
                            .addOutItem(new ItemStack(manaLevel.get(ManaLevelItem.gather.host).item(), 1), 1d)
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
            if (manaLevel.hasSet(ManaLevel.spread)) {
                DataPack.ManaLevelDataPack manaLevelDataPack = manaLevel.getSet(ManaLevel.spread);
                if (manaLevel.up != null) {
                    new ShapedOre(this, ShapedDrive.get(4), manaLevel)
                            .addInItem(manaLevel.get(ManaLevelItem.operationBasics.integrate).itemTag(), 1)
                            .addOutItem(new ItemStack(manaLevel.get(ManaLevelItem.spread.integrate).item(), 1), 1d)
                            .runThis(s -> manaLevelDataPack.run(s, manaLevel))
                            .addMultipleSurplusTime(2048)
                            .addMultipleConsumeMana(16);
                }
                new ShapedOre(this, ShapedDrive.get(4), manaLevel)
                        .addInItem(manaLevel.get(ManaLevelItem.operationBasics.processor).itemTag(), 1)
                        .addOutItem(new ItemStack(manaLevel.get(ManaLevelItem.spread.processor).item(), 1), 1d)
                        .runThis(s -> {
                            manaLevelDataPack.run(s, manaLevel);
                            manaLevelDataPack.run(s, manaLevel);
                        })
                        .addMultipleSurplusTime(4096)
                        .addMultipleConsumeMana(32);
                if (manaLevel.next != null) {
                    new ShapedOre(this, ShapedDrive.get(4), manaLevel)
                            .addInItem(manaLevel.get(ManaLevelItem.operationBasics.host).itemTag(), 1)
                            .addOutItem(new ItemStack(manaLevel.get(ManaLevelItem.spread.host).item(), 1), 1d)
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
            if (manaLevel.hasSet(ManaLevel.power)) {
                DataPack.ManaLevelDataPack manaLevelDataPack = manaLevel.getSet(ManaLevel.power);
                if (manaLevel.up != null) {
                    new ShapedOre(this, ShapedDrive.get(5), manaLevel)
                            .addInItem(manaLevel.get(ManaLevelItem.operationBasics.integrate).itemTag(), 1)
                            .addOutItem(new ItemStack(manaLevel.get(ManaLevelItem.power.integrate).item(), 1), 1d)
                            .runThis(s -> manaLevelDataPack.run(s, manaLevel))
                            .addMultipleSurplusTime(2048)
                            .addMultipleConsumeMana(16);
                }
                new ShapedOre(this, ShapedDrive.get(5), manaLevel)
                        .addInItem(manaLevel.get(ManaLevelItem.operationBasics.processor).itemTag(), 1)
                        .addOutItem(new ItemStack(manaLevel.get(ManaLevelItem.power.processor).item(), 1), 1d)
                        .runThis(s -> {
                            manaLevelDataPack.run(s, manaLevel);
                            manaLevelDataPack.run(s, manaLevel);
                        })
                        .addMultipleSurplusTime(4096)
                        .addMultipleConsumeMana(32);
                if (manaLevel.next != null) {
                    new ShapedOre(this, ShapedDrive.get(5), manaLevel)
                            .addInItem(manaLevel.get(ManaLevelItem.operationBasics.host).itemTag(), 1)
                            .addOutItem(new ItemStack(manaLevel.get(ManaLevelItem.power.host).item(), 1), 1d)
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
            if (manaLevel.hasSet(ManaLevel.instructions)) {
                DataPack.ManaLevelDataPack manaLevelDataPack = manaLevel.getSet(ManaLevel.instructions);
                if (manaLevel.up != null) {
                    new ShapedOre(this, ShapedDrive.get(5), manaLevel)
                            .addInItem(manaLevel.get(ManaLevelItem.operationBasics.integrate).itemTag(), 1)
                            .addOutItem(new ItemStack(manaLevel.get(ManaLevelItem.instructions.integrate).item(), 1), 1d)
                            .runThis(s -> manaLevelDataPack.run(s, manaLevel))
                            .addMultipleSurplusTime(2048)
                            .addMultipleConsumeMana(16);
                }
                new ShapedOre(this, ShapedDrive.get(5), manaLevel)
                        .addInItem(manaLevel.get(ManaLevelItem.operationBasics.processor).itemTag(), 1)
                        .addOutItem(new ItemStack(manaLevel.get(ManaLevelItem.instructions.processor).item(), 1), 1d)
                        .runThis(s -> {
                            manaLevelDataPack.run(s, manaLevel);
                            manaLevelDataPack.run(s, manaLevel);
                        })
                        .addMultipleSurplusTime(4096)
                        .addMultipleConsumeMana(32);
                if (manaLevel.next != null) {
                    new ShapedOre(this, ShapedDrive.get(5), manaLevel)
                            .addInItem(manaLevel.get(ManaLevelItem.operationBasics.host).itemTag(), 1)
                            .addOutItem(new ItemStack(manaLevel.get(ManaLevelItem.instructions.host).item(), 1), 1d)
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
