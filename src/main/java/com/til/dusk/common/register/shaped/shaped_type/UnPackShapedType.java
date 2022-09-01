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

public class UnPackShapedType extends ShapedType{

    public UnPackShapedType(){
        super("unpack", () -> ManaLevelBlock.unpack);
    }

    @Override
    public void registerSubsidiaryBlack() {
        for (Ore ore : Ore.screen(Ore.HAS_BLOCK, Ore.IS_METAL)) {
            new Shaped.ShapedOre(
                    fuseName(this, ore, OreItem.ingot),
                    this,
                    ShapedDrive.get(0),
                    ore.manaLevel,
                    Map.of(ItemTags.create(fuseName(ore, OreBlock.block)), 1),
                    null,
                    (long) (ore.strength * 128L),
                    (long) (ore.consume * 4L),
                    0,
                    List.of(new ItemStack(ore.itemMap.get(OreItem.ingot).item(), 9)),
                    null);
        }

        for (Ore ore : Ore.screen(Ore.HAS_BLOCK, Ore.IS_CRYSTA)) {
            new Shaped.ShapedOre(
                    fuseName("__", this, ore, OreItem.ingot),
                    this,
                    ShapedDrive.get(1),
                    ore.manaLevel,
                    Map.of(ItemTags.create(fuseName(ore, OreBlock.block)), 1),
                    null,
                    (long) (ore.strength * 128L),
                    (long) (ore.consume * 4L),
                    0,
                    List.of(new ItemStack(ore.itemMap.get(OreItem.crystal).item(), 9)),
                    null);
        }

        for (Ore ore : Ore.screen(Ore.IS_METAL)) {
            new Shaped.ShapedOre(
                    fuseName(this, ore, OreItem.nuggets),
                    this,
                    ShapedDrive.get(2),
                    ore.manaLevel,
                    Map.of(ItemTags.create(fuseName(ore, OreItem.ingot)), 1),
                    null,
                    (long) (ore.strength * 128L),
                    (long) (ore.consume * 4L),
                    0,
                    List.of(new ItemStack(ore.itemMap.get(OreItem.nuggets).item(), 9)),
                    null);
        }

        for (Ore ore : Ore.screen(Ore.HAS_DUST)) {
            new Shaped.ShapedOre(
                    fuseName(this, ore, OreItem.dustTiny),
                    this,
                    ShapedDrive.get(3),
                    ore.manaLevel,
                    Map.of(ItemTags.create(fuseName(ore, OreItem.dust)), 1),
                    null,
                    (long) (ore.strength * 128L),
                    (long) (ore.consume * 4L),
                    0,
                    List.of(new ItemStack(ore.itemMap.get(OreItem.dustTiny).item(), 9)),
                    null);
        }


    }

}
