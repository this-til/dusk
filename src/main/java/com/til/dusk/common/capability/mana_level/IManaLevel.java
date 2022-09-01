package com.til.dusk.common.capability.mana_level;

import com.til.dusk.common.capability.ITooltipCapability;
import com.til.dusk.common.register.CapabilityRegister;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.util.Lang;
import com.til.dusk.util.TooltipPack;
import com.til.dusk.util.nbt.pack.AllNBTPack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.Nullable;

/**
 * 代表该实体方块有一个等级
 * @author til
 */
public interface IManaLevel extends ITooltipCapability {

    /***
     * 获取等级
     * @return 等级
     */
    ManaLevel manaLevel();

    @Nullable
    @Override
   default CompoundTag appendServerData(ServerPlayer serverPlayer, Level level, BlockEntity blockEntity, boolean detailed){
        CompoundTag compoundTag = new CompoundTag();
        AllNBTPack.MANA_LEVEL.set(compoundTag, manaLevel());
        return compoundTag;

    }

    @Override
    default  void appendTooltip(TooltipPack iTooltip, CompoundTag compoundTag){
        ManaLevel manaLevel = AllNBTPack.MANA_LEVEL.get(compoundTag);
        iTooltip.add(Lang.getLang(Lang.getLang(CapabilityRegister.iManaHandle), Lang.getLang(manaLevel)));

    }
}
