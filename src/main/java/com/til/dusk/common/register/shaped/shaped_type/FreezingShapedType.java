package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.ore.fluid.OreFluid;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;

/**
 * @author til
 */
public class FreezingShapedType extends ShapedType {
    public FreezingShapedType() {
        super("freezing", () -> ManaLevelBlock.freezing);
    }

    @Override
    public void registerRuleShaped(Consumer<Shaped> shapedConsumer) {
        for (Ore ore : Ore.screen(Ore.IS_METAL, Ore.FLUID_DATA)) {
            new ShapedOre(this, ShapedDrive.get(0), ore.manaLevel)
                    .addInFluid(ore.get(OreFluid.solution).fluidTag(), 144)
                    .addOutItem(new ItemStack(ore.get(OreItem.ingot).item(), 1), 1D)
                    .addMultipleSurplusTime((long) (ore.strength * 724L))
                    .addMultipleConsumeMana((long) (ore.consume * 32L));
        }
    }
}
