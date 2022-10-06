package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.data.ModRecipeProvider;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.mana_level.ManaLevelItem;
import com.til.dusk.common.register.mana_level.block.Mechanic;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.world.block.ModBlock;
import com.til.dusk.common.world.block.RepeaterBlock;
import com.til.dusk.common.world.item.DuskItem;
import com.til.dusk.util.prefab.JsonPrefab;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import java.util.function.Consumer;

/**
 * @author til
 */
public class RepeaterMechanic extends Mechanic {


    public RepeaterMechanic() {
        super("repeater");
        setConfig(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                .addRun((s, m) -> s.addInItem(m.getAcceptableTagPack(OreItem.casing).itemTagKey(), 2))
                .addRun((s, m) -> s.addInItem(ManaLevelItem.operation.getTag(m), 1)));
    }

    @Override
    public Block createBlock(ManaLevel manaLevel) {
        return new RepeaterBlock(manaLevel);
    }

    @Override
    public ModBlock.ICustomModel getBlockModelMapping(ManaLevel manaLevel) {
        return new ModBlock.ICustomModel() {
            @Override
            public ResourceLocation blockModelName() {
                return name;
            }

            @Override
            public String blockJsonBasics() {
                return JsonPrefab.FACING_BLOCK_STATE_JSON;
            }
        };
    }

    @Override
    public void registerRecipe(Consumer<RecipeBuilder> recipeConsumer) {
        for (ManaLevel manaLevel : ManaLevel.LEVEL.get()) {
            if (!manaLevel.hasSet(ManaLevel.CAN_USE_RECIPE_MAKE)) {
                continue;
            }
            recipeConsumer.accept(ShapedRecipeBuilder.shaped(manaLevel.get(this).blockItem(), 1)
                    .define('A', manaLevel.getAcceptableTagPack(OreItem.casing).itemTagKey())
                    .define('B', DuskItem.diamondMakeOperation.get().tag())
                    .define('D', manaLevel.getAcceptableTagPack(OreItem.wrench).itemTagKey())
                    .pattern(" D ")
                    .pattern("BAB")
                    .unlockedBy("has_casing",
                            ModRecipeProvider.has(manaLevel.getAcceptableTagPack(OreItem.casing).itemTagKey())));
        }
    }
}
