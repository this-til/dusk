package com.til.dusk.common.register.mana_level.block;

import com.til.dusk.common.capability.CapabilityHelp;
import com.til.dusk.common.capability.black.Back;
import com.til.dusk.common.capability.black.IBack;
import com.til.dusk.common.capability.clock.IClock;
import com.til.dusk.common.capability.clock.ManaClock;
import com.til.dusk.common.capability.control.Control;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.capability.DuskCapabilityProvider;
import com.til.dusk.common.event.EventIO;
import com.til.dusk.common.register.other.BindType;
import com.til.dusk.common.register.other.CapabilityRegister;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.util.Pos;
import com.til.dusk.util.RoutePack;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/***
 * 收集
 * @author til
 */
public class CollectMechanic extends DefaultCapacityMechanic {

    public CollectMechanic() {
        super("collect");
    }

    @Override
    public void addCapability(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, ManaLevel manaLevel, IPosTrack iPosTrack) {
        super.addCapability(event, duskModCapability, manaLevel, iPosTrack);
        IControl iControl = duskModCapability.addCapability(CapabilityRegister.iControl.capability, new Control(iPosTrack, List.of(BindType.manaIn, BindType.itemOut, BindType.posTrack), manaLevel));
        IBack iBack = duskModCapability.addCapability(CapabilityRegister.iBlack.capability, new Back());
        IClock iClock = duskModCapability.addCapability(CapabilityRegister.iClock.capability, new ManaClock(iBack, manaLevel.clock / 5, iControl, 12L * manaLevel.level));
        iClock.addBlock(() -> {
            Level level = event.getObject().getLevel();
            if (level == null) {
                return;
            }
            List<ItemEntity> itemEntityList = level.getEntities(EntityType.ITEM, CapabilityHelp.getAABB(iControl, iControl.getMaxRange()), Objects::nonNull);
            if (itemEntityList.isEmpty()) {
                return;
            }
            Map<IPosTrack, IItemHandler> outItem = iControl.getCapability(BindType.itemOut);
            if (outItem.isEmpty()) {
                return;
            }
            for (ItemEntity itemEntity : itemEntityList) {
                ItemStack itemStackSimulate = itemEntity.getItem().copy();
                for (IItemHandler value : outItem.values()) {
                    itemStackSimulate = ItemHandlerHelper.insertItemStacked(value, itemStackSimulate, true);
                    if (itemStackSimulate.isEmpty()) {
                        break;
                    }
                }
                if (!itemStackSimulate.isEmpty()) {
                    continue;
                }
                ItemStack itemStack = itemEntity.getItem().copy();
                RoutePack<ItemStack> routePack = new RoutePack<>();
                routePack.getUp().add(new RoutePack.RouteCell<>(new Pos(itemEntity), iPosTrack.getPos(), itemStack));
                CapabilityHelp.insertItem(iPosTrack, routePack, outItem, itemStack, false);
                itemEntity.kill();
                MinecraftForge.EVENT_BUS.post(new EventIO.Item(level, routePack));
            }
        });
    }
}
