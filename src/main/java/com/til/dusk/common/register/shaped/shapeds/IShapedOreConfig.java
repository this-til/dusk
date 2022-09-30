package com.til.dusk.common.register.shaped.shapeds;

import com.google.gson.JsonObject;
import com.til.dusk.util.nbt.ISerialize;
import com.til.dusk.util.nbt.pack.AllNBTPack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;

/***
 * 配方配置器
 * 用于设置配方
 * @author til
 */
public interface IShapedOreConfig extends ISerialize {
    /***
     * 配置配方
     * @param shapedOre 配方
     */
    void config(ShapedOre shapedOre);

    class ItemIn implements IShapedOreConfig {

        public TagKey<Item> itemTag;
        public int amount;

        public ItemIn(TagKey<Item> itemTag, int amount) {
            this.itemTag = itemTag;
            this.amount = amount;
        }

        public ItemIn() {
        }

        @Override
        public void config(ShapedOre shapedOre) {
            shapedOre.addInItem(itemTag, amount);
        }

        @Override
        public CompoundTag as() {
            CompoundTag compoundTag = new CompoundTag();
            AllNBTPack.ITEM_TAG.set(compoundTag, itemTag);
            AllNBTPack.AMOUNT.set(compoundTag, amount);
            return compoundTag;
        }

        @Override
        public void init(CompoundTag t) {
            itemTag = AllNBTPack.ITEM_TAG.get(t);
            amount = AllNBTPack.AMOUNT.get(t);
        }

        @Override
        public JsonObject asJson() {
            JsonObject jsonObject = new JsonObject();
            AllNBTPack.ITEM_TAG.set(jsonObject, itemTag);
            AllNBTPack.AMOUNT.set(jsonObject, amount);
            return jsonObject;
        }

        @Override
        public void init(JsonObject json) {
            itemTag = AllNBTPack.ITEM_TAG.get(json);
            amount = AllNBTPack.AMOUNT.get(json);
        }
    }

    class FluidIn implements IShapedOreConfig {

        public TagKey<Fluid> fluidTag;
        public int amount;

        public FluidIn(TagKey<Fluid> fluidTag, int amount) {
            this.fluidTag = fluidTag;
            this.amount = amount;
        }

        public FluidIn() {
        }

        @Override
        public void config(ShapedOre shapedOre) {
            shapedOre.addInFluid(fluidTag, amount);
        }

        @Override
        public CompoundTag as() {
            CompoundTag compoundTag = new CompoundTag();
            AllNBTPack.FLUID_TAG.set(compoundTag, fluidTag);
            AllNBTPack.AMOUNT.set(compoundTag, amount);
            return compoundTag;
        }

        @Override
        public void init(CompoundTag t) {
            fluidTag = AllNBTPack.FLUID_TAG.get(t);
            amount = AllNBTPack.AMOUNT.get(t);
        }

        @Override
        public JsonObject asJson() {
            JsonObject jsonObject = new JsonObject();
            AllNBTPack.FLUID_TAG.set(jsonObject, fluidTag);
            AllNBTPack.AMOUNT.set(jsonObject, amount);
            return jsonObject;
        }

        @Override
        public void init(JsonObject json) {
            fluidTag = AllNBTPack.FLUID_TAG.get(json);
            amount = AllNBTPack.AMOUNT.get(json);
        }
    }

    class ItemOut implements IShapedOreConfig {
        public ItemStack itemStack;
        public double probability;

        public ItemOut() {
        }

        public ItemOut(ItemStack itemStack, double probability) {
            this.itemStack = itemStack;
            this.probability = probability;
        }

        @Override
        public void config(ShapedOre shapedOre) {
            shapedOre.addOutItem(itemStack, probability);
        }

        @Override
        public CompoundTag as() {
            CompoundTag compoundTag = new CompoundTag();
            AllNBTPack.ITEM_STACK.set(compoundTag, itemStack);
            AllNBTPack.PROBABILITY.set(compoundTag, probability);
            return compoundTag;
        }

        @Override
        public void init(CompoundTag t) {
            itemStack = AllNBTPack.ITEM_STACK.get(t);
            probability = AllNBTPack.PROBABILITY.get(t);
        }

        @Override
        public JsonObject asJson() {
            JsonObject jsonObject = new JsonObject();
            AllNBTPack.ITEM_STACK.set(jsonObject, itemStack);
            AllNBTPack.PROBABILITY.set(jsonObject, probability);
            return jsonObject;
        }

        @Override
        public void init(JsonObject json) {
            itemStack = AllNBTPack.ITEM_STACK.get(json);
            probability = AllNBTPack.PROBABILITY.get(json);
        }
    }

    class FluidOut implements IShapedOreConfig {
        public FluidStack fluidStack;
        public double probability;

        public FluidOut() {
        }

        public FluidOut(FluidStack fluidStack, double probability) {
            this.fluidStack = fluidStack;
            this.probability = probability;
        }

        @Override
        public void config(ShapedOre shapedOre) {
            shapedOre.addOutFluid(fluidStack, probability);
        }

        @Override
        public CompoundTag as() {
            CompoundTag compoundTag = new CompoundTag();
            AllNBTPack.FLUID_STACK.set(compoundTag, fluidStack);
            AllNBTPack.PROBABILITY.set(compoundTag, probability);
            return compoundTag;
        }

        @Override
        public void init(CompoundTag t) {
            fluidStack = AllNBTPack.FLUID_STACK.get(t);
            probability = AllNBTPack.PROBABILITY.get(t);
        }

        @Override
        public JsonObject asJson() {
            JsonObject jsonObject = new JsonObject();
            AllNBTPack.FLUID_STACK.set(jsonObject, fluidStack);
            AllNBTPack.PROBABILITY.set(jsonObject, probability);
            return jsonObject;
        }

        @Override
        public void init(JsonObject json) {
            fluidStack = AllNBTPack.FLUID_STACK.get(json);
            probability = AllNBTPack.PROBABILITY.get(json);
        }


    }

    class MultipleSurplusTime implements IShapedOreConfig {
        public long surplusTime;

        public MultipleSurplusTime() {
        }

        public MultipleSurplusTime(long surplusTime) {
            this.surplusTime = surplusTime;
        }

        @Override
        public void config(ShapedOre shapedOre) {
            shapedOre.addMultipleSurplusTime(surplusTime);
        }

        @Override
        public CompoundTag as() {
            CompoundTag compoundTag = new CompoundTag();
            AllNBTPack.SURPLUS_TIME.set(compoundTag, surplusTime);
            return compoundTag;
        }

        @Override
        public void init(CompoundTag t) {
            surplusTime = AllNBTPack.SURPLUS_TIME.get(t);
        }

        @Override
        public JsonObject asJson() {
            JsonObject jsonObject = new JsonObject();
            AllNBTPack.SURPLUS_TIME.set(jsonObject, surplusTime);
            return jsonObject;
        }

        @Override
        public void init(JsonObject json) {
            surplusTime = AllNBTPack.SURPLUS_TIME.get(json);
        }
    }

    class MultipleConsumeMana implements IShapedOreConfig {
        public long consumeMana;

        public MultipleConsumeMana() {
        }

        public MultipleConsumeMana(long consumeMana) {
            this.consumeMana = consumeMana;
        }

        @Override
        public void config(ShapedOre shapedOre) {
            shapedOre.addMultipleConsumeMana(consumeMana);
        }

        @Override
        public CompoundTag as() {
            CompoundTag compoundTag = new CompoundTag();
            AllNBTPack.CONSUME_MANA.set(compoundTag, consumeMana);
            return compoundTag;
        }

        @Override
        public void init(CompoundTag t) {
            consumeMana = AllNBTPack.CONSUME_MANA.get(t);
        }

        @Override
        public JsonObject asJson() {
            JsonObject jsonObject = new JsonObject();
            AllNBTPack.CONSUME_MANA.set(jsonObject, consumeMana);
            return jsonObject;
        }

        @Override
        public void init(JsonObject json) {
            consumeMana = AllNBTPack.CONSUME_MANA.get(json);
        }
    }

    class MultipleOutMana implements IShapedOreConfig {
        public long outMana;

        public MultipleOutMana() {
        }

        public MultipleOutMana(long outMana) {
            this.outMana = outMana;
        }

        @Override
        public void config(ShapedOre shapedOre) {
            shapedOre.addMultipleOutMana(outMana);
        }

        @Override
        public CompoundTag as() {
            CompoundTag compoundTag = new CompoundTag();
            AllNBTPack.OUT_MANA.set(compoundTag, outMana);
            return compoundTag;
        }

        @Override
        public void init(CompoundTag t) {
            outMana = AllNBTPack.OUT_MANA.get(t);
        }

        @Override
        public JsonObject asJson() {
            JsonObject jsonObject = new JsonObject();
            AllNBTPack.OUT_MANA.set(jsonObject, outMana);
            return jsonObject;
        }

        @Override
        public void init(JsonObject json) {
            outMana = AllNBTPack.OUT_MANA.get(json);
        }
    }

}
