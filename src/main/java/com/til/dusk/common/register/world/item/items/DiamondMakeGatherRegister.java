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
import com.til.dusk.util.prefab.ColorPrefab;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

/**
 * @author til
 */
public class DiamondMakeGatherRegister extends DiamondMakeItemPackRegister {
    public DiamondMakeGatherRegister() {
        super("diamond_make_gather");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "钻石制聚集核心");
        lang.add(LangType.EN_CH, "Diamond Make Gather");
    }

    @Override
    public void defaultConfig() {
        strokeColor = new DuskColor(107, 197, 191);
        coreColor = ColorPrefab.FLUID_IO;
    }

    @Override
    protected void register(ItemPack itemPack) {
        super.register(itemPack);
        ItemTag.addTag(ManaLevel.t1.acceptableTagPack.getTagPack(ManaLevelItemGroup.gather).itemTagKey(), itemPack.item());
    }

    @Override
    public void registerRecipe(Consumer<RecipeBuilder> recipeConsumer) {
        recipeConsumer.accept(ShapedRecipeBuilder.shaped(pack.item())
                .define('A', diamondMakeOperationBasics.pack.itemTag())
                .define('B', Tags.Items.GEMS_LAPIS)
                .pattern(" B ")
                .pattern("BAB")
                .pattern(" B ")
                .unlockedBy("has_diamond_make_operation", ModRecipeProvider.has(diamondMakeOperationBasics.pack.item())));
    }
}
