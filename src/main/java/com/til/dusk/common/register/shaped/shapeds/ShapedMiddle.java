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
    public final long surplusTime;
    public final long consumeMana;
    public final long outMana;

    public ShapedMiddle(ShapedType shapedType, ShapedDrive shapedDrive, ManaLevel manaLevel, long surplusTime, long consumeMana, long outMana) {
        super(shapedType, shapedDrive, manaLevel);
        this.surplusTime = surplusTime;
        this.consumeMana = consumeMana;
        this.outMana = outMana;
    }

    public ShapedMiddle(ResourceLocation name, JsonObject jsonObject) throws Exception {
        super(name, jsonObject);
        surplusTime = AllNBTPack.SURPLUS_TIME.get(jsonObject);
        consumeMana = AllNBTPack.CONSUME_MANA.get(jsonObject);
        outMana = AllNBTPack.OUT_MANA.get(jsonObject);
    }

    @Override
    public JsonObject writ(JsonObject compoundTag) {
        super.writ(compoundTag);
        AllNBTPack.CONSUME_MANA.set(compoundTag, consumeMana);
        AllNBTPack.SURPLUS_TIME.set(compoundTag, surplusTime);
        AllNBTPack.OUT_MANA.set(compoundTag, outMana);
        return compoundTag;
    }

    @Override
    public List<Component> getComponent() {
        List<Component> componentList = super.getComponent();
        if (consumeMana > 0) {
            componentList.add(Lang.getLang(Lang.getKey("消耗灵气"), String.valueOf(consumeMana)));
        }
        if (surplusTime > 0) {
            componentList.add(Lang.getLang(Lang.getKey("消耗时间"), String.valueOf(surplusTime)));
        }
        if (outMana > 0) {
            componentList.add(Lang.getLang(Lang.getKey("输出灵气"), String.valueOf(outMana)));
        }
        return componentList;
    }
}
