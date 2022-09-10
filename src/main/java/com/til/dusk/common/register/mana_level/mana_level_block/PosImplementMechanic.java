package com.til.dusk.common.register.mana_level.mana_level_block;

import com.til.dusk.Dusk;
import com.til.dusk.common.capability.CapabilityHelp;
import com.til.dusk.common.capability.clock.IClock;
import com.til.dusk.common.capability.clock.IManaClock;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.capability.tile_entity.DuskCapabilityProvider;
import com.til.dusk.common.capability.up.IUp;
import com.til.dusk.common.capability.up.Up;
import com.til.dusk.common.register.CapabilityRegister;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.util.Extension;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.AttachCapabilitiesEvent;

import javax.annotation.Nullable;

/***
 * 在给定范围内进行坐标递增
 * @author til
 */
public abstract class PosImplementMechanic extends DefaultCapacityMechanic {

    @Nullable
    public BlockPos blockPos;

    public PosImplementMechanic(ResourceLocation name) {
        super(name);
    }

    public PosImplementMechanic(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    @Override
    public void addCapability(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, ManaLevel manaLevel, IPosTrack iPosTrack) {
        super.addCapability(event, duskModCapability, manaLevel, iPosTrack);
        IControl iControl = duskModCapability.addCapability(CapabilityRegister.iControl.capability, createControl(manaLevel, iPosTrack));
        IUp iUp = duskModCapability.addCapability(CapabilityRegister.iUp.capability, new Up());
        IClock iClock = duskModCapability.addCapability(CapabilityRegister.iClock.capability, createClock(manaLevel, iUp, iControl, iPosTrack));
        Extension.Action_3V<BlockPos, IControl, IPosTrack> run = createBlockBlack();
        iClock.addBlock(() -> {
            Level level = iPosTrack.getLevel();
            if (level == null) {
                return;
            }
            AABB aabb = CapabilityHelp.getAABB(iControl, iControl.getMaxRange());
            if (blockPos == null || !CapabilityHelp.isInAABB(aabb, blockPos)) {
                blockPos = new BlockPos(aabb.minX, aabb.minY, aabb.minZ);
                if (CapabilityHelp.isInAABB(aabb, blockPos)) {
                    run.action(blockPos, iControl, iPosTrack);
                    return;
                }
            }
            blockPos = blockPos.offset(1, 0, 0);
            if (CapabilityHelp.isInAABB(aabb, blockPos)) {
                run.action(blockPos, iControl, iPosTrack);
                return;
            }
            blockPos = new BlockPos(aabb.minX, blockPos.getY() + 1, blockPos.getZ());
            if (CapabilityHelp.isInAABB(aabb, blockPos)) {
                run.action(blockPos, iControl, iPosTrack);
                return;
            }
            blockPos = new BlockPos(aabb.minX, aabb.minY, blockPos.getZ() + 1);
            if (CapabilityHelp.isInAABB(aabb, blockPos)) {
                run.action(blockPos, iControl, iPosTrack);
                return;
            }
            blockPos = null;
        });
    }

    public abstract IControl createControl(ManaLevel manaLevel, IPosTrack iPosTrack);

    public abstract IClock createClock(ManaLevel manaLevel, IUp iup, IControl iControl, IPosTrack iPosTrack);

    public abstract Extension.Action_3V<BlockPos, IControl, IPosTrack> createBlockBlack();
}
