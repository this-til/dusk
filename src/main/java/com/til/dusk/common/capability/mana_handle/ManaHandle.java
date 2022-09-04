package com.til.dusk.common.capability.mana_handle;

import com.til.dusk.common.capability.up.IUp;
import com.til.dusk.common.register.CapabilityRegister;
import com.til.dusk.util.Lang;
import com.til.dusk.util.Pos;
import com.til.dusk.util.RoutePack;
import com.til.dusk.util.TooltipPack;
import com.til.dusk.util.nbt.pack.AllNBTPack;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.MinecraftForge;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

/**
 * @author til
 */
public class ManaHandle implements IManaHandle {

    public final long maxMana;
    public final long maxRate;
    public long inRate;
    public long outRate;
    public long mana;

    public ManaHandle(long maxRate, IUp iUp, long maxMana) {
        this.maxMana = maxMana;
        this.maxRate = maxRate;
        iUp.addUpBlack(this::up);
    }

    public void up() {
        inRate = maxRate;
        outRate = maxRate;
    }

    @Override
    public long getInCurrentRate() {
        return inRate;
    }

    @Override
    public long getOutCurrentRate() {
        return outRate;
    }

    @Override
    public long getMaxMana() {
        return maxMana;
    }

    @Override
    public long getMana() {
        return mana;
    }

    @Override
    public long getMaxRate() {
        return maxRate;
    }



    @Override
    public long addMana(long mana, boolean isSimulate) {
        if (mana <= 0) {
            return 0;
        }
        long addMana = Math.min(Math.min(mana, this.getInCurrentRate()), this.getRemainMana());
        if (addMana != 0) {
            if (!isSimulate) {
                this.mana += addMana;
                this.inRate -= addMana;
                MinecraftForge.EVENT_BUS.post(new EventManaHandle.Add(this, addMana));
            }
        }
        return addMana;
    }

    @Override
    public long extractMana(long demand, boolean isSimulate) {
        if (demand <= 0) {
            return 0;
        }
        long extractMana = Math.min(Math.min(demand, this.getOutCurrentRate()), this.getMana());
        if (extractMana != 0) {
            if (!isSimulate) {
                this.mana -= extractMana;
                this.outRate -= extractMana;
                MinecraftForge.EVENT_BUS.post(new EventManaHandle.Extract(this, extractMana));
            }
        }
        return extractMana;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbtTagCompound = new CompoundTag();
        AllNBTPack.MANA.set(nbtTagCompound, mana);
        return nbtTagCompound;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        mana = AllNBTPack.MANA.get(nbt);
    }

    @Nullable
    @Override
    public CompoundTag appendServerData(ServerPlayer serverPlayer, Level level, BlockEntity blockEntity, boolean detailed) {
        CompoundTag compoundTag = serializeNBT();
        AllNBTPack.MAX_MANA.set(compoundTag, getMaxMana());
        AllNBTPack.RATE.set(compoundTag, getMaxRate());
        return compoundTag;
    }

    @Override
    public void appendTooltip(TooltipPack iTooltip, CompoundTag compoundTag) {
        iTooltip.add(Lang.getLang(CapabilityRegister.iManaHandle));
        iTooltip.indent();
        long mana = AllNBTPack.MANA.get(compoundTag);
        long maxMana = AllNBTPack.MAX_MANA.get(compoundTag);
        long rate = AllNBTPack.RATE.get(compoundTag);
        if (mana > 0 && maxMana > 0) {
            iTooltip.add(Lang.getLang(Component.translatable(Lang.getKey("现存灵气")), Component.literal(mana + "/" + maxMana)));
        }
        iTooltip.add(Lang.getLang(Component.translatable(Lang.getKey("最大灵气流速")), Component.literal(String.valueOf(rate))));

    }
}
