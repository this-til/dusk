package com.til.dusk.common.data.shaped;

import com.google.gson.JsonObject;
import com.til.dusk.Dusk;
import com.til.dusk.common.data.ModGatherData;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.util.nbt.pack.AllNBTPack;
import com.til.dusk.util.nbt.NBTUtil;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author til
 */
public class ModShaped {

    /***
     * 类型找寻
     */
    public static final Map<ShapedType, Map<ShapedDrive, List<Shaped>>> MAP = new HashMap<>();
    /***
     * 通过名字找寻
     */
    public static final Map<ResourceLocation, Shaped> NAME_MAP = new HashMap<>();

    public static final Map<Class<?>, ShapedBuilder<?>> BUILDER_MAP = new HashMap<>();


    static {
        BUILDER_MAP.put(Shaped.ShapedOre.class, new ShapedBuilder<Shaped.ShapedOre>() {
            @Override
            public Shaped as(ResourceLocation name, JsonObject shaped) {
                CompoundTag compoundTag = (CompoundTag) NBTUtil.toTag(shaped);
                ShapedType shapedType = AllNBTPack.SHAPED_TYPE.get(compoundTag);
                ShapedDrive shapedDrive = AllNBTPack.SHAPED_DRIVE.get(compoundTag);
                ManaLevel manaLevel = AllNBTPack.MANA_LEVEL.get(compoundTag);
                long surplusTime = AllNBTPack.CONSUME_MANA.get(compoundTag);
                long consumeMana = AllNBTPack.SURPLUS_TIME.get(compoundTag);
                long outMana = AllNBTPack.OUT_MANA.get(compoundTag);

                Map<TagKey<Item>, Integer> item = null;
                if (AllNBTPack.ITEM_IN_MAP.contains(compoundTag)) {
                    item = AllNBTPack.ITEM_IN_MAP.get(compoundTag);
                }
                Map<TagKey<Fluid>, Integer> fluid = null;
                if (AllNBTPack.FLUID_IN_MAP.contains(compoundTag)) {
                    fluid = AllNBTPack.FLUID_IN_MAP.get(compoundTag);
                }
                Map<ItemStack, Double> outItem = null;
                if (AllNBTPack.ITEM_OUT_MAP.contains(compoundTag)) {
                    outItem = AllNBTPack.ITEM_OUT_MAP.get(compoundTag);
                }
                Map<FluidStack, Double> outFluid = null;
                if (AllNBTPack.FLUID_OUT_MAP.contains(compoundTag)) {
                    outFluid = AllNBTPack.FLUID_OUT_MAP.get(compoundTag);
                }
                boolean noEffective = true;
                if (item != null && item.isEmpty()) {
                    noEffective = false;
                }
                if (fluid != null && fluid.isEmpty()) {
                    noEffective = false;
                }
                if (outItem != null && outItem.isEmpty()) {
                    noEffective = false;
                }
                if (outFluid != null && outFluid.isEmpty()) {
                    noEffective = false;
                }
                if (noEffective) {
                    return new Shaped.ShapedOre(name, shapedType, shapedDrive, manaLevel, item, fluid, surplusTime, consumeMana, outMana, outItem, outFluid);
                }
                Dusk.instance.logger.error(String.format("解析配方时发现一无效配方%s", name));
                return EMPTY;
            }

            @Override
            public void write(Shaped.ShapedOre shaped, CachedOutput cachedOutput) throws IOException {
                CompoundTag compoundTag = new CompoundTag();
                AllNBTPack.CLASS.set(compoundTag, shaped.getClass());
                AllNBTPack.SHAPED_TYPE.set(compoundTag, shaped.shapedType);
                AllNBTPack.SHAPED_DRIVE.set(compoundTag, shaped.shapedDrive);
                AllNBTPack.MANA_LEVEL.set(compoundTag, shaped.manaLevel);
                AllNBTPack.CONSUME_MANA.set(compoundTag, shaped.consumeMana);
                AllNBTPack.SURPLUS_TIME.set(compoundTag, shaped.surplusTime);
                AllNBTPack.OUT_MANA.set(compoundTag, shaped.outMana);
                if (shaped.item != null) {
                    AllNBTPack.ITEM_IN_MAP.set(compoundTag, shaped.item);
                }
                if (shaped.fluid != null) {
                    AllNBTPack.FLUID_IN_MAP.set(compoundTag, shaped.fluid);
                }
                if (shaped.outItem != null) {
                    AllNBTPack.ITEM_OUT_MAP.set(compoundTag, shaped.outItem);
                }
                if (shaped.outFluid != null) {
                    AllNBTPack.FLUID_OUT_MAP.set(compoundTag, shaped.outFluid);
                }
                Path mainOutput = ModGatherData.dataGenerator.getOutputFolder();
                String pathSuffix = "data/" + shaped.name.getNamespace() + "/shaped/" + shaped.name.getPath() + ".json";
                Path outputPath = mainOutput.resolve(pathSuffix);
                DataProvider.saveStable(cachedOutput, NBTUtil.toJson(compoundTag), outputPath);
            }
        });
        BUILDER_MAP.put(Shaped.EmptyShaped.class, new ShapedBuilder<>() {
            @Override
            public Shaped as(ResourceLocation name, JsonObject s) {
                return EMPTY;
            }

            @Override
            public void write(Shaped shaped, CachedOutput cachedOutput) {
            }
        });
    }

    /***
     * 这是一个空配方
     * 当配方损坏的时候将代替
     */
    public static final Shaped EMPTY = new Shaped.EmptyShaped();

    public static void add(Shaped shaped) {
        MAP.computeIfAbsent(shaped.shapedType, k -> new HashMap<>(8)).computeIfAbsent(shaped.shapedDrive, k -> new ArrayList<>()).add(shaped);
        if (NAME_MAP.containsKey(shaped.name)) {
            Dusk.instance.logger.error(String.format("错误，有相同的配方名称[%s]", shaped.name));
        }
        NAME_MAP.put(shaped.name, shaped);
    }
}
