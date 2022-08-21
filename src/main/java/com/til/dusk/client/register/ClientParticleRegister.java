package com.til.dusk.client.register;

import com.til.dusk.Dusk;
import com.til.dusk.client.particle.DefaultParticle;
import com.til.dusk.common.register.ParticleRegister;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.util.Pos;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;

import java.awt.*;
import java.util.function.Supplier;

/**
 * @author til
 */
@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public abstract class ClientParticleRegister extends RegisterBasics<ClientParticleRegister> {

    public static Supplier<IForgeRegistry<ClientParticleRegister>> CLIENT_PARTICLE_REGISTER;


    /***
     * 空粒子效果
     */
    public static ClientParticleRegister air;
    /***
     * 灵气转移
     */
    public static ClientParticleRegister manaTransfer;
    /***
     * 物品转移
     */
    public static ClientParticleRegister itemTransfer;
    /***
     * 流体转移
     */
    public static ClientParticleRegister fluidTransfer;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        CLIENT_PARTICLE_REGISTER = event.create(new RegistryBuilder<ClientParticleRegister>().setName(new ResourceLocation(Dusk.MOD_ID, "client_particle_register")));
        air = new ClientParticleRegister("air") {
            @Override
            public void run(ClientLevel world, Pos start, Pos end, Color color, double density) {

            }
        };
        manaTransfer = new ClientParticleRegister("mana_transfer") {
            @Override
            public void run(ClientLevel world, Pos start, Pos end, Color color, double density) {
                Pos r = Pos.getRandomPos();
                end.toMove(r);

                int dis = (int) start.getDistance(end) * 3;
                for (int i = 0; i < density; i++) {
                    Pos direction = Pos.getMovePos(start, end, dis);
                    Minecraft.getInstance().particleEngine.add(new DefaultParticle(world, start, DEFAULT, color, direction, 0.25f, dis));
                }
            }
        };
        itemTransfer = new ClientParticleRegister("item_transfer") {
            @Override
            public void run(ClientLevel world, Pos start, Pos end, Color color, double density) {
                for (int i = 0; i < density; i++) {
                    {
                        int dis = (int) start.getDistance(end) * 6;
                        Pos direction = Pos.getMovePos(start, end, dis);
                        Minecraft.getInstance().particleEngine.add(new DefaultParticle(world, start, DEFAULT, color, direction, 1.5f, dis));
                    }
                    for (int ii = 0; ii < 15; ii++) {
                        Pos p = Pos.getRandomPos(1.5, 1.5, 1.5);
                        Pos e = new Pos(p.getX() + end.getX(), p.getY() + end.getY(), p.getZ() + end.getZ());
                        int dis = (int) start.getDistance(e) * 6;
                        Pos direction = Pos.getMovePos(start, e, dis);
                        Minecraft.getInstance().particleEngine.add(new DefaultParticle(world, start, DEFAULT, color, direction, 0.25f, dis));
                    }
                }
            }
        };
        fluidTransfer = new ClientParticleRegister("fluid_transfer") {
            @Override
            public void run(ClientLevel world, Pos start, Pos end, Color color, double density) {
                Pos r = Pos.getRandomPos();
                end.toMove(r);

                int dis = (int) start.getDistance(end) * 6;
                for (int i = 0; i < density; i++) {
                    Pos direction = Pos.getMovePos(start, end, dis);
                    Minecraft.getInstance().particleEngine.add(new DefaultParticle(world, start, DEFAULT, color, direction, 0.25f, dis));
                }
            }
        };
    }

    public ClientParticleRegister(ResourceLocation name) {
        super(name, CLIENT_PARTICLE_REGISTER);
    }

    public ClientParticleRegister(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    /***
     * 实现粒子效果
     * @param world 当前的世界
     * @param start 开始点
     * @param end 结束点
     * @param color 颜色
     * @param density 密度
     */
    public abstract void run(ClientLevel world, Pos start, Pos end, Color color, double density);

    public static final ResourceLocation DEFAULT = new ResourceLocation(Dusk.MOD_ID, "textures/particle/modparticle.png");

    public static void run(ParticleRegister.Data data) {
        ClientParticleRegister iClientParticleRegister = CLIENT_PARTICLE_REGISTER.get().getValue(new ResourceLocation(data.type));
        if (iClientParticleRegister != null) {
            iClientParticleRegister.run(Minecraft.getInstance().level, data.start, data.end, data.color, data.density);
        }
    }


}
