package com.til.dusk.common.capability.mana_handle;

import com.til.dusk.common.capability.CapabilityHelp;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.register.bind_type.BindType;
import com.til.dusk.common.register.other.CapabilityRegister;
import com.til.dusk.util.Extension;
import com.til.dusk.util.Lang;
import com.til.dusk.util.RoutePack;
import com.til.dusk.util.nbt.pack.AllNBTPack;
import com.til.dusk.util.tooltip_pack.IComponentPack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class WhirlBoostManaHandle implements IManaHandle, RoutePack.ISupportRoutePack<Long> {

    public final IControl control;

    public boolean lock;

    public RoutePack<Long> routePackCache;

    /***
     * 灵气损耗
     */
    public final double manaLoss;

    public WhirlBoostManaHandle(IControl control, double manaLoss) {
        this.control = control;
        this.manaLoss = manaLoss;
    }

    public long integration(Extension.Func_1I<IManaHandle, Long> func1I) {
        if (lock) {
            return 0;
        }
        lock = true;
        Map<IPosTrack, IManaHandle> map = control.getCapability(BindType.manaIn);
        if (map.isEmpty()) {
            lock = false;
            return 0;
        }
        long mana = 0;
        for (IManaHandle value : map.values()) {
            mana += func1I.func(value);
        }
        lock = false;
        return mana;
    }

    @Override
    public long getMaxMana() {
        return integration(IManaHandle::getMaxMana);
    }

    @Override
    public long getMana() {
        return integration(IManaHandle::getMana);
    }

    @Override
    public long getMaxOut() {
        return integration(IManaHandle::getMaxOut);
    }

    @Override
    public long getMaxIn() {
        return integration(IManaHandle::getMaxIn);
    }

    @Override
    public long getInCurrentRate() {
        return integration(IManaHandle::getInCurrentRate);
    }

    @Override
    public long getOutCurrentRate() {
        return integration(IManaHandle::getOutCurrentRate);
    }

    @Override
    public void set(RoutePack<Long> routePack) {
        routePackCache = routePack;
    }

    @Override
    public long addMana(long mana, boolean simulate) {
        if (lock) {
            return 0;
        }
        lock = true;
        Map<IPosTrack, IManaHandle> map = control.getCapability(BindType.manaIn);
        if (map.isEmpty()) {
            lock = false;
            return 0;
        }
        long l = (long) (mana * manaLoss);
        long inMana = CapabilityHelp.addMana(control.getPosTrack(), routePackCache, map, mana - l, simulate);
        this.routePackCache = null;
        lock = false;
        inMana += l;
        return inMana;
    }

    @Override
    public long extractMana(long demand, boolean simulate) {
        if (lock) {
            return 0;
        }
        lock = true;
        Map<IPosTrack, IManaHandle> map = control.getCapability(BindType.manaIn);
        if (map.isEmpty()) {
            lock = false;
            return 0;
        }
        long l = (long) (demand * manaLoss);
        long mana = CapabilityHelp.extractMana(control.getPosTrack(), routePackCache, map, demand + l, simulate);
        this.routePackCache = null;
        lock = false;
        mana = mana - l;
        return mana;
    }

    @Override
    public CompoundTag serializeNBT() {
        return new CompoundTag();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {

    }

    @Nullable
    @Override
    public CompoundTag appendServerData() {
        CompoundTag compoundTag = new CompoundTag();
        AllNBTPack.MANA.set(compoundTag, getMana());
        AllNBTPack.MAX_MANA.set(compoundTag, getMaxMana());
        AllNBTPack.RATE_IN.set(compoundTag, getMaxOut());
        return compoundTag;
    }

    @Override
    public void appendTooltip(IComponentPack iTooltip, CompoundTag compoundTag) {
        iTooltip.add(Lang.getLang(
                Lang.getLang(CapabilityRegister.iManaHandle),
                Component.literal(":")));
        iTooltip.indent();
        long mana = AllNBTPack.MANA.get(compoundTag);
        long maxMana = AllNBTPack.MAX_MANA.get(compoundTag);
        long rate = AllNBTPack.RATE_IN.get(compoundTag);
        if (mana > 0 && maxMana > 0) {
            iTooltip.add(Lang.getLang(Component.translatable(Lang.getKey("现存灵气")), Component.literal(mana + "/" + maxMana)));
        }
        iTooltip.add(Lang.getLang(Component.translatable(Lang.getKey("最大灵气流速")), Component.literal(String.valueOf(rate))));

    }
}
