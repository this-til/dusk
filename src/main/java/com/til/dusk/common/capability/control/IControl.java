package com.til.dusk.common.capability.control;


import com.til.dusk.common.capability.INBT;
import com.til.dusk.common.capability.IThis;
import com.til.dusk.common.register.BindType;

import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;
import java.util.*;


public interface IControl extends IThis<BlockEntity>, INBT {

    /***
     * 全部解绑
     */
    void unBundlingAll();

    /***
     * 绑定
     */
    Component binding(BlockEntity tileEntity, BindType iBindType);

    /***
     * 解绑
     */
    Component unBindling(BlockEntity tileEntity, BindType iBindType);

    /***
     * 有没有绑定实体
     */
    boolean hasBundling(BlockEntity tileEntity, BindType bindType);

    /***
     * 获取所有绑定的实体
     */
    List<BlockEntity> getAllTileEntity(BindType iBindType);

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

    class TellPlayerMessage {
        public String key;
        @Nullable
        public String[] keys;
        public boolean actionBar;

        public TellPlayerMessage() {
            key = "";
        }

        public TellPlayerMessage(boolean actionBar, String key, @Nullable String... strings) {
            this.key = key;
            if (strings != null) {
                keys = strings;
            }
            this.actionBar = actionBar;
        }
    }
}
