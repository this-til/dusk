package com.til.dusk.capability;


import com.til.dusk.util.AllNBT;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.util.INBTSerializable;

public interface INBT extends INBTSerializable<CompoundTag> {

    AllNBT.IGS<Tag> getNBTBase();

}
