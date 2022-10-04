package com.til.dusk.common.data.lang;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.til.dusk.Dusk;
import com.til.dusk.common.data.DuskData;
import com.til.dusk.common.event.DelayTrigger;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.common.register.RegisterManage;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * @author til
 */
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
            Path mainOutput = DuskData.dataGenerator.getOutputFolder();
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
}
