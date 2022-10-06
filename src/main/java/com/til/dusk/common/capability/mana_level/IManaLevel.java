package com.til.dusk.common.capability.mana_level;

import com.til.dusk.common.capability.ITooltipCapability;
import com.til.dusk.common.register.other.CapabilityRegister;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.util.Lang;
import com.til.dusk.util.tooltip_pack.IComponentPack;
import com.til.dusk.util.nbt.pack.AllNBTPack;
import net.minecraft.nbt.CompoundTag;
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
   default CompoundTag appendServerData(){
        CompoundTag compoundTag = new CompoundTag();
        AllNBTPack.MANA_LEVEL.set(compoundTag, manaLevel());
        return compoundTag;

    }

    @Override
    default  void appendTooltip(IComponentPack iTooltip, CompoundTag compoundTag){
        ManaLevel manaLevel = AllNBTPack.MANA_LEVEL.get(compoundTag);
        iTooltip.add(Lang.getLang(Lang.getLang(CapabilityRegister.iManaLevel), Lang.getLang(manaLevel)));

    }
}
