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

import java.util.List;
import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public abstract class MultiBlock<D> extends RegisterBasics<MultiBlock<D>> {
    public static Supplier<IForgeRegistry<MultiBlock<?>>> MULTI_BLOCK_REGISTERS;

    public static AccelerateMultiBlock accelerate;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        MULTI_BLOCK_REGISTERS = RegisterManage.create(Util.forcedConversion(MultiBlock.class), new ResourceLocation(Dusk.MOD_ID, "multi_block"), event);
        accelerate = new AccelerateMultiBlock();
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

    public abstract List<DefaultBlockPack> asDefaultBlockPack(D d);

}
