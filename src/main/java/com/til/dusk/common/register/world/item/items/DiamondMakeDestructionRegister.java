package com.til.dusk.common.register.world.item.items;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.common.data.ModRecipeProvider;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.mana_level.item.ManaLevelItemPack;
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
public class DiamondMakeDestructionRegister extends DiamondMakeItemPackRegister {
    public DiamondMakeDestructionRegister() {
        super("diamond_make_destruction");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "钻石制破坏核心");
        lang.add(LangType.EN_CH, "Diamond Make Destruction");
    }

    @Override
    public void defaultConfig() {
        strokeColor = new DuskColor(141, 116, 130);
        coreColor = ColorPrefab.ITEM_IO;
    }

    @Override
    protected void register(ItemPack itemPack) {
        super.register(itemPack);
        ItemTag.addTag(ManaLevel.t1.acceptableTagPack.getTagPack(ManaLevelItemPack.destruction).itemTagKey(), itemPack.item());
    }

    @Override
    public void registerRecipe(Consumer<RecipeBuilder> recipeConsumer) {
        recipeConsumer.accept(ShapedRecipeBuilder.shaped(pack.item())
                .define('A', diamondMakeOperationBasics.pack.itemTag())
                .define('B', Tags.Items.GEMS_QUARTZ)
                .pattern(" B ")
                .pattern("BAB")
                .pattern(" B ")
                .unlockedBy("has_diamond_make_operation", ModRecipeProvider.has(diamondMakeOperationBasics.pack.item())));
    }
}
