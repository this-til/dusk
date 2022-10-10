package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.config.util.IShapedCreate;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.Shaped;

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
        for (Ore ore : Ore.ORE.get()) {
            if (!ore.isMetal) {
                continue;
            }
            shapedConsumer.accept(freezing.create(ore));
        }
    }

    @Override
    public void defaultConfig() {
        freezing = new IShapedCreate.OreShapedCreate(name, this, ShapedDrive.get(0), 724L, 32L, 0L);
    }

    @ConfigField
    public IShapedCreate<Ore> freezing;

}
