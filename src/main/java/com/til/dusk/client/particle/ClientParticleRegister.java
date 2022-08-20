package com.til.dusk.client.particle;

import com.til.dusk.Dusk;
import com.til.dusk.common.particle.CommonParticle;
import com.til.dusk.util.Pos;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public enum ClientParticleRegister implements IClientParticleRegister {

    /***
     * 空效果
     */
    AIR {
        @Override
        public void run(ClientLevel world, Pos start, Pos end, Color color, double density) {

        }
    },
    MANA_TRANSFER {
        @Override
        public void run(ClientLevel world, Pos start, Pos end, Color color, double density) {
            Pos r = Pos.getRandomPos();
            end.toMove(r);

            int dis = (int) start.getDistance(end) * 3;
            for (int i = 0; i < density; i++) {
                Pos direction = Pos.getMovePos(start, end, dis);
                Minecraft.getInstance().particleEngine.add(new DefaultParticle(world, start, DEFAULT, color, direction, 0.25f, dis));
            }

        }
    },
    ITEM_TRANSFER {
        @Override
        public void run(ClientLevel world, Pos start, Pos end, Color color, double density) {
            for (int i = 0; i < density; i++) {
                {
                    int dis = (int) start.getDistance(end) * 6;
                    Pos direction = Pos.getMovePos(start, end, dis);
                    Minecraft.getInstance().particleEngine.add(new DefaultParticle(world, start, DEFAULT, color, direction, 1.5f, dis));
                }
                for (int ii = 0; ii < 15; ii++) {
                    Pos p = Pos.getRandomPos(1.5, 1.5, 1.5);
                    Pos e = new Pos(p.getX() + end.getX(), p.getY() + end.getY(), p.getZ() + end.getZ());
                    int dis = (int) start.getDistance(e) * 6;
                    Pos direction = Pos.getMovePos(start, e, dis);
                    Minecraft.getInstance().particleEngine.add(new DefaultParticle(world, start, DEFAULT, color, direction, 0.25f, dis));
                }
            }
        }
    },
    FLUID_TRANSFER {
        @Override
        public void run(ClientLevel world, Pos start, Pos end, Color color, double density) {
            Pos r = Pos.getRandomPos();
            end.toMove(r);

            int dis = (int) start.getDistance(end) * 6;
            for (int i = 0; i < density; i++) {
                Pos direction = Pos.getMovePos(start, end, dis);
                Minecraft.getInstance().particleEngine.add(new DefaultParticle(world, start, DEFAULT, color, direction, 0.25f, dis));
            }
        }
    };

    @Override
    public String type() {
        return toString();
    }

    public static final ResourceLocation DEFAULT = new ResourceLocation(Dusk.MOD_ID, "textures/particle/modparticle.png");

    public static final Map<String, IClientParticleRegister> map = new HashMap<>();

    static {
        for (ClientParticleRegister value : values()) {
            map.put(value.toString(), value);
        }
    }

    public static void run(CommonParticle.Data data) {
        IClientParticleRegister iClientParticleRegister = map.get(data.type);
        if (iClientParticleRegister != null) {
            iClientParticleRegister.run(Minecraft.getInstance().level, data.start, data.end, data.color, data.density );
        }
    }
}
