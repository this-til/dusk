package com.til.dusk.common.register.shaped.shapeds;

import com.google.gson.JsonObject;
import com.til.dusk.Dusk;
import com.til.dusk.common.capability.CapabilityHelp;
import com.til.dusk.common.capability.handle.IHandle;
import com.til.dusk.common.capability.handle.ShapedHandle;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.data.tag.PotionsTag;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.util.Pos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.registries.tags.IReverseTag;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/***
 * 对单一物品进行处理
 */
public abstract class ShapedMiddleExtend extends ShapedMiddle {

    public ShapedMiddleExtend(ShapedType shapedType, ShapedDrive shapedDrive, ManaLevel manaLevel, long surplusTime, long consumeMana, long outMana) {
        super(shapedType, shapedDrive, manaLevel, surplusTime, consumeMana, outMana);
    }

    public ShapedMiddleExtend(ResourceLocation name, JsonObject jsonObject) throws Exception {
        super(name, jsonObject);
    }

    @Nullable
    @Override
    public ShapedHandle get(IHandle iHandle, Map<BlockEntity, IItemHandler> items, Map<BlockEntity, IFluidHandler> fluids) {
        if (!extractItem(iHandle, items, true).isEmpty()) {
            ItemStack outItemStack = extractItem(iHandle, items, false);
            create(outItemStack);
        }
        return null;
    }


    protected ItemStack extractItem(IHandle iHandle, Map<BlockEntity, IItemHandler> items, boolean isSimulated) {
        for (Map.Entry<BlockEntity, IItemHandler> entry : items.entrySet()) {
            for (int i = 0; i < entry.getValue().getSlots(); i++) {
                ItemStack itemStack = entry.getValue().getStackInSlot(i);
                if (!isItem(itemStack)) {
                    continue;
                }
                ItemStack outItemStack = CapabilityHelp.extractItem(iHandle.getPosTrack(), null, entry.getValue(), new Pos(entry.getKey()), i, 1, isSimulated);
                if (outItemStack.isEmpty()) {
                    continue;
                }
                return outItemStack;
            }
        }
        return ItemStack.EMPTY;
    }

    /***
     * 在查询，提取时确定物品
     */
    protected abstract boolean isItem(ItemStack itemStack);

    protected abstract ShapedHandle  create(ItemStack itemStack);
}
