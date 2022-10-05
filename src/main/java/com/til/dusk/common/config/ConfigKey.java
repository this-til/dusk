package com.til.dusk.common.config;

import com.til.dusk.Dusk;
import com.til.dusk.util.Util;
import com.til.dusk.util.nbt.cell.AllNBTCell;
import com.til.dusk.util.nbt.cell.NBTCell;
import com.til.dusk.util.nbt.pack.AllNBTPack;
import com.til.dusk.util.nbt.pack.NBTPack;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author til
 */
public class ConfigKey<T> extends NBTPack<T> {
    protected static final Map<String, ConfigKey<?>> CONFIG_KEY_MAP = new HashMap<>(128);

    /***
     * 如果为空表示调用时是必要的配置
     */
    @Nullable
    public final Supplier<T> defaultValue;

    public ConfigKey(String name, NBTCell<T> nbtCell, @Nullable Supplier<T> defaultValue) {
        super(name, nbtCell);
        this.defaultValue = defaultValue;
        if (CONFIG_KEY_MAP.containsKey(name)) {
            Dusk.instance.logger.error("配置文件解析器出现重复的Key{},为确保程序正常运行已抛弃之前的值", name);
        }
        CONFIG_KEY_MAP.put(name, this);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ConfigKey<?> configKey) {
            return name.equals(configKey.name);
        }
        if (obj instanceof String s) {
            return name.equals(s);
        }
        return false;
    }

    public static ConfigKey<?> getKey(String name) {
        if (CONFIG_KEY_MAP.containsKey(name)) {
            return CONFIG_KEY_MAP.get(name);
        }
        Dusk.instance.logger.error("配置文件解析器出现未知的Key{},为确保程序正常运行已创建默认值", name);
        ConfigKey<?> configKey = new ConfigKey<>(null, Util.forcedConversion(AllNBTCell.EMPTY), () -> null);
        CONFIG_KEY_MAP.put(name, configKey);
        return configKey;
    }

    public static class VoidConfigKey extends ConfigKey<Void> {
        public VoidConfigKey(String name) {
            super(name, Util.forcedConversion(AllNBTCell.EMPTY), () -> null);
        }
    }

    public static class ConfigMapKey extends ConfigKey<ConfigMap> {
        public ConfigMapKey(String name) {
            super(name, AllNBTCell.CONFIG_MAP, null);
        }
    }

    public static final ConfigKey<TagKey<Item>> ITEM_TAG = copy(AllNBTPack.ITEM_TAG, null);
    public static final ConfigKey<TagKey<Fluid>> FLUID_TAG = copy(AllNBTPack.FLUID_TAG, null);
    public static final ConfigKey<ItemStack> ITEM_STACK = copy(AllNBTPack.ITEM_STACK, () -> ItemStack.EMPTY);
    public static final ConfigKey<FluidStack> FLUID_STACK = copy(AllNBTPack.FLUID_STACK, () -> FluidStack.EMPTY);

    public static final ConfigKey<Integer> AMOUNT = copy(AllNBTPack.AMOUNT, () -> 1);
    public static final ConfigKey<Double> PROBABILITY = copy(AllNBTPack.PROBABILITY, () -> 1d);
    public static final ConfigKey<Long> SURPLUS_TIME = copy(AllNBTPack.SURPLUS_TIME, () -> 1L);
    public static final ConfigKey<Long> CONSUME_MANA = copy(AllNBTPack.CONSUME_MANA, () -> 1L);
    public static final ConfigKey<Long> OUT_MANA = copy(AllNBTPack.OUT_MANA, () -> 1L);

    public static final ConfigKey<Class<?>> TYPE = copy(AllNBTPack.TYPE, () -> Object.class);

    public static <V> ConfigKey<V> copy(NBTPack<V> pack, Supplier<V> defaultValue) {
        return new ConfigKey<>(pack.name, pack.nbtCell, defaultValue);
    }
}
