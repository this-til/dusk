package com.til.dusk.common.register.ore.ore.ores.fluid.cell;

import com.til.dusk.common.capability.handle.IHandle;
import com.til.dusk.common.capability.handle.ShapedHandle;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.fluid.FluidData;
import com.til.dusk.common.register.ore.fluid.OreFluid;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.common.register.shaped.shapeds.ShapedMiddle;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.ResourceLocationUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

/**
 * @author til
 */
public class StemCellOre extends Ore {
    public StemCellOre() {
        super("stem_cell");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "干细胞");
        lang.add(LangType.EN_CH, "Stem Cell");
    }

    @Override
    public void defaultConfig() {
        color = new DuskColor(209, 149, 182);
        manaLevel = ManaLevel.t4;
        fluidData = new FluidData();
        relevantShaped = new Delayed.ListShapedDelayed(() -> List.of(
                new ShapedOre(ResourceLocationUtil.fuseName(this, OreFluid.solution), ShapedType.stemCellExtract, ShapedDrive.get(0),manaLevel)
                        .addInItem(ItemTag.CAN_EXTRACT_STEM_CELL, 1)
                        .addOutFluid(new FluidStack(this.get(OreFluid.solution).source(), 1), 0.1)
                        .addMultipleSurplusTime((long) (512L * strength))
                        .addMultipleConsumeMana((long) (128L * consume)),

                new ShapedOre(ResourceLocationUtil.fuseName(this, culture, OreFluid.solution), ShapedType.cellCulture, ShapedDrive.get(0), manaLevel)
                        .addInFluid(this.get(OreFluid.solution).fluidTag(), 1)
                        .addInFluid(culture.get(OreFluid.solution).fluidTag(), 128)
                        .addOutFluid(new FluidStack(this.get(OreFluid.solution).source(), 2), 1d)
                        .addMultipleSurplusTime((long) (1024L * strength))
                        .addMultipleConsumeMana((long) (64L * consume))));
    }

}

