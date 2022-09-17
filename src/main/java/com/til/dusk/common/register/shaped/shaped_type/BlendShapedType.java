package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreItem;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.Map;


/**
 * @author til
 */
public class BlendShapedType extends ShapedType {

    public BlendShapedType() {
        super("blend", () -> ManaLevelBlock.blend);
    }

    @Override
    public void registerShaped() {
        for (Ore ore : Ore.ORE.get()) {
            if (ore.hasSet(Ore.BLEND_BYPRODUCT)) {
                HashMap<TagKey<Item>, Integer> inItem = new HashMap<>(1);
                Ore[] ores = ore.getSet(Ore.BLEND_BYPRODUCT).get();
                for (Ore ore1 : ores) {
                    inItem.put(ore1.itemMap.get(OreItem.dust).itemTag(), 1);
                }
                new ShapedOre(
                        this,
                        ShapedDrive.get(0),
                        ore.manaLevel,
                        inItem,
                        null,
                        (long) (512L * ore.strength),
                        (long) (24L * ore.consume),
                        0,
                        Map.of(new ItemStack(ore.itemMap.get(OreItem.dust).item(), ores.length), 1d),
                        null
                );
            }

        }
    }
}
