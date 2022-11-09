package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.common.data.ModRecipeProvider;
import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.mana_level.block.DefaultCapacityMechanic;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.block.OreBlock;
import com.til.dusk.common.register.ore.item.OreItem;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.ShapedRecipeBuilder;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author til
 */
public class FrameBasicMechanic extends DefaultCapacityMechanic {
    public FrameBasicMechanic() {
        super("frame_basic");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "基础框架");
        lang.add(LangType.EN_CH, "Frame Basic");
    }


    @Override
    public void registerRecipe(Consumer<RecipeBuilder> recipeConsumer) {
        for (ManaLevel manaLevel : ManaLevel.MANA_LEVEL.get()) {
            if (!manaLevel.canUseRecipeMake) {
                continue;
            }
            recipeConsumer.accept(ShapedRecipeBuilder.shaped(manaLevel.get(this).blockItem(), 1)
                    .define('A', manaLevel.acceptableTagPack.getTagPack(OreBlock.bracket).itemTagKey())
                    .define('B', manaLevel.get(repeater).blockItemTag())
                    .define('D', manaLevel.acceptableTagPack.getTagPack(OreItem.wrench).itemTagKey())
                    .pattern(" D ")
                    .pattern("BAB")
                    .unlockedBy("has_repeater",
                            ModRecipeProvider.has(manaLevel.get(repeater).blockItemTag())));
        }
    }

    @Override
    public void defaultConfig() {
        manaLevelMakeData = new ManaLevelMakeData()
                .addOreConfig(List.of(
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(repeater.name, 2),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(OreBlock.bracket.name, 1)));
    }
}
