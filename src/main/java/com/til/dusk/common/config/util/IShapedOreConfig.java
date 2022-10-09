package com.til.dusk.common.config.util;

import com.til.dusk.common.config.AcceptTypeJson;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.fluid.OreFluid;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;

import java.util.function.Supplier;

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

        public Delayed<TagKey<Item>> itemTag;
        public int amount;

        public ItemIn(Supplier<TagKey<Item>> itemTag, int amount) {
            this.itemTag = new Delayed<>(itemTag);
            this.amount = amount;
        }

        public ItemIn() {
        }

        @Override
        public void config(ShapedOre shapedOre, Void data) {
            shapedOre.addInItem(itemTag.get(), amount);
        }
    }

    class FluidIn implements IShapedOreConfig<Void> {

        public Delayed<TagKey<Fluid>> fluidTag;
        public int amount;

        public FluidIn(Supplier<TagKey<Fluid>> fluidTag, int amount) {
            this.fluidTag = new Delayed<>(fluidTag);
            this.amount = amount;
        }

        public FluidIn() {
        }

        @Override
        public void config(ShapedOre shapedOre, Void data) {
            shapedOre.addInFluid(fluidTag.get(), amount);
        }
    }

    class ItemOut implements IShapedOreConfig<Void> {
        public Delayed<ItemStack> itemStack;
        public double probability;

        public ItemOut() {
        }

        public ItemOut(Supplier<ItemStack> itemStack, double probability) {
            this.itemStack = new Delayed<>(itemStack);
            this.probability = probability;
        }

        @Override
        public void config(ShapedOre shapedOre, Void data) {
            shapedOre.addOutItem(itemStack.get(), probability);
        }
    }

    class FluidOut implements IShapedOreConfig<Void> {
        public Delayed<FluidStack> fluidStack;
        public double probability;

        public FluidOut() {
        }

        public FluidOut(Supplier<FluidStack> fluidStack, double probability) {
            this.fluidStack = new Delayed<>(fluidStack);
            this.probability = probability;
        }

        @Override
        public void config(ShapedOre shapedOre, Void data) {
            shapedOre.addOutFluid(fluidStack.get(), probability);
        }
    }

    class MultipleSurplusTime<DATA> implements IShapedOreConfig<DATA> {
        public long surplusTime;

        public MultipleSurplusTime() {
        }

        public MultipleSurplusTime(long surplusTime) {
            this.surplusTime = surplusTime;
        }

        @Override
        public void config(ShapedOre shapedOre, DATA data) {
            shapedOre.addMultipleSurplusTime(surplusTime);
        }
    }

    class MultipleConsumeMana<DATA> implements IShapedOreConfig<DATA> {
        public long consumeMana;

        public MultipleConsumeMana() {
        }

        public MultipleConsumeMana(long consumeMana) {
            this.consumeMana = consumeMana;
        }

        @Override
        public void config(ShapedOre shapedOre, DATA data) {
            shapedOre.addMultipleConsumeMana(consumeMana);
        }
    }

    class MultipleOutMana<DATA> implements IShapedOreConfig<DATA> {
        public long outMana;

        public MultipleOutMana() {
        }

        public MultipleOutMana(long outMana) {
            this.outMana = outMana;
        }

        @Override
        public void config(ShapedOre shapedOre, DATA data) {
            shapedOre.addMultipleOutMana(outMana);
        }
    }

    interface IShapedOreManaLevelConfig extends IShapedOreConfig<ManaLevel> {
        class AcceptItemIn implements IShapedOreManaLevelConfig {

            public ResourceLocation resourceLocation;
            public int amount;

            public AcceptItemIn(ResourceLocation resourceLocation, int amount) {
                this.resourceLocation = resourceLocation;
                this.amount = amount;
            }

            public AcceptItemIn() {
            }

            @Override
            public void config(ShapedOre shapedOre, ManaLevel data) {
                shapedOre.addInItem(data.acceptableTagPack.getTagPack(resourceLocation).itemTagKey(), amount);
            }
        }

        class AcceptFluidIn implements IShapedOreManaLevelConfig {

            public ResourceLocation resourceLocation;
            public int amount;

            public AcceptFluidIn(ResourceLocation fluidTag, int amount) {
                this.resourceLocation = fluidTag;
                this.amount = amount;
            }

            public AcceptFluidIn() {
            }

            @Override
            public void config(ShapedOre shapedOre, ManaLevel data) {
                shapedOre.addInFluid(data.acceptableTagPack.getTagPack(resourceLocation).fluidTagKey(), amount);
            }
        }

        class ItemIn implements IShapedOreManaLevelConfig {

            public Delayed<TagKey<Item>> itemTag;
            public int amount;

            public ItemIn(Supplier<TagKey<Item>> itemTag, int amount) {
                this.itemTag = new Delayed<>(itemTag);
                this.amount = amount;
            }

            public ItemIn() {
            }

            @Override
            public void config(ShapedOre shapedOre, ManaLevel data) {
                shapedOre.addInItem(itemTag.get(), amount);
            }
        }

        class FluidIn implements IShapedOreManaLevelConfig {

            public Delayed<TagKey<Fluid>> fluidTag;
            public int amount;

            public FluidIn(Supplier<TagKey<Fluid>> fluidTag, int amount) {
                this.fluidTag = new Delayed<>(fluidTag);
                this.amount = amount;
            }

            public FluidIn() {
            }

            @Override
            public void config(ShapedOre shapedOre, ManaLevel data) {
                shapedOre.addInFluid(fluidTag.get(), amount);
            }
        }

        class ItemOut implements IShapedOreManaLevelConfig {
            public Delayed<ItemStack> itemStack;
            public double probability;

            public ItemOut() {
            }

            public ItemOut(Supplier<ItemStack> itemStack, double probability) {
                this.itemStack = new Delayed<>(itemStack);
                this.probability = probability;
            }

            @Override
            public void config(ShapedOre shapedOre, ManaLevel data) {
                shapedOre.addOutItem(itemStack.get(), probability);
            }
        }

        class FluidOut implements IShapedOreManaLevelConfig {
            public Delayed<FluidStack> fluidStack;
            public double probability;

            public FluidOut() {
            }

            public FluidOut(Supplier<FluidStack> fluidStack, double probability) {
                this.fluidStack = new Delayed<>(fluidStack);
                this.probability = probability;
            }

            @Override
            public void config(ShapedOre shapedOre, ManaLevel data) {
                shapedOre.addOutFluid(fluidStack.get(), probability);
            }
        }


    }

    interface IShapedOreOreConfig extends IShapedOreConfig<Ore> {

        class AcceptItemIn implements IShapedOreOreConfig {

            public ResourceLocation resourceLocation;
            public int amount;

            public AcceptItemIn(ResourceLocation resourceLocation, int amount) {
                this.resourceLocation = resourceLocation;
                this.amount = amount;
            }

            public AcceptItemIn() {
            }

            @Override
            public void config(ShapedOre shapedOre, Ore ore) {
                shapedOre.addInItem(ore.tagPackSupplier.getTagPack(resourceLocation).itemTagKey(), amount);
            }
        }

        class AcceptFluidIn implements IShapedOreOreConfig {

            public ResourceLocation resourceLocation;
            public int amount;

            public AcceptFluidIn(ResourceLocation resourceLocation, int amount) {
                this.resourceLocation = resourceLocation;
                this.amount = amount;
            }

            public AcceptFluidIn() {
            }

            @Override
            public void config(ShapedOre shapedOre, Ore ore) {
                shapedOre.addInFluid(ore.tagPackSupplier.getTagPack(resourceLocation).fluidTagKey(), amount);
            }
        }

        class ItemIn implements IShapedOreConfig<Ore> {

            public Delayed<TagKey<Item>> itemTag;
            public int amount;

            public ItemIn(Supplier<TagKey<Item>> itemTag, int amount) {
                this.itemTag = new Delayed<>(itemTag);
                this.amount = amount;
            }

            public ItemIn() {
            }

            @Override
            public void config(ShapedOre shapedOre, Ore data) {
                shapedOre.addInItem(itemTag.get(), amount);
            }
        }

        class FluidIn implements IShapedOreConfig<Ore> {

            public Delayed<TagKey<Fluid>> fluidTag;
            public int amount;

            public FluidIn(Supplier<TagKey<Fluid>> fluidTag, int amount) {
                this.fluidTag = new Delayed<>(fluidTag);
                this.amount = amount;
            }

            public FluidIn() {
            }

            @Override
            public void config(ShapedOre shapedOre, Ore data) {
                shapedOre.addInFluid(fluidTag.get(), amount);
            }
        }

        class ItemOut implements IShapedOreConfig<Ore> {
            public Delayed<ItemStack> itemStack;
            public double probability;

            public ItemOut() {
            }

            public ItemOut(Supplier<ItemStack> itemStack, double probability) {
                this.itemStack = new Delayed<>(itemStack);
                this.probability = probability;
            }

            @Override
            public void config(ShapedOre shapedOre, Ore data) {
                shapedOre.addOutItem(itemStack.get(), probability);
            }
        }

        class FluidOut implements IShapedOreConfig<Ore> {
            public Delayed<FluidStack> fluidStack;
            public double probability;

            public FluidOut() {
            }

            public FluidOut(Supplier<FluidStack> fluidStack, double probability) {
                this.fluidStack = new Delayed<>(fluidStack);
                this.probability = probability;
            }

            @Override
            public void config(ShapedOre shapedOre, Ore data) {
                shapedOre.addOutFluid(fluidStack.get(), probability);
            }
        }
    }
}
