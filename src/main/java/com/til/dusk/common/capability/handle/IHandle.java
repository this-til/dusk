package com.til.dusk.common.capability.handle;


import com.til.dusk.common.capability.*;
import com.til.dusk.common.capability.clock.IClock;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.up.IUp;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.common.register.shaped.ShapedDrive;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.*;


public interface IHandle extends IThis<BlockEntity>, INBTSerializable<CompoundTag>, ITooltipCapability {

    /***
     * 获取所有的配方
     */
    List<Shaped> getShaped();

    void addShapedHandle(ShapedHandle shaped);

    /***
     * 获取最大配方并行
     */
    int getParallelHandle();

    List<ShapedDrive> getShapedDrive();

    IControl getControl();

    IClock getClockTime();

    IUp getUp();
}
