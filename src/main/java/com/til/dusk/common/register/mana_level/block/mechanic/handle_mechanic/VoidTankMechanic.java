package com.til.dusk.common.register.mana_level.block.mechanic.handle_mechanic;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.capability.CapabilityHelp;
import com.til.dusk.common.capability.DuskCapabilityProvider;
import com.til.dusk.common.capability.fluid_handler.VoidTankFluidHandler;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.register.mana_level.block.DefaultCapacityMechanic;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.other.CapabilityRegister;
import com.til.dusk.common.world.block.MechanicBlock;
import com.til.dusk.util.Extension;
import com.til.dusk.util.math.INumberPack;
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
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * @author til
 */
public class VoidTankMechanic extends DefaultCapacityMechanic {

    public VoidTankMechanic() {
        super("void_tank");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "虚空缸");
        lang.add(LangType.EN_CH, "Void Tank");
    }

    @Override
    public Block createBlock(ManaLevel manaLevel) {
        return new MechanicBlock(manaLevel) {
            @Override
            public void add(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, IPosTrack iPosTrack) {
                addCapability(event, duskModCapability, manaLevel, iPosTrack);
            }

            @Deprecated
            @Override
            public void attack(@NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, @NotNull Player player) {
                if (level.isClientSide) {
                    return;
                }
                BlockEntity blockEntity = level.getBlockEntity(blockPos);
                if (blockEntity == null) {
                    return;
                }
                Optional<IFluidHandler> blockLazyOptional = blockEntity.getCapability(ForgeCapabilities.FLUID_HANDLER).resolve();
                if (blockLazyOptional.isEmpty()) {
                    return;
                }
                ItemStack itemStack = player.getItemInHand(InteractionHand.MAIN_HAND);
                if (itemStack.isEmpty()) {
                    return;
                }
                Optional<IFluidHandler> itemLazyOptional = itemStack.getCapability(ForgeCapabilities.FLUID_HANDLER).resolve();
                if (itemLazyOptional.isEmpty()) {
                    return;
                }
                IFluidHandler blockFluidHandler = blockLazyOptional.get();
                IFluidHandler itemFluidHandler = itemLazyOptional.get();
                IPosTrack blockPosTrack = blockEntity.getCapability(CapabilityRegister.iPosTrack.capability, null).orElse(null);
                IPosTrack playerPosTrack = player.getCapability(CapabilityRegister.iPosTrack.capability, null).orElse(null);
                for (int i = 0; i < itemFluidHandler.getTanks(); i++) {
                    FluidStack fluidStack = itemFluidHandler.getFluidInTank(i);
                    FluidStack simulateOutFluid;
                    if (fluidStack.isEmpty()) {
                        simulateOutFluid = blockFluidHandler.drain(itemFluidHandler.getTankCapacity(i), IFluidHandler.FluidAction.SIMULATE);
                    } else {
                        simulateOutFluid = blockFluidHandler.drain(fluidStack, IFluidHandler.FluidAction.SIMULATE);
                    }
                    if (simulateOutFluid.isEmpty()) {
                        continue;
                    }
                    int simulateIn = itemFluidHandler.fill(simulateOutFluid, IFluidHandler.FluidAction.SIMULATE);
                    if (simulateIn <= 0) {
                        continue;
                    }
                    FluidStack outFluidStack = simulateOutFluid.copy();
                    outFluidStack.setAmount(simulateIn);
                    itemFluidHandler.fill(CapabilityHelp.drain(playerPosTrack, null, new Extension.VariableData_2<>(blockPosTrack, blockFluidHandler), outFluidStack, false),
                            IFluidHandler.FluidAction.EXECUTE);
                }
            }

            @Deprecated
            @Override
            public InteractionResult use(@NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, @NotNull Player player,
                                         @NotNull InteractionHand interactionHand, @NotNull BlockHitResult blockHitResult) {
                if (level.isClientSide) {
                    return InteractionResult.FAIL;
                }
                BlockEntity blockEntity = level.getBlockEntity(blockPos);
                if (blockEntity == null) {
                    return InteractionResult.FAIL;
                }
                Optional<IFluidHandler> blockLazyOptional = blockEntity.getCapability(ForgeCapabilities.FLUID_HANDLER).resolve();
                if (blockLazyOptional.isEmpty()) {
                    return InteractionResult.FAIL;
                }
                if (!interactionHand.equals(InteractionHand.MAIN_HAND)) {
                    return InteractionResult.FAIL;
                }
                ItemStack itemStack = player.getItemInHand(interactionHand);
                if (itemStack.isEmpty()) {
                    return InteractionResult.FAIL;
                }
                Optional<IFluidHandler> itemLazyOptional = itemStack.getCapability(ForgeCapabilities.FLUID_HANDLER).resolve();
                if (itemLazyOptional.isEmpty()) {
                    return InteractionResult.FAIL;
                }
                IFluidHandler blockFluidHandler = blockLazyOptional.get();
                IFluidHandler itemFluidHandler = itemLazyOptional.get();
                IPosTrack blockPosTrack = blockEntity.getCapability(CapabilityRegister.iPosTrack.capability, null).orElse(null);
                IPosTrack playerPosTrack = player.getCapability(CapabilityRegister.iPosTrack.capability, null).orElse(null);
                for (int i = 0; i < itemFluidHandler.getTanks(); i++) {
                    FluidStack fluidStack = itemFluidHandler.getFluidInTank(i);
                    if (fluidStack.isEmpty()) {
                        continue;
                    }
                    FluidStack simulateOut = itemFluidHandler.drain(fluidStack, IFluidHandler.FluidAction.SIMULATE);
                    if (simulateOut.isEmpty()) {
                        continue;
                    }
                    int simulateIn = blockFluidHandler.fill(simulateOut, IFluidHandler.FluidAction.SIMULATE);
                    if (simulateIn <= 0) {
                        continue;
                    }
                    FluidStack inFluidStack = simulateOut.copy();
                    inFluidStack.setAmount(simulateIn);
                    CapabilityHelp.fill(playerPosTrack, null, new Extension.VariableData_2<>(blockPosTrack, blockFluidHandler),
                            itemFluidHandler.drain(inFluidStack, IFluidHandler.FluidAction.EXECUTE), false);
                }
                return InteractionResult.SUCCESS;
            }

        };
    }

    @Override
    public void addCapability(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, ManaLevel manaLevel, IPosTrack iPosTrack) {
        super.addCapability(event, duskModCapability, manaLevel, iPosTrack);
        duskModCapability.addCapability(ForgeCapabilities.FLUID_HANDLER, new VoidTankFluidHandler((int) loadBasics.ofValue(manaLevel.level)));
    }

    @Override
    public void defaultConfig() {
        loadBasics = new INumberPack.IIntPack.LinearFunction(new INumberPack.IIntPack.Constant(12800000), new INumberPack.IIntPack.Constant(0));
    }

    @ConfigField
    public INumberPack<Integer> loadBasics;
}
