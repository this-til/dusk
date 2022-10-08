package com.til.dusk.common.register.ore.block.blocks;

import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.data.tag.BlockTag;
import com.til.dusk.common.register.ore.block.OreBlockDecorate;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.world.block.ModBlock;
import com.til.dusk.util.prefab.JsonPrefab;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import org.jetbrains.annotations.Nullable;

/**
 * @author til
 */
public class SlabOreBlockDecorate extends OreBlockDecorate {

    public SlabOreBlockDecorate(){
        super("slab");
    }
    @Override
    public @Nullable Block createBlock(Ore ore) {
        Block block = new SlabBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL)
                .strength(0.3f * ore.getConfig(Ore.STRENGTH).floatValue(), 0.6f * ore.getConfig(Ore.STRENGTH).floatValue())
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

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN,"半砖");
        lang.add(LangType.EN_CH, "Slab");
    }
}
