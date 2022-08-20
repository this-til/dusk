package com.til.dusk.common.register.ore;

import com.til.dusk.Dusk;
import com.til.dusk.common.data.TagAdd;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public abstract class OreBlock extends Ore.OreType<OreBlock, BlockItem> {

    public static Supplier<IForgeRegistry<OreBlock>> ORE_BLOCK;

    public static OreBlock lordWorld;
    public static OreBlock lordWorldDeepslate;
    public static OreBlock lordWorldSand;
    public static OreBlock lordWorldDirt;
    public static OreBlock lordWorldGravel;
    public static OreBlock netherWorldNetherrack;
    public static OreBlock endWorldEndStone;

    public static OreBlock block;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        ORE_BLOCK = event.create(new RegistryBuilder<OreBlock>().setName(new ResourceLocation(Dusk.MOD_ID, "ore_block")));
        lordWorld = new OreBlock("lord_world") {
            @Override
            public Block createBlock(Ore ore) {
                Block block = new Block(BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.DIRT)
                        .strength(ore.strength, 2f * ore.strength)
                        .requiresCorrectToolForDrops()
                        .sound(SoundType.STONE));
                //Objects.requireNonNull(ForgeRegistries.BLOCKS.tags()).addOptionalTagDefaults(BlockTags.MINEABLE_WITH_PICKAXE, Set.of(() -> block));
                //Objects.requireNonNull(ForgeRegistries.BLOCKS.tags()).addOptionalTagDefaults(BlockTags.NEEDS_STONE_TOOL, Set.of(() -> block));
                TagAdd.addTag(ForgeRegistries.BLOCKS, BlockTags.MINEABLE_WITH_PICKAXE, block);
                TagAdd.addTag(ForgeRegistries.BLOCKS, BlockTags.NEEDS_STONE_TOOL, block);
                return block;
            }
        };
        lordWorldDeepslate = new OreBlock("lord_world_deepslate") {
            @Override
            public Block createBlock(Ore ore) {
                Block block = new Block(BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.DIRT)
                        .strength(1.5f * ore.strength, 3f * ore.strength)
                        .requiresCorrectToolForDrops()
                        .sound(SoundType.STONE));
                //Objects.requireNonNull(ForgeRegistries.BLOCKS.tags()).addOptionalTagDefaults(BlockTags.MINEABLE_WITH_PICKAXE, Set.of(() -> block));
                //Objects.requireNonNull(ForgeRegistries.BLOCKS.tags()).addOptionalTagDefaults(BlockTags.NEEDS_STONE_TOOL, Set.of(() -> block));
                TagAdd.addTag(ForgeRegistries.BLOCKS, BlockTags.MINEABLE_WITH_PICKAXE, block);
                TagAdd.addTag(ForgeRegistries.BLOCKS, BlockTags.NEEDS_STONE_TOOL, block);
                return block;
            }
        };
        lordWorldSand = new OreBlock("lord_world_sand") {
            @Override
            public Block createBlock(Ore ore) {
                Block block = new Block(BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.DIRT)
                        .strength(0.5f * ore.strength, ore.strength)
                        .requiresCorrectToolForDrops()
                        .sound(SoundType.GRAVEL));
                //Objects.requireNonNull(ForgeRegistries.BLOCKS.tags()).addOptionalTagDefaults(BlockTags.MINEABLE_WITH_SHOVEL, Set.of(() -> block));
                //Objects.requireNonNull(ForgeRegistries.BLOCKS.tags()).addOptionalTagDefaults(BlockTags.NEEDS_STONE_TOOL, Set.of(() -> block));
                TagAdd.addTag(ForgeRegistries.BLOCKS, BlockTags.MINEABLE_WITH_SHOVEL, block);
                TagAdd.addTag(ForgeRegistries.BLOCKS, BlockTags.NEEDS_STONE_TOOL, block);
                return block;
            }
        };

        lordWorldDirt = new OreBlock("lord_world_dirt") {
            @Override
            public Block createBlock(Ore ore) {
                Block block = new Block(BlockBehaviour.Properties.of(Material.SAND, MaterialColor.SAND)
                        .strength(0.5f * ore.strength, ore.strength)
                        .requiresCorrectToolForDrops()
                        .sound(SoundType.GRAVEL));
                //Objects.requireNonNull(ForgeRegistries.BLOCKS.tags()).addOptionalTagDefaults(BlockTags.MINEABLE_WITH_SHOVEL, Set.of(() -> block));
                //Objects.requireNonNull(ForgeRegistries.BLOCKS.tags()).addOptionalTagDefaults(BlockTags.NEEDS_STONE_TOOL, Set.of(() -> block));
                TagAdd.addTag(ForgeRegistries.BLOCKS, BlockTags.MINEABLE_WITH_SHOVEL, block);
                TagAdd.addTag(ForgeRegistries.BLOCKS, BlockTags.NEEDS_STONE_TOOL, block);
                return block;
            }
        };
        lordWorldGravel = new OreBlock("lord_world_gravel") {
            @Override
            public Block createBlock(Ore ore) {
                Block block = new Block(BlockBehaviour.Properties.of(Material.SAND, MaterialColor.STONE)
                        .strength(0.5f * ore.strength, ore.strength)
                        .requiresCorrectToolForDrops()
                        .sound(SoundType.GRAVEL));
                //Objects.requireNonNull(ForgeRegistries.BLOCKS.tags()).addOptionalTagDefaults(BlockTags.MINEABLE_WITH_SHOVEL, Set.of(() -> block));
                //Objects.requireNonNull(ForgeRegistries.BLOCKS.tags()).addOptionalTagDefaults(BlockTags.NEEDS_STONE_TOOL, Set.of(() -> block));
                TagAdd.addTag(ForgeRegistries.BLOCKS, BlockTags.MINEABLE_WITH_SHOVEL, block);
                TagAdd.addTag(ForgeRegistries.BLOCKS, BlockTags.NEEDS_STONE_TOOL, block);
                return block;
            }
        };

        netherWorldNetherrack = new OreBlock("nether_world_netherrack") {
            @Override
            public Block createBlock(Ore ore) {
                Block block = new Block(BlockBehaviour.Properties.of(Material.SAND, MaterialColor.STONE)
                        .strength(0.75f * ore.strength, 1.25f * ore.strength)
                        .requiresCorrectToolForDrops()
                        .sound(SoundType.STONE));
                //Objects.requireNonNull(ForgeRegistries.BLOCKS.tags()).addOptionalTagDefaults(BlockTags.MINEABLE_WITH_PICKAXE, Set.of(() -> block));
                //Objects.requireNonNull(ForgeRegistries.BLOCKS.tags()).addOptionalTagDefaults(BlockTags.NEEDS_STONE_TOOL, Set.of(() -> block));
                TagAdd.addTag(ForgeRegistries.BLOCKS, BlockTags.MINEABLE_WITH_SHOVEL, block);
                TagAdd.addTag(ForgeRegistries.BLOCKS, BlockTags.NEEDS_STONE_TOOL, block);
                return block;
            }
        };
        endWorldEndStone = new OreBlock("end_world_end_stone") {
            @Override
            public Block createBlock(Ore ore) {
                Block block = new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.SAND)
                        .strength(0.6f * ore.strength, 1.2f * ore.strength)
                        .requiresCorrectToolForDrops()
                        .sound(SoundType.STONE));
                //Objects.requireNonNull(ForgeRegistries.BLOCKS.tags()).addOptionalTagDefaults(BlockTags.MINEABLE_WITH_PICKAXE, Set.of(() -> block));
                //Objects.requireNonNull(ForgeRegistries.BLOCKS.tags()).addOptionalTagDefaults(BlockTags.NEEDS_STONE_TOOL, Set.of(() -> block));
                TagAdd.addTag(ForgeRegistries.BLOCKS, BlockTags.MINEABLE_WITH_PICKAXE, block);
                TagAdd.addTag(ForgeRegistries.BLOCKS, BlockTags.NEEDS_STONE_TOOL, block);
                return block;
            }
        };
        block = new OreBlock("block") {
            @Override
            public Block createBlock(Ore ore) {
                Block block = new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL)
                        .strength(0.6f * ore.strength, 1.2f * ore.strength)
                        .requiresCorrectToolForDrops()
                        .sound(SoundType.STONE));
                //Objects.requireNonNull(ForgeRegistries.BLOCKS.tags()).addOptionalTagDefaults(BlockTags.MINEABLE_WITH_PICKAXE, Set.of(() -> block));
                //Objects.requireNonNull(ForgeRegistries.BLOCKS.tags()).addOptionalTagDefaults(BlockTags.NEEDS_STONE_TOOL, Set.of(() -> block));
                TagAdd.addTag(ForgeRegistries.BLOCKS, BlockTags.MINEABLE_WITH_PICKAXE, block);
                TagAdd.addTag(ForgeRegistries.BLOCKS, BlockTags.NEEDS_STONE_TOOL, block);
                return block;
            }
        };
    }

    public OreBlock(ResourceLocation name) {
        super(name, ORE_BLOCK);
    }

    public OreBlock(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    @Override
    public BlockItem create(Ore ore) {
        Block block = createBlock(ore);
        ForgeRegistries.BLOCKS.register(fuseName("_", ore, this), block);
        Objects.requireNonNull(ForgeRegistries.BLOCKS.tags()).addOptionalTagDefaults(BlockTags.create(fuseName("_", ore, this)), Set.of(() -> block));

        BlockItem blockItem = createBlockItem(ore, block);
        ForgeRegistries.ITEMS.register(fuseName("_", ore, this), blockItem);
        Objects.requireNonNull(ForgeRegistries.ITEMS.tags()).addOptionalTagDefaults(ItemTags.create(fuseName("_", ore, this)), Set.of(() -> blockItem));

        return blockItem;
    }

    public abstract Block createBlock(Ore ore);

    public BlockItem createBlockItem(Ore ore, Block block) {
        return new BlockItem(block, new Item.Properties().tab(Dusk.TAB));

    }


/*    public static class OreBlockTableProvider extends LootTableProvider {
        public OreBlockTableProvider(DataGenerator p_124437_) {
            super(p_124437_);
        }

        @Override
        protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
            var list = new ArrayList<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>>();
            list.add(new Pair<>(OreBlockLoot::new, LootContextParamSets.BLOCK));
            return list;
        }


    }*/

/*    public static class OreBlockLoot extends BlockLoot {
        @Override
        public void accept(BiConsumer<ResourceLocation, LootTable.Builder> biConsumer) {
            Ore.ORE.get().forEach(o -> o.blockMap.values().forEach(b -> biConsumer.accept(ForgeRegistries.BLOCKS.getKey(b.getBlock()), createSingleItemTable(b.getBlock()))));
        }
    }*/

/*    @SubscribeEvent
    public static void gatherDataEvent(GatherDataEvent event) {
        event.getGenerator().addProvider(true, new OreBlockTableProvider(event.getGenerator()));
    }*/


}
