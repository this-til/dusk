package com.til.dusk.common.register.mana_level.mana_level_block;

import com.til.dusk.Dusk;
import com.til.dusk.common.capability.black.Back;
import com.til.dusk.common.capability.black.IBack;
import com.til.dusk.common.capability.mana_handle.IManaHandle;
import com.til.dusk.common.capability.mana_handle.ManaHandle;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.capability.DuskCapabilityProvider;
import com.til.dusk.common.register.CapabilityRegister;
import com.til.dusk.common.register.mana_level.ManaLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.event.AttachCapabilitiesEvent;

/**
 * @author til
 */
public abstract class PassiveProductionMechanic extends DefaultCapacityMechanic {
    public PassiveProductionMechanic(ResourceLocation name) {
        super(name);
    }

    public PassiveProductionMechanic(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }


    @Override
    public void addCapability(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, ManaLevel manaLevel, IPosTrack iPosTrack) {
        super.addCapability(event, duskModCapability, manaLevel, iPosTrack);
        IBack iBack = duskModCapability.addCapability(CapabilityRegister.iBlack.capability, new Back());
        IManaHandle iManaHandle = duskModCapability.addCapability(CapabilityRegister.iManaHandle.capability, new ManaHandle(2560L * manaLevel.level, 2L * manaLevel.level, iBack));
        BlockEntity blockEntity = event.getObject();
        iBack.add(IBack.VOID, v -> up(blockEntity, iManaHandle, manaLevel));
    }

    /***
     * up回调
     * @param blockEntity 方块实体
     * @param iManaHandle 灵气处理
     * @param manaLevel 等级
     */
    public abstract void up(BlockEntity blockEntity, IManaHandle iManaHandle, ManaLevel manaLevel);

}
