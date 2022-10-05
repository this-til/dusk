package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.util.pack.DataPack;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.ItemStack;

/**
 * @author til
 */
public class WashShapedType extends ShapedType {

    public WashShapedType() {
        super("wash", () -> ManaLevelBlock.wash);
    }

    @Override
    public void registerShaped() {
        for (Ore ore : Ore.screen(Ore.MINERAL_BLOCK_DATA)) {
            new ShapedOre(this, ShapedDrive.get(0), ore.manaLevel)
                    .addInItem(ore.get(OreItem.crushed).itemTag(), 1)
                    .addInFluid(FluidTags.WATER, 1000)
                    .addOutItem(new ItemStack(ore.get(OreItem.crushedPurified).item(), 1), 1d)
                    .runThis(s -> {
                        DataPack.OreDataPack centrifugeByproduct = ore.getSet(Ore.MINERAL_BLOCK_DATA).washByproduct;
                        if (centrifugeByproduct != null) {
                            centrifugeByproduct.run(s, null);
                        }
                    })
                    .addMultipleSurplusTime((long) (1280L * ore.strength))
                    .addMultipleConsumeMana((long) (12L * ore.consume));
        }
    }

}
