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
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author til
 */
public class PatchInductanceRegister extends ItemPackRegister {
    public PatchInductanceRegister() {
        super("patch_inductance");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "贴片电感");
        lang.add(LangType.EN_CH, "Patch Inductance");
    }

    @Override
    protected void register(ItemPack itemPack) {
        super.register(itemPack);
        ItemTag.addTag(ItemTag.inductanceTag, itemPack.item());
    }
    @Override
    public void defaultConfig() {
        defaultShaped = new Delayed.ListShapedDelayed(() -> List.of(
                new ShapedOre(name, ShapedType.encapsulation, ShapedDrive.get(1), ManaLevel.t2)
                        .addInItem(Ore.spiritSilver.get(OreItem.foil).itemTag(), 2)
                        .addInItem(Ore.pineCypress.get(OreItem.dustTiny).itemTag(), 1)
                        .addInItem(Ore.cotinusCoggygria.get(OreItem.dustTiny).itemTag(), 2)
                        .addOutItem(new ItemStack(pack.item(), 4), 1d)
                        .addMultipleSurplusTime(512L)
                        .addMultipleConsumeMana(12L)
        ));
    }
}
