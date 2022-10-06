package com.til.dusk.common.register.ore.block.blocks;

import com.til.dusk.common.data.tag.BlockTag;
import com.til.dusk.common.register.ore.block.OreBlockMineral;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.ore.ore.OreConfig;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

/**
 * @author til
 */
public class NetherWorldNetherrackOreBlockMineral extends OreBlockMineral {

    public NetherWorldNetherrackOreBlockMineral() {
        super("nether_world_netherrack");
    }

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
}
