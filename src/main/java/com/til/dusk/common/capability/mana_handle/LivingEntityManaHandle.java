package com.til.dusk.common.capability.mana_handle;

import com.til.dusk.common.capability.black.IBack;
import com.til.dusk.common.register.CapabilityRegister;
import com.til.dusk.util.Extension;
import com.til.dusk.util.tooltip_pack.IComponentPack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class LivingEntityManaHandle implements IManaHandle {

    public final LivingEntity livingEntity;
    public List<IManaHandle> allManaHandles = new ArrayList<>(EquipmentSlot.values().length);

    public boolean lock;

    public final IBack iBack;

    public LivingEntityManaHandle(LivingEntity livingEntity, IBack iBack) {
        this.livingEntity = livingEntity;
        this.iBack = iBack;
        iBack.add(IBack.LIVING_EQUIPMENT_CHANGE_EVENT, event -> {
            allManaHandles.clear();
            for (EquipmentSlot value : EquipmentSlot.values()) {
                ItemStack itemStack = livingEntity.getItemBySlot(value);
                if (itemStack.isEmpty()) {
                    continue;
                }
                LazyOptional<IManaHandle> lazyOptional = itemStack.getCapability(CapabilityRegister.iManaHandle.capability);
                if (lazyOptional.isPresent()) {
                    allManaHandles.add(lazyOptional.orElse(null));
                }
            }
        });
    }


    public long integration(Extension.Func_1I<IManaHandle, Long> func1I) {
        if (lock) {
            return 0;
        }
        lock = true;
        List<IManaHandle> allItemManaHandle = allManaHandles;
        if (allItemManaHandle.isEmpty()) {
            lock = false;
            return 0;
        }
        long mana = 0;
        for (IManaHandle value : allItemManaHandle) {
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
    public long getMaxRate() {
        return integration(IManaHandle::getMaxRate);
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
    public long addMana(long mana, boolean simulate) {
        if (lock) {
            return 0;
        }
        lock = true;
        List<IManaHandle> allItemManaHandle = allManaHandles;
        if (allItemManaHandle.isEmpty()) {
            lock = false;
            return 0;
        }
        long inMana = 0;
        for (IManaHandle iManaHandle : allItemManaHandle) {
            long in = iManaHandle.addMana(mana, simulate);
            inMana += in;
            mana -= in;
            if (mana <= 0) {
                break;
            }
        }
        lock = false;
        return inMana;
    }

    @Override
    public long extractMana(long demand, boolean simulate) {
        if (lock) {
            return 0;
        }
        lock = true;
        List<IManaHandle> allItemManaHandle = allManaHandles;
        if (allItemManaHandle.isEmpty()) {
            lock = false;
            return 0;
        }
        long outMana = 0;
        for (IManaHandle iManaHandle : allItemManaHandle) {
            long out = iManaHandle.extractMana(demand, simulate);
            outMana += out;
            demand -= out;
            if (demand <= 0) {
                break;
            }
        }
        lock = false;
        return outMana;
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
        return new CompoundTag();
    }

    @Override
    public void appendTooltip(IComponentPack iTooltip, CompoundTag compoundTag) {

    }
}
