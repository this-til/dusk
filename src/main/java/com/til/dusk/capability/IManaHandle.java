package com.til.dusk.capability;

import com.google.gson.JsonObject;
import com.til.dusk.register.ManaLevel;
import com.til.dusk.util.AllNBT;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.Capability;

import java.util.Random;

public interface IManaHandle extends IThis<BlockEntity>, INBT, IManaLevel {

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

    class ManaHandle implements IManaHandle {

        public long mana;
        public final BlockEntity tileEntity;
        public final IManaLevel iManaLevel;

        public ManaHandle(BlockEntity tileEntity, IManaLevel iManaLevel) {
            this.tileEntity = tileEntity;
            this.iManaLevel = iManaLevel;
        }

        /***
         * 返回最大容量
         */
        @Override
        public long getMaxMana() {
            return 1260000L * getManaLevel().level;
        }

        /***
         * 返回当前容量
         */
        @Override
        public long getMana() {
            return mana;
        }

        /***
         * 返回最大的提取速度
         */
        @Override
        public long getMaxRate() {
            return 32L * getManaLevel().level;
        }

        /***
         * 添加灵气
         * 返返回加入了多少
         */
        @Override
        public long addMana(long mana) {
            if (mana == 0) {
                return 0;
            }
            long addMana = Math.min(Math.min(mana, this.getMaxRate()), this.getRemainMana());
            this.mana += addMana;
            return addMana;
        }

        /***
         *  抽取灵气
         *  返回提取了多少
         */
        @Override
        public long extractMana(long demand) {
            if (demand == 0) {
                return 0;
            }
            long extractMana = Math.min(Math.min(demand, this.getMaxRate()), this.getMana());
            this.mana -= extractMana;
            return extractMana;
        }

        @Override
        public AllNBT.IGS<Tag> getNBTBase() {
            return AllNBT.iManaHandleNBT;
        }

        @Override
        public ManaLevel getManaLevel() {
            return iManaLevel.getManaLevel();
        }

        /***
         * 返回自己
         */
        @Override
        public BlockEntity getThis() {
            return tileEntity;
        }

        @Override
        public CompoundTag serializeNBT() {
            CompoundTag nbtTagCompound = new CompoundTag();
            AllNBT.modMana.set(nbtTagCompound, mana);
            return nbtTagCompound;
        }

        @Override
        public void deserializeNBT(CompoundTag nbt) {
            mana = AllNBT.modMana.get(nbt);
        }
    }
}
