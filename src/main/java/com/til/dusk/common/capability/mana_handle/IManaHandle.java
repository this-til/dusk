package com.til.dusk.common.capability.mana_handle;

import com.til.dusk.common.capability.IThis;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.util.INBTSerializable;

public interface IManaHandle extends IThis<BlockEntity>, INBTSerializable<CompoundTag> {

    /***
     * 返回最大容量
     */
    long getMaxMana();

    /***
     * 返回当前容量
     */
    long getMana();

    /***
     * 返回剩余空间
     */
    default long getRemainMana() {
        return Math.max(this.getMaxMana() - this.getMana(), 0);
    }

    /***
     * 返回最大的提取速度
     */
    long getMaxRate();

    /***
     * 添加灵气
     * 返返回加入了多少
     */
    long addMana(long mana);

    /***
     *  抽取灵气
     *  返回提取了多少
     */
    long extractMana(long demand);

}
