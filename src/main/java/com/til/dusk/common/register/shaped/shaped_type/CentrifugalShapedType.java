package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.util.pack.DataPack;
import net.minecraft.world.item.ItemStack;

/**
 * @author til
 */
public class CentrifugalShapedType extends ShapedType {

    public CentrifugalShapedType() {
        super("centrifugal", () -> ManaLevelBlock.centrifugal);
    }

    @Override
    public void registerShaped() {
        for (Ore ore : Ore.screen(Ore.MINERAL_BLOCK_DATA)) {
            new ShapedOre(this, ShapedDrive.get(0), ore.manaLevel)
                    .addInItem(ore.get(OreItem.crushedPurified).itemTag(), 1)
                    .addOutItem(new ItemStack(ore.get(OreItem.dust).item(), 1), 1d)
                    .runThis(s -> {
                        DataPack.OreDataPack centrifugeByproduct = ore.getSet(Ore.MINERAL_BLOCK_DATA).centrifugeByproduct;
                        if (centrifugeByproduct != null) {
                            centrifugeByproduct.run(s, null);
                        }
                    })
                    .addMultipleSurplusTime((long) (1280L * ore.strength))
                    .addMultipleConsumeMana((long) (48L * ore.consume));
        }
    }

}
