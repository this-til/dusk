package com.til.dusk.common.register;

import com.til.dusk.Dusk;
import com.til.dusk.client.register.ClientParticleRegister;
import com.til.dusk.common.event.EventIO;
import com.til.dusk.util.Pos;
import com.til.dusk.util.RoutePack;
import com.til.dusk.util.prefab.ColorPrefab;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;
import org.jetbrains.annotations.Nullable;

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
    public static final Map<ServerLevel, List<RouteData>> ROUTE_DATA = new HashMap<>();
    public static final Map<ServerPlayer, List<Data>> PLAYER_MAP = new HashMap<>();
    public static final Map<ServerPlayer, List<RouteData>> PLAYER_ROUTE_DATA = new HashMap<>();

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
        manaTransfer = new ParticleRegister("mana_transfer") {

            final float manaThreshold = 320f;
            final Random random = new Random();

            @Override
            public void registerSubsidiaryBlack() {
                MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, (Consumer<EventIO.Mana>) event -> {
                    List<RoutePack<Long>> list = event.routePack.getAll();
                    List<List<RoutePack.RouteCell<Double>>> route = new ArrayList<>(list.size());
                    for (RoutePack<Long> longRoutePack : list) {
                        List<RoutePack.RouteCell<Double>> cells = new ArrayList<>();
                        for (RoutePack.RouteCell<Long> longRouteCell : longRoutePack.routeCellList) {
                            double n = Math.floor(longRouteCell.data() / manaThreshold);
                            if (random.nextDouble() < (longRouteCell.data() % manaThreshold) / manaThreshold) {
                                n++;
                            }
                            if (n > 0) {
                                cells.add(new RoutePack.RouteCell<>(longRouteCell.start(), longRouteCell.end(), n));
                            }
                        }
                        if (!cells.isEmpty()) {
                            route.add(cells);
                        }
                    }
                    this.add(event.level, route, ColorPrefab.MANA_IO, null);
                });
            }
        };
        itemTransfer = new ParticleRegister("item_transfer") {
            @Override
            public void registerSubsidiaryBlack() {
                MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, (Consumer<EventIO.Item>) event -> {
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
                });
            }
        };
        fluidTransfer = new ParticleRegister("fluid_transfer") {

            @Override
            public void registerSubsidiaryBlack() {
                MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, (Consumer<EventIO.Fluid>) event -> {
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

    public void add(Level world, Color color, double density, ResourceLocation resourceLocation, Pos... pos) {
        if (world instanceof ServerLevel serverLevel) {
            List<Data> list;
            if (MAP.containsKey(serverLevel)) {
                list = MAP.get(serverLevel);
            } else {
                list = new ArrayList<>();
                MAP.put(serverLevel, list);
            }
            list.add(new Data(name, color, density, resourceLocation, pos));
            return;
        }
        throw new RuntimeException("在服务端了粒子创建中出现了非服务端的世界");
    }

    public void add(Level world, List<List<RoutePack.RouteCell<Double>>> route, Color color, @Nullable ResourceLocation resourceLocation) {
        if (world instanceof ServerLevel serverLevel) {
            List<RouteData> list;
            if (ROUTE_DATA.containsKey(serverLevel)) {
                list = ROUTE_DATA.get(serverLevel);
            } else {
                list = new ArrayList<>();
                ROUTE_DATA.put(serverLevel, list);
            }
            list.add(new RouteData(route, name, color, resourceLocation));
            return;
        }
        throw new RuntimeException("在服务端了粒子创建中出现了非服务端的世界");
    }

    public void add(Player player, Color color, double density, @Nullable ResourceLocation resourceLocation, Pos... pos) {
        if (player instanceof ServerPlayer serverPlayer) {
            List<Data> list;
            if (PLAYER_MAP.containsKey(serverPlayer)) {
                list = PLAYER_MAP.get(serverPlayer);
            } else {
                list = new ArrayList<>();
                PLAYER_MAP.put(serverPlayer, list);
            }
            list.add(new Data(name, color, density, resourceLocation, pos));
            return;
        }
        throw new RuntimeException("在服务端了粒子创建中出现了非服务端的玩家");
    }

    public void add(Player player, List<List<RoutePack.RouteCell<Double>>> route, Color color, @Nullable ResourceLocation resourceLocation) {
        if (player instanceof ServerPlayer serverPlayer) {
            List<RouteData> list;
            if (PLAYER_ROUTE_DATA.containsKey(serverPlayer)) {
                list = PLAYER_ROUTE_DATA.get(serverPlayer);
            } else {
                list = new ArrayList<>();
                PLAYER_ROUTE_DATA.put(serverPlayer, list);
            }
            list.add(new RouteData(route, name, color, resourceLocation));
            return;
        }
        throw new RuntimeException("在服务端了粒子创建中出现了非服务端的玩家");
    }

    public static class Data {
        /***
         * 粒子类型
         */
        public ResourceLocation type;

        public Pos[] pos;

        /***
         * 颜色
         */
        public Color color;
        /***
         * 密度
         */
        public double density;
        @Nullable
        public ResourceLocation resourceLocation;

        public Data(ResourceLocation type, Color color, double density, @Nullable ResourceLocation resourceLocation, Pos... pos) {
            this.type = type;
            this.color = color;
            this.density = density;
            this.pos = pos;
            if (this.pos == null) {
                this.pos = new Pos[0];
            }
            this.resourceLocation = resourceLocation;
        }
    }

    public static class RouteData {
        public List<List<RoutePack.RouteCell<Double>>> route;
        public ResourceLocation type;
        public Color color;
        @Nullable
        public ResourceLocation resourceLocation;

        public RouteData(List<List<RoutePack.RouteCell<Double>>> route, ResourceLocation type, Color color, @Nullable ResourceLocation resourceLocation) {
            this.route = route;
            this.type = type;
            this.color = color;
            this.resourceLocation = resourceLocation;
        }
    }

}
