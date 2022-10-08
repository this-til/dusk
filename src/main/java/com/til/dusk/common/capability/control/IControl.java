package com.til.dusk.common.capability.control;


import com.til.dusk.common.capability.ITooltipCapability;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.register.other.BindType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.List;
import java.util.Map;


/**
 * @author til
 */
public interface IControl extends INBTSerializable<CompoundTag>, ITooltipCapability {

    /***
     * 全部解绑
     */
    void unBundlingAll();

    /***
     * 绑定
     */
    Component bind(IPosTrack tileEntity, BindType iBindType);

    /***
     * 解绑
     */
    Component unBind(IPosTrack tileEntity, BindType iBindType);

    /***
     * 有没有绑定实体
     */
    boolean hasBind(IPosTrack tileEntity, BindType bindType);

    /***
     * 获取所有绑定的实体
     */
    List<IPosTrack> getAllTileEntity(BindType iBindType);

    /***
     * 获取所有绑定的信息
     */
    Map<BindType, List<IPosTrack>> getAllBind();

    <C> Map<IPosTrack, C> getCapability(Capability<C> capability, BindType iBindType);

    <C> Map<IPosTrack, C> getCapability(BindType.BindTypeBindCapability<C> bundTypeBindCapability);

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

    /***
     * 获取位置追踪
     */
    IPosTrack getPosTrack();
}
