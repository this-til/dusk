package com.til.dusk.common.register.ore.block.blocks;

import com.til.dusk.common.data.tag.BlockTag;
import com.til.dusk.common.register.ore.block.OreBlockMineral;
import com.til.dusk.common.register.ore.ore.Ore;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

/**
 * @author til
 */
public class LordWorldDirtOreBlockMineral extends OreBlockMineral {

    public LordWorldDirtOreBlockMineral(){
        super("lord_world_dirt");
        setReplaceBasicsBlock(() -> Blocks.DIRT);
    }

    @Override
    public Block createBlock(Ore ore) {
        Block block = new Block(BlockBehaviour.Properties.of(Material.SAND, MaterialColor.SAND)
                .strength((float) (strengthBasics * ore.strength), (float) (explosionProofBasics * ore.strength))
                .requiresCorrectToolForDrops()
                .sound(SoundType.GRAVEL));
        BlockTag.addTag(BlockTags.MINEABLE_WITH_SHOVEL, block);
        BlockTag.addTag(BlockTags.NEEDS_STONE_TOOL, block);
        return block;
    }
    @Override
    public void defaultConfig() {
        strength(0.5);
    }
}
