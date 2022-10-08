package com.til.dusk.util.pack;

import com.til.dusk.common.register.BlockUnitRegister;
import com.til.dusk.common.register.FluidUnitRegister;
import com.til.dusk.common.register.ItemUnitRegister;
import com.til.dusk.common.register.UnitRegister;
import net.minecraftforge.registries.IForgeRegistry;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public record RegistryPack<T extends UnitRegister<T, ITEM, BLOCK, FLUID>,
        ITEM extends ItemUnitRegister<?, T>,
        BLOCK extends BlockUnitRegister<?, T>,
        FLUID extends FluidUnitRegister<?, T>>
        (@Nullable Supplier<IForgeRegistry<ITEM>> item,
         @Nullable Supplier<IForgeRegistry<BLOCK>> block,
         @Nullable Supplier<IForgeRegistry<FLUID>> fluid) {
}
