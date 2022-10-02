package com.til.dusk.common.register.shaped;

import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;

public class CrystalSeedMakeShapedType extends ShapedType {

    public CrystalSeedMakeShapedType(){
        super("crystal_seed_make", () -> ManaLevelBlock.crystalSeedMake);
    }

    @Override
    public void registerShaped() {
        for (Ore ore : Ore.screen(Ore.IS_CRYSTA, Ore.FLUID_DATA, Ore.HAS_DUST)) {
            new ShapedOre(this, ShapedDrive.get(0), ore.manaLevel)
                    .addInItem(ItemTags.SAND, 1)
                    .addInItem(ore.itemMap.get(OreItem.dust).itemTag(), 1)
                    .addOutItem(new ItemStack(ore.itemMap.get(OreItem.crystalSeed).item(), 2), 1D);
        }
    }
}
