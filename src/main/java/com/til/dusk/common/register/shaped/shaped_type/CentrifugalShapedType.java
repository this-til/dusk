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
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;

import java.util.function.Consumer;

/**
 * @author til
 */
public class CentrifugalShapedType extends ShapedType {

    public CentrifugalShapedType() {
        super("centrifugal");
    }

    @Override
    public void registerRuleShaped(Consumer<Shaped> shapedConsumer) {

        for (Ore ore : Ore.ORE.get()) {
            if (ore.mineralBlockData == null) {
                continue;
            }
            Shaped shaped = centrifugal.create(ore);
            if (ore.mineralBlockData.centrifugeByproduct != null) {
                for (IShapedOreConfig<Void> voidShapedOreConfig : ore.mineralBlockData.centrifugeByproduct) {
                    voidShapedOreConfig.config((ShapedOre) shaped, null);
                }
            }
            shapedConsumer.accept(shaped);
        }
    }

    @Override
    public void defaultConfig() {blockTagKey =new Delayed.BlockDelayed(() ->  ManaLevelBlock.centrifugal.tagPackSupplier.getTagPack().blockTagKey());
        centrifugal = new IShapedCreate.OreShapedCreate(name, this, ShapedDrive.get(0), 1280L, 24L, 0L)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.crushedPurified.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemOut(OreItem.dust, 1, 1));
    }

    @ConfigField
    public IShapedCreate<Ore> centrifugal;

}
