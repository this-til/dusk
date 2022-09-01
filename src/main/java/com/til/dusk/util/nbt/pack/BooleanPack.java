package com.til.dusk.util.nbt.pack;

import com.til.dusk.util.nbt.cell.AllNBTCell;
import com.til.dusk.util.nbt.pack.NBTPack;

/**
 * @author til
 */
public class BooleanPack extends NBTPack<Boolean> {
    public BooleanPack(String name) {
        super(name, AllNBTCell.BOOLEAN);
    }


}
