package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
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
public class PressureStickShapedType extends ShapedType {

    public PressureStickShapedType() {
        super("pressure_stick", () -> ManaLevelBlock.pressureStick);
    }

    @Override
    public void registerRuleShaped(Consumer<Shaped> shapedConsumer) {
        for (Ore ore : Ore.screen(Ore.IS_METAL)) {
            new ShapedOre(this, ShapedDrive.get(0), ore.manaLevel)
                    .addInItem(ore.get(OreItem.ingot).itemTag(), 1)
                    .addOutItem(new ItemStack(ore.get(OreItem.stick).item()), 1d)
                    .addMultipleSurplusTime((long) (ore.strength * 512))
                    .addMultipleConsumeMana((long) (ore.consume * 32L));
        }
    }

    @Override
    public void defaultConfig() {

    }
}
