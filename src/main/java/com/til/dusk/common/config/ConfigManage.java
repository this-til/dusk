package com.til.dusk.common.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.til.dusk.Dusk;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.common.register.RegisterManage;
import com.til.dusk.util.Extension;
import com.til.dusk.util.IOUtil;
import com.til.dusk.util.ReflectionUtil;
import com.til.dusk.util.gson.AcceptTypeJson;
import com.til.dusk.util.gson.ConfigGson;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.IForgeRegistry;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author til
 */
public class ConfigManage {

    protected static List<Map.Entry<File, RegisterBasics<?>>> needWrite = new ArrayList<>();

    public static void init() {
        File basicsFile = FMLPaths.CONFIGDIR.get().toFile();
        for (Map.Entry<Class<?>, Supplier<IForgeRegistry<RegisterBasics<?>>>> entry : RegisterManage.ALL_MAP.entrySet()) {
            for (RegisterBasics<?> vEntry : entry.getValue().get()) {
                String pack = String.format("%s/%s/%s.json", vEntry.name.getNamespace(), entry.getValue().get().getRegistryName().getPath(), vEntry.name.getPath());
                initRegister(new File(basicsFile, pack), vEntry);
            }
        }
    }

    public static void write() {
        for (Map.Entry<File, RegisterBasics<?>> entry : needWrite) {
            JsonObject jsonObject;
            try {
                jsonObject = readRegister(entry.getValue());
            } catch (IllegalAccessException e) {
                Dusk.instance.logger.error("读取配置时出现问题", e);
                continue;
            }
            IOUtil.writer(entry.getKey(),ConfigGson.GSON.toJson(jsonObject));
        }
        needWrite.clear();
        needWrite = null;
    }

    protected static void initRegister(File file, RegisterBasics<?> registerBasics) {
        if (!file.exists()) {
            needWrite.add(new Extension.VariableData_2<>(file, registerBasics));
            registerBasics.defaultConfig();
            return;
        }
        String s = IOUtil.readFileByLines(file);
        if (s.isEmpty()) {
            return;
        }
        JsonObject jsonObject = ConfigGson.GSON.fromJson(s, JsonObject.class);
        try {
            writeRegister(registerBasics, jsonObject);
        } catch (IllegalAccessException e) {
            Dusk.instance.logger.error("赋值配置时出现问题", e);
        }
    }

    protected static void writeRegister(RegisterBasics<?> registerBasics, JsonObject jsonObject) throws IllegalAccessException {
        for (Field field : ReflectionUtil.getAllFields(registerBasics.getClass())) {
            if (!field.isAnnotationPresent(ConfigField.class)) {
                continue;
            }
            if (!jsonObject.has(field.getName())) {
                continue;
            }
            field.setAccessible(true);
            field.set(registerBasics, ConfigGson.GSON.fromJson(jsonObject.get(field.getName()), field.getGenericType()));
        }
    }

    protected static JsonObject readRegister(RegisterBasics<?> registerBasics) throws IllegalAccessException {
        JsonObject jsonObject = new JsonObject();
        for (Field field : ReflectionUtil.getAllFields(registerBasics.getClass())) {
            if (!field.isAnnotationPresent(ConfigField.class)) {
                continue;
            }
            field.setAccessible(true);
            Object v = field.get(registerBasics);
            if (v == null) {
                continue;
            }
            jsonObject.add(field.getName(), ConfigGson.GSON.toJsonTree(v));
        }
        return jsonObject;
    }


}
