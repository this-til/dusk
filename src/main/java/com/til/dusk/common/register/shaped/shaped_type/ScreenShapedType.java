package com.til.dusk.common.register.shaped.shaped_type;


import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.config.util.IShapedCreate;
import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.util.pack.DataPack;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;

/**
 * @author til
 */
public class ScreenShapedType extends ShapedType {

    public ScreenShapedType() {
        super("screen", () -> ManaLevelBlock.screen);
    }

    @Override
    public void registerRuleShaped(Consumer<Shaped> shapedConsumer) {
        for (Ore ore : Ore.ORE.get()) {
            if (ore.mineralBlockData == null) {return;}
            shapedConsumer.accept(screen.create(ore));
        }
    }

    @Override
    public void defaultConfig() {
        screen = new IShapedCreate.OreShapedCreate(name, this, ShapedDrive.get(0), 128, 4, 0)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.crushedPurified.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemItemOut(OreItem.damagedCrystal, 1, 0.5))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemItemOut(OreItem.crystal, 1, 0.5))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemItemOut(OreItem.delicateCrystal, 1, 0.05))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemItemOut(OreItem.perfectCrystal, 1, 0.01))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemItemOut(OreItem.crystalSeed, 1, 0.3))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemItemOut(OreItem.dustTiny, 1, 0.6))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemItemOut(OreItem.dust, 1, 0.5));
    }

    @ConfigField
    public IShapedCreate<Ore> screen;
}
