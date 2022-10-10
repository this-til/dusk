package com.til.dusk.common.register.particle_register.particle_registers;

import com.til.dusk.client.particle.DefaultParticle;
import com.til.dusk.common.config.ConfigField;
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
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author til
 */
public class ItemTransferParticle extends ParticleRegister {

    public ItemTransferParticle() {
        super("item_transfer");
        MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, this::eventItem);
    }

    public void eventItem(EventIO.Item event) {
        if (event.routePack.isEmpty()) {
            return;
        }
        List<RoutePack<ItemStack>> list = event.routePack.getAll();
        List<List<RoutePack.RouteCell<Double>>> route = new ArrayList<>(list.size());
        for (RoutePack<ItemStack> itemStackRoutePack : list) {
            List<RoutePack.RouteCell<Double>> cells = new ArrayList<>();
            for (RoutePack.RouteCell<ItemStack> itemStackRouteCell : itemStackRoutePack.routeCellList) {
                if (itemStackRouteCell.data().isEmpty()) {
                    continue;
                }
                cells.add(new RoutePack.RouteCell<>(itemStackRouteCell.start(), itemStackRouteCell.end(), 1D));
            }
            if (!cells.isEmpty()) {
                route.add(cells);
            }
        }
        this.add(event.level, route, ColorPrefab.ITEM_IO, null);
    }

    @Override
    public Extension.Data_2<Float, List<Particle>> run(ClientLevel world, Pos start, Pos end, DuskColor color, double density, @Nullable ResourceLocation resourceLocation) {
        List<Particle> list = new ArrayList<>();
            {
                int dis = (int) (start.distance(end) * speed);
                Pos direction = Pos.movePos(start, end, dis);
                list.add(new DefaultParticle(world, start, DEFAULT, color, direction, size, dis));
            }
            for (int ii = 0; ii < subsidiaryAmount; ii++) {
                Pos _end = end.move(Pos.randomPos(1.5, 1.5, 1.5));
                int dis = (int) start.distance(_end) * 6;
                list.add(new DefaultParticle(world, start, DEFAULT, color, Pos.movePos(start, _end, dis), subsidiarySize, dis));
            }
        return new Extension.Data_2<>(start.distance(end) * speed, list);
    }

    @Override
    public void defaultConfig() {
        speed = 6;
        size = 1.5f;
        subsidiarySize = 0.25f;
        subsidiaryAmount = 15;
    }

    @ConfigField
    public float speed;

    @ConfigField
    public float size;

    @ConfigField
    public float subsidiarySize;

    @ConfigField
    public float subsidiaryAmount;
}
