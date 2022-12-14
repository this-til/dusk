package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;

import java.util.function.Consumer;

/**
 * @author til
 */
public class FurnaceShapedType extends ShapedType {

    public FurnaceShapedType() {
        super("furnace");
    }

    final RecipeManager.CachedCheck<Container, SmeltingRecipe> quickCheck = RecipeManager.createCheck(RecipeType.SMELTING);

    @Override
    public void defaultConfig() {
        blockTagKey =new Delayed.BlockDelayed(() ->  ManaLevelBlock.furnace.tagPackSupplier.getTagPack().blockTagKey());
    }

    @Override
    public void registerRuleShaped(Consumer<Shaped> shapedConsumer) {
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
