package com.til.dusk.common.register.ore.block.blocks;

import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.data.tag.BlockTag;
import com.til.dusk.common.register.ore.block.OreBlockMetal;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.ore.ore.OreConfig;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import org.jetbrains.annotations.Nullable;

/**
 * @author til
 */
public class CoilOreBlockMetal extends OreBlockMetal {

    public CoilOreBlockMetal(){
        super("coil");
    }

    @Override
    public @Nullable Block createBlock(Ore ore) {
        Block block = new Block(BlockBehaviour.Properties.of(Material.STONE)
                .strength(1.3f * ore.getConfig(OreConfig.STRENGTH).floatValue(), 2.6f * ore.getConfig(OreConfig.STRENGTH).floatValue())
                .requiresCorrectToolForDrops()
                .sound(SoundType.STONE));
        BlockTag.addTag(BlockTags.MINEABLE_WITH_PICKAXE, block);
        BlockTag.addTag(BlockTags.NEEDS_STONE_TOOL, block);
        return block;
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "线圈");
        lang.add(LangType.EN_CH, "Coil");
    }

}
