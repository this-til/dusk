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
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author til
 */
public class ManaTransferParticle extends ParticleRegister {
    public static final float MANA_THRESHOLD = 320f;

    public ManaTransferParticle() {
        super("mana_transfer");
        MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, this::eventMana);
    }

    public void eventMana(EventIO.Mana event) {
        if (event.routePack.isEmpty()) {
            return;
        }
        List<RoutePack<Long>> list = event.routePack.getAll();
        List<List<RoutePack.RouteCell<Double>>> route = new ArrayList<>(list.size());
        for (RoutePack<Long> longRoutePack : list) {
            List<RoutePack.RouteCell<Double>> cells = new ArrayList<>();
            for (RoutePack.RouteCell<Long> longRouteCell : longRoutePack.routeCellList) {
                if (random.nextDouble() < longRouteCell.data() / MANA_THRESHOLD) {
                    cells.add(new RoutePack.RouteCell<>(longRouteCell.start(), longRouteCell.end(), 1d));
                } else {
                    cells.add(new RoutePack.RouteCell<>(longRouteCell.start(), longRouteCell.end(), 0d));
                }
            }
            if (!cells.isEmpty()) {
                route.add(cells);
            }
        }
        this.add(event.level, route, ColorPrefab.MANA_IO, null);
    }

    @Override
    public Extension.Data_2<Float, List<Particle>> run(ClientLevel world, Pos start, Pos end, DuskColor color, double density, @Nullable ResourceLocation resourceLocation) {
        if (density <= 0) {
            return new Extension.Data_2<>(start.distance(end) * 3, null);
        }
        Pos _end = end.move(Pos.randomPos());
        int dis = (int) start.distance(_end) * 3;
        new DefaultParticle(world, start, DEFAULT, color, Pos.movePos(start, _end, dis), 0.25f, dis);
        return new Extension.Data_2<>(start.distance(end) * 3, List.of(new DefaultParticle(world, start, DEFAULT, color, Pos.movePos(start, _end, dis), 0.25f, dis)));
    }


}
