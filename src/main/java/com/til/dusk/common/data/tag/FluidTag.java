package com.til.dusk.common.data.tag;

import com.til.dusk.Dusk;
import com.til.dusk.common.event.RegisterManageEvent;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;
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
public class FluidTag extends FluidTagsProvider {

    public static final Map<TagKey<Fluid>, List<Fluid>> MAP = new HashMap<>();

    public static void addTag(TagKey<Fluid> tTagKey, Fluid t) {
        List<Fluid> tList;
        if (MAP.containsKey(tTagKey)) {
            tList = MAP.get(tTagKey);
        } else {
            tList = new ArrayList<>();
            MAP.put(tTagKey, tList);
        }
        tList.add(t);
    }

    public FluidTag(DataGenerator dataGenerator, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, Dusk.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        for (Map.Entry<TagKey<Fluid>, List<Fluid>> entry : FluidTag.MAP.entrySet()) {
            this.tag(entry.getKey()).add(entry.getValue().toArray(new Fluid[0]));
        }
    }


    public static TagKey<Fluid> ELEMENT;
    public static TagKey<Fluid> BA_GUA;
    public static TagKey<Fluid> SI_XIANG;
    public static TagKey<Fluid> LIANG_YI;

    @SubscribeEvent
    public static void event(RegisterManageEvent.InMap event) {
        ELEMENT = Dusk.instance.FLUID_TAG.createTagKey(new ResourceLocation(Dusk.MOD_ID, "elements"));
        BA_GUA = Dusk.instance.FLUID_TAG.createTagKey(new ResourceLocation(Dusk.MOD_ID, "ba_guas"));
        SI_XIANG = Dusk.instance.FLUID_TAG.createTagKey(new ResourceLocation(Dusk.MOD_ID, "si_xiangs"));
        LIANG_YI = Dusk.instance.FLUID_TAG.createTagKey(new ResourceLocation(Dusk.MOD_ID, "liang_yis"));
    }

}
