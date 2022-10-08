package com.til.dusk.common.register.key;

import com.mojang.blaze3d.platform.InputConstants;
import com.til.dusk.Dusk;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.common.register.other.MessageRegister;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class KeyRegister extends RegisterBasics<KeyRegister> {

    public static Supplier<IForgeRegistry<KeyRegister>> KEY_REGISTER;

    /***
     * 切换绑定类型
     * 默认 G
     */
    public static KeyRegister switchBindType;

    /***
     * 显示绑定状态
     * 默认 H
     */
    public static KeyRegister showBindState;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        KEY_REGISTER = event.create(new RegistryBuilder<KeyRegister>().setName(new ResourceLocation(Dusk.MOD_ID, "key_register")));
        switchBindType = new KeyRegister("switch_bind_type", InputConstants.KEY_G);
        showBindState = new KeyRegister("show_bind_state", InputConstants.KEY_H);
    }

    public final int inputId;
    public final KeyMapping keyMapping;
    public boolean lock = true;

    public KeyRegister(ResourceLocation name, int inputId) {
        super(name, KEY_REGISTER);
        this.inputId = inputId;
        keyMapping = new KeyMapping(name.toString(), KeyConflictContext.UNIVERSAL, KeyModifier.NONE, InputConstants.Type.KEYSYM, inputId, Dusk.MOD_ID + ".key");
        Dusk.instance.modEventBus.addListener(this::registerKeyMapping);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, (Consumer<TickEvent.ClientTickEvent>) c -> {
            if (keyMapping.isDown()) {
                if (lock) {
                    lock = false;
                    MessageRegister.keyMessage.sendToServer(new KeyRegister.KeyData(name.toString()));
                }
            } else {
                lock = true;
            }
        });
    }

    public KeyRegister(String name, int inputId) {
        this(new ResourceLocation(Dusk.MOD_ID, name), inputId);
    }

    public void run(Supplier<NetworkEvent.Context> supplier) {
        MinecraftForge.EVENT_BUS.post(new EventKey(this, supplier));
    }

    public void registerKeyMapping(RegisterKeyMappingsEvent registerKeyMappingsEvent) {
        registerKeyMappingsEvent.register(keyMapping);
    }

    public static class KeyData {
        public String keyName = "";

        public KeyData() {
        }

        public KeyData(String keyName) {
            this.keyName = keyName;
        }

        public KeyData(ResourceLocation resourceLocation) {
            this(resourceLocation.toString());
        }

    }

    @Override
    public void defaultConfig() {

    }
}
