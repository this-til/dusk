package com.til.dusk.common.register.particle_register.particle_registers;

import com.til.dusk.client.particle.DefaultParticle;
import com.til.dusk.common.event.EventIO;
import com.til.dusk.common.register.particle_register.ParticleRegister;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.Extension;
import com.til.dusk.util.Pos;
import com.til.dusk.util.RoutePack;
import com.til.dusk.util.prefab.ColorPrefab;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class FluidTransferParticle extends ParticleRegister {

    public FluidTransferParticle() {
        super("fluid_transfer");
        MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, this::eventFluid);
    }

    public void eventFluid(EventIO.Fluid event) {
        if (event.routePack.isEmpty()) {
            return;
        }
        FluidType fluidType = null;
        List<RoutePack<FluidStack>> list = event.routePack.getAll();
        List<List<RoutePack.RouteCell<Double>>> route = new ArrayList<>(list.size());
        for (RoutePack<FluidStack> fluidStackRoutePack : list) {
            List<RoutePack.RouteCell<Double>> cells = new ArrayList<>();
            for (RoutePack.RouteCell<FluidStack> fluidStackRouteCell : fluidStackRoutePack.routeCellList) {
                if (fluidStackRouteCell.data().isEmpty()) {
                    continue;
                }
                if (fluidType == null) {
                    fluidType = fluidStackRouteCell.data().getFluid().getFluidType();
                }
                cells.add(new RoutePack.RouteCell<>(fluidStackRouteCell.start(), fluidStackRouteCell.end(), fluidStackRouteCell.data().getAmount() / 128D));
            }
            if (!cells.isEmpty()) {
                route.add(cells);
            }
        }
        this.add(event.level, route, ColorPrefab.FLUID_IO, ForgeRegistries.FLUID_TYPES.get().getKey(fluidType));
    }

    @Override
    public Extension.Data_2<Float, List<Particle>> run(ClientLevel world, Pos start, Pos end, DuskColor color, double density, @Nullable ResourceLocation resourceLocation) {
        if (resourceLocation != null) {
            FluidType fluidType = ForgeRegistries.FLUID_TYPES.get().getValue(resourceLocation);
            if (fluidType != null) {
                IClientFluidTypeExtensions clientFluidTypeExtensions = IClientFluidTypeExtensions.of(fluidType);
                color = new DuskColor(clientFluidTypeExtensions.getTintColor());
            }
        }
        List<Particle> list = new ArrayList<>();
        for (int i = 0; i < density; i++) {
            Pos _end = end.move(Pos.randomPos());
            int dis = (int) start.distance(end) * 6;
            list.add(new DefaultParticle(world, start, DEFAULT, color, Pos.movePos(start, _end, dis), 0.25f, dis));
        }
        return new Extension.Data_2<>(start.distance(end) * 6, list);
    }

}
