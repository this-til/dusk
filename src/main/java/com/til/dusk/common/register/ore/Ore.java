package com.til.dusk.common.register.ore;

import com.til.dusk.Dusk;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.util.Extension;
import com.til.dusk.util.StaticTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
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
public class Ore extends RegisterBasics<Ore> {
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

    public static Supplier<IForgeRegistry<Ore>> ORE;

    public static Ore testOre;

    /***
     * 日耀
     */
    public static Ore sunlight;

    /***
     * 月耀
     */
    public static Ore moonlight;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        ORE = event.create(new RegistryBuilder<Ore>().setName(new ResourceLocation(Dusk.MOD_ID, "ore")));
        testOre = new Ore("test_ore", new Color(255, 200, 140), ManaLevel.t1);
        sunlight = new Ore("sunlight", new Color(250, 250, 87), ManaLevel.t2).removeTag(IS_METAL).addTag(IS_CRYSTA);
        moonlight = new Ore("moonlight", new Color(45, 121, 255, 255), ManaLevel.t2).removeTag(IS_METAL).addTag(IS_CRYSTA);
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

    public final Map<OreItem, Item> itemMap = new HashMap<>();
    public final Map<OreBlock, BlockItem> blockMap = new HashMap<>();
    public final Map<OreFluid, Fluid> fluidMap = new HashMap<>();


    public Ore(String name, Color color, ManaLevel manaLevel) {
        this(new ResourceLocation(Dusk.MOD_ID, name), color, manaLevel);
        addTag(HAS_MINERAL_BLOCK, HAS_BLOCK, HAS_FLUID, HAS_CRUSHED, HAS_DUST,IS_METAL);
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
    public void registerSubsidiaryBlack() {
        for (OreItem oreItem : OreItem.ORE_ITEM.get()) {
            Item item = oreItem.create(this);
            if (item != null) {
                itemMap.put(oreItem, item);
            }
        }
        for (OreBlock oreBlock : OreBlock.ORE_BLOCK.get()) {
            BlockItem blockItem = oreBlock.create(this);
            if (blockItem != null) {
                blockMap.put(oreBlock, blockItem);
            }
        }
        for (OreFluid oreFluid : OreFluid.ORE_FLUID.get()) {
            Fluid field = oreFluid.create(this);
            if (field != null) {
                fluidMap.put(oreFluid, field);
            }
        }
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

    public static List<Ore> screen(StaticTag... staticTags) {
        List<Ore> oreList = new ArrayList<>();
        for (Ore ore : ORE.get()) {
            if (ore.hasTag(staticTags)) {
                oreList.add(ore);
            }
        }
        return oreList;
    }

    public static abstract class OreType<T extends RegisterBasics<?>, I> extends RegisterBasics<T> {
        public OreType(ResourceLocation name, Supplier<IForgeRegistry<T>> iForgeRegistrySupplier) {
            super(name, iForgeRegistrySupplier);
        }

        /***
         * 创建元素
         * @param ore 创建的目标对象
         * @return 创建结果
         */
        @Nullable
        public abstract I create(Ore ore);

    }
}
