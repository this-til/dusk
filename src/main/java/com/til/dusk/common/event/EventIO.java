package com.til.dusk.common.event;

import com.til.dusk.util.Pos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Mod;


/**
 * @author til
 */
public class EventIO extends Event {

    public final Level level;

    public final Pos start;
    public final Pos end;

    public EventIO(Level level, Pos start, Pos end) {
        this.level = level;
        this.start = start;
        this.end = end;
    }

    /***
     * 物品转移
     */
    public static class Item extends EventIO {
        public final ItemStack itemStack;

        public Item(Level level, Pos start, Pos end, ItemStack itemStack) {
            super(level, start, end);
            this.itemStack = itemStack;
        }
    }

    /***
     * 流体转移
     */
    public static class Fluid extends EventIO {
        public final FluidStack fluidStack;

        public Fluid(Level level, Pos start, Pos end, FluidStack fluidStack) {
            super(level, start, end);
            this.fluidStack = fluidStack;
        }
    }

    /***
     * 灵气转移
     */
    public static class Mana extends EventIO {
        public final long mana;

        public Mana(Level level, Pos start, Pos end, long mana) {
            super(level, start, end);
            this.mana = mana;
        }
    }
}
