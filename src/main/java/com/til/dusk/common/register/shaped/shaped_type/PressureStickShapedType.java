package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.config.util.IShapedCreate;
import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.shaped.shapeds.Shaped;

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
        for (Ore ore : Ore.ORE.get()) {
            if (!ore.isMetal) {
                continue;
            }
            shapedConsumer.accept(pressureStick.create(ore));
        }
    }



    @Override
    public void defaultConfig() {
        pressureStick = new IShapedCreate.OreShapedCreate(name)
                .setSurplusTime(1280L)
                .setConsumeMana(12L)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.ingot.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemOut(OreItem.stick, 1, 1));
    }

    @ConfigField
    public IShapedCreate<Ore> pressureStick;
}
