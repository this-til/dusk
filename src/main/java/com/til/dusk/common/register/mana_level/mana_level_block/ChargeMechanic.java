package com.til.dusk.common.register.mana_level.mana_level_block;

import com.til.dusk.Dusk;
import com.til.dusk.common.capability.CapabilityHelp;
import com.til.dusk.common.capability.control.Control;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.mana_handle.IManaHandle;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.capability.tile_entity.DuskCapabilityProvider;
import com.til.dusk.common.capability.up.IUp;
import com.til.dusk.common.capability.up.Up;
import com.til.dusk.common.register.BindType;
import com.til.dusk.common.register.CapabilityRegister;
import com.til.dusk.common.register.mana_level.ManaLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author til
 */
public class ChargeMechanic extends DefaultCapacityMechanic {
    public ChargeMechanic(ResourceLocation name) {
        super(name);
    }

    public ChargeMechanic(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    @Override
    public void addCapability(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, ManaLevel manaLevel, IPosTrack iPosTrack) {
        super.addCapability(event, duskModCapability, manaLevel, iPosTrack);
        IControl iControl = duskModCapability.addCapability(CapabilityRegister.iControl.capability, new Control(iPosTrack, List.of(BindType.manaIn, BindType.posTrack), manaLevel));
        IUp iUp = duskModCapability.addCapability(CapabilityRegister.iUp.capability, new Up());
        iUp.addUpBlack(() -> {
            Level level = event.getObject().getLevel();
            if (level == null) {
                return;
            }
            Map<BlockEntity, IManaHandle> outManaMap = iControl.getCapability(BindType.manaOut);
            List<Player> itemEntityList = level.getEntities(EntityType.PLAYER, CapabilityHelp.getAABB(iControl, 16 + (4 * manaLevel.level)), Objects::nonNull);
            if (itemEntityList.isEmpty()) {
                return;
            }
            long canOutMana = 16L * manaLevel.level;
            for (Player player : itemEntityList) {
                LazyOptional<IManaHandle> lazyOptional = player.getCapability(CapabilityRegister.iManaHandle.capability);
                if (!lazyOptional.isPresent()) {
                    continue;
                }
                canOutMana -= CapabilityHelp.extractMana(iPosTrack, null, outManaMap, canOutMana, false);
                if (canOutMana <= 0) {
                    return;
                }
            }
        });
    }

}
