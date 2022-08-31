package com.til.dusk.common.event;

import com.til.dusk.Dusk;
import com.til.dusk.util.Extension;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 一个服务端tick计时工具类
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ServerTick {

    public static final List<Extension.Data_2<Long, Runnable>> RUN_LIST = new ArrayList<>();
    public static long time;
    public static boolean hasTime;

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void tick(TickEvent.ServerTickEvent event) {
        if (!event.phase.equals(TickEvent.Phase.END)) {
            return;
        }
        hasTime = event.haveTime();
        time = event.getServer().getNextTickTime();
        List<Extension.Data_2<Long, Runnable>> rList = null;
        for (Extension.Data_2<Long, Runnable> longRunnableData_2 : RUN_LIST) {
            if (longRunnableData_2.d1() <= time) {
                event.getServer().addTickable(longRunnableData_2.d2());
                if (rList == null) {
                    rList = new ArrayList<>();
                }
                rList.add(longRunnableData_2);
            }
        }
        if (rList != null) {
            for (Extension.Data_2<Long, Runnable> longRunnableData_2 : rList) {
                RUN_LIST.remove(longRunnableData_2);
            }
            rList.clear();
        }
    }

    public static void addRun(long _time, Runnable runnable) {
        if (!hasTime) {
            return;
        }
        RUN_LIST.add(new Extension.Data_2<>(time + _time, runnable));
    }

}
