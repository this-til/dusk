package com.til.dusk.common.register.multiblock;

import com.til.dusk.Dusk;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.common.register.RegisterManage;
import com.til.dusk.common.register.multiblock.multiblocks.*;
import com.til.dusk.util.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;

import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public abstract class MultiBlock<D> extends RegisterBasics<MultiBlock<D>> {
    public static Supplier<IForgeRegistry<MultiBlock<?>>> MULTI_BLOCK_REGISTERS;

    public static T1Accelerate t1Accelerate;
    public static T2Accelerate t2Accelerate;
    public static T3Accelerate t3Accelerate;
    public static T4Accelerate t4Accelerate;
    public static T5Accelerate t5Accelerate;
    public static T6Accelerate t6Accelerate;
    public static T7Accelerate t7Accelerate;
    public static T8Accelerate t8Accelerate;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        MULTI_BLOCK_REGISTERS = RegisterManage.create(Util.forcedConversion(MultiBlock.class), new ResourceLocation(Dusk.MOD_ID, "multi_block"), event);
        t1Accelerate = new T1Accelerate();
        t2Accelerate = new T2Accelerate();
        t3Accelerate = new T3Accelerate();
        t4Accelerate = new T4Accelerate();
        t5Accelerate = new T5Accelerate();
        t6Accelerate = new T6Accelerate();
        t7Accelerate = new T7Accelerate();
        t8Accelerate = new T8Accelerate();
    }

    public MultiBlock(ResourceLocation name) {
        super(name, Util.forcedConversion(MULTI_BLOCK_REGISTERS));
    }

    public MultiBlock(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    /***
     * 验证多方块结构是否完成
     * @param level 世界
     * @param core 中心店
     * @param d 自定义数据
     * @return 是否完成
     */
    public abstract boolean isCompleted(Level level, BlockPos core, D d);

}
