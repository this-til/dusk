package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.common.config.util.IShapedCreate;
import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.Shaped;

import java.util.function.Consumer;

/**
 * @author til
 */

public class TieWireShapedType extends ShapedType {

    public TieWireShapedType() {
        super("tie_wire");
    }

    @Override
    public void registerRuleShaped(Consumer<Shaped> shapedConsumer) {
        for (Ore ore : Ore.ORE.get()) {
            if (!ore.isMetal) {
                continue;
            }
            shapedConsumer.accept(tieWire.create(ore));
        }
    }

    @ConfigField
    public IShapedCreate<Ore> tieWire;

    @Override
    public void defaultConfig() {        blockTagKey = new Delayed.BlockDelayed(() -> ManaLevelBlock.tieWire.tagPackSupplier.getTagPack().blockTagKey());
        tieWire = new IShapedCreate.OreShapedCreate(name, this, ShapedDrive.get(0), 1024L, 12L, 0L)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.stick.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemOut(OreItem.string, 6, 1));
    }
}
