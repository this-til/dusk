package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.common.config.util.IShapedCreate;
import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.ore.block.OreBlock;
import com.til.dusk.common.register.ore.block.OreBlockMineral;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Consumer;

/**
 * @author til
 */
public class GrindShapedType extends ShapedType {

    public GrindShapedType() {
        super("grind");
    }

    @Override
    public void registerRuleShaped(Consumer<Shaped> shapedConsumer) {
        for (Ore ore : Ore.ORE.get()) {
            if (ore.mineralBlockData != null) {
                shapedConsumer.accept(crushed.create(ore));
            }
            if (ore.isMetal && ore.hasDust) {
                shapedConsumer.accept(ingotDust.create(ore));
            }
            if (ore.isCrysta && ore.hasDust) {
                shapedConsumer.accept(crystalDust.create(ore));
                shapedConsumer.accept(damagedCrystalDust.create(ore));
                shapedConsumer.accept(delicateCrystalDust.create(ore));
                shapedConsumer.accept(perfectCrystalDust.create(ore));
            }
        }
    }

    @Override
    public void defaultConfig() {
        blockTagKey = new Delayed.BlockDelayed(() -> ManaLevelBlock.grind.tagPackSupplier.getTagPack().blockTagKey());
        crushed = new IShapedCreate.OreShapedCreate(new ResourceLocation(name.getNamespace(),  "crushed")
                , this, ShapedDrive.get(0), 1024L, 8L, 0)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreBlockMineral.MINERAL_NAME, 1))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemOut(OreItem.crushed, 2, 1));
        ingotDust = new IShapedCreate.OreShapedCreate(new ResourceLocation(name.getNamespace(),  "ingot")
                , this, ShapedDrive.get(1), 1024L, 12L, 0)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.ingot.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemOut(OreItem.dust, 1, 1));
        crystalDust = new IShapedCreate.OreShapedCreate(new ResourceLocation(name.getNamespace(),  "crysta")
                , this, ShapedDrive.get(1), 1024L, 12L, 0)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.crystal.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemOut(OreItem.dust, 1, 1));
        damagedCrystalDust = new IShapedCreate.OreShapedCreate(new ResourceLocation(name.getNamespace(),  "damaged_crystal")
                , this, ShapedDrive.get(1), 757L, 12L, 0)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.damagedCrystal.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemOut(OreItem.dust, 1, 1));
        delicateCrystalDust = new IShapedCreate.OreShapedCreate(new ResourceLocation(name.getNamespace(),  "delicate_crystal")
                , this, ShapedDrive.get(1), 1024L, 12L, 0)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.delicateCrystal.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemOut(OreItem.dust, 2, 1));
        perfectCrystalDust = new IShapedCreate.OreShapedCreate(new ResourceLocation(name.getNamespace(),  "perfect_crystal")
                , this, ShapedDrive.get(1), 1024L, 12L, 0)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.perfectCrystal.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemOut(OreItem.dust, 3, 1));
        blockDust = new IShapedCreate.OreShapedCreate(new ResourceLocation(name.getNamespace(),  "block")
                , this, ShapedDrive.get(2), 5000L, 12L, 0)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreBlock.block.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemOut(OreItem.dust, 9, 1));
        slabDust = new IShapedCreate.OreShapedCreate(new ResourceLocation(name.getNamespace(),  "slab")
                , this, ShapedDrive.get(2), 5000L, 6L, 0)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreBlock.slab.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemOut(OreItem.dust, 3, 1));
        stairsDust = new IShapedCreate.OreShapedCreate(new ResourceLocation(name.getNamespace(),  "stairs")
                , this, ShapedDrive.get(2), 5000L, 12L, 0)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreBlock.stairs.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemOut(OreItem.dust, 7, 1));
        wallDust = new IShapedCreate.OreShapedCreate(new ResourceLocation(name.getNamespace(),  "wall")
                , this, ShapedDrive.get(2), 5000L, 12L, 0)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreBlock.wall.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemOut(OreItem.dust, 6, 1));
    }

    @ConfigField
    public IShapedCreate<Ore> crushed;
    @ConfigField
    public IShapedCreate<Ore> ingotDust;
    @ConfigField
    public IShapedCreate<Ore> crystalDust;
    @ConfigField
    public IShapedCreate<Ore> damagedCrystalDust;
    @ConfigField
    public IShapedCreate<Ore> delicateCrystalDust;
    @ConfigField
    public IShapedCreate<Ore> perfectCrystalDust;
    @ConfigField
    public IShapedCreate<Ore> blockDust;
    @ConfigField
    public IShapedCreate<Ore> slabDust;
    @ConfigField
    public IShapedCreate<Ore> stairsDust;
    @ConfigField
    public IShapedCreate<Ore> wallDust;
}
