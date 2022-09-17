package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.mana_level.ManaLevelItem;
import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreFluid;
import com.til.dusk.common.register.ore.OreItem;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.util.Extension;
import com.til.dusk.util.pack.BlockPack;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;

import java.util.HashMap;
import java.util.Map;

/**
 * @author til
 */
public class AssembleShapedType extends ShapedType {
    public AssembleShapedType() {
        super("assemble", () -> ManaLevelBlock.assemble);
    }

    @Override
    public void registerShaped() {
        int id = 1;
        for (ManaLevel manaLevel : ManaLevel.LEVEL.get()) {
            if (manaLevel.up == null) {
                continue;
            }
            for (Map.Entry<ManaLevelBlock, BlockPack> entry : manaLevel.blockMap.entrySet()) {
                if (!entry.getKey().hasSet(ManaLevelBlock.MECHANIC_UP_DATA)) {
                    continue;
                }
                ManaLevelBlock.MechanicUpData mechanicUpData = entry.getKey().getSet(ManaLevelBlock.MECHANIC_UP_DATA).get();
                Map<TagKey<Item>, Integer> inItem = new HashMap<>(2);
                {
                    inItem.put(manaLevel.up.get().blockMap.get(entry.getKey()).blockItemTag(), 1);
                    inItem.put(manaLevel.blockMap.get(ManaLevelBlock.frameBasic).blockItemTag(), 1);
                }
                Map<TagKey<Fluid>, Integer> inFluid = new HashMap<>(0);
                for (Extension.Action_3V<Map<TagKey<Item>, Integer>, Map<TagKey<Fluid>, Integer>, ManaLevel> e : mechanicUpData.run) {
                    e.action(inItem, inFluid, manaLevel);
                }
                new ShapedOre(
                        this,
                        ShapedDrive.get(id),
                        manaLevel.up.get(),
                        inItem,
                        inFluid,
                        manaLevel.level * mechanicUpData.timeMultiple,
                        manaLevel.level * mechanicUpData.consumeManaMultiple,
                        0,
                        Map.of(new ItemStack(entry.getValue().blockItem(), 1), 1d),
                        null
                );
            }
            id++;
        }
        id = 1;
        for (ManaLevel manaLevel : ManaLevel.LEVEL.get()) {
            for (Map.Entry<ManaLevelBlock, BlockPack> entry : manaLevel.blockMap.entrySet()) {
                if (!entry.getKey().hasSet(ManaLevelBlock.MECHANIC_MAKE_DATA)) {
                    continue;
                }
                ManaLevelBlock.MechanicMakeData mechanicMakeData = entry.getKey().getSet(ManaLevelBlock.MECHANIC_MAKE_DATA).get();
                Map<TagKey<Item>, Integer> inItem = new HashMap<>(1);
                Map<TagKey<Fluid>, Integer> inFluid = new HashMap<>(0);
                for (Extension.Action_3V<Map<TagKey<Item>, Integer>, Map<TagKey<Fluid>, Integer>, ManaLevel> run : mechanicMakeData.run) {
                    run.action(inItem, inFluid, manaLevel);
                }
                new ShapedOre(
                        this,
                        ShapedDrive.get(8),
                        manaLevel,
                        inItem,
                        inFluid,
                        manaLevel.level * mechanicMakeData.timeMultiple,
                        manaLevel.level * mechanicMakeData.consumeManaMultiple,
                        0,
                        Map.of(new ItemStack(entry.getValue().blockItem(), 1), 1d),
                        null
                );
            }
            id++;
        }

        for (Ore ore : Ore.screen(Ore.ARMOR_DATA)) {
            OreItem.ArmorData armorData = ore.getSet(Ore.ARMOR_DATA);

            {
                HashMap<TagKey<Item>, Integer> inItem = new HashMap<>(8);
                inItem.put(ore.itemMap.get(OreItem.plate_4).itemTag(), 32 * ore.manaLevel.level);
                inItem.put(ore.manaLevel.itemMap.get(ManaLevelItem.operation).itemTag(), 24 * ore.manaLevel.level);
                inItem.put(ore.manaLevel.itemMap.get(ManaLevelItem.forming).itemTag(), 12 * ore.manaLevel.level);
                inItem.put(ore.manaLevel.itemMap.get(ManaLevelItem.gather).itemTag(), 8 * ore.manaLevel.level);
                inItem.put(ore.manaLevel.itemMap.get(ManaLevelItem.instructions).itemTag(), 8 * ore.manaLevel.level);
                inItem.put(ore.manaLevel.itemMap.get(ManaLevelItem.power).itemTag(), 8 * ore.manaLevel.level);
                HashMap<TagKey<Fluid>, Integer> inFluid = new HashMap<>(8);
                inFluid.put(ore.fluidMap.get(OreFluid.solution).fluidTag(), 65536 * ore.manaLevel.level);
                new ShapedOre(
                        this,
                        ShapedDrive.get(9),
                        ore.manaLevel,
                        inItem,
                        inFluid,
                        16384L * ore.manaLevel.level,
                        64L * ore.manaLevel.level,
                        0,
                        Map.of(new ItemStack(ore.itemMap.get(OreItem.head).item(), 1), 1d),
                        null
                );
            }
            {
                HashMap<TagKey<Item>, Integer> inItem = new HashMap<>(8);
                inItem.put(ore.itemMap.get(OreItem.plate_4).itemTag(), 64 * ore.manaLevel.level);
                inItem.put(ore.manaLevel.itemMap.get(ManaLevelItem.operation).itemTag(), 24 * ore.manaLevel.level);
                inItem.put(ore.manaLevel.itemMap.get(ManaLevelItem.forming).itemTag(), 24 * ore.manaLevel.level);
                inItem.put(ore.manaLevel.itemMap.get(ManaLevelItem.gather).itemTag(), 24 * ore.manaLevel.level);
                inItem.put(ore.manaLevel.itemMap.get(ManaLevelItem.instructions).itemTag(), 24 * ore.manaLevel.level);
                inItem.put(ore.manaLevel.itemMap.get(ManaLevelItem.power).itemTag(), 24 * ore.manaLevel.level);
                HashMap<TagKey<Fluid>, Integer> inFluid = new HashMap<>(8);
                inFluid.put(ore.fluidMap.get(OreFluid.solution).fluidTag(), 65536 * ore.manaLevel.level);
                new ShapedOre(
                        this,
                        ShapedDrive.get(10),
                        ore.manaLevel,
                        inItem,
                        inFluid,
                        16384L * ore.manaLevel.level,
                        64L * ore.manaLevel.level,
                        0,
                        Map.of(new ItemStack(ore.itemMap.get(OreItem.chest).item(), 1), 1d),
                        null
                );
            }
            {
                HashMap<TagKey<Item>, Integer> inItem = new HashMap<>(8);
                inItem.put(ore.itemMap.get(OreItem.plate_4).itemTag(), 48 * ore.manaLevel.level);
                inItem.put(ore.manaLevel.itemMap.get(ManaLevelItem.operation).itemTag(), 24 * ore.manaLevel.level);
                inItem.put(ore.manaLevel.itemMap.get(ManaLevelItem.forming).itemTag(), 24 * ore.manaLevel.level);
                inItem.put(ore.manaLevel.itemMap.get(ManaLevelItem.gather).itemTag(), 12 * ore.manaLevel.level);
                inItem.put(ore.manaLevel.itemMap.get(ManaLevelItem.instructions).itemTag(), 12 * ore.manaLevel.level);
                inItem.put(ore.manaLevel.itemMap.get(ManaLevelItem.power).itemTag(), 12 * ore.manaLevel.level);
                HashMap<TagKey<Fluid>, Integer> inFluid = new HashMap<>(8);
                inFluid.put(ore.fluidMap.get(OreFluid.solution).fluidTag(), 65536 * ore.manaLevel.level);
                new ShapedOre(
                        this,
                        ShapedDrive.get(11),
                        ore.manaLevel,
                        inItem,
                        inFluid,
                        16384L * ore.manaLevel.level,
                        64L * ore.manaLevel.level,
                        0,
                        Map.of(new ItemStack(ore.itemMap.get(OreItem.legs).item(), 1), 1d),
                        null
                );
            }
            {
                HashMap<TagKey<Item>, Integer> inItem = new HashMap<>(8);
                inItem.put(ore.itemMap.get(OreItem.plate_4).itemTag(), 32 * ore.manaLevel.level);
                inItem.put(ore.manaLevel.itemMap.get(ManaLevelItem.operation).itemTag(), 24 * ore.manaLevel.level);
                inItem.put(ore.manaLevel.itemMap.get(ManaLevelItem.forming).itemTag(), 12 * ore.manaLevel.level);
                inItem.put(ore.manaLevel.itemMap.get(ManaLevelItem.gather).itemTag(), 4 * ore.manaLevel.level);
                inItem.put(ore.manaLevel.itemMap.get(ManaLevelItem.instructions).itemTag(), 16 * ore.manaLevel.level);
                inItem.put(ore.manaLevel.itemMap.get(ManaLevelItem.power).itemTag(), 16 * ore.manaLevel.level);
                HashMap<TagKey<Fluid>, Integer> inFluid = new HashMap<>(8);
                inFluid.put(ore.fluidMap.get(OreFluid.solution).fluidTag(), 65536 * ore.manaLevel.level);
                new ShapedOre(
                        this,
                        ShapedDrive.get(12),
                        ore.manaLevel,
                        inItem,
                        inFluid,
                        16384L * ore.manaLevel.level,
                        64L * ore.manaLevel.level,
                        0,
                        Map.of(new ItemStack(ore.itemMap.get(OreItem.feet).item(), 1), 1d),
                        null
                );
            }

        }
        for (Ore ore : Ore.screen(Ore.ARMS_DATA)) {
            OreItem.ArmsData armorData = ore.getSet(Ore.ARMS_DATA);

            {
                HashMap<TagKey<Item>, Integer> inItem = new HashMap<>(8);
                inItem.put(ore.itemMap.get(OreItem.swordBasics).itemTag(), 1);
                inItem.put(ore.itemMap.get(OreItem.plate_4).itemTag(), 128 * ore.manaLevel.level);
                inItem.put(ore.manaLevel.itemMap.get(ManaLevelItem.operation).itemTag(), 32 * ore.manaLevel.level);
                inItem.put(ore.manaLevel.itemMap.get(ManaLevelItem.destruction).itemTag(), 64 * ore.manaLevel.level);
                inItem.put(ore.manaLevel.itemMap.get(ManaLevelItem.spread).itemTag(), 48 * ore.manaLevel.level);
                inItem.put(ore.manaLevel.itemMap.get(ManaLevelItem.instructions).itemTag(), 32 * ore.manaLevel.level);
                inItem.put(ore.manaLevel.itemMap.get(ManaLevelItem.power).itemTag(), 48 * ore.manaLevel.level);
                HashMap<TagKey<Fluid>, Integer> inFluid = new HashMap<>(8);
                inFluid.put(ore.fluidMap.get(OreFluid.solution).fluidTag(), 65536 * ore.manaLevel.level);
                new ShapedOre(
                        this,
                        ShapedDrive.get(13),
                        ore.manaLevel,
                        inItem,
                        inFluid,
                        16384L * ore.manaLevel.level,
                        64L * ore.manaLevel.level,
                        0,
                        Map.of(new ItemStack(ore.itemMap.get(OreItem.sword).item(), 1), 1d),
                        null
                );
            }

            {
                HashMap<TagKey<Item>, Integer> inItem = new HashMap<>(8);
                inItem.put(ore.itemMap.get(OreItem.shovelBasics).itemTag(), 1);
                inItem.put(ore.itemMap.get(OreItem.plate_4).itemTag(), 32 * ore.manaLevel.level);
                inItem.put(ore.manaLevel.itemMap.get(ManaLevelItem.operation).itemTag(), 12 * ore.manaLevel.level);
                inItem.put(ore.manaLevel.itemMap.get(ManaLevelItem.destruction).itemTag(), 12 * ore.manaLevel.level);
                inItem.put(ore.manaLevel.itemMap.get(ManaLevelItem.spread).itemTag(), 4 * ore.manaLevel.level);
                inItem.put(ore.manaLevel.itemMap.get(ManaLevelItem.instructions).itemTag(), 4 * ore.manaLevel.level);
                inItem.put(ore.manaLevel.itemMap.get(ManaLevelItem.power).itemTag(), 8 * ore.manaLevel.level);
                HashMap<TagKey<Fluid>, Integer> inFluid = new HashMap<>(8);
                inFluid.put(ore.fluidMap.get(OreFluid.solution).fluidTag(), 65536 * ore.manaLevel.level);
                new ShapedOre(
                        this,
                        ShapedDrive.get(14),
                        ore.manaLevel,
                        inItem,
                        inFluid,
                        16384L * ore.manaLevel.level,
                        64L * ore.manaLevel.level,
                        0,
                        Map.of(new ItemStack(ore.itemMap.get(OreItem.shovel).item(), 1), 1d),
                        null
                );
            }

            {
                HashMap<TagKey<Item>, Integer> inItem = new HashMap<>(8);
                inItem.put(ore.itemMap.get(OreItem.pickaxeBasics).itemTag(), 1);
                inItem.put(ore.itemMap.get(OreItem.plate_4).itemTag(), 64 * ore.manaLevel.level);
                inItem.put(ore.manaLevel.itemMap.get(ManaLevelItem.operation).itemTag(), 32 * ore.manaLevel.level);
                inItem.put(ore.manaLevel.itemMap.get(ManaLevelItem.destruction).itemTag(), 48 * ore.manaLevel.level);
                inItem.put(ore.manaLevel.itemMap.get(ManaLevelItem.spread).itemTag(), 12 * ore.manaLevel.level);
                inItem.put(ore.manaLevel.itemMap.get(ManaLevelItem.instructions).itemTag(), 12 * ore.manaLevel.level);
                inItem.put(ore.manaLevel.itemMap.get(ManaLevelItem.power).itemTag(), 16 * ore.manaLevel.level);
                HashMap<TagKey<Fluid>, Integer> inFluid = new HashMap<>(8);
                inFluid.put(ore.fluidMap.get(OreFluid.solution).fluidTag(), 65536 * ore.manaLevel.level);
                new ShapedOre(
                        this,
                        ShapedDrive.get(15),
                        ore.manaLevel,
                        inItem,
                        inFluid,
                        16384L * ore.manaLevel.level,
                        64L * ore.manaLevel.level,
                        0,
                        Map.of(new ItemStack(ore.itemMap.get(OreItem.pickaxe).item(), 1), 1d),
                        null
                );
            }

            {
                HashMap<TagKey<Item>, Integer> inItem = new HashMap<>(8);
                inItem.put(ore.itemMap.get(OreItem.axeBasics).itemTag(), 1);
                inItem.put(ore.itemMap.get(OreItem.plate_4).itemTag(), 64 * ore.manaLevel.level);
                inItem.put(ore.manaLevel.itemMap.get(ManaLevelItem.operation).itemTag(), 32 * ore.manaLevel.level);
                inItem.put(ore.manaLevel.itemMap.get(ManaLevelItem.destruction).itemTag(), 64 * ore.manaLevel.level);
                inItem.put(ore.manaLevel.itemMap.get(ManaLevelItem.spread).itemTag(), 12 * ore.manaLevel.level);
                inItem.put(ore.manaLevel.itemMap.get(ManaLevelItem.instructions).itemTag(), 12 * ore.manaLevel.level);
                inItem.put(ore.manaLevel.itemMap.get(ManaLevelItem.power).itemTag(), 12 * ore.manaLevel.level);
                HashMap<TagKey<Fluid>, Integer> inFluid = new HashMap<>(8);
                inFluid.put(ore.fluidMap.get(OreFluid.solution).fluidTag(), 65536 * ore.manaLevel.level);
                new ShapedOre(
                        this,
                        ShapedDrive.get(16),
                        ore.manaLevel,
                        inItem,
                        inFluid,
                        16384L * ore.manaLevel.level,
                        64L * ore.manaLevel.level,
                        0,
                        Map.of(new ItemStack(ore.itemMap.get(OreItem.axe).item(), 1), 1d),
                        null
                );
            }

            {
                HashMap<TagKey<Item>, Integer> inItem = new HashMap<>(8);
                inItem.put(ore.itemMap.get(OreItem.hoeBasics).itemTag(), 1);
                inItem.put(ore.itemMap.get(OreItem.plate_4).itemTag(), 64 * ore.manaLevel.level);
                inItem.put(ore.manaLevel.itemMap.get(ManaLevelItem.operation).itemTag(), 16 * ore.manaLevel.level);
                inItem.put(ore.manaLevel.itemMap.get(ManaLevelItem.destruction).itemTag(), 4 * ore.manaLevel.level);
                inItem.put(ore.manaLevel.itemMap.get(ManaLevelItem.spread).itemTag(), 12 * ore.manaLevel.level);
                inItem.put(ore.manaLevel.itemMap.get(ManaLevelItem.instructions).itemTag(), 8 * ore.manaLevel.level);
                inItem.put(ore.manaLevel.itemMap.get(ManaLevelItem.power).itemTag(), 8 * ore.manaLevel.level);
                HashMap<TagKey<Fluid>, Integer> inFluid = new HashMap<>(8);
                inFluid.put(ore.fluidMap.get(OreFluid.solution).fluidTag(), 65536 * ore.manaLevel.level);
                new ShapedOre(
                        this,
                        ShapedDrive.get(17),
                        ore.manaLevel,
                        inItem,
                        inFluid,
                        16384L * ore.manaLevel.level,
                        64L * ore.manaLevel.level,
                        0,
                        Map.of(new ItemStack(ore.itemMap.get(OreItem.hoe).item(), 1), 1d),
                        null
                );
            }
        }
    }
}
