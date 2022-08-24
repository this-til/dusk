package com.til.dusk.common.register.key;

import com.til.dusk.Dusk;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.register.BindType;
import com.til.dusk.common.register.CapabilityRegister;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.common.register.tag_tool.TagTool;
import com.til.dusk.common.world.ModItem;
import com.til.dusk.util.Lang;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;

import java.awt.*;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class KeyRegister extends RegisterBasics<KeyRegister> {

    public static Supplier<IForgeRegistry<KeyRegister>> KEY_REGISTER;

    public static KeyRegister switchBindType;
    public static KeyRegister showBindState;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        KEY_REGISTER = event.create(new RegistryBuilder<KeyRegister>().setName(new ResourceLocation(Dusk.MOD_ID, "key_register")));
        switchBindType = new KeyRegister("switch_bind_type");
        showBindState = new KeyRegister("show_bind_state");
    }


    public KeyRegister(ResourceLocation name) {
        super(name, KEY_REGISTER);
    }

    public KeyRegister(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    public void run(Supplier<NetworkEvent.Context> supplier) {
        MinecraftForge.EVENT_BUS.post(new EventKey(this, supplier));
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

}
