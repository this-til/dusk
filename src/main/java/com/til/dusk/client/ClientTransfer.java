package com.til.dusk.client;

import com.til.dusk.Dusk;
import com.til.dusk.client.register.ClientParticleRegister;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.register.ParticleRegister;
import com.til.dusk.util.Pos;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkEvent;

import java.awt.*;
import java.util.function.Supplier;

/***
 * 客户端中转站
 * 用来处理客户端调用
 * @author til
 */
@OnlyIn(Dist.CLIENT)
public class ClientTransfer {
    public static void messageConsumer(IControl.TellPlayerMessage tellPlayerMessage, Supplier<NetworkEvent.Context> supplier) {
        Player player = Minecraft.getInstance().player;
        if (player == null) {
            return;
        }
        String text = tellPlayerMessage.key;
        if (tellPlayerMessage.keys != null) {
            String[] keys = new String[tellPlayerMessage.keys.length];
            for (int i = 0; i < tellPlayerMessage.keys.length; i++) {
                keys[i] = I18n.get(tellPlayerMessage.keys[i]);
            }
            text = I18n.get(text, (Object[]) keys);
        }
        String finalText = text;
        supplier.get().enqueueWork(() -> player.sendSystemMessage(Component.translatable(finalText, tellPlayerMessage.actionBar)));
    }

    public static void messageConsumer(ParticleRegister.Data data, Supplier<NetworkEvent.Context> supplier) {
        ResourceLocation name = new ResourceLocation(data.type);
        ClientParticleRegister clientParticleRegister = ClientParticleRegister.CLIENT_PARTICLE_REGISTER.get().getValue(name);
        if (clientParticleRegister == null) {
            Dusk.instance.logger.error("在客户端不存在粒子效果{}", data.type);
            return;
        }
        supplier.get().enqueueWork(() -> clientParticleRegister.run(Minecraft.getInstance().level,
                data.start, data.end, data.color, data.density));
        supplier.get().setPacketHandled(true);
    }
}
