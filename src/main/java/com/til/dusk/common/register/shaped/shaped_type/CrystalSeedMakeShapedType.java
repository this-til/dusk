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
import net.minecraft.tags.ItemTags;

import java.util.function.Consumer;

/**
 * @author til
 */
public class CrystalSeedMakeShapedType extends ShapedType {

    public CrystalSeedMakeShapedType() {
        super("crystal_seed_make");
    }

    @Override
    public void registerRuleShaped(Consumer<Shaped> shapedConsumer) {

        for (Ore ore : Ore.ORE.get()) {
            if (!ore.isCrysta) {
                continue;
            }
            if (!ore.hasDust) {
                continue;
            }
            if (ore.fluidData == null) {
                continue;
            }
            shapedConsumer.accept(crystalSeedMake.create(ore));
        }
    }

    @Override
    public void defaultConfig() {        blockTagKey = new Delayed.BlockDelayed(() -> ManaLevelBlock.crystalSeedMake.tagPackSupplier.getTagPack().blockTagKey());
        crystalSeedMake = new IShapedCreate.OreShapedCreate(name, this, ShapedDrive.get(0), 256L, 12L, 0)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.ItemIn(() -> ItemTags.SAND, 1))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.dust.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemOut(OreItem.crystalSeed, 2, 1));
    }

    @ConfigField
    public IShapedCreate<Ore> crystalSeedMake;
}
