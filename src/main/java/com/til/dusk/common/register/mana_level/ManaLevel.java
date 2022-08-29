package com.til.dusk.common.register.mana_level;

import com.til.dusk.Dusk;
import com.til.dusk.common.register.RegisterBasics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.*;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ManaLevel extends RegisterBasics.UnitRegister<ManaLevel, ManaLevelItem, ManaLevelBlock, ManaLevelFluid> {

    public static Supplier<IForgeRegistry<ManaLevel>> LEVEL;

    public static ManaLevel t1;
    public static ManaLevel t2;
    public static ManaLevel t3;
    public static ManaLevel t4;
    public static ManaLevel t5;
    public static ManaLevel t6;
    public static ManaLevel t7;
    public static ManaLevel t8;

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onEvent(NewRegistryEvent event) {
        LEVEL = event.create(new RegistryBuilder<ManaLevel>().setName(new ResourceLocation(Dusk.MOD_ID, "mana_level")));
        t1 = new ManaLevel(1, 2560, 1, 2, 1, new Color(50, 255, 255), null, () -> t2);
        t2 = new ManaLevel(2, 1280, 2, 2, 1.2, new Color(100, 200, 225), () -> t1, () -> t3);
        t3 = new ManaLevel(3, 640, 3, 4, 1.4, new Color(125, 150, 200), () -> t2, () -> t4);
        t4 = new ManaLevel(4, 320, 4, 4, 1.6, new Color(150, 100, 175), () -> t3, () -> t5);
        t5 = new ManaLevel(5, 160, 5, 8, 1.8, new Color(175, 100, 150), () -> t4, () -> t6);
        t6 = new ManaLevel(6, 80, 6, 8, 2, new Color(200, 150, 120), () -> t5, () -> t7);
        t7 = new ManaLevel(7, 40, 7, 16, 2.2, new Color(225, 200, 100), () -> t6, () -> t8);
        t8 = new ManaLevel(8, 20, 8, 16, 2.4, new Color(255, 255, 50), () -> t7, null);
    }

    /***
     * 等级序列
     */
    public final int level;
    /***
     * 机器运行时的时钟时间
     */
    public final int clock;

    /***
     * 机器并行次数
     */
    public final int parallel;

    /***
     * 最大绑定数量
     */
    public final int maxBind;

    /***
     * 配方加速
     */
    public final double accelerate;

    /***
     * 机器颜色
     */
    public final Color color;

    /***
     * 上一级
     */
    @Nullable
    public final Supplier<ManaLevel> up;

    /***
     * 下一级
     */
    @Nullable
    public final Supplier<ManaLevel> next;

    public ManaLevel(int level, int clock, int parallel, int maxBind, double accelerate, Color color, @Nullable Supplier<ManaLevel> up, @Nullable Supplier<ManaLevel> next) {
        this(new ResourceLocation(Dusk.MOD_ID, "t" + level), level, clock, parallel, maxBind, accelerate, color, up, next);
    }

    public ManaLevel(ResourceLocation name, int level, int clock, int parallel, int maxBind, double accelerate, Color color, @Nullable Supplier<ManaLevel> up, @Nullable Supplier<ManaLevel> next) {
        super(name, LEVEL);
        this.level = level;
        this.clock = clock;
        this.parallel = parallel;
        this.maxBind = maxBind;
        this.accelerate = accelerate;
        this.color = color;
        this.up = up;
        this.next = next;
    }


    @Override
    public Supplier<IForgeRegistry<ManaLevelItem>> itemRegistry() {
        return ManaLevelItem.LEVEL_ITEM;
    }

    @Override
    public Supplier<IForgeRegistry<ManaLevelBlock>> blockRegistry() {
        return ManaLevelBlock.LEVEL_BLOCK;
    }

    @Override
    public Supplier<IForgeRegistry<ManaLevelFluid>> fluidRegistry() {
        return ManaLevelFluid.LEVEL_FLUID;
    }
}
