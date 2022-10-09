package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.ore.block.OreBlock;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;

/**
 * @author til
 */
public class CuttingShapedType extends ShapedType {

    public CuttingShapedType() {
        super("cutting", () -> ManaLevelBlock.cutting);
    }

    @Override
    public void registerRuleShaped(Consumer<Shaped> shapedConsumer) {
        for (Ore ore : Ore.screen(Ore.IS_METAL)) {
            new ShapedOre(this, ShapedDrive.get(0), ore.manaLevel)
                    .addInItem(ore.get(OreItem.stickLong).itemTag(), 1)
                    .addOutItem(new ItemStack(ore.get(OreItem.stick).item(), 2), 1d)
                    .addMultipleSurplusTime((long) (ore.strength * 64L))
                    .addMultipleOutMana((long) (ore.consume * 16L));
        }
        for (Ore ore : Ore.screen(Ore.DECORATE_BLOCK_DATA)) {
            new ShapedOre(this, ShapedDrive.get(1), ore.manaLevel)
                    .addInItem(ore.get(OreBlock.block).blockItemTag(), 1)
                    .addOutItem(new ItemStack(ore.get(OreBlock.slab).blockItem(), 2), 1d)
                    .addMultipleSurplusTime((long) (ore.strength * 1024L))
                    .addMultipleOutMana((long) (ore.consume * 24L));
            new ShapedOre(this, ShapedDrive.get(2), ore.manaLevel)
                    .addInItem(ore.get(OreBlock.block).blockItemTag(), 1)
                    .addOutItem(new ItemStack(ore.get(OreBlock.stairs).blockItem(), 1), 1d)
                    .addMultipleSurplusTime((long) (ore.strength * 1024L))
                    .addMultipleOutMana((long) (ore.consume * 24L));
            new ShapedOre(this, ShapedDrive.get(3), ore.manaLevel)
                    .addInItem(ore.get(OreBlock.block).blockItemTag(), 1)
                    .addOutItem(new ItemStack(ore.get(OreBlock.wall).blockItem(), 1), 1d)
                    .addMultipleSurplusTime((long) (ore.strength * 1024L))
                    .addMultipleOutMana((long) (ore.consume * 24L));
        }

    }
}
