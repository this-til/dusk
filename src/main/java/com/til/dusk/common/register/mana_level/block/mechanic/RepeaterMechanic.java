package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.common.data.ModRecipeProvider;
import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.mana_level.block.Mechanic;
import com.til.dusk.common.register.mana_level.item.group.ManaLevelItemGroup;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.world.item.ItemPackRegister;
import com.til.dusk.common.world.block.DuskBlock;
import com.til.dusk.common.world.block.RepeaterBlock;
import com.til.dusk.util.ModelJsonUtil;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author til
 */
public class RepeaterMechanic extends Mechanic {

    public RepeaterMechanic() {
        super("repeater");
    }

    @Override
    public Block createBlock(ManaLevel manaLevel) {
        return new RepeaterBlock(manaLevel);
    }

    @Override
    public void registerRecipe(Consumer<RecipeBuilder> recipeConsumer) {
        for (ManaLevel manaLevel : ManaLevel.MANA_LEVEL.get()) {
            if (!manaLevel.canUseRecipeMake) {
                continue;
            }
            recipeConsumer.accept(ShapedRecipeBuilder.shaped(manaLevel.get(this).blockItem(), 1)
                    .define('A', manaLevel.acceptableTagPack.getTagPack(OreItem.casing).itemTagKey())
                    .define('B', ItemPackRegister.diamondMakeOperation.pack.itemTag())
                    .define('D', manaLevel.acceptableTagPack.getTagPack(OreItem.wrench).itemTagKey())
                    .pattern(" D ")
                    .pattern("BAB")
                    .unlockedBy("has_casing",
                            ModRecipeProvider.has(manaLevel.acceptableTagPack.getTagPack(OreItem.casing).itemTagKey())));
        }
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "能力中继器");
        lang.add(LangType.EN_CH, "Capacity Repeater");
    }

    @Override
    public void defaultConfig() {
        manaLevelMakeData = new ManaLevelMakeData()
                .addOreConfig(List.of(
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(OreItem.casing.name, 2),
                        new IShapedOreConfig.IShapedOreManaLevelConfig.AcceptItemIn(ManaLevelItemGroup.operation.name, 1)));
    }
}
