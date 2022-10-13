package com.til.dusk.common.register.world.item.items;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.data.ModRecipeProvider;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.mana_level.item.group.ManaLevelItemGroup;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.world.item.DiamondMakeItemPackRegister;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.pack.ItemPack;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

/**
 * @author til
 */
public class DiamondMakeOperationBasicsRegister extends DiamondMakeItemPackRegister {
    public DiamondMakeOperationBasicsRegister() {
        super("diamond_make_operation_basics");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "钻石制基体");
        lang.add(LangType.EN_CH, "Diamond Make Basics");
    }

    @Override
    public void defaultConfig() {
        strokeColor = new DuskColor(25, 25, 25);
        coreColor = new DuskColor(25, 25, 25);
    }

    @Override
    protected void register(ItemPack itemPack) {
        super.register(itemPack);
        ItemTag.addTag(ManaLevel.t1.acceptableTagPack.getTagPack(ManaLevelItemGroup.operationBasics).itemTagKey(), itemPack.item());
    }

    @Override
    public void registerRecipe(Consumer<RecipeBuilder> recipeConsumer) {
        recipeConsumer.accept(ShapedRecipeBuilder.shaped(pack.item())
                .define('A', Tags.Items.GEMS_DIAMOND)
                .define('B', ItemTag.REPEATER.d1())
                .define('C', ItemTag.COMPARATOR.d1())
                .define('D', Tags.Items.DUSTS_REDSTONE)
                .pattern("DBD")
                .pattern("CAC")
                .pattern("DBD")
                .unlockedBy("has_diamond", ModRecipeProvider.has(Tags.Items.GEMS_DIAMOND)));
    }
}
