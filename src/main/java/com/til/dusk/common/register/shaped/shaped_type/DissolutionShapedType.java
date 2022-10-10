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
import net.minecraftforge.fluids.FluidStack;

import java.util.function.Consumer;

/**
 * @author til
 */
public class DissolutionShapedType extends ShapedType {

    public DissolutionShapedType() {
        super("dissolution", () -> ManaLevelBlock.dissolution);
    }

    @Override
    public void registerRuleShaped(Consumer<Shaped> shapedConsumer) {
        for (Ore ore : Ore.ORE.get()) {
            if (!ore.hasDust) {
                continue;
            }
            shapedConsumer.accept(dust.create(ore));
        }
    }

    @Override
    public void defaultConfig() {
        dust = new IShapedCreate.OreShapedCreate(name, this, ShapedDrive.get(1), 1024L, 52L, 0)
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptItemIn(OreItem.dust.name, 1))
                .addConfig(new IShapedOreConfig.IShapedOreOreConfig.AcceptFluidOut(OreFluid.solution, 144, 1));
    }


    @ConfigField
    public IShapedCreate<Ore> dust;


}
