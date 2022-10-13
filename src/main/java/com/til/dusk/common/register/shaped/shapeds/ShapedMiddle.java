package com.til.dusk.common.register.shaped.shapeds;

import com.google.gson.JsonObject;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.util.Lang;
import com.til.dusk.util.nbt.pack.AllNBTPack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

/**
 * @author til
 */
public abstract class ShapedMiddle extends Shaped {
    public long surplusTime;
    public long consumeMana;
    public long outMana;

    public ShapedMiddle() {
    }

    public ShapedMiddle(ResourceLocation name, ShapedType shapedType, ShapedDrive shapedDrive, ManaLevel manaLevel) {
        super(name, shapedType, shapedDrive, manaLevel);
    }

    @Override
    public List<Component> getComponent() {
        List<Component> componentList = super.getComponent();
        if (consumeMana > 0) {
            componentList.add(Lang.getLang(Component.translatable(Lang.getKey("消耗灵气")), Component.literal(String.valueOf(consumeMana))));
        }
        if (surplusTime > 0) {
            componentList.add(Lang.getLang(Component.translatable(Lang.getKey("消耗时间")), Component.literal(String.valueOf(surplusTime))));
        }
        if (outMana > 0) {
            componentList.add(Lang.getLang(Component.translatable(Lang.getKey("输出灵气")), Component.literal(String.valueOf(outMana))));
        }
        return componentList;
    }

    public ShapedMiddle addMultipleSurplusTime(long surplusTime) {
        this.surplusTime = this.surplusTime == 0 ? surplusTime : this.surplusTime * surplusTime;
        return this;
    }

    public ShapedMiddle addMultipleConsumeMana(long consumeMana) {
        this.consumeMana = this.consumeMana == 0 ? consumeMana : this.consumeMana * consumeMana;
        return this;
    }

    public ShapedMiddle addMultipleOutMana(long outMana) {
        this.outMana = this.outMana == 0 ? outMana : this.outMana * outMana;
        return this;
    }
}
