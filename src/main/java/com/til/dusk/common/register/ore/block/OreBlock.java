package com.til.dusk.common.register.ore.block;

import com.til.dusk.Dusk;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.data.ModRecipeProvider;
import com.til.dusk.common.register.BlockUnitRegister;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.ore.ore.OreConfig;
import com.til.dusk.common.world.block.ModBlock;
import com.til.dusk.util.GenericMap;
import com.til.dusk.util.Lang;
import com.til.dusk.common.data.tag.BlockTag;
import com.til.dusk.util.pack.BlockPack;
import com.til.dusk.util.prefab.JsonPrefab;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public abstract class OreBlock extends BlockUnitRegister<OreBlock, Ore> {
    public static Supplier<IForgeRegistry<OreBlock>> ORE_BLOCK;

    public static OreBlockMineral lordWorld;
    public static OreBlockMineral lordWorldDeepslate;
    public static OreBlockMineral lordWorldSand;
    public static OreBlockMineral lordWorldDirt;
    public static OreBlockMineral lordWorldGravel;
    public static OreBlockMineral netherWorldNetherrack;
    public static OreBlockMineral endWorldEndStone;

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
    public static OreBlockDecorate block;

    /***
     * 半砖
     */
    public static OreBlockDecorate slab;

    /***
     * 楼梯
     */
    public static OreBlockDecorate stairs;

    /***
     * 墙
     */
    public static OreBlockDecorate wall;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        ORE_BLOCK = event.create(new RegistryBuilder<OreBlock>().setName(new ResourceLocation(Dusk.MOD_ID, "ore_block")));
        lordWorld = new OreBlockMineral("lord_world") {
            @Override
            public Block createBlock(Ore ore) {
                Block block = new Block(BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.DIRT)
                        .strength(ore.getConfig(OreConfig.STRENGTH).floatValue(),  2f * ore.getConfig(OreConfig.STRENGTH).floatValue())
                        .requiresCorrectToolForDrops()
                        .sound(SoundType.STONE));
                BlockTag.addTag(BlockTags.MINEABLE_WITH_PICKAXE, block);
                BlockTag.addTag(BlockTags.NEEDS_STONE_TOOL, block);
                return block;
            }
        };
        lordWorldDeepslate = new OreBlockMineral("lord_world_deepslate") {
            @Override
            public Block createBlock(Ore ore) {
                Block block = new Block(BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.DIRT)
                        .strength(1.5f * ore.getConfig(OreConfig.STRENGTH).floatValue(), 3f * ore.getConfig(OreConfig.STRENGTH).floatValue())
                        .requiresCorrectToolForDrops()
                        .sound(SoundType.STONE));
                BlockTag.addTag(BlockTags.MINEABLE_WITH_PICKAXE, block);
                BlockTag.addTag(BlockTags.NEEDS_STONE_TOOL, block);
                return block;
            }
        };
        lordWorldSand = new OreBlockMineral("lord_world_sand") {
            @Override
            public Block createBlock(Ore ore) {
                Block block = new Block(BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.DIRT)
                        .strength(0.5f * ore.getConfig(OreConfig.STRENGTH).floatValue(), ore.getConfig(OreConfig.STRENGTH).floatValue())
                        .requiresCorrectToolForDrops()
                        .sound(SoundType.GRAVEL));
                BlockTag.addTag(BlockTags.MINEABLE_WITH_SHOVEL, block);
                BlockTag.addTag(BlockTags.NEEDS_STONE_TOOL, block);
                return block;
            }
        };

        lordWorldDirt = new OreBlockMineral("lord_world_dirt") {
            @Override
            public Block createBlock(Ore ore) {
                Block block = new Block(BlockBehaviour.Properties.of(Material.SAND, MaterialColor.SAND)
                        .strength(0.5f * ore.getConfig(OreConfig.STRENGTH).floatValue(), ore.getConfig(OreConfig.STRENGTH).floatValue())
                        .requiresCorrectToolForDrops()
                        .sound(SoundType.GRAVEL));
                BlockTag.addTag(BlockTags.MINEABLE_WITH_SHOVEL, block);
                BlockTag.addTag(BlockTags.NEEDS_STONE_TOOL, block);
                return block;
            }
        };
        lordWorldGravel = new OreBlockMineral("lord_world_gravel") {
            @Override
            public Block createBlock(Ore ore) {
                Block block = new Block(BlockBehaviour.Properties.of(Material.SAND, MaterialColor.STONE)
                        .strength(0.5f * ore.getConfig(OreConfig.STRENGTH).floatValue(), ore.getConfig(OreConfig.STRENGTH).floatValue())
                        .requiresCorrectToolForDrops()
                        .sound(SoundType.GRAVEL));
                BlockTag.addTag(BlockTags.MINEABLE_WITH_SHOVEL, block);
                BlockTag.addTag(BlockTags.NEEDS_STONE_TOOL, block);
                return block;
            }
        };

        netherWorldNetherrack = new OreBlockMineral("nether_world_netherrack") {
            @Override
            public Block createBlock(Ore ore) {
                Block block = new Block(BlockBehaviour.Properties.of(Material.SAND, MaterialColor.STONE)
                        .strength(0.75f * ore.getConfig(OreConfig.STRENGTH).floatValue(), 1.25f * ore.getConfig(OreConfig.STRENGTH).floatValue())
                        .requiresCorrectToolForDrops()
                        .sound(SoundType.STONE));
                BlockTag.addTag(BlockTags.MINEABLE_WITH_SHOVEL, block);
                BlockTag.addTag(BlockTags.NEEDS_STONE_TOOL, block);
                return block;
            }
        };
        endWorldEndStone = new OreBlockMineral("end_world_end_stone") {
            @Override
            public Block createBlock(Ore ore) {
                Block block = new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.SAND)
                        .strength(0.6f * ore.getConfig(OreConfig.STRENGTH).floatValue(), 1.2f * ore.getConfig(OreConfig.STRENGTH).floatValue())
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
                        .strength(1.2f * ore.getConfig(OreConfig.STRENGTH).floatValue(), 2.4f * ore.getConfig(OreConfig.STRENGTH).floatValue())
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
                itemColorPack.addColor(0, itemStack -> ore.getConfig(OreConfig.COLOR));
            }

            @Override
            public void dyeBlack(Ore ore, ColorProxy.BlockColorPack itemColorPack) {
                itemColorPack.addColor(0, (blockState, blockAndTintGetter, blockPos) -> ore.getConfig(OreConfig.COLOR));
            }
        }.addRecipes(list -> {
            for (Ore ore : Ore.screen(Ore.IS_METAL)) {
                if (!ore.manaLevel.hasSet(ManaLevel.CAN_UET_TOOL_MAKE)) {
                    continue;
                }
                list.add(ShapedRecipeBuilder.shaped(ore.get(bracket).blockItem(), 1)
                        .define('A', ore.get(OreItem.casing).itemTag())
                        .define('B', ore.get(OreItem.stick).itemTag())
                        .define('C', ore.manaLevel.getAcceptableTagPack(OreItem.wrench).itemTagKey())
                        .pattern("BAB")
                        .pattern("ACA")
                        .pattern("BAB")
                        .unlockedBy("has_casing",
                                ModRecipeProvider.has(ore.get(OreItem.casing).itemTag())));
            }

        });
        coil = new OreBlockDecorate("coil") {
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
        block = new OreBlockDecorate("block") {
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
        slab = new OreBlockDecorate("slab") {
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
        stairs = new OreBlockDecorate("stairs") {
            @Override
            public @Nullable Block createBlock(Ore ore) {
                StairBlock block = new StairBlock(() -> ore.get(OreBlock.block).block().defaultBlockState(), BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL)
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
        wall = new OreBlockDecorate("wall") {
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

    public static final GenericMap.IKey<Void> IS_MINERAL = new GenericMap.IKey.Key<>();
    public static final GenericMap.IKey<Void> IS_DECORATE = new GenericMap.IKey.Key<>();

}
