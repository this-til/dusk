package com.til.dusk.util.nbt.pack;

import com.til.dusk.util.nbt.cell.AllNBTCell;
import com.til.dusk.util.nbt.pack.NBTPack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;

/**
 * @author til
 */
public class StringPack extends NBTPack<String> {
    public StringPack(String name) {
        super(name, AllNBTCell.STRING);
    }
}
