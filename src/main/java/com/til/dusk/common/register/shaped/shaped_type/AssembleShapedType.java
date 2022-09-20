package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.mana_level.ManaLevelItem;
import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreBlock;
import com.til.dusk.common.register.ore.OreFluid;
import com.til.dusk.common.register.ore.OreItem;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.util.Extension;
import com.til.dusk.util.pack.BlockPack;
import com.til.dusk.util.pack.DataPack;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;

import java.util.HashMap;
import java.util.Map;

/**
 * @author til
 */
public class AssembleShapedType extends ShapedType {
    public AssembleShapedType() {
        super("assemble", () -> ManaLevelBlock.assemble);
    }

    @Override
    public void registerShaped() {
        for (Ore ore : Ore.screen(Ore.IS_METAL)) {
            new ShapedOre(this, ShapedDrive.get(0), ore.manaLevel)
                    .addInItem(ore.itemMap.get(OreItem.plate).itemTag(), 2)
                    .addInItem(ore.itemMap.get(OreItem.stick).itemTag(), 2)
                    .addOutItem(new ItemStack(ore.blockMap.get(OreBlock.bracket).blockItem(), 1), 1d)
                    .addMultipleSurplusTime(4096L)
                    .addMultipleConsumeMana(12L);
        }
        for (ManaLevel manaLevel : ManaLevel.LEVEL.get()) {
            for (Map.Entry<ManaLevelBlock, BlockPack> entry : manaLevel.blockMap.entrySet()) {
                if (entry.getKey().hasSet(ManaLevelBlock.MECHANIC_UP_DATA) && manaLevel.up != null) {
                    new ShapedOre(this, ShapedDrive.get(1), manaLevel.up.get())
                            .addInItem(manaLevel.up.get().blockMap.get(entry.getKey()).blockItemTag(), 1)
                            .addInItem(manaLevel.blockMap.get(ManaLevelBlock.frameBasic).blockItemTag(), 1)
                            .addOutItem(new ItemStack(entry.getValue().blockItem(), 1), 1d)
                            .runThis(ManaLevelBlock.MECHANIC_UP_DATA, entry.getKey(), manaLevel)
                            .addMultipleSurplusTime(4096L * manaLevel.level)
                            .addMultipleConsumeMana(32L * manaLevel.level);
                }
                if (entry.getKey().hasSet(ManaLevelBlock.MECHANIC_MAKE_DATA)) {
                    new ShapedOre(this, ShapedDrive.get(2), manaLevel)
                            .addInItem(manaLevel.blockMap.get(ManaLevelBlock.frameBasic).blockItemTag(), 1)
                            .addOutItem(new ItemStack(entry.getValue().blockItem(), 1), 1d)
                            .runThis(ManaLevelBlock.MECHANIC_MAKE_DATA, entry.getKey(), manaLevel)
                            .addMultipleSurplusTime(4096L * manaLevel.level)
                            .addMultipleConsumeMana(32L * manaLevel.level);
                }
            }
        }

        for (Ore ore : Ore.screen(Ore.ARMOR_DATA)) {
            OreItem.ArmorData armorData = ore.getSet(Ore.ARMOR_DATA);
            new ShapedOre(this, ShapedDrive.get(3), ore.manaLevel)
                    .addInItem(ore.itemMap.get(OreItem.plate_4).itemTag(), 32 * ore.manaLevel.level)
                    .addInItem(ore.manaLevel.itemMap.get(ManaLevelItem.operation).itemTag(), 24 * ore.manaLevel.level)
                    .addInItem(ore.manaLevel.itemMap.get(ManaLevelItem.forming).itemTag(), 12 * ore.manaLevel.level)
                    .addInItem(ore.manaLevel.itemMap.get(ManaLevelItem.gather).itemTag(), 8 * ore.manaLevel.level)
                    .addInItem(ore.manaLevel.itemMap.get(ManaLevelItem.instructions).itemTag(), 8 * ore.manaLevel.level)
                    .addInItem(ore.manaLevel.itemMap.get(ManaLevelItem.power).itemTag(), 8 * ore.manaLevel.level)
                    .addInFluid(ore.fluidMap.get(OreFluid.solution).fluidTag(), 65536 * ore.manaLevel.level)
                    .addOutItem(new ItemStack(ore.itemMap.get(OreItem.head).item(), 1), 1d)
                    .addMultipleSurplusTime(16384L * ore.manaLevel.level)
                    .addMultipleConsumeMana(64L * ore.manaLevel.level);
            new ShapedOre(this, ShapedDrive.get(4), ore.manaLevel)
                    .addInItem(ore.itemMap.get(OreItem.plate_4).itemTag(), 64 * ore.manaLevel.level)
                    .addInItem(ore.manaLevel.itemMap.get(ManaLevelItem.operation).itemTag(), 24 * ore.manaLevel.level)
                    .addInItem(ore.manaLevel.itemMap.get(ManaLevelItem.forming).itemTag(), 24 * ore.manaLevel.level)
                    .addInItem(ore.manaLevel.itemMap.get(ManaLevelItem.gather).itemTag(), 24 * ore.manaLevel.level)
                    .addInItem(ore.manaLevel.itemMap.get(ManaLevelItem.instructions).itemTag(), 24 * ore.manaLevel.level)
                    .addInItem(ore.manaLevel.itemMap.get(ManaLevelItem.power).itemTag(), 24 * ore.manaLevel.level)
                    .addInFluid(ore.fluidMap.get(OreFluid.solution).fluidTag(), 65536 * ore.manaLevel.level)
                    .addOutItem(new ItemStack(ore.itemMap.get(OreItem.chest).item(), 1), 1d)
                    .addMultipleSurplusTime(16384L * ore.manaLevel.level)
                    .addMultipleConsumeMana(64L * ore.manaLevel.level);
            new ShapedOre(this, ShapedDrive.get(5), ore.manaLevel)
                    .addInItem(ore.itemMap.get(OreItem.plate_4).itemTag(), 48 * ore.manaLevel.level)
                    .addInItem(ore.manaLevel.itemMap.get(ManaLevelItem.operation).itemTag(), 24 * ore.manaLevel.level)
                    .addInItem(ore.manaLevel.itemMap.get(ManaLevelItem.forming).itemTag(), 24 * ore.manaLevel.level)
                    .addInItem(ore.manaLevel.itemMap.get(ManaLevelItem.gather).itemTag(), 12 * ore.manaLevel.level)
                    .addInItem(ore.manaLevel.itemMap.get(ManaLevelItem.instructions).itemTag(), 12 * ore.manaLevel.level)
                    .addInItem(ore.manaLevel.itemMap.get(ManaLevelItem.power).itemTag(), 12 * ore.manaLevel.level)
                    .addInFluid(ore.fluidMap.get(OreFluid.solution).fluidTag(), 65536 * ore.manaLevel.level)
                    .addOutItem(new ItemStack(ore.itemMap.get(OreItem.legs).item(), 1), 1d)
                    .addMultipleSurplusTime(16384L * ore.manaLevel.level)
                    .addMultipleConsumeMana(64L * ore.manaLevel.level);
            new ShapedOre(this, ShapedDrive.get(6), ore.manaLevel)
                    .addInItem(ore.itemMap.get(OreItem.plate_4).itemTag(), 32 * ore.manaLevel.level)
                    .addInItem(ore.manaLevel.itemMap.get(ManaLevelItem.operation).itemTag(), 24 * ore.manaLevel.level)
                    .addInItem(ore.manaLevel.itemMap.get(ManaLevelItem.forming).itemTag(), 24 * ore.manaLevel.level)
                    .addInItem(ore.manaLevel.itemMap.get(ManaLevelItem.gather).itemTag(), 4 * ore.manaLevel.level)
                    .addInItem(ore.manaLevel.itemMap.get(ManaLevelItem.instructions).itemTag(), 16 * ore.manaLevel.level)
                    .addInItem(ore.manaLevel.itemMap.get(ManaLevelItem.power).itemTag(), 16 * ore.manaLevel.level)
                    .addInFluid(ore.fluidMap.get(OreFluid.solution).fluidTag(), 65536 * ore.manaLevel.level)
                    .addOutItem(new ItemStack(ore.itemMap.get(OreItem.feet).item(), 1), 1d)
                    .addMultipleSurplusTime(16384L * ore.manaLevel.level)
                    .addMultipleConsumeMana(64L * ore.manaLevel.level);
        }
        for (Ore ore : Ore.screen(Ore.ARMS_DATA)) {
            OreItem.ArmsData armorData = ore.getSet(Ore.ARMS_DATA);
            new ShapedOre(this, ShapedDrive.get(7), ore.manaLevel)
                    .addInItem(ore.itemMap.get(OreItem.swordBasics).itemTag(), 1)
                    .addInItem(ore.itemMap.get(OreItem.plate_4).itemTag(), 128 * ore.manaLevel.level)
                    .addInItem(ore.manaLevel.itemMap.get(ManaLevelItem.operation).itemTag(), 32 * ore.manaLevel.level)
                    .addInItem(ore.manaLevel.itemMap.get(ManaLevelItem.destruction).itemTag(), 64 * ore.manaLevel.level)
                    .addInItem(ore.manaLevel.itemMap.get(ManaLevelItem.spread).itemTag(), 48 * ore.manaLevel.level)
                    .addInItem(ore.manaLevel.itemMap.get(ManaLevelItem.instructions).itemTag(), 32 * ore.manaLevel.level)
                    .addInItem(ore.manaLevel.itemMap.get(ManaLevelItem.power).itemTag(), 48 * ore.manaLevel.level)
                    .addInFluid(ore.fluidMap.get(OreFluid.solution).fluidTag(), 65536 * ore.manaLevel.level)
                    .addOutItem(new ItemStack(ore.itemMap.get(OreItem.sword).item(), 1), 1d)
                    .addMultipleSurplusTime(16384L * ore.manaLevel.level)
                    .addMultipleConsumeMana(64L * ore.manaLevel.level);
            new ShapedOre(this, ShapedDrive.get(8), ore.manaLevel)
                    .addInItem(ore.itemMap.get(OreItem.shovelBasics).itemTag(), 1)
                    .addInItem(ore.itemMap.get(OreItem.plate_4).itemTag(), 128 * ore.manaLevel.level)
                    .addInItem(ore.manaLevel.itemMap.get(ManaLevelItem.operation).itemTag(), 12 * ore.manaLevel.level)
                    .addInItem(ore.manaLevel.itemMap.get(ManaLevelItem.destruction).itemTag(), 12 * ore.manaLevel.level)
                    .addInItem(ore.manaLevel.itemMap.get(ManaLevelItem.spread).itemTag(), 4 * ore.manaLevel.level)
                    .addInItem(ore.manaLevel.itemMap.get(ManaLevelItem.instructions).itemTag(), 4 * ore.manaLevel.level)
                    .addInItem(ore.manaLevel.itemMap.get(ManaLevelItem.power).itemTag(), 8 * ore.manaLevel.level)
                    .addInFluid(ore.fluidMap.get(OreFluid.solution).fluidTag(), 65536 * ore.manaLevel.level)
                    .addOutItem(new ItemStack(ore.itemMap.get(OreItem.shovel).item(), 1), 1d)
                    .addMultipleSurplusTime(16384L * ore.manaLevel.level)
                    .addMultipleConsumeMana(64L * ore.manaLevel.level);
            new ShapedOre(this, ShapedDrive.get(9), ore.manaLevel)
                    .addInItem(ore.itemMap.get(OreItem.pickaxeBasics).itemTag(), 1)
                    .addInItem(ore.itemMap.get(OreItem.plate_4).itemTag(), 64 * ore.manaLevel.level)
                    .addInItem(ore.manaLevel.itemMap.get(ManaLevelItem.operation).itemTag(), 32 * ore.manaLevel.level)
                    .addInItem(ore.manaLevel.itemMap.get(ManaLevelItem.destruction).itemTag(), 48 * ore.manaLevel.level)
                    .addInItem(ore.manaLevel.itemMap.get(ManaLevelItem.spread).itemTag(), 12 * ore.manaLevel.level)
                    .addInItem(ore.manaLevel.itemMap.get(ManaLevelItem.instructions).itemTag(), 12 * ore.manaLevel.level)
                    .addInItem(ore.manaLevel.itemMap.get(ManaLevelItem.power).itemTag(), 16 * ore.manaLevel.level)
                    .addInFluid(ore.fluidMap.get(OreFluid.solution).fluidTag(), 65536 * ore.manaLevel.level)
                    .addOutItem(new ItemStack(ore.itemMap.get(OreItem.pickaxe).item(), 1), 1d)
                    .addMultipleSurplusTime(16384L * ore.manaLevel.level)
                    .addMultipleConsumeMana(64L * ore.manaLevel.level);
            new ShapedOre(this, ShapedDrive.get(10), ore.manaLevel)
                    .addInItem(ore.itemMap.get(OreItem.axeBasics).itemTag(), 1)
                    .addInItem(ore.itemMap.get(OreItem.plate_4).itemTag(), 64 * ore.manaLevel.level)
                    .addInItem(ore.manaLevel.itemMap.get(ManaLevelItem.operation).itemTag(), 32 * ore.manaLevel.level)
                    .addInItem(ore.manaLevel.itemMap.get(ManaLevelItem.destruction).itemTag(), 64 * ore.manaLevel.level)
                    .addInItem(ore.manaLevel.itemMap.get(ManaLevelItem.spread).itemTag(), 12 * ore.manaLevel.level)
                    .addInItem(ore.manaLevel.itemMap.get(ManaLevelItem.instructions).itemTag(), 12 * ore.manaLevel.level)
                    .addInItem(ore.manaLevel.itemMap.get(ManaLevelItem.power).itemTag(), 12 * ore.manaLevel.level)
                    .addInFluid(ore.fluidMap.get(OreFluid.solution).fluidTag(), 65536 * ore.manaLevel.level)
                    .addOutItem(new ItemStack(ore.itemMap.get(OreItem.axe).item(), 1), 1d)
                    .addMultipleSurplusTime(16384L * ore.manaLevel.level)
                    .addMultipleConsumeMana(64L * ore.manaLevel.level);
            new ShapedOre(this, ShapedDrive.get(11), ore.manaLevel)
                    .addInItem(ore.itemMap.get(OreItem.hoeBasics).itemTag(), 1)
                    .addInItem(ore.itemMap.get(OreItem.plate_4).itemTag(), 64 * ore.manaLevel.level)
                    .addInItem(ore.manaLevel.itemMap.get(ManaLevelItem.operation).itemTag(), 16 * ore.manaLevel.level)
                    .addInItem(ore.manaLevel.itemMap.get(ManaLevelItem.destruction).itemTag(), 4 * ore.manaLevel.level)
                    .addInItem(ore.manaLevel.itemMap.get(ManaLevelItem.spread).itemTag(), 12 * ore.manaLevel.level)
                    .addInItem(ore.manaLevel.itemMap.get(ManaLevelItem.instructions).itemTag(), 8 * ore.manaLevel.level)
                    .addInItem(ore.manaLevel.itemMap.get(ManaLevelItem.power).itemTag(), 8 * ore.manaLevel.level)
                    .addInFluid(ore.fluidMap.get(OreFluid.solution).fluidTag(), 65536 * ore.manaLevel.level)
                    .addOutItem(new ItemStack(ore.itemMap.get(OreItem.hoe).item(), 1), 1d)
                    .addMultipleSurplusTime(16384L * ore.manaLevel.level)
                    .addMultipleConsumeMana(64L * ore.manaLevel.level);

        }
    }
}
