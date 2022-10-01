package com.til.dusk.util.pack;

import com.til.dusk.common.register.RegisterBasics;
import net.minecraftforge.registries.IForgeRegistry;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public record RegistryPack<T extends RegisterBasics.UnitRegister<T, ITEM, BLOCK, FLUID>,
        ITEM extends RegisterBasics.ItemUnitRegister<?, T>,
        BLOCK extends RegisterBasics.BlockUnitRegister<?, T>,
        FLUID extends RegisterBasics.FluidUnitRegister<?, T>>
        (@Nullable Supplier<IForgeRegistry<ITEM>> item,
         @Nullable Supplier<IForgeRegistry<BLOCK>> block,
         @Nullable Supplier<IForgeRegistry<FLUID>> fluid) {
}
