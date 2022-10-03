package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.Dusk;
import com.til.dusk.common.data.ModRecipeProvider;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.mana_level.block.DefaultCapacityMechanic;
import com.til.dusk.common.register.ore.block.OreBlock;
import com.til.dusk.common.register.ore.item.OreItem;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

/**
 * @author til
 */
public class FrameBasicMechanic extends DefaultCapacityMechanic {
    public FrameBasicMechanic() {
        super("frame_basic");
        setSet(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                .addRun((s, m) -> s.addInItem(m.blockMap.get(repeater).blockItemTag(), 2))
                .addRun((s, m) -> s.addInItem(m.getAcceptableTagPack(OreBlock.bracket).itemTagKey(), 1)));
    }

    @Override
    public void registerRecipe(List<RecipeBuilder> recipeBuilderList) {
        for (ManaLevel manaLevel : ManaLevel.LEVEL.get()) {
            if (!manaLevel.hasSet(ManaLevel.CAN_USE_RECIPE_MAKE)) {
                continue;
            }
            recipeBuilderList.add(ShapedRecipeBuilder.shaped(manaLevel.blockMap.get(this).blockItem(), 1)
                    .define('A', manaLevel.getAcceptableTagPack(OreBlock.bracket).itemTagKey())
                    .define('B', manaLevel.blockMap.get(repeater).blockItemTag())
                    .define('D', manaLevel.getAcceptableTagPack(OreItem.wrench).itemTagKey())
                    .pattern(" D ")
                    .pattern("BAB")
                    .unlockedBy("has_repeater",
                            ModRecipeProvider.has(manaLevel.blockMap.get(repeater).blockItemTag())));
        }
    }
}
