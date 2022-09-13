package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreBlock;
import com.til.dusk.common.register.ore.OreItem;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.util.pack.BlockPack;
import net.minecraft.world.item.ItemStack;

import java.util.Map;

/**
 * @author til
 */
public class GrindShapedType extends ShapedType{

    public GrindShapedType(){
        super("grind", () -> ManaLevelBlock.grind);
    }

    @Override
    public void registerShaped() {
        for (Ore ore : Ore.screen(Ore.MINERAL_BLOCK_DATA)) {
            for (Map.Entry<OreBlock, BlockPack> entry : ore.blockMap.entrySet()) {
                if (!(entry.getKey() instanceof OreBlock.MineralOreBlock)) {
                    continue;
                }
                new ShapedOre(
                        this,
                        ShapedDrive.get(0),
                        ore.manaLevel,
                        Map.of(ore.blockMap.get(entry.getKey()).blockItemTag(), 1),
                        null,
                        (long) (ore.strength * 640L),
                        (long) (ore.consume * 16L),
                        0,
                        Map.of(new ItemStack(ore.itemMap.get(OreItem.crushed).item(), 2), 1d),
                        null);
            }
        }

        for (Ore ore : Ore.screen(Ore.IS_METAL)) {
            new ShapedOre(
                    this,
                    ShapedDrive.get(1),
                    ore.manaLevel,
                    Map.of(ore.itemMap.get(OreItem.ingot).itemTag(), 1),
                    null,
                    (long) (ore.strength * 320L),
                    (long) (ore.consume * 8L),
                    0,
                    Map.of(new ItemStack(ore.itemMap.get(OreItem.dust).item(), 1),1d),
                    null
            );
        }

        for (Ore ore : Ore.screen(Ore.IS_CRYSTA)) {
            new ShapedOre(
                    this,
                    ShapedDrive.get(2),
                    ore.manaLevel,
                    Map.of(ore.itemMap.get(OreItem.crystal).itemTag(), 1),
                    null,
                    (long) (ore.strength * 320L),
                    (long) (ore.consume * 12L),
                    0,
                    Map.of(new ItemStack(ore.itemMap.get(OreItem.dust).item(), 1), 1d),
                    null
            );
            new ShapedOre(
                    this,
                    ShapedDrive.get(3),
                    ore.manaLevel,
                    Map.of(ore.itemMap.get(OreItem.damagedCrystal).itemTag(), 1),
                    null,
                    (long) (ore.strength * 640L),
                    (long) (ore.consume * 18L),
                    0,
                    Map.of(new ItemStack(ore.itemMap.get(OreItem.dust).item(), 1), 1d),
                    null
            );
            new ShapedOre(
                    this,
                    ShapedDrive.get(4),
                    ore.manaLevel,
                    Map.of(ore.itemMap.get(OreItem.delicateCrystal).itemTag(), 1),
                    null,
                    (long) (ore.strength * 640L),
                    (long) (ore.consume * 24L),
                    0,
                    Map.of(new ItemStack(ore.itemMap.get(OreItem.dust).item(), 2), 1d),
                    null
            );
            new ShapedOre(
                    this,
                    ShapedDrive.get(5),
                    ore.manaLevel,
                    Map.of(ore.itemMap.get(OreItem.perfectCrystal).itemTag(), 1),
                    null,
                    (long) (ore.strength * 640L),
                    (long) (ore.consume * 24L),
                    0,
                    Map.of(new ItemStack(ore.itemMap.get(OreItem.dust).item(), 3), 1d),
                    null
            );
        }
    }
}
