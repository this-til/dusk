package com.til.dusk.common.register.mana_level.mana_level_block;

import com.til.dusk.Dusk;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.capability.CapabilityHelp;
import com.til.dusk.common.capability.black.Back;
import com.til.dusk.common.capability.black.IBack;
import com.til.dusk.common.capability.clock.IClock;
import com.til.dusk.common.capability.clock.ManaClock;
import com.til.dusk.common.capability.control.Control;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.mana_handle.IManaHandle;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.capability.DuskCapabilityProvider;
import com.til.dusk.common.register.BindType;
import com.til.dusk.common.register.CapabilityRegister;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.util.Extension;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author til
 */
public class IOMechanic extends DefaultCapacityMechanic {
    public static final ResourceLocation MODEL_NAME = new ResourceLocation(Dusk.MOD_ID, "io");
    public final Color color;

    public IOMechanic(ResourceLocation name, Color color) {
        super(name);
        this.color = color;
    }

    public IOMechanic(String name, Color color) {
        this(new ResourceLocation(Dusk.MOD_ID, name), color);
    }

    @Override
    public ResourceLocation getBlockModelMapping(ManaLevel manaLevel) {
        return MODEL_NAME;
    }

    @Override
    public void dyeBlack(ManaLevel manaLevel, ColorProxy.ItemColorPack itemColorPack) {
        super.dyeBlack(manaLevel, itemColorPack);
        itemColorPack.addColor(1, itemStack -> color);
    }

    @Override
    public void dyeBlack(ManaLevel manaLevel, ColorProxy.BlockColorPack blockColorPack) {
        super.dyeBlack(manaLevel, blockColorPack);
        blockColorPack.addColor(1, (blockState, blockAndTintGetter, blockPos) -> color);
    }

    public static class ManaIO extends IOMechanic {

        public ManaIO(ResourceLocation name, Color color) {
            super(name, color);
        }

        public ManaIO(String name, Color color) {
            super(name, color);
        }

        @Override
        public void addCapability(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, ManaLevel manaLevel, IPosTrack iPosTrack) {
            super.addCapability(event, duskModCapability, manaLevel, iPosTrack);
            IControl iControl = duskModCapability.addCapability(CapabilityRegister.iControl.capability, new Control(iPosTrack, List.of(BindType.manaIn, BindType.manaOut), manaLevel));
            IBack iUp = duskModCapability.addCapability(CapabilityRegister.iBlack.capability, new Back());
            iUp.add(IBack.UP, v -> {
                Level level = event.getObject().getLevel();
                if (level == null) {
                    return;
                }
                CapabilityHelp.manaPointToPointTransmit(iControl.getPosTrack(), iControl.getCapability(BindType.manaIn), iControl.getCapability(BindType.manaOut), 1024L * manaLevel.level,manaLevel.manaLoss, false);
            });
        }


    }

    public static class ItemIO extends IOMechanic {
        public ItemIO(ResourceLocation name, Color color) {
            super(name, color);
        }

        public ItemIO(String name, Color color) {
            super(name, color);
        }

        @Override
        public void addCapability(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, ManaLevel manaLevel, IPosTrack iPosTrack) {
            super.addCapability(event, duskModCapability, manaLevel, iPosTrack);
            IControl iControl = duskModCapability.addCapability(CapabilityRegister.iControl.capability, new Control(iPosTrack, List.of(BindType.itemIn, BindType.itemOut, BindType.manaIn), manaLevel));
            IBack iUp = duskModCapability.addCapability(CapabilityRegister.iBlack.capability, new Back());
            IClock iClock = duskModCapability.addCapability(CapabilityRegister.iClock.capability, new ManaClock(iUp, manaLevel.clock / 10, iControl, 4L * manaLevel.level));
            iClock.addBlock(() -> {
                Level level = event.getObject().getLevel();
                if (level == null) {
                    return;
                }
                Map<IPosTrack, IItemHandler> inMap = iControl.getCapability(BindType.itemIn);
                if (inMap.isEmpty()) {
                    return;
                }
                Map<IPosTrack, IManaHandle> inMana = iControl.getCapability(BindType.manaIn);
                if (inMana.isEmpty()) {
                    return;
                }
                Map<IPosTrack, IItemHandler> _outMap = iControl.getCapability(BindType.itemOut);
                if (_outMap.isEmpty()) {
                    return;
                }
                Map<IPosTrack, IItemHandler> outMap = new HashMap<>();
                for (Map.Entry<IPosTrack, IItemHandler> entry : _outMap.entrySet()) {
                    if (!inMap.containsKey(entry.getKey())) {
                        outMap.put(entry.getKey(), entry.getValue());
                    }
                }
                ItemStack outSimulate = null;
                Extension.Data_4<IPosTrack, IItemHandler, ItemStack, Integer> outData = null;
                for (Map.Entry<IPosTrack, IItemHandler> entry : inMap.entrySet()) {
                    for (int i = 0; i < entry.getValue().getSlots(); i++) {
                        ItemStack itemStack = entry.getValue().getStackInSlot(i);
                        if (!itemStack.isEmpty()) {
                            ItemStack extractItemStack = entry.getValue().extractItem(i, Math.min(itemStack.getMaxStackSize(), 64), true);
                            if (!extractItemStack.isEmpty()) {
                                outSimulate = extractItemStack;
                                outData = new Extension.Data_4<>(entry.getKey(), entry.getValue(), extractItemStack, i);
                                break;
                            }
                        }
                    }
                    if (outSimulate != null) {
                        break;
                    }
                }
                if (outSimulate == null) {
                    return;
                }
                for (IItemHandler value : outMap.values()) {
                    outSimulate = ItemHandlerHelper.insertItemStacked(value, outSimulate, true);
                    if (outSimulate.isEmpty()) {
                        break;
                    }
                }
                if (!outSimulate.isEmpty()) {
                    return;
                }
                CapabilityHelp.extractAndInsertItem(iPosTrack, outData.d2(), outData.d1().getPos(), outData.d4(), outData.d3().getCount(), outMap, false);
            });
        }
    }

    public static class FluidIO extends IOMechanic {
        public FluidIO(ResourceLocation name, Color color) {
            super(name, color);
        }

        public FluidIO(String name, Color color) {
            super(name, color);
        }

        @Override
        public void addCapability(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, ManaLevel manaLevel, IPosTrack iPosTrack) {
            super.addCapability(event, duskModCapability, manaLevel, iPosTrack);
            IControl iControl = duskModCapability.addCapability(CapabilityRegister.iControl.capability, new Control(iPosTrack, List.of(BindType.fluidIn, BindType.fluidOut, BindType.manaIn), manaLevel));
            IBack iBack = duskModCapability.addCapability(CapabilityRegister.iBlack.capability, new Back());
            IClock iClock = duskModCapability.addCapability(CapabilityRegister.iClock.capability, new ManaClock(iBack, manaLevel.clock / 10, iControl, 4L * manaLevel.level));
            iClock.addBlock(() -> {
                Level level = event.getObject().getLevel();
                if (level == null) {
                    return;
                }
                Map<IPosTrack, IFluidHandler> inMap = iControl.getCapability(BindType.fluidIn);
                if (inMap.isEmpty()) {
                    return;
                }
                Map<IPosTrack, IFluidHandler> _outMap = iControl.getCapability(BindType.fluidOut);
                if (_outMap.isEmpty()) {
                    return;
                }
                Map<IPosTrack, IFluidHandler> outMap = new HashMap<>();
                for (Map.Entry<IPosTrack, IFluidHandler> entry : _outMap.entrySet()) {
                    if (!inMap.containsKey(entry.getKey())) {
                        outMap.put(entry.getKey(), entry.getValue());
                    }
                }
                int maxRate = 1000 * manaLevel.level;
                FluidStack outSimulate = null;
                Extension.Data_3<IPosTrack, IFluidHandler, FluidStack> outData = null;
                for (Map.Entry<IPosTrack, IFluidHandler> entry : inMap.entrySet()) {
                    for (int i = 0; i < entry.getValue().getTanks(); i++) {
                        FluidStack fluidStack = entry.getValue().getFluidInTank(i);
                        if (!fluidStack.isEmpty()) {
                            FluidStack out = fluidStack.copy();
                            out.setAmount(Math.min(out.getAmount(), maxRate));
                            FluidStack extractFluidStack = entry.getValue().drain(out, IFluidHandler.FluidAction.SIMULATE);
                            if (!extractFluidStack.isEmpty()) {
                                outSimulate = extractFluidStack.copy();
                                outData = new Extension.Data_3<>(entry.getKey(), entry.getValue(), extractFluidStack);
                                break;
                            }
                        }
                    }
                    if (outSimulate != null) {
                        break;
                    }
                }
                if (outSimulate == null) {
                    return;
                }
                for (IFluidHandler value : outMap.values()) {
                    outSimulate.setAmount(outSimulate.getAmount() - value.fill(outSimulate, IFluidHandler.FluidAction.SIMULATE));
                    if (outSimulate.isEmpty()) {
                        break;
                    }
                }
                if (!outSimulate.isEmpty()) {
                    return;
                }
                CapabilityHelp.drainAndFillFluid(iPosTrack, outData.d2(), outData.d1().getPos(), outData.d3(), outMap, false);
            });
        }
    }
}
