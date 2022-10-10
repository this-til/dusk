package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.config.util.IShapedCreate;
import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;

/**
 * @author til
 */
public class CrystalSeedMakeShapedType extends ShapedType {

    public CrystalSeedMakeShapedType() {
        super("crystal_seed_make", () -> ManaLevelBlock.crystalSeedMake);
    }

    @Override
    public void registerRuleShaped(Consumer<Shaped> shapedConsumer) {
        for (Ore ore : Ore.ORE.get()) {
            if (!ore.isCrysta) {
                continue;
            }
            if (!ore.hasDust) {
                continue;
            }
            if (ore.fluidData == null) {
                continue;
            }
            shapedConsumer.accept(crystalSeedMake.create(ore));
        }
    }

    @Override
    public void defaultConfig() {
        crystalSeedMake = new IShapedCreate.OreShapedCreate(name, this, ShapedDrive.get(0), 256L, 12L, 0)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.ItemIn(() -> ItemTags.SAND, 1))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.dust.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.OreItemOut(OreItem.crystalSeed, 2, 1));
    }

    @ConfigField
    public IShapedCreate<Ore> crystalSeedMake;
}
