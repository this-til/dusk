package com.til.dusk.client.register;

import com.til.dusk.Dusk;
import com.til.dusk.client.particle.DefaultParticle;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.util.Extension;
import com.til.dusk.util.Pos;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
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
    /***
     * 线
     */
    public static ClientParticleRegister line;
    /***
     * 围绕一个方块
     */
    public static ClientParticleRegister block;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        CLIENT_PARTICLE_REGISTER = event.create(new RegistryBuilder<ClientParticleRegister>().setName(new ResourceLocation(Dusk.MOD_ID, "client_particle_register")));
        air = new ClientParticleRegister("air") {
        };
        manaTransfer = new ClientParticleRegister("mana_transfer") {
            @Override
            public Extension.Data_2<Float, List<Particle>> run(ClientLevel world, Pos start, Pos end, Color color, double density, @Nullable ResourceLocation resourceLocation) {
                List<Particle> list = new ArrayList<>();
                for (int i = 0; i < density; i++) {
                    Pos _end = end.move(Pos.randomPos());
                    int dis = (int) start.distance(_end) * 3;
                    list.add(new DefaultParticle(world, start, DEFAULT, color, Pos.movePos(start, _end, dis), 0.25f, dis));
                }
                return new Extension.Data_2<>(start.distance(end) * 3, list);
            }
        };
        itemTransfer = new ClientParticleRegister("item_transfer") {
            @Override
            public Extension.Data_2<Float, List<Particle>> run(ClientLevel world, Pos start, Pos end, Color color, double density, @Nullable ResourceLocation resourceLocation) {
                List<Particle> list = new ArrayList<>();
                for (int i = 0; i < density; i++) {
                    {
                        int dis = (int) start.distance(end) * 6;
                        Pos direction = Pos.movePos(start, end, dis);
                        list.add(new DefaultParticle(world, start, DEFAULT, color, direction, 1.5f, dis));
                    }
                    for (int ii = 0; ii < 15; ii++) {
                        Pos _end = end.move(Pos.randomPos(1.5, 1.5, 1.5));
                        int dis = (int) start.distance(_end) * 6;
                        list.add(new DefaultParticle(world, start, DEFAULT, color, Pos.movePos(start, _end, dis), 0.25f, dis));
                    }
                }
                return new Extension.Data_2<>(start.distance(end) * 6, list);
            }
        };
        fluidTransfer = new ClientParticleRegister("fluid_transfer") {
            @Override
            public Extension.Data_2<Float, List<Particle>> run(ClientLevel world, Pos start, Pos end, Color color, double density, @Nullable ResourceLocation resourceLocation) {
                if (resourceLocation != null) {
                    FluidType fluidType = ForgeRegistries.FLUID_TYPES.get().getValue(resourceLocation);
                    if (fluidType != null) {
                        IClientFluidTypeExtensions clientFluidTypeExtensions = IClientFluidTypeExtensions.of(fluidType);
                        color = new Color(clientFluidTypeExtensions.getTintColor());
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
        };
        line = new ClientParticleRegister("line") {
            @Override
            public Extension.Data_2<Float, List<Particle>> run(ClientLevel world, Pos start, Pos end, Color color, double density, @Nullable ResourceLocation resourceLocation) {
                List<Particle> list = new ArrayList<>();
                Pos _start = new Pos(start);
                int dis = (int) (start.distance(end) * density);
                Pos movePos = Pos.movePos(start, end, (start.distance(end) * density));
                for (int i = 0; i < dis; i++) {
                    list.add(new DefaultParticle(world, _start, DEFAULT, color, new Pos(), 0.1f, 40));
                    _start = _start.move(movePos);
                }
                return new Extension.Data_2<>(40f, list);
            }
        };
        block = new ClientParticleRegister("block") {

            @Override
            public Extension.Data_2<Float, List<Particle>> run(ClientLevel world, Pos pos, Color color, double density, @Nullable ResourceLocation resourceLocation) {
                List<Particle> list = new ArrayList<>();
                Pos p1 = pos.move(-0.5, -0.5, -0.5);
                Pos p2 = p1.addX(1);
                Pos p3 = p1.addZ(1);
                Pos p4 = p1.move(1, 0, 1);
                Pos p5 = p1.addY(1);
                Pos p6 = p5.addX(1);
                Pos p7 = p5.addZ(1);
                Pos p8 = p5.move(1, 0, 1);
                Extension.Data_2[] l = new Extension.Data_2[]{
                        new Extension.Data_2<>(p1, p2),
                        new Extension.Data_2<>(p1, p3),
                        new Extension.Data_2<>(p4, p2),
                        new Extension.Data_2<>(p4, p3),
                        new Extension.Data_2<>(p5, p6),
                        new Extension.Data_2<>(p5, p7),
                        new Extension.Data_2<>(p8, p6),
                        new Extension.Data_2<>(p8, p7),
                        new Extension.Data_2<>(p1, p5),
                        new Extension.Data_2<>(p2, p6),
                        new Extension.Data_2<>(p3, p7),
                        new Extension.Data_2<>(p4, p8),
                };
                for (Extension.Data_2 posPosData_2 : l) {
                    Extension.Data_2<Float, List<Particle>> data_2 = line.run(world, (Pos) posPosData_2.d1(), (Pos) posPosData_2.d2(), color, density, resourceLocation);
                    if (data_2 != null) {
                        list.addAll(data_2.d2());
                    }
                }
                return new Extension.Data_2<>(0f, list);
            }

        }.setSingle(ParsingMode.SINGLE);
    }

    public ParsingMode parsingMode = ParsingMode.SPELL;

    public ClientParticleRegister(ResourceLocation name) {
        super(name, CLIENT_PARTICLE_REGISTER);
    }

    public ClientParticleRegister(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    public ClientParticleRegister setSingle(ParsingMode parsingMode) {
        this.parsingMode = parsingMode;
        return this;
    }

    /***
     * 实现粒子效果
     * @param world 当前的世界
     * @param start 开始点
     * @param end 结束点
     * @param color 颜色
     * @param density 密度
     * @return 返回粒子是生命用于拼接
     */
    @Nullable
    public Extension.Data_2<Float, List<Particle>> run(ClientLevel world, Pos start, Pos end, Color color, double density, @Nullable ResourceLocation resourceLocation) {
        return null;
    }

    @Nullable
    public Extension.Data_2<Float, List<Particle>> run(ClientLevel world, Pos pos, Color color, double density, @Nullable ResourceLocation resourceLocation) {
        return null;
    }

    public static final ResourceLocation DEFAULT = new ResourceLocation(Dusk.MOD_ID, "textures/particle/modparticle.png");


    public enum ParsingMode {
        /***
         * 拼接，根据坐标数组(0,1)(1,2)
         */
        SPELL,
        /***
         * 配对(0,1)(2,3)
         */
        PAIR,
        /***
         * 单个位置一次
         */
        SINGLE

    }
}
