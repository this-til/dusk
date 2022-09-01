package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreBlock;
import com.til.dusk.common.register.ore.OreItem;
import com.til.dusk.common.data.shaped.Shaped;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.util.Extension;
import com.til.dusk.util.pack.BlockPack;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author til
 */
public class GrindShapedType extends ShapedType{

    public GrindShapedType(){
        super("grind", () -> ManaLevelBlock.grind);
    }

    @Override
    public void registerSubsidiaryBlack() {
        for (Ore ore : Ore.screen(Ore.HAS_MINERAL_BLOCK, Ore.HAS_CRUSHED)) {
            for (Map.Entry<OreBlock, BlockPack> entry : ore.blockMap.entrySet()) {
                new Shaped.ShapedOre(
                        fuseName(this, ore, entry.getKey()),
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

        for (Ore ore : Ore.screen(Ore.IS_METAL, Ore.HAS_DUST)) {
            new Shaped.ShapedOre(
                    fuseName(this, ore, OreItem.dust),
                    this,
                    ShapedDrive.get(1),
                    ore.manaLevel,
                    Map.of(ItemTags.create(fuseName(ore, OreItem.ingot)), 1),
                    null,
                    (long) (ore.strength * 320L),
                    (long) (ore.consume * 8L),
                    0,
                    List.of(new ItemStack(ore.itemMap.get(OreItem.dust).item(), 1)),
                    null
            );
        }

        for (Ore ore : Ore.screen(Ore.IS_CRYSTA, Ore.HAS_DUST)) {
            new Shaped.ShapedOre(
                    fuseName("__", this, ore, OreItem.dust),
                    this,
                    ShapedDrive.get(2),
                    ore.manaLevel,
                    Map.of(ItemTags.create(fuseName(ore, OreItem.crystal)), 1),
                    null,
                    (long) (ore.strength * 320L),
                    (long) (ore.consume * 12L),
                    0,
                    List.of(new ItemStack(ore.itemMap.get(OreItem.dust).item(), 1)),
                    null
            );
            new Shaped.ShapedOre(
                    fuseName("___", this, ore, OreItem.dust),
                    this,
                    ShapedDrive.get(3),
                    ore.manaLevel,
                    Map.of(ItemTags.create(fuseName(ore, OreItem.damagedCrystal)), 1),
                    null,
                    (long) (ore.strength * 640L),
                    (long) (ore.consume * 18L),
                    0,
                    List.of(new ItemStack(ore.itemMap.get(OreItem.dust).item(), 1)),
                    null
            );
            new Shaped.ShapedOre(
                    fuseName("____", this, ore, OreItem.dust),
                    this,
                    ShapedDrive.get(4),
                    ore.manaLevel,
                    Map.of(ItemTags.create(fuseName(ore, OreItem.delicateCrystal)), 1),
                    null,
                    (long) (ore.strength * 640L),
                    (long) (ore.consume * 24L),
                    0,
                    List.of(new ItemStack(ore.itemMap.get(OreItem.dust).item(), 2)),
                    null
            );
            new Shaped.ShapedOre(
                    fuseName("_____", this, ore, OreItem.dust),
                    this,
                    ShapedDrive.get(5),
                    ore.manaLevel,
                    Map.of(ItemTags.create(fuseName(ore, OreItem.perfectCrystal)), 1),
                    null,
                    (long) (ore.strength * 640L),
                    (long) (ore.consume * 24L),
                    0,
                    List.of(new ItemStack(ore.itemMap.get(OreItem.dust).item(), 3)),
                    null
            );
        }
    }
}
