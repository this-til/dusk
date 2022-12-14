package com.til.dusk.client;

import com.til.dusk.Dusk;
import com.til.dusk.common.register.particle_register.ParticleData;
import com.til.dusk.common.register.particle_register.ParticleRegister;
import com.til.dusk.common.register.particle_register.ParticleRouteData;
import com.til.dusk.util.Extension;
import com.til.dusk.util.Pos;
import com.til.dusk.util.RoutePack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/***
 * 客户端中转站
 * 用来处理客户端调用
 * @author til
 */
@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, value = Dist.CLIENT)
public class ClientTransfer {

    public static void messageConsumer(ParticleData data, Supplier<NetworkEvent.Context> supplier) {
        ResourceLocation name = data.type();
        ParticleRegister clientParticleRegister = ParticleRegister.PARTICLE_REGISTER.get().getValue(name);
        if (clientParticleRegister == null) {
            Dusk.instance.logger.error("在客户端不存在粒子效果{}", data.type());
            return;
        }
        supplier.get().enqueueWork(() -> {
            List<Extension.Data_2<Float, List<Particle>>> list = new ArrayList<>();
            switch (clientParticleRegister.particleParsingMode) {
                case PAIR -> {
                    Pos s = null;
                    Pos e = null;
                    for (Pos po : data.pos()) {
                        if (s == null) {
                            s = po;
                            continue;
                        }
                        if (e == null) {
                            e = po;
                        }
                        Extension.Data_2<Float, List<Particle>> data_2 = clientParticleRegister.run(Minecraft.getInstance().level, s, e, data.color(), data.density(), data.resourceLocation());
                        if (data_2 != null) {
                            list.add(data_2);
                        }
                        s = null;
                        e = null;
                    }
                }
                case SPELL -> {
                    if (data.pos().length >= 2) {
                        Pos s = data.pos()[0];
                        Pos e;
                        int i = 1;
                        do {
                            e = data.pos()[i];
                            Extension.Data_2<Float, List<Particle>> data_2 = clientParticleRegister.run(Minecraft.getInstance().level, s, e, data.color(), data.density(), data.resourceLocation());
                            if (data_2 != null) {
                                list.add(data_2);
                            }
                            s = e;
                            i++;
                        } while (i < data.pos().length);
                    }
                }
                case SINGLE -> {
                    for (Pos po : data.pos()) {
                        Extension.Data_2<Float, List<Particle>> data_2 = clientParticleRegister.run(Minecraft.getInstance().level, po, data.color(), data.density(), data.resourceLocation());
                        if (data_2 != null) {
                            list.add(data_2);
                        }
                    }
                }
                default -> {}
            }

            float time = 0;
            for (Extension.Data_2<Float, List<Particle>> data_2 : list) {
                addRun(time, () -> {
                    for (Particle particle : data_2.d2()) {
                        Minecraft.getInstance().particleEngine.add(particle);
                    }
                });
                time += data_2.d1();
            }
        });
        supplier.get().setPacketHandled(true);
    }

    public static void messageConsumer(ParticleRouteData data, Supplier<NetworkEvent.Context> supplier) {
        ResourceLocation name = data.type();
        ParticleRegister clientParticleRegister = ParticleRegister.PARTICLE_REGISTER.get().getValue(name);
        if (clientParticleRegister == null) {
            Dusk.instance.logger.error("在客户端不存在粒子效果{}", data.type());
            return;
        }
        supplier.get().enqueueWork(() -> {
            float time = 0;
            for (List<RoutePack.RouteCell<Double>> routeCells : data.route()) {
                float _time = 0;
                for (RoutePack.RouteCell<Double> routeCell : routeCells) {
                    Extension.Data_2<Float, List<Particle>> data_2 = clientParticleRegister.run(Minecraft.getInstance().level, routeCell.start(), routeCell.end(), data.color(), routeCell.data(), data.resourceLocation());
                    if (data_2 != null) {
                        _time = Math.max(_time, data_2.d1());
                        if (data_2.d2() != null) {
                            addRun(time, () -> {
                                for (Particle particle : data_2.d2()) {
                                    Minecraft.getInstance().particleEngine.add(particle);
                                }
                            });
                        }
                    }
                }
                time += _time;
            }
        });
        supplier.get().setPacketHandled(true);
    }

    public static final List<Extension.VariableData_2<Float, Runnable>> RUN_LIST = new ArrayList<>();

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void tick(TickEvent.ClientTickEvent event) {
        if (!event.phase.equals(TickEvent.Phase.END)) {
            return;
        }
        List<Extension.VariableData_2<Float, Runnable>> rList = null;
        for (Extension.VariableData_2<Float, Runnable> longRunnableData_2 : RUN_LIST) {
            longRunnableData_2.k--;
            if (longRunnableData_2.k <= 0) {
                longRunnableData_2.v.run();
                if (rList == null) {
                    rList = new ArrayList<>();
                }
                rList.add(longRunnableData_2);
            }
        }
        if (rList != null) {
            for (Extension.VariableData_2<Float, Runnable> longRunnableData_2 : rList) {
                RUN_LIST.remove(longRunnableData_2);
            }
            rList.clear();
        }
    }

    public static void addRun(float _time, Runnable runnable) {
        RUN_LIST.add(new Extension.VariableData_2<>(_time, runnable));
    }

}
