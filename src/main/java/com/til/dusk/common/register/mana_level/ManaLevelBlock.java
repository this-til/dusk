package com.til.dusk.common.register.mana_level;

import com.til.dusk.Dusk;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
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
public abstract class ManaLevelBlock extends ManaLevel.ManaLevelType<ManaLevelBlock, BlockItem> {

    public static Supplier<IForgeRegistry<ManaLevelBlock>> LEVEL_BLOCK;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        LEVEL_BLOCK = event.create(new RegistryBuilder<ManaLevelBlock>().setName(new ResourceLocation(Dusk.MOD_ID, "mana_level_block")));
    }

    public ManaLevelBlock(ResourceLocation name) {
        super(name, LEVEL_BLOCK);
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
        return new BlockItem(block, new Item.Properties().tab(Dusk.TAB));
    }

}
