package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.config.util.IShapedCreate;
import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.ore.fluid.OreFluid;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.util.ResourceLocationUtil;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;

/**
 * @author til
 */
public class CrystallizingShapedType extends ShapedType {

    public CrystallizingShapedType() {
        super("crystallizing", () -> ManaLevelBlock.crystallizing);
    }

    @Override
    public void registerRuleShaped(Consumer<Shaped> shapedConsumer) {
        for (Ore ore : Ore.ORE.get()) {
            if (!ore.isCrysta) {
                continue;
            }
            if (ore.fluidData == null) {
                continue;
            }
            shapedConsumer.accept(crystal.create(ore));
            shapedConsumer.accept(delicateCrystal.create(ore));
            shapedConsumer.accept(perfectCrystal.create(ore));
        }
    }

    @Override
    public void defaultConfig() {
        crystal = new IShapedCreate.OreShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{name.getPath(), "crystal"}),
                this, ShapedDrive.get(0), 2048L, 8L, 0L)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.crystalSeed.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptFluidIn(OreFluid.solution.name, 288))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemOut(OreItem.crystal, 1, 1));
        delicateCrystal = new IShapedCreate.OreShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{name.getPath(), "delicate_crystal"}),
                this, ShapedDrive.get(1), 4096L, 8L, 0L)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.crystal.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptFluidIn(OreFluid.solution.name, 576))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemOut(OreItem.delicateCrystal, 1, 1));
        perfectCrystal = new IShapedCreate.OreShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{name.getPath(), "perfect_crystal"}),
                this, ShapedDrive.get(0), 8192L, 8L, 0L)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.delicateCrystal.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptFluidIn(OreFluid.solution.name, 1152))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemOut(OreItem.perfectCrystal, 1, 1));
    }

    @ConfigField
    public IShapedCreate<Ore> crystal;
    @ConfigField
    public IShapedCreate<Ore> delicateCrystal;
    @ConfigField
    public IShapedCreate<Ore> perfectCrystal;
}
