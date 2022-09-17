package com.til.dusk.common.register;

import com.til.dusk.Dusk;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.data.tag.BlockTag;
import com.til.dusk.common.data.tag.FluidTag;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.world.block.ModBlock;
import com.til.dusk.common.world.item.ModItem;
import com.til.dusk.util.*;
import com.til.dusk.util.pack.*;
import com.til.dusk.util.prefab.JsonPrefab;
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
import net.minecraft.world.level.material.*;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegisterEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Supplier;


/**
 * @author til
 */
public abstract class RegisterBasics<T extends RegisterBasics<?>> {

    /***
     * 注册项的名称
     */
    public final ResourceLocation name;

    /***
     * 注册项的注册表
     */
    protected final Supplier<IForgeRegistry<T>> registrySupplier;

    /***
     * 注册项是数据表
     */
    protected final GenericMap setMap = new GenericMap();

    /***
     * 时候注册
     */
    protected boolean isRegister;

    /***
     * 时候注册回调
     */
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
                for (Object value : setMap.values()) {
                    if (value instanceof IBackRun iBackRun) {
                        iBackRun.backRun();
                    }
                }
                registerSubsidiaryBlack();
            } catch (Exception assertionError) {
                Dusk.instance.logger.error(String.format("注册项目[%s]出错", name), assertionError);
            }
        }
    }

    public <V> T setSet(GenericMap.IKey<V> key, V v) {
        setMap.set(key, v);
        return Util.forcedConversion(this);
    }

    public <V> V getSet(GenericMap.IKey<V> key) {
        return setMap.get(key);
    }

    public boolean hasSet(GenericMap.IKey<?> key) {
        return setMap.containsKey(key);
    }

    /***
     * 注册回调的触发事件
     */
    public void registerSubsidiaryBlack() {
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

        protected TagPack tagPack;

        public ItemUnitRegister(ResourceLocation name, Supplier<IForgeRegistry<T>> iForgeRegistrySupplier) {
            super(name, iForgeRegistrySupplier);
        }

        @Nullable
        public ItemPack create(O o) {
            Item item = createItem(o);
            return new ItemPack(item, createItemTag(o));
        }

        public TagKey<Item> createItemTag(O o) {
            return ItemTags.create(fuseName("/", new String[]{"item", o.name.getPath(), name.getPath()}));
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
        public ModItem.ICustomModel getItemMoldMapping(O o) {
            return () -> name;
        }

        /***
         * 染色回调
         */
        public abstract void dyeBlack(O o, ColorProxy.ItemColorPack itemColorPack);

        public TagPack getTagPack() {
            if (tagPack == null) {
                tagPack = new TagPack(Dusk.instance.ITEM_TAG.createTagKey(name), Dusk.instance.BLOCK_TAG.createTagKey(name), Dusk.instance.FLUID_TAG.createTagKey(name));
            }
            return tagPack;
        }
    }

    public static abstract class BlockUnitRegister<T extends BlockUnitRegister<T, O>, O extends RegisterBasics<?>> extends RegisterBasics<T> {
        protected TagPack tagPack;

        public BlockUnitRegister(ResourceLocation name, Supplier<IForgeRegistry<T>> iForgeRegistrySupplier) {
            super(name, iForgeRegistrySupplier);
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
            return BlockTags.create(fuseName("/", new String[]{"item", o.name.getPath(), name.getPath()}));
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
            return ItemTags.create(fuseName("/", new String[]{"item", o.name.getPath(), name.getPath()}));
        }


        /***
         * 获取模型映射
         */
        public ModBlock.ICustomModel getBlockModelMapping(O o) {
            return () -> name;
        }

        /***
         * 染色回调
         */
        public abstract void dyeBlack(O o, ColorProxy.ItemColorPack itemColorPack);

        /***
         * 染色回调
         */
        public abstract void dyeBlack(O o, ColorProxy.BlockColorPack itemColorPack);

        public TagPack getTagPack() {
            if (tagPack == null) {
                tagPack = new TagPack(Dusk.instance.ITEM_TAG.createTagKey(name), Dusk.instance.BLOCK_TAG.createTagKey(name), Dusk.instance.FLUID_TAG.createTagKey(name));
            }
            return tagPack;
        }
    }

    public static abstract class FluidUnitRegister<T extends FluidUnitRegister<T, O>, O extends RegisterBasics<?>> extends RegisterBasics<T> {

        public final Map<O, Fluid> sourceMap = new HashMap<>();
        public final Map<O, Fluid> flowingMap = new HashMap<>();

        protected TagPack tagPack;

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

        public abstract FluidType createFluidType(O ore);

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
            return FluidTags.create(fuseName("/", new String[]{"fluid", o.name.getPath(), name.getPath()}));
        }

        @Nullable
        public LiquidBlock createLiquidBlock(O o, FlowingFluid source) {
            return new LiquidBlock(() -> source, BlockBehaviour.Properties.of(Material.WATER).noCollission().strength(100.0F).noLootTable());
        }

        @Nullable
        public TagKey<Block> createBlockTag(O o) {
            return BlockTags.create(fuseName("/", new String[]{"fluid", o.name.getPath(), name.getPath()}));
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
            return ItemTags.create(fuseName("/", new String[]{"fluid", o.name.getPath(), name.getPath()}));
        }

        public TagPack getTagPack() {
            if (tagPack == null) {
                tagPack = new TagPack(Dusk.instance.ITEM_TAG.createTagKey(name), Dusk.instance.BLOCK_TAG.createTagKey(name), Dusk.instance.FLUID_TAG.createTagKey(name));
            }
            return tagPack;
        }
    }

    public static abstract class UnitRegister<T extends UnitRegister<T, ITEM, BLOCK, FLUID>,
            ITEM extends ItemUnitRegister<?, T>,
            BLOCK extends BlockUnitRegister<?, T>,
            FLUID extends FluidUnitRegister<?, T>> extends RegisterBasics<T> {

        public final Map<ITEM, ItemPack> itemMap = new HashMap<>();
        public final Map<BLOCK, BlockPack> blockMap = new HashMap<>();
        public final Map<FLUID, FluidPack> fluidMap = new HashMap<>();

        protected TagPack tagPack;

        public UnitRegister(ResourceLocation name, Supplier<IForgeRegistry<T>> iForgeRegistrySupplier) {
            super(name, iForgeRegistrySupplier);
        }

        @Override
        public void registerSubsidiaryBlack() {
            for (ITEM item : getCellRegistry().item().get()) {
                ItemPack itemPack = item.create(Util.forcedConversion(this));
                if (itemPack == null) {
                    continue;
                }
                itemMap.put(item, itemPack);
                registerItem(item, itemPack);
            }
            for (BLOCK block : getCellRegistry().block().get()) {
                BlockPack blockPack = block.create(Util.forcedConversion(this));
                if (blockPack == null) {
                    continue;
                }
                blockMap.put(block, blockPack);
                registerBlock(block, blockPack);
            }
            for (FLUID fluid : getCellRegistry().fluid().get()) {
                FluidPack fluidPack = fluid.create(Util.forcedConversion(this));
                if (fluidPack == null) {
                    continue;
                }
                fluidMap.put(fluid, fluidPack);
                registerFluid(fluid, fluidPack);
            }
        }

        public void registerItem(ITEM item, ItemPack itemPack) {
            ForgeRegistries.ITEMS.register(fuseName("/", new String[]{"item", name.getPath(), item.name.getPath()}), itemPack.item());
            ItemTag.addTag(itemPack.itemTag(), itemPack.item());
            ItemTag.addTag(Dusk.instance.MOD_ITEM, itemPack.item());
            ItemTag.addTag(getTagPack().itemTagKey(), itemPack.item());
            ItemTag.addTag(item.getTagPack().itemTagKey(), itemPack.item());
        }

        public void registerBlock(BLOCK block, BlockPack blockPack) {
            ForgeRegistries.BLOCKS.register(fuseName("/", new String[]{"block", name.getPath(), block.name.getPath()}), blockPack.block());
            BlockTag.addTag(blockPack.blockTag(), blockPack.block());
            BlockTag.addTag(Dusk.instance.MOD_BLOCK, blockPack.block());
            BlockTag.addTag(getTagPack().blockTagKey(), blockPack.block());
            BlockTag.addTag(block.getTagPack().blockTagKey(), blockPack.block());
            ForgeRegistries.ITEMS.register(fuseName("/", new String[]{"block", name.getPath(), block.name.getPath()}), blockPack.blockItem());
            ItemTag.addTag(blockPack.blockItemTag(), blockPack.blockItem());
            ItemTag.addTag(Dusk.instance.MOD_ITEM, blockPack.blockItem());
            ItemTag.addTag(getTagPack().itemTagKey(), blockPack.blockItem());
            ItemTag.addTag(block.getTagPack().itemTagKey(), blockPack.blockItem());
        }

        public void registerFluid(FLUID fluid, FluidPack fluidPack) {
            ForgeRegistries.FLUID_TYPES.get().register(fuseName(this, fluid), fluidPack.fluidType());
            ForgeRegistries.FLUIDS.register(fuseName("_", new String[]{name.getPath(), fluid.name.getPath(), "source"}), fluidPack.source());
            ForgeRegistries.FLUIDS.register(fuseName("_", new String[]{name.getPath(), fluid.name.getPath(), "flowing"}), fluidPack.flowing());
            FluidTag.addTag(fluidPack.fluidTag(), fluidPack.source());
            FluidTag.addTag(fluidPack.fluidTag(), fluidPack.flowing());
            FluidTag.addTag(Dusk.instance.MOD_FLUID, fluidPack.source());
            FluidTag.addTag(Dusk.instance.MOD_FLUID, fluidPack.flowing());
            FluidTag.addTag(getTagPack().fluidTagKey(), fluidPack.source());
            FluidTag.addTag(getTagPack().fluidTagKey(), fluidPack.flowing());
            FluidTag.addTag(fluid.getTagPack().fluidTagKey(), fluidPack.source());
            FluidTag.addTag(fluid.getTagPack().fluidTagKey(), fluidPack.flowing());
            if (fluidPack.liquidBlock() != null) {
                ForgeRegistries.BLOCKS.register(fuseName("/", new String[]{"fluid", name.getPath(), fluid.name.getPath()}), fluidPack.liquidBlock());
                if (fluidPack.liquidBlockTag() != null) {
                    BlockTag.addTag(fluidPack.liquidBlockTag(), fluidPack.liquidBlock());
                    BlockTag.addTag(Dusk.instance.MOD_BLOCK, fluidPack.liquidBlock());
                    BlockTag.addTag(getTagPack().blockTagKey(), fluidPack.liquidBlock());
                    BlockTag.addTag(fluid.getTagPack().blockTagKey(), fluidPack.liquidBlock());
                }
            }
            if (fluidPack.bucketItem() != null) {
                ForgeRegistries.ITEMS.register(fuseName("/", new String[]{"fluid", name.getPath(), fluid.name.getPath()}), fluidPack.bucketItem());
                if (fluidPack.bucketItemTag() != null) {
                    ItemTag.addTag(fluidPack.bucketItemTag(), fluidPack.bucketItem());
                    ItemTag.addTag(Dusk.instance.MOD_ITEM, fluidPack.bucketItem());
                    ItemTag.addTag(getTagPack().itemTagKey(), fluidPack.bucketItem());
                    ItemTag.addTag(fluid.getTagPack().itemTagKey(), fluidPack.bucketItem());
                }
            }
        }

        public abstract RegistryPack<T, ITEM, BLOCK, FLUID> getCellRegistry();

        public TagPack getTagPack() {
            if (tagPack == null) {
                tagPack = new TagPack(Dusk.instance.ITEM_TAG.createTagKey(name), Dusk.instance.BLOCK_TAG.createTagKey(name), Dusk.instance.FLUID_TAG.createTagKey(name));
            }
            return tagPack;
        }
    }

}
