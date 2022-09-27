package com.til.dusk.common.register.particle_register;

import com.til.dusk.util.DuskColor;
import com.til.dusk.util.RoutePack;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public record ParticleRouteData(List<List<RoutePack.RouteCell<Double>>> route, ResourceLocation type, DuskColor color,
                                @Nullable ResourceLocation resourceLocation) {
    public ParticleRouteData(List<List<RoutePack.RouteCell<Double>>> route, ResourceLocation type, DuskColor color, @Nullable ResourceLocation resourceLocation) {
        this.route = route;
        this.type = type;
        this.color = color;
        this.resourceLocation = resourceLocation;
    }
}
