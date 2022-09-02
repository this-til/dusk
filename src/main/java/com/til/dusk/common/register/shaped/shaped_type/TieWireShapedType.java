package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreItem;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Map;

public class TieWireShapedType extends ShapedType{

    public TieWireShapedType(){
        super("tie_wire",() -> ManaLevelBlock.tieWire);
    }

    @Override
    public void registerSubsidiaryBlack() {
        for (Ore ore : Ore.screen(Ore.IS_METAL)) {
            new ShapedOre(
                    this,
                    ShapedDrive.get(1),
                    ore.manaLevel,
                    Map.of(ore.itemMap.get(OreItem.stick).itemTag(), 1),
                    null,
                    (long) (ore.strength * 1024L),
                    (long) (ore.consume * 12L),
                    0,
                    Map.of(new ItemStack(ore.itemMap.get(OreItem.string).item(), 1), 1D),
                    null
            );
        }
    }

}
