package com.til.dusk.common.register.mana_level.block;

import com.til.dusk.common.capability.CapabilityHelp;
import com.til.dusk.common.capability.DuskCapabilityProvider;
import com.til.dusk.common.capability.black.Back;
import com.til.dusk.common.capability.black.IBack;
import com.til.dusk.common.capability.clock.IClock;
import com.til.dusk.common.capability.clock.ManaClock;
import com.til.dusk.common.capability.control.Control;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.other.BindType;
import com.til.dusk.common.register.other.CapabilityRegister;
import com.til.dusk.common.register.shaped.ShapedDrive;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.AttachCapabilitiesEvent;

import java.util.List;

/**
 * @author til
 */
public class MassacreMechanic extends DefaultCapacityMechanic {

    public MassacreMechanic() {
        super("massacre");
    }

    @Override
    public void addCapability(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, ManaLevel manaLevel, IPosTrack iPosTrack) {
        super.addCapability(event, duskModCapability, manaLevel, iPosTrack);
        IControl iControl = duskModCapability.addCapability(CapabilityRegister.iControl.capability, new Control(iPosTrack, List.of(BindType.manaIn, BindType.modelStore, BindType.posTrack), manaLevel));
        IBack iBack = duskModCapability.addCapability(CapabilityRegister.iBlack.capability, new Back());
        IClock iClock = duskModCapability.addCapability(CapabilityRegister.iClock.capability, new ManaClock(iBack, manaLevel.clock / transmissionEfficiency, iControl, consume * manaLevel.level));
        iClock.addBlock(() -> {
            Level level = iPosTrack.getLevel();
            if (level == null) {
                return;
            }
            AABB aabb = CapabilityHelp.getAABB(iControl, iControl.getMaxRange());
            List<LivingEntity> mobList = level.getEntitiesOfClass(LivingEntity.class, aabb);
            if (mobList.isEmpty()) {
                return;
            }
            List<ShapedDrive> list = CapabilityHelp.getShapedDrive(iControl);
            boolean attackPlayer = list.contains(ShapedDrive.get(2));
            boolean attackAll = list.contains(ShapedDrive.get(1));
            boolean attackMod = list.contains(ShapedDrive.get(0));
            LivingEntity attackEntity = null;
            for (LivingEntity livingEntity : mobList) {
                if (!livingEntity.isAlive()) {
                    continue;
                }
                if (livingEntity instanceof Player) {
                    if (attackPlayer) {
                        attackEntity = livingEntity;
                        break;
                    }
                    continue;
                }
                if (attackAll) {
                    attackEntity = livingEntity;
                    break;
                }
                if (attackMod && livingEntity instanceof Enemy) {
                    attackEntity = livingEntity;
                    break;
                }
            }
            if (attackEntity != null) {
                attackEntity.invulnerableTime = 0;
                attackEntity.hurt(new DamageSource("regression").setMagic(), attackBasics + (attackUp * manaLevel.level));
            }
        });
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "屠杀晶体");
        lang.add(LangType.EN_CH, "Massacre Crystal");
    }

    @Override
    public void defaultConfig() {
        consume = 8L;
        transmissionEfficiency = 3;
        attackBasics = 8;
        attackUp = 2;
    }

    @ConfigField
    public int transmissionEfficiency;
    @ConfigField
    public float attackBasics;
    @ConfigField
    public float attackUp;
    @ConfigField
    public long consume;

}
