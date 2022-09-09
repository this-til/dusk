package com.til.dusk.common.register.mana_level.mana_level_block;

import com.til.dusk.Dusk;
import com.til.dusk.common.capability.mana_handle.IManaHandle;
import com.til.dusk.common.capability.mana_handle.ManaHandle;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.capability.tile_entity.DuskCapabilityProvider;
import com.til.dusk.common.capability.up.IUp;
import com.til.dusk.common.capability.up.Up;
import com.til.dusk.common.register.CapabilityRegister;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.world.block.MechanicBlock;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.event.AttachCapabilitiesEvent;

/**
 * @author til
 */
public abstract class PassiveProductionMechanic extends Mechanic {
    public PassiveProductionMechanic(ResourceLocation name) {
        super(name);
    }

    public PassiveProductionMechanic(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    @Override
    public Block createBlock(ManaLevel manaLevel) {
        return new MechanicBlock(manaLevel) {
            @Override
            public void add(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, IPosTrack iPosTrack) {
                duskModCapability.addCapability(CapabilityRegister.iManaLevel.capability, () -> manaLevel);
                IUp iUp = duskModCapability.addCapability(CapabilityRegister.iUp.capability, new Up());
                IManaHandle iManaHandle = duskModCapability.addCapability(CapabilityRegister.iManaHandle.capability, new ManaHandle(2560L * manaLevel.level, 2L * manaLevel.level, iUp));
                BlockEntity blockEntity = event.getObject();
                iUp.addUpBlack(() -> up(blockEntity, iManaHandle, manaLevel));
            }
        };
    }

    /***
     * up回调
     * @param blockEntity 方块实体
     * @param iManaHandle 灵气处理
     * @param manaLevel 等级
     */
    public abstract void up(BlockEntity blockEntity, IManaHandle iManaHandle, ManaLevel manaLevel);

}
