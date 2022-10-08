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
public class EndWorldEndStoneOreBlockMineral extends OreBlockMineral {
    public EndWorldEndStoneOreBlockMineral() {
        super("end_world_end_stone");
        setReplaceBasicsBlock(() -> Blocks.END_STONE);
    }

    @Override
    public Block createBlock(Ore ore) {
        Block block = new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.SAND)
                .strength((float) (strengthBasics * ore.strength), (float) (explosionProofBasics * ore.strength))
                .requiresCorrectToolForDrops()
                .sound(SoundType.STONE));
        BlockTag.addTag(BlockTags.MINEABLE_WITH_PICKAXE, block);
        BlockTag.addTag(BlockTags.NEEDS_STONE_TOOL, block);
        return block;
    }
    @Override
    public void defaultConfig() {
        strength(0.6);
    }

}
