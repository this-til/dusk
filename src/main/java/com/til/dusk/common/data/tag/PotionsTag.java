package com.til.dusk.common.data.tag;

import com.til.dusk.Dusk;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.tags.ITagManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PotionsTag {

    public static Map<TagKey<Potion>, List<Potion>> map = new HashMap<>();

    public static void addTag(TagKey<Potion> tTagKey, Potion t) {
        List<Potion> tList;
        if (map.containsKey(tTagKey)) {
            tList = map.get(tTagKey);
        } else {
            tList = new ArrayList<>();
            map.put(tTagKey, tList);
        }
        tList.add(t);
    }

    public static TagKey<Potion> NO_EFFECT;

    @SubscribeEvent
    public static void event(NewRegistryEvent event) {
        NO_EFFECT = Dusk.instance.POTION_TAG.createTagKey(new ResourceLocation(Dusk.MOD_ID, "no_effect"));
        {
            addTag(NO_EFFECT, Potions.EMPTY);
            addTag(NO_EFFECT, Potions.WATER);
            addTag(NO_EFFECT, Potions.MUNDANE);
            addTag(NO_EFFECT, Potions.THICK);
            addTag(NO_EFFECT, Potions.AWKWARD);
        }
    }
}
