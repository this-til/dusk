package com.til.dusk.common.register.ore.item.items;

import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.data.ModRecipeProvider;
import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.register.ore.item.OreItemTool;
import com.til.dusk.common.register.ore.ore.Ore;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

/**
 * @author til
 */
public class HammerOreItemTool extends OreItemTool {
    public HammerOreItemTool() {
        super("hammer");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "扳手");
        lang.add(LangType.EN_CH, "Hammer");
    }

    @Override
    public void dyeBlack(Ore ore, ColorProxy.ItemColorPack itemColorPack) {
        itemColorPack.addColor(1, itemStack -> ore.color);
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
            recipeConsumer.accept(ShapedRecipeBuilder.shaped(ore.get(hammer).item(), 1)
                    .define('A', ore.get(ingot).itemTag())
                    .define('B', Tags.Items.RODS_WOODEN)
                    .pattern("AAA")
                    .pattern("AAA")
                    .pattern(" B ")
                    .unlockedBy("has_ore", ModRecipeProvider.has(ore.get(ingot).itemTag())));
        }

        for (Ore ore : Ore.ORE.get()) {
            if (!ore.manaLevel.canUetToolMake){
                continue;
            }
            recipeConsumer.accept(ShapedRecipeBuilder.shaped(ore.get(plate).item(), 1)
                    .define('A', hammer.tagPackSupplier.getTagPack().itemTagKey())
                    .define('B', ore.get(ingot).itemTag())
                    .pattern("AB")
                    .unlockedBy("has_hammer", ModRecipeProvider.has(hammer.tagPackSupplier.getTagPack().itemTagKey())));
            recipeConsumer.accept(ShapedRecipeBuilder.shaped(ore.get(casing).item(), 1)
                    .define('A', hammer.tagPackSupplier.getTagPack().itemTagKey())
                    .define('B', ore.get(plate).itemTag())
                    .pattern("A")
                    .pattern("B")
                    .unlockedBy("has_hammer", ModRecipeProvider.has(hammer.tagPackSupplier.getTagPack().itemTagKey())));
            recipeConsumer.accept(ShapedRecipeBuilder.shaped(ore.get(foil).item(), 2)
                    .define('A', hammer.tagPackSupplier.getTagPack().itemTagKey())
                    .define('B', ore.get(plate).itemTag())
                    .pattern("A ")
                    .pattern(" B")
                    .unlockedBy("has_hammer", ModRecipeProvider.has(hammer.tagPackSupplier.getTagPack().itemTagKey())));
            recipeConsumer.accept(ShapedRecipeBuilder.shaped(ore.get(stickLong).item(), 1)
                    .define('A', hammer.tagPackSupplier.getTagPack().itemTagKey())
                    .define('B', ore.get(stick).itemTag())
                    .pattern("A")
                    .pattern("B")
                    .pattern("B")
                    .unlockedBy("has_hammer", ModRecipeProvider.has(hammer.tagPackSupplier.getTagPack().itemTagKey())));
        }
    }
}
