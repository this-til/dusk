package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.config.util.IShapedCreate;
import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.Shaped;

import java.util.function.Consumer;

public class FormingShapedType extends ShapedType {

    public FormingShapedType() {
        super("forming", () -> ManaLevelBlock.shaping);
    }

    @Override
    public void registerRuleShaped(Consumer<Shaped> shapedConsumer) {
        for (Ore ore : Ore.ORE.get()) {
            if (!ore.isMetal) {
             continue;
            }
            shapedConsumer.accept(casing.create(ore));
        }
    }

    @Override
    public void defaultConfig() {
        casing = new IShapedCreate.OreShapedCreate(name, this, ShapedDrive.get(0), 1024L, 12L, 0L)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.ingot.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemOut(OreItem.casing, 1, 1));
    }

    @ConfigField
    public IShapedCreate<Ore> casing;
}
