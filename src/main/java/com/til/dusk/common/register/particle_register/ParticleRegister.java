package com.til.dusk.common.register.particle_register;

import com.til.dusk.Dusk;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.common.register.other.MessageRegister;
import com.til.dusk.common.register.particle_register.particle_registers.*;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.Extension;
import com.til.dusk.util.Pos;
import com.til.dusk.util.RoutePack;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ParticleRegister extends RegisterBasics<ParticleRegister> {

    public static Supplier<IForgeRegistry<ParticleRegister>> PARTICLE_REGISTER;

    public static final Map<ServerLevel, List<ParticleData>> MAP = new HashMap<>();
    public static final Map<ServerLevel, List<ParticleRouteData>> ROUTE_DATA = new HashMap<>();
    public static final Map<ServerPlayer, List<ParticleData>> PLAYER_MAP = new HashMap<>();
    public static final Map<ServerPlayer, List<ParticleRouteData>> PLAYER_ROUTE_DATA = new HashMap<>();

    /***
     * 空粒子效果
     */
    public static ParticleRegister air;
    /***
     * 灵气转移
     */
    public static ManaTransferParticle manaTransfer;
    /***
     * 物品转移
     */
    public static ItemTransferParticle itemTransfer;
    /***
     * 流体转移
     */
    public static FluidTransferParticle fluidTransfer;
    /***
     * 线
     */
    public static LineParticle line;
    /***
     * 围绕一个方块
     */
    public static BlockParticle block;


    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        PARTICLE_REGISTER = event.create(new RegistryBuilder<ParticleRegister>().setName(new ResourceLocation(Dusk.MOD_ID, "particle_register")));
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, (Consumer<TickEvent.ServerTickEvent>) serverTickEvent -> {
            MAP.forEach((k, v) -> {
                List<ServerPlayer> serverPlayerList = k.getPlayers(p -> true);
                v.forEach(d -> serverPlayerList.forEach(player -> MessageRegister.particleRegisterMessage.sendToPlayerClient(d, player)));
            });
            ROUTE_DATA.forEach((k, v) -> {
                List<ServerPlayer> serverPlayerList = k.getPlayers(p -> true);
                v.forEach(d -> serverPlayerList.forEach(player -> MessageRegister.particleRouteRegisterMessage.sendToPlayerClient(d, player)));
            });
            PLAYER_MAP.forEach((k, v) -> v.forEach(d -> MessageRegister.particleRegisterMessage.sendToPlayerClient(d, k)));
            PLAYER_ROUTE_DATA.forEach((k, v) -> v.forEach(d -> MessageRegister.particleRouteRegisterMessage.sendToPlayerClient(d, k)));
            MAP.clear();
            ROUTE_DATA.clear();
            PLAYER_MAP.clear();
        });
        air = new ParticleRegister("air");
        manaTransfer = new ManaTransferParticle();
        itemTransfer = new ItemTransferParticle();
        fluidTransfer = new FluidTransferParticle();
        line = new LineParticle();
        block = new BlockParticle();
    }

    public ParticleParsingMode particleParsingMode = ParticleParsingMode.SPELL;

    public ParticleRegister(ResourceLocation name) {
        super(name, PARTICLE_REGISTER);

    }

    public ParticleRegister(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    public void add(Level world, DuskColor color, double density, ResourceLocation resourceLocation, Pos... pos) {
        if (world instanceof ServerLevel serverLevel) {
            List<ParticleData> list;
            if (MAP.containsKey(serverLevel)) {
                list = MAP.get(serverLevel);
            } else {
                list = new ArrayList<>();
                MAP.put(serverLevel, list);
            }
            list.add(new ParticleData(name, color, density, resourceLocation, pos));
            return;
        }
        throw new RuntimeException("在服务端了粒子创建中出现了非服务端的世界");
    }

    public void add(Level world, List<List<RoutePack.RouteCell<Double>>> route, DuskColor color, @Nullable ResourceLocation resourceLocation) {
        if (world instanceof ServerLevel serverLevel) {
            List<ParticleRouteData> list;
            if (ROUTE_DATA.containsKey(serverLevel)) {
                list = ROUTE_DATA.get(serverLevel);
            } else {
                list = new ArrayList<>();
                ROUTE_DATA.put(serverLevel, list);
            }
            list.add(new ParticleRouteData(route, name, color, resourceLocation));
            return;
        }
        throw new RuntimeException("在服务端了粒子创建中出现了非服务端的世界");
    }

    public void add(Player player, DuskColor color, double density, @Nullable ResourceLocation resourceLocation, Pos... pos) {
        if (player instanceof ServerPlayer serverPlayer) {
            List<ParticleData> list;
            if (PLAYER_MAP.containsKey(serverPlayer)) {
                list = PLAYER_MAP.get(serverPlayer);
            } else {
                list = new ArrayList<>();
                PLAYER_MAP.put(serverPlayer, list);
            }
            list.add(new ParticleData(name, color, density, resourceLocation, pos));
            return;
        }
        throw new RuntimeException("在服务端了粒子创建中出现了非服务端的玩家");
    }

    public void add(Player player, List<List<RoutePack.RouteCell<Double>>> route, DuskColor color, @Nullable ResourceLocation resourceLocation) {
        if (player instanceof ServerPlayer serverPlayer) {
            List<ParticleRouteData> list;
            if (PLAYER_ROUTE_DATA.containsKey(serverPlayer)) {
                list = PLAYER_ROUTE_DATA.get(serverPlayer);
            } else {
                list = new ArrayList<>();
                PLAYER_ROUTE_DATA.put(serverPlayer, list);
            }
            list.add(new ParticleRouteData(route, name, color, resourceLocation));
            return;
        }
        throw new RuntimeException("在服务端了粒子创建中出现了非服务端的玩家");
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
    public Extension.Data_2<Float, List<Particle>> run(ClientLevel world, Pos start, Pos end, DuskColor color, double density, @javax.annotation.Nullable ResourceLocation resourceLocation) {
        return null;
    }

    @Nullable
    public Extension.Data_2<Float, List<Particle>> run(ClientLevel world, Pos pos, DuskColor color, double density, @javax.annotation.Nullable ResourceLocation resourceLocation) {
        return null;
    }

    public static final ResourceLocation DEFAULT = new ResourceLocation(Dusk.MOD_ID, "textures/particle/modparticle.png");


}
