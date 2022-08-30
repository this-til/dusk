package com.til.dusk.common.capability.control;


import com.til.dusk.common.capability.IThis;
import com.til.dusk.common.capability.ITooltipCapability;
import com.til.dusk.common.register.BindType;

import com.til.dusk.common.register.CapabilityRegister;
import com.til.dusk.util.Lang;
import com.til.dusk.util.TooltipPack;
import com.til.dusk.util.tag_tool.TagTool;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import org.jetbrains.annotations.Nullable;

import java.util.*;


public interface IControl extends IThis<BlockEntity>, INBTSerializable<CompoundTag>, ITooltipCapability {

    /***
     * 全部解绑
     */
    void unBundlingAll();

    /***
     * 绑定
     */
    Component bind(BlockEntity tileEntity, BindType iBindType);

    /***
     * 解绑
     */
    Component unBind(BlockEntity tileEntity, BindType iBindType);

    /***
     * 有没有绑定实体
     */
    boolean hasBind(BlockEntity tileEntity, BindType bindType);

    /***
     * 获取所有绑定的实体
     */
    List<BlockEntity> getAllTileEntity(BindType iBindType);

    /***
     * 获取所有绑定的方块信息
     */
    Map<BindType, List<BlockPos>> getAllBind();

    <C> Map<BlockEntity, C> getCapability(Capability<C> capability, BindType iBindType);

    <C> Map<BlockEntity, C> getCapability(BindType.BindTypeBindCapability<C> bundTypeBindCapability);

    /***
     * 获取可以绑定实体方块的最大范围
     */
    int getMaxRange();

    /***
     * 获取最大绑定数量
     */
    int getMaxBind();

    /***
     * 获得可以绑定的类型
     */
    List<BindType> getCanBindType();
}
