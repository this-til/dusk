package com.til.dusk.common.register.other;

import com.til.dusk.Dusk;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.mana_handle.IManaHandle;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.capability.shaped_drive.IShapedDrive;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.Lang;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
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
    public static BindTypeBindCapability<IPosTrack> posTrack;
    public static BindType relayIn;
    public static BindType relayOut;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        BIND_TYPE = event.create(new RegistryBuilder<BindType>().setName(new ResourceLocation(Dusk.MOD_ID, "bind_type")));

        itemIn = new BindTypeBindCapability<>("item_in", new DuskColor(43, 255, 33), Lang.getKey("capability.item_handler"), () -> ForgeCapabilities.ITEM_HANDLER);
        itemOut = new BindTypeBindCapability<>("item_out", new DuskColor(65, 112, 62), Lang.getKey("capability.item_handler"), () -> ForgeCapabilities.ITEM_HANDLER);
        manaIn = new BindTypeBindCapability<>("mana_in", new DuskColor(255, 255, 0), Lang.getKey("capability.mana_handler"), () -> CapabilityRegister.iManaHandle.capability);
        manaOut = new BindTypeBindCapability<>("mana_out", new DuskColor(129, 129, 72), Lang.getKey("capability.mana_handler"), () -> CapabilityRegister.iManaHandle.capability);
        fluidIn = new BindTypeBindCapability<>("fluid_in", new DuskColor(29, 237, 255), Lang.getKey("capability.fluid_handler"), () -> ForgeCapabilities.FLUID_HANDLER);
        fluidOut = new BindTypeBindCapability<>("fluid_out", new DuskColor(68, 124, 129), Lang.getKey("capability.fluid_handler"), () -> ForgeCapabilities.FLUID_HANDLER);
        modelStore = new BindTypeBindCapability<>("model_store", new DuskColor(204, 147, 255), Lang.getKey("capability.shaped_drive "), () -> CapabilityRegister.iShapedDrive.capability);
        posTrack = new BindTypeBindCapability<>("pos_track", new DuskColor(69, 187, 178, 255), Lang.getKey("capability.pos_track"), () -> CapabilityRegister.iPosTrack.capability);
        relayIn = new BindType("relay_in", new DuskColor(255, 0, 0));
        relayOut = new BindType("relay_out", new DuskColor(141, 74, 74));

    }

    public final DuskColor color;

    public BindType(ResourceLocation name, DuskColor color) {
        super(name, BIND_TYPE);
        this.color = color;
    }

    public BindType(String name, DuskColor color) {
        this(new ResourceLocation(Dusk.MOD_ID, name), color);
    }


    /***
     * 过滤绑定
     * @param iControl 控制器
     * @param iPosTrack 要绑定的方块
     * @return 如果通过返回NULL，如果不通过返回结果信息
     */
    @Nullable
    public Component filter(IControl iControl, IPosTrack iPosTrack) {
        return null;
    }

    public static class BindTypeBindCapability<C> extends BindType {

        public Capability<C> capability;
        public Supplier<Capability<C>> supplier;

        public final String capabilityName;

        public BindTypeBindCapability(String name, DuskColor color, String capabilityName, Supplier<Capability<C>> supplier) {
            this(new ResourceLocation(Dusk.MOD_ID, name), color, capabilityName, supplier);
        }

        public BindTypeBindCapability(ResourceLocation resourceLocation, DuskColor color, String capabilityName, Supplier<Capability<C>> supplier) {
            super(resourceLocation, color);
            Dusk.instance.modEventBus.addListener(this::commonSetup);
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
        public Component filter(IControl iControl, IPosTrack blockEntity) {
            LazyOptional<C> lazyOptional = blockEntity.getAsCapabilityProvider().getCapability(capability);
            if (lazyOptional.isPresent()) {
                return null;
            }
            return Component.translatable("绑定失败，绑定方块没有[%s]的能力", Component.translatable(capabilityName));
        }

    }
}