package com.til.dusk.particle;

import com.google.gson.JsonObject;
import com.til.dusk.util.Pos;
import net.minecraft.world.level.Level;

import java.util.ArrayList;


/**
 * @author til
 */
public interface IParticleRegister {

    String type();

    default void add(Level world, Pos start, Pos end, JsonObject old) {
        CommonParticle.MAP.computeIfAbsent(world, k -> new ArrayList<>()).add(new CommonParticle.Data(type(), start, end, old));
    }


}
