package com.til.dusk.common.register.ore;

import com.til.dusk.Dusk;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.common.register.mana_level.ManaLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.*;


import javax.annotation.Nullable;
import java.awt.*;
import java.util.*;
import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Ore extends RegisterBasics<Ore> {
    public static Supplier<IForgeRegistry<Ore>> ORE;

    public static Ore testOre;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        ORE = event.create(new RegistryBuilder<Ore>().setName(new ResourceLocation(Dusk.MOD_ID, "ore")));
        testOre = new Ore("test_ore", new Color(255, 200, 140), ManaLevel.t1).setStrength(0.1f);
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
     * 强度系数，对应方块的采集时间和防爆
     */
    public float strength;

    public final Map<OreItem, Item> itemMap = new HashMap<>();
    public final Map<OreBlock, BlockItem> blockMap = new HashMap<>();
    public final Map<OreFluid, Fluid> fluidMap = new HashMap<>();


    public Ore(String name, Color color, ManaLevel manaLevel) {
        this(new ResourceLocation(Dusk.MOD_ID, name), color, manaLevel);
    }


    public Ore(ResourceLocation name, Color color, ManaLevel manaLevel) {
        super(name, ORE);
        this.color = color;
        this.manaLevel = manaLevel;
    }

    public Ore setStrength(float strength) {
        this.strength = strength;
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

/*    public static class OreTagsProvider extends TagsProvider<Item> {

        protected OreTagsProvider(DataGenerator p_126546_, Registry<Item> p_126547_, String modId, @org.jetbrains.annotations.Nullable ExistingFileHelper existingFileHelper) {
            super(p_126546_, p_126547_, modId, existingFileHelper);
        }
    }*/

}
