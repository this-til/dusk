package com.til.dusk.util.nbt.pack;

import com.til.dusk.util.nbt.cell.AllNBTCell;
import com.til.dusk.util.nbt.pack.NBTPack;

public class IntPack extends NBTPack<Integer> {

    public IntPack(String name) {
        super(name, AllNBTCell.INT);
    }
}
