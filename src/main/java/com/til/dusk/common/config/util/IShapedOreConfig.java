package com.til.dusk.common.config.util;

import com.til.dusk.common.config.AcceptTypeJson;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.mana_level.fluid.ManaLevelFluid;
import com.til.dusk.common.register.mana_level.item.ManaLevelItem;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.block.OreBlock;
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

import java.util.List;
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
            public boolean isMultiple;

            public AcceptItemIn(ResourceLocation resourceLocation, int amount) {
                this.resourceLocation = resourceLocation;
                this.amount = amount;
            }

            public AcceptItemIn() {
            }

            @Override
            public void config(ShapedOre shapedOre, ManaLevel data) {
                shapedOre.addInItem(data.acceptableTagPack.getTagPack(resourceLocation).itemTagKey(), isMultiple ? amount * data.level : amount);
            }

            public AcceptItemIn setMultiple(boolean multiple) {
                isMultiple = multiple;
                return this;
            }
        }

        class AcceptFluidIn implements IShapedOreManaLevelConfig {

            public ResourceLocation resourceLocation;
            public int amount;
            public boolean isMultiple;

            public AcceptFluidIn(ResourceLocation fluidTag, int amount) {
                this.resourceLocation = fluidTag;
                this.amount = amount;
            }

            public AcceptFluidIn() {
            }

            @Override
            public void config(ShapedOre shapedOre, ManaLevel data) {
                shapedOre.addInFluid(data.acceptableTagPack.getTagPack(resourceLocation).fluidTagKey(), isMultiple ? amount * data.level : amount);
            }

            public AcceptFluidIn(boolean isMultiple) {
                this.isMultiple = isMultiple;
            }
        }

        class ItemIn implements IShapedOreManaLevelConfig {

            public Delayed<TagKey<Item>> itemTag;
            public int amount;
            public boolean isMultiple;

            public ItemIn(Supplier<TagKey<Item>> itemTag, int amount) {
                this.itemTag = new Delayed<>(itemTag);
                this.amount = amount;
            }

            public ItemIn() {
            }

            @Override
            public void config(ShapedOre shapedOre, ManaLevel data) {
                shapedOre.addInItem(itemTag.get(), isMultiple ? amount * data.level : amount);
            }

            public ItemIn(Delayed<TagKey<Item>> itemTag) {
                this.itemTag = itemTag;
            }
        }

        class FluidIn implements IShapedOreManaLevelConfig {

            public Delayed<TagKey<Fluid>> fluidTag;
            public int amount;
            public boolean isMultiple;

            public FluidIn(Supplier<TagKey<Fluid>> fluidTag, int amount) {
                this.fluidTag = new Delayed<>(fluidTag);
                this.amount = amount;
            }

            public FluidIn() {
            }

            @Override
            public void config(ShapedOre shapedOre, ManaLevel data) {
                shapedOre.addInFluid(fluidTag.get(), isMultiple ? amount * data.level : amount);
            }

            public FluidIn(Delayed<TagKey<Fluid>> fluidTag) {
                this.fluidTag = fluidTag;
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

        class ManaLevelItemOut implements IShapedOreManaLevelConfig {

            public ManaLevelItem manaLevelItem;
            public int amount;
            public double probability;

            public ManaLevelItemOut(ManaLevelItem manaLevelItem, int amount, double probability) {
                this.manaLevelItem = manaLevelItem;
                this.amount = amount;
                this.probability = probability;
            }

            public ManaLevelItemOut() {
            }

            @Override
            public void config(ShapedOre shapedOre, ManaLevel ore) {
                if (!ore.has(manaLevelItem)) {
                    return;
                }
                shapedOre.addOutItem(new ItemStack(ore.get(manaLevelItem).item(), amount), probability);
            }
        }

        class ManaLevelBlockOut implements IShapedOreManaLevelConfig {

            public ManaLevelBlock manaLevelBlock;
            public int amount;
            public double probability;

            public ManaLevelBlockOut(ManaLevelBlock manaLevelBlock, int amount, double probability) {
                this.manaLevelBlock = manaLevelBlock;
                this.amount = amount;
                this.probability = probability;
            }

            public ManaLevelBlockOut() {
            }

            @Override
            public void config(ShapedOre shapedOre, ManaLevel ore) {
                if (!ore.has(manaLevelBlock)) {
                    return;
                }
                shapedOre.addOutItem(new ItemStack(ore.get(manaLevelBlock).blockItem(), amount), probability);
            }
        }

        class AcceptFluidOut implements IShapedOreManaLevelConfig {

            public ManaLevelFluid manaLevelFluid;
            public int amount;
            public double probability;

            public AcceptFluidOut() {
            }

            public AcceptFluidOut(ManaLevelFluid manaLevelFluid, int amount, double probability) {
                this.manaLevelFluid = manaLevelFluid;
                this.amount = amount;
                this.probability = probability;
            }

            @Override
            public void config(ShapedOre shapedOre, ManaLevel ore) {
                if (!ore.has(manaLevelFluid)) {
                    return;
                }
                shapedOre.addOutFluid(new FluidStack(ore.get(manaLevelFluid).source(), amount), probability);
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
        class ItemIn implements IShapedOreOreConfig {

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

        class FluidIn implements IShapedOreOreConfig {

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

        class AcceptFluidOut implements IShapedOreOreConfig {

            public OreFluid oreFluid;
            public int amount;
            public double probability;

            public AcceptFluidOut() {
            }

            public AcceptFluidOut(OreFluid oreFluid, int amount, double probability) {
                this.oreFluid = oreFluid;
                this.amount = amount;
                this.probability = probability;
            }

            @Override
            public void config(ShapedOre shapedOre, Ore ore) {
                if (!ore.has(oreFluid)) {
                    return;
                }
                shapedOre.addOutFluid(new FluidStack(ore.get(oreFluid).source(), amount), probability);
            }
        }

        class ItemOut implements IShapedOreOreConfig {
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

        class OreItemOut implements IShapedOreOreConfig {

            public OreItem oreItem;
            public int amount;
            public double probability;

            public OreItemOut(OreItem oreItem, int amount, double probability) {
                this.oreItem = oreItem;
                this.amount = amount;
                this.probability = probability;
            }

            public OreItemOut() {
            }

            @Override
            public void config(ShapedOre shapedOre, Ore ore) {
                if (!ore.has(oreItem)) {
                    return;
                }
                shapedOre.addOutItem(new ItemStack(ore.get(oreItem).item(), amount), probability);
            }
        }

        class OreBlockOut implements IShapedOreOreConfig {

            public OreBlock oreBlock;
            public int amount;
            public double probability;

            public OreBlockOut(OreBlock oreBlock, int amount, double probability) {
                this.oreBlock = oreBlock;
                this.amount = amount;
                this.probability = probability;
            }

            public OreBlockOut() {
            }

            @Override
            public void config(ShapedOre shapedOre, Ore ore) {
                if (!ore.has(oreBlock)) {
                    return;
                }
                shapedOre.addOutItem(new ItemStack(ore.get(oreBlock).blockItem(), amount), probability);
            }
        }

        class FluidOut implements IShapedOreOreConfig {
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

        class ManaLevelPack implements IShapedOreOreConfig {
            public List<IShapedOreConfig<ManaLevel>> pack;

            public ManaLevelPack() {
            }

            public ManaLevelPack(IShapedOreConfig<ManaLevel> pack) {
                this.pack = List.of(pack);
            }

            public ManaLevelPack(List<IShapedOreConfig<ManaLevel>> pack) {
                this.pack = pack;
            }

            @Override
            public void config(ShapedOre shapedOre, Ore ore) {
                if (pack == null) {
                    return;
                }
                for (IShapedOreConfig<ManaLevel> manaLevelIShapedOreConfig : pack) {
                    manaLevelIShapedOreConfig.config(shapedOre, ore.manaLevel);
                }
            }
        }
    }
}
