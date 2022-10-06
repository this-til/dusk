package com.til.dusk.common.register.ore.item.items;

import com.til.dusk.common.config.ConfigMap;
import com.til.dusk.common.data.ModRecipeProvider;
import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.item.OreItemTool;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.ore.ore.OreConfig;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.ShapedRecipeBuilder;

import java.util.function.Consumer;

/**
 * @author til
 */
public class WireCutterOreItemTool extends OreItemTool {
    public WireCutterOreItemTool(){
        super("wire_cutter");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "剪刀");
        lang.add(LangType.EN_CH,"Wire Cutter");
    }

    @Override
    public ConfigMap defaultConfigMap() {
        return super.defaultConfigMap()
                .setConfig(IS_WIRE_CUTTER);
    }

    @Override
    public void registerRecipe(Consumer<RecipeBuilder> recipeConsumer) {
        super.registerRecipe(recipeConsumer);
        for (Ore ore : Ore.screen(OreConfig.IS_METAL, OreConfig.ToolDataConfig.TOOL_DATA_CONFIG)) {
            recipeConsumer.accept(ShapedRecipeBuilder.shaped(ore.get(wireCutter).item(), 1)
                    .define('A', ore.get(ingot).itemTag())
                    .define('B', ore.get(plate).itemTag())
                    .pattern("B B")
                    .pattern(" B ")
                    .pattern("A A")
                    .unlockedBy("has_ore", ModRecipeProvider.has(ore.get(ingot).itemTag())));
        }
        for (Ore ore : Ore.screen(OreConfig.IS_METAL)) {
            if (!ore.getConfig(OreConfig.MANA_LEVEL).hasConfig(ManaLevel.CAN_UET_TOOL_MAKE)) {
                continue;
            }
            recipeConsumer.accept(ShapedRecipeBuilder.shaped(ore.get(string).item(), 1)
                    .define('A', wireCutter.getTagPack().itemTagKey())
                    .define('B', ore.get(plate).itemTag())
                    .pattern("A")
                    .pattern("B")
                    .unlockedBy("has_wire_cutter", ModRecipeProvider.has(wireCutter.getTagPack().itemTagKey())));
        }
    }
}
