package com.til.dusk.common.register.ore.item.items;

import com.til.dusk.common.data.ModRecipeProvider;
import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.item.OreItemMetal;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.ore.ore.OreConfig;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;

/**
 * @author til
 */
public class IngotOreItemMetal extends OreItemMetal {
    public IngotOreItemMetal() {
        super("ingot");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "锭");
        lang.add(LangType.EN_CH, "ingot");
    }

    @Override
    public void registerRecipe(Consumer<RecipeBuilder> recipeConsumer) {
        for (Ore ore : Ore.screen(OreConfig.IS_METAL, OreConfig.MineralBlockConfig.MINERAL_BLOCK_CONFIG)) {
            if (!ore.getConfig(OreConfig.MANA_LEVEL).hasConfig(ManaLevel.CAN_UET_TOOL_MAKE)) {
                continue;
            }
            recipeConsumer.accept(SimpleCookingRecipeBuilder.smelting(Ingredient.of(ore.getMineralBlockTag().itemTagKey()), ore.get(this).item(), 0.6F, 200)
                    .unlockedBy("has_ore", ModRecipeProvider.has(ore.getMineralBlockTag().itemTagKey())));
        }
    }
}
