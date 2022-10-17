package com.til.dusk.common.register.attribute.entity.limit;

import com.til.dusk.Dusk;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.common.register.RegisterManage;
import com.til.dusk.common.register.attribute.entity.Attribute;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;

import java.util.function.Supplier;

/**
 * 相当于一个游标卡尺，在0~1指点
 * 用来表示一个属性的0~当前最大值的区间
 *
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public abstract class AttributeLimit extends RegisterBasics<AttributeLimit> {
    public static Supplier<IForgeRegistry<AttributeLimit>> ATTRIBUTE_LIMIT;

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onEvent(NewRegistryEvent event) {
        ATTRIBUTE_LIMIT = RegisterManage.create(AttributeLimit.class, new ResourceLocation(Dusk.MOD_ID, "attribute_limit"), event);
    }

    public Attribute attribute;

    public AttributeLimit(ResourceLocation name, Attribute attribute) {
        super(name, ATTRIBUTE_LIMIT);
        this.attribute = attribute;
    }

    public AttributeLimit(String name, Attribute attribute) {
        this(new ResourceLocation(Dusk.MOD_ID, name), attribute);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public double limit(double t) {
        return Math.max(0, Math.min(t, 1));
    }
}
