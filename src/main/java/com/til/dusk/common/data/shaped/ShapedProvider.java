package com.til.dusk.common.data.shaped;

import com.google.common.collect.Maps;
import com.til.dusk.util.Util;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Map;

/**
 * @author til
 */
public class ShapedProvider implements DataProvider {


    @Override
    public void run(@NotNull CachedOutput cachedOutput) throws IOException {
        for (Map.Entry<ResourceLocation, Shaped> entry : ModShaped.NAME_MAP.entrySet()) {
            ModShaped.BUILDER_MAP.get(entry.getValue().getClass()).write(Util.forcedConversion(entry.getValue()), cachedOutput);
        }
    }

    @Override
    public String getName() {
        return "shaped";
    }
}
