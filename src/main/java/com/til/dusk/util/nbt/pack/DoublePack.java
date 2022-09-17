package com.til.dusk.util.nbt.pack;

import com.til.dusk.util.nbt.cell.AllNBTCell;
import com.til.dusk.util.nbt.pack.NBTPack;

/**
 * @author til
 */
public class DoublePack extends NBTPack<Double> {
    public DoublePack(String name) {
        super(name, AllNBTCell.DOUBLE);
    }

}
