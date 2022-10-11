package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.capability.CapabilityHelp;
import com.til.dusk.common.capability.DuskCapabilityProvider;
import com.til.dusk.common.capability.item_handler.VoidCaseItemHandler;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.register.mana_level.block.DefaultCapacityMechanic;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.other.CapabilityRegister;
import com.til.dusk.common.world.block.MechanicBlock;
import com.til.dusk.util.Extension;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author til
 */
public class VoidCaseMechanic extends DefaultCapacityMechanic {
    public VoidCaseMechanic() {
        super("void_case");
    }

    @Override
    public Block createBlock(ManaLevel manaLevel) {
        return new MechanicBlock(manaLevel) {
            @Override
            public void add(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, IPosTrack iPosTrack) {
                addCapability(event, duskModCapability, manaLevel, iPosTrack);
            }

            @Override
            @Deprecated
            public void attack(@NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, @NotNull Player player) {
                if (level.isClientSide) {
                    return;
                }
                BlockEntity blockEntity = level.getBlockEntity(blockPos);
                if (blockEntity == null) {
                    return;
                }
                Optional<IItemHandler> lazyOptional = blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).resolve();
                if (lazyOptional.isEmpty()) {
                    return;
                }
                Optional<IItemHandler> playerLazyOptional = player.getCapability(ForgeCapabilities.ITEM_HANDLER).resolve();
                if (playerLazyOptional.isEmpty()) {
                    return;
                }
                IItemHandler iItemHandler = lazyOptional.get();
                IItemHandler playerItemHandler = playerLazyOptional.get();
                IPosTrack blockPosTrack = blockEntity.getCapability(CapabilityRegister.iPosTrack.capability, null).orElse(null);
                IPosTrack playerPosTrack = player.getCapability(CapabilityRegister.iPosTrack.capability, null).orElse(null);
                Extension.Data_2<Integer, ItemStack> data = null;
                for (int i = 0; i < iItemHandler.getSlots(); i++) {
                    ItemStack itemStack = iItemHandler.getStackInSlot(i);
                    if (itemStack.isEmpty()) {
                        continue;
                    }
                    ItemStack outItem = iItemHandler.extractItem(i, Math.min(player.isShiftKeyDown() ? 64 : 1, itemStack.getCount()), true);
                    ItemStack surplusStack = ItemHandlerHelper.insertItemStacked(playerItemHandler, outItem, true);
                    ItemStack determineItemStack = outItem.copy();
                    determineItemStack.setCount(determineItemStack.getCount() - surplusStack.getCount());
                    if (determineItemStack.isEmpty()) {
                        continue;
                    }
                    data = new Extension.Data_2<>(i, determineItemStack);
                    break;
                }
                if (data == null) {
                    return;
                }
                ItemStack itemStack = CapabilityHelp.extractItem(playerPosTrack, null, new Extension.VariableData_2<>(blockPosTrack, iItemHandler), data.d1(), data.d2().getCount(), false);
                ItemHandlerHelper.insertItemStacked(playerItemHandler, itemStack, false);
            }

            @Override
            @Deprecated
            public InteractionResult use(@NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, @NotNull Player player,
                                         @NotNull InteractionHand interactionHand, @NotNull BlockHitResult blockHitResult) {
                if (level.isClientSide) {
                    return InteractionResult.FAIL;
                }
                if (interactionHand.equals(InteractionHand.OFF_HAND)) {
                    return InteractionResult.FAIL;
                }
                BlockEntity blockEntity = level.getBlockEntity(blockPos);
                if (blockEntity == null) {
                    return InteractionResult.FAIL;
                }
                Optional<IItemHandler> lazyOptional = blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).resolve();
                if (lazyOptional.isEmpty()) {
                    return InteractionResult.FAIL;
                }
                Optional<IItemHandler> playerLazyOptional = player.getCapability(ForgeCapabilities.ITEM_HANDLER).resolve();
                if (playerLazyOptional.isEmpty()) {
                    return InteractionResult.FAIL;
                }
                IItemHandler iItemHandler = lazyOptional.get();
                IItemHandler playerItemHandler = playerLazyOptional.get();
                IPosTrack blockPosTrack = blockEntity.getCapability(CapabilityRegister.iPosTrack.capability, null).orElse(null);
                IPosTrack playerPosTrack = player.getCapability(CapabilityRegister.iPosTrack.capability, null).orElse(null);
                ItemStack handlerItemStack = player.getItemInHand(interactionHand);
                if (handlerItemStack.isEmpty()) {
                    for (int i = 0; i < iItemHandler.getSlots(); i++) {
                        ItemStack itemStack = iItemHandler.getStackInSlot(i);
                        if (itemStack.isEmpty()) {
                            continue;
                        }
                        int playerAmount = 0;
                        List<Integer> playerSlotList = null;
                        for (int i1 = 0; i1 < playerItemHandler.getSlots(); i1++) {
                            ItemStack playerItemStack = playerItemHandler.getStackInSlot(i1);
                            if (ItemHandlerHelper.canItemStacksStack(playerItemStack, itemStack)) {
                                ItemStack outItem = playerItemHandler.extractItem(i1, playerItemStack.getCount(), true);
                                if (playerSlotList == null) {
                                    playerSlotList = new ArrayList<>();
                                }
                                playerSlotList.add(i1);
                                playerAmount += outItem.getCount();
                            }
                        }

                        if (playerAmount <= 0) {
                            continue;
                        }
                        ItemStack simulateInItemStack = itemStack.copy();
                        simulateInItemStack.setCount(playerAmount);
                        ItemStack surplusItemStack = ItemHandlerHelper.insertItemStacked(iItemHandler, simulateInItemStack, true);
                        simulateInItemStack.setCount(simulateInItemStack.getCount() - surplusItemStack.getCount());
                        if (simulateInItemStack.isEmpty()) {
                            continue;
                        }
                        int inAmount = simulateInItemStack.getCount();
                        ItemStack inItemStack = itemStack.copy();
                        inItemStack.setCount(0);
                        for (int playerId : playerSlotList) {
                            ItemStack outItem = playerItemHandler.extractItem(playerId, inAmount, false);
                            inItemStack.setCount(inItemStack.getCount() + outItem.getCount());
                            inAmount -= outItem.getCount();
                            if (inAmount <= 0) {
                                break;
                            }
                        }
                        if (inItemStack.isEmpty()) {
                            break;
                        }
                        CapabilityHelp.insertItem(playerPosTrack, null, new Extension.VariableData_2<>(blockPosTrack, iItemHandler), inItemStack, false);
                    }
                } else {
                    ItemStack outItemStack = CapabilityHelp.insertItem(playerPosTrack, null, new Extension.VariableData_2<>(blockPosTrack, iItemHandler), handlerItemStack, false);
                    player.setItemInHand(InteractionHand.MAIN_HAND, outItemStack.isEmpty() ? ItemStack.EMPTY : outItemStack);
                }
                return InteractionResult.SUCCESS;
            }


        };
    }

    @Override
    public void addCapability(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, ManaLevel manaLevel, IPosTrack iPosTrack) {
        super.addCapability(event, duskModCapability, manaLevel, iPosTrack);
        duskModCapability.addCapability(ForgeCapabilities.ITEM_HANDLER, new VoidCaseItemHandler(loadBasics * manaLevel.level));
    }

    @Override
    public void defaultConfig() {
        loadBasics = 4096L;
    }

    @ConfigField
    public long loadBasics;
}
