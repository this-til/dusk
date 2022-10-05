package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.data.ModRecipeProvider;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.mana_level.ManaLevelItem;
import com.til.dusk.common.register.mana_level.block.HandleMechanic;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.common.world.item.DuskItem;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.ShapedRecipeBuilder;

import java.util.Set;
import java.util.function.Consumer;

/**
 * @author til
 */
public class AssembleMechanic extends HandleMechanic {
    public AssembleMechanic(){
        super("assemble", () -> Set.of(ShapedType.assemble));
        setConfig(MECHANIC_MAKE_DATA, () -> new ManaLevelMakeData()
                .setMakeLevel(ManaLevelMakeData.MakeLevel.UP)
                .addRun((s, m) -> s.addInItem(m.get(frameBasic).blockItemTag(), 1))
                .addRun((s, m) -> s.addInItem(ManaLevelItem.power.getTag(m), 1))
                .addRun((s, m) -> s.addInItem(ManaLevelItem.instructions.getTag(m), 1))
                .addRun((s, m) -> s.addInItem(m.getAcceptableTagPack(OreItem.wrench).itemTagKey(), 1))
                .addInItem(ItemTag.CRAFTING_TABLE.d1(), 18));
    }

    @Override
    public void registerRecipe(Consumer<RecipeBuilder> recipeConsumer) {
        for (ManaLevel manaLevel : ManaLevel.LEVEL.get()) {
            if (!manaLevel.hasSet(ManaLevel.CAN_USE_RECIPE_MAKE)) {
                continue;
            }
            recipeConsumer.accept(ShapedRecipeBuilder.shaped(manaLevel.get(this).blockItem(), 1)
                    .define('A', manaLevel.get(frameBasic).blockItemTag())
                    .define('B', DuskItem.diamondMakePower.get().tag())
                    .define('C', DuskItem.diamondMakeInstructions.get().tag())
                    .define('D', manaLevel.getAcceptableTagPack(OreItem.wrench).itemTagKey())
                    .pattern(" D ")
                    .pattern("BAC")
                    .unlockedBy("has_frame_basic",
                            ModRecipeProvider.has(manaLevel.get(frameBasic).blockItemTag())));
        }
    }
}
