package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.data.ModRecipeProvider;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.mana_level.block.HandleMechanic;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.ShapedRecipeBuilder;

import java.util.List;
import java.util.Set;

/**
 * @author til
 */
public class ExtractManaHandleMechanic extends HandleMechanic {

    public ExtractManaHandleMechanic() {
        super("extract_mana", () -> Set.of(ShapedType.extractMana));
        setSet(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                .addRun((s, m) -> s.addInItem(m.getAcceptableTagPack(OreItem.casing).itemTagKey(), 4))
                .addRun((s, m) -> s.addInItem(m.getAcceptableTagPack(OreItem.foil).itemTagKey(), 4))
                .addRun((s, m) -> s.addInItem(m.blockMap.get(frameBasic).blockItemTag(), 1)));
    }

    @Override
    public void registerRecipe(List<RecipeBuilder> recipeBuilderList) {
        for (ManaLevel manaLevel : ManaLevel.LEVEL.get()) {
            if (!manaLevel.hasSet(ManaLevel.CAN_USE_RECIPE_MAKE)) {
                continue;
            }
            recipeBuilderList.add(ShapedRecipeBuilder.shaped(manaLevel.blockMap.get(this).blockItem(), 1)
                    .define('A', manaLevel.getAcceptableTagPack(OreItem.foil).itemTagKey())
                    .define('B', manaLevel.getAcceptableTagPack(OreItem.casing).itemTagKey())
                    .define('C', manaLevel.blockMap.get(frameBasic).blockItemTag())
                    .define('D', manaLevel.getAcceptableTagPack(OreItem.wrench).itemTagKey())
                    .pattern("BDB")
                    .pattern("ACA")
                    .pattern("BAB")
                    .unlockedBy("has_casing",
                            ModRecipeProvider.has(manaLevel.getAcceptableTagPack(OreItem.casing).itemTagKey())));
        }
    }
}