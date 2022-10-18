package com.til.dusk.common.config.util;

import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.util.DuskColor;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;
import java.util.function.Supplier;

/***
 * 延迟获取值
 * 请使用{}创造子类去捕获父类泛型
 * @author til
 */
public abstract class Delayed<E> {
    public Supplier<E> supplier;
    public E e;

    public Delayed(Supplier<E> supplier) {
        this.supplier = supplier;
    }

    public E get() {
        if (e == null) {
            if (supplier != null) {
                e = supplier.get();
            }
            supplier = null;
        }
        return e;
    }

    public static class ItemDelayed extends Delayed<TagKey<Item>> {
        public ItemDelayed(Supplier<TagKey<Item>> supplier) {
            super(supplier);
        }
    }

    public static class BlockDelayed extends Delayed<TagKey<Block>> {
        public BlockDelayed(Supplier<TagKey<Block>> supplier) {
            super(supplier);
        }
    }

    public static class FluidDelayed extends Delayed<TagKey<Fluid>> {
        public FluidDelayed(Supplier<TagKey<Fluid>> supplier) {
            super(supplier);
        }
    }

    public static class ItemStackDelayed extends Delayed<ItemStack> {
        public ItemStackDelayed(Supplier<ItemStack> supplier) {
            super(supplier);
        }
    }

    public static class FluidStackDelayed extends Delayed<FluidStack> {
        public FluidStackDelayed(Supplier<FluidStack> supplier) {
            super(supplier);
        }
    }

    public static class ColorDelayed extends Delayed<DuskColor> {
        public ColorDelayed(Supplier<DuskColor> supplier) {
            super(supplier);
        }
    }

    public static class ListShapedDelayed extends Delayed<List<Shaped>> {
        public ListShapedDelayed(Supplier<List<Shaped>> supplier) {
            super(supplier);
        }
    }

    public static class ManaLevelShapedOreConfigDelayed extends Delayed<List<IShapedOreConfig<ManaLevel>>>{
        public ManaLevelShapedOreConfigDelayed(Supplier<List<IShapedOreConfig<ManaLevel>>> supplier) {
            super(supplier);
        }
    }

    public static class BlockStateDelayed extends Delayed<BlockState> {
        public BlockStateDelayed(Supplier<BlockState> supplier) {
            super(supplier);
        }
    }


}
