package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreFluid;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import net.minecraft.world.item.ItemStack;

/**
 * @author til
 */
public class FreezingShapedType extends ShapedType {
    public FreezingShapedType() {
        super("freezing", () -> ManaLevelBlock.freezing);
    }

    @Override
    public void registerShaped() {
        for (Ore ore : Ore.screen(Ore.IS_METAL, Ore.FLUID_DATA)) {
            new ShapedOre(this, ShapedDrive.get(0), ore.manaLevel)
                    .addInFluid(ore.fluidMap.get(OreFluid.solution).fluidTag(), 144)
                    .addOutItem(new ItemStack(ore.itemMap.get(OreItem.ingot).item(), 1), 1D)
                    .addMultipleSurplusTime((long) (ore.strength * 724L))
                    .addMultipleConsumeMana((long) (ore.consume * 32L));
        }
    }
}
