package com.til.dusk.client.particle;

import com.til.dusk.util.Pos;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.awt.*;

@OnlyIn(Dist.CLIENT)
public interface IClientParticleRegister {

    String type();

    void run(ClientLevel world, Pos start, Pos end, Color color, double density);

}
