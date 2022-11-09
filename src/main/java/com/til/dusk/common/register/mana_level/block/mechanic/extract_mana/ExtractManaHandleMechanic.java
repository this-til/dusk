package com.til.dusk.common.register.mana_level.block.mechanic.extract_mana;

import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.common.data.ModRecipeProvider;
import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.mana_level.block.HandleMechanic;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.ShapedRecipeBuilder;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author til
 */
public class ExtractManaHandleMechanic extends HandleMechanic {

    public ExtractManaHandleMechanic() {
        super("extract_mana");

    }

    @Override
    public void registerRecipe(Consumer<RecipeBuilder> recipeConsumer) {
        for (ManaLevel manaLevel : ManaLevel.MANA_LEVEL.get()) {
            if (!manaLevel.canUseRecipeMake) {
                continue;
            }
            recipeConsumer.accept(ShapedRecipeBuilder.shaped(manaLevel.get(this).blockItem(), 1)
                    .define('A', manaLevel.acceptableTagPack.getTagPack(OreItem.foil).itemTagKey())
                    .define('B', manaLevel.acceptableTagPack.getTagPack(OreItem.casing).itemTagKey())
                    .define('C', manaLevel.get(frameBasic).blockItemTag())
                    .define('D', manaLevel.acceptableTagPack.getTagPack(OreItem.wrench).itemTagKey())
                    .pattern("BDB")
                    .pattern("ACA")
                    .pattern("BAB")
                    .unlockedBy("has_casing",
                            ModRecipeProvider.has(manaLevel.acceptableTagPack.getTagPack(OreItem.casing).itemTagKey())));
        }
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "灵气提取晶体");
        lang.add(LangType.EN_CH, "Extract Mana Crystal");
    }

    @Override
    public void defaultConfig() {shapedTypeList = List.of(ShapedType.extractMana);
        manaLevelMakeData = new ManaLevelMakeData()
                .addOreConfig(List.of(
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(frameBasic.name, 1),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(OreItem.casing.name, 4),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(OreItem.foil.name, 4)));
    }
}
