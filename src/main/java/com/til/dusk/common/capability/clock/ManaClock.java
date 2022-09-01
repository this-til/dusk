package com.til.dusk.common.capability.clock;

import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.mana_handle.ManaHandleHelp;
import com.til.dusk.common.capability.up.IUp;
import com.til.dusk.common.register.BindType;
import com.til.dusk.common.register.CapabilityRegister;
import com.til.dusk.common.register.shaped.ShapedHandleProcess;
import com.til.dusk.util.Extension;
import com.til.dusk.util.Lang;
import com.til.dusk.util.TooltipPack;
import com.til.dusk.util.nbt.pack.AllNBTPack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.Nullable;

/***
 * 指定需要消耗灵气才能
 */
public class ManaClock extends Clock implements IManaClock {

    public final BlockEntity blockEntity;
    public final IControl control;
    public final long consumeMana;
    public ShapedHandleProcess process = ShapedHandleProcess.production;

    public ManaClock(IUp up, int clock, BlockEntity blockEntity, IControl iControl, long consumeMana) {
        super(up, clock);
        this.blockEntity = blockEntity;
        this.control = iControl;
        this.consumeMana = consumeMana;
    }

    @Override
    public void up() {
        long needMana = getConsumeMana();
        time--;
        if (time <= 0) {
            time = clock;
            if (!process.equals(ShapedHandleProcess.trippingOperation)) {
                blackRun.forEach(Extension.Action::action);
            } else {
                setProcess(ShapedHandleProcess.production);
            }
        }
        if (needMana == 0) {
            return;
        }
        if (process.equals(ShapedHandleProcess.trippingOperation)) {
            return;
        }
        if (ManaHandleHelp.extractMana(getConsumeMana(), getControl().getCapability(BindType.manaIn), getThis()) < getConsumeMana()) {
            setProcess(ShapedHandleProcess.trippingOperation);
        }
    }

    @Override
    public IControl getControl() {
        return control;
    }

    @Override
    public long getConsumeMana() {
        return consumeMana;
    }

    @Override
    public BlockEntity getThis() {
        return blockEntity;
    }

    @Override
    public ShapedHandleProcess getProcess() {
        return process;
    }

    @Override
    public void setProcess(ShapedHandleProcess process) {
        this.process = process;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = super.serializeNBT();
        AllNBTPack.PROCESS.set(nbt, process);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        super.deserializeNBT(nbt);
        process = AllNBTPack.PROCESS.get(nbt);
    }

    @Nullable
    @Override
    public CompoundTag appendServerData(ServerPlayer serverPlayer, Level level, BlockEntity blockEntity, boolean detailed) {
        CompoundTag compoundTag = serializeNBT();
        AllNBTPack.CYCLE_TIME.set(compoundTag, getCycleTime());
        AllNBTPack.CONSUME_MANA.set(compoundTag, getConsumeMana());
        return compoundTag;

    }

    @Override
    public void appendTooltip(TooltipPack iTooltip, CompoundTag compoundTag) {
        iTooltip.add(Lang.getLang(Lang.getLang(CapabilityRegister.iClock), Component.literal(AllNBTPack.TIME.get(compoundTag) + "/" + AllNBTPack.CYCLE_TIME.get(compoundTag))));
        iTooltip.indent();
        iTooltip.add(Lang.getLang(Component.translatable(Lang.getKey("状态")), Lang.getLang(AllNBTPack.PROCESS.get(compoundTag))));
        iTooltip.add(Lang.getLang(Component.translatable(Lang.getKey("消耗灵气")), Component.literal(String.valueOf(AllNBTPack.CONSUME_MANA.get(compoundTag)))));
    }

}
