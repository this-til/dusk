package com.til.dusk.common.register.shaped.shapeds;

import com.google.gson.JsonObject;
import com.til.dusk.common.register.mana_level.ManaLevel;
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

    public ShapedMiddle(ShapedType shapedType, ShapedDrive shapedDrive, ManaLevel manaLevel) {
        super(shapedType, shapedDrive, manaLevel);
    }

    public ShapedMiddle(ResourceLocation name, JsonObject jsonObject) {
        super(name, jsonObject);
        surplusTime = AllNBTPack.SURPLUS_TIME.get(jsonObject);
        consumeMana = AllNBTPack.CONSUME_MANA.get(jsonObject);
        outMana = AllNBTPack.OUT_MANA.get(jsonObject);
    }

    @Override
    public JsonObject writ(JsonObject jsonObject) {
        super.writ(jsonObject);
        AllNBTPack.CONSUME_MANA.set(jsonObject, consumeMana);
        AllNBTPack.SURPLUS_TIME.set(jsonObject, surplusTime);
        AllNBTPack.OUT_MANA.set(jsonObject, outMana);
        return jsonObject;
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
        this.outMana = outMana;
        return this;
    }
}
