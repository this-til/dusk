package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.config.util.Delayed;
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
public class PackShapedType extends ShapedType {

    public PackShapedType() {
        super("pack");
    }

    @Override
    public void registerRuleShaped(Consumer<Shaped> shapedConsumer) {
        for (Ore ore : Ore.ORE.get()) {
            if (ore.isMetal && ore.decorateBlockData != null) {
                shapedConsumer.accept(blockIngot.create(ore));
            }
            if (ore.isCrysta && ore.decorateBlockData != null) {
                shapedConsumer.accept(blockCrystal.create(ore));
            }
            if (ore.isMetal) {
                shapedConsumer.accept(ingot.create(ore));
            }
            if (ore.hasDust) {
                shapedConsumer.accept(dust.create(ore));
            }
        }
    }

    @Override
    public void defaultConfig() {
        blockTagKey =new Delayed.BlockDelayed(() ->  ManaLevelBlock.pack.tagPackSupplier.getTagPack().blockTagKey());
        blockIngot = new IShapedCreate.OreShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(), "block_ingot"),
                this, ShapedDrive.get(0), 128L, 4L, 0)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.ingot.name, 9))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreBlockOut(OreBlock.block, 1, 1));
        blockCrystal = new IShapedCreate.OreShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(), "block_crystal"),
                this, ShapedDrive.get(1), 128L, 4L, 0)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.crystal.name, 9))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreBlockOut(OreBlock.block, 1, 1));
        ingot = new IShapedCreate.OreShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(), "ingot"),
                this, ShapedDrive.get(2), 128L, 4L, 0)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.dustTiny.name, 9))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemOut(OreItem.ingot, 1, 1));
        dust = new IShapedCreate.OreShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(), "dust"),
                this, ShapedDrive.get(3), 128L, 4L, 0)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.dustTiny.name, 9))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemOut(OreItem.dust, 1, 1));
    }

    @ConfigField
    public IShapedCreate<Ore> blockIngot;

    @ConfigField
    public IShapedCreate<Ore> blockCrystal;

    @ConfigField
    public IShapedCreate<Ore> ingot;

    @ConfigField
    public IShapedCreate<Ore> dust;

}
