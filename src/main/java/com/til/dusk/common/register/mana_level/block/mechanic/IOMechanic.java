package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.Dusk;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.capability.CapabilityHelp;
import com.til.dusk.common.capability.DuskCapabilityProvider;
import com.til.dusk.common.capability.black.Back;
import com.til.dusk.common.capability.black.IBack;
import com.til.dusk.common.capability.clock.IClock;
import com.til.dusk.common.capability.clock.ManaClock;
import com.til.dusk.common.capability.control.Control;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.mana_handle.IManaHandle;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.config.ConfigField;
import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.mana_level.block.DefaultCapacityMechanic;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.bind_type.BindType;
import com.til.dusk.common.register.other.CapabilityRegister;
import com.til.dusk.common.world.block.DuskBlock;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.Extension;
import com.til.dusk.util.math.INumberPack;
import com.til.dusk.util.prefab.ColorPrefab;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author til
 */
public abstract class IOMechanic extends DefaultCapacityMechanic {
    public static final ResourceLocation MODEL_NAME = new ResourceLocation(Dusk.MOD_ID, "io");

    public IOMechanic(ResourceLocation name) {
        super(name);
    }

    public IOMechanic(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    @Override
    public DuskBlock.ICustomModel getBlockModelMapping(ManaLevel manaLevel) {
        return () -> MODEL_NAME;
    }

    @Override
    public void dyeBlack(ManaLevel manaLevel, ColorProxy.ItemColorPack itemColorPack) {
        super.dyeBlack(manaLevel, itemColorPack);
        itemColorPack.addColor(1, itemStack -> ioColor);
    }

    @Override
    public void dyeBlack(ManaLevel manaLevel, ColorProxy.BlockColorPack blockColorPack) {
        super.dyeBlack(manaLevel, blockColorPack);
        blockColorPack.addColor(1, (blockState, blockAndTintGetter, blockPos) -> ioColor);
    }


    public static class ManaIO extends IOMechanic {

        public ManaIO() {
            super("mana_io");
        }

        @Override
        public void addCapability(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, ManaLevel manaLevel, IPosTrack iPosTrack) {
            super.addCapability(event, duskModCapability, manaLevel, iPosTrack);
            long max = (long) transmissionAmount.ofValue(manaLevel.level);
            IControl iControl = duskModCapability.addCapability(CapabilityRegister.iControl.capability, new Control(iPosTrack, List.of(BindType.manaIn, BindType.manaOut), manaLevel));
            IBack iUp = duskModCapability.addCapability(CapabilityRegister.iBlack.capability, new Back());
            iUp.add(IBack.UP, v -> {
                Level level = event.getObject().getLevel();
                if (level == null) {
                    return;
                }
                CapabilityHelp.manaPointToPointTransmit(iControl.getPosTrack(), iControl.getCapability(BindType.manaIn),
                        iControl.getCapability(BindType.manaOut), max, manaLevel.manaLoss, false);
            });
        }

        @Override
        public void registerLang(LangProvider.LangTool lang) {
            lang.setCache(name.toLanguageKey());
            lang.add(LangType.ZH_CN, "灵气传输节点");
            lang.add(LangType.EN_CH, "Item IO");
        }

        @Override
        public void defaultConfig() {
            ioColor = ColorPrefab.MANA_IO;
            transmissionAmount = new INumberPack.IIntPack.LinearFunction(new INumberPack.IIntPack.Constant(1024), new INumberPack.IIntPack.Constant(0));
        }

    }

    public static class ItemIO extends IOMechanic {

        public ItemIO() {
            super("item_io");
        }

        @Override
        public void addCapability(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, ManaLevel manaLevel, IPosTrack iPosTrack) {
            super.addCapability(event, duskModCapability, manaLevel, iPosTrack);
            IControl iControl = duskModCapability.addCapability(CapabilityRegister.iControl.capability, new Control(iPosTrack, List.of(BindType.itemIn, BindType.itemOut, BindType.manaIn), manaLevel));
            IBack iUp = duskModCapability.addCapability(CapabilityRegister.iBlack.capability, new Back());
            IClock iClock = duskModCapability.addCapability(CapabilityRegister.iClock.capability, new ManaClock(iUp,
                    (int) (manaLevel.clock / transmissionEfficiency.ofValue(manaLevel.level)), iControl, consume.ofValue((long) manaLevel.level)));
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
                Extension.Data_3<Map.Entry<IPosTrack, IItemHandler>, ItemStack, Integer> outData = null;
                for (Map.Entry<IPosTrack, IItemHandler> entry : inMap.entrySet()) {
                    for (int i = 0; i < entry.getValue().getSlots(); i++) {
                        ItemStack itemStack = entry.getValue().getStackInSlot(i);
                        if (!itemStack.isEmpty()) {
                            ItemStack extractItemStack = entry.getValue().extractItem(i, Math.min(itemStack.getMaxStackSize(), 64), true);
                            if (!extractItemStack.isEmpty()) {
                                outSimulate = extractItemStack;
                                outData = new Extension.Data_3<>(entry, extractItemStack, i);
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
                CapabilityHelp.extractAndInsertItem(iPosTrack, outData.d1(), outData.d3(), outData.d2().getCount(), outMap, false);
            });
        }

        @Override
        public void registerLang(LangProvider.LangTool lang) {
            lang.setCache(name.toLanguageKey());
            lang.add(LangType.ZH_CN, "物品传输节点");
            lang.add(LangType.EN_CH, "Item IO");
        }

        @Override
        public void defaultConfig() {
            ioColor = ColorPrefab.ITEM_IO;
            transmissionEfficiency = new INumberPack.IIntPack.Constant(5);
            consume = new INumberPack.ILongPack.LinearFunction(new INumberPack.ILongPack.Constant(2), new INumberPack.ILongPack.Constant(0));
        }
    }

    public static class FluidIO extends IOMechanic {

        public FluidIO() {
            super("fluid_io");
        }

        @Override
        public void addCapability(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, ManaLevel manaLevel, IPosTrack iPosTrack) {
            super.addCapability(event, duskModCapability, manaLevel, iPosTrack);
            IControl iControl = duskModCapability.addCapability(CapabilityRegister.iControl.capability, new Control(iPosTrack, List.of(BindType.fluidIn, BindType.fluidOut, BindType.manaIn), manaLevel));
            IBack iBack = duskModCapability.addCapability(CapabilityRegister.iBlack.capability, new Back());
            IClock iClock = duskModCapability.addCapability(CapabilityRegister.iClock.capability, new ManaClock(iBack,
                    (int) (manaLevel.clock / transmissionEfficiency.ofValue(manaLevel.level)), iControl,  consume.ofValue((long) manaLevel.level)));
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
                int maxRate = (int) transmissionAmount.ofValue(manaLevel.level);
                FluidStack outSimulate = null;
                Extension.Data_2<Map.Entry<IPosTrack, IFluidHandler>, FluidStack> outData = null;
                for (Map.Entry<IPosTrack, IFluidHandler> entry : inMap.entrySet()) {
                    for (int i = 0; i < entry.getValue().getTanks(); i++) {
                        FluidStack fluidStack = entry.getValue().getFluidInTank(i);
                        if (!fluidStack.isEmpty()) {
                            FluidStack out = fluidStack.copy();
                            out.setAmount(Math.min(out.getAmount(), maxRate));
                            FluidStack extractFluidStack = entry.getValue().drain(out, IFluidHandler.FluidAction.SIMULATE);
                            if (!extractFluidStack.isEmpty()) {
                                outSimulate = extractFluidStack.copy();
                                outData = new Extension.Data_2<>(entry, extractFluidStack);
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
                CapabilityHelp.drainAndFillFluid(iPosTrack, outData.d1(), outData.d2(), outMap, false);
            });
        }

        @Override
        public void registerLang(LangProvider.LangTool lang) {
            lang.setCache(name.toLanguageKey());
            lang.add(LangType.ZH_CN, "流体传输节点");
            lang.add(LangType.EN_CH, "Item IO");
        }

        @Override
        public void defaultConfig() {
            ioColor = ColorPrefab.ITEM_IO;
            transmissionEfficiency = new INumberPack.IIntPack.Constant(5);
            transmissionAmount = new INumberPack.IIntPack.LinearFunction(new INumberPack.IIntPack.Constant(1000), new INumberPack.IIntPack.Constant(0));
            consume = new INumberPack.ILongPack.LinearFunction(new INumberPack.ILongPack.Constant(2), new INumberPack.ILongPack.Constant(0));
        }
    }

    @ConfigField
    public DuskColor ioColor;
    @ConfigField
    public INumberPack<Integer> transmissionEfficiency;
    @ConfigField
    public INumberPack<Integer> transmissionAmount;
    @ConfigField
    public INumberPack<Long> consume;

}
