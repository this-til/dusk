package com.til.dusk.util.nbt.pack;

import com.til.dusk.util.nbt.cell.AllNBTCell;

/**
 * @author til
 */
public class LongPack extends NBTPack<Long> {

    public LongPack(String name) {
        super(name, AllNBTCell.LONG);
    }
}
