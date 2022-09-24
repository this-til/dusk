package com.til.dusk.common.register;

import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraftforge.fml.IModLoadingState;
import net.minecraftforge.fml.IModStateProvider;
import net.minecraftforge.fml.ModLoadingPhase;
import net.minecraftforge.fml.ModLoadingState;
import net.minecraftforge.registries.GameData;

import java.util.ArrayList;
import java.util.List;

/**
 * 这是一个注册管理类
 *
 * @author til
 */
public class RegisterManage implements IModStateProvider {

    public static final List<RegisterBasics<?>> ALL_REGISTER_BASICS = new ArrayList<>(1024);
    public static final ModLoadingState REGISTER_IN_MAP = ModLoadingState.withInline("REGISTER_IN_MAP", "CREATE_REGISTRIES", ModLoadingPhase.GATHER, ml -> {
        Registry.REGISTRY.stream().filter(r -> r instanceof MappedRegistry).forEach(r -> ((MappedRegistry<?>)r).unfreeze());
        for (RegisterBasics<?> allRegisterBasic : ALL_REGISTER_BASICS) {
            allRegisterBasic.registerThis();
        }
    });

    public static final ModLoadingState REGISTER_BACK = ModLoadingState.withInline("REGISTER_BACK", "REGISTER_IN_MAP", ModLoadingPhase.GATHER, ml -> {
        for (RegisterBasics<?> allRegisterBasic : ALL_REGISTER_BASICS) {
            allRegisterBasic.registerBack();
        }
    });

    public static final ModLoadingState REGISTER_BLACK_TO_BACK = ModLoadingState.withInline("REGISTER_BLACK_TO_BACK", "REGISTER_BACK", ModLoadingPhase.GATHER, ml -> {
        for (RegisterBasics<?> allRegisterBasic : ALL_REGISTER_BASICS) {
            allRegisterBasic.registerBlackToBack();
        }
    });


    @Override
    public List<IModLoadingState> getAllStates() {
        return List.of(REGISTER_IN_MAP, REGISTER_BACK, REGISTER_BLACK_TO_BACK);
    }
}
