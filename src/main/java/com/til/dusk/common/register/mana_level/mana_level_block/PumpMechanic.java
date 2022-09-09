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
import com.til.dusk.common.event.EventIO;
import com.til.dusk.common.register.BindType;
import com.til.dusk.common.register.CapabilityRegister;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.util.Pos;
import com.til.dusk.util.RoutePack;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PumpMechanic extends DefaultCapacityMechanic {

    public PumpMechanic(ResourceLocation name) {
        super(name);
    }

    public PumpMechanic(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    @Override
    public void addCapability(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, ManaLevel manaLevel, IPosTrack iPosTrack) {
        super.addCapability(event, duskModCapability, manaLevel, iPosTrack);
        IUp iUp = duskModCapability.addCapability(CapabilityRegister.iUp.capability, new Up());
        IControl iControl = duskModCapability.addCapability(CapabilityRegister.iControl.capability, new Control(iPosTrack, List.of(BindType.manaIn, BindType.fluidOut, BindType.posTrack), manaLevel));
        IClock iClock = duskModCapability.addCapability(CapabilityRegister.iClock.capability, new ManaClock(iUp, manaLevel.clock, iControl, 8L * manaLevel.level));
        iClock.addBlock(() -> {
            Level level = iPosTrack.getLevel();
            if (level == null) {
                return;
            }
            Map<BlockEntity, IFluidHandler> outFluid = iControl.getCapability(BindType.fluidOut);
            if (outFluid.isEmpty()) {
                return;
            }
            AABB aabb = CapabilityHelp.getAABB(iControl, iControl.getMaxRange());
            for (double ix = aabb.minX; ix < aabb.maxX; ix++) {
                for (double iy = aabb.minY; iy < aabb.maxY; iy++) {
                    for (double iz = aabb.minZ; iz < aabb.maxZ; iz++) {
                        BlockPos blockPos = new BlockPos(ix, iy, iz);
                        BlockState blockState = level.getBlockState(blockPos);
                        if (blockState.isAir()) {
                            continue;
                        }
                        Block block = blockState.getBlock();
                        if (block instanceof LiquidBlock liquidBlock) {
                            FluidState fluidState = liquidBlock.getFluidState(blockState);
                            if (fluidState.isEmpty() || !fluidState.isSource()) {
                                continue;
                            }
                            Fluid fluid = fluidState.getType();
                            FluidStack fluidStack = new FluidStack(fluid, fluid.getFluidType().getDensity());
                            if (fluidStack.isEmpty()) {
                                return;
                            }
                            if (CapabilityHelp.fill(iPosTrack, null, outFluid, fluidStack, true) >= fluidStack.getAmount()) {
                                RoutePack<FluidStack> routePack = new RoutePack<>();
                                CapabilityHelp.fill(iPosTrack, routePack, outFluid, fluidStack, false);
                                level.setBlock(blockPos, Blocks.AIR.defaultBlockState(), 3);
                                routePack.getUp().add(new RoutePack.RouteCell<>(new Pos(blockPos), iPosTrack.getPos(), fluidStack));
                                MinecraftForge.EVENT_BUS.post(new EventIO.Fluid(iPosTrack.getLevel(), routePack));
                                return;
                            }
                        }
                    }
                }
            }
        });
    }
}
