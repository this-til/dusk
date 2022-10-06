package com.til.dusk.common.register;

import com.til.dusk.common.config.ConfigMap;
import com.til.dusk.common.config.IAcceptConfigMap;
import com.til.dusk.common.config.ConfigKey;
import com.til.dusk.common.data.lang.LangProvider;
import com.til.dusk.common.event.DelayTrigger;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.util.*;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;


/**
 * @author til
 */
public abstract class RegisterBasics<T extends RegisterBasics<?>> implements IAcceptConfigMap {

    /***
     * 注册项的名称
     */
    public final ResourceLocation name;

    /***
     * 注册项的注册表
     */
    protected final Supplier<IForgeRegistry<T>> registrySupplier;

    /***
     * 配置表
     */
    protected ConfigMap configMap;

    protected Random random = new Random();

    public RegisterBasics(ResourceLocation name, Supplier<IForgeRegistry<T>> registrySupplier) {
        this.name = name;
        this.registrySupplier = registrySupplier;
        RegisterManage.ALL_REGISTER_BASICS.add(this);
    }

    protected final void registerThis() {
        registrySupplier.get().register(name, Util.forcedConversion(this));
    }

    /***
     * 注册回调的触发事件
     */
    protected void registerBack() {

    }

    /***
     * 第二次回调
     */
    protected void registerBlackToBack() {

    }

    public <V> boolean hasConfig(ConfigKey<V> key) {
        return configMap.containsKey(key);
    }

    public <V> V getConfig(ConfigKey<V> key) {
        return configMap.get(key);
    }

    public <V> T setConfig(ConfigKey<V> key, Supplier<V> v) {
        configMap.setConfig(key, v);
        return Util.forcedConversion(this);
    }

    public boolean hasConfigMap(ConfigKey<?> key) {
        return configMap.containsKey(key);
    }

    public final T addDelayRun(Runnable run) {
        DelayTrigger.addRun(DelayTrigger.COMMON_SETUP, run);
        return Util.forcedConversion(this);
    }

    public final T addShaped(Extension.Func<Shaped> func) {
        DelayTrigger.addRun(DelayTrigger.SHAPED, func);
        return Util.forcedConversion(this);
    }

    public final T addRecipes(Extension.Action_1V<List<RecipeBuilder>> func) {
        DelayTrigger.addRun(DelayTrigger.RECIPE, c -> {
            List<RecipeBuilder> stringBuffers = new ArrayList<>();
            func.action(stringBuffers);
            for (RecipeBuilder stringBuffer : stringBuffers) {
                stringBuffer.save(c);
            }
        });
        return Util.forcedConversion(this);
    }

    /***
     * 注册配方
     */
    public void registerShaped(Consumer<Shaped> shapedConsumer) {
    }

    /***
     * 注册核查表
     */
    public void registerRecipe(Consumer<RecipeBuilder> recipeConsumer) {
    }

    /***
     * 注册语言
     */
    public void registerLang(LangProvider.LangTool lang) {
    }

    @Override
    public void init(ConfigMap configMap) {
        this.configMap = configMap;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof RegisterBasics registerBasics) {
            return name.equals(registerBasics.name);
        }
        return false;
    }

    @Override
    public String toString() {
        return name.toString();
    }


}
