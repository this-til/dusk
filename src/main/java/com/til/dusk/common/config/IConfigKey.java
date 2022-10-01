package com.til.dusk.common.config;

import com.google.gson.JsonElement;
import com.til.dusk.Dusk;
import com.til.dusk.util.Extension;
import com.til.dusk.util.Util;
import com.til.dusk.util.nbt.cell.AllNBTCell;
import com.til.dusk.util.nbt.cell.NBTCell;
import com.til.dusk.util.nbt.pack.NBTPack;
import net.minecraft.nbt.Tag;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author til
 */
@Deprecated
public class IConfigKey<T> extends NBTPack<Supplier<T>> {
    protected static final Map<String, IConfigKey<?>> CONFIG_KEY_MAP = new HashMap<>(128);

    /***
     * 如果为空表示必要的配置
     */
    @Nullable
    public final Supplier<T> defaultValue;

    public IConfigKey(String name, NBTCell<T> nbtCell, @Nullable Supplier<T> defaultValue) {
        super(name, new NBTCell<>() {
            @Override
            public Tag as(Supplier<T> tSupplier) {
                return nbtCell.as(tSupplier.get());
            }

            @Override
            public Supplier<T> from(Tag t) {
                Extension.VariableData<T> tPack = new Extension.VariableData<>(null);
                return () -> {
                    if (tPack.d1 == null) {
                        tPack.d1 = nbtCell.from(t);
                        if (tPack.d1 instanceof INeedBack back) {
                            back.back();
                        }
                    }
                    return tPack.d1;
                };
            }

            @Override
            public JsonElement asJson(Supplier<T> tSupplier) {
                return nbtCell.asJson(tSupplier.get());
            }

            @Override
            public Supplier<T> fromJson(JsonElement json) {
                Extension.VariableData<T> tPack = new Extension.VariableData<>(null);
                return () -> {
                    if (tPack.d1 == null) {
                        tPack.d1 = nbtCell.fromJson(json);
                        if (tPack.d1 instanceof INeedBack back) {
                            back.back();
                        }
                    }
                    return tPack.d1;
                };
            }
        });
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
        if (obj instanceof IConfigKey<?> configKey) {
            return name.equals(configKey.name);
        }
        if (obj instanceof String s) {
            return name.equals(s);
        }
        return false;
    }

    public static <V> IConfigKey<V> getKey(String name) {
        if (CONFIG_KEY_MAP.containsKey(name)) {
            return Util.forcedConversion(CONFIG_KEY_MAP.get(name));
        }
        Dusk.instance.logger.error("配置文件解析器出现未知的Key{},为确保程序正常运行已创建默认值", name);
        IConfigKey<V> iConfigKey = new IConfigKey<>(null, Util.forcedConversion(AllNBTCell.EMPTY), () -> null);
        CONFIG_KEY_MAP.put(name, iConfigKey);
        return iConfigKey;
    }

    public static class VoidConfigKey extends IConfigKey<Void> {
        public VoidConfigKey(String name) {
            super(name, Util.forcedConversion(AllNBTCell.EMPTY), () -> null);
        }
    }
}
