package com.til.dusk.common.register.mana_level;

import com.til.dusk.Dusk;
import com.til.dusk.common.register.CapabilityRegister;
import com.til.dusk.util.Lang;
import com.til.dusk.common.capability.clock.Clock;
import com.til.dusk.common.capability.clock.IClock;
import com.til.dusk.common.capability.control.Control;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.handle.Handle;
import com.til.dusk.common.capability.handle.IHandle;
import com.til.dusk.common.capability.mana_level.GetManaLevel;
import com.til.dusk.common.capability.mana_level.IManaLevel;
import com.til.dusk.common.capability.tile_entity.DuskCapabilityProvider;
import com.til.dusk.common.capability.up.IUp;
import com.til.dusk.common.capability.up.Up;
import com.til.dusk.common.register.BindType;
import com.til.dusk.common.register.shaped.ShapedType;
import com.til.dusk.common.world.ModBlock;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
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

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        LEVEL_BLOCK = event.create(new RegistryBuilder<ManaLevelBlock>().setName(new ResourceLocation(Dusk.MOD_ID, "mana_level_block")));
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

        @Override
        public Block createBlock(ManaLevel manaLevel) {

            return new ModBlock.MechanicBlock(manaLevel) {
                @Override
                public void add(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability) {
                    Mechanic.this.add(event, duskModCapability, manaLevel);
                }
            };
        }

        /***
         * 为方块添加能力
         * @param event 事件
         * @param duskModCapability 能力供应
         * @param  manaLevel 方块等级
         */
        public abstract void add(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, ManaLevel manaLevel);

        public static abstract class HandleMechanic extends Mechanic {

            public HandleMechanic(ResourceLocation name) {
                super(name);
            }

            public HandleMechanic(String name) {
                super(name);
            }

            @Override
            public void add(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, ManaLevel manaLevel) {
                IManaLevel iManaLevel = duskModCapability.addCapability(CapabilityRegister.iManaLevel.capability, new GetManaLevel(event.getObject(), manaLevel));
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

    }

}
