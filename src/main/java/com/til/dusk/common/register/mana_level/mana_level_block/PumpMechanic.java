package com.til.dusk.common.register.mana_level.mana_level_block;

import com.til.dusk.common.capability.CapabilityHelp;
import com.til.dusk.common.capability.black.IBack;
import com.til.dusk.common.capability.clock.IClock;
import com.til.dusk.common.capability.clock.ManaClock;
import com.til.dusk.common.capability.control.Control;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.event.EventIO;
import com.til.dusk.common.register.BindType;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.util.Extension;
import com.til.dusk.util.Pos;
import com.til.dusk.util.RoutePack;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

import java.util.List;
import java.util.Map;

public class PumpMechanic extends PosImplementMechanic {

    public PumpMechanic() {
        super("pump_mechanic");
    }

    @Override
    public IControl createControl(ManaLevel manaLevel, IPosTrack iPosTrack) {
        return new Control(iPosTrack, List.of(BindType.manaIn, BindType.fluidOut, BindType.posTrack), manaLevel);
    }

    @Override
    public IClock createClock(ManaLevel manaLevel, IBack iBack, IControl iControl, IPosTrack iPosTrack) {
        return new ManaClock(iBack, manaLevel.clock / 5, iControl, 8L * manaLevel.level);
    }

    @Override
    public Extension.Action_3V<BlockPos, IControl, IPosTrack> createBlockBlack() {
        return (blockPos, iControl, iPosTrack) -> {
            Level level = iPosTrack.getLevel();
            Map<IPosTrack, IFluidHandler> outFluid = iControl.getCapability(BindType.fluidOut);
            if (outFluid.isEmpty()) {
                return;
            }
            BlockState blockState = level.getBlockState(blockPos);
            if (blockState.isAir()) {
                return;
            }
            boolean isWaterlogged = blockState.hasProperty(BlockStateProperties.WATERLOGGED);
            if (isWaterlogged && !blockState.getValue(BlockStateProperties.WATERLOGGED)) {
                return;
            }
            FluidState fluidState = level.getFluidState(blockPos);
            if (fluidState.isEmpty() || !fluidState.isSource()) {
                return;
            }
            Fluid fluid = fluidState.getType();
            FluidStack fluidStack = new FluidStack(fluid, fluid.getFluidType().getDensity());
            if (fluidStack.isEmpty()) {
                return;
            }
            if (CapabilityHelp.fill(iPosTrack, null, outFluid, fluidStack, true) < fluidStack.getAmount()) {
                return;
            }
            RoutePack<FluidStack> routePack = new RoutePack<>();
            CapabilityHelp.fill(iPosTrack, routePack, outFluid, fluidStack, false);
            level.setBlock(blockPos, isWaterlogged ? blockState.setValue(BlockStateProperties.WATERLOGGED, false) : Blocks.AIR.defaultBlockState(), 3);
            routePack.getUp().add(new RoutePack.RouteCell<>(new Pos(blockPos), iPosTrack.getPos(), fluidStack));
            MinecraftForge.EVENT_BUS.post(new EventIO.Fluid(iPosTrack.getLevel(), routePack));
        };
    }
}
