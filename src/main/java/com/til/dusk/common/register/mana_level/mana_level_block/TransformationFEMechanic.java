package com.til.dusk.common.register.mana_level.mana_level_block;


import com.til.dusk.common.capability.DuskCapabilityProvider;
import com.til.dusk.common.capability.black.Back;
import com.til.dusk.common.capability.black.IBack;
import com.til.dusk.common.capability.energy_storage.DuskEnergyStorage;
import com.til.dusk.common.capability.mana_handle.EventManaHandle;
import com.til.dusk.common.capability.mana_handle.ManaHandle;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.register.CapabilityRegister;
import com.til.dusk.common.register.mana_level.ManaLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;

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
        TransformationFEManaHandle iManaHandle = (TransformationFEManaHandle) duskModCapability.addCapability(CapabilityRegister.iManaHandle.capability, new TransformationFEManaHandle(256000L * manaLevel.level, 32L * manaLevel.level, back));
        TransformationFEEnergyStorage energyStorage = (TransformationFEEnergyStorage) duskModCapability.addCapability(CapabilityRegister.iEnergyStorage.capability, new TransformationFEEnergyStorage(2560000 * manaLevel.level, (int) (iManaHandle.getMaxRate() * 8), back));
        back.add(IBack.UP, v -> {
            if (iManaHandle.getMana() <= 0) {
                return;
            }
            if (energyStorage.getEnergyStored() >= energyStorage.getMaxEnergyStored()) {
                return;
            }
            energyStorage.receiveEnergyInside((int) (iManaHandle.extractManaInside(iManaHandle.getMaxRate(), false) * 8), false);
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
