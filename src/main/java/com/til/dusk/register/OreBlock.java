package com.til.dusk.register;

import com.til.dusk.Dusk;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
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
                return new Block(BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.DIRT)
                        .strength(ore.strength, ore.strength)
                        .requiresCorrectToolForDrops());
            }
        };
        lordWorldSand = new OreBlock("lord_world_sand") {
            @Override
            public Block createBlock(Ore ore) {
                return new Block(BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.DIRT)
                        .strength(0.5f * ore.strength, ore.strength)
                        .requiresCorrectToolForDrops());
            }
        };
        lordWorldDirt = new OreBlock("lord_world_dirt") {
            @Override
            public Block createBlock(Ore ore) {
                return new Block(BlockBehaviour.Properties.of(Material.SAND, MaterialColor.SAND)
                        .strength(0.5f * ore.strength, ore.strength)
                        .requiresCorrectToolForDrops());
            }
        };
        lordWorldGravel = new OreBlock("lord_world_gravel") {
            @Override
            public Block createBlock(Ore ore) {
                return new Block(BlockBehaviour.Properties.of(Material.SAND, MaterialColor.STONE)
                        .strength(ore.strength, 2 * ore.strength)
                        .requiresCorrectToolForDrops());
            }
        };
        netherWorldNetherrack = new OreBlock("nether_world_netherrack") {
            @Override
            public Block createBlock(Ore ore) {
                return new Block(BlockBehaviour.Properties.of(Material.SAND, MaterialColor.STONE)
                        .strength(0.75f * ore.strength, 1.25f * ore.strength)
                        .requiresCorrectToolForDrops());
            }
        };
        endWorldEndStone = new OreBlock("end_world_end_stone") {
            @Override
            public Block createBlock(Ore ore) {
                return new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.SAND)
                        .strength(0.6f * ore.strength, 1.2f * ore.strength)
                        .requiresCorrectToolForDrops());
            }
        };
        block = new OreBlock("block") {
            @Override
            public Block createBlock(Ore ore) {
                return new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL)
                        .strength(0.6f * ore.strength, 1.2f * ore.strength)
                        .requiresCorrectToolForDrops());
            }
        };
    }

    public OreBlock(ResourceLocation name) {
        super(name, ORE_BLOCK);
    }

    public OreBlock(String name) {
        super(new ResourceLocation(Dusk.MOD_ID, name), ORE_BLOCK);
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

}
