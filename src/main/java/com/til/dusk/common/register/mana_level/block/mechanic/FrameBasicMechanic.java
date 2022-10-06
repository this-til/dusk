package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.data.ModRecipeProvider;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.mana_level.block.DefaultCapacityMechanic;
import com.til.dusk.common.register.ore.block.OreBlock;
import com.til.dusk.common.register.ore.item.OreItem;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.ShapedRecipeBuilder;

import java.util.function.Consumer;

/**
 * @author til
 */
public class FrameBasicMechanic extends DefaultCapacityMechanic {
    public FrameBasicMechanic() {
        super("frame_basic");
        setConfig(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                .addRun((s, m) -> s.addInItem(m.get(repeater).blockItemTag(), 2))
                .addRun((s, m) -> s.addInItem(m.getAcceptableTagPack(OreBlock.bracket).itemTagKey(), 1)));
    }

    @Override
    public void registerRecipe(Consumer<RecipeBuilder> recipeConsumer) {
        for (ManaLevel manaLevel : ManaLevel.LEVEL.get()) {
            if (!manaLevel.hasSet(ManaLevel.CAN_USE_RECIPE_MAKE)) {
                continue;
            }
            recipeConsumer.accept(ShapedRecipeBuilder.shaped(manaLevel.get(this).blockItem(), 1)
                    .define('A', manaLevel.getAcceptableTagPack(OreBlock.bracket).itemTagKey())
                    .define('B', manaLevel.get(repeater).blockItemTag())
                    .define('D', manaLevel.getAcceptableTagPack(OreItem.wrench).itemTagKey())
                    .pattern(" D ")
                    .pattern("BAB")
                    .unlockedBy("has_repeater",
                            ModRecipeProvider.has(manaLevel.get(repeater).blockItemTag())));
        }
    }
}
