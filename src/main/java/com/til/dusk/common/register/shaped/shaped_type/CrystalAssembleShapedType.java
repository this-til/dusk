package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.config.util.IShapedCreate;
import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.mana_level.item.ManaLevelItem;
import com.til.dusk.common.register.mana_level.item.ManaLevelItemPack;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.block.OreBlock;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.util.ResourceLocationUtil;
import com.til.dusk.util.pack.DataPack;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.function.Consumer;

public class CrystalAssembleShapedType extends ShapedType {

    public CrystalAssembleShapedType() {
        super("crystal_assemble", () -> ManaLevelBlock.crystalAssemble);
    }

    @Override
    public void registerRuleShaped(Consumer<Shaped> shapedConsumer) {
        for (ManaLevel level : ManaLevel.MANA_LEVEL.get()) {
            if (level.operation != null) {
                List<IShapedOreConfig<ManaLevel>> list = level.operation.get();
                if (level.getUp() != null) {
                    Shaped shaped = operation_integrate.create(level);
                    for (IShapedOreConfig<ManaLevel> manaLevelIShapedOreConfig : list) {
                        manaLevelIShapedOreConfig.config((ShapedOre) shaped, level);
                    }
                    shapedConsumer.accept(shaped);
                }
                {
                    Shaped shaped = operation_processor.create(level);
                    for (IShapedOreConfig<ManaLevel> manaLevelIShapedOreConfig : list) {
                        manaLevelIShapedOreConfig.config((ShapedOre) shaped, level);
                    }
                    shapedConsumer.accept(shaped);
                }
                if (level.getNext() != null) {
                    Shaped shaped = operation_host.create(level);
                    for (IShapedOreConfig<ManaLevel> manaLevelIShapedOreConfig : list) {
                        manaLevelIShapedOreConfig.config((ShapedOre) shaped, level);
                    }
                    shapedConsumer.accept(shaped);
                }
            }
            if (level.forming != null) {
                List<IShapedOreConfig<ManaLevel>> list = level.forming.get();
                if (level.getUp() != null) {
                    Shaped shaped = forming_integrate.create(level);
                    for (IShapedOreConfig<ManaLevel> manaLevelIShapedOreConfig : list) {
                        manaLevelIShapedOreConfig.config((ShapedOre) shaped, level);
                    }
                    shapedConsumer.accept(shaped);
                }
                {
                    Shaped shaped = forming_processor.create(level);
                    for (IShapedOreConfig<ManaLevel> manaLevelIShapedOreConfig : list) {
                        manaLevelIShapedOreConfig.config((ShapedOre) shaped, level);
                    }
                    shapedConsumer.accept(shaped);
                }
                if (level.getNext() != null) {
                    Shaped shaped = forming_host.create(level);
                    for (IShapedOreConfig<ManaLevel> manaLevelIShapedOreConfig : list) {
                        manaLevelIShapedOreConfig.config((ShapedOre) shaped, level);
                    }
                    shapedConsumer.accept(shaped);
                }
            }
            if (level.destruction != null) {
                List<IShapedOreConfig<ManaLevel>> list = level.destruction.get();
                if (level.getUp() != null) {
                    Shaped shaped = destruction_integrate.create(level);
                    for (IShapedOreConfig<ManaLevel> manaLevelIShapedOreConfig : list) {
                        manaLevelIShapedOreConfig.config((ShapedOre) shaped, level);
                    }
                    shapedConsumer.accept(shaped);
                }
                {
                    Shaped shaped = destruction_processor.create(level);
                    for (IShapedOreConfig<ManaLevel> manaLevelIShapedOreConfig : list) {
                        manaLevelIShapedOreConfig.config((ShapedOre) shaped, level);
                    }
                    shapedConsumer.accept(shaped);
                }
                if (level.getNext() != null) {
                    Shaped shaped = destruction_host.create(level);
                    for (IShapedOreConfig<ManaLevel> manaLevelIShapedOreConfig : list) {
                        manaLevelIShapedOreConfig.config((ShapedOre) shaped, level);
                    }
                    shapedConsumer.accept(shaped);
                }
            }
            if (level.gather != null) {
                List<IShapedOreConfig<ManaLevel>> list = level.gather.get();
                if (level.getUp() != null) {
                    Shaped shaped = gather_integrate.create(level);
                    for (IShapedOreConfig<ManaLevel> manaLevelIShapedOreConfig : list) {
                        manaLevelIShapedOreConfig.config((ShapedOre) shaped, level);
                    }
                    shapedConsumer.accept(shaped);
                }
                {
                    Shaped shaped = gather_processor.create(level);
                    for (IShapedOreConfig<ManaLevel> manaLevelIShapedOreConfig : list) {
                        manaLevelIShapedOreConfig.config((ShapedOre) shaped, level);
                    }
                    shapedConsumer.accept(shaped);
                }
                if (level.getNext() != null) {
                    Shaped shaped = gather_host.create(level);
                    for (IShapedOreConfig<ManaLevel> manaLevelIShapedOreConfig : list) {
                        manaLevelIShapedOreConfig.config((ShapedOre) shaped, level);
                    }
                    shapedConsumer.accept(shaped);
                }
            }
            if (level.spread != null) {
                List<IShapedOreConfig<ManaLevel>> list = level.spread.get();
                if (level.getUp() != null) {
                    Shaped shaped = spread_integrate.create(level);
                    for (IShapedOreConfig<ManaLevel> manaLevelIShapedOreConfig : list) {
                        manaLevelIShapedOreConfig.config((ShapedOre) shaped, level);
                    }
                    shapedConsumer.accept(shaped);
                }
                {
                    Shaped shaped = spread_processor.create(level);
                    for (IShapedOreConfig<ManaLevel> manaLevelIShapedOreConfig : list) {
                        manaLevelIShapedOreConfig.config((ShapedOre) shaped, level);
                    }
                    shapedConsumer.accept(shaped);
                }
                if (level.getNext() != null) {
                    Shaped shaped = spread_host.create(level);
                    for (IShapedOreConfig<ManaLevel> manaLevelIShapedOreConfig : list) {
                        manaLevelIShapedOreConfig.config((ShapedOre) shaped, level);
                    }
                    shapedConsumer.accept(shaped);
                }
            }
            if (level.power != null) {
                List<IShapedOreConfig<ManaLevel>> list = level.power.get();
                if (level.getUp() != null) {
                    Shaped shaped = power_integrate.create(level);
                    for (IShapedOreConfig<ManaLevel> manaLevelIShapedOreConfig : list) {
                        manaLevelIShapedOreConfig.config((ShapedOre) shaped, level);
                    }
                    shapedConsumer.accept(shaped);
                }
                {
                    Shaped shaped = power_processor.create(level);
                    for (IShapedOreConfig<ManaLevel> manaLevelIShapedOreConfig : list) {
                        manaLevelIShapedOreConfig.config((ShapedOre) shaped, level);
                    }
                    shapedConsumer.accept(shaped);
                }
                if (level.getNext() != null) {
                    Shaped shaped = power_host.create(level);
                    for (IShapedOreConfig<ManaLevel> manaLevelIShapedOreConfig : list) {
                        manaLevelIShapedOreConfig.config((ShapedOre) shaped, level);
                    }
                    shapedConsumer.accept(shaped);
                }
            }
            if (level.instructions != null) {
                List<IShapedOreConfig<ManaLevel>> list = level.instructions.get();
                if (level.getUp() != null) {
                    Shaped shaped = instructions_integrate.create(level);
                    for (IShapedOreConfig<ManaLevel> manaLevelIShapedOreConfig : list) {
                        manaLevelIShapedOreConfig.config((ShapedOre) shaped, level);
                    }
                    shapedConsumer.accept(shaped);
                }
                {
                    Shaped shaped = instructions_processor.create(level);
                    for (IShapedOreConfig<ManaLevel> manaLevelIShapedOreConfig : list) {
                        manaLevelIShapedOreConfig.config((ShapedOre) shaped, level);
                    }
                    shapedConsumer.accept(shaped);
                }
                if (level.getNext() != null) {
                    Shaped shaped = instructions_host.create(level);
                    for (IShapedOreConfig<ManaLevel> manaLevelIShapedOreConfig : list) {
                        manaLevelIShapedOreConfig.config((ShapedOre) shaped, level);
                    }
                    shapedConsumer.accept(shaped);
                }
            }
        }
    }

    @Override
    public void defaultConfig() {
        operation_integrate = new IShapedCreate.ManaLevelShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{name.getPath(), "operation_integrate"}),
                this, ShapedDrive.get(0), 2048, 16, 0L)
                .addConfig(new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.operationBasics.integrate.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemOut(ManaLevelItemPack.operation.integrate, 1, 1));
        operation_processor = new IShapedCreate.ManaLevelShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{name.getPath(), "operation_processor"}),
                this, ShapedDrive.get(0), 4096, 32, 0L)
                .addConfig(new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.operationBasics.processor.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemOut(ManaLevelItemPack.operation.processor, 1, 1));
        operation_host = new IShapedCreate.ManaLevelShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{name.getPath(), "operation_host"}),
                this, ShapedDrive.get(0), 8192, 45, 0L)
                .addConfig(new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.operationBasics.host.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemOut(ManaLevelItemPack.operation.host, 1, 1));
        forming_integrate = new IShapedCreate.ManaLevelShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{name.getPath(), "forming_integrate"}),
                this, ShapedDrive.get(1), 2048, 16, 0L)
                .addConfig(new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.operationBasics.integrate.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemOut(ManaLevelItemPack.forming.integrate, 1, 1));
        forming_processor = new IShapedCreate.ManaLevelShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{name.getPath(), "forming_processor"}),
                this, ShapedDrive.get(1), 4096, 32, 0L)
                .addConfig(new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.operationBasics.processor.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemOut(ManaLevelItemPack.forming.processor, 1, 1));
        forming_host = new IShapedCreate.ManaLevelShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{name.getPath(), "forming_host"}),
                this, ShapedDrive.get(1), 8192, 42, 0L)
                .addConfig(new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.operationBasics.host.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemOut(ManaLevelItemPack.forming.host, 1, 1));
        destruction_integrate = new IShapedCreate.ManaLevelShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{name.getPath(), "destruction_integrate"}),
                this, ShapedDrive.get(2), 2048, 16, 0L)
                .addConfig(new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.operationBasics.integrate.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemOut(ManaLevelItemPack.destruction.integrate, 1, 1));
        destruction_processor = new IShapedCreate.ManaLevelShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{name.getPath(), "destruction_processor"}),
                this, ShapedDrive.get(2), 4096, 32, 0L)
                .addConfig(new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.operationBasics.processor.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemOut(ManaLevelItemPack.destruction.processor, 1, 1));
        destruction_host = new IShapedCreate.ManaLevelShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{name.getPath(), "destruction_host"}),
                this, ShapedDrive.get(2), 8192, 42, 0L)
                .addConfig(new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.operationBasics.host.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemOut(ManaLevelItemPack.destruction.host, 1, 1));
        gather_integrate = new IShapedCreate.ManaLevelShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{name.getPath(), "gather_integrate"}),
                this, ShapedDrive.get(3), 2048, 16, 0L)
                .addConfig(new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.operationBasics.integrate.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemOut(ManaLevelItemPack.gather.integrate, 1, 1));
        gather_processor = new IShapedCreate.ManaLevelShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{name.getPath(), "gather_processor"}),
                this, ShapedDrive.get(3), 4096, 32, 0L)
                .addConfig(new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.operationBasics.processor.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemOut(ManaLevelItemPack.gather.processor, 1, 1));
        gather_host = new IShapedCreate.ManaLevelShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{name.getPath(), "gather_host"}),
                this, ShapedDrive.get(3), 8192, 42, 0L)
                .addConfig(new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.operationBasics.host.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemOut(ManaLevelItemPack.gather.host, 1, 1));
        spread_integrate = new IShapedCreate.ManaLevelShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{name.getPath(), "spread_integrate"}),
                this, ShapedDrive.get(4), 2048, 16, 0L)
                .addConfig(new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.operationBasics.integrate.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemOut(ManaLevelItemPack.spread.integrate, 1, 1));
        spread_processor = new IShapedCreate.ManaLevelShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{name.getPath(), "spread_processor"}),
                this, ShapedDrive.get(4), 4096, 32, 0L)
                .addConfig(new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.operationBasics.processor.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemOut(ManaLevelItemPack.spread.processor, 1, 1));
        spread_host = new IShapedCreate.ManaLevelShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{name.getPath(), "spread_host"}),
                this, ShapedDrive.get(4), 8192, 42, 0L)
                .addConfig(new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.operationBasics.host.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemOut(ManaLevelItemPack.spread.host, 1, 1));
        power_integrate = new IShapedCreate.ManaLevelShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{name.getPath(), "power_integrate"}),
                this, ShapedDrive.get(5), 2048, 16, 0L)
                .addConfig(new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.operationBasics.integrate.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemOut(ManaLevelItemPack.power.integrate, 1, 1));
        power_processor = new IShapedCreate.ManaLevelShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{name.getPath(), "power_processor"}),
                this, ShapedDrive.get(5), 4096, 16, 0L)
                .addConfig(new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.operationBasics.integrate.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemOut(ManaLevelItemPack.power.integrate, 1, 1));
        power_host = new IShapedCreate.ManaLevelShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{name.getPath(), "power_host"}),
                this, ShapedDrive.get(5), 8192, 16, 0L)
                .addConfig(new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.operationBasics.integrate.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemOut(ManaLevelItemPack.power.integrate, 1, 1));
        instructions_integrate = new IShapedCreate.ManaLevelShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{name.getPath(), "instructions_integrate"}),
                this, ShapedDrive.get(6), 2048, 16, 0L)
                .addConfig(new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.operationBasics.integrate.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemOut(ManaLevelItemPack.instructions.integrate, 1, 1));
        instructions_processor = new IShapedCreate.ManaLevelShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{name.getPath(), "instructions_processor"}),
                this, ShapedDrive.get(6), 4096, 16, 0L)
                .addConfig(new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.operationBasics.integrate.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemOut(ManaLevelItemPack.instructions.integrate, 1, 1));
        instructions_host = new IShapedCreate.ManaLevelShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{name.getPath(), "instructions_host"}),
                this, ShapedDrive.get(6), 8192, 16, 0L)
                .addConfig(new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.operationBasics.integrate.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreManaLevelConfig.ManaLevelItemOut(ManaLevelItemPack.instructions.integrate, 1, 1));
    }

    @ConfigField
    public IShapedCreate<ManaLevel> operation_integrate;
    @ConfigField
    public IShapedCreate<ManaLevel> operation_processor;
    @ConfigField
    public IShapedCreate<ManaLevel> operation_host;
    @ConfigField
    public IShapedCreate<ManaLevel> forming_integrate;
    @ConfigField
    public IShapedCreate<ManaLevel> forming_processor;
    @ConfigField
    public IShapedCreate<ManaLevel> forming_host;
    @ConfigField
    public IShapedCreate<ManaLevel> destruction_integrate;
    @ConfigField
    public IShapedCreate<ManaLevel> destruction_processor;
    @ConfigField
    public IShapedCreate<ManaLevel> destruction_host;
    @ConfigField
    public IShapedCreate<ManaLevel> gather_integrate;
    @ConfigField
    public IShapedCreate<ManaLevel> gather_processor;
    @ConfigField
    public IShapedCreate<ManaLevel> gather_host;
    @ConfigField
    public IShapedCreate<ManaLevel> spread_integrate;
    @ConfigField
    public IShapedCreate<ManaLevel> spread_processor;
    @ConfigField
    public IShapedCreate<ManaLevel> spread_host;
    @ConfigField
    public IShapedCreate<ManaLevel> power_integrate;
    @ConfigField
    public IShapedCreate<ManaLevel> power_processor;
    @ConfigField
    public IShapedCreate<ManaLevel> power_host;
    @ConfigField
    public IShapedCreate<ManaLevel> instructions_integrate;
    @ConfigField
    public IShapedCreate<ManaLevel> instructions_processor;
    @ConfigField
    public IShapedCreate<ManaLevel> instructions_host;
}
