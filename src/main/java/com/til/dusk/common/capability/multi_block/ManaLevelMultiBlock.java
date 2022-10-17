package com.til.dusk.common.capability.multi_block;

import com.til.dusk.common.capability.black.IBack;
import com.til.dusk.common.capability.clock.IClock;
import com.til.dusk.common.capability.mana_level.IManaLevel;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.multiblock.MultiBlock;
import com.til.dusk.common.register.other.CapabilityRegister;
import com.til.dusk.util.Lang;
import com.til.dusk.util.nbt.pack.AllNBTPack;
import com.til.dusk.util.tooltip_pack.IComponentPack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;

public class ManaLevelMultiBlock implements IMultiBlock {

    public final MultiBlock<ManaLevel> multiBlock;
    public final IPosTrack posTrack;
    public final IManaLevel iManaLevel;

    public boolean isComplete;

    public ManaLevelMultiBlock(MultiBlock<ManaLevel> multiBlock, IClock clock, IPosTrack posTrack, IManaLevel iManaLevel) {
        this.posTrack = posTrack;
        this.multiBlock = multiBlock;
        this.iManaLevel = iManaLevel;
        clock.addBlock(this::back);
    }


    @Override
    public boolean isComplete() {
        return isComplete;
    }

    @Override
    public MultiBlock<ManaLevel> getMultiBlock() {
        return multiBlock;
    }

    @Override
    public IPosTrack pos() {
        return posTrack;
    }

    public void back() {
        isComplete = multiBlock.isCompleted(posTrack.getLevel(), posTrack.getAsBlockPos(), iManaLevel.manaLevel());
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag compoundTag = new CompoundTag();
        AllNBTPack.IS_COMPLETE.set(compoundTag, isComplete);
        return compoundTag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        isComplete = AllNBTPack.IS_COMPLETE.get(nbt);
    }

    @Nullable
    @Override
    public CompoundTag appendServerData() {
        return serializeNBT();
    }

    @Override
    public void appendTooltip(IComponentPack iTooltip, CompoundTag compoundTag) {
        iTooltip.add(Lang.getLang(
                Lang.getLang(CapabilityRegister.iMultiBlock),
                Component.literal(":"),
                Component.literal(AllNBTPack.IS_COMPLETE.get(compoundTag).toString())
        ));
    }
}
