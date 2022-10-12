package com.til.dusk.common.register.world.item.items;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.common.register.world.item.ItemPackRegister;
import com.til.dusk.util.pack.ItemPack;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author til
 */
public class TriodeRegister extends ItemPackRegister {
    public TriodeRegister() {
        super("triode");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "三极管");
        lang.add(LangType.EN_CH, "Triode");
    }

    @Override
    protected void register(ItemPack itemPack) {
        super.register(itemPack);
        ItemTag.addTag(ItemTag.triodeTag, itemPack.item());
    }

    @Override
    public void defaultConfig() {
        defaultShaped = new Delayed.ListShapedDelayed(() -> List.of(
                new ShapedOre(name, ShapedType.encapsulation, ShapedDrive.get(0), ManaLevel.t1)
                        .addInItem(Ore.spiritSilver.get(OreItem.casing).itemTag(), 1)
                        .addInItem(Ore.spiritSilver.get(OreItem.string).itemTag(), 3)
                        .addInItem(Ore.pineCypress.get(OreItem.dust).itemTag(), 1)
                        .addInItem(Ore.cotinusCoggygria.get(OreItem.dust).itemTag(), 2)
                        .addOutItem(new ItemStack(pack.item(), 4), 1d)
                        .addMultipleSurplusTime(1024L)
                        .addMultipleConsumeMana(12L)
        ));
    }
}
