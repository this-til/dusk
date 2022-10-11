package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.config.util.IShapedCreate;
import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.common.data.tag.FluidTag;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import net.minecraft.tags.FluidTags;

import java.util.function.Consumer;

/**
 * @author til
 */
public class WashShapedType extends ShapedType {

    public WashShapedType() {
        super("wash", () -> ManaLevelBlock.wash);
    }

    @Override
    public void registerRuleShaped(Consumer<Shaped> shapedConsumer) {
        for (Ore ore : Ore.ORE.get()) {
            if (ore.mineralBlockData == null) {
                continue;
            }
            Shaped shaped = wash.create(ore);
            if (ore.mineralBlockData.washByproduct != null) {
                for (IShapedOreConfig<Void> voidShapedOreConfig : ore.mineralBlockData.washByproduct) {
                    voidShapedOreConfig.config((ShapedOre) shaped, null);
                }
            }
            shapedConsumer.accept(shaped);
        }
    }

    @Override
    public void defaultConfig() {
        wash = new IShapedCreate.OreShapedCreate(name, this, ShapedDrive.get(0), 1280L, 12L, 0)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.crushed.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.FluidIn(() -> FluidTags.WATER, 1000))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemOut(OreItem.crushedPurified, 1, 1));
    }

    @ConfigField
    public IShapedCreate<Ore> wash;

}
