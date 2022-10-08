package com.til.dusk.common.event;

import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.event.IModBusEvent;

import java.util.function.Consumer;

/**
 * @author til
 */
public class RegisterRecipeEvent extends Event implements IModBusEvent {
    public final Consumer<RecipeBuilder> recipeBuilderConsumer;

    public RegisterRecipeEvent(Consumer<RecipeBuilder> recipeBuilderConsumer) {
        this.recipeBuilderConsumer = recipeBuilderConsumer;
    }
}
