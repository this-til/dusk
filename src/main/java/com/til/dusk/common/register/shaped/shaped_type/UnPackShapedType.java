package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.config.util.IShapedCreate;
import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.ore.block.OreBlock;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.util.ResourceLocationUtil;

import java.util.function.Consumer;

/**
 * @author til
 */
public class UnPackShapedType extends ShapedType {

    public UnPackShapedType() {
        super("unpack", () -> ManaLevelBlock.unpack);
    }

    @Override
    public void registerRuleShaped(Consumer<Shaped> shapedConsumer) {
        for (Ore ore : Ore.ORE.get()) {

            if (ore.isMetal && ore.decorateBlockData != null) {
                shapedConsumer.accept(ingot.create(ore));
            }
            if (ore.isCrysta && ore.decorateBlockData != null) {
                shapedConsumer.accept(crystal.create(ore));
            }
            if (ore.isMetal) {
                shapedConsumer.accept(nuggets.create(ore));
            }
            if (ore.hasDust) {
                shapedConsumer.accept(dustTiny.create(ore));
            }
        }
    }

    @Override
    public void defaultConfig() {
        ingot = new IShapedCreate.OreShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(), "ingot"),
                this, ShapedDrive.get(0), 128L, 4L, 0L)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreBlock.block.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemOut(OreItem.ingot, 9, 1));
        crystal = new IShapedCreate.OreShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(), "crystal"),
                this, ShapedDrive.get(1), 128L, 4L, 0L)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreBlock.block.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemOut(OreItem.crystal, 9, 1));
        nuggets = new IShapedCreate.OreShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(), "nuggets"),
                this, ShapedDrive.get(2), 128L, 4L, 0L)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.ingot.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemOut(OreItem.nuggets, 9, 1));
        dustTiny = new IShapedCreate.OreShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(), "dust_tiny"),
                this, ShapedDrive.get(3), 128L, 4L, 0L)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.dust.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemOut(OreItem.dustTiny, 9, 1));
    }

    @ConfigField
    public IShapedCreate<Ore> ingot;

    @ConfigField
    public IShapedCreate<Ore> crystal;

    @ConfigField
    public IShapedCreate<Ore> nuggets;

    @ConfigField
    public IShapedCreate<Ore> dustTiny;
}
