package com.til.dusk.common.register.ore;

import com.til.dusk.Dusk;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.util.Extension;
import com.til.dusk.util.StaticTag;
import com.til.dusk.util.prefab.ColorPrefab;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.*;


import javax.annotation.Nullable;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Ore extends RegisterBasics.UnitRegister<Ore, OreItem, OreBlock, OreFluid> {
    /***
     * 有矿物方块
     */
    public static final StaticTag HAS_MINERAL_BLOCK = new StaticTag("HAS_MINERAL_BLOCK", List.of());

    /***
     * 拥有方块
     */
    public static final StaticTag HAS_BLOCK = new StaticTag("HAS_BLOCK", List.of());

    /***
     * 有流体
     */
    public static final StaticTag HAS_FLUID = new StaticTag("HAS_FLUID", List.of());

    /***
     * 粉碎矿物
     */
    public static final StaticTag HAS_CRUSHED = new StaticTag("HAS_CRUSHED", List.of(HAS_MINERAL_BLOCK));

    /***
     * 有矿粉
     */
    public static final StaticTag HAS_DUST = new StaticTag("HAS_DUST", List.of());

    /***
     * 是金属
     */
    public static final StaticTag IS_METAL = new StaticTag("IS_METAL", List.of());

    /***
     * 是晶体
     */
    public static final StaticTag IS_CRYSTA = new StaticTag("IS_CRYSTA", List.of());

    /***
     * 晶体可以种植
     */
    public static final StaticTag CAN_PLANT = new StaticTag("CAN_PLANT", List.of(IS_CRYSTA));

    public static Supplier<IForgeRegistry<Ore>> ORE;

    public static Ore testOre;

    public static Ore mana;

    /***
     * 日耀
     */
    public static Ore sunlight;

    /***
     * 月耀
     */
    public static Ore moonlight;

    /***
     * 雨灵
     */
    public static Ore rain;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        ORE = event.create(new RegistryBuilder<Ore>().setName(new ResourceLocation(Dusk.MOD_ID, "ore")));
        mana = new Ore("mana", ColorPrefab.MANA_IO, ManaLevel.t1).removeTag(HAS_MINERAL_BLOCK, HAS_BLOCK, HAS_CRUSHED, HAS_DUST, IS_METAL);
        testOre = new Ore("test_ore", new Color(255, 200, 140), ManaLevel.t1);
        sunlight = new Ore("sunlight", ColorPrefab.SUNLIGHT_COLOR, ManaLevel.t2).removeTag(IS_METAL).addTag(CAN_PLANT);
        moonlight = new Ore("moonlight", ColorPrefab.MOONLIGHT_COLOR, ManaLevel.t2).removeTag(IS_METAL).addTag(CAN_PLANT);
        rain = new Ore("rain", ColorPrefab.RAIN_COLOR, ManaLevel.t2).removeTag(IS_METAL).addTag(CAN_PLANT);
    }

    /***
     * 矿物的颜色
     */
    public final Color color;

    /***
     * 矿物处理的等级
     */
    public final ManaLevel manaLevel;

    /***
     * 强度系数，对应方块的采集时间和防爆，和加工时间倍数
     */
    public double strength = 1f;

    /***
     * 加工消耗灵气倍数
     */
    public double consume = 1f;


    public Ore(String name, Color color, ManaLevel manaLevel) {
        this(new ResourceLocation(Dusk.MOD_ID, name), color, manaLevel);
        addTag(HAS_MINERAL_BLOCK, HAS_BLOCK, HAS_FLUID, HAS_CRUSHED, HAS_DUST, IS_METAL);
    }


    public Ore(ResourceLocation name, Color color, ManaLevel manaLevel) {
        super(name, ORE);
        this.color = color;
        this.manaLevel = manaLevel;
    }

    public Ore setStrength(double strength) {
        this.strength = strength;
        return this;
    }

    public Ore setConsume(double consume) {
        this.consume = consume;
        return this;
    }

    @Override
    public Supplier<IForgeRegistry<OreItem>> itemRegistry() {
        return OreItem.ORE_ITEM;
    }

    @Override
    public Supplier<IForgeRegistry<OreBlock>> blockRegistry() {
        return OreBlock.ORE_BLOCK;
    }

    @Override
    public Supplier<IForgeRegistry<OreFluid>> fluidRegistry() {
        return OreFluid.ORE_FLUID;
    }

    /***
     * 洗矿副产物
     */
    @Nullable
    public List<Extension.Data_2<ItemStack, Double>> wash() {
        return null;
    }

    /***
     * 研磨副产物
     */
    @Nullable
    public List<Extension.Data_2<ItemStack, Double>> grind() {
        return null;
    }

    /***
     * 离心机副产物
     */
    @Nullable
    public List<Extension.Data_2<ItemStack, Double>> centrifugal() {
        return null;
    }

    /***
     * 筛选副产物
     */
    @Nullable
    public List<Extension.Data_2<ItemStack, Double>> screen() {
        return null;
    }

    public static List<Ore> screen(StaticTag... staticTags) {
        List<Ore> oreList = new ArrayList<>();
        for (Ore ore : ORE.get()) {
            if (ore.hasTag(staticTags)) {
                oreList.add(ore);
            }
        }
        return oreList;
    }
}
