package com.til.dusk.common.register.mana_level.mana_level_block;

import com.til.dusk.Dusk;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.capability.mana_handle.IManaHandle;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.Extension;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.awt.*;

/**
 * @author til
 */
public class SimilarSolarEnergyMechanic extends PassiveProductionMechanic {

    public static final ResourceLocation MODEL_NAME = new ResourceLocation(Dusk.MOD_ID, "solar_energy");
    public final long productionMultiple;
    public final Extension.Func_1I<Level, Boolean> isTimePass;
    public final DuskColor color;

    public SimilarSolarEnergyMechanic(ResourceLocation name, long productionMultiple, Extension.Func_1I<Level, Boolean> isTimePass, DuskColor color) {
        super(name);
        this.productionMultiple = productionMultiple;
        this.isTimePass = isTimePass;
        this.color = color;
    }

    public SimilarSolarEnergyMechanic(String name, long productionMultiple, Extension.Func_1I<Level, Boolean> isTimePass, DuskColor color) {
        this(new ResourceLocation(Dusk.MOD_ID, name), productionMultiple, isTimePass, color);
    }

    @Override
    public void up(BlockEntity blockEntity, IManaHandle iManaHandle, ManaLevel manaLevel) {
        Level level = blockEntity.getLevel();
        if (level == null || level.isClientSide) {
            return;
        }
        if (!isTimePass.func(level)) {
            return;
        }
        BlockPos blockPos = blockEntity.getBlockPos();
        for (int i = blockPos.getY() + 1; i < 255; i++) {
            if (!level.getBlockState(new BlockPos(blockPos.getX(), i, blockPos.getZ())).getBlock().equals(Blocks.AIR)) {
                return;
            }
        }
        iManaHandle.addMana(manaLevel.level * productionMultiple,false);
    }

    @Override
    public void dyeBlack(ManaLevel manaLevel, ColorProxy.BlockColorPack blockColorPack) {
        super.dyeBlack(manaLevel, blockColorPack);
        blockColorPack.addColor(1, (blockState, blockAndTintGetter, blockPos) -> color);
    }

    @Override
    public void dyeBlack(ManaLevel manaLevel, ColorProxy.ItemColorPack itemColorPack) {
        super.dyeBlack(manaLevel, itemColorPack);
        itemColorPack.addColor(1, itemStack -> color);
    }

    @Override
    public ResourceLocation getBlockModelMapping(ManaLevel manaLevel) {
        return MODEL_NAME;
    }
}
