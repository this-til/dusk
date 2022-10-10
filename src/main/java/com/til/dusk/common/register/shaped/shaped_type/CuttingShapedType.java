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
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.util.ResourceLocationUtil;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;

/**
 * @author til
 */
public class CuttingShapedType extends ShapedType {

    public CuttingShapedType() {
        super("cutting", () -> ManaLevelBlock.cutting);
    }

    @Override
    public void registerRuleShaped(Consumer<Shaped> shapedConsumer) {
        for (Ore ore : Ore.ORE.get()) {
            if (ore.isMetal) {
                shapedConsumer.accept(stick.create(ore));
            }
            if (ore.decorateBlockData != null) {
                shapedConsumer.accept(slab.create(ore));
                shapedConsumer.accept(stairs.create(ore));
                shapedConsumer.accept(wall.create(ore));
            }
        }

    }

    @Override
    public void defaultConfig() {
        stick = new IShapedCreate.OreShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(), "stick"),
                this, ShapedDrive.get(0), 64L, 16L, 1)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.stickLong.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemOut(OreItem.stick, 2, 1));
        slab = new IShapedCreate.OreShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(), "slab"),
                this, ShapedDrive.get(1), 1024L, 24L, 1)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreBlock.block.name, 1));
        stairs = new IShapedCreate.OreShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(), "stairs"),
                this, ShapedDrive.get(2), 1024L, 24L, 1)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreBlock.block.name, 1));
        wall = new IShapedCreate.OreShapedCreate(ResourceLocationUtil.fuseName(name.getNamespace(), "wall"),
                this, ShapedDrive.get(3), 1024L, 24L, 1)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreBlock.block.name, 1));
    }

    @ConfigField
    public IShapedCreate<Ore> stick;
    @ConfigField
    public IShapedCreate<Ore> slab;
    @ConfigField
    public IShapedCreate<Ore> stairs;
    @ConfigField
    public IShapedCreate<Ore> wall;
}
