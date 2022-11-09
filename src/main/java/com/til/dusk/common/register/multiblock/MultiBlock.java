package com.til.dusk.common.register.multiblock;

import com.til.dusk.Dusk;
import com.til.dusk.common.config.ConfigField;
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
import org.checkerframework.checker.units.qual.C;

import java.util.List;
import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public abstract class MultiBlock<D> extends RegisterBasics<MultiBlock<D>> {
    public static Supplier<IForgeRegistry<MultiBlock<?>>> MULTI_BLOCK_REGISTERS;

    public static AccelerateMultiBlock accelerate;
    public static MK1MultiBlock mk1;
    public static MK2MultiBlock mk2;
    public static MK3MultiBlock mk3;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        MULTI_BLOCK_REGISTERS = RegisterManage.create(Util.forcedConversion(MultiBlock.class), new ResourceLocation(Dusk.MOD_ID, "multi_block"), event);
        accelerate = new AccelerateMultiBlock();
        mk1 = new MK1MultiBlock();
        mk2 = new MK2MultiBlock();
        mk3 = new MK3MultiBlock();
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
    public boolean isCompleted(Level level, BlockPos core, D d) {
        for (IMultiBlockPack<D> diMultiBlockPack : getMultiBlockPack(d)) {
            if (!diMultiBlockPack.isCompleted(level, core, d)) {
                return false;
            }
        }
        return true;
    }

    public abstract List<DefaultBlockPack> asDefaultBlockPack(D d);

    public abstract List<IMultiBlockPack<D>> getMultiBlockPack(D d);

}
