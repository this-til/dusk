package com.til.dusk.common.register.mana_level;

import com.til.dusk.Dusk;
import com.til.dusk.common.capability.fluid_handler.VoidTankFluidHandler;
import com.til.dusk.common.capability.item_handler.VoidCaseItemHandler;
import com.til.dusk.common.capability.mana_handle.IManaHandle;
import com.til.dusk.common.capability.mana_handle.ManaHandle;
import com.til.dusk.common.register.CapabilityRegister;
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
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
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
        sunlight = new Mechanic.PassiveProductionMechanic.SimilarSolarEnergyMechanic("sunlight", 1) {
            @Override
            public boolean isTimePass(Level level, BlockEntity blockEntity, IManaHandle iManaHandle, ManaLevel manaLevel) {
                return level.isDay();
            }
        };
        moonlight = new Mechanic.PassiveProductionMechanic.SimilarSolarEnergyMechanic("moonlight", 1) {
            @Override
            public boolean isTimePass(Level level, BlockEntity blockEntity, IManaHandle iManaHandle, ManaLevel manaLevel) {
                return level.isNight();
            }
        };
        grind = new Mechanic.HandleMechanic("grind") {
            @Override
            public List<ShapedType> getShapedTypeList() {
                return List.of(ShapedType.grind);
            }
        };
        wash = new Mechanic.HandleMechanic("wash") {
            @Override
            public List<ShapedType> getShapedTypeList() {
                return List.of(ShapedType.wash);
            }
        };
        centrifugal = new Mechanic.HandleMechanic("centrifugal") {
            @Override
            public List<ShapedType> getShapedTypeList() {
                return List.of(ShapedType.centrifugal);
            }
        };
        pack = new Mechanic.HandleMechanic("pack") {
            @Override
            public List<ShapedType> getShapedTypeList() {
                return List.of(ShapedType.pack);
            }
        };
        unpack = new Mechanic.HandleMechanic("unpack") {
            @Override
            public List<ShapedType> getShapedTypeList() {
                return List.of(ShapedType.unpack);
            }
        };
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
                        duskModCapability.addCapability(CapabilityRegister.iManaHandle.capability, new ManaHandle(512000L * manaLevel.level, 128L * manaLevel.level));
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

    public static abstract class Mechanic extends ManaLevelBlock {
        public Mechanic(ResourceLocation name) {
            super(name);
        }

        public Mechanic(String name) {
            super(name);
        }

        public static abstract class HandleMechanic extends Mechanic {

            public HandleMechanic(ResourceLocation name) {
                super(name);
            }

            public HandleMechanic(String name) {
                super(name);
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
                IControl iControl = duskModCapability.addCapability(CapabilityRegister.iControl.capability, new Control(event.getObject(),
                        List.of(BindType.manaIn, BindType.manaOut, BindType.itemIn, BindType.itemOut, BindType.fluidIn, BindType.fluidOut, BindType.modelStore)
                        , iManaLevel));
                IUp iUp = duskModCapability.addCapability(CapabilityRegister.iUp.capability, new Up());
                IClock iClock = duskModCapability.addCapability(CapabilityRegister.iClock.capability, new Clock(iUp, iManaLevel));
                IHandle iHandle = duskModCapability.addCapability(CapabilityRegister.iHandle.capability, new Handle(event.getObject(), getShapedTypeList(), iControl, iClock, iUp, iManaLevel));
            }

            /***
             * 返回可接受的配方
             * @return 接受的配方
             */
            public abstract List<ShapedType> getShapedTypeList();


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

            public static abstract class SimilarSolarEnergyMechanic extends PassiveProductionMechanic {
                public final long productionMultiple;

                public SimilarSolarEnergyMechanic(ResourceLocation name, long productionMultiple) {
                    super(name);
                    this.productionMultiple = productionMultiple;
                }

                public SimilarSolarEnergyMechanic(String name, long productionMultiple) {
                    this(new ResourceLocation(Dusk.MOD_ID, name), productionMultiple);
                }

                @Override
                public void up(BlockEntity blockEntity, IManaHandle iManaHandle, ManaLevel manaLevel) {
                    Level level = blockEntity.getLevel();
                    if (level == null || level.isClientSide) {
                        return;
                    }
                    if (!level.isDay()) {
                        return;
                    }
                    if (!isTimePass(level, blockEntity, iManaHandle, manaLevel)) {
                        return;
                    }
                    BlockPos blockPos = blockEntity.getBlockPos();
                    for (int i = blockPos.getY() + 1; i < 255; i++) {
                        if (level.getBlockState(new BlockPos(blockPos.getY(), i, blockPos.getZ())).canOcclude()) {
                            return;
                        }
                    }
                    iManaHandle.addMana(manaLevel.level * productionMultiple);
                }

                /***
                 * 再时间上通过
                 * @param level 世界
                 * @param blockEntity 方块类型
                 * @param iManaHandle 灵气处理
                 * @param manaLevel 等级
                 * @return 时候通过
                 */
                public abstract boolean isTimePass(Level level, BlockEntity blockEntity, IManaHandle iManaHandle, ManaLevel manaLevel);
            }
        }

    }

}
