package com.til.dusk.util.nbt.pack;

import com.til.dusk.util.nbt.cell.AllNBTCell;

public class IntPack extends NBTPack<Integer> {

    public IntPack(String name) {
        super(name, AllNBTCell.INT);
    }
}
