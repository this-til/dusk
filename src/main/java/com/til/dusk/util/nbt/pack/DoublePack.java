package com.til.dusk.util.nbt.pack;

import com.til.dusk.util.nbt.cell.AllNBTCell;

/**
 * @author til
 */
public class DoublePack extends NBTPack<Double> {
    public DoublePack(String name) {
        super(name, AllNBTCell.DOUBLE);
    }

}
