package com.til.dusk.common.event;

import com.til.dusk.util.Pos;
import com.til.dusk.util.RoutePack;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Mod;

import java.util.List;


/**
 * @author til
 */
public class EventIO<T> extends Event {

    public final Level level;

    public RoutePack<T> routePack;


    public EventIO(Level level, RoutePack<T> routePack) {
        this.level = level;
        this.routePack = routePack;
    }

    public List<Pos> getPos() {
        return routePack.getAllPos();
    }

    /***
     * 物品转移
     */
    public static class Item extends EventIO<ItemStack> {
        public Item(Level level, RoutePack<ItemStack> routePack) {
            super(level, routePack);
        }

    }

    /***
     * 流体转移
     */
    public static class Fluid extends EventIO<FluidStack> {

        public Fluid(Level level, RoutePack<FluidStack> routePack) {
            super(level, routePack);
        }

    }

    /***
     * 灵气转移
     */
    public static class Mana extends EventIO<Long> {
        public Mana(Level level, RoutePack<Long> routePack) {
            super(level, routePack);
        }
    }
}
