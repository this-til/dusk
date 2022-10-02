package com.til.dusk.common.register.mana_level.block;

import com.til.dusk.Dusk;
import com.til.dusk.common.capability.black.Back;
import com.til.dusk.common.capability.black.IBack;
import com.til.dusk.common.capability.control.Control;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.capability.DuskCapabilityProvider;
import com.til.dusk.common.register.other.BindType;
import com.til.dusk.common.register.other.CapabilityRegister;
import com.til.dusk.common.register.mana_level.ManaLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.event.AttachCapabilitiesEvent;

import java.util.List;

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
        IControl iControl = duskModCapability.addCapability(CapabilityRegister.iControl.capability, new Control(iPosTrack, List.of(BindType.manaOut), manaLevel));
        BlockEntity blockEntity = event.getObject();
        iBack.add(IBack.VOID, v -> up(blockEntity, iControl, manaLevel));
    }

    /***
     * up回调
     * @param blockEntity 方块实体
     * @param iControl 灵气处理
     * @param manaLevel 等级
     */
    public abstract void up(BlockEntity blockEntity, IControl iControl, ManaLevel manaLevel);

}
