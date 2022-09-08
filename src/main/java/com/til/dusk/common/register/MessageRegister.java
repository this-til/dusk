package com.til.dusk.common.register;

import com.google.gson.Gson;
import com.til.dusk.Dusk;
import com.til.dusk.client.ClientTransfer;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.register.key.KeyRegister;
import com.til.dusk.util.Pos;
import com.til.dusk.util.RoutePack;
import com.til.dusk.util.Util;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public abstract class MessageRegister<MSG> extends RegisterBasics<MessageRegister<MSG>> {

    public static Supplier<IForgeRegistry<MessageRegister<?>>> MESSAGE_REGISTER;
    private static final Gson GSON = new Gson();
    private static final String PROTOCOL_VERSION = "1";
    public static SimpleChannel INSTANCE;

    public static int idSequence;

    public static MessageRegister<ParticleRegister.Data> particleRegisterMessage;

    public static MessageRegister<ParticleRegister.RouteData> particleRouteRegisterMessage;

    public static MessageRegister<KeyRegister.KeyData> keyMessage;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        MESSAGE_REGISTER = event.create(new RegistryBuilder<MessageRegister<?>>().setName(new ResourceLocation(Dusk.MOD_ID, "message_register")));
        INSTANCE = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(Dusk.MOD_ID, "main"),
                () -> PROTOCOL_VERSION,
                PROTOCOL_VERSION::equals,
                PROTOCOL_VERSION::equals
        );

        particleRegisterMessage = new MessageRegister<>("particle_register_message", ParticleRegister.Data.class) {
            @Override
            public void messageConsumer(ParticleRegister.Data data, Supplier<NetworkEvent.Context> supplier) {
                ClientTransfer.messageConsumer(data, supplier);
            }

            @Override
            public void encoder(ParticleRegister.Data data, FriendlyByteBuf friendlyByteBuf) {
                friendlyByteBuf.writeUtf(data.type.toString());
                friendlyByteBuf.writeInt(data.color.getRGB());
                friendlyByteBuf.writeDouble(data.density);
                friendlyByteBuf.writeInt(data.pos.length);
                for (Pos po : data.pos) {
                    po.write(friendlyByteBuf);
                }
                friendlyByteBuf.writeBoolean(data.resourceLocation != null);
                if (data.resourceLocation != null) {
                    friendlyByteBuf.writeUtf(data.resourceLocation.toString());
                }
            }

            @Override
            public ParticleRegister.Data decoder(FriendlyByteBuf friendlyByteBuf) {
                ResourceLocation type = new ResourceLocation(friendlyByteBuf.readUtf());
                Color color = new Color(friendlyByteBuf.readInt());
                double density = friendlyByteBuf.readDouble();
                int l = friendlyByteBuf.readInt();
                Pos[] pos = new Pos[l];
                for (int i = 0; i < l; i++) {
                    pos[i] = new Pos(friendlyByteBuf);
                }
                ResourceLocation resourceLocation = null;
                if (friendlyByteBuf.readBoolean()) {
                    resourceLocation = new ResourceLocation(friendlyByteBuf.readUtf());
                }
                return new ParticleRegister.Data(type, color, density, resourceLocation, pos);
            }
        };
        particleRouteRegisterMessage = new MessageRegister<>("particle_route_register_message", ParticleRegister.RouteData.class) {

            @Override
            public void encoder(ParticleRegister.RouteData data, FriendlyByteBuf friendlyByteBuf) {
                friendlyByteBuf.writeUtf(data.type.toString());
                friendlyByteBuf.writeInt(data.color.getRGB());
                friendlyByteBuf.writeInt(data.route.size());
                for (List<RoutePack.RouteCell<Double>> routeCells : data.route) {
                    friendlyByteBuf.writeInt(routeCells.size());
                    for (RoutePack.RouteCell<Double> routeCell : routeCells) {
                        routeCell.start().write(friendlyByteBuf);
                        routeCell.end().write(friendlyByteBuf);
                        friendlyByteBuf.writeDouble(routeCell.data());
                    }
                }
                friendlyByteBuf.writeBoolean(data.resourceLocation != null);
                if (data.resourceLocation != null) {
                    friendlyByteBuf.writeUtf(data.resourceLocation.toString());
                }
            }

            @Override
            public ParticleRegister.RouteData decoder(FriendlyByteBuf friendlyByteBuf) {
                ResourceLocation type = new ResourceLocation(friendlyByteBuf.readUtf());
                Color color = new Color(friendlyByteBuf.readInt());
                int l = friendlyByteBuf.readInt();
                List<List<RoutePack.RouteCell<Double>>> pack = new ArrayList<>(l);
                for (int i = 0; i < l; i++) {
                    int ll = friendlyByteBuf.readInt();
                    List<RoutePack.RouteCell<Double>> data = new ArrayList<>(ll);
                    for (int ii = 0; ii < ll; ii++) {
                        data.add(new RoutePack.RouteCell<>(new Pos(friendlyByteBuf), new Pos(friendlyByteBuf), friendlyByteBuf.readDouble()));
                    }
                    pack.add(data);
                }
                ResourceLocation resourceLocation = null;
                if (friendlyByteBuf.readBoolean()) {
                    resourceLocation = new ResourceLocation(friendlyByteBuf.readUtf());
                }
                return new ParticleRegister.RouteData(pack, type, color, resourceLocation);
            }

            @Override
            public void messageConsumer(ParticleRegister.RouteData routeData, Supplier<NetworkEvent.Context> supplier) {
                ClientTransfer.messageConsumer(routeData, supplier);
            }
        };
        keyMessage = new MessageRegister<>("key_message", KeyRegister.KeyData.class) {
            @Override
            public void messageConsumer(KeyRegister.KeyData keyData, Supplier<NetworkEvent.Context> supplier) {
                ResourceLocation resourceLocation = new ResourceLocation(keyData.keyName);
                KeyRegister keyRegister = KeyRegister.KEY_REGISTER.get().getValue(resourceLocation);
                if (keyRegister == null) {
                    Dusk.instance.logger.error("在服务端不存按键{}", keyData.keyName);
                    return;
                }
                keyRegister.run(supplier);
            }
        };

    }

    public final int id;
    public final Class<MSG> msgClass;

    public MessageRegister(ResourceLocation name, Class<MSG> msgClass) {
        super(name, Util.forcedConversion(MESSAGE_REGISTER));
        id = idSequence;
        idSequence++;
        this.msgClass = msgClass;

    }

    public MessageRegister(String name, Class<MSG> msgClass) {
        this(new ResourceLocation(Dusk.MOD_ID, name), msgClass);
    }

    @Override
    public void registerSubsidiaryBlack() {
        INSTANCE.registerMessage(id, msgClass, this::encoder, this::decoder, this::messageConsumer);
    }

    public void encoder(MSG msg, FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeUtf(GSON.toJson(msg));
    }

    public MSG decoder(FriendlyByteBuf friendlyByteBuf) {
        return GSON.fromJson(friendlyByteBuf.readUtf(), msgClass);
    }

    /***
     * 消息的调用
     * @param msg 消息
     * @param supplier 运行序列
     */
    public abstract void messageConsumer(MSG msg, Supplier<NetworkEvent.Context> supplier);

    public void sendToServer(MSG msg) {
        INSTANCE.sendToServer(msg);
    }

    public void sendToPlayerClient(MSG msg, ServerPlayer player) {
        INSTANCE.sendTo(msg, player.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
    }

}
