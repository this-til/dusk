package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.common.config.util.IShapedCreate;
import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author til
 */
public class ExtractManaShapedType extends ShapedType {

    public ExtractManaShapedType() {
        super("extract_mana", () -> ManaLevelBlock.extractMana);
    }

    @Override
    public void registerRuleShaped(Consumer<Shaped> shapedConsumer) {
        for (Ore ore : Ore.ORE.get()) {
            if (ore.defaultMana > 0) {
                if (ore.isMetal) {
                    shapedConsumer.accept(metalManaExtract.create(ore));
                }
                if (ore.isCrysta) {
                    shapedConsumer.accept(crystaManaExtract.create(ore));
                }
            }
        }
    }

    @Override
    public void defaultConfig() {
        relevantShaped = new Delayed.ListShapedDelayed(() -> List.of(
                new ShapedOre(name, this, ShapedDrive.get(1), ManaLevel.t1)
                        .addMultipleSurplusTime(1024)
                        .addMultipleOutMana(20000)
        ));
        metalManaExtract = new IShapedCreate.OreShapedCreate(new ResourceLocation(name.getNamespace(), "metal"),
                this, ShapedDrive.get(0), 1024, 0, 0)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.ingot.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreDefaultManaOut());
        crystaManaExtract = new IShapedCreate.OreShapedCreate(new ResourceLocation(name.getNamespace(), "crysta"),
                this, ShapedDrive.get(0), 1024, 0, 0)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.crystal.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreDefaultManaOut());
    }

    @ConfigField
    public IShapedCreate<Ore> metalManaExtract;
    @ConfigField
    public IShapedCreate<Ore> crystaManaExtract;
}
