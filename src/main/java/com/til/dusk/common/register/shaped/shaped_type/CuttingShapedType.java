package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreBlock;
import com.til.dusk.common.register.ore.OreItem;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import net.minecraft.world.item.ItemStack;

import java.util.Map;

/**
 * @author til
 */
public class CuttingShapedType extends ShapedType {

    public CuttingShapedType() {
        super("cutting", () -> ManaLevelBlock.cutting);
    }

    @Override
    public void registerShaped() {
        for (Ore ore : Ore.screen(Ore.IS_METAL)) {
            new ShapedOre(this, ShapedDrive.get(0), ore.manaLevel)
                    .addInItem(ore.itemMap.get(OreItem.stick_long).itemTag(), 1)
                    .addOutItem(new ItemStack(ore.itemMap.get(OreItem.stick).item(), 2), 1d)
                    .addMultipleSurplusTime((long) (ore.strength * 64L))
                    .addMultipleOutMana((long) (ore.consume * 16L));
        }
        for (Ore ore : Ore.screen(Ore.DECORATE_BLOCK_DATA)) {
            new ShapedOre(this, ShapedDrive.get(1), ore.manaLevel)
                    .addInItem(ore.blockMap.get(OreBlock.block).blockItemTag(), 1)
                    .addOutItem(new ItemStack(ore.blockMap.get(OreBlock.slab).blockItem(), 2), 1d)
                    .addMultipleSurplusTime((long) (ore.strength * 1024L))
                    .addMultipleOutMana((long) (ore.consume * 24L));
            new ShapedOre(this, ShapedDrive.get(2), ore.manaLevel)
                    .addInItem(ore.blockMap.get(OreBlock.block).blockItemTag(), 1)
                    .addOutItem(new ItemStack(ore.blockMap.get(OreBlock.stairs).blockItem(), 1), 1d)
                    .addMultipleSurplusTime((long) (ore.strength * 1024L))
                    .addMultipleOutMana((long) (ore.consume * 24L));
            new ShapedOre(this, ShapedDrive.get(3), ore.manaLevel)
                    .addInItem(ore.blockMap.get(OreBlock.block).blockItemTag(), 1)
                    .addOutItem(new ItemStack(ore.blockMap.get(OreBlock.wall).blockItem(), 1), 1d)
                    .addMultipleSurplusTime((long) (ore.strength * 1024L))
                    .addMultipleOutMana((long) (ore.consume * 24L));
        }

    }
}
