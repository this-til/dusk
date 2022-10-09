package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
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
public class BlastFurnaceShapedType extends ShapedType {
    public BlastFurnaceShapedType() {
        super("blast_furnace", () -> ManaLevelBlock.blastFurnace);
    }


    @Override
    public void registerRuleShaped(Consumer<Shaped> shapedConsumer) {
        for (Ore ore : Ore.screen(Ore.IS_METAL, Ore.HAS_DUST)) {
            new ShapedOre(this, ShapedDrive.get(0), ore.manaLevel).addInItem(ore.get(OreItem.dust).itemTag(), 1)
                    .addOutItem(new ItemStack(ore.get(OreItem.ingot).item(), 1), 1d)
                    .addMultipleSurplusTime((long) (1024L * ore.strength))
                    .addMultipleConsumeMana((long) (32L * ore.consume));
        }
    }
}
