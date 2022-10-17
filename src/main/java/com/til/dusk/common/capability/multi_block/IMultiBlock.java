package com.til.dusk.common.capability.multi_block;

import com.til.dusk.common.capability.ITooltipCapability;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.multiblock.MultiBlock;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public interface IMultiBlock extends ITooltipCapability , INBTSerializable<CompoundTag> {
    MultiBlock<ManaLevel> getMultiBlock();

    /***
     * 是完成的
     * 调用时取缓存以减少性能开销
     * @return 是完成的
     */
    boolean isComplete();

    /***
     * 需要保留实体供其他能力调用
     */
    IPosTrack pos();
}
