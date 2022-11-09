package com.til.dusk.common.register.ore.item.items;

import com.til.dusk.common.data.ModRecipeProvider;
import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.ore.block.OreBlockMineral;
import com.til.dusk.common.register.ore.item.OreItemMetal;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.util.pack.ItemPack;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.Nullable;

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
        for (Ore ore : Ore.ORE.get()) {
            if (!ore.isMetal) {
                continue;
            }
            if (ore.mineralBlockData == null) {
                continue;
            }
            recipeConsumer.accept(SimpleCookingRecipeBuilder.smelting(Ingredient.of(ore.tagPackSupplier.getTagPack(OreBlockMineral.MINERAL_NAME).itemTagKey()), ore.get(this).item(), 0.6F, 200)
                    .unlockedBy("has_ore", ModRecipeProvider.has(ore.tagPackSupplier.getTagPack(OreBlockMineral.MINERAL_NAME).itemTagKey())));
        }

    }

    @Override
    public @Nullable ItemPack create(Ore ore) {
        ItemPack itemPack = super.create(ore);
        if (itemPack == null) {
            return null;
        }
        ItemTag.addTag(Tags.Items.GEMS, itemPack.item());
        return itemPack;
    }
}
