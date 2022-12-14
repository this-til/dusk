package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.capability.CapabilityHelp;
import com.til.dusk.common.capability.DuskCapabilityProvider;
import com.til.dusk.common.capability.black.Back;
import com.til.dusk.common.capability.black.IBack;
import com.til.dusk.common.capability.control.Control;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.mana_handle.IManaHandle;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.config.ConfigField;
import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.mana_level.block.DefaultCapacityMechanic;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.bind_type.BindType;
import com.til.dusk.common.register.other.CapabilityRegister;
import com.til.dusk.util.math.INumberPack;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author til
 */
public class ChargeMechanic extends DefaultCapacityMechanic {
    public ChargeMechanic() {
        super("charge");
    }

    @Override
    public void addCapability(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, ManaLevel manaLevel, IPosTrack iPosTrack) {
        super.addCapability(event, duskModCapability, manaLevel, iPosTrack);
        IControl iControl = duskModCapability.addCapability(CapabilityRegister.iControl.capability, new Control(iPosTrack, List.of(BindType.manaIn, BindType.posTrack), manaLevel));
        IBack iBack = duskModCapability.addCapability(CapabilityRegister.iBlack.capability, new Back());
        iBack.add(IBack.UP, v -> {
            Level level = event.getObject().getLevel();
            if (level == null) {
                return;
            }
            Map<IPosTrack, IManaHandle> inManaMap = iControl.getCapability(BindType.manaIn);
            if (inManaMap.isEmpty()) {
                return;
            }
            List<Player> itemEntityList = level.getEntities(EntityType.PLAYER, CapabilityHelp.getAABB(iControl, iControl.getMaxRange()), Objects::nonNull);
            if (itemEntityList.isEmpty()) {
                return;
            }
            Map<IPosTrack, IManaHandle> outManaMap = new HashMap<>(itemEntityList.size());
            for (Player player : itemEntityList) {
                LazyOptional<IManaHandle> lazyOptional = player.getCapability(CapabilityRegister.iManaHandle.capability);
                if (!lazyOptional.isPresent()) {
                    continue;
                }
                IManaHandle manaHandle = lazyOptional.orElse(null);
                outManaMap.put(player.getCapability(CapabilityRegister.iPosTrack.capability).orElse(null), manaHandle);
            }
            CapabilityHelp.manaPointToPointTransmit(iPosTrack, inManaMap, outManaMap, power.ofValue((long) manaLevel.level), 0, false);
        });
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "????????????");
        lang.add(LangType.EN_CH, "Charge Crystal");
    }

    @Override
    public void defaultConfig() {
        power = new INumberPack.ILongPack.LinearFunction(new INumberPack.ILongPack.Constant(12), new INumberPack.ILongPack.Constant(0));
    }

    @ConfigField
    public INumberPack<Long> power;

}
