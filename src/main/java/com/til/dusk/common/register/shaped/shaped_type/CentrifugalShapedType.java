package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreItem;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author til
 */
public class CentrifugalShapedType extends ShapedType {

    public CentrifugalShapedType() {
        super("centrifugal", () -> ManaLevelBlock.centrifugal);
    }

    @Override
    public void registerShaped() {
        for (Ore ore : Ore.screen(Ore.MINERAL_BLOCK_DATA)) {
            HashMap<ItemStack, Double> outItem = new HashMap<>(1);
            outItem.put(new ItemStack(ore.itemMap.get(OreItem.dust).item(), 1), 1d);
            Supplier<Ore[]> centrifugeByproduct = ore.getSet(Ore.CENTRIFUGE_BYPRODUCT);
            if (centrifugeByproduct != null) {
                for (Ore ore1 : centrifugeByproduct.get()) {
                    outItem.put(new ItemStack(ore1.itemMap.get(OreItem.dustTiny).item(), 1), 0.5);
                }
            }
            new ShapedOre(
                    this,
                    ShapedDrive.get(0),
                    ore.manaLevel,
                    Map.of(ore.itemMap.get(OreItem.crushedPurified).itemTag(), 1),
                    null,
                    (long) (ore.strength * 1280L),
                    (long) (ore.consume * 48L),
                    0,
                    outItem,
                    null);
        }
    }

}
