package com.til.dusk.common.capability.handle;


import com.til.dusk.common.capability.*;
import com.til.dusk.common.capability.black.IBack;
import com.til.dusk.common.capability.clock.IClock;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.common.register.shaped.ShapedDrive;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.*;


/**
 * @author til
 */
public interface IHandle extends INBTSerializable<CompoundTag>, ITooltipCapability {

    /***
     * 获取所有的配方
     */
    Set<Shaped> getShaped();

    void addShapedHandle(ShapedHandle shaped);

    /***
     * 获取最大配方并行
     */
    int getParallelHandle();

    Set<ShapedDrive> getShapedDrive();

    IControl getControl();

    IClock getClockTime();

    IBack getBack();

    IPosTrack getPosTrack();
}
