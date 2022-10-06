package com.til.dusk.common.register.mana_level.block;


import com.til.dusk.common.capability.CapabilityHelp;
import com.til.dusk.common.capability.DuskCapabilityProvider;
import com.til.dusk.common.capability.black.Back;
import com.til.dusk.common.capability.black.IBack;
import com.til.dusk.common.capability.control.Control;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.energy_storage.DuskEnergyStorage;
import com.til.dusk.common.capability.mana_handle.EventManaHandle;
import com.til.dusk.common.capability.mana_handle.IManaHandle;
import com.til.dusk.common.capability.mana_handle.ManaHandle;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.register.other.BindType;
import com.til.dusk.common.register.other.CapabilityRegister;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;

import java.util.List;
import java.util.Map;

/**
 * @author til
 */
public class TransformationFEMechanic extends DefaultCapacityMechanic {
    public TransformationFEMechanic() {
        super("transformation_fe");
    }

    @Override
    public void addCapability(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, ManaLevel manaLevel, IPosTrack iPosTrack) {
        super.addCapability(event, duskModCapability, manaLevel, iPosTrack);
        IBack back = duskModCapability.addCapability(CapabilityRegister.iBlack.capability, new Back());
        IControl iControl = duskModCapability.addCapability(CapabilityRegister.iControl.capability, new Control(iPosTrack, List.of(BindType.manaIn), manaLevel));
        TransformationFEEnergyStorage energyStorage = (TransformationFEEnergyStorage) duskModCapability.addCapability(CapabilityRegister.iEnergyStorage.capability, new TransformationFEEnergyStorage(25600000 * manaLevel.level, manaLevel.level * 512, back));
        back.add(IBack.UP, v -> {
            if (energyStorage.getEnergyStored() >= energyStorage.getMaxEnergyStored()) {
                return;
            }
            Map<IPosTrack, IManaHandle> inMana = iControl.getCapability(BindType.manaIn);
            if (inMana.isEmpty()) {
                return;
            }
            energyStorage.receiveEnergyInside((int) (CapabilityHelp.extractMana(iPosTrack, null, inMana, manaLevel.level * 64L, false) * 8), false);
        });
    }

    public static class TransformationFEManaHandle extends ManaHandle {
        public TransformationFEManaHandle(long maxMana, long maxRate, IBack iBack) {
            super(maxMana, maxRate, iBack);
        }

        @Override
        public long extractMana(long demand, boolean isSimulate) {
            return 0;
        }

        public long extractManaInside(long demand, boolean isSimulate) {
            if (demand <= 0) {
                return 0;
            }
            long extractMana = Math.min(Math.min(demand, getMaxRate()), this.getMana());
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
        public long getOutCurrentRate() {
            return 0;
        }
    }

    public static class TransformationFEEnergyStorage extends DuskEnergyStorage {
        public TransformationFEEnergyStorage(int maxStorage, int maxRate, IBack back) {
            super(maxStorage, maxRate, back);
        }

        @Override
        public boolean canReceive() {
            return false;
        }


        @Override
        public int receiveEnergy(int maxReceive, boolean isSimulate) {
            return 0;
        }

        public int receiveEnergyInside(int maxReceive, boolean isSimulate) {
            if (maxReceive <= 0) {
                return 0;
            }
            int addExtract = Math.min(Math.min(maxReceive, getRemainEnergy()), this.getRemainEnergy());
            if (addExtract != 0) {
                if (!isSimulate) {
                    this.energy += addExtract;
                    this.inRate -= addExtract;
                }
            }
            return addExtract;
        }

        @Override
        public void up() {
            super.up();
            inRate = 0;
        }
    }


}
