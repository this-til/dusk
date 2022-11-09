package com.til.dusk.common.capability.mana_handle;

import com.til.dusk.common.capability.black.IBack;
import com.til.dusk.util.Extension;

/***
 * 可变灵气处理
 * 可以通过外部的值控制最大容量和流速
 * @author til
 */
public class VariableManaHandle extends ManaHandle {

    public final Extension.Func<Double> manaMultiple;
    public final Extension.Func<Double> rateMultiple;


    public VariableManaHandle(long maxMana, long maxIn, long maxOut, IBack back, Extension.Func<Double> manaMultiple, Extension.Func<Double> rateMultiple) {
        super(maxMana, maxIn,maxOut, back);
        this.manaMultiple = manaMultiple;
        this.rateMultiple = rateMultiple;
    }

    @Override
    public long getMaxMana() {
        if (manaMultiple == null) {
            return super.getMaxMana();
        }
        return (long) (maxMana * manaMultiple.func());
    }

    @Override
    public long getMaxOut() {
        if (rateMultiple == null) {
            return super.getMaxOut();
        }
        return (long) (maxOut * rateMultiple.func());
    }

    @Override
    public long getRemainMana() {
        if (rateMultiple == null) {
            return super.getMaxOut();
        }
        return (long) (maxOut * rateMultiple.func());
    }
}
