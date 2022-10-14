package com.til.dusk.common.register.attribute.multiple;

import com.til.dusk.Dusk;
import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.common.register.RegisterManage;
import com.til.dusk.util.math.INumberPack;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;

import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AttributeMultiple extends RegisterBasics<AttributeMultiple> {
    public static Supplier<IForgeRegistry<AttributeMultiple>> ATTRIBUTE_MULTIPLE;

    /***
     * 基础乘区
     */
    public static AttributeMultiple basics;

    /***
     * 技能乘区
     */
    public static AttributeMultiple skill;

    /***
     * effect乘区
     */
    public static AttributeMultiple effect;

    /***
     * 暴击乘区
     */
    public static AttributeMultiple hit;

    /***
     * 防御负乘区
     */
    public static AttributeMultiple defense;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        ATTRIBUTE_MULTIPLE = RegisterManage.create(AttributeMultiple.class, new ResourceLocation(Dusk.MOD_ID, "attribute_multiple"), event);
        basics = new AttributeMultiple("basics", 1, new INumberPack.Range(new INumberPack.Constant(10), new INumberPack.Constant(0.02)));
        skill = new AttributeMultiple("skill", 1, new INumberPack.Range(new INumberPack.Constant(10), new INumberPack.Constant(0.02)));
        effect = new AttributeMultiple("effect", 1, new INumberPack.Range(new INumberPack.Constant(10), new INumberPack.Constant(0.02)));
        hit = new AttributeMultiple("hit", 1, new INumberPack.Range(new INumberPack.Constant(2), new INumberPack.Constant(0.02)));
        defense = new AttributeMultiple("defense", 1, new INumberPack.Range(new INumberPack.Constant(1), new INumberPack.Constant(0.02)));
    }

    public AttributeMultiple(ResourceLocation name, double basicsValue, INumberPack.Range range) {
        super(name, ATTRIBUTE_MULTIPLE);
        this.basicsValue = basicsValue;
        this.range = range;
    }

    public AttributeMultiple(String name, double basicsValue, INumberPack.Range range) {
        this(new ResourceLocation(Dusk.MOD_ID, name), basicsValue, range);
    }

    @Override
    public void defaultConfig() {

    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /***
     * 这个属性值存在的范围
     */
    @ConfigField
    public INumberPack.Range range;

    /***
     * 这个乘区的基础值
     */
    public double basicsValue;

}
