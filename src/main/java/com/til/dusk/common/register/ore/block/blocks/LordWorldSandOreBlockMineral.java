package com.til.dusk.common.register.ore.block.blocks;

import com.til.dusk.common.data.tag.BlockTag;
import com.til.dusk.common.register.ore.block.OreBlockMineral;
import com.til.dusk.common.register.ore.ore.Ore;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

/**
 * @author til
 */
public class LordWorldSandOreBlockMineral extends OreBlockMineral {

    public LordWorldSandOreBlockMineral() {
        super("lord_world_sand");
    }

    @Override
    public Block createBlock(Ore ore) {
        Block block = new Block(BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.DIRT)
                .strength(0.5f * ore.getConfig(Ore.STRENGTH).floatValue(), ore.getConfig(Ore.STRENGTH).floatValue())
                .requiresCorrectToolForDrops()
                .sound(SoundType.GRAVEL));
        BlockTag.addTag(BlockTags.MINEABLE_WITH_SHOVEL, block);
        BlockTag.addTag(BlockTags.NEEDS_STONE_TOOL, block);
        return block;
    }
}
