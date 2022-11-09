package com.til.dusk.common.register.ore.block.blocks;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.data.tag.BlockTag;
import com.til.dusk.common.register.ore.block.OreBlock;
import com.til.dusk.common.register.ore.block.OreBlockDecorate;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.world.block.DuskBlock;
import com.til.dusk.util.ModelJsonUtil;
import com.til.dusk.util.gson.ConfigGson;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import org.jetbrains.annotations.Nullable;

import java.text.MessageFormat;

/**
 * @author til
 */
public class StairsOreBlockDecorate extends OreBlockDecorate {

    public StairsOreBlockDecorate(){
        super("stairs");
    }
    @Override
    public @Nullable Block createBlock(Ore ore) {
        StairBlock block = new StairBlock(() -> ore.get(OreBlock.block).block().defaultBlockState(), BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL)
                .strength((float) (strengthBasics * ore.strength), (float) (explosionProofBasics * ore.strength))
                .requiresCorrectToolForDrops()
                .sound(SoundType.STONE));
        BlockTag.addTag(BlockTags.MINEABLE_WITH_PICKAXE, block);
        BlockTag.addTag(BlockTags.NEEDS_IRON_TOOL, block);
        return block;
    }

    @Override
    public JsonObject createBlockModel(Block block, Ore ore) {
        return GsonHelper.parse(MessageFormat.format(ModelJsonUtil.BRICK_STAIRS, name.getNamespace(), name.getPath()));
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN,"楼梯");
        lang.add(LangType.EN_CH, "Stairs");
    }

    @Override
    public void defaultConfig() {
        strength(0.3);
    }
}
