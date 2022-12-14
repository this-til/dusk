package com.til.dusk.util.nbt.cell;

import com.google.gson.JsonElement;
import com.til.dusk.Dusk;
import net.minecraft.nbt.*;

/***
 * 这是一个NBT元素类
 * @author til
 */
public abstract class NBTCell<E> {

    protected ListNBTCell<E> listNBTCell;

    public abstract Tag as(E e);

    public abstract E from(Tag t);

    public abstract JsonElement asJson(E e);

    public abstract E fromJson(JsonElement json);

    public ListNBTCell<E> getListNBTCell() {
        if (listNBTCell == null) {
            listNBTCell = new ListNBTCell<>(this);
        }
        return listNBTCell;
    }

    public static CompoundTag getAsCompoundTag(Tag tag) {
        if (tag instanceof CompoundTag compoundTag) {
            return compoundTag;
        }
        return new CompoundTag();
    }

    public static ListTag getAsListTag(Tag tag) {
        if (tag instanceof ListTag listTag) {
            return listTag;
        }
        return new ListTag();
    }

    public static NumericTag getAsNumericTag(Tag tag) {
        if (tag instanceof NumericTag numericTag) {
            return numericTag;
        }
        if (tag instanceof StringTag stringTag) {
            try {
                String s = stringTag.getAsString();
                if (s.isEmpty()) {
                    return DoubleTag.valueOf(0);
                }
                char c = s.charAt(s.length() - 1);
                if (!Character.isDigit(c)) {
                    s = s.substring(0, s.length() - 1);
                }
                return DoubleTag.valueOf(Double.parseDouble(s));
            } catch (Exception e) {
                Dusk.instance.logger.error("获取NBT值时出错", e);
            }
        }
        return DoubleTag.valueOf(0);
    }
}
