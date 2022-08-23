package com.til.dusk.common.register;

import com.til.dusk.Dusk;
import com.til.dusk.client.other_mod_interact.Jade_Interact;
import com.til.dusk.common.capability.clock.IClock;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.handle.IHandle;
import com.til.dusk.common.capability.handle.ShapedHandle;
import com.til.dusk.common.capability.mana_handle.IManaHandle;
import com.til.dusk.common.capability.mana_level.IManaLevel;
import com.til.dusk.common.capability.shaped_drive.IShapedDrive;
import com.til.dusk.common.capability.up.IUp;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.tag_tool.TagTool;
import com.til.dusk.util.Lang;
import com.til.dusk.util.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.AttackDamageMobEffect;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;
import org.jetbrains.annotations.NotNull;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public abstract class CapabilityRegister<C> extends RegisterBasics<CapabilityRegister<C>> {

    public static Supplier<IForgeRegistry<CapabilityRegister<?>>> CAPABILITY_REGISTER;
    public static CapabilityRegister<IManaLevel> iManaLevel;
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
                iTooltip.add(Lang.getLang(Component.translatable(Lang.getKey("can.bind")), Component.literal(String.valueOf(TagTool.maxBindTag.get(compoundTag)))));
                iTooltip.add(Lang.getLang(Component.translatable(Lang.getKey("max.range")), Component.literal(String.valueOf(TagTool.maxRangeTag.get(compoundTag)))));

                Map<BindType, List<BlockPos>> bindTypeListMap = TagTool.bindType_BlockPosListMapTag.get(compoundTag);
                List<BindType> bindTypeList = TagTool.bindTypeListTag.get(compoundTag);
                iTooltip.add(Component.translatable(Lang.getKey("bind.type")));
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
                    iTooltip.add(Lang.getLang(Lang.getLang(Lang.getKey("now.mana.handel")), Component.literal(mana + "/" + maxMana)));
                }
                iTooltip.add(Lang.getLang(Lang.getLang(Lang.getKey("rate.mana.handel")), Component.literal(String.valueOf(rate))));
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
                    stringBuilder.charAt(stringBuilder.length() - 1);
                    stringBuilder.append(']');
                }
                iTooltip.add(Lang.getLang(Component.translatable(Lang.getKey("use.shaped.type")), Component.literal(stringBuilder.toString())));

                List<ShapedHandle> shapedHandles = TagTool.shapedHandleListTag.get(compoundTag);
                if (shapedHandles.isEmpty()) {
                    return;
                }
                for (int i = 0; i < shapedHandles.size(); i++) {
                    ShapedHandle shapedHandle = shapedHandles.get(i);
                    iTooltip.indent();
                    iTooltip.add(Lang.getLang(Component.translatable(Lang.getKey("shaped.handle")), Component.literal(String.valueOf(i))));
                    iTooltip.indent();
                    iTooltip.add(Lang.getLang(Component.translatable(Lang.getKey("state")), Lang.getLang(shapedHandle.process)));
                    if (shapedHandle.consumeMana > 0) {
                        iTooltip.add(Lang.getLang(Component.translatable(Lang.getKey("consume.mana")), Component.literal(String.valueOf(shapedHandle.consumeMana))));
                    }
                    if (shapedHandle._surplusTime > 0) {
                        iTooltip.add(Lang.getLang(Component.translatable(Lang.getKey("surplus.time")), Component.literal(String.valueOf(shapedHandle._surplusTime))));
                    }
                    if (shapedHandle.outMana > 0) {
                        iTooltip.add(Lang.getLang(Component.translatable(Lang.getKey("out.mana")), Component.literal(String.valueOf(shapedHandle.outMana))));
                    }
                    if (shapedHandle.outItem != null) {
                        iTooltip.indent();
                        iTooltip.add(Lang.getLang(Lang.getLang(BindType.itemOut), Component.translatable(":")));
                        iTooltip.indent();
                        for (ItemStack itemStack : shapedHandle.outItem) {
                            iTooltip.add(Lang.getLang(itemStack.getItem().getName(itemStack), Component.literal("x" + itemStack.getCount())));
                        }
                        iTooltip.returnIndent();
                        iTooltip.returnIndent();
                    }
                    if (shapedHandle.outFluid != null) {
                        iTooltip.indent();
                        iTooltip.add(Lang.getLang(Lang.getLang(BindType.fluidOut), Component.translatable(":")));
                        iTooltip.indent();
                        for (FluidStack fluidStack : shapedHandle.outFluid) {
                            iTooltip.add(Lang.getLang(fluidStack.getFluid().getFluidType().getDescription(), Component.literal("x" + fluidStack.getAmount() + "ml")));
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

    public CapabilityRegister(ResourceLocation name, Class<C> cClass, Supplier<CapabilityToken<C>> create) {
        super(name, Util.forcedConversion(CAPABILITY_REGISTER));
        this.cClass = cClass;
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::registerCapabilities);
        capability = CapabilityManager.get(create.get());
    }

    public CapabilityRegister(String name, Class<C> cClass, Supplier<CapabilityToken<C>> create) {
        this(new ResourceLocation(Dusk.MOD_ID, name), cClass, create);
    }

    public void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.register(cClass);
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