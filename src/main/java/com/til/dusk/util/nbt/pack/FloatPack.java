package com.til.dusk.util.nbt.pack;

import com.til.dusk.util.nbt.cell.AllNBTCell;
import com.til.dusk.util.nbt.pack.NBTPack;

public class FloatPack extends NBTPack<Float> {

    public FloatPack(String name) {
        super(name, AllNBTCell.FLOAT);
    }

}
