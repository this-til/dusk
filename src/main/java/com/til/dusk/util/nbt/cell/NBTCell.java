package com.til.dusk.util.nbt.cell;

import net.minecraft.nbt.Tag;

/***
 * 这是一个NBT元素类
 * @author til
 */
public abstract class NBTCell<T extends Tag, E> {

    protected ListNBTCell<E> listNBTCell;

    public abstract T as(E e);

    public abstract E from(T t);

    public abstract byte getaNBTId();

    public ListNBTCell<E> getListNBTCell() {
        if (listNBTCell == null) {
            listNBTCell = new ListNBTCell<>(this);
        }
        return listNBTCell;
    }

}
