package com.til.dusk.common.register.mana_level.block.mechanic.handle_mechanic;

import com.til.dusk.common.capability.DuskCapabilityProvider;
import com.til.dusk.common.capability.black.Back;
import com.til.dusk.common.capability.black.IBack;
import com.til.dusk.common.capability.mana_handle.ManaHandle;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.config.ConfigField;
import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.mana_level.block.DefaultCapacityMechanic;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.other.CapabilityRegister;
import com.til.dusk.util.math.INumberPack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.event.AttachCapabilitiesEvent;

/**
 * @author til
 */
public class GatherManaMechanic extends DefaultCapacityMechanic {

    public GatherManaMechanic() {
        super("gather_mana");
    }

    @Override
    public void addCapability(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, ManaLevel manaLevel, IPosTrack iPosTrack) {
        super.addCapability(event, duskModCapability, manaLevel, iPosTrack);
        IBack iBack = duskModCapability.addCapability(CapabilityRegister.iBlack.capability, new Back());
        duskModCapability.addCapability(CapabilityRegister.iManaHandle.capability, new ManaHandle(
                manaBasics.ofValue(manaLevel.level),
                rateInBasics.ofValue(manaLevel.level),
                rateOutBasics.ofValue(manaLevel.level),
                iBack));
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "聚灵晶体");
        lang.add(LangType.EN_CH, "GatherMana Crystal");
    }


    @Override
    public void defaultConfig() {
        manaBasics = new INumberPack.ILongPack.LinearFunction(new INumberPack.ILongPack.Constant(5120000), new INumberPack.ILongPack.Constant(0));
        rateInBasics = new INumberPack.ILongPack.LinearFunction(new INumberPack.ILongPack.Constant(128), new INumberPack.ILongPack.Constant(0));
        rateOutBasics = new INumberPack.ILongPack.LinearFunction(new INumberPack.ILongPack.Constant(128), new INumberPack.ILongPack.Constant(0));
    }

    @ConfigField
    public INumberPack<Long> manaBasics;

    @ConfigField
    public INumberPack<Long> rateInBasics;
    @ConfigField
    public INumberPack<Long> rateOutBasics;

}
