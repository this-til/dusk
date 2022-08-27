package com.til.dusk.common.register;

import com.til.dusk.Dusk;
import com.til.dusk.client.other_mod_interact.Jade_Interact;
import com.til.dusk.common.capability.clock.IClock;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.handle.IHandle;
import com.til.dusk.common.capability.handle.ShapedHandle;
import com.til.dusk.common.capability.item_handler.VoidCaseItemHandler;
import com.til.dusk.common.capability.mana_handle.IManaHandle;
import com.til.dusk.common.capability.mana_level.IManaLevel;
import com.til.dusk.common.capability.shaped_drive.IShapedDrive;
import com.til.dusk.common.capability.up.IUp;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.util.tag_tool.TagTool;
import com.til.dusk.util.Lang;
import com.til.dusk.util.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;
import org.jetbrains.annotations.NotNull;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.config.IPluginConfig;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public abstract class CapabilityRegister<C> extends RegisterBasics<CapabilityRegister<C>> {

    public static Supplier<IForgeRegistry<CapabilityRegister<?>>> CAPABILITY_REGISTER;
    public static CapabilityRegister<IManaLevel> iManaLevel;
    public static CapabilityRegister<IItemHandler> iItemHandler;
    public static CapabilityRegister<IFluidHandler> iFluidHandler;
    public static CapabilityRegister<IClock> iClock;
    public static CapabilityRegister<IControl> iControl;
    public static CapabilityRegister<IUp> iUp;
    public static CapabilityRegister<IManaHandle> iManaHandle;
    public static CapabilityRegister<IShapedDrive> iShapedDrive;
    public static CapabilityRegister<IHandle> iHandle;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        CAPABILITY_REGISTER = event.create(new RegistryBuilder<CapabilityRegister<?>>().setName(new ResourceLocation(Dusk.MOD_ID, "capability_register")));
        iManaLevel = new CapabilityRegister<>("i_mana_level", IManaLevel.class, () -> new CapabilityToken<IManaLevel>() {
        }) {
            @Override
            public void appendTooltip(Jade_Interact.TooltipPack iTooltip, BlockAccessor blockAccessor, IPluginConfig iPluginConfig, CompoundTag component) {
                ManaLevel manaLevel = TagTool.manaLevelTag.get(component);
                iTooltip.add(Lang.getLang(Lang.getLang(this), Lang.getLang(manaLevel)));
            }

            @Override
            public @NotNull CompoundTag appendServerData(ServerPlayer serverPlayer, Level level, BlockEntity blockEntity, boolean detailed, IManaLevel i) {
                CompoundTag compoundTag = new CompoundTag();
                TagTool.manaLevelTag.set(compoundTag, i.manaLevel());
                return compoundTag;
            }
        };
        iItemHandler = new CapabilityRegister<>("i_item_handler", IItemHandler.class, ForgeCapabilities.ITEM_HANDLER) {
            @Override
            public @NotNull CompoundTag appendServerData(ServerPlayer serverPlayer, Level level, BlockEntity blockEntity, boolean detailed, IItemHandler i) {
                if (i instanceof VoidCaseItemHandler voidCaseItemHandler) {
                    CompoundTag compoundTag = voidCaseItemHandler.serializeNBT();
                    TagTool.isVoidCaseItemHandlerTag.set(compoundTag, true);
                    return compoundTag;
                } else {
                    CompoundTag compoundTag = new CompoundTag();
                    List<ItemStack> itemStackList = new ArrayList<>(i.getSlots());
                    for (int i1 = 0; i1 < i.getSlots(); i1++) {
                        itemStackList.add(i.getStackInSlot(i1));
                    }
                    TagTool.itemStackListTag.set(compoundTag, itemStackList);
                    return compoundTag;
                }
            }

            @Override
            public void appendTooltip(Jade_Interact.TooltipPack iTooltip, BlockAccessor blockAccessor, IPluginConfig iPluginConfig, CompoundTag compoundTag) {
                if (TagTool.isVoidCaseItemHandlerTag.get(compoundTag)) {
                    ItemStack itemStack = TagTool.itemStackTag.get(compoundTag);
                    long c = TagTool.count.get(compoundTag);
                    if (!itemStack.isEmpty() && c > 0) {
                        iTooltip.add(Lang.getLang(Lang.getLang(this), itemStack.getDisplayName(), Component.literal("x" + c)));
                        return;
                    }
                }
                super.appendTooltip(iTooltip, blockAccessor, iPluginConfig, compoundTag);
                Map<Item, Integer> integerMap = new HashMap<>();
                TagTool.itemStackListTag.get(compoundTag).forEach(itemStack -> {
                    if (itemStack.isEmpty()) {
                        return;
                    }
                    Item item = itemStack.getItem();
                    if (integerMap.containsKey(item)) {
                        integerMap.put(item, integerMap.get(item) + itemStack.getCount());
                    } else {
                        integerMap.put(item, itemStack.getCount());
                    }
                });
                for (Map.Entry<Item, Integer> itemIntegerEntry : integerMap.entrySet()) {
                    ItemStack itemStack = new ItemStack(itemIntegerEntry.getKey(), itemIntegerEntry.getValue());
                    iTooltip.add(
                            Lang.getLang(itemStack.getDisplayName(),
                                    Component.literal("x" + itemIntegerEntry.getValue())));
                }

            }
        };
        iFluidHandler = new CapabilityRegister<>("i_fluid_handler", IFluidHandler.class, ForgeCapabilities.FLUID_HANDLER) {
            @Override
            public @NotNull CompoundTag appendServerData(ServerPlayer serverPlayer, Level level, BlockEntity blockEntity, boolean detailed, IFluidHandler i) {
                CompoundTag compoundTag = new CompoundTag();
                List<FluidStack> fluidStacks = new ArrayList<>(i.getTanks());
                for (int i1 = 0; i1 < i.getTanks(); i1++) {
                    fluidStacks.add(i.getFluidInTank(i1));
                }
                TagTool.fluidStackListTag.set(compoundTag, fluidStacks);
                return compoundTag;
            }

            @Override
            public void appendTooltip(Jade_Interact.TooltipPack iTooltip, BlockAccessor blockAccessor, IPluginConfig iPluginConfig, CompoundTag compoundTag) {
                super.appendTooltip(iTooltip, blockAccessor, iPluginConfig, compoundTag);
                Map<Fluid, Integer> integerMap = new HashMap<>();
                TagTool.fluidStackListTag.get(compoundTag).forEach(fluidStack -> {
                    if (fluidStack.isEmpty()) {
                        return;
                    }
                    Fluid item = fluidStack.getFluid();
                    if (integerMap.containsKey(item)) {
                        integerMap.put(item, integerMap.get(item) + fluidStack.getAmount());
                    } else {
                        integerMap.put(item, fluidStack.getAmount());
                    }
                });
                for (Map.Entry<Fluid, Integer> fluidIntegerEntry : integerMap.entrySet()) {
                    FluidStack fluidStack = new FluidStack(fluidIntegerEntry.getKey(), fluidIntegerEntry.getValue());
                    Lang.getLang(fluidStack.getDisplayName(),
                            Component.literal("x" + fluidIntegerEntry.getValue()));
                }
            }
        };
        iClock = new CapabilityRegister<>("i_clock", IClock.class, () -> new CapabilityToken<IClock>() {
        }) {
            @org.jetbrains.annotations.Nullable
            @Override
            public CompoundTag appendServerData(ServerPlayer serverPlayer, Level level, BlockEntity blockEntity, boolean detailed, IClock i) {
                CompoundTag compoundTag = super.appendServerData(serverPlayer, level, blockEntity, detailed, i);
                if (compoundTag != null) {
                    TagTool.cycleTimeTag.set(compoundTag, i.getCycleTime());
                }
                return compoundTag;
            }

            @Override
            public void appendTooltip(Jade_Interact.TooltipPack iTooltip, BlockAccessor blockAccessor, IPluginConfig iPluginConfig, CompoundTag compoundTag) {
                iTooltip.add(Lang.getLang(Lang.getLang(this), Component.literal(TagTool.timeTag.get(compoundTag) + "/" + TagTool.cycleTimeTag.get(compoundTag))));
            }
        };
        iControl = new CapabilityRegister<>("i_control", IControl.class, () -> new CapabilityToken<IControl>() {
        }) {
            @org.jetbrains.annotations.Nullable
            @Override
            public CompoundTag appendServerData(ServerPlayer serverPlayer, Level level, BlockEntity blockEntity, boolean detailed, IControl i) {
                CompoundTag compoundTag = super.appendServerData(serverPlayer, level, blockEntity, detailed, i);
                if (compoundTag != null) {
                    TagTool.maxBindTag.set(compoundTag, i.getMaxBind());
                    TagTool.maxRangeTag.set(compoundTag, i.getMaxRange());
                    TagTool.bindTypeListTag.set(compoundTag, i.getCanBindType());
                }
                return compoundTag;
            }

            @Override
            public void appendTooltip(Jade_Interact.TooltipPack iTooltip, BlockAccessor blockAccessor, IPluginConfig iPluginConfig, CompoundTag compoundTag) {
                super.appendTooltip(iTooltip, blockAccessor, iPluginConfig, compoundTag);
                iTooltip.add(Lang.getLang(Component.translatable(Lang.getKey("最大绑定数量")), Component.literal(String.valueOf(TagTool.maxBindTag.get(compoundTag)))));
                iTooltip.add(Lang.getLang(Component.translatable(Lang.getKey("最大范围")), Component.literal(String.valueOf(TagTool.maxRangeTag.get(compoundTag)))));

                Map<BindType, List<BlockPos>> bindTypeListMap = TagTool.bindType_BlockPosListMapTag.get(compoundTag);
                List<BindType> bindTypeList = TagTool.bindTypeListTag.get(compoundTag);
                iTooltip.add(Component.translatable(Lang.getKey("绑定类型")));
                iTooltip.indent();
                for (BindType bindType : bindTypeList) {
                    iTooltip.add(Lang.getLang(Lang.getLang(bindType), Component.literal(":")));
                    iTooltip.indent();
                    if (bindTypeListMap.containsKey(bindType)) {
                        for (BlockPos blockPos : bindTypeListMap.get(bindType)) {
                            iTooltip.add(Component.translatable("[x:%s,y:%s,z:%s]",
                                    Component.literal(String.valueOf(blockPos.getX())),
                                    Component.literal(String.valueOf(blockPos.getY())),
                                    Component.literal(String.valueOf(blockPos.getZ()))));
                        }

                    }
                    iTooltip.returnIndent();
                }
                iTooltip.returnIndent();
            }
        };
        iUp = new CapabilityRegister<>("i_up", IUp.class, () -> new CapabilityToken<IUp>() {
        }) {
        };
        iManaHandle = new CapabilityRegister<>("i_mana_handle", IManaHandle.class, () -> new CapabilityToken<IManaHandle>() {
        }) {
            @org.jetbrains.annotations.Nullable
            @Override
            public CompoundTag appendServerData(ServerPlayer serverPlayer, Level level, BlockEntity blockEntity, boolean detailed, IManaHandle i) {
                CompoundTag compoundTag = super.appendServerData(serverPlayer, level, blockEntity, detailed, i);
                if (compoundTag != null) {
                    TagTool.maxManaTag.set(compoundTag, i.getMaxMana());
                    TagTool.rateTag.set(compoundTag, i.getMaxRate());
                }
                return compoundTag;
            }

            @Override
            public void appendTooltip(Jade_Interact.TooltipPack iTooltip, BlockAccessor blockAccessor, IPluginConfig iPluginConfig, CompoundTag compoundTag) {
                super.appendTooltip(iTooltip, blockAccessor, iPluginConfig, compoundTag);
                long mana = TagTool.manaTag.get(compoundTag);
                long maxMana = TagTool.maxManaTag.get(compoundTag);
                long rate = TagTool.rateTag.get(compoundTag);
                if (mana > 0 && maxMana > 0) {
                    iTooltip.add(Lang.getLang(Component.translatable(Lang.getKey("现存灵气")), Component.literal(mana + "/" + maxMana)));
                }
                iTooltip.add(Lang.getLang(Component.translatable(Lang.getKey("最大灵气流速")), Component.literal(String.valueOf(rate))));
            }
        };
        iShapedDrive = new CapabilityRegister<>("i_shaped_drive", IShapedDrive.class, () -> new CapabilityToken<IShapedDrive>() {
        }) {
            @Override
            public @NotNull CompoundTag appendServerData(ServerPlayer serverPlayer, Level level, BlockEntity blockEntity, boolean detailed, IShapedDrive i) {
                CompoundTag compoundTag = new CompoundTag();
                TagTool.shapedDriveListTag.set(compoundTag, i.get());
                return compoundTag;
            }

            @Override
            public void appendTooltip(Jade_Interact.TooltipPack iTooltip, BlockAccessor blockAccessor, IPluginConfig iPluginConfig, CompoundTag compoundTag) {
                List<ShapedDrive> shapedDriveList = TagTool.shapedDriveListTag.get(compoundTag);
                if (shapedDriveList.isEmpty()) {
                    return;
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append('[');
                for (ShapedDrive shapedDrive : shapedDriveList) {
                    stringBuilder.append(shapedDrive.name.getPath());
                    stringBuilder.append(',');
                }
                stringBuilder.charAt(stringBuilder.length() - 1);
                stringBuilder.append(']');
                iTooltip.add(Lang.getLang(Lang.getLang(this), Component.literal(stringBuilder.toString())));
            }
        };
        iHandle = new CapabilityRegister<>("i_handle", IHandle.class, () -> new CapabilityToken<IHandle>() {
        }) {
            @org.jetbrains.annotations.Nullable
            @Override
            public CompoundTag appendServerData(ServerPlayer serverPlayer, Level level, BlockEntity blockEntity, boolean detailed, IHandle i) {
                CompoundTag compoundTag = super.appendServerData(serverPlayer, level, blockEntity, detailed, i);
                if (compoundTag != null) {
                    TagTool.maxParallelTag.set(compoundTag, i.getParallelHandle());
                    TagTool.shapedDriveListTag.set(compoundTag, i.getShapedDrive());
                }
                return compoundTag;
            }

            @Override
            public void appendTooltip(Jade_Interact.TooltipPack iTooltip, BlockAccessor blockAccessor, IPluginConfig iPluginConfig, CompoundTag compoundTag) {
                super.appendTooltip(iTooltip, blockAccessor, iPluginConfig, compoundTag);
                iTooltip.add(Lang.getLang(Component.translatable(Lang.getKey("max.parallel")),
                        Component.literal(String.valueOf(TagTool.maxParallelTag.get(compoundTag)))));
                List<ShapedDrive> shapedDriveList = TagTool.shapedDriveListTag.get(compoundTag);
                StringBuilder stringBuilder = new StringBuilder();
                if (shapedDriveList.isEmpty()) {
                    stringBuilder.append('[').append(']');
                } else {
                    stringBuilder.append('[');
                    for (ShapedDrive shapedDrive : shapedDriveList) {
                        stringBuilder.append(shapedDrive.name.getPath());
                        stringBuilder.append(',');
                    }
                    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                    stringBuilder.append(']');
                }
                iTooltip.add(Lang.getLang(Component.translatable(Lang.getKey("使用配方集")), Component.literal(stringBuilder.toString())));

                List<ShapedHandle> shapedHandles = TagTool.shapedHandleListTag.get(compoundTag);
                if (shapedHandles.isEmpty()) {
                    return;
                }
                for (int i = 0; i < shapedHandles.size(); i++) {
                    ShapedHandle shapedHandle = shapedHandles.get(i);
                    iTooltip.indent();
                    iTooltip.add(Lang.getLang(Component.translatable(Lang.getKey("配方处理")), Component.literal(String.valueOf(i))));
                    iTooltip.indent();
                    iTooltip.add(Lang.getLang(Component.translatable(Lang.getKey("状态")), Lang.getLang(shapedHandle.process)));
                    if (shapedHandle.consumeMana > 0) {
                        iTooltip.add(Lang.getLang(Component.translatable(Lang.getKey("消耗灵气")), Component.literal(String.valueOf(shapedHandle.consumeMana))));
                    }
                    if (shapedHandle._surplusTime > 0) {
                        iTooltip.add(Lang.getLang(Component.translatable(Lang.getKey("剩余时间")), Component.literal(String.valueOf(shapedHandle._surplusTime))));
                    }
                    if (shapedHandle.outMana > 0) {
                        iTooltip.add(Lang.getLang(Component.translatable(Lang.getKey("输出灵气")), Component.literal(String.valueOf(shapedHandle.outMana))));
                    }
                    if (shapedHandle.outItem != null) {
                        iTooltip.indent();
                        iTooltip.add(Lang.getLang(Lang.getLang(BindType.itemOut), Component.translatable(":")));
                        iTooltip.indent();
                        for (ItemStack itemStack : shapedHandle.outItem) {
                            iTooltip.add(Lang.getLang(itemStack.getDisplayName(), Component.literal("x" + itemStack.getCount())));
                        }
                        iTooltip.returnIndent();
                        iTooltip.returnIndent();
                    }
                    if (shapedHandle.outFluid != null) {
                        iTooltip.indent();
                        iTooltip.add(Lang.getLang(Lang.getLang(BindType.fluidOut), Component.translatable(":")));
                        iTooltip.indent();
                        for (FluidStack fluidStack : shapedHandle.outFluid) {
                            iTooltip.add(Lang.getLang(fluidStack.getDisplayName(), Component.literal("x" + fluidStack.getAmount() + "ml")));
                        }
                        iTooltip.returnIndent();
                        iTooltip.returnIndent();
                    }
                    iTooltip.returnIndent();
                    iTooltip.returnIndent();
                }
            }
        };


    }

    public final Class<C> cClass;
    public final Capability<C> capability;
    public boolean needRegister = true;

    public CapabilityRegister(ResourceLocation name, Class<C> cClass, Supplier<CapabilityToken<C>> create) {
        super(name, Util.forcedConversion(CAPABILITY_REGISTER));
        this.cClass = cClass;
        Dusk.instance.modEventBus.addListener(this::registerCapabilities);
        capability = CapabilityManager.get(create.get());
    }

    public CapabilityRegister(String name, Class<C> cClass, Supplier<CapabilityToken<C>> create) {
        this(new ResourceLocation(Dusk.MOD_ID, name), cClass, create);
    }

    public CapabilityRegister(ResourceLocation name, Class<C> cClass, Capability<C> capability) {
        super(name, Util.forcedConversion(CAPABILITY_REGISTER));
        this.cClass = cClass;
        Dusk.instance.modEventBus.addListener(this::registerCapabilities);
        this.capability = capability;
        this.needRegister = false;

    }

    public CapabilityRegister(String name, Class<C> cClass, Capability<C> capability) {
        this(new ResourceLocation(Dusk.MOD_ID, name), cClass, capability);
    }

    public void registerCapabilities(RegisterCapabilitiesEvent event) {
        if (needRegister) {
            event.register(cClass);
        }
    }

    @Nullable
    public CompoundTag appendServerData(ServerPlayer serverPlayer, Level level, BlockEntity blockEntity, boolean detailed, C i) {
        if (i instanceof INBTSerializable serializable) {
            INBTSerializable<CompoundTag> compoundTagINBTSerializable = Util.forcedConversion(serializable);
            return compoundTagINBTSerializable.serializeNBT();
        }
        return null;
    }


    public void appendTooltip(Jade_Interact.TooltipPack iTooltip, BlockAccessor blockAccessor, IPluginConfig iPluginConfig, CompoundTag compoundTag) {
        iTooltip.add(Lang.getLang(this));
        iTooltip.indent();
    }
}
