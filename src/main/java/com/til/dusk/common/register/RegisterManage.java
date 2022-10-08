package com.til.dusk.common.register;

import com.til.dusk.Dusk;
import com.til.dusk.common.event.RegisterManageEvent;
import com.til.dusk.util.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.IModLoadingState;
import net.minecraftforge.fml.IModStateProvider;
import net.minecraftforge.fml.ModLoadingPhase;
import net.minecraftforge.fml.ModLoadingState;
import net.minecraftforge.registries.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * 这是一个注册管理类
 *
 * @author til
 */
public class RegisterManage implements IModStateProvider {

    public static final Map<Class<?>, Supplier<IForgeRegistry<?>>> ALL_MAP = new HashMap<>();
    public static final List<RegisterBasics<?>> ALL_REGISTER_BASICS = new ArrayList<>(1024);

    public static final ModLoadingState REGISTER_INIT = ModLoadingState.withInline("REGISTER_INIT", "UNFREEZE_DATA", ModLoadingPhase.GATHER, ml -> {
        Dusk.instance.modEventBus.post(new RegisterManageEvent.Init());
    });

    public static final ModLoadingState REGISTER_IN_MAP = ModLoadingState.withInline("REGISTER_IN_MAP", "REGISTER_INIT", ModLoadingPhase.GATHER, ml -> {
        GameData.unfreezeData();
        for (ResourceLocation vanillaRegistryKey : RegistryManager.getRegistryNamesForSyncToClient()) {
            ForgeRegistry<?> forgeRegistry = RegistryManager.ACTIVE.getRegistry(vanillaRegistryKey);
            if (forgeRegistry != null) {
                forgeRegistry.unfreeze();
            }
        }
        for (RegisterBasics<?> allRegisterBasic : ALL_REGISTER_BASICS) {
            allRegisterBasic.registerThis();
        }
        Dusk.instance.modEventBus.post(new RegisterManageEvent.InMap());
    });


    public static final ModLoadingState REGISTER_BACK = ModLoadingState.withInline("REGISTER_BACK", "REGISTER_IN_MAP", ModLoadingPhase.GATHER, ml -> {
        for (RegisterBasics<?> allRegisterBasic : ALL_REGISTER_BASICS) {
            allRegisterBasic.registerBack();
        }
        Dusk.instance.modEventBus.post(new RegisterManageEvent.Back());
    });

    public static final ModLoadingState REGISTER_BLACK_TO_BACK = ModLoadingState.withInline("REGISTER_BLACK_TO_BACK", "REGISTER_BACK", ModLoadingPhase.GATHER, ml -> {
        for (RegisterBasics<?> allRegisterBasic : ALL_REGISTER_BASICS) {
            allRegisterBasic.registerBlackToBack();
        }
        Dusk.instance.modEventBus.post(new RegisterManageEvent.BackToBack());
    });


    @Override
    public List<IModLoadingState> getAllStates() {
        return List.of(REGISTER_INIT, REGISTER_IN_MAP, REGISTER_BACK, REGISTER_BLACK_TO_BACK);
    }

    public static <C> Supplier<IForgeRegistry<C>> create(Class<C> cClass, ResourceLocation name, NewRegistryEvent event) {
        Supplier<IForgeRegistry<C>> supplier = event.create(new RegistryBuilder<C>().setName(name));
        ALL_MAP.put(cClass, Util.forcedConversion(supplier));
        return supplier;
    }
}
