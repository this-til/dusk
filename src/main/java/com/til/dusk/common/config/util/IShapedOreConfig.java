package com.til.dusk.common.config.util;

import com.til.dusk.common.config.AcceptTypeJson;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import net.minecraft.resources.ResourceLocation;
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
@AcceptTypeJson
public interface IShapedOreConfig<DATA> {
    /***
     * 配置配方
     * @param shapedOre 配方
     * @param data
     */
    void config(ShapedOre shapedOre, DATA data);

    class ItemIn implements IShapedOreConfig<Void> {

        public TagKey<Item> itemTag;
        public int amount;

        public ItemIn(TagKey<Item> itemTag, int amount) {
            this.itemTag = itemTag;
            this.amount = amount;
        }

        public ItemIn() {
        }

        @Override
        public void config(ShapedOre shapedOre, Void data) {
            shapedOre.addInItem(itemTag, amount);
        }
    }

    class FluidIn implements IShapedOreConfig<Void> {

        public TagKey<Fluid> fluidTag;
        public int amount;

        public FluidIn(TagKey<Fluid> fluidTag, int amount) {
            this.fluidTag = fluidTag;
            this.amount = amount;
        }

        public FluidIn() {
        }

        @Override
        public void config(ShapedOre shapedOre, Void data) {
            shapedOre.addInFluid(fluidTag, amount);
        }
    }

    class ItemOut implements IShapedOreConfig<Void> {
        public ItemStack itemStack;
        public double probability;

        public ItemOut() {
        }

        public ItemOut(ItemStack itemStack, double probability) {
            this.itemStack = itemStack;
            this.probability = probability;
        }

        @Override
        public void config(ShapedOre shapedOre, Void data) {
            shapedOre.addOutItem(itemStack, probability);
        }
    }

    class FluidOut implements IShapedOreConfig<Void> {
        public FluidStack fluidStack;
        public double probability;

        public FluidOut() {
        }

        public FluidOut(FluidStack fluidStack, double probability) {
            this.fluidStack = fluidStack;
            this.probability = probability;
        }

        @Override
        public void config(ShapedOre shapedOre, Void data) {
            shapedOre.addOutFluid(fluidStack, probability);
        }
    }

    class MultipleSurplusTime implements IShapedOreConfig<Object> {
        public long surplusTime;

        public MultipleSurplusTime() {
        }

        public MultipleSurplusTime(long surplusTime) {
            this.surplusTime = surplusTime;
        }

        @Override
        public void config(ShapedOre shapedOre, Object data) {
            shapedOre.addMultipleSurplusTime(surplusTime);
        }
    }

    class MultipleConsumeMana implements IShapedOreConfig<Object> {
        public long consumeMana;

        public MultipleConsumeMana() {
        }

        public MultipleConsumeMana(long consumeMana) {
            this.consumeMana = consumeMana;
        }

        @Override
        public void config(ShapedOre shapedOre, Object data) {
            shapedOre.addMultipleConsumeMana(consumeMana);
        }
    }

    class MultipleOutMana implements IShapedOreConfig<Object> {
        public long outMana;

        public MultipleOutMana() {
        }

        public MultipleOutMana(long outMana) {
            this.outMana = outMana;
        }

        @Override
        public void config(ShapedOre shapedOre, Object data) {
            shapedOre.addMultipleOutMana(outMana);
        }
    }

    interface IShapedOreManaLevelConfig extends IShapedOreConfig<ManaLevel> {
        class ManaLevelItemIn implements IShapedOreManaLevelConfig {

            public TagKey<Item> itemTag;
            public int amount;

            public ManaLevelItemIn(TagKey<Item> itemTag, int amount) {
                this.itemTag = itemTag;
                this.amount = amount;
            }

            public ManaLevelItemIn() {
            }

            @Override
            public void config(ShapedOre shapedOre, ManaLevel data) {
                shapedOre.addInItem(itemTag, amount);
            }
        }

        class ManaLevelAcceptItemIn implements IShapedOreManaLevelConfig {

            public ResourceLocation resourceLocation;
            public int amount;

            public ManaLevelAcceptItemIn(ResourceLocation resourceLocation, int amount) {
                this.resourceLocation = resourceLocation;
                this.amount = amount;
            }

            public ManaLevelAcceptItemIn() {
            }

            @Override
            public void config(ShapedOre shapedOre, ManaLevel data) {
                shapedOre.addInItem(data.acceptableTagPack.getTagPack(resourceLocation).itemTagKey(), amount);
            }
        }

        class ManaLevelFluidIn implements IShapedOreManaLevelConfig {

            public TagKey<Fluid> fluidTag;
            public int amount;

            public ManaLevelFluidIn(TagKey<Fluid> fluidTag, int amount) {
                this.fluidTag = fluidTag;
                this.amount = amount;
            }

            public ManaLevelFluidIn() {
            }

            @Override
            public void config(ShapedOre shapedOre, ManaLevel data) {
                shapedOre.addInFluid(fluidTag, amount);
            }
        }

        class ManaLevelAcceptFluidIn implements IShapedOreManaLevelConfig {

            public ResourceLocation resourceLocation;
            public int amount;

            public ManaLevelAcceptFluidIn(ResourceLocation fluidTag, int amount) {
                this.resourceLocation = fluidTag;
                this.amount = amount;
            }

            public ManaLevelAcceptFluidIn() {
            }

            @Override
            public void config(ShapedOre shapedOre, ManaLevel data) {
                shapedOre.addInFluid(data.acceptableTagPack.getTagPack(resourceLocation).fluidTagKey(), amount);
            }
        }

        class ManaLevelItemOut implements IShapedOreManaLevelConfig {
            public ItemStack itemStack;
            public double probability;

            public ManaLevelItemOut() {
            }

            public ManaLevelItemOut(ItemStack itemStack, double probability) {
                this.itemStack = itemStack;
                this.probability = probability;
            }

            @Override
            public void config(ShapedOre shapedOre, ManaLevel data) {
                shapedOre.addOutItem(itemStack, probability);
            }
        }

        class ManaLevelFluidOut implements IShapedOreManaLevelConfig {
            public FluidStack fluidStack;
            public double probability;

            public ManaLevelFluidOut() {
            }

            public ManaLevelFluidOut(FluidStack fluidStack, double probability) {
                this.fluidStack = fluidStack;
                this.probability = probability;
            }

            @Override
            public void config(ShapedOre shapedOre, ManaLevel data) {
                shapedOre.addOutFluid(fluidStack, probability);
            }
        }
    }
}
