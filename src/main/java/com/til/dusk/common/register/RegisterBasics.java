package com.til.dusk.common.register;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.common.event.DelayTrigger;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.util.Extension;
import com.til.dusk.util.Util;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;


/**
 * @author til
 */
public abstract class RegisterBasics<T extends RegisterBasics<?>> {

    /***
     * 注册项的名称
     */
    public final ResourceLocation name;

    /***
     * 注册项的注册表
     */
    protected final Supplier<IForgeRegistry<T>> registrySupplier;


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

    /***
     * 注册配方
     */
    public void registerShaped(Consumer<Shaped> shapedConsumer) {
    }

    /***
     * 便捷添加配方
     */
    public T addShaped(Extension.Func<Shaped> recipeBuilder) {
        DelayTrigger.addRun(DelayTrigger.SHAPED, recipeBuilder);
        return Util.forcedConversion(this);
    }
    /***
     * 注册核查表
     */
    public void registerRecipe(Consumer<RecipeBuilder> recipeConsumer) {
    }

    /***
     * 便捷添加配方
     */
    public T addRecipe(Extension.Func<RecipeBuilder> recipeBuilder) {
        DelayTrigger.addRun(DelayTrigger.RECIPE, recipeBuilder);
        return Util.forcedConversion(this);
    }

    /***
     * 注册语言
     */
    public void registerLang(LangProvider.LangTool lang) {
    }

    /***
     * 便捷添加语言秒
     */
    public T addLang(Extension.Action_1V<LangProvider.LangTool> shapedSupplier) {
        DelayTrigger.addRun(DelayTrigger.LANG, shapedSupplier);
        return Util.forcedConversion(this);
    }

    /***
     * 当配对丢失时重新生成配置文件
     */
    public abstract void defaultConfig();

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
