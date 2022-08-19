package com.til.dusk.register;

import com.til.dusk.Dusk;
import com.til.dusk.capability.AllCapability;
import com.til.dusk.capability.IManaHandle;
import com.til.dusk.capability.IShapedDrive;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BindType extends RegisterBasics<BindType> {

    public static Supplier<IForgeRegistry<BindType>> BIND_TYPE;

    public static BindTypeBindCapability<IItemHandler> itemIn;
    public static BindTypeBindCapability<IItemHandler> itemOut;
    public static BindTypeBindCapability<IManaHandle> manaIn;
    public static BindTypeBindCapability<IManaHandle> manaOut;
    public static BindTypeBindCapability<IFluidHandler> fluidIn;
    public static BindTypeBindCapability<IFluidHandler> fluidOut;
    public static BindTypeBindCapability<IShapedDrive> modelStore;
    public static BindType relayIn;
    public static BindType relayOut;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        BIND_TYPE = event.create(new RegistryBuilder<BindType>().setName(new ResourceLocation(Dusk.MOD_ID, "bind_type")));

        itemIn = new BindTypeBindCapability<>("item_in", CapabilityItemHandler.ITEM_HANDLER_CAPABILITY);
        itemOut = new BindTypeBindCapability<>("item_out", CapabilityItemHandler.ITEM_HANDLER_CAPABILITY);
        manaIn = new BindTypeBindCapability<>("mana_in", AllCapability.I_MANA_HANDEL);
        manaOut = new BindTypeBindCapability<>("mana_out", AllCapability.I_MANA_HANDEL);
        fluidIn = new BindTypeBindCapability<>("fluid_in", CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY);
        fluidOut = new BindTypeBindCapability<>("fluid_out", CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY);
        modelStore = new BindTypeBindCapability<>("model_store", AllCapability.I_SHAPED_DRIVE);
        relayIn = new BindType("relay_in");
        relayOut = new BindType("relay_out");

    }

    public BindType(ResourceLocation name) {
        super(name, BIND_TYPE);
    }

    public BindType(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    public static class BindTypeBindCapability<C> extends BindType {

        public final Capability<C> capability;

        public BindTypeBindCapability(String name, Capability<C> capability) {
            this(new ResourceLocation(Dusk.MOD_ID, name), capability);
        }

        public BindTypeBindCapability(ResourceLocation resourceLocation, Capability<C> capability) {
            super(resourceLocation);
            this.capability = capability;
        }

        public Capability<C> getCapability() {
            return capability;
        }
    }
}
