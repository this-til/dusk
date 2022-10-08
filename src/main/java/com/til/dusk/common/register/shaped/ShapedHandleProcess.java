package com.til.dusk.common.register.shaped;

import com.til.dusk.Dusk;
import com.til.dusk.common.capability.handle.EventHandle;
import com.til.dusk.common.capability.CapabilityHelp;
import com.til.dusk.common.config.ConfigMap;
import com.til.dusk.common.register.RegisterBasics;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public abstract class ShapedHandleProcess extends RegisterBasics<ShapedHandleProcess> {

    public static Supplier<IForgeRegistry<ShapedHandleProcess>> SHAPED_TYPE_PROCESS;

    public static ShapedHandleProcess production;
    public static ShapedHandleProcess out;
    public static ShapedHandleProcess trippingOperation;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        SHAPED_TYPE_PROCESS = event.create(new RegistryBuilder<ShapedHandleProcess>().setName(new ResourceLocation(Dusk.MOD_ID, "shaped_handle_process")));
        production = new ShapedHandleProcess("production") {
            @Override
            public void up(EventHandle.EventShapedHandle.Up event) {
                if (CapabilityHelp.extractMana(event.iHandle.getPosTrack(), null, event.manaIn, event.shapedHandle.consumeMana, false) < event.shapedHandle.consumeMana) {
                    event.shapedHandle.process = trippingOperation;
                    event.shapedHandle._surplusTime = event.shapedHandle.surplusTime;
                } else {
                    event.shapedHandle._surplusTime--;
                    if (event.shapedHandle._surplusTime <= 0) {
                        event.shapedHandle.process = out;
                    }
                }
            }

            @Override
            public void clock(EventHandle.EventShapedHandle.Clock event) {

            }
        };
        out = new ShapedHandleProcess("out") {

            @Override
            public void up(EventHandle.EventShapedHandle.Up event) {
                event.shapedHandle.outMana -= CapabilityHelp.addMana(event.iHandle.getPosTrack(), null, event.manaOut, event.shapedHandle.outMana, false);
            }

            @Override
            public void clock(EventHandle.EventShapedHandle.Clock event) {
                if (event.shapedHandle.outItem != null && !event.shapedHandle.outItem.isEmpty()) {
                    event.shapedHandle.outItem = CapabilityHelp.insertItem(event.iHandle.getPosTrack(), null, event.itemOut, event.shapedHandle.outItem, false);
                }
                if (event.shapedHandle.outFluid != null && !event.shapedHandle.outFluid.isEmpty()) {
                    event.shapedHandle.outFluid = CapabilityHelp.fill(event.iHandle.getPosTrack(), null, event.fluidOut, event.shapedHandle.outFluid, false);
                }
            }
        };
        trippingOperation = new ShapedHandleProcess("tripping_operation") {

            @Override
            public void up(EventHandle.EventShapedHandle.Up event) {
            }

            @Override
            public void clock(EventHandle.EventShapedHandle.Clock event) {
                event.shapedHandle.process = production;
            }
        };
    }

    public ShapedHandleProcess(ResourceLocation name) {
        super(name, SHAPED_TYPE_PROCESS);
    }

    public ShapedHandleProcess(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    public abstract void up(EventHandle.EventShapedHandle.Up event);

    public abstract void clock(EventHandle.EventShapedHandle.Clock event);

    @Nullable
    @Override
    public ConfigMap defaultConfigMap() {
        return null;
    }
}
