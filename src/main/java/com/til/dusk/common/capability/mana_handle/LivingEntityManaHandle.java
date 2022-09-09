package com.til.dusk.common.capability.mana_handle;

import com.til.dusk.common.capability.CapabilityHelp;
import com.til.dusk.common.capability.mana_handle.IManaHandle;
import com.til.dusk.common.capability.up.IUp;
import com.til.dusk.common.register.BindType;
import com.til.dusk.common.register.CapabilityRegister;
import com.til.dusk.util.Extension;
import com.til.dusk.util.RoutePack;
import com.til.dusk.util.TooltipPack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LivingEntityManaHandle implements IManaHandle {

    public final LivingEntity livingEntity;

    public boolean lock;

    public LivingEntityManaHandle(LivingEntity livingEntity) {
        this.livingEntity = livingEntity;
    }

    public List<IManaHandle> getAllItemManaHandle() {
        List<IManaHandle> iManaHandles = new ArrayList<>();
        for (IItemHandler livingEntityIItemHandler : CapabilityHelp.getLivingEntityIItemHandlers(livingEntity)) {
            for (int i = 0; i < livingEntityIItemHandler.getSlots(); i++) {
                ItemStack itemStack = livingEntityIItemHandler.getStackInSlot(i);
                LazyOptional<IManaHandle> iManaHandleLazyOptional = itemStack.getCapability(CapabilityRegister.iManaHandle.capability);
                if (iManaHandleLazyOptional.isPresent()) {
                    iManaHandles.add(iManaHandleLazyOptional.orElse(null));
                }
            }
        }
        return iManaHandles;
    }


    public long integration(Extension.Func_1I<IManaHandle, Long> func1I) {
        if (lock) {
            return 0;
        }
        lock = true;
        List<IManaHandle> allItemManaHandle = getAllItemManaHandle();
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
        List<IManaHandle> allItemManaHandle = getAllItemManaHandle();
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
        List<IManaHandle> allItemManaHandle = getAllItemManaHandle();
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
    public CompoundTag appendServerData(ServerPlayer serverPlayer, Level level, BlockEntity blockEntity, boolean detailed) {
        return new CompoundTag();
    }

    @Override
    public void appendTooltip(TooltipPack iTooltip, CompoundTag compoundTag) {

    }
}
