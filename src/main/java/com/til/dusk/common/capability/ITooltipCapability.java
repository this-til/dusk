package com.til.dusk.common.capability;

import com.til.dusk.util.TooltipPack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

import javax.annotation.Nullable;

/**
 * @author til
 */
public interface ITooltipCapability {

    /***
     * 从服务器加载数据进NBT
     * @param serverPlayer 服务器玩家实例
     * @param level 世界
     * @param blockEntity 实体方块
     * @param detailed 是否详细
     * @return 数据NBT
     */
    @Nullable
    CompoundTag appendServerData(ServerPlayer serverPlayer, Level level, BlockEntity blockEntity, boolean detailed);


    /***
     * 根据数据添加文本
     * @param iTooltip 文本包
     * @param compoundTag 服务端数据
     */
    void appendTooltip(TooltipPack iTooltip, CompoundTag compoundTag);

}
