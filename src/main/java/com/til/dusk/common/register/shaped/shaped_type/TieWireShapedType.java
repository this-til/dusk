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

public class TieWireShapedType extends ShapedType {

    public TieWireShapedType() {
        super("tie_wire", () -> ManaLevelBlock.tieWire);
    }

    @Override
    public void registerRuleShaped(Consumer<Shaped> shapedConsumer) {
        for (Ore ore : Ore.screen(Ore.IS_METAL)) {
            new ShapedOre(this, ShapedDrive.get(1), ore.manaLevel)
                    .addInItem(ore.get(OreItem.stick).itemTag(), 1)
                    .addOutItem(new ItemStack(ore.get(OreItem.string).item(), 6), 1D)
                    .addMultipleSurplusTime((long) (ore.strength * 1024L))
                    .addMultipleConsumeMana((long) (ore.consume * 12L));
        }
    }

    @Override
    public void defaultConfig() {

    }
}
