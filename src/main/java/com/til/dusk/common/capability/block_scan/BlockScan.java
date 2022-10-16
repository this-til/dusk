package com.til.dusk.common.capability.block_scan;

import com.til.dusk.common.capability.CapabilityHelp;
import com.til.dusk.common.capability.black.IBack;
import com.til.dusk.common.capability.clock.IClock;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.register.other.CapabilityRegister;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.util.Lang;
import com.til.dusk.util.nbt.pack.AllNBTPack;
import com.til.dusk.util.tooltip_pack.IComponentPack;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

/**
 * @author til
 */
public abstract class BlockScan implements IBlockScan {

    public final IPosTrack posTrack;
    public final IControl iControl;

    public BlockPos blockPos = new BlockPos(0, 0, 0);

    public BlockScan(IClock iClock, IPosTrack posTrack, IControl iControl) {
        this.posTrack = posTrack;
        this.iControl = iControl;
        iClock.addBlock(this::back);
    }

    public void back() {
        Level level = posTrack.getLevel();
        if (level == null) {
            return;
        }
        AABB aabb = CapabilityHelp.getAABB(iControl, iControl.getMaxRange());
        if (blockPos == null || !CapabilityHelp.isInAABB(aabb, blockPos)) {
            blockPos = new BlockPos(aabb.minX, aabb.minY, aabb.minZ);
            if (CapabilityHelp.isInAABB(aabb, blockPos)) {
                run();
                return;
            }
        }
        blockPos = blockPos.offset(1, 0, 0);
        if (CapabilityHelp.isInAABB(aabb, blockPos)) {
            run();
            return;
        }
        blockPos = new BlockPos(aabb.minX, blockPos.getY() + 1, blockPos.getZ());
        if (CapabilityHelp.isInAABB(aabb, blockPos)) {
            run();
            return;
        }
        blockPos = new BlockPos(aabb.minX, aabb.minY, blockPos.getZ() + 1);
        if (CapabilityHelp.isInAABB(aabb, blockPos)) {
            run();
            return;
        }
        blockPos = null;
    }

    @Override
    public BlockPos pos() {
        return blockPos;
    }

    @Override
    public IPosTrack getPosTrack() {
        return posTrack;
    }

    @Override
    public IControl getControl() {
        return iControl;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag compoundTag = new CompoundTag();
        AllNBTPack.BLOCK_POS.set(compoundTag, blockPos);
        return compoundTag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        blockPos = AllNBTPack.BLOCK_POS.get(nbt);
    }

    @Nullable
    @Override
    public CompoundTag appendServerData() {
        return serializeNBT();
    }

    @Override
    public void appendTooltip(IComponentPack iTooltip, CompoundTag compoundTag) {
        BlockPos blockPos = AllNBTPack.BLOCK_POS.get(compoundTag);
        iTooltip.add(Lang.getLang(
                Lang.getLang(CapabilityRegister.iControl),
                Component.literal(":"),
                Component.literal(blockPos.toShortString())));
    }
}
