package com.til.dusk.common.capability;

import com.til.dusk.util.tooltip_pack.IComponentPack;
import net.minecraft.nbt.CompoundTag;

import javax.annotation.Nullable;

/**
 * @author til
 */
public interface ITooltipCapability {

    /***
     * 从服务器加载数据进NBT
     * @return 数据NBT
     */
    @Nullable
    CompoundTag appendServerData();


    /***
     * 根据数据添加文本
     * @param iTooltip 文本包
     * @param compoundTag 服务端数据
     */
    void appendTooltip(IComponentPack iTooltip, CompoundTag compoundTag);

}
