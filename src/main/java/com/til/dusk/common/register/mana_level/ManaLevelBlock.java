package com.til.dusk.common.register.mana_level;

import com.til.dusk.Dusk;
import com.til.dusk.common.capability.fluid_handler.VoidTankFluidHandler;
import com.til.dusk.common.capability.item_handler.VoidCaseItemHandler;
import com.til.dusk.common.capability.mana_handle.IManaHandle;
import com.til.dusk.common.capability.mana_handle.ManaHandle;
import com.til.dusk.common.capability.tile_entity.RepeaterTileEntity;
import com.til.dusk.common.event.EventIO;
import com.til.dusk.common.register.CapabilityRegister;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.util.Extension;
import com.til.dusk.util.Lang;
import com.til.dusk.common.capability.clock.Clock;
import com.til.dusk.common.capability.clock.IClock;
import com.til.dusk.common.capability.control.Control;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.handle.Handle;
import com.til.dusk.common.capability.handle.IHandle;
import com.til.dusk.common.capability.mana_level.IManaLevel;
import com.til.dusk.common.capability.tile_entity.DuskCapabilityProvider;
import com.til.dusk.common.capability.up.IUp;
import com.til.dusk.common.capability.up.Up;
import com.til.dusk.common.register.BindType;
import com.til.dusk.common.register.shaped.ShapedType;
import com.til.dusk.common.world.ModBlock;
import com.til.dusk.util.Pos;
import com.til.dusk.util.StaticTag;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RepeaterBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.MDC;

import java.util.*;
import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public abstract class ManaLevelBlock extends RegisterBasics.BlockUnitRegister<ManaLevelBlock, ManaLevel> {

    /***
     * 需要框架升级
     */
    public static final StaticTag NEED_FRAME_UP = new StaticTag("NEED_FRAME_UP", List.of());

    public static Supplier<IForgeRegistry<ManaLevelBlock>> LEVEL_BLOCK;

    //基础
    /***
     * 中继器
     * 在提取相应方块能力
     */
    public static Mechanic repeater;

    /***
     * 基础框架
     */
    public static ManaLevelBlock frameBasic;

    //产物
    /***
     * 日光晶体
     * 太阳能神教
     */
    public static Mechanic.PassiveProductionMechanic.SimilarSolarEnergyMechanic sunlight;

    /***
     * 月光晶体
     */
    public static Mechanic.PassiveProductionMechanic.SimilarSolarEnergyMechanic moonlight;
    //处理

    /***
     * 研磨
     */
    public static Mechanic.HandleMechanic grind;

    /***
     * 洗涤
     */
    public static Mechanic.HandleMechanic wash;

    /***
     * 离心
     */
    public static Mechanic.HandleMechanic centrifugal;

    /***
     * 打包
     */
    public static Mechanic.HandleMechanic pack;

    /***
     * 解包
     */
    public static Mechanic.HandleMechanic unpack;

    /***
     * 高炉
     */
    public static Mechanic.HandleMechanic blastFurnace;

    /***
     * 结晶
     */
    public static Mechanic.HandleMechanic crystallizing;

    /***
     * 组装机
     */
    public static Mechanic.HandleMechanic assemble;

    /***
     * 蒸馏
     */
    public static Mechanic.HandleMechanic distillation;

    /***
     * 溶解
     */
    public static Mechanic.HandleMechanic dissolution;

    /***
     * 凝固
     */
    public static Mechanic.HandleMechanic freezing;

    /***
     * 高压融合
     */
    public static Mechanic.HandleMechanic highPressureFuse;

    /***
     * 雕刻
     */
    public static Mechanic.HandleMechanic carving;

    //功能

    /***
     * 虚空箱
     * 1280 * l
     */
    public static Mechanic voidCase;

    /***
     * 虚空缸
     * 1280000mb * l
     */
    public static Mechanic voidTank;

    /***
     * 聚灵
     */
    public static Mechanic gatherMana;

    /***
     * 灵气传输
     */
    public static Mechanic manaIO;

    /***
     * 物品传输
     */
    public static Mechanic ItemIO;

    /***
     * 流体传输
     */
    public static Mechanic fluidIO;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        LEVEL_BLOCK = event.create(new RegistryBuilder<ManaLevelBlock>().setName(new ResourceLocation(Dusk.MOD_ID, "mana_level_block")));
        repeater = (Mechanic) new Mechanic("repeater") {
            @Override
            public Block createBlock(ManaLevel manaLevel) {
                return new ModBlock.RepeaterBlock(manaLevel);
            }

            @Override
            public @NotNull Block createCamouflageBlock() {
                return new Block(BlockBehaviour.Properties.of(Material.AIR)) {
                    @Override
                    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> blockBlockStateBuilder) {
                        blockBlockStateBuilder.add(ModBlock.RepeaterBlock.FACING);
                    }
                };
            }
        }.removeTag(NEED_FRAME_UP);
        frameBasic = new Mechanic("frame_basic") {
            @Override
            public Block createBlock(ManaLevel manaLevel) {
                return new ModBlock.MechanicBlock(manaLevel) {
                    @Override
                    public void add(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability) {
                        duskModCapability.addCapability(CapabilityRegister.iManaLevel.capability, () -> manaLevel);
                    }
                };
            }
        }.removeTag(NEED_FRAME_UP);
        sunlight = new Mechanic.PassiveProductionMechanic.SimilarSolarEnergyMechanic("sunlight", 1, Level::isDay);
        moonlight = new Mechanic.PassiveProductionMechanic.SimilarSolarEnergyMechanic("moonlight", 1, Level::isNight);
        grind = new Mechanic.HandleMechanic("grind", () -> List.of(ShapedType.grind));
        wash = new Mechanic.HandleMechanic("wash", () -> List.of(ShapedType.wash));
        centrifugal = new Mechanic.HandleMechanic("centrifugal", () -> List.of(ShapedType.centrifugal));
        pack = new Mechanic.HandleMechanic("pack", () -> List.of(ShapedType.pack));
        unpack = new Mechanic.HandleMechanic("unpack", () -> List.of(ShapedType.unpack));
        blastFurnace = new Mechanic.HandleMechanic("blast_furnace", () -> List.of(ShapedType.blastFurnace));
        crystallizing = new Mechanic.HandleMechanic("crystallizing", () -> List.of(ShapedType.crystallizing));
        assemble = new Mechanic.HandleMechanic("assemble", () -> List.of(ShapedType.assemble));
        distillation = new Mechanic.HandleMechanic("distillation", () -> List.of(ShapedType.distillation));
        dissolution = new Mechanic.HandleMechanic("dissolution", () -> List.of(ShapedType.dissolution));
        freezing = new Mechanic.HandleMechanic("freezing", () -> List.of(ShapedType.freezing));
        highPressureFuse = new Mechanic.HandleMechanic("high_pressure_fuse", () -> List.of(ShapedType.highPressureFuse));
        carving = new Mechanic.HandleMechanic("carving", () -> List.of(ShapedType.carving));
        voidCase = new Mechanic("void_case") {
            @Override
            public Block createBlock(ManaLevel manaLevel) {
                return new ModBlock.MechanicBlock(manaLevel) {
                    @Override
                    public void add(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability) {
                        IManaLevel iManaLevel = duskModCapability.addCapability(CapabilityRegister.iManaLevel.capability, () -> manaLevel);
                        IItemHandler iItemHandler = duskModCapability.addCapability(ForgeCapabilities.ITEM_HANDLER, new VoidCaseItemHandler(1280L * manaLevel.level));
                    }
                };
            }
        };
        voidTank = new Mechanic("void_tank") {
            @Override
            public Block createBlock(ManaLevel manaLevel) {
                return new ModBlock.MechanicBlock(manaLevel) {
                    @Override
                    public void add(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability) {
                        IManaLevel iManaLevel = duskModCapability.addCapability(CapabilityRegister.iManaLevel.capability, () -> manaLevel);
                        IFluidHandler iItemHandler = duskModCapability.addCapability(ForgeCapabilities.FLUID_HANDLER, new VoidTankFluidHandler(1280000 * manaLevel.level));
                    }
                };
            }
        };
        gatherMana = new Mechanic("gather_mana") {
            @Override
            public Block createBlock(ManaLevel manaLevel) {
                return new ModBlock.MechanicBlock(manaLevel) {
                    @Override
                    public void add(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability) {
                        duskModCapability.addCapability(CapabilityRegister.iManaLevel.capability, () -> manaLevel);
                        IUp iUp = duskModCapability.addCapability(CapabilityRegister.iUp.capability, new Up());
                        duskModCapability.addCapability(CapabilityRegister.iManaHandle.capability, new ManaHandle(5120000L * manaLevel.level, 128L * manaLevel.level, iUp));
                    }
                };
            }
        };
        manaIO = new Mechanic("mana_io") {
            @Override
            public Block createBlock(ManaLevel manaLevel) {
                return new ModBlock.MechanicBlock(manaLevel) {
                    @Override
                    public void add(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability) {
                        IManaLevel iManaLevel = duskModCapability.addCapability(CapabilityRegister.iManaLevel.capability, () -> manaLevel);
                        IControl iControl = duskModCapability.addCapability(CapabilityRegister.iControl.capability, new Control(event.getObject(), List.of(BindType.manaIn, BindType.manaOut), iManaLevel));
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
                };
            }
        };
        ItemIO = new Mechanic("item_io") {

            @Override
            public Block createBlock(ManaLevel manaLevel) {
                return new ModBlock.MechanicBlock(manaLevel) {

                    @Override
                    public void add(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability) {
                        IManaLevel iManaLevel = duskModCapability.addCapability(CapabilityRegister.iManaLevel.capability, () -> manaLevel);
                        IControl iControl = duskModCapability.addCapability(CapabilityRegister.iControl.capability, new Control(event.getObject(), List.of(BindType.itemIn, BindType.itemOut), iManaLevel));
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
                };
            }
        };
        fluidIO = new Mechanic("fluid_io") {
            @Override
            public Block createBlock(ManaLevel manaLevel) {
                return new ModBlock.MechanicBlock(manaLevel) {
                    @Override
                    public void add(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability) {
                        IManaLevel iManaLevel = duskModCapability.addCapability(CapabilityRegister.iManaLevel.capability, () -> manaLevel);
                        IControl iControl = duskModCapability.addCapability(CapabilityRegister.iControl.capability, new Control(event.getObject(), List.of(BindType.fluidIn, BindType.fluidOut), iManaLevel));
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
                            int maxRate = 2000 * manaLevel.level;
                            FluidStack outSimulate = null;
                            Extension.Data_3<BlockEntity, IFluidHandler, FluidStack> outData = null;
                            for (Map.Entry<BlockEntity, IFluidHandler> entry : inMap.entrySet()) {
                                for (int i = 0; i < entry.getValue().getTanks(); i++) {
                                    FluidStack fluidStack = entry.getValue().getFluidInTank(i);
                                    if (!fluidStack.isEmpty()) {
                                        FluidStack out = fluidStack.copy();
                                        out.setAmount(Math.min(out.getAmount(), maxRate));
                                        FluidStack extractFluidStack = entry.getValue().drain(fluidStack, IFluidHandler.FluidAction.SIMULATE);
                                        if (!extractFluidStack.isEmpty()) {
                                            outSimulate = extractFluidStack;
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
                            MinecraftForge.EVENT_BUS.post(new EventIO.Fluid(level, new Pos(outData.d1().getBlockPos()), new Pos(event.getObject().getBlockPos()), out));
                            for (Map.Entry<BlockEntity, IFluidHandler> entry : outMap.entrySet()) {
                                FluidStack _out = entry.getValue().drain(out, IFluidHandler.FluidAction.EXECUTE);
                                out.setAmount(out.getAmount() - _out.getAmount());
                                MinecraftForge.EVENT_BUS.post(new EventIO.Fluid(level, new Pos(event.getObject().getBlockPos()), new Pos(entry.getKey().getBlockPos()), _out));
                                if (out.isEmpty()) {
                                    return;
                                }
                            }
                        });
                    }
                };
            }
        };


    }

    public ManaLevelBlock(ResourceLocation name) {
        super(name, LEVEL_BLOCK);
    }

    public ManaLevelBlock(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    public static abstract class Mechanic extends ManaLevelBlock {
        public Mechanic(ResourceLocation name) {
            super(name);
            addTag(NEED_FRAME_UP);
        }

        public Mechanic(String name) {
            super(new ResourceLocation(Dusk.MOD_ID, name));
        }

        public static class HandleMechanic extends Mechanic {

            public final Supplier<List<ShapedType>> getShapedTypeList;

            public HandleMechanic(ResourceLocation name, Supplier<List<ShapedType>> getShapedTypeList) {
                super(name);
                this.getShapedTypeList = getShapedTypeList;
            }

            public HandleMechanic(String name, Supplier<List<ShapedType>> getShapedTypeList) {
                this(new ResourceLocation(Dusk.MOD_ID, name), getShapedTypeList);
            }

            @Override
            public Block createBlock(ManaLevel manaLevel) {

                return new ModBlock.MechanicBlock(manaLevel) {
                    @Override
                    public void add(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability) {
                        addCapabilitie(event, duskModCapability, manaLevel);
                    }
                };
            }


            public void addCapabilitie(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, ManaLevel manaLevel) {
                IManaLevel iManaLevel = duskModCapability.addCapability(CapabilityRegister.iManaLevel.capability, () -> manaLevel);
                IControl iControl = duskModCapability.addCapability(CapabilityRegister.iControl.capability, new Control(event.getObject(), List.of(BindType.manaIn, BindType.manaOut, BindType.itemIn, BindType.itemOut, BindType.fluidIn, BindType.fluidOut, BindType.modelStore), iManaLevel));
                IUp iUp = duskModCapability.addCapability(CapabilityRegister.iUp.capability, new Up());
                IClock iClock = duskModCapability.addCapability(CapabilityRegister.iClock.capability, new Clock(iUp, iManaLevel));
                IHandle iHandle = duskModCapability.addCapability(CapabilityRegister.iHandle.capability, new Handle(event.getObject(), getShapedTypeList.get(), iControl, iClock, iUp, iManaLevel));
            }

        }

        public static abstract class PassiveProductionMechanic extends Mechanic {
            public PassiveProductionMechanic(ResourceLocation name) {
                super(name);
            }

            public PassiveProductionMechanic(String name) {
                this(new ResourceLocation(Dusk.MOD_ID, name));
            }

            @Override
            public Block createBlock(ManaLevel manaLevel) {
                return new ModBlock.MechanicBlock(manaLevel) {
                    @Override
                    public void add(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability) {
                        duskModCapability.addCapability(CapabilityRegister.iManaLevel.capability, () -> manaLevel);
                        IUp iUp = duskModCapability.addCapability(CapabilityRegister.iUp.capability, new Up());
                        IManaHandle iManaHandle = duskModCapability.addCapability(CapabilityRegister.iManaHandle.capability, new ManaHandle(2560L * manaLevel.level, 2L * manaLevel.level, iUp));
                        BlockEntity blockEntity = event.getObject();
                        iUp.addUpBlack(() -> up(blockEntity, iManaHandle, manaLevel));
                    }
                };
            }

            /***
             * up回调
             * @param blockEntity 方块实体
             * @param iManaHandle 灵气处理
             * @param manaLevel 等级
             */
            public abstract void up(BlockEntity blockEntity, IManaHandle iManaHandle, ManaLevel manaLevel);

            public static class SimilarSolarEnergyMechanic extends PassiveProductionMechanic {
                public final long productionMultiple;
                public final Extension.Func_1I<Level, Boolean> isTimePass;

                public SimilarSolarEnergyMechanic(ResourceLocation name, long productionMultiple, Extension.Func_1I<Level, Boolean> isTimePass) {
                    super(name);
                    this.productionMultiple = productionMultiple;
                    this.isTimePass = isTimePass;
                }

                public SimilarSolarEnergyMechanic(String name, long productionMultiple, Extension.Func_1I<Level, Boolean> isTimePass) {
                    this(new ResourceLocation(Dusk.MOD_ID, name), productionMultiple, isTimePass);
                }

                @Override
                public void up(BlockEntity blockEntity, IManaHandle iManaHandle, ManaLevel manaLevel) {
                    Level level = blockEntity.getLevel();
                    if (level == null || level.isClientSide) {
                        return;
                    }
                    if (!isTimePass.func(level)) {
                        return;
                    }
                    BlockPos blockPos = blockEntity.getBlockPos();
                    for (int i = blockPos.getY() + 1; i < 255; i++) {
                        if (!level.getBlockState(new BlockPos(blockPos.getX(), i, blockPos.getZ())).getBlock().equals(Blocks.AIR)) {
                            return;
                        }
                    }
                    iManaHandle.addMana(manaLevel.level * productionMultiple);
                }
            }
        }
    }
}
