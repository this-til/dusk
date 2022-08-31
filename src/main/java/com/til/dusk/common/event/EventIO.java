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

    public final Pos[] pos;

    public EventIO(Level level, Pos... pos) {
        this.level = level;
        if (pos == null) {
            pos = new Pos[0];
        }
        this.pos = pos;
    }

    /***
     * 物品转移
     */
    public static class Item extends EventIO {
        public final ItemStack itemStack;

        public Item(Level level, ItemStack itemStack, Pos... pos) {
            super(level, pos);
            this.itemStack = itemStack;
        }
    }

    /***
     * 流体转移
     */
    public static class Fluid extends EventIO {
        public final FluidStack fluidStack;

        public Fluid(Level level, FluidStack fluidStack, Pos... pos) {
            super(level, pos);
            this.fluidStack = fluidStack;
        }
    }

    /***
     * 灵气转移
     */
    public static class Mana extends EventIO {
        public final long mana;

        public Mana(Level level, long mana, Pos... pos) {
            super(level, pos);
            this.mana = mana;
        }
    }
}
