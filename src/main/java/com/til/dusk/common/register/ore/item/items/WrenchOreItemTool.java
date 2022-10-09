package com.til.dusk.common.register.ore.item.items;

import com.til.dusk.common.data.ModRecipeProvider;
import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.register.ore.item.OreItemTool;
import com.til.dusk.common.register.ore.ore.Ore;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.ShapedRecipeBuilder;

import java.util.function.Consumer;

/**
 * @author til
 */
public class WrenchOreItemTool extends OreItemTool {

    public WrenchOreItemTool() {
        super("wrench");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "扳手");
        lang.add(LangType.EN_CH, "Wrench");
    }

    @Override
    public void registerRecipe(Consumer<RecipeBuilder> recipeConsumer) {
        super.registerRecipe(recipeConsumer);
        for (Ore ore : Ore.ORE.get()) {
            if (!ore.isMetal) {
                continue;
            }
            if (ore.toolData == null) {
                continue;
            }
            recipeConsumer.accept(ShapedRecipeBuilder.shaped(ore.get(wrench).item(), 1)
                    .define('A', ore.get(ingot).itemTag())
                    .pattern("A A")
                    .pattern(" A ")
                    .pattern(" A ")
                    .unlockedBy("has_ore", ModRecipeProvider.has(ore.get(ingot).itemTag())));
        }
        for (Ore ore : Ore.ORE.get()) {
            if (!ore.manaLevel.canUetToolMake) {
                continue;
            }
            recipeConsumer.accept(ShapedRecipeBuilder.shaped(ore.get(gear).item(), 1)
                    .define('A', wrench.tagPackSupplier.getTagPack().itemTagKey())
                    .define('B', ore.get(plate).itemTag())
                    .define('C', ore.get(ingot).item())
                    .pattern("BCB")
                    .pattern("CAC")
                    .pattern("BCB")
                    .unlockedBy("has_wrench", ModRecipeProvider.has(wrench.tagPackSupplier.getTagPack().itemTagKey())));
            recipeConsumer.accept(ShapedRecipeBuilder.shaped(ore.get(rotor).item(), 1)
                    .define('A', wrench.tagPackSupplier.getTagPack().itemTagKey())
                    .define('B', ore.get(plate).itemTag())
                    .define('C', ore.get(stickLong).item())
                    .pattern("BCB")
                    .pattern("CAC")
                    .pattern("BCB")
                    .unlockedBy("has_wrench", ModRecipeProvider.has(wrench.tagPackSupplier.getTagPack().itemTagKey())));
        }
    }
}
