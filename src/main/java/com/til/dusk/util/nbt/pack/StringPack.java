package com.til.dusk.util.nbt.pack;

import com.til.dusk.util.nbt.cell.AllNBTCell;
import com.til.dusk.util.nbt.pack.NBTPack;

/**
 * @author til
 */
public class StringPack extends NBTPack<String> {
    public StringPack(String name) {
        super(name, AllNBTCell.STRING);
    }
}
