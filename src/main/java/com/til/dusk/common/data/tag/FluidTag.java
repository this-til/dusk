package com.til.dusk.common.data.tag;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FluidTag {
    private static FluidTag fluidTag;


    public static FluidTag getInstance() {
        if (fluidTag == null) {
            fluidTag = new FluidTag();
        }
        return fluidTag;
    }

    public Map<TagKey<Fluid>, List<Fluid>> map = new HashMap<>();

    public void addTag(TagKey<Fluid> tTagKey, Fluid t) {
        List<Fluid> tList;
        if (map.containsKey(tTagKey)) {
            tList = map.get(tTagKey);
        } else {
            tList = new ArrayList<>();
            map.put(tTagKey, tList);
        }
        tList.add(t);
    }


}
