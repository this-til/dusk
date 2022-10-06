package com.til.dusk.common.register.ore.block.blocks;

import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.data.ModRecipeProvider;
import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.data.tag.BlockTag;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.block.OreBlockMetal;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.ore.ore.OreConfig;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

/**
 * @author til
 */
public class BracketOreBlockMetal extends OreBlockMetal {

    public BracketOreBlockMetal() {
        super("bracket");
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

    @Override
    public void registerRecipe(Consumer<RecipeBuilder> recipeConsumer) {
        for (Ore ore : Ore.screen(OreConfig.IS_METAL)) {
            if (!ore.getConfig(OreConfig.MANA_LEVEL).hasConfig(ManaLevel.CAN_UET_TOOL_MAKE)) {
                continue;
            }
            recipeConsumer.accept(ShapedRecipeBuilder.shaped(ore.get(bracket).blockItem(), 1)
                    .define('A', ore.get(OreItem.casing).itemTag())
                    .define('B', ore.get(OreItem.stick).itemTag())
                    .define('C', ore.getConfig(OreConfig.MANA_LEVEL).getAcceptableTagPack(OreItem.wrench).itemTagKey())
                    .pattern("BAB")
                    .pattern("ACA")
                    .pattern("BAB")
                    .unlockedBy("has_casing",
                            ModRecipeProvider.has(ore.get(OreItem.casing).itemTag())));
        }
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "支架");
        lang.add(LangType.EN_CH, "Bracket");
    }
}
