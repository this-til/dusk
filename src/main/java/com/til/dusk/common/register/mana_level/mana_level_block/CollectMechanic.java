package com.til.dusk.common.register.mana_level.mana_level_block;

import com.til.dusk.common.capability.clock.IClock;
import com.til.dusk.common.capability.clock.ManaClock;
import com.til.dusk.common.capability.control.Control;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.tile_entity.DuskCapabilityProvider;
import com.til.dusk.common.capability.up.IUp;
import com.til.dusk.common.capability.up.Up;
import com.til.dusk.common.event.EventIO;
import com.til.dusk.common.register.BindType;
import com.til.dusk.common.register.CapabilityRegister;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.util.Pos;
import net.minecraft.resources.ResourceLocation;
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

    public CollectMechanic(String name) {
        super(name);
    }

    public CollectMechanic(ResourceLocation name) {
        super(name);
    }

    @Override
    public void addCapability(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, ManaLevel manaLevel) {
        super.addCapability(event, duskModCapability, manaLevel);
        IControl iControl = duskModCapability.addCapability(CapabilityRegister.iControl.capability, new Control(event.getObject(), List.of(BindType.manaIn, BindType.itemOut), manaLevel));
        IUp iUp = duskModCapability.addCapability(CapabilityRegister.iUp.capability, new Up());
        IClock iClock = duskModCapability.addCapability(CapabilityRegister.iClock.capability, new ManaClock(iUp, manaLevel.clock / 5, event.getObject(), iControl, 12L * manaLevel.level));
        iClock.addBlock(() -> {
            Level level = event.getObject().getLevel();
            if (level == null) {
                return;
            }
            List<ItemEntity> itemEntityList = level.getEntities(EntityType.ITEM, new Pos(event.getObject().getBlockPos()).axisAlignedBB(16 + (4 * manaLevel.level)), Objects::nonNull);
            if (itemEntityList.isEmpty()) {
                return;
            }
            Map<BlockEntity, IItemHandler> outItem = iControl.getCapability(BindType.itemOut);
            if (outItem.isEmpty()) {
                return;
            }
            int numbe = 4 + manaLevel.level;
            for (ItemEntity itemEntity : itemEntityList) {

                ItemStack itemStackSimulate = itemEntity.getItem().copy();
                for (IItemHandler value : outItem.values()) {
                    itemStackSimulate = ItemHandlerHelper.insertItemStacked(value, itemStackSimulate, true);
                    if (itemStackSimulate.isEmpty()) {
                        break;
                    }
                }
                if (!itemStackSimulate.isEmpty()) {
                    return;
                }
                ItemStack itemStack = itemEntity.getItem().copy();
                MinecraftForge.EVENT_BUS.post(new EventIO.Item(event.getObject().getLevel(), new Pos(itemEntity),
                        new Pos(event.getObject().getBlockPos()), itemEntity.getItem()));
                for (Map.Entry<BlockEntity, IItemHandler> entry : outItem.entrySet()) {
                    ItemStack out = ItemHandlerHelper.insertItemStacked(entry.getValue(), itemStack, false);
                    if (out.getCount() < itemStack.getCount()) {
                        MinecraftForge.EVENT_BUS.post(new EventIO.Item(event.getObject().getLevel(),
                                new Pos(event.getObject().getBlockPos()), new Pos(entry.getKey().getBlockPos()),
                                new ItemStack(itemStack.getItem(), itemStack.getCount() - out.getCount())));
                    }
                    itemStack = out;
                    if (itemStack.isEmpty()) {
                        itemEntity.kill();
                        break;
                    }
                }
                numbe--;
                if (numbe <= 0) {
                    return;
                }
            }
        });
    }
}
