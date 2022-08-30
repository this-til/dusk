package com.til.dusk.common.register.mana_level.mana_level_block;

import com.til.dusk.Dusk;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.capability.clock.Clock;
import com.til.dusk.common.capability.clock.IClock;
import com.til.dusk.common.capability.control.Control;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.mana_handle.IManaHandle;
import com.til.dusk.common.capability.tile_entity.DuskCapabilityProvider;
import com.til.dusk.common.capability.up.IUp;
import com.til.dusk.common.capability.up.Up;
import com.til.dusk.common.event.EventIO;
import com.til.dusk.common.register.BindType;
import com.til.dusk.common.register.CapabilityRegister;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.util.Extension;
import com.til.dusk.util.Pos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.MinecraftForge;
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
        itemColorPack.addClock(1, itemStack -> color);
    }

    @Override
    public void dyeBlack(ManaLevel manaLevel, ColorProxy.BlockColorPack blockColorPack) {
        super.dyeBlack(manaLevel, blockColorPack);
        blockColorPack.addClock(1, (blockState, blockAndTintGetter, blockPos) -> color);
    }

    public static class ManaIO extends IOMechanic {

        public ManaIO(ResourceLocation name, Color color) {
            super(name, color);
        }

        public ManaIO(String name, Color color) {
            super(name, color);
        }
        @Override
        public void addCapability(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, ManaLevel manaLevel) {
            super.addCapability(event, duskModCapability, manaLevel);
            IControl iControl = duskModCapability.addCapability(CapabilityRegister.iControl.capability, new Control(event.getObject(), List.of(BindType.manaIn, BindType.manaOut), manaLevel));
            IUp iUp = duskModCapability.addCapability(CapabilityRegister.iUp.capability, new Up());
            iUp.addUpBlack(() -> {
                Level level = event.getObject().getLevel();
                if (level == null) {
                    return;
                }
                Map<BlockEntity, IManaHandle> inMap = iControl.getCapability(BindType.manaIn);
                if (inMap.isEmpty()) {
                    return;
                }
                Map<BlockEntity, IManaHandle> _outMap = iControl.getCapability(BindType.manaOut);
                if (_outMap.isEmpty()) {
                    return;
                }
                Map<BlockEntity, IManaHandle> outMap = new HashMap<>();
                for (Map.Entry<BlockEntity, IManaHandle> entry : _outMap.entrySet()) {
                    if (!inMap.containsKey(entry.getKey())) {
                        outMap.put(entry.getKey(), entry.getValue());
                    }
                }
                long needOut = 0;
                for (IManaHandle value : outMap.values()) {
                    needOut += value.getOutCurrentRate();
                }
                if (needOut == 0) {
                    return;
                }
                long needIn = 0;
                for (IManaHandle value : inMap.values()) {
                    needIn += value.getInCurrentRate();
                }
                if (needIn == 0) {
                    return;
                }
                long need = Math.min(needIn, needOut);
                long inIO = 0;
                for (Map.Entry<BlockEntity, IManaHandle> entry : inMap.entrySet()) {
                    long in = entry.getValue().extractMana(need);
                    MinecraftForge.EVENT_BUS.post(new EventIO.Mana(level, new Pos(entry.getKey().getBlockPos()), new Pos(event.getObject().getBlockPos()), in));
                    need -= in;
                    inIO += in;
                    if (need <= 0) {
                        break;
                    }
                }
                for (Map.Entry<BlockEntity, IManaHandle> entry : outMap.entrySet()) {
                    long out = entry.getValue().addMana(inIO);
                    MinecraftForge.EVENT_BUS.post(new EventIO.Mana(level, new Pos(event.getObject().getBlockPos()), new Pos(entry.getKey().getBlockPos()), out));
                    inIO -= out;
                    if (inIO <= 0) {
                        break;
                    }
                }
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
        public void addCapability(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, ManaLevel manaLevel) {
            super.addCapability(event, duskModCapability, manaLevel);
            IControl iControl = duskModCapability.addCapability(CapabilityRegister.iControl.capability, new Control(event.getObject(), List.of(BindType.itemIn, BindType.itemOut, BindType.manaIn), manaLevel));
            IUp iUp = duskModCapability.addCapability(CapabilityRegister.iUp.capability, new Up());
            IClock iClock = duskModCapability.addCapability(CapabilityRegister.iClock.capability, new Clock(iUp, manaLevel.clock / 20));
            iClock.addBlock(() -> {
                Level level = event.getObject().getLevel();
                if (level == null) {
                    return;
                }
                Map<BlockEntity, IItemHandler> inMap = iControl.getCapability(BindType.itemIn);
                if (inMap.isEmpty()) {
                    return;
                }
                Map<BlockEntity, IManaHandle> inMana = iControl.getCapability(BindType.manaIn);
                if (inMana.isEmpty()) {
                    return;
                }
                Map<BlockEntity, IItemHandler> _outMap = iControl.getCapability(BindType.itemOut);
                if (_outMap.isEmpty()) {
                    return;
                }
                Map<BlockEntity, IItemHandler> outMap = new HashMap<>();
                for (Map.Entry<BlockEntity, IItemHandler> entry : _outMap.entrySet()) {
                    if (!inMap.containsKey(entry.getKey())) {
                        outMap.put(entry.getKey(), entry.getValue());
                    }
                }
                ItemStack outSimulate = null;
                Extension.Data_4<BlockEntity, IItemHandler, ItemStack, Integer> outData = null;
                for (Map.Entry<BlockEntity, IItemHandler> entry : inMap.entrySet()) {
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
                    outSimulate = ItemHandlerHelper.insertItem(value, outSimulate, true);
                    if (outSimulate.isEmpty()) {
                        break;
                    }
                }
                if (!outSimulate.isEmpty()) {
                    return;
                }
                ItemStack out = outData.d2().extractItem(outData.d4(), outData.d3().getCount(), false);
                MinecraftForge.EVENT_BUS.post(new EventIO.Item(level, new Pos(outData.d1().getBlockPos()), new Pos(event.getObject().getBlockPos()), out));
                for (Map.Entry<BlockEntity, IItemHandler> entry : outMap.entrySet()) {
                    ItemStack outCopy = out.copy();
                    out = ItemHandlerHelper.insertItem(entry.getValue(), out, false);
                    outCopy.setCount(outCopy.getCount() - out.getCount());
                    MinecraftForge.EVENT_BUS.post(new EventIO.Item(level, new Pos(event.getObject().getBlockPos()), new Pos(entry.getKey().getBlockPos()), outCopy));
                    if (out.isEmpty()) {
                        return;
                    }
                }
            });
        }
    }

    public static class FluidIO extends  IOMechanic{
        public FluidIO(ResourceLocation name, Color color) {
            super(name, color);
        }

        public FluidIO(String name, Color color) {
            super(name, color);
        }

        @Override
        public void addCapability(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, ManaLevel manaLevel) {
            super.addCapability(event, duskModCapability, manaLevel);
            IControl iControl = duskModCapability.addCapability(CapabilityRegister.iControl.capability, new Control(event.getObject(), List.of(BindType.fluidIn, BindType.fluidOut, BindType.manaIn), manaLevel));
            IUp iUp = duskModCapability.addCapability(CapabilityRegister.iUp.capability, new Up());
            IClock iClock = duskModCapability.addCapability(CapabilityRegister.iClock.capability, new Clock(iUp, manaLevel.clock / 20));
            iClock.addBlock(() -> {
                Level level = event.getObject().getLevel();
                if (level == null) {
                    return;
                }
                Map<BlockEntity, IFluidHandler> inMap = iControl.getCapability(BindType.fluidIn);
                if (inMap.isEmpty()) {
                    return;
                }
                Map<BlockEntity, IFluidHandler> _outMap = iControl.getCapability(BindType.fluidOut);
                if (_outMap.isEmpty()) {
                    return;
                }
                Map<BlockEntity, IFluidHandler> outMap = new HashMap<>();
                for (Map.Entry<BlockEntity, IFluidHandler> entry : _outMap.entrySet()) {
                    if (!inMap.containsKey(entry.getKey())) {
                        outMap.put(entry.getKey(), entry.getValue());
                    }
                }
                int maxRate = 1000 * manaLevel.level;
                FluidStack outSimulate = null;
                Extension.Data_3<BlockEntity, IFluidHandler, FluidStack> outData = null;
                for (Map.Entry<BlockEntity, IFluidHandler> entry : inMap.entrySet()) {
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
                FluidStack out = outData.d2().drain(outData.d3(), IFluidHandler.FluidAction.EXECUTE);
                if (out.isEmpty()) {
                    return;
                }
                MinecraftForge.EVENT_BUS.post(new EventIO.Fluid(level, new Pos(outData.d1().getBlockPos()), new Pos(event.getObject().getBlockPos()), out));
                for (Map.Entry<BlockEntity, IFluidHandler> entry : outMap.entrySet()) {
                    int _out = entry.getValue().fill(out, IFluidHandler.FluidAction.EXECUTE);
                    FluidStack __out = out.copy();
                    __out.setAmount(_out);
                    out.setAmount(out.getAmount() - _out);
                    MinecraftForge.EVENT_BUS.post(new EventIO.Fluid(level, new Pos(event.getObject().getBlockPos()), new Pos(entry.getKey().getBlockPos()), __out));
                    if (out.isEmpty()) {
                        return;
                    }
                }
            });
        }
    }
}
