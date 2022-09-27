package com.til.dusk.common.register.particle_register;

import com.til.dusk.util.DuskColor;
import com.til.dusk.util.Pos;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

/**
 * @param type    粒子类型
 * @param color   颜色
 * @param density 密度
 */
public record ParticleData(ResourceLocation type, DuskColor color, double density,
                           @Nullable ResourceLocation resourceLocation, Pos... pos) {
    public ParticleData(ResourceLocation type, DuskColor color, double density, @Nullable ResourceLocation resourceLocation, Pos... pos) {
        this.type = type;
        this.color = color;
        this.density = density;
        this.pos = pos == null ? new Pos[0] : pos;
        this.resourceLocation = resourceLocation;
    }
}
