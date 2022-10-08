package com.til.dusk.common.data.tag;

import com.til.dusk.Dusk;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.NewRegistryEvent;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PotionsTag extends TagsProvider<Potion> {


    public static Map<TagKey<Potion>, List<Potion>> MAP = new HashMap<>();

    public static void addTag(TagKey<Potion> tTagKey, Potion t) {
        List<Potion> tList;
        if (MAP.containsKey(tTagKey)) {
            tList = MAP.get(tTagKey);
        } else {
            tList = new ArrayList<>();
            MAP.put(tTagKey, tList);
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

    public PotionsTag(DataGenerator dataGenerator, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, Registry.POTION, Dusk.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        for (Map.Entry<TagKey<Potion>, List<Potion>> entry : PotionsTag.MAP.entrySet()) {
            this.tag(entry.getKey()).add(entry.getValue().toArray(new Potion[0]));
        }
    }
}
