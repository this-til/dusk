package com.til.dusk.common.capability.mana_handle;

import com.til.dusk.common.capability.ITooltipCapability;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * @author til
 */
public interface IManaHandle extends INBTSerializable<CompoundTag>, ITooltipCapability {

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
     * 返回当前输入速度
     */
    long getInCurrentRate();

    /***
     * 返回当前输出的速度
     */
    long getOutCurrentRate();

    /***
     * 添加灵气
     * 返返回加入了多少
     */
    long addMana(long mana, boolean isSimulate);

    /***
     *  抽取灵气
     *  返回提取了多少
     */
    long extractMana(long demand, boolean isSimulate);



}
