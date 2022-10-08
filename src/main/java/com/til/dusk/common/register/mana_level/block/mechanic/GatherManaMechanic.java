package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.capability.DuskCapabilityProvider;
import com.til.dusk.common.capability.black.Back;
import com.til.dusk.common.capability.black.IBack;
import com.til.dusk.common.capability.mana_handle.ManaHandle;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.config.ConfigKey;
import com.til.dusk.common.config.ConfigMap;
import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.register.mana_level.block.DefaultCapacityMechanic;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.other.CapabilityRegister;
import com.til.dusk.util.nbt.cell.AllNBTCell;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.event.AttachCapabilitiesEvent;

public class GatherManaMechanic extends DefaultCapacityMechanic {

    public GatherManaMechanic() {
        super("gather_mana");
    }

    @Override
    public void addCapability(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, ManaLevel manaLevel, IPosTrack iPosTrack) {
        super.addCapability(event, duskModCapability, manaLevel, iPosTrack);
        IBack iBack = duskModCapability.addCapability(CapabilityRegister.iBlack.capability, new Back());
        duskModCapability.addCapability(CapabilityRegister.iManaHandle.capability, new ManaHandle(
                getConfig(MANA_BASICS) * manaLevel.getConfig(ManaLevel.level),
                getConfig(MANA_RATE_BASICS) * manaLevel.getConfig(ManaLevel.level), iBack));
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "聚灵晶体");
        lang.add(LangType.EN_CH, "GatherMana Crystal");
    }


    @Override
    public ConfigMap defaultConfigMap() {
        return new ConfigMap()
                .setConfigOfV(MANA_BASICS, 5120000L)
                .setConfigOfV(MANA_RATE_BASICS, 128L);
    }

    public static final ConfigKey<Long> MANA_BASICS = new ConfigKey<>("mana_level_block.gather_mana.mana_basics", AllNBTCell.LONG, null);
    public static final ConfigKey<Long> MANA_RATE_BASICS = new ConfigKey<>("mana_level_block.gather_mana.mana_rate_basics", AllNBTCell.LONG, null);
}
