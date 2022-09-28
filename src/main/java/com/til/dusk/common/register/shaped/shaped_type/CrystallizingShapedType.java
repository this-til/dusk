package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreFluid;
import com.til.dusk.common.register.ore.OreItem;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;

import java.util.Map;

/**
 * @author til
 */
public class CrystallizingShapedType extends ShapedType {

    public CrystallizingShapedType() {
        super("crystallizing", () -> ManaLevelBlock.crystallizing);
    }

    @Override
    public void registerShaped() {
        for (Ore ore : Ore.screen(Ore.IS_CRYSTA, Ore.FLUID_DATA)) {
            new ShapedOre(this, ShapedDrive.get(0), ore.manaLevel)
                    .addInItem(ore.itemMap.get(OreItem.crystalSeed).itemTag(), 1)
                    .addInFluid(ore.fluidMap.get(OreFluid.solution).fluidTag(), 288)
                    .addOutItem(new ItemStack(ore.itemMap.get(OreItem.crystal).item(), 1), 1d)
                    .addMultipleSurplusTime((long) (ore.strength * 2048L))
                    .addMultipleConsumeMana((long) (ore.consume * 8L));
            new ShapedOre(this, ShapedDrive.get(1), ore.manaLevel)
                    .addInItem(ore.itemMap.get(OreItem.crystal).itemTag(), 1)
                    .addInFluid(ore.fluidMap.get(OreFluid.solution).fluidTag(), 576)
                    .addOutItem(new ItemStack(ore.itemMap.get(OreItem.delicateCrystal).item(), 1), 1d)
                    .addMultipleSurplusTime((long) (ore.strength * 4096L))
                    .addMultipleConsumeMana((long) (ore.consume * 8L));
            new ShapedOre(this, ShapedDrive.get(2), ore.manaLevel)
                    .addInItem(ore.itemMap.get(OreItem.delicateCrystal).itemTag(), 1)
                    .addInFluid(ore.fluidMap.get(OreFluid.solution).fluidTag(), 1152)
                    .addOutItem(new ItemStack(ore.itemMap.get(OreItem.perfectCrystal).item(), 1), 1d)
                    .addMultipleSurplusTime((long) (ore.strength * 8192L))
                    .addMultipleConsumeMana((long) (ore.consume * 8L));
        }
    }

}
