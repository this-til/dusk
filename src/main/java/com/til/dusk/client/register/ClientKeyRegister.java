package com.til.dusk.client.register;

import com.mojang.blaze3d.platform.InputConstants;
import com.til.dusk.Dusk;
import com.til.dusk.common.register.RegisterBasics;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.function.Supplier;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientKeyRegister extends RegisterBasics<ClientKeyRegister> {

    public static Supplier<IForgeRegistry<ClientKeyRegister>> CLIENT_KEY_REGISTER;

    /***
     * 切换绑定类型
     * 默认 G
     */
    public static ClientKeyRegister switchBindType;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        CLIENT_KEY_REGISTER = event.create(new RegistryBuilder<ClientKeyRegister>().setName(new ResourceLocation(Dusk.MOD_ID, "client_key_register")));
        switchBindType = new ClientKeyRegister("switch_bind_type", InputConstants.KEY_G);
    }

    public final int inputId;
    public final KeyMapping keyMapping;

    public ClientKeyRegister(ResourceLocation name, int inputId) {
        super(name, CLIENT_KEY_REGISTER);
        this.inputId = inputId;
        keyMapping = new KeyMapping(name.toString(), KeyConflictContext.UNIVERSAL, InputConstants.Type.KEYSYM, inputId, Dusk.MOD_ID + ".key");
    }

    public ClientKeyRegister(String name, int inputId) {
        this(new ResourceLocation(Dusk.MOD_ID, name), inputId);
    }

}
