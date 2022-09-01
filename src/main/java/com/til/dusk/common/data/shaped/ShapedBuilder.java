package com.til.dusk.common.data.shaped;

import com.google.gson.JsonObject;
import net.minecraft.data.CachedOutput;
import net.minecraft.resources.ResourceLocation;

import java.io.IOException;

/**
 * @author til
 */
public interface ShapedBuilder<S extends Shaped> {

    Shaped as(ResourceLocation name, JsonObject s);

    void write(S s, CachedOutput cachedOutput) throws IOException;
}
