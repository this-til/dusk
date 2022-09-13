package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreItem;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.Map;
import java.util.function.Supplier;

/**
 * @author til
 */
public class PressureStickShapedType extends ShapedType {

    public PressureStickShapedType() {
        super("pressure_stick", () -> ManaLevelBlock.pressureStick);
    }

    @Override
    public void registerShaped() {
        for (Ore ore : Ore.screen(Ore.IS_METAL)) {
            new ShapedOre(
                    this,
                    ShapedDrive.get(0),
                    ore.manaLevel,
                    Map.of(ore.itemMap.get(OreItem.ingot).itemTag(), 1),
                    null,
                    (long) (ore.strength * 512),
                    (long) (ore.consume * 32L),
                    0,
                    Map.of(new ItemStack(ore.itemMap.get(OreItem.stick).item()), 1d),
                    null);
        }
    }
}
