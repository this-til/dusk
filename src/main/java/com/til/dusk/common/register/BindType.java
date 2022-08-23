package com.til.dusk.common.register;

import com.til.dusk.Dusk;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.mana_handle.IManaHandle;
import com.til.dusk.common.capability.shaped_drive.IShapedDrive;
import com.til.dusk.util.Lang;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;

import javax.annotation.Nullable;
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

        itemIn = new BindTypeBindCapability<>("item_in",Lang.getKey("capability.item_handler") ,() -> ForgeCapabilities.ITEM_HANDLER);
        itemOut = new BindTypeBindCapability<>("item_out",Lang.getKey("capability.item_handler") ,() -> ForgeCapabilities.ITEM_HANDLER);
        manaIn = new BindTypeBindCapability<>("mana_in", Lang.getKey("capability.mana_handler"),() -> CapabilityRegister.iManaHandle.capability);
        manaOut = new BindTypeBindCapability<>("mana_out",Lang.getKey("capability.mana_handler") ,() -> CapabilityRegister.iManaHandle.capability);
        fluidIn = new BindTypeBindCapability<>("fluid_in", Lang.getKey("capability.fluid_handler"),() -> ForgeCapabilities.FLUID_HANDLER);
        fluidOut = new BindTypeBindCapability<>("fluid_out",Lang.getKey("capability.fluid_handler") ,() -> ForgeCapabilities.FLUID_HANDLER);
        modelStore = new BindTypeBindCapability<>("model_store",Lang.getKey("capability.shaped_drive ")  , () -> CapabilityRegister.iShapedDrive.capability);
        relayIn = new BindType("relay_in");
        relayOut = new BindType("relay_out");

    }

    public BindType(ResourceLocation name) {
        super(name, BIND_TYPE);
    }

    public BindType(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }


    /***
     * 过滤绑定
     * @param iControl 控制器
     * @param blockEntity 要绑定的方块
     * @return 如果通过返回NULL，如果不通过返回结果信息
     */
    @Nullable
    public Component filter(IControl iControl, BlockEntity blockEntity) {
        return null;
    }

    public static class BindTypeBindCapability<C> extends BindType {

        public Capability<C> capability;
        public Supplier<Capability<C>> supplier;
        public String capabilityName;

        public BindTypeBindCapability(String name, String capabilityName, Supplier<Capability<C>> supplier) {
            this(new ResourceLocation(Dusk.MOD_ID, name), capabilityName, supplier);
        }

        public BindTypeBindCapability(ResourceLocation resourceLocation, String capabilityName, Supplier<Capability<C>> supplier) {
            super(resourceLocation);
            IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
            modEventBus.addListener(this::commonSetup);
            this.supplier = supplier;
            this.capabilityName = capabilityName;
        }

        public void commonSetup(FMLCommonSetupEvent event) {
            capability = supplier.get();
            supplier = null;
        }


        public Capability<C> getCapability() {
            return capability;
        }

        @Nullable
        @Override
        public Component filter(IControl iControl, BlockEntity blockEntity) {
            LazyOptional<C> lazyOptional = blockEntity.getCapability(capability);
            if (lazyOptional.isPresent()) {
                return null;
            }
            return Component.translatable("绑定方块没有[%s]的能力", Component.translatable(capabilityName));
        }

    }
}
