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
import com.til.dusk.util.ResourceLocationUtil;

import java.util.function.Consumer;

/**
 * @author til
 */
public class StampingMachineShapedType extends ShapedType {

    public StampingMachineShapedType() {
        super("stamping_machine");
    }

    @Override
    public void registerRuleShaped(Consumer<Shaped> shapedConsumer) {
        for (Ore ore : Ore.ORE.get()) {
            if (!ore.isMetal) {
                continue;
            }
            shapedConsumer.accept(plate.create(ore));
            shapedConsumer.accept(plate2.create(ore));
            shapedConsumer.accept(plate3.create(ore));
            shapedConsumer.accept(plate4.create(ore));
            shapedConsumer.accept(foil.create(ore));
            shapedConsumer.accept(stickLong.create(ore));
        }
    }

    @Override
    public void defaultConfig() {
        blockTagKey = new Delayed.BlockDelayed(() -> ManaLevelBlock.stampingMachine.tagPackSupplier.getTagPack().blockTagKey());
        plate = new IShapedCreate.OreShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(),  "plate1"),
                this, ShapedDrive.get(0), 1024L, 16L, 0L)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.ingot.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemOut(OreItem.plate, 1, 1));
        plate2 = new IShapedCreate.OreShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(),  "plate2"),
                this, ShapedDrive.get(0), 1024L, 32L, 0L)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.plate.name, 2))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemOut(OreItem.plate2, 1, 1));
        plate3 = new IShapedCreate.OreShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(), "plate3"),
                this, ShapedDrive.get(1), 2048L, 32L, 0L)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.plate2.name, 2))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemOut(OreItem.plate3, 1, 1));
        plate4 = new IShapedCreate.OreShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(),  "plate4"),
                this, ShapedDrive.get(2), 4096L, 32L, 0L)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.plate3.name, 2))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemOut(OreItem.plate4, 1, 1));
        foil = new IShapedCreate.OreShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(), "foil"),
                this, ShapedDrive.get(3), 512L, 12L, 0L)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.plate.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemOut(OreItem.foil, 4, 1));
        stickLong = new IShapedCreate.OreShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(), "stick_long"),
                this, ShapedDrive.get(4), 512L, 8L, 0L)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.stick.name, 2))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemOut(OreItem.stickLong, 1, 1));
    }

    @ConfigField
    public IShapedCreate<Ore> plate;
    @ConfigField
    public IShapedCreate<Ore> plate2;

    @ConfigField
    public IShapedCreate<Ore> plate3;

    @ConfigField
    public IShapedCreate<Ore> plate4;

    @ConfigField
    public IShapedCreate<Ore> foil;

    @ConfigField
    public IShapedCreate<Ore> stickLong;


}
