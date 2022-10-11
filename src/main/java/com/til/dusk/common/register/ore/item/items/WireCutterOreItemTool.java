package com.til.dusk.common.register.ore.item.items;

import com.til.dusk.common.data.ModRecipeProvider;
import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.ore.item.OreItemTool;
import com.til.dusk.common.register.ore.ore.Ore;
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
    public void registerRecipe(Consumer<RecipeBuilder> recipeConsumer) {
        super.registerRecipe(recipeConsumer);
        for (Ore ore : Ore.ORE.get()) {
            if (!ore.isMetal) {
                continue;
            }
            if (ore.toolData == null) {
                continue;
            }
            if (!ore.manaLevel.canUseRecipeMake){
                continue;
            }
            recipeConsumer.accept(ShapedRecipeBuilder.shaped(ore.get(wireCutter).item(), 1)
                    .define('A', ore.get(ingot).itemTag())
                    .define('B', ore.get(plate).itemTag())
                    .pattern("B B")
                    .pattern(" B ")
                    .pattern("A A")
                    .unlockedBy("has_ore", ModRecipeProvider.has(ore.get(ingot).itemTag())));
        }
        for (Ore ore : Ore.ORE.get()) {
            if (!ore.isMetal) {
                continue;
            }
            if (!ore.manaLevel.canUseToolMake) {
                continue;
            }
            recipeConsumer.accept(ShapedRecipeBuilder.shaped(ore.get(string).item(), 1)
                    .define('A', wireCutter.tagPackSupplier.getTagPack().itemTagKey())
                    .define('B', ore.get(plate).itemTag())
                    .pattern("A")
                    .pattern("B")
                    .unlockedBy("has_wire_cutter", ModRecipeProvider.has(wireCutter.tagPackSupplier.getTagPack().itemTagKey())));
        }
    }
}
