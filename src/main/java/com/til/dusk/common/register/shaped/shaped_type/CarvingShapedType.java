package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.config.util.IShapedCreate;
import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.mana_level.item.ManaLevelItemPack;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.util.ResourceLocationUtil;

import java.util.function.Consumer;

/**
 * @author til
 */
public class CarvingShapedType extends ShapedType {

    public CarvingShapedType() {
        super("carving", () -> ManaLevelBlock.carving);
    }

    @Override
    public void registerRuleShaped(Consumer<Shaped> shapedConsumer) {
        for (ManaLevel level : ManaLevel.MANA_LEVEL.get()) {
            if (level.operationBasics == null) {
                continue;
            }
            if (level.getUp() != null) {
                Shaped shaped = integrate.create(level);
                for (IShapedOreConfig<ManaLevel> manaLevelIShapedOreConfig : level.operationBasics.get()) {
                    manaLevelIShapedOreConfig.config((ShapedOre) shaped, level);
                }
                shapedConsumer.accept(shaped);
            }
            {
                Shaped shaped = processor.create(level);
                for (IShapedOreConfig<ManaLevel> manaLevelIShapedOreConfig : level.operationBasics.get()) {
                    manaLevelIShapedOreConfig.config((ShapedOre) shaped, level);
                    manaLevelIShapedOreConfig.config((ShapedOre) shaped, level);
                }
                shapedConsumer.accept(shaped);
            }

            if (level.getNext() != null) {
                Shaped shaped = host.create(level);
                for (IShapedOreConfig<ManaLevel> manaLevelIShapedOreConfig : level.operationBasics.get()) {
                    manaLevelIShapedOreConfig.config((ShapedOre) shaped, level);
                    manaLevelIShapedOreConfig.config((ShapedOre) shaped, level);
                    manaLevelIShapedOreConfig.config((ShapedOre) shaped, level);
                    manaLevelIShapedOreConfig.config((ShapedOre) shaped, level);
                }
                shapedConsumer.accept(shaped);
            }
        }
    }

    @Override
    public void defaultConfig() {
        integrate = new IShapedCreate.ManaLevelShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(),  "integrate"),
                this, ShapedDrive.get(0), 1024L, 16L, 0L)
                .addConfig(new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(OreItem.crystal.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemOut(ManaLevelItemPack.operationBasics.integrate, 1, 1));
        processor = new IShapedCreate.ManaLevelShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(), "processor"),
                this, ShapedDrive.get(0), 2048L, 32L, 0L)
                .addConfig(new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(OreItem.delicateCrystal.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemOut(ManaLevelItemPack.operationBasics.processor, 1, 1));
        host = new IShapedCreate.ManaLevelShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(), "host"),
                this, ShapedDrive.get(0), 4096L, 42L, 0L)
                .addConfig(new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(OreItem.perfectCrystal.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemOut(ManaLevelItemPack.operationBasics.host, 1, 1));
    }

    @ConfigField
    public IShapedCreate<ManaLevel> integrate;
    @ConfigField
    public IShapedCreate<ManaLevel> processor;
    @ConfigField
    public IShapedCreate<ManaLevel> host;

}
