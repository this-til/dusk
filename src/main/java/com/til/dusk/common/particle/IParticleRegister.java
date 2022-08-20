package com.til.dusk.common.particle;

import com.til.dusk.util.Pos;
import net.minecraft.world.level.Level;

import java.awt.*;
import java.util.ArrayList;


/**
 * @author til
 */
public interface IParticleRegister {

    String type();

    default void add(Level world, Pos start, Pos end, Color color, double density) {
        CommonParticle.MAP.computeIfAbsent(world, k -> new ArrayList<>()).add(new CommonParticle.Data(type(), start, end, color, density));
    }


}
