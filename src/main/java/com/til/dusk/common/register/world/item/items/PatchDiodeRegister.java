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
public class PatchDiodeRegister extends ItemPackRegister {
    public PatchDiodeRegister() {
        super("patch_diode");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "贴片二极管");
        lang.add(LangType.EN_CH, "Patch Diode");
    }

    @Override
    protected void register(ItemPack itemPack) {
        super.register(itemPack);
        ItemTag.addTag(ItemTag.diodeTag, itemPack.item());
    }

    @Override
    public void defaultConfig() {
        defaultShaped = new Delayed.ListShapedDelayed(() -> List.of(
                new ShapedOre(name, ShapedType.encapsulation, ShapedDrive.get(1), ManaLevel.t2)
                        .addInItem(Ore.spiritSilver.get(OreItem.foil).itemTag(), 1)
                        .addInItem(Ore.tibetanBlue.get(OreItem.dustTiny).itemTag(), 1)
                        .addInItem(Ore.pineCypress.get(OreItem.dustTiny).itemTag(), 1)
                        .addOutItem(new ItemStack(pack.item(), 4), 1d)
                        .addMultipleSurplusTime(512L)
                        .addMultipleConsumeMana(12L)
        ));
    }
}
