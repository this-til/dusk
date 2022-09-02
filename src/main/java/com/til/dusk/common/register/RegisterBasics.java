package com.til.dusk.common.register;

import com.til.dusk.Dusk;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.data.tag.BlockTag;
import com.til.dusk.common.data.tag.FluidTag;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.util.Extension;
import com.til.dusk.util.Lang;
import com.til.dusk.util.StaticTag;
import com.til.dusk.util.Util;
import com.til.dusk.util.pack.BlockPack;
import com.til.dusk.util.pack.FluidPack;
import com.til.dusk.util.pack.ItemPack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.*;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.tags.ITagManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Supplier;

/**
 * @author til
 */
public abstract class RegisterBasics<T extends RegisterBasics<?>> {

    public final ResourceLocation name;
    public final Supplier<IForgeRegistry<T>> registrySupplier;
    public List<StaticTag> staticTagList = new ArrayList<>();

    protected boolean isRegister;
    protected boolean isRegisterSubsidiary;

    public RegisterBasics(ResourceLocation name, Supplier<IForgeRegistry<T>> registrySupplier) {
        this.name = name;
        this.registrySupplier = registrySupplier;
        Dusk.instance.modEventBus.addListener(EventPriority.HIGH, this::registerEvent);
        Dusk.instance.modEventBus.addListener(getRegisterBlackPriority(), this::registerSubsidiary);
    }

    public void registerEvent(RegisterEvent event) {
        if (!isRegister) {
            isRegister = true;
            registrySupplier.get().register(name, Util.forcedConversion(this));
        }
    }

    /***
     * 注册回调
     * @param event 注册事件
     */
    public void registerSubsidiary(RegisterEvent event) {
        if (!isRegisterSubsidiary) {
            isRegisterSubsidiary = true;
            try {
                registerSubsidiaryBlack();
            } catch (AssertionError assertionError) {
                Dusk.instance.logger.error(String.format("注册项目[%s]出错", name), assertionError);
            }
        }
    }

    public T setTag(Extension.Action_1V<List<StaticTag>> action) {
        action.action(staticTagList);
        return Util.forcedConversion(this);
    }

    public T removeTag(StaticTag staticTag) {
        staticTagList.remove(staticTag);
        return Util.forcedConversion(this);
    }

    public T removeTag(StaticTag... staticTags) {
        for (StaticTag staticTag : staticTags) {
            removeTag(staticTag);
        }
        return Util.forcedConversion(this);
    }

    public T addTag(StaticTag staticTag) {
        if (!staticTagList.contains(staticTag)) {
            staticTagList.add(staticTag);
            for (StaticTag tag : staticTag.father) {
                addTag(tag);
            }
        }
        return Util.forcedConversion(this);
    }

    public T addTag(StaticTag... staticTags) {
        for (StaticTag staticTag : staticTags) {
            addTag(staticTag);
        }
        return Util.forcedConversion(this);
    }

    public boolean hasTag(StaticTag staticTag) {
        if (staticTagList.contains(staticTag)) {
            return hasTag(staticTag.father);
        }
        return false;
    }

    public boolean hasTag(List<StaticTag> staticTags) {
        for (StaticTag staticTag : staticTags) {
            if (!hasTag(staticTag)) {
                return false;
            }
        }
        return true;
    }

    public boolean hasTag(StaticTag... staticTags) {
        for (StaticTag staticTag : staticTags) {
            if (!hasTag(staticTag)) {
                return false;
            }
        }
        return true;
    }

    /***
     * 注册回调的触发事件
     */
    public void registerSubsidiaryBlack() throws AssertionError {
    }

    /***
     * 返回注册回调的触发时间
     * @return 时间
     */
    public EventPriority getRegisterBlackPriority() {
        return EventPriority.LOW;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof RegisterBasics registerBasics) {
            return name.equals(registerBasics.name);
        }
        return false;
    }

    public String getLangKey() {
        return name.getPath();
    }

    @Override
    public String toString() {
        return name.toString();
    }

    public static ResourceLocation fuseName(String splicing, String[] strings) {
        return new ResourceLocation(Dusk.MOD_ID, String.join(splicing, Arrays.asList(strings)));
    }

    public static ResourceLocation fuseName(String splicing, RegisterBasics<?>... registerBasics) {
        String[] strings = new String[registerBasics.length];
        for (int i = 0; i < registerBasics.length; i++) {
            strings[i] = registerBasics[i].name.getPath();
        }
        return fuseName(splicing, strings);
    }

    public static ResourceLocation fuseName(RegisterBasics<?>... registerBasics) {
        return fuseName("_", registerBasics);
    }

    public static ResourceLocation fuseName(ResourceLocation... name) {
        return fuseName("_", name);
    }

    public static ResourceLocation fuseName(String splicing, ResourceLocation... name) {
        String[] stringArrayList = new String[name.length];
        for (int i = 0; i < name.length; i++) {
            stringArrayList[i] = name[i].getPath();
        }
        return fuseName(splicing, stringArrayList);
    }

    public static abstract class ItemUnitRegister<T extends ItemUnitRegister<T, O>, O extends RegisterBasics<?>> extends RegisterBasics<T> {

        public ItemUnitRegister(ResourceLocation name, Supplier<IForgeRegistry<T>> iForgeRegistrySupplier) {
            super(name, iForgeRegistrySupplier);
        }

        @Nullable
        public ItemPack create(O o) {
            Item item = createItem(o);
            return new ItemPack(item, createItemTag(o));
        }

        public TagKey<Item> createItemTag(O o) {
            return ItemTags.create(fuseName("/", o, this));
        }

        public Item createItem(O o) {
            return new Item(new Item.Properties().tab(Dusk.TAB)) {
                @Override
                public @NotNull Component getName(@NotNull ItemStack stack) {
                    return Lang.getLang(o, ItemUnitRegister.this);
                }
            };
        }


        /***
         * 获取方块物品模型映射
         */
        public ResourceLocation getItemMoldMapping(O o) {
            return name;
        }

        /***
         * 染色回调
         */
        public abstract void dyeBlack(O o, ColorProxy.ItemColorPack itemColorPack);
    }

    public static abstract class BlockUnitRegister<T extends BlockUnitRegister<T, O>, O extends RegisterBasics<?>> extends RegisterBasics<T> {
        public BlockUnitRegister(ResourceLocation name, Supplier<IForgeRegistry<T>> iForgeRegistrySupplier) {
            super(name, iForgeRegistrySupplier);
        }

        @Override
        public void registerSubsidiaryBlack() {
            Block block = createCamouflageBlock();
            if (block != null) {
                ForgeRegistries.BLOCKS.register(name, block);
            }
        }

        @Nullable
        public BlockPack create(O o) {
            Block block = createBlock(o);
            if (block == null) {
                return null;
            }
            BlockItem blockItem = createBlockItem(o, block);
            return new BlockPack(block, createBlockTag(o), blockItem, createBlockItemTag(o));
        }

        @Nullable
        public abstract Block createBlock(O o);

        public TagKey<Block> createBlockTag(O o) {
            return BlockTags.create(fuseName("/", o, this));
        }

        public BlockItem createBlockItem(O o, Block block) {
            return new BlockItem(block, new Item.Properties().tab(Dusk.TAB)) {
                @Override
                public @NotNull Component getName(@NotNull ItemStack stack) {
                    return Lang.getLang(o, BlockUnitRegister.this);
                }
            };
        }

        public TagKey<Item> createBlockItemTag(O o) {
            return ItemTags.create(fuseName("/", o, this));
        }

        /***
         * 创建一个假方块并且注册，用去欺骗mc去加载模型
         */
        @Nullable
        public Block createCamouflageBlock() {
            return null;
        }

        /***
         * 获取模型映射
         */
        public ResourceLocation getBlockModelMapping(O o) {
            return name;
        }

        /***
         * 获取方块物品模型映射
         */
        public ResourceLocation getBlockItemMoldMapping(O o) {
            return getBlockModelMapping(o);
        }

        /***
         * 染色回调
         */
        public abstract void dyeBlack(O o, ColorProxy.ItemColorPack itemColorPack);

        /***
         * 染色回调
         */
        public abstract void dyeBlack(O o, ColorProxy.BlockColorPack itemColorPack);
    }

    public static abstract class FluidUnitRegister<T extends FluidUnitRegister<T, O>, O extends RegisterBasics<?>> extends RegisterBasics<T> {

        public final Map<O, Fluid> sourceMap = new HashMap<>();
        public final Map<O, Fluid> flowingMap = new HashMap<>();


        public FluidUnitRegister(ResourceLocation name, Supplier<IForgeRegistry<T>> iForgeRegistrySupplier) {
            super(name, iForgeRegistrySupplier);
        }

        public FluidPack create(O o) {
            FluidType fluidType = createFluidType(o);
            ForgeFlowingFluid.Properties properties = createProperties(o, fluidType);
            FlowingFluid source = createSourceFluid(o, properties);
            sourceMap.put(o, source);
            FlowingFluid flowing = createFlowingFluid(o, properties);
            flowingMap.put(o, flowing);
            LiquidBlock liquidBlock = createLiquidBlock(o, source);
            if (liquidBlock != null) {
                properties.block(() -> liquidBlock);
            }
            BucketItem item = createBanner(o, source);
            if (item != null) {
                properties.bucket(() -> item);
            }
            return new FluidPack(fluidType, source, flowing, createFluidTag(o), liquidBlock, createBlockTag(o), item, createBlockItemTag(o));
        }

        public FluidType createFluidType(O ore) {
            return new FluidType(FluidType.Properties.create());
        }

        public ForgeFlowingFluid.Properties createProperties(O ore, FluidType fluidType) {
            return new ForgeFlowingFluid.Properties(() -> fluidType, () -> sourceMap.get(ore), () -> flowingMap.get(ore));
        }

        public FlowingFluid createFlowingFluid(O o, ForgeFlowingFluid.Properties properties) {
            return new ForgeFlowingFluid.Flowing(properties);
        }

        public FlowingFluid createSourceFluid(O o, ForgeFlowingFluid.Properties properties) {
            return new ForgeFlowingFluid.Source(properties);
        }

        public TagKey<Fluid> createFluidTag(O o) {
            return FluidTags.create(fuseName("/", o, this));
        }

        @Nullable
        public LiquidBlock createLiquidBlock(O o, FlowingFluid source) {
            return new LiquidBlock(() -> source, BlockBehaviour.Properties.of(Material.WATER).noCollission());
        }

        @Nullable
        public TagKey<Block> createBlockTag(O o) {
            return BlockTags.create(fuseName("/", o, this));
        }

        @Nullable
        public BucketItem createBanner(O o, FlowingFluid source) {
            return new BucketItem(() -> source, new Item.Properties().tab(Dusk.TAB)) {
                @Override
                public @NotNull Component getName(@NotNull ItemStack stack) {
                    return Lang.getLang(o, FluidUnitRegister.this);
                }
            };
        }

        @Nullable
        public TagKey<Item> createBlockItemTag(O o) {
            return ItemTags.create(fuseName("/", o, this));
        }

        @Override
        public void registerSubsidiaryBlack() throws AssertionError {
            Block block = new Block(BlockBehaviour.Properties.of(Material.WATER)) {
                @Override
                protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> blockBlockStateBuilder) {
                    blockBlockStateBuilder.add(BlockStateProperties.LEVEL);
                }
            };
            ForgeRegistries.BLOCKS.register(name, block);
        }
    }

    public static abstract class UnitRegister<T extends UnitRegister<T, ITEM, BLOCK, FLUID>,
            ITEM extends ItemUnitRegister<?, T>,
            BLOCK extends BlockUnitRegister<?, T>,
            FLUID extends FluidUnitRegister<?, T>> extends RegisterBasics<T> {

        public final Map<ITEM, ItemPack> itemMap = new HashMap<>();
        public final Map<BLOCK, BlockPack> blockMap = new HashMap<>();
        public final Map<FLUID, FluidPack> fluidMap = new HashMap<>();

        public TagKey<Item> itemTagKey;
        public TagKey<Block> blockTagKey;
        public TagKey<Fluid> fluidTagKey;

        public UnitRegister(ResourceLocation name, Supplier<IForgeRegistry<T>> iForgeRegistrySupplier) {
            super(name, iForgeRegistrySupplier);
        }

        @Override
        public void registerSubsidiaryBlack() throws AssertionError {
            itemTagKey = Dusk.instance.ITEM_TAG.createTagKey(name);
            blockTagKey = Dusk.instance.BLOCK_TAG.createTagKey(name);
            fluidTagKey = Dusk.instance.FLUID_TAG.createTagKey(name);
            for (ITEM item : itemRegistry().get()) {
                ItemPack itemPack = item.create(Util.forcedConversion(this));
                if (itemPack == null) {
                    continue;
                }
                itemMap.put(item, itemPack);
                ForgeRegistries.ITEMS.register(fuseName(this, item), itemPack.item());
                ItemTag.addTag(itemPack.itemTag(), itemPack.item());
                ItemTag.addTag(Dusk.instance.MOD_ITEM, itemPack.item());
                ItemTag.addTag(itemTagKey, itemPack.item());
            }
            for (BLOCK block : blockRegistry().get()) {
                BlockPack blockPack = block.create(Util.forcedConversion(this));
                if (blockPack == null) {
                    continue;
                }
                blockMap.put(block, blockPack);
                ForgeRegistries.BLOCKS.register(fuseName(this, block), blockPack.block());
                BlockTag.addTag(blockPack.blockTag(), blockPack.block());
                BlockTag.addTag(Dusk.instance.MOD_BLOCK, blockPack.block());
                BlockTag.addTag(blockTagKey, blockPack.block());
                ForgeRegistries.ITEMS.register(fuseName(this, block), blockPack.blockItem());
                ItemTag.addTag(blockPack.blockItemTag(), blockPack.blockItem());
                ItemTag.addTag(Dusk.instance.MOD_ITEM, blockPack.blockItem());
                ItemTag.addTag(itemTagKey, blockPack.blockItem());
            }
            for (FLUID fluid : fluidRegistry().get()) {
                FluidPack fluidPack = fluid.create(Util.forcedConversion(this));
                if (fluidPack == null) {
                    continue;
                }
                fluidMap.put(fluid, fluidPack);
                ForgeRegistries.FLUIDS.register(fuseName("_", new String[]{name.getPath(), fluid.name.getPath(), "source"}), fluidPack.source());
                ForgeRegistries.FLUIDS.register(fuseName("_", new String[]{name.getPath(), fluid.name.getPath(), "flowing"}), fluidPack.flowing());
                FluidTag.addTag(fluidPack.fluidTag(), fluidPack.source());
                FluidTag.addTag(fluidPack.fluidTag(), fluidPack.flowing());
                FluidTag.addTag(Dusk.instance.MOD_FLUID, fluidPack.source());
                FluidTag.addTag(Dusk.instance.MOD_FLUID, fluidPack.flowing());
                FluidTag.addTag(fluidTagKey, fluidPack.source());
                FluidTag.addTag(fluidTagKey, fluidPack.flowing());
                if (fluidPack.liquidBlock() != null) {
                    ForgeRegistries.BLOCKS.register(fuseName(this, fluid), fluidPack.liquidBlock());
                    if (fluidPack.liquidBlockTag() != null) {
                        BlockTag.addTag(fluidPack.liquidBlockTag(), fluidPack.liquidBlock());
                        BlockTag.addTag(Dusk.instance.MOD_BLOCK, fluidPack.liquidBlock());
                        BlockTag.addTag(blockTagKey, fluidPack.liquidBlock());
                    }
                }
                if (fluidPack.bucketItem() != null) {
                    ForgeRegistries.ITEMS.register(fuseName(this, fluid), fluidPack.bucketItem());
                    if (fluidPack.bucketItemTag() != null) {
                        ItemTag.addTag(fluidPack.bucketItemTag(), fluidPack.bucketItem());
                        ItemTag.addTag(Dusk.instance.MOD_ITEM, fluidPack.bucketItem());
                        ItemTag.addTag(itemTagKey, fluidPack.bucketItem());
                    }
                }

            }
        }

        public abstract Supplier<IForgeRegistry<ITEM>> itemRegistry();

        public abstract Supplier<IForgeRegistry<BLOCK>> blockRegistry();

        public abstract Supplier<IForgeRegistry<FLUID>> fluidRegistry();
    }

}
