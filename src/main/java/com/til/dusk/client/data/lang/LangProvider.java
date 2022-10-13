package com.til.dusk.client.data.lang;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.til.dusk.Dusk;
import com.til.dusk.client.data.ClientDuskData;
import com.til.dusk.common.event.DelayTrigger;
import com.til.dusk.common.event.RegisterLangEvent;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.common.register.RegisterManage;
import com.til.dusk.common.register.mana_level.item.group.ManaLevelItemGroup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class LangProvider implements DataProvider {
    @Override
    public void run(@NotNull CachedOutput cachedOutput) throws IOException {
        LangTool langTool = new LangTool();
        for (RegisterBasics<?> allRegisterBasic : RegisterManage.ALL_REGISTER_BASICS) {
            allRegisterBasic.registerLang(langTool);
            langTool.clearCache();
        }
        DelayTrigger.run(DelayTrigger.LANG, run -> {
            run.action(langTool);
            langTool.clearCache();
        });
        Dusk.instance.modEventBus.post(new RegisterLangEvent(langTool));
        Map<LangType, Map<String, String>> outLang = new HashMap<>(LangType.values().length);
        for (Map.Entry<String, Map<LangType, String>> entry : langTool.langMap.entrySet()) {
            for (Map.Entry<LangType, String> e : entry.getValue().entrySet()) {
                outLang.computeIfAbsent(e.getKey(), k -> new HashMap<>(1024)).put(entry.getKey(), e.getValue());
            }
        }
        for (Map.Entry<LangType, Map<String, String>> entry : outLang.entrySet()) {
            JsonObject jsonObject = new JsonObject();
            for (Map.Entry<String, String> e : entry.getValue().entrySet()) {
                jsonObject.add(e.getKey(), new JsonPrimitive(entry.getKey().optimize(e.getValue())));
            }
            Path mainOutput = ClientDuskData.dataGenerator.getOutputFolder();
            String pathSuffix = String.format("assets/%s/lang/%s.json",
                    Dusk.MOD_ID,
                    entry.getKey().toString());
            Path outputPath = mainOutput.resolve(pathSuffix);
            DataProvider.saveStable(cachedOutput, jsonObject, outputPath);
        }
    }

    @Override
    public @NotNull String getName() {
        return "lang";
    }

    public static class LangTool {
        Map<String, Map<LangType, String>> langMap = new HashMap<>(1024);
        Map<LangType, String> cacheKey = null;

        public LangTool() {
        }

        public LangTool setCache(String cacheKey) {
            this.cacheKey = langMap.computeIfAbsent(cacheKey, k -> new HashMap<>(LangType.values().length));
            return this;
        }

        public LangTool add(LangType langType, String lang) {
            cacheKey.put(langType, lang);
            return this;
        }

        public void clearCache() {
            cacheKey = null;
        }
    }

    @SubscribeEvent
    public static void onEvent(RegisterLangEvent event) {
        event.langTool.setCache("config.jade.plugin_" + Dusk.MOD_ID + ".default");
        for (LangType value : LangType.values()) {
            event.langTool.add(value, "dusk default plugin");
        }
        event.langTool.setCache(ManaLevelItemGroup.INTEGRATE.toLanguageKey());
        event.langTool.add(LangType.ZH_CN, "集成处理器");
        event.langTool.add(LangType.EN_CH, "Integrate Processor");
        event.langTool.setCache(ManaLevelItemGroup.PROCESSOR.toLanguageKey());
        event.langTool.add(LangType.ZH_CN, "处理器");
        event.langTool.add(LangType.EN_CH, "Processor");
        event.langTool.setCache(ManaLevelItemGroup.HOST.toLanguageKey());
        event.langTool.add(LangType.ZH_CN, "主机");
        event.langTool.add(LangType.EN_CH, "Host Processor");
    }
}
