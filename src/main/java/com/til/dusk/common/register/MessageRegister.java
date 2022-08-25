package com.til.dusk.common.register;

import com.google.gson.Gson;
import com.til.dusk.Dusk;
import com.til.dusk.client.ClientTransfer;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.register.key.KeyRegister;
import com.til.dusk.util.Pos;
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
                friendlyByteBuf.writeUtf(data.type);
                data.start.write(friendlyByteBuf);
                data.end.write(friendlyByteBuf);
                friendlyByteBuf.writeInt(data.color.getRGB());
                friendlyByteBuf.writeDouble(data.density);
            }

            @Override
            public ParticleRegister.Data decoder(FriendlyByteBuf friendlyByteBuf) {
                return new ParticleRegister.Data(friendlyByteBuf.readUtf(), new Pos(friendlyByteBuf), new Pos(friendlyByteBuf), new Color(friendlyByteBuf.readInt()), friendlyByteBuf.readDouble());
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
