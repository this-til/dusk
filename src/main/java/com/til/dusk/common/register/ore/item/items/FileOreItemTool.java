package com.til.dusk.common.register.ore.item.items;

import com.til.dusk.common.config.ConfigMap;
import com.til.dusk.common.data.ModRecipeProvider;
import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.item.OreItemTool;
import com.til.dusk.common.register.ore.ore.Ore;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.ShapedRecipeBuilder;

import java.util.function.Consumer;

/**
 * @author til
 */
public class FileOreItemTool extends OreItemTool {

    public FileOreItemTool() {
        super("file");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "锉刀");
        lang.add(LangType.EN_CH, "File");
    }

    @Override
    public ConfigMap defaultConfigMap() {
        return super.defaultConfigMap()
                .setConfig(IS_FILE);
    }

    @Override
    public void registerRecipe(Consumer<RecipeBuilder> recipeConsumer) {
        super.registerRecipe(recipeConsumer);
        for (Ore ore : Ore.screen(Ore.IS_METAL, Ore.ToolDataConfig.TOOL_DATA_CONFIG)) {
            recipeConsumer.accept(ShapedRecipeBuilder.shaped(ore.get(file).item(), 1)
                    .define('A', ore.get(plate).itemTag())
                    .define('B', ore.get(casing).itemTag())
                    .pattern("A")
                    .pattern("A")
                    .pattern("B")
                    .unlockedBy("has_ore", ModRecipeProvider.has(ore.get(ingot).itemTag())));
        }
        for (Ore ore : Ore.screen(Ore.IS_METAL)) {
            if (!ore.getConfig(Ore.MANA_LEVEL).hasConfig(ManaLevel.CAN_UET_TOOL_MAKE)) {
                continue;
            }
            recipeConsumer.accept(ShapedRecipeBuilder.shaped(ore.get(stick).item(), 1)
                    .define('A', file.getTagPack().itemTagKey())
                    .define('B', ore.get(ingot).itemTag())
                    .pattern("A")
                    .pattern("B")
                    .unlockedBy("has_file", ModRecipeProvider.has(file.getTagPack().itemTagKey())));
        }
    }
}
