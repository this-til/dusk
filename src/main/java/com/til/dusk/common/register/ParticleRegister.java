package com.til.dusk.common.register;

import com.til.dusk.Dusk;
import com.til.dusk.common.event.EventIO;
import com.til.dusk.util.Pos;
import com.til.dusk.util.prefab.ColorPrefab;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Supplier;

import static com.til.dusk.common.capability.handle.ShapedHandle.rand;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.DEDICATED_SERVER)
public class ParticleRegister extends RegisterBasics<ParticleRegister> {

    public static Supplier<IForgeRegistry<ParticleRegister>> PARTICLE_REGISTER;

    public static final Map<Level, List<Data>> MAP = new HashMap<>();

    /***
     * 空粒子效果
     */
    public static ParticleRegister air;
    /***
     * 灵气转移
     */
    public static ParticleRegister manaTransfer;
    /***
     * 物品转移
     */
    public static ParticleRegister itemTransfer;
    /***
     * 流体转移
     */
    public static ParticleRegister fluidTransfer;


    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        PARTICLE_REGISTER = event.create(new RegistryBuilder<ParticleRegister>().setName(new ResourceLocation(Dusk.MOD_ID, "particle_register")));
        air = new ParticleRegister("air");
        manaTransfer = new ParticleRegister("mana_transfer") {

            final float manaThreshold = 320f;
            final Random random = new Random();

            @SubscribeEvent
            public void onEvent(EventIO.Mana event) {
                if (event.mana < manaThreshold && random.nextFloat() < event.mana / manaThreshold) {
                    if (random.nextFloat() < event.mana / manaThreshold) {
                        this.add(event.level,
                                event.start,
                                event.end,
                                ColorPrefab.MANA_IO,
                                1);
                    }
                }  else {
                    this.add(event.level,
                            event.start,
                            event.end,
                            ColorPrefab.MANA_IO,
                            event.mana / manaThreshold);
                }


            }
        };
        itemTransfer = new ParticleRegister("item_transfer") {
            @SubscribeEvent
            public void onEvent(EventIO.Item event) {
                this.add(event.level,
                        event.start,
                        event.end,
                        ColorPrefab.ITEM_IO,
                        1);
            }
        };
        fluidTransfer = new ParticleRegister("fluid_transfer") {
            @SubscribeEvent
            public void onEvent(EventIO.Fluid event) {
                this.add(event.level,
                        event.start,
                        event.end,
                        ColorPrefab.FLUID_IO,
                        event.fluidStack.getAmount() / 128f);
            }
        };
    }

    public ParticleRegister(ResourceLocation name) {
        super(name, PARTICLE_REGISTER);

    }

    public ParticleRegister(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    public void add(Level world, Pos start, Pos end, Color color, double density) {
        MAP.computeIfAbsent(world, k -> new ArrayList<>()).add(new Data(name.toString(), start, end, color, density));
    }

    public static class Data {
        /***
         * 粒子类型
         */
        public String type;
        /***
         * 开始点
         */
        public Pos start;
        /***
         * 结束点
         */
        public Pos end;
        /***
         * 颜色
         */
        public Color color;
        /***
         * 密度
         */
        public double density;

        public Data() {
            type = ParticleRegister.air.name.toString();
            start = new Pos();
            end = new Pos();
            color = new Color(255, 255, 255, 255);

        }

        public Data(String type, Pos start, Pos end, Color color, double density) {
            this.type = type;
            this.start = start;
            this.end = end;
            this.color = color;
            this.density = density;
        }


    }

}
