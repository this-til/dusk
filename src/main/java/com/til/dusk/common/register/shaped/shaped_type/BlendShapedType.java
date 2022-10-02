package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;


/**
 * @author til
 */
public class BlendShapedType extends ShapedType {

    public BlendShapedType() {
        super("blend", () -> ManaLevelBlock.blend);
    }

    @Override
    public void registerShaped() {
        /*for (Ore ore : Ore.screen(Ore.IS_CRYSTA)) {
            new ShapedOre(this, ShapedDrive.get(0), ore.manaLevel)
                    .addInItem(ore.itemMap.get(OreItem.dust).itemTag(), 1)
                    .addInItem(ItemTags.SAND, 1)
                    .addOutItem(new ItemStack(ore.itemMap.get(OreItem.crystalSeed).item(), 2), 1d)
                    .addMultipleSurplusTime(512L)
                    .addMultipleConsumeMana(12L);
        }*/
    }
}
