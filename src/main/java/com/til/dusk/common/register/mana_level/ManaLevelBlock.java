package com.til.dusk.common.register.mana_level;

import com.til.dusk.Dusk;
import com.til.dusk.common.capability.fluid_handler.VoidTankFluidHandler;
import com.til.dusk.common.capability.item_handler.VoidCaseItemHandler;
import com.til.dusk.common.capability.mana_handle.IManaHandle;
import com.til.dusk.common.capability.mana_handle.ManaHandle;
import com.til.dusk.common.capability.tile_entity.RepeaterTileEntity;
import com.til.dusk.common.register.CapabilityRegister;
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
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public abstract class ManaLevelBlock extends ManaLevel.ManaLevelType<ManaLevelBlock, BlockItem> {

    public static Supplier<IForgeRegistry<ManaLevelBlock>> LEVEL_BLOCK;

    //基础
    /***
     * 中继器
     * 在提取相应方块能力
     */
    public static Mechanic repeater;

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


    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        LEVEL_BLOCK = event.create(new RegistryBuilder<ManaLevelBlock>().setName(new ResourceLocation(Dusk.MOD_ID, "mana_level_block")));
        repeater = new Mechanic("repeater") {
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
        };
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
                        duskModCapability.addCapability(CapabilityRegister.iManaHandle.capability, new ManaHandle(5120000L * manaLevel.level, 128L * manaLevel.level));
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

    @Override
    public BlockItem create(ManaLevel manaLevel) {
        Block block = createBlock(manaLevel);
        ForgeRegistries.BLOCKS.register(fuseName("_", manaLevel, this), block);
        Objects.requireNonNull(ForgeRegistries.BLOCKS.tags()).addOptionalTagDefaults(BlockTags.create(fuseName("_", manaLevel, this)), Set.of(() -> block));

        BlockItem blockItem = createBlockItem(manaLevel, block);
        ForgeRegistries.ITEMS.register(fuseName("_", manaLevel, this), blockItem);
        Objects.requireNonNull(ForgeRegistries.ITEMS.tags()).addOptionalTagDefaults(ItemTags.create(fuseName("_", manaLevel, this)), Set.of(() -> blockItem));

        return blockItem;
    }

    public abstract Block createBlock(ManaLevel manaLevel);

    public BlockItem createBlockItem(ManaLevel manaLevel, Block block) {
        return new BlockItem(block, new Item.Properties().tab(Dusk.TAB)) {
            @Override
            public @NotNull Component getName(@NotNull ItemStack stack) {
                return Lang.getLang(manaLevel, ManaLevelBlock.this);
            }
        };
    }

    @Override
    public void registerSubsidiaryBlack() {
        Block block = createCamouflageBlock();
        if (block != null) {
            ForgeRegistries.BLOCKS.register(name, block);
        }
    }

    /***
     * 创建伪装方块，作用在映射带有多个方块状态的模型时欺骗模型加载器
     * @return 伪装方块
     */
    @Nullable
    public Block createCamouflageBlock() {
        return null;
    }

    public static abstract class Mechanic extends ManaLevelBlock {
        public Mechanic(ResourceLocation name) {
            super(name);
        }

        public Mechanic(String name) {
            super(name);
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
                super(name);
            }

            @Override
            public Block createBlock(ManaLevel manaLevel) {
                return new ModBlock.MechanicBlock(manaLevel) {
                    @Override
                    public void add(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability) {
                        duskModCapability.addCapability(CapabilityRegister.iManaLevel.capability, () -> manaLevel);
                        IManaHandle iManaHandle = duskModCapability.addCapability(CapabilityRegister.iManaHandle.capability, new ManaHandle(2560L * manaLevel.level, 2L * manaLevel.level));
                        IUp iUp = duskModCapability.addCapability(CapabilityRegister.iUp.capability, new Up());
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
