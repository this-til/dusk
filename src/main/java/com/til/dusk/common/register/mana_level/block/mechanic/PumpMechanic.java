package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.capability.CapabilityHelp;
import com.til.dusk.common.capability.DuskCapabilityProvider;
import com.til.dusk.common.capability.black.Back;
import com.til.dusk.common.capability.black.IBack;
import com.til.dusk.common.capability.block_scan.BlockScan;
import com.til.dusk.common.capability.block_scan.IBlockScan;
import com.til.dusk.common.capability.clock.IClock;
import com.til.dusk.common.capability.clock.ManaClock;
import com.til.dusk.common.capability.control.Control;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.config.ConfigField;
import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.event.EventIO;
import com.til.dusk.common.register.mana_level.block.DefaultCapacityMechanic;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.bind_type.BindType;
import com.til.dusk.common.register.other.CapabilityRegister;
import com.til.dusk.util.Pos;
import com.til.dusk.util.RoutePack;
import com.til.dusk.util.math.INumberPack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

import java.util.List;
import java.util.Map;

public class PumpMechanic extends DefaultCapacityMechanic {

    public PumpMechanic() {
        super("pump_mechanic");
    }


    @Override
    public void addCapability(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, ManaLevel manaLevel, IPosTrack iPosTrack) {
        super.addCapability(event, duskModCapability, manaLevel, iPosTrack);
        IControl control = duskModCapability.addCapability(CapabilityRegister.iControl.capability, new Control(iPosTrack, List.of(BindType.manaIn, BindType.itemOut, BindType.posTrack, BindType.modelStore), manaLevel));
        IBack iBack = duskModCapability.addCapability(CapabilityRegister.iBlack.capability, new Back());
        IClock clock = duskModCapability.addCapability(CapabilityRegister.iClock.capability, new ManaClock(iBack, manaLevel.clock / transmissionEfficiency.ofValue(manaLevel.level), control, consume.ofValue((long) manaLevel.level)));
        IBlockScan iBlockScan = duskModCapability.addCapability(CapabilityRegister.iBlockScan.capability, new BlockScan(clock, iPosTrack, control) {
            public void run() {
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
            }
        });
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "泵晶体");
        lang.add(LangType.EN_CH, "Pump Mechanic Crystal");
    }

    @Override
    public void defaultConfig() {
        consume = new INumberPack.ILongPack.LinearFunction(new INumberPack.ILongPack.Constant(5), new INumberPack.ILongPack.Constant(0));
        transmissionEfficiency = new INumberPack.IIntPack.Constant(5);
    }

    @ConfigField
    public INumberPack<Integer> transmissionEfficiency;
    @ConfigField
    public INumberPack<Long> consume;
}
