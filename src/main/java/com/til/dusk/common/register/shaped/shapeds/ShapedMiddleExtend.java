package com.til.dusk.common.register.shaped.shapeds;

import com.til.dusk.common.capability.CapabilityHelp;
import com.til.dusk.common.capability.handle.IHandle;
import com.til.dusk.common.capability.handle.ShapedHandle;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

/***
 * 对单一物品进行处理
 * @author til
 */
public abstract class ShapedMiddleExtend extends ShapedMiddle {

    public ShapedMiddleExtend() {
        super();
    }

    public ShapedMiddleExtend(ResourceLocation name, ShapedType shapedType, ShapedDrive shapedDrive, ManaLevel manaLevel) {
        super(name, shapedType, shapedDrive, manaLevel);
    }

    @Nullable
    @Override
    public ShapedHandle get(IHandle iHandle, Map.Entry<IPosTrack, IItemHandler> items, Map<IPosTrack, IFluidHandler> fluids) {
        if (items == null) {
            return null;
        }
        if (!extractItem(iHandle, items, true).isEmpty()) {
            ItemStack outItemStack = extractItem(iHandle, items, false);
            return create(outItemStack);
        }
        return null;
    }


    protected ItemStack extractItem(IHandle iHandle, Map.Entry<IPosTrack, IItemHandler> items, boolean isSimulated) {
        for (int i = 0; i < items.getValue().getSlots(); i++) {
            ItemStack itemStack = items.getValue().getStackInSlot(i);
            if (itemStack.isEmpty()) {
                continue;
            }
            if (!isItem(itemStack)) {
                continue;
            }
            ItemStack outItemStack = CapabilityHelp.extractItem(iHandle.getPosTrack(), null, items, i, 1, isSimulated);
            if (outItemStack.isEmpty()) {
                continue;
            }
            return outItemStack;
        }
        return ItemStack.EMPTY;
    }

    /***
     * 在查询，提取时确定物品
     */
    protected abstract boolean isItem(ItemStack itemStack);

    protected abstract ShapedHandle create(ItemStack itemStack);

    @Override
    public boolean screenOfFluid(FluidStack fluidStack) {
        return true;
    }

    @Override
    public boolean screenOfItem(ItemStack itemStack) {
        return isItem(itemStack);
    }

    @Override
    public boolean hasItemIn() {
        return true;
    }

    @Override
    public boolean hasFluidIn() {
        return false;
    }

}
