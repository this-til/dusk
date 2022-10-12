package com.til.dusk.common.register.world.item.items;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.common.register.world.item.ItemPackRegister;
import com.til.dusk.util.pack.ItemPack;
import net.minecraft.data.recipes.RecipeBuilder;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author til
 */
public class PatchTriodeRegister extends ItemPackRegister {
    public PatchTriodeRegister() {
        super("patch_triode");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "贴片三极管");
        lang.add(LangType.EN_CH, "Patch Triode");
    }

    @Override
    protected void register(ItemPack itemPack) {
        super.register(itemPack);
        ItemTag.addTag(ItemTag.triodeTag, itemPack.item());
    }

    @Override
    public void defaultConfig() {
        defaultShaped = new Delayed.ListShapedDelayed(() -> List.of(

        ));
    }
}
