package com.til.dusk.common.capability.block_scan;

import com.til.dusk.common.capability.ITooltipCapability;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.pos.IPosTrack;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * @author til
 */
public interface IBlockScan extends INBTSerializable<CompoundTag>, ITooltipCapability {

    /***
     * 扫描到那个方块来了
     */
    BlockPos pos();

    void run();

    IPosTrack getPosTrack();

    IControl getControl();
}
