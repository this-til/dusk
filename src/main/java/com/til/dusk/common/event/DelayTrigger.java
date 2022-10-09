package com.til.dusk.common.event;

import com.til.dusk.Dusk;
import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.util.Extension;
import com.til.dusk.util.Util;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DelayTrigger {

    protected static final Map<ITriggerType<?>, List<?>> RUN_MAP = new HashMap<>();

    public static <RUN_TYPE> void addRun(ITriggerType<RUN_TYPE> iTriggerType, RUN_TYPE run) {
        List<RUN_TYPE> runnableList;
        if (RUN_MAP.containsKey(iTriggerType)) {
            runnableList = Util.forcedConversion(RUN_MAP.get(iTriggerType));
        } else {
            runnableList = new ArrayList<>();
            RUN_MAP.put(iTriggerType, runnableList);
        }
        runnableList.add(run);
    }

    public static <RUN_TYPE> void run(ITriggerType<RUN_TYPE> iTriggerType, Extension.Action_1V<RUN_TYPE> run) {
        if (RUN_MAP.containsKey(iTriggerType)) {
            List<RUN_TYPE> runnableList = Util.forcedConversion(RUN_MAP.get(iTriggerType));
            for (RUN_TYPE runnable : runnableList) {
                run.action(runnable);
            }
            runnableList.clear();
        }
    }

    @SubscribeEvent
    @Deprecated
    public static void setupRun(FMLCommonSetupEvent event) {
        run(COMMON_SETUP, Runnable::run);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void gatherDataEvent(GatherDataEvent event) {
        run(COMMON_SETUP, Runnable::run);
        run(GATHER_DATA_EVENT, Runnable::run);
    }

    public static final ITriggerType<Runnable> COMMON_SETUP = new ITriggerType.TriggerType<>();
    public static final ITriggerType<Runnable> GATHER_DATA_EVENT = new ITriggerType.TriggerType<>();
    public static final ITriggerType<Runnable> TAG = new ITriggerType.TriggerType<>();
    public static final ITriggerType<Extension.Func<Shaped>> SHAPED = new ITriggerType.TriggerType<>();
    public static final ITriggerType<Extension.Func<RecipeBuilder>> RECIPE = new ITriggerType.TriggerType<>();
    public static final ITriggerType<Extension.Action_1V<LangProvider.LangTool>> LANG = new ITriggerType.TriggerType<>();

    public interface ITriggerType<RUN_TYPE> {
        class TriggerType<RUN_TYPE> implements ITriggerType<RUN_TYPE> {
        }
    }

}
