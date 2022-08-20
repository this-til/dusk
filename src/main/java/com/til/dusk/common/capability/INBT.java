package com.til.dusk.common.capability;


import com.til.dusk.util.AllNBT;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.util.INBTSerializable;

/***
 * 这个接口表示该能力需要存储数据
 * @author til
 */
public interface INBT extends INBTSerializable<CompoundTag> {

    /***
     * 返回一个Tag作为数据的key
     * @return tag
     */
    AllNBT.IGS<Tag> getNBTBase();

}
