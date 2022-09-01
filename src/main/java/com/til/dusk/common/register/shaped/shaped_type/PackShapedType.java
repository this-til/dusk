package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreBlock;
import com.til.dusk.common.register.ore.OreItem;
import com.til.dusk.common.data.shaped.Shaped;
import com.til.dusk.common.register.shaped.ShapedDrive;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Map;

public class PackShapedType extends ShapedType{

    public PackShapedType(){
        super("pack", () -> ManaLevelBlock.pack);
    }

    @Override
    public void registerSubsidiaryBlack() {
        for (Ore ore : Ore.screen(Ore.HAS_BLOCK, Ore.IS_METAL)) {
            new Shaped.ShapedOre(
                    fuseName(this, ore, OreBlock.block),
                    this,
                    ShapedDrive.get(0),
                    ore.manaLevel,
                    Map.of(ItemTags.create(fuseName(ore, OreItem.ingot)), 9),
                    null,
                    (long) (ore.strength * 128L),
                    (long) (ore.consume * 4L),
                    0,
                    List.of(new ItemStack(ore.blockMap.get(OreBlock.block).blockItem())),
                    null);
        }

        for (Ore ore : Ore.screen(Ore.HAS_BLOCK, Ore.IS_CRYSTA)) {
            new Shaped.ShapedOre(
                    fuseName("__", this, ore, OreBlock.block),
                    this,
                    ShapedDrive.get(1),
                    ore.manaLevel,
                    Map.of(ItemTags.create(fuseName(ore, OreItem.crystal)), 9),
                    null,
                    (long) (ore.strength * 128L),
                    (long) (ore.consume * 4L),
                    0,
                    List.of(new ItemStack(ore.blockMap.get(OreBlock.block).blockItem())),
                    null);
        }

        for (Ore ore : Ore.screen(Ore.IS_METAL)) {
            new Shaped.ShapedOre(
                    fuseName(this, ore, OreItem.ingot),
                    this,
                    ShapedDrive.get(2),
                    ore.manaLevel,
                    Map.of(ItemTags.create(fuseName(ore, OreItem.nuggets)), 9),
                    null,
                    (long) (ore.strength * 128L),
                    (long) (ore.consume * 4L),
                    0,
                    List.of(new ItemStack(ore.itemMap.get(OreItem.ingot).item())),
                    null);
        }

        for (Ore ore : Ore.screen(Ore.HAS_DUST)) {
            new Shaped.ShapedOre(
                    fuseName(this, ore, OreItem.dust),
                    this,
                    ShapedDrive.get(3),
                    ore.manaLevel,
                    Map.of(ItemTags.create(fuseName(ore, OreItem.dustTiny)), 9),
                    null,
                    (long) (ore.strength * 128L),
                    (long) (ore.consume * 4L),
                    0,
                    List.of(new ItemStack(ore.itemMap.get(OreItem.dust).item())),
                    null);
        }
    }

}
