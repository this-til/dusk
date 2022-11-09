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
public class BlastFurnaceShapedType extends ShapedType {
    public BlastFurnaceShapedType() {
        super("blast_furnace");
    }


    @Override
    public void registerRuleShaped(Consumer<Shaped> shapedConsumer) {

        for (Ore ore : Ore.ORE.get()) {
            if (!ore.isMetal) {
                continue;
            }
            if (!ore.hasDust) {
                continue;
            }
            shapedConsumer.accept(blastFurnace.create(ore));
        }
    }

    @Override
    public void defaultConfig() {      blockTagKey = new Delayed.BlockDelayed(() -> ManaLevelBlock.blastFurnace.tagPackSupplier.getTagPack().blockTagKey());
        blastFurnace = new IShapedCreate.OreShapedCreate(name, this, ShapedDrive.get(0), 1024L, 32L, 0)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.dust.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemOut(OreItem.ingot, 1,1));
    }

    @ConfigField
    public IShapedCreate<Ore> blastFurnace;

}
