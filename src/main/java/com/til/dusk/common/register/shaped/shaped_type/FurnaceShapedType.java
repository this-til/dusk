package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;

public class FurnaceShapedType extends ShapedType {

    public FurnaceShapedType() {
        super("furnace", () -> ManaLevelBlock.furnace);
    }

    final RecipeManager.CachedCheck<Container, SmeltingRecipe> quickCheck = RecipeManager.createCheck(RecipeType.SMELTING);

    @Override
    public void registerShaped() {
        /*new Shaped(name, this, ShapedDrive.get(0), ManaLevel.t1) {
            @Override
            public ShapedHandle get(IHandle iControl, Map<BlockEntity, IItemHandler> items, Map<BlockEntity, IFluidHandler> fluids) {
                return null;
            }

            @Override
            public IJEIShaped getJEIShaped() {
                return null;
            }

            @Override
            public List<Component> getComponent() {
                return null;
            }

            @Override
            public boolean isJEIShow() {
                return false;
            }
        };
*/
    }

}
