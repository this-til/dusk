package com.til.dusk.common.register.mana_level.item;

import com.til.dusk.Dusk;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.register.ItemUnitRegister;
import com.til.dusk.common.register.RegisterManage;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;

import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ManaLevelItem extends ItemUnitRegister<ManaLevelItem, ManaLevel> {

    public static Supplier<IForgeRegistry<ManaLevelItem>> LEVEL_ITEM;


    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        LEVEL_ITEM = RegisterManage.create(ManaLevelItem.class, new ResourceLocation(Dusk.MOD_ID, "mana_level_item"), event);
    }

    public ManaLevelItem(ResourceLocation name) {
        super(name, LEVEL_ITEM);
    }

    public ManaLevelItem(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    @Override
    public void dyeBlack(ManaLevel manaLevel, ColorProxy.ItemColorPack itemColorPack) {
        itemColorPack.addColor(0, itemStack -> manaLevel.color);
    }

    @Override
    public void defaultConfig() {

    }
}
