package com.til.dusk.common.register.ore;

import com.mojang.serialization.Codec;
import com.til.dusk.Dusk;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.common.world.block.ModBlock;
import com.til.dusk.util.Extension;
import com.til.dusk.util.Lang;
import com.til.dusk.common.data.tag.BlockTag;
import com.til.dusk.util.pack.BlockPack;
import com.til.dusk.util.prefab.JsonPrefab;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public abstract class OreBlock extends RegisterBasics.BlockUnitRegister<OreBlock, Ore> {
    public static Supplier<IForgeRegistry<OreBlock>> ORE_BLOCK;

    public static MineralOreBlock lordWorld;
    public static MineralOreBlock lordWorldDeepslate;
    public static MineralOreBlock lordWorldSand;
    public static MineralOreBlock lordWorldDirt;
    public static MineralOreBlock lordWorldGravel;
    public static MineralOreBlock netherWorldNetherrack;
    public static MineralOreBlock endWorldEndStone;

    /***
     * 支架
     */
    public static OreBlock bracket;

    /***
     * 线圈
     */
    public static OreBlock coil;

    /***
     * 快
     */
    public static DecorateOreBlock block;

    /***
     * 半砖
     */
    public static DecorateOreBlock slab;

    /***
     * 楼梯
     */
    public static DecorateOreBlock stairs;
    /***
     * 墙
     */
    public static DecorateOreBlock wall;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        ORE_BLOCK = event.create(new RegistryBuilder<OreBlock>().setName(new ResourceLocation(Dusk.MOD_ID, "ore_block")));
        lordWorld = new MineralOreBlock("lord_world") {
            @Override
            public Block createBlock(Ore ore) {
                Block block = new Block(BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.DIRT)
                        .strength((float) ore.strength, (float) (2f * ore.strength))
                        .requiresCorrectToolForDrops()
                        .sound(SoundType.STONE));
                BlockTag.addTag(BlockTags.MINEABLE_WITH_PICKAXE, block);
                BlockTag.addTag(BlockTags.NEEDS_STONE_TOOL, block);
                return block;
            }
        };
        lordWorldDeepslate = new MineralOreBlock("lord_world_deepslate") {
            @Override
            public Block createBlock(Ore ore) {
                Block block = new Block(BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.DIRT)
                        .strength((float) (1.5f * ore.strength), (float) (3f * ore.strength))
                        .requiresCorrectToolForDrops()
                        .sound(SoundType.STONE));
                BlockTag.addTag(BlockTags.MINEABLE_WITH_PICKAXE, block);
                BlockTag.addTag(BlockTags.NEEDS_STONE_TOOL, block);
                return block;
            }
        };
        lordWorldSand = new MineralOreBlock("lord_world_sand") {
            @Override
            public Block createBlock(Ore ore) {
                Block block = new Block(BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.DIRT)
                        .strength((float) (0.5f * ore.strength), (float) ore.strength)
                        .requiresCorrectToolForDrops()
                        .sound(SoundType.GRAVEL));
                BlockTag.addTag(BlockTags.MINEABLE_WITH_SHOVEL, block);
                BlockTag.addTag(BlockTags.NEEDS_STONE_TOOL, block);
                return block;
            }
        };

        lordWorldDirt = new MineralOreBlock("lord_world_dirt") {
            @Override
            public Block createBlock(Ore ore) {
                Block block = new Block(BlockBehaviour.Properties.of(Material.SAND, MaterialColor.SAND)
                        .strength((float) (0.5f * ore.strength), (float) ore.strength)
                        .requiresCorrectToolForDrops()
                        .sound(SoundType.GRAVEL));
                BlockTag.addTag(BlockTags.MINEABLE_WITH_SHOVEL, block);
                BlockTag.addTag(BlockTags.NEEDS_STONE_TOOL, block);
                return block;
            }
        };
        lordWorldGravel = new MineralOreBlock("lord_world_gravel") {
            @Override
            public Block createBlock(Ore ore) {
                Block block = new Block(BlockBehaviour.Properties.of(Material.SAND, MaterialColor.STONE)
                        .strength((float) (0.5f * ore.strength), (float) ore.strength)
                        .requiresCorrectToolForDrops()
                        .sound(SoundType.GRAVEL));
                BlockTag.addTag(BlockTags.MINEABLE_WITH_SHOVEL, block);
                BlockTag.addTag(BlockTags.NEEDS_STONE_TOOL, block);
                return block;
            }
        };

        netherWorldNetherrack = new MineralOreBlock("nether_world_netherrack") {
            @Override
            public Block createBlock(Ore ore) {
                Block block = new Block(BlockBehaviour.Properties.of(Material.SAND, MaterialColor.STONE)
                        .strength((float) (0.75f * ore.strength), (float) (1.25f * ore.strength))
                        .requiresCorrectToolForDrops()
                        .sound(SoundType.STONE));
                BlockTag.addTag(BlockTags.MINEABLE_WITH_SHOVEL, block);
                BlockTag.addTag(BlockTags.NEEDS_STONE_TOOL, block);
                return block;
            }
        };
        endWorldEndStone = new MineralOreBlock("end_world_end_stone") {
            @Override
            public Block createBlock(Ore ore) {
                Block block = new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.SAND)
                        .strength((float) (0.6f * ore.strength), (float) (1.2f * ore.strength))
                        .requiresCorrectToolForDrops()
                        .sound(SoundType.STONE));
                BlockTag.addTag(BlockTags.MINEABLE_WITH_PICKAXE, block);
                BlockTag.addTag(BlockTags.NEEDS_STONE_TOOL, block);
                return block;
            }
        };
        bracket = new OreBlock("bracket") {

            @Override
            public @Nullable BlockPack create(Ore ore) {
                if (ore.hasSet(Ore.IS_METAL)) {
                    return super.create(ore);
                }
                return null;
            }

            @Override
            public @Nullable Block createBlock(Ore ore) {
                Block block = new Block(BlockBehaviour.Properties.of(Material.GLASS)
                        .strength((float) (1.2f * ore.strength), (float) (2.4f * ore.strength))
                        .requiresCorrectToolForDrops()
                        .sound(SoundType.GLASS)
                        .noCollission()
                        .noOcclusion());
                BlockTag.addTag(BlockTags.MINEABLE_WITH_PICKAXE, block);
                BlockTag.addTag(BlockTags.NEEDS_STONE_TOOL, block);
                return block;
            }

            @Override
            public void dyeBlack(Ore ore, ColorProxy.ItemColorPack itemColorPack) {
                itemColorPack.addColor(0, itemStack -> ore.color);
            }

            @Override
            public void dyeBlack(Ore ore, ColorProxy.BlockColorPack itemColorPack) {
                itemColorPack.addColor(0, (blockState, blockAndTintGetter, blockPos) -> ore.color);
            }
        };
        coil = new DecorateOreBlock("coil") {
            @Override
            public @Nullable BlockPack create(Ore ore) {
                if (ore.hasSet(Ore.IS_METAL)) {
                    return super.create(ore);
                }
                return null;
            }

            @Override
            public @Nullable Block createBlock(Ore ore) {
                Block block = new Block(BlockBehaviour.Properties.of(Material.STONE)
                        .strength((float) (1.3f * ore.strength), (float) (2.6f * ore.strength))
                        .requiresCorrectToolForDrops()
                        .sound(SoundType.STONE));
                BlockTag.addTag(BlockTags.MINEABLE_WITH_PICKAXE, block);
                BlockTag.addTag(BlockTags.NEEDS_STONE_TOOL, block);
                return block;
            }
        };
        block = new DecorateOreBlock("block") {
            @Override
            public Block createBlock(Ore ore) {
                Block block = new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL)
                        .strength((float) (0.6f * ore.strength), (float) (1.2f * ore.strength))
                        .requiresCorrectToolForDrops()
                        .sound(SoundType.STONE));
                BlockTag.addTag(BlockTags.MINEABLE_WITH_PICKAXE, block);
                BlockTag.addTag(BlockTags.NEEDS_IRON_TOOL, block);
                return block;
            }
        };
        slab = new DecorateOreBlock("slab") {
            @Override
            public @Nullable Block createBlock(Ore ore) {
                Block block = new SlabBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL)
                        .strength((float) (0.3f * ore.strength), (float) (0.6f * ore.strength))
                        .requiresCorrectToolForDrops()
                        .sound(SoundType.STONE));
                BlockTag.addTag(BlockTags.MINEABLE_WITH_PICKAXE, block);
                BlockTag.addTag(BlockTags.NEEDS_IRON_TOOL, block);
                return block;
            }

            @Override
            public ModBlock.ICustomModel getBlockModelMapping(Ore ore) {
                return new ModBlock.ICustomModel() {
                    @Override
                    public ResourceLocation blockModelName() {
                        return name;
                    }

                    @Override
                    public String blockJsonBasics() {
                        return JsonPrefab.BRICK_SLAB;
                    }
                };
            }
        };
        stairs = new DecorateOreBlock("stairs") {
            @Override
            public @Nullable Block createBlock(Ore ore) {
                StairBlock block = new StairBlock(() -> ore.blockMap.get(OreBlock.block).block().defaultBlockState(), BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL)
                        .strength((float) (0.3f * ore.strength), (float) (0.6f * ore.strength))
                        .requiresCorrectToolForDrops()
                        .sound(SoundType.STONE));
                BlockTag.addTag(BlockTags.MINEABLE_WITH_PICKAXE, block);
                BlockTag.addTag(BlockTags.NEEDS_IRON_TOOL, block);
                return block;
            }

            @Override
            public ModBlock.ICustomModel getBlockModelMapping(Ore ore) {
                return new ModBlock.ICustomModel() {
                    @Override
                    public ResourceLocation blockModelName() {
                        return name;
                    }

                    @Override
                    public String blockJsonBasics() {
                        return JsonPrefab.BRICK_STAIRS;
                    }
                };
            }
        };
        wall = new DecorateOreBlock("wall") {
            @Override
            public @Nullable Block createBlock(Ore ore) {
                WallBlock block = new WallBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL)
                        .strength((float) (0.3f * ore.strength), (float) (0.6f * ore.strength))
                        .requiresCorrectToolForDrops()
                        .sound(SoundType.STONE));
                BlockTag.addTag(BlockTags.MINEABLE_WITH_PICKAXE, block);
                BlockTag.addTag(BlockTags.NEEDS_IRON_TOOL, block);
                BlockTag.addTag(BlockTags.WALLS, block);
                return block;
            }

            @Override
            public ModBlock.ICustomModel getBlockModelMapping(Ore ore) {
                return new ModBlock.ICustomModel() {
                    @Override
                    public ResourceLocation blockModelName() {
                        return name;
                    }

                    @Override
                    public String blockJsonBasics() {
                        return JsonPrefab.WALL;
                    }
                };
            }
        };
    }

    public OreBlock(ResourceLocation name) {
        super(name, ORE_BLOCK);
    }

    public OreBlock(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    public BlockItem createBlockItem(Ore ore, Block block) {
        return new BlockItem(block, new Item.Properties().tab(Dusk.TAB)) {
            @Override
            public @NotNull Component getName(@NotNull ItemStack stack) {
                return Lang.getLang(ore, OreBlock.this);
            }
        };
    }


    @Override
    public void dyeBlack(Ore ore, ColorProxy.ItemColorPack itemColorPack) {
        itemColorPack.addColor(1, itemStack -> ore.color);
    }

    @Override
    public void dyeBlack(Ore ore, ColorProxy.BlockColorPack itemColorPack) {
        itemColorPack.addColor(1, (blockState, blockAndTintGetter, blockPos) -> ore.color);
    }

    public abstract static class MineralOreBlock extends OreBlock {
        public MineralOreBlock(ResourceLocation name) {
            super(name);
        }

        public MineralOreBlock(String name) {
            this(new ResourceLocation(Dusk.MOD_ID, name));
        }

        @Override
        public @Nullable BlockPack create(Ore ore) {
            if (ore.hasSet(Ore.MINERAL_BLOCK_DATA)) {
                return super.create(ore);
            }
            return null;
        }
    }

    public static class MineralBlockData {
        public final Ore ore;

        public MineralBlockData(Ore ore) {
            this.ore = ore;
        }

        @Nullable
        public List<OreGenerateData> oreGenerateDataList;

        public MineralBlockData addOreGenerateData(OreGenerateData oreGenerateData) {
            if (oreGenerateDataList == null) {
                oreGenerateDataList = new ArrayList<>();
            }
            oreGenerateDataList.add(oreGenerateData.setOre(ore).setId(oreGenerateData.amount));
            return this;
        }

        /***
         * 添加通用生成数据
         */
        public MineralBlockData addCurrencyGenerateData(int amount, int inChunkAmount) {
            return addOreGenerateData(new OreGenerateData()
                    .setAmount(amount)
                    .setAmount(inChunkAmount)
                    .setPlace((blockPos, level, blockState) -> {
                        Block inBlock = blockState.getBlock();
                        if (inBlock.equals(Blocks.STONE)) {
                            return ore.blockMap.get(lordWorld).block().defaultBlockState();
                        }
                        if (inBlock.equals(Blocks.DEEPSLATE)) {
                            return ore.blockMap.get(lordWorldDeepslate).block().defaultBlockState();
                        }
                        if (inBlock.equals(Blocks.DIRT)) {
                            return ore.blockMap.get(lordWorldDirt).block().defaultBlockState();
                        }
                        if (inBlock.equals(Blocks.GRAVEL)) {
                            return ore.blockMap.get(lordWorldGravel).block().defaultBlockState();
                        }
                        if (inBlock.equals(Blocks.NETHERITE_BLOCK)) {
                            return ore.blockMap.get(netherWorldNetherrack).block().defaultBlockState();
                        }
                        if (inBlock.equals(Blocks.END_STONE)) {
                            return ore.blockMap.get(endWorldEndStone).block().defaultBlockState();
                        }
                        return null;

                    }));
        }

        @Nullable
        public OreGenerateData getOreGenerateDataByID(int id) {
            if (oreGenerateDataList == null) {
                return null;
            }
            for (OreGenerateData oreGenerateData : oreGenerateDataList) {
                if (oreGenerateData.id == id) {
                    return oreGenerateData;
                }
            }
            return null;
        }

    }

    public static class OreGenerateData {

        public Ore ore;
        public int id = 0;

        /***
         * 数量，单次生成的数量
         */
        public int amount = 12;

        /***
         * 一区块中生产的数量
         */
        public int inChunkAmount = 4;

        /***
         * 最高高度
         */
        public int maxHeight = 128;

        /***
         * 最低高度
         */
        public int minHeight = -64;

        /***
         * 筛选世界
         */
        @Nullable
        public Extension.Func_1I<Level, Boolean> canInLevel;

        /***
         * 可以在某生物群系生成
         */
        @Nullable
        public Extension.Func_1I<Biome, Boolean> canInBiome;

        /***
         * 能够放置在某方块群中
         */
        @Nullable
        public Extension.Func_1I<BlockState, Boolean> canPlace;

        /***
         * 获取放置方块
         */
        public Extension.Func_3I<BlockPos, Level, BlockState, BlockState> place = (blockPos, level, blockState) -> Blocks.AIR.defaultBlockState();

        public OreGenerateData setAmount(int amount) {
            this.amount = amount;
            return this;
        }

        public OreGenerateData setInChunkAmount(int inChunkAmount) {
            this.inChunkAmount = inChunkAmount;
            return this;
        }

        public OreGenerateData setMaxHeight(int maxHeight) {
            this.maxHeight = maxHeight;
            return this;
        }

        public OreGenerateData setMinHeight(int minHeight) {
            this.minHeight = minHeight;
            return this;
        }

        public OreGenerateData setCanInLevel(Extension.Func_1I<Level, Boolean> canInLevel) {
            this.canInLevel = canInLevel;
            return this;
        }

        public OreGenerateData setCanInBiome(Extension.Func_1I<Biome, Boolean> canInBiome) {
            this.canInBiome = canInBiome;
            return this;
        }

        public OreGenerateData setCanPlace(Extension.Func_1I<BlockState, Boolean> canPlace) {
            this.canPlace = canPlace;
            return this;
        }

        public OreGenerateData setPlace(Extension.Func_3I<BlockPos, Level, BlockState, BlockState> place) {
            this.place = place;
            return this;
        }

        public OreGenerateData setOre(Ore ore) {
            this.ore = ore;
            return this;
        }

        public OreGenerateData setId(int id) {
            this.id = id;
            return this;
        }
    }

    /***
     * 装饰方块，包含块、楼梯、半砖、墙等
     */
    public abstract static class DecorateOreBlock extends OreBlock {
        public DecorateOreBlock(ResourceLocation name) {
            super(name);
        }

        public DecorateOreBlock(String name) {
            this(new ResourceLocation(Dusk.MOD_ID, name));
        }

        @Override
        public @Nullable BlockPack create(Ore ore) {
            if (ore.hasSet(Ore.DECORATE_BLOCK_DATA)) {
                return super.create(ore);
            }
            return null;
        }
    }

    public static class DecorateBlockData {
        public final Ore ore;

        public DecorateBlockData(Ore ore) {
            this.ore = ore;
        }
    }

}
