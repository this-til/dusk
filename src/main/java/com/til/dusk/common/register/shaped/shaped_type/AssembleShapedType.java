package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.config.util.IShapedCreate;
import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.mana_level.item.ManaLevelItemPack;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.fluid.OreFluid;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.util.ResourceLocationUtil;
import com.til.dusk.util.pack.BlockPack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.Tags;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author til
 */
public class AssembleShapedType extends ShapedType {
    public AssembleShapedType() {
        super("assemble", () -> ManaLevelBlock.assemble);
    }

    @Override
    public void registerRuleShaped(Consumer<Shaped> shapedConsumer) {
        for (Ore ore : Ore.ORE.get()) {
            if (!ore.isMetal) {
                continue;
            }
            shapedConsumer.accept(bracket.create(ore));
            shapedConsumer.accept(coil.create(ore));
        }
        for (ManaLevel manaLevel : ManaLevel.MANA_LEVEL.get()) {
            for (Map.Entry<ManaLevelBlock, BlockPack> entry : manaLevel.blockEntrySet()) {
                if (entry.getKey().manaLevelMakeData == null) {
                    continue;
                }
                ManaLevelBlock.ManaLevelMakeData makeLevel = entry.getKey().manaLevelMakeData;
                if (makeLevel.oreConfig == null) {
                    continue;
                }
                ManaLevel level = ManaLevel.get(manaLevel, makeLevel.makeLevel, makeLevel.isMustRegister);
                if (level == null) {
                    continue;
                }
                ShapedOre shaped = (ShapedOre) mechanicMakeData.create(manaLevel);
                if (shaped == null) {
                    continue;
                }
                shaped.addOutItem(new ItemStack(entry.getValue().blockItem(), 1), 1d);
                for (IShapedOreConfig<ManaLevel> config : makeLevel.oreConfig) {
                    config.config(shaped, level);
                }
            }
        }


        for (Ore ore : Ore.ORE.get()) {
            if (ore.armorData == null) {
                continue;
            }
            shapedConsumer.accept(head.create(ore));
            shapedConsumer.accept(chest.create(ore));
            shapedConsumer.accept(legs.create(ore));
            shapedConsumer.accept(feet.create(ore));
        }
        for (Ore ore : Ore.ORE.get()) {
            if (ore.armsData == null) {
                continue;
            }
            shapedConsumer.accept(sword.create(ore));
            shapedConsumer.accept(shovel.create(ore));
            shapedConsumer.accept(pickaxe.create(ore));
            shapedConsumer.accept(axe.create(ore));
            shapedConsumer.accept(hoe.create(ore));
        }
        for (Ore ore : Ore.ORE.get()) {
            if (!ore.isMetal) {
                continue;
            }
            if (ore.toolData == null) {
                continue;
            }
            shapedConsumer.accept(hammer.create(ore));
            shapedConsumer.accept(wrench.create(ore));
            shapedConsumer.accept(wireCutter.create(ore));
            shapedConsumer.accept(file.create(ore));
            shapedConsumer.accept(tank.create(ore));
        }
        for (Ore ore : Ore.ORE.get()) {
            if (!ore.isCrysta) {
                continue;
            }
            if (ore.toolData == null) {
                continue;
            }
            //TODO 有关晶体的工具
        }
    }

    @Override
    public void defaultConfig() {
        bracket = new IShapedCreate.OreShapedCreate(new ResourceLocation(name.getNamespace(), "bracket")
                , this, ShapedDrive.get(0), 4096L, 12L, 0);
        coil = new IShapedCreate.OreShapedCreate(new ResourceLocation(name.getNamespace(), "coil")
                , this, ShapedDrive.get(1), 8192L, 22L, 0);
        mechanicMakeData = new IShapedCreate.ManaLevelShapedCreate(new ResourceLocation(name.getNamespace(), "mechanic_make_data")
                , this, ShapedDrive.get(2), 4096L, 22L, 0);

        head = new IShapedCreate.OreShapedCreate(new ResourceLocation(name.getNamespace(), "head")
                , this, ShapedDrive.get(3), 16384L, 64L, 0)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptFluidIn(OreFluid.solution.name, 16384))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.plate4.name, 5 * 4))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.ManaLevelPack(List.of(
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.operation.name, 24).setMultiple(true),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.forming.name, 12).setMultiple(true),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.gather.name, 8).setMultiple(true),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.instructions.name, 8).setMultiple(true),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.power.name, 8).setMultiple(true)
                )))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemOut(OreItem.head, 1, 1));
        chest = new IShapedCreate.OreShapedCreate(new ResourceLocation(name.getNamespace(), "chest")
                , this, ShapedDrive.get(4), 16384L, 64L, 0)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptFluidIn(OreFluid.solution.name, 16384))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.plate4.name, 8 * 4))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.ManaLevelPack(List.of(
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.operation.name, 24).setMultiple(true),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.forming.name, 24).setMultiple(true),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.gather.name, 24).setMultiple(true),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.instructions.name, 24).setMultiple(true),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.power.name, 24).setMultiple(true)
                )))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemOut(OreItem.chest, 1, 1));
        legs = new IShapedCreate.OreShapedCreate(new ResourceLocation(name.getNamespace(), "legs")
                , this, ShapedDrive.get(5), 16384L, 64L, 0)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptFluidIn(OreFluid.solution.name, 16384))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.plate4.name, 7 * 4))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.ManaLevelPack(List.of(
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.operation.name, 24).setMultiple(true),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.forming.name, 24).setMultiple(true),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.gather.name, 12).setMultiple(true),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.instructions.name, 12).setMultiple(true),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.power.name, 12).setMultiple(true)
                )))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemOut(OreItem.legs, 1, 1));
        feet = new IShapedCreate.OreShapedCreate(new ResourceLocation(name.getNamespace(), "feet")
                , this, ShapedDrive.get(6), 16384L, 64L, 0)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptFluidIn(OreFluid.solution.name, 16384))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.plate4.name, 4 * 4))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.ManaLevelPack(List.of(
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.operation.name, 24).setMultiple(true),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.forming.name, 24).setMultiple(true),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.gather.name, 4).setMultiple(true),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.instructions.name, 16).setMultiple(true),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.power.name, 16).setMultiple(true)
                )))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemOut(OreItem.chest, 1, 1));

        sword = new IShapedCreate.OreShapedCreate(new ResourceLocation(name.getNamespace(), "sword")
                , this, ShapedDrive.get(7), 16384L, 64L, 0)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptFluidIn(OreFluid.solution.name, 16384))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.swordBasics.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.plate4.name, 64 * 2))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.ManaLevelPack(List.of(
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.operation.name, 32).setMultiple(true),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.destruction.name, 64).setMultiple(true),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.spread.name, 48).setMultiple(true),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.instructions.name, 32).setMultiple(true),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.power.name, 48).setMultiple(true)
                )))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemOut(OreItem.sword, 1, 1));
        shovel = new IShapedCreate.OreShapedCreate(new ResourceLocation(name.getNamespace(), "shovel")
                , this, ShapedDrive.get(8), 16384L, 64L, 0)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptFluidIn(OreFluid.solution.name, 16384))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.shovelBasics.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.plate4.name, 16))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.ManaLevelPack(List.of(
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.operation.name, 12).setMultiple(true),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.destruction.name, 12).setMultiple(true),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.spread.name, 4).setMultiple(true),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.instructions.name, 4).setMultiple(true),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.power.name, 8).setMultiple(true)
                )))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemOut(OreItem.shovel, 1, 1));
        pickaxe = new IShapedCreate.OreShapedCreate(new ResourceLocation(name.getNamespace(), "pickaxe")
                , this, ShapedDrive.get(9), 16384L, 64L, 0)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptFluidIn(OreFluid.solution.name, 16384))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.pickaxeBasics.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.plate4.name, 16 * 3))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.ManaLevelPack(List.of(
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.operation.name, 32).setMultiple(true),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.destruction.name, 48).setMultiple(true),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.spread.name, 12).setMultiple(true),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.instructions.name, 12).setMultiple(true),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.power.name, 16).setMultiple(true)
                )))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemOut(OreItem.pickaxe, 1, 1));
        axe = new IShapedCreate.OreShapedCreate(new ResourceLocation(name.getNamespace(), "axe")
                , this, ShapedDrive.get(10), 16384L, 64L, 0)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptFluidIn(OreFluid.solution.name, 16384))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.axeBasics.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.plate4.name, 16 * 3))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.ManaLevelPack(List.of(
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.operation.name, 32).setMultiple(true),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.destruction.name, 64).setMultiple(true),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.spread.name, 12).setMultiple(true),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.instructions.name, 12).setMultiple(true),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.power.name, 12).setMultiple(true)
                )))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemOut(OreItem.axe, 1, 1));
        hoe = new IShapedCreate.OreShapedCreate(new ResourceLocation(name.getNamespace(), "hoe")
                , this, ShapedDrive.get(11), 16384L, 64L, 0)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptFluidIn(OreFluid.solution.name, 16384))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.hoeBasics.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.plate4.name, 16 * 2))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.ManaLevelPack(List.of(
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.operation.name, 16).setMultiple(true),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.destruction.name, 4).setMultiple(true),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.spread.name, 12).setMultiple(true),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.instructions.name, 8).setMultiple(true),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemPack.power.name, 8).setMultiple(true)
                )))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemOut(OreItem.hoe, 1, 1));

        hammer = new IShapedCreate.OreShapedCreate(new ResourceLocation(name.getNamespace(), "hammer")
                , this, ShapedDrive.get(12), 2048L, 7L, 0)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.ingot.name, 6))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.ItemIn(() -> Tags.Items.RODS_WOODEN, 1))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemOut(OreItem.hammer, 1, 1));
        wrench = new IShapedCreate.OreShapedCreate(new ResourceLocation(name.getNamespace(), "wrench")
                , this, ShapedDrive.get(13), 2048L, 7L, 0)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.ingot.name, 4))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemOut(OreItem.wrench, 1, 1));
        wireCutter = new IShapedCreate.OreShapedCreate(new ResourceLocation(name.getNamespace(), "wire_cutter")
                , this, ShapedDrive.get(14), 2048L, 7L, 0)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.plate.name, 3))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.ingot.name, 2))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemOut(OreItem.wireCutter, 1, 1));
        file = new IShapedCreate.OreShapedCreate(new ResourceLocation(name.getNamespace(), "file")
                , this, ShapedDrive.get(15), 2048L, 7L, 0)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.plate.name, 2))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.casing.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemOut(OreItem.file, 1, 1));
        tank = new IShapedCreate.OreShapedCreate(new ResourceLocation(name.getNamespace(), "tank")
                , this, ShapedDrive.get(16), 4096L, 24L, 0)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.plate2.name, 6))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.plate3.name, 3))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.casing.name, 12))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemOut(OreItem.tank, 1, 1));
    }

    @ConfigField
    public IShapedCreate<Ore> bracket;

    @ConfigField
    public IShapedCreate<Ore> coil;
    @ConfigField
    public IShapedCreate<ManaLevel> mechanicMakeData;

    @ConfigField
    public IShapedCreate<Ore> head;

    @ConfigField
    public IShapedCreate<Ore> chest;

    @ConfigField
    public IShapedCreate<Ore> legs;

    @ConfigField
    public IShapedCreate<Ore> feet;

    @ConfigField
    public IShapedCreate<Ore> sword;

    @ConfigField
    public IShapedCreate<Ore> shovel;

    @ConfigField
    public IShapedCreate<Ore> pickaxe;

    @ConfigField
    public IShapedCreate<Ore> axe;

    @ConfigField
    public IShapedCreate<Ore> hoe;

    @ConfigField
    public IShapedCreate<Ore> hammer;

    @ConfigField
    public IShapedCreate<Ore> wrench;

    @ConfigField
    public IShapedCreate<Ore> wireCutter;

    @ConfigField
    public IShapedCreate<Ore> file;

    @ConfigField
    public IShapedCreate<Ore> tank;

}