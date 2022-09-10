package com.til.dusk.common.register.mana_level.mana_level_block;

import com.til.dusk.Dusk;
import com.til.dusk.common.capability.CapabilityHelp;
import com.til.dusk.common.capability.clock.IClock;
import com.til.dusk.common.capability.clock.ManaClock;
import com.til.dusk.common.capability.control.Control;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.capability.tile_entity.DuskCapabilityProvider;
import com.til.dusk.common.capability.up.IUp;
import com.til.dusk.common.capability.up.Up;
import com.til.dusk.common.register.BindType;
import com.til.dusk.common.register.CapabilityRegister;
import com.til.dusk.common.register.mana_level.ManaLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.AttachCapabilitiesEvent;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.Predicate;

/**
 * @author til
 */
public class MassacreMechanic extends DefaultCapacityMechanic {
    public final Random random = new Random();

    public MassacreMechanic(ResourceLocation name) {
        super(name);
    }

    public MassacreMechanic(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    @Override
    public void addCapability(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, ManaLevel manaLevel, IPosTrack iPosTrack) {
        super.addCapability(event, duskModCapability, manaLevel, iPosTrack);
        IControl iControl = duskModCapability.addCapability(CapabilityRegister.iControl.capability, new Control(iPosTrack, List.of(BindType.manaIn, BindType.posTrack), manaLevel));
        IUp iUp = duskModCapability.addCapability(CapabilityRegister.iUp.capability, new Up());
        IClock iClock = duskModCapability.addCapability(CapabilityRegister.iClock.capability, new ManaClock(iUp, manaLevel.clock / 3, iControl, 8L * manaLevel.level));
        iClock.addBlock(() -> {
            Level level = iPosTrack.getLevel();
            if (level == null) {
                return;
            }
            AABB aabb = CapabilityHelp.getAABB(iControl, iControl.getMaxRange());
            List<Mob> mobList = level.getEntitiesOfClass(Mob.class, aabb);
            if (mobList.isEmpty()) {
                return;
            }
            Mob mob = mobList.get(random.nextInt(0, mobList.size()));
            mob.hurt(new DamageSource("regression").setMagic().bypassInvul(), 8 + (2 * manaLevel.level) );
        });
    }
}
