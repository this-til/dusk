package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
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
                        ShapedDrive.get(id),
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
    }
}
