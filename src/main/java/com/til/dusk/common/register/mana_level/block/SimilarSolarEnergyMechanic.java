package com.til.dusk.common.register.mana_level.block;

import com.google.gson.JsonObject;
import com.til.dusk.Dusk;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.capability.CapabilityHelp;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.mana_handle.IManaHandle;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.bind_type.BindType;
import com.til.dusk.common.world.block.DuskBlock;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.Extension;
import com.til.dusk.util.ModelJsonUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.Map;

/**
 * @author til
 */
public abstract class SimilarSolarEnergyMechanic extends PassiveProductionMechanic {

    public static final ResourceLocation MODEL_NAME = new ResourceLocation(Dusk.MOD_ID, "solar_energy");
    public final Extension.Func_1I<Level, Boolean> isTimePass;

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
        CapabilityHelp.addMana(iControl.getPosTrack(), null, outMana, (long) manaLevel.level * productionMultiple, false);
    }

    @Override
    public void dyeBlack(ManaLevel manaLevel, ColorProxy.BlockColorPack blockColorPack) {
        super.dyeBlack(manaLevel, blockColorPack);
        blockColorPack.addColor(1, (blockState, blockAndTintGetter, blockPos) -> solarEnergyColor);
    }

    @Override
    public void dyeBlack(ManaLevel manaLevel, ColorProxy.ItemColorPack itemColorPack) {
        super.dyeBlack(manaLevel, itemColorPack);
        itemColorPack.addColor(1, itemStack -> solarEnergyColor);
    }

    @Override
    protected void registerBack() {
        super.registerBack();
    }

    @Override
    public JsonObject createBlockModel(Block block, ManaLevel o) {
        return ModelJsonUtil.createBlockState(MODEL_NAME);
    }

    @Override
    public JsonObject createModel(Item item, ManaLevel o) {
        return ModelJsonUtil.createItemFather(MODEL_NAME);
    }


    @ConfigField
    public DuskColor solarEnergyColor;
    @ConfigField
    public int productionMultiple = 1;
}
