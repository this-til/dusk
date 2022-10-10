package com.til.dusk.common.register.particle_register.particle_registers;

import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.register.particle_register.ParticleParsingMode;
import com.til.dusk.common.register.particle_register.ParticleRegister;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.Extension;
import com.til.dusk.util.Pos;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class BlockParticle extends ParticleRegister {

    public BlockParticle() {
        super("block");
        particleParsingMode = ParticleParsingMode.SINGLE;
    }

    @Override
    public Extension.Data_2<Float, List<Particle>> run(ClientLevel world, Pos pos, DuskColor color, double density, @Nullable ResourceLocation resourceLocation) {
        List<Particle> list = new ArrayList<>();
        Pos p1 = pos.move(-0.5, -0.5, -0.5);
        Pos p2 = p1.addX(1);
        Pos p3 = p1.addZ(1);
        Pos p4 = p1.move(1, 0, 1);
        Pos p5 = p1.addY(1);
        Pos p6 = p5.addX(1);
        Pos p7 = p5.addZ(1);
        Pos p8 = p5.move(1, 0, 1);
        Extension.Data_2[] l = new Extension.Data_2[]{
                new Extension.Data_2<>(p1, p2),
                new Extension.Data_2<>(p1, p3),
                new Extension.Data_2<>(p4, p2),
                new Extension.Data_2<>(p4, p3),
                new Extension.Data_2<>(p5, p6),
                new Extension.Data_2<>(p5, p7),
                new Extension.Data_2<>(p8, p6),
                new Extension.Data_2<>(p8, p7),
                new Extension.Data_2<>(p1, p5),
                new Extension.Data_2<>(p2, p6),
                new Extension.Data_2<>(p3, p7),
                new Extension.Data_2<>(p4, p8),
        };
        for (Extension.Data_2 posPosData_2 : l) {
            Extension.Data_2<Float, List<Particle>> data_2 = line.run(world, (Pos) posPosData_2.d1(), (Pos) posPosData_2.d2(), color, density, resourceLocation);
            if (data_2 != null) {
                list.addAll(data_2.d2());
            }
        }
        return new Extension.Data_2<>(0f, list);
    }

    @Override
    public void defaultConfig() {

    }
}
