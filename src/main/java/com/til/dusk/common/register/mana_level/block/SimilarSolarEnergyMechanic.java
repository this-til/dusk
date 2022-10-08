package com.til.dusk.common.register.mana_level.block;

import com.til.dusk.Dusk;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.capability.CapabilityHelp;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.mana_handle.IManaHandle;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.config.ConfigKey;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.other.BindType;
import com.til.dusk.common.world.block.ModBlock;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.Extension;
import com.til.dusk.util.nbt.cell.AllNBTCell;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.Map;

/**
 * @author til
 */
public abstract class SimilarSolarEnergyMechanic extends PassiveProductionMechanic {

    public static final ResourceLocation MODEL_NAME = new ResourceLocation(Dusk.MOD_ID, "solar_energy");
    public final Extension.Func_1I<Level, Boolean> isTimePass;
    protected int productionMultiple;

    public SimilarSolarEnergyMechanic(ResourceLocation name, Extension.Func_1I<Level, Boolean> isTimePass) {
        super(name);
        this.isTimePass = isTimePass;
    }

    public SimilarSolarEnergyMechanic(String name, Extension.Func_1I<Level, Boolean> isTimePass) {
        this(new ResourceLocation(Dusk.MOD_ID, name), isTimePass);
    }

    @Override
    public void up(BlockEntity blockEntity, IControl iControl, ManaLevel manaLevel) {
        Level level = blockEntity.getLevel();
        if (level == null || level.isClientSide) {
            return;
        }
        if (!isTimePass.func(level)) {
            return;
        }
        Map<IPosTrack, IManaHandle> outMana = iControl.getCapability(BindType.manaOut);
        if (outMana.isEmpty()) {
            return;
        }
        BlockPos blockPos = blockEntity.getBlockPos();
        for (int i = blockPos.getY() + 1; i < 255; i++) {
            if (!level.getBlockState(new BlockPos(blockPos.getX(), i, blockPos.getZ())).getBlock().equals(Blocks.AIR)) {
                return;
            }
        }
        CapabilityHelp.addMana(iControl.getPosTrack(), null, outMana, (long) manaLevel.getConfig(ManaLevel.level) * productionMultiple, false);
    }

    @Override
    public void dyeBlack(ManaLevel manaLevel, ColorProxy.BlockColorPack blockColorPack) {
        super.dyeBlack(manaLevel, blockColorPack);
        DuskColor color = getConfig(SOLAR_ENERGY_COLOR);
        blockColorPack.addColor(1, (blockState, blockAndTintGetter, blockPos) -> color);
    }

    @Override
    public void dyeBlack(ManaLevel manaLevel, ColorProxy.ItemColorPack itemColorPack) {
        super.dyeBlack(manaLevel, itemColorPack);
        DuskColor color = getConfig(SOLAR_ENERGY_COLOR);
        itemColorPack.addColor(1, itemStack -> color);
    }

    @Override
    protected void registerBack() {
        super.registerBack();
        productionMultiple = getConfig(PRODUCTION_MULTIPLE);
    }

    @Override
    public ModBlock.ICustomModel getBlockModelMapping(ManaLevel manaLevel) {
        return () -> MODEL_NAME;
    }


    public static final ConfigKey<DuskColor> SOLAR_ENERGY_COLOR = new ConfigKey<>("mana_level_block.solar_energy.color", AllNBTCell.COLOR, null);
    public static final ConfigKey<Integer> PRODUCTION_MULTIPLE = new ConfigKey<>("mana_level_block.solar_energy.production_multiple", AllNBTCell.INT, () -> 1);
}
