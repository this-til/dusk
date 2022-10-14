package com.til.dusk.common.register.mana_level.block.mechanic;


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
import com.til.dusk.common.config.ConfigField;
import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.mana_level.block.DefaultCapacityMechanic;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.other.BindType;
import com.til.dusk.common.register.other.CapabilityRegister;
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
        TransformationFEEnergyStorage energyStorage = (TransformationFEEnergyStorage) duskModCapability.addCapability(CapabilityRegister.iEnergyStorage.capability, new TransformationFEEnergyStorage(loadBasics * manaLevel.level, manaLevel.level * power, back));
        back.add(IBack.UP, v -> {
            if (energyStorage.getEnergyStored() >= energyStorage.getMaxEnergyStored()) {
                return;
            }
            Map<IPosTrack, IManaHandle> inMana = iControl.getCapability(BindType.manaIn);
            if (inMana.isEmpty()) {
                return;
            }
            energyStorage.receiveEnergyInside((int) (CapabilityHelp.extractMana(iPosTrack, null, inMana, (long) manaLevel.level * power, false) * transmissionProportion), false);
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

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "灵转FE晶体");
        lang.add(LangType.EN_CH, "Transformation FE Crystal");
    }

    @Override
    public void defaultConfig() {
        transmissionProportion = 8;
        power = 64;
        loadBasics = 25600000;
    }

    /***
     * 一灵气转FE的量
     */
    @ConfigField
    public double transmissionProportion;
    @ConfigField
    public int power;
    @ConfigField
    public int loadBasics;
}