package com.til.dusk.common.register.multiblock;

import com.mojang.blaze3d.platform.InputConstants;
import com.til.dusk.Dusk;
import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.common.register.RegisterManage;
import com.til.dusk.common.register.key.KeyRegister;
import net.minecraft.resources.ResourceLocation;
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
public abstract class MultiBlockRegisters extends RegisterBasics<MultiBlockRegisters> {
    public static Supplier<IForgeRegistry<MultiBlockRegisters>> MULTI_BLOCK_REGISTERS;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        MULTI_BLOCK_REGISTERS = RegisterManage.create(MultiBlockRegisters.class, new ResourceLocation(Dusk.MOD_ID, "multi_block"), event);
    }

    public MultiBlockRegisters(ResourceLocation name) {
        super(name, MULTI_BLOCK_REGISTERS);
    }

    public MultiBlockRegisters(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    @ConfigField
    public List<BlockPosPack> blockPosPackList;
}
