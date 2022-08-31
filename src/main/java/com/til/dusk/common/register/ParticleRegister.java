package com.til.dusk.common.register;

import com.til.dusk.Dusk;
import com.til.dusk.client.register.ClientParticleRegister;
import com.til.dusk.common.event.EventIO;
import com.til.dusk.util.Pos;
import com.til.dusk.util.prefab.ColorPrefab;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ParticleRegister extends RegisterBasics<ParticleRegister> {

    public static Supplier<IForgeRegistry<ParticleRegister>> PARTICLE_REGISTER;

    public static final Map<ServerLevel, List<Data>> MAP = new HashMap<>();
    public static final Map<ServerPlayer, List<Data>> PLAYER_MAP = new HashMap<>();

    /***
     * 空粒子效果
     */
    public static ParticleRegister air;
    /***
     * 灵气转移
     */
    public static ParticleRegister manaTransfer;
    /***
     * 物品转移
     */
    public static ParticleRegister itemTransfer;
    /***
     * 流体转移
     */
    public static ParticleRegister fluidTransfer;
    /***
     * 线
     */
    public static ParticleRegister line;
    /***
     * 围绕一个方块
     */
    public static ParticleRegister block;


    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        PARTICLE_REGISTER = event.create(new RegistryBuilder<ParticleRegister>().setName(new ResourceLocation(Dusk.MOD_ID, "particle_register")));
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, (Consumer<TickEvent.ServerTickEvent>) serverTickEvent -> {
            MAP.forEach((k, v) -> {
                List<ServerPlayer> serverPlayerList = k.getPlayers(p -> true);
                v.forEach(d -> serverPlayerList.forEach(player -> MessageRegister.particleRegisterMessage.sendToPlayerClient(d, player)));
            });
            PLAYER_MAP.forEach((k, v) -> v.forEach(d -> MessageRegister.particleRegisterMessage.sendToPlayerClient(d, k)));
            MAP.clear();
            PLAYER_MAP.clear();
        });
        air = new ParticleRegister("air");
        manaTransfer = new ParticleRegister("mana_transfer") {

            final float manaThreshold = 320f;
            final Random random = new Random();

            @Override
            public void registerSubsidiaryBlack() {
                MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, (Consumer<EventIO.Mana>) event -> {
                    if (event.mana < manaThreshold) {
                        if (random.nextFloat() < event.mana / manaThreshold) {
                            this.add(event.level,
                                    ColorPrefab.MANA_IO,
                                    1,
                                    event.pos);
                        }
                    } else {
                        this.add(event.level,
                                ColorPrefab.MANA_IO,
                                event.mana / manaThreshold
                                , event.pos);
                    }
                });
            }
        };
        itemTransfer = new ParticleRegister("item_transfer") {
            @Override
            public void registerSubsidiaryBlack() {
                MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, (Consumer<EventIO.Item>) event -> {
                    this.add(event.level,
                            ColorPrefab.ITEM_IO,
                            1,
                            event.pos);
                });
            }
        };
        fluidTransfer = new ParticleRegister("fluid_transfer") {

            @Override
            public void registerSubsidiaryBlack() {
                MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, (Consumer<EventIO.Fluid>) event -> {
                    this.add(event.level,
                            ColorPrefab.FLUID_IO,
                            event.fluidStack.getAmount() / 128f,
                            event.pos);
                });
            }
        };
        line = new ParticleRegister("line");
        block = new ParticleRegister("block");
    }

    public ParticleRegister(ResourceLocation name) {
        super(name, PARTICLE_REGISTER);

    }

    public ParticleRegister(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    public void add(Level world, Color color, double density, Pos... pos) {
        if (world instanceof ServerLevel serverLevel) {
            MAP.computeIfAbsent(serverLevel, k -> new ArrayList<>()).add(new Data(name.toString(), color, density, pos));
            return;
        }
        throw new RuntimeException("在服务端了粒子创建中出现了非服务端的世界");
    }

    public void add(Player player, Color color, double density, Pos... pos) {
        if (player instanceof ServerPlayer serverPlayer) {
            PLAYER_MAP.computeIfAbsent(serverPlayer, k -> new ArrayList<>()).add(new Data(name.toString(), color, density, pos));
            return;
        }
        throw new RuntimeException("在服务端了粒子创建中出现了非服务端的玩家");
    }

    public static class Data {
        /***
         * 粒子类型
         */
        public String type;

        public Pos[] pos;

        /***
         * 颜色
         */
        public Color color;
        /***
         * 密度
         */
        public double density;

        public Data(String type, Color color, double density, Pos... pos) {
            this.type = type;
            this.color = color;
            this.density = density;
            this.pos = pos;
            if (this.pos == null) {
                this.pos = new Pos[0];
            }
        }


    }

}
