package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreFluid;
import com.til.dusk.common.register.ore.OreItem;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import net.minecraft.world.item.ItemStack;

import java.util.Map;

/**
 * @author til
 */
public class FreezingShapedType extends ShapedType {
    public FreezingShapedType() {
        super("freezing", () -> ManaLevelBlock.freezing);
    }

    @Override
    public void registerShaped() {
        for (Ore ore : Ore.screen(Ore.IS_METAL, Ore.FLUID_DATA)) {
            new ShapedOre(
                    this,
                    ShapedDrive.get(0),
                    ore.manaLevel,
                    null,
                    Map.of(ore.fluidMap.get(OreFluid.solution).fluidTag(), 144),
                    (long) (ore.strength * 724L),
                    (long) (ore.consume * 32L),
                    0,
                    Map.of(new ItemStack(ore.itemMap.get(OreItem.ingot).item(), 1), 1D),
                    null);
        }
    }
}
