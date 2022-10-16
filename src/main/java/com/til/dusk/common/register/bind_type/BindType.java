package com.til.dusk.common.register.bind_type;

import com.til.dusk.Dusk;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.mana_handle.IManaHandle;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.capability.shaped_drive.IShapedDrive;
import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.common.register.RegisterManage;
import com.til.dusk.common.register.bind_type.bind_types.*;
import com.til.dusk.common.register.other.CapabilityRegister;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.Lang;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;

import javax.annotation.Nullable;
import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)

public abstract class BindType extends RegisterBasics<BindType> {

    public static Supplier<IForgeRegistry<BindType>> BIND_TYPE;

    public static BindTypeBindCapability<IItemHandler> itemIn;
    public static BindTypeBindCapability<IItemHandler> itemOut;
    public static BindTypeBindCapability<IManaHandle> manaIn;
    public static BindTypeBindCapability<IManaHandle> manaOut;
    public static BindTypeBindCapability<IFluidHandler> fluidIn;
    public static BindTypeBindCapability<IFluidHandler> fluidOut;
    public static BindTypeBindCapability<IShapedDrive> modelStore;
    public static BindTypeBindCapability<IPosTrack> posTrack;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        BIND_TYPE = RegisterManage.create(BindType.class, new ResourceLocation(Dusk.MOD_ID, "bind_type"), event);
        itemIn = new ItemInBindType();
        itemOut = new ItemOutBindType();
        manaIn = new ManaInBindType();
        manaOut = new ManaOutBindType();
        fluidIn = new FluidInBindType();
        fluidOut = new FluidOutBindType();
        modelStore = new ModelStoreBindType();
        posTrack = new PosTrackBindType();
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
     * @param iPosTrack 要绑定的方块
     * @return 如果通过返回NULL，如果不通过返回结果信息
     */
    @Nullable
    public Component filter(IControl iControl, IPosTrack iPosTrack) {
        return null;
    }

    @ConfigField
    public DuskColor color;
}
