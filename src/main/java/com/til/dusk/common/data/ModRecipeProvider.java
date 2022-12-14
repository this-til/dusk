package com.til.dusk.common.data;

import com.til.dusk.Dusk;
import com.til.dusk.common.event.DelayTrigger;
import com.til.dusk.common.event.RegisterRecipeEvent;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.common.register.RegisterManage;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author til
 */
public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        List<RecipeBuilder> recipeBuilderList = new ArrayList<>(32);
        Consumer<RecipeBuilder> listConsumer = recipeBuilderList::add;
        for (RegisterBasics<?> allRegisterBasic : RegisterManage.ALL_REGISTER_BASICS) {
            allRegisterBasic.registerRecipe(listConsumer);
        }
        Dusk.instance.modEventBus.post(new RegisterRecipeEvent(listConsumer));
        DelayTrigger.run(DelayTrigger.RECIPE, r -> listConsumer.accept(r.func()));
        if (!recipeBuilderList.isEmpty()) {
            for (RecipeBuilder recipeBuilder : recipeBuilderList) {
                recipeBuilder.save(consumer);
            }
        }
    }

    public static InventoryChangeTrigger.TriggerInstance has(@NotNull TagKey<Item> key) {
        return RecipeProvider.has(key);
    }

    public static InventoryChangeTrigger.TriggerInstance has(Item key) {
        return RecipeProvider.has(key);
    }

    public static InventoryChangeTrigger.TriggerInstance inventoryTrigger(ItemPredicate @NotNull ... itemPredicates) {
        return RecipeProvider.inventoryTrigger(itemPredicates);
    }

}
