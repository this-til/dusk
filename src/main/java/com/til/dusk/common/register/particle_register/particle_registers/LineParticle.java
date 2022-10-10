package com.til.dusk.common.register.particle_register.particle_registers;

import com.til.dusk.client.particle.DefaultParticle;
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

/**
 * @author til
 */
public class LineParticle extends ParticleRegister {

    public LineParticle() {
        super("line");
        particleParsingMode = ParticleParsingMode.PAIR;
    }

    @Override
    public Extension.Data_2<Float, List<Particle>> run(ClientLevel world, Pos start, Pos end, DuskColor color, double density, @Nullable ResourceLocation resourceLocation) {
        List<Particle> list = new ArrayList<>();
        Pos _start = new Pos(start);
        density = density * interval;
        int dis = (int) (start.distance(end) * density);
        Pos movePos = Pos.movePos(start, end, (start.distance(end) * density));
        for (int i = 0; i < dis; i++) {
            list.add(new DefaultParticle(world, _start, DEFAULT, color, new Pos(), size, (int) life));
            _start = _start.move(movePos);
        }
        return new Extension.Data_2<>(life, list);
    }

    @Override
    public void defaultConfig() {
        life = 40;
        interval = 1f;
        size = 0.1f;
    }

    @ConfigField
    public float life;

    @ConfigField
    public float interval;

    @ConfigField
    public float size;
}
