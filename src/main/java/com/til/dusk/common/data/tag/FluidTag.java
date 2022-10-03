package com.til.dusk.common.data.tag;

import com.til.dusk.Dusk;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author til
 */
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

}
