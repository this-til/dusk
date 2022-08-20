package com.til.dusk.common.capability;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraft.world.level.block.Block;

import java.util.Map;

/***
 * 接口提供给{@link Block},{@link BlockEntity}用来添加能力
 * @author til
 */
public interface ITileEntityType {

    /***
     * 添加能力
     * @param event 添加能力时触发的事件
     * @param map 能力和能力实体的对照表
     */
    default void add(AttachCapabilitiesEvent<BlockEntity> event, Map<Capability<?>, Object> map) {
    }

}
