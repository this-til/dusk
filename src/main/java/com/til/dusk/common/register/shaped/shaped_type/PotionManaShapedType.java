package com.til.dusk.common.register.shaped.shaped_type;

import com.google.gson.JsonObject;
import com.til.dusk.Dusk;
import com.til.dusk.common.capability.CapabilityHelp;
import com.til.dusk.common.capability.handle.IHandle;
import com.til.dusk.common.capability.handle.ShapedHandle;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.data.tag.PotionsTag;
import com.til.dusk.common.event.EventIO;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedMiddle;
import com.til.dusk.util.Lang;
import com.til.dusk.util.Pos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.IReverseTag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author til
 */
public class PotionManaShapedType extends ShapedType {

    public PotionManaShapedType() {
        super("potion_mana", () -> ManaLevelBlock.potionMana);
    }

    @Override
    public void registerSubsidiaryBlack() {
        new ShapedPotionMana( this, ShapedDrive.get(0), ManaLevel.t1, 1024, 0, 20);
    }

    public static class ShapedPotionMana extends ShapedMiddle {


        public ShapedPotionMana(ShapedType shapedType, ShapedDrive shapedDrive, ManaLevel manaLevel, long surplusTime, long consumeMana, long outMana) {
            super(shapedType, shapedDrive, manaLevel, surplusTime, consumeMana, outMana);
        }

        public ShapedPotionMana(ResourceLocation name, JsonObject jsonObject) throws Exception {
            super(name, jsonObject);
        }

        @Nullable
        @Override
        public ShapedHandle get(IHandle iHandle, Map<BlockEntity, IItemHandler> items, Map<BlockEntity, IFluidHandler> fluids) {
            if (!extractItem(iHandle, items, true).isEmpty()) {
                ItemStack outItemStack = extractItem(iHandle, items, false);
                Potion potion = PotionUtils.getPotion(outItemStack);
                long outMana = 0;
                for (MobEffectInstance effect : potion.getEffects()) {
                    long time = effect.getEffect().isInstantenous() ? 180 : effect.getDuration();
                    outMana += time * effect.getAmplifier();
                }
                ItemStack itemStack = outItemStack.copy();
                PotionUtils.setPotion(itemStack, Potions.WATER);
                return new ShapedHandle(surplusTime, consumeMana, outMana * this.outMana, List.of(itemStack), null);
            }
            return null;
        }


        protected ItemStack extractItem(IHandle iHandle, Map<BlockEntity, IItemHandler> items, boolean isSimulated) {
            for (Map.Entry<BlockEntity, IItemHandler> entry : items.entrySet()) {
                for (int i = 0; i < entry.getValue().getSlots(); i++) {
                    ItemStack itemStack = entry.getValue().getStackInSlot(i);
                    if (!itemStack.is(ItemTag.POTIONS)) {
                        continue;
                    }
                    Potion potion = PotionUtils.getPotion(itemStack);
                    Optional<IReverseTag<Potion>> optional = Dusk.instance.POTION_TAG.getReverseTag(potion);
                    if (optional.isEmpty()) {
                        continue;
                    }
                    if (optional.get().containsTag(PotionsTag.NO_EFFECT)) {
                        continue;
                    }
                    ItemStack outItemStack = CapabilityHelp.extractItem(iHandle.getPosTrack(), null, entry.getValue(), new Pos(entry.getKey()), i, 1,isSimulated);
                    if (outItemStack.isEmpty()) {
                        continue;
                    }
                    return outItemStack;
                }
            }
            return ItemStack.EMPTY;
        }

        @Override
        public IJEIShaped getJEIShaped() {
            return new IJEIShaped() {
                @Override
                public @NotNull List<List<ItemStack>> getItemIn() {
                    List<ItemStack> itemStackList = new ArrayList<>();
                    List<Item> itemList = Dusk.instance.ITEM_TAG.getTag(ItemTag.POTIONS).stream().toList();
                    for (Potion potion : ForgeRegistries.POTIONS) {
                        Optional<IReverseTag<Potion>> optional = Dusk.instance.POTION_TAG.getReverseTag(potion);
                        if (optional.isEmpty()) {
                            continue;
                        }
                        if (optional.get().containsTag(PotionsTag.NO_EFFECT)) {
                            continue;
                        }
                        for (Item item : itemList) {
                            ItemStack itemStack = new ItemStack(item);
                            PotionUtils.setPotion(itemStack, potion);
                            itemStackList.add(itemStack);
                        }
                    }
                    return List.of(itemStackList);
                }

                @Override
                public @NotNull List<List<ItemStack>> getItemOut() {
                    ItemStack itemStack = new ItemStack(Items.POTION);
                    return List.of(List.of(itemStack));
                }
            };


        }

        @Override
        public List<Component> getComponent() {
            List<Component> componentList = new ArrayList<>();
            componentList.add(Component.literal("message"));
            componentList.add(Lang.getLang(Lang.getKey("需要灵压等级"), Lang.getKey(manaLevel)));
            componentList.add(Lang.getLang(Lang.getKey("需要配方集"), shapedDrive.getLangKey()));
            if (consumeMana > 0) {
                componentList.add(Lang.getLang(Lang.getKey("消耗灵气"), String.valueOf(consumeMana)));
            }
            if (surplusTime > 0) {
                componentList.add(Lang.getLang(Lang.getKey("消耗时间"), String.valueOf(surplusTime)));
            }
            if (outMana > 0) {
                componentList.add(Lang.getLang(Component.translatable(Lang.getKey("输出灵气")),
                        Component.translatable(Lang.getKey("药水等级")), Component.literal("x"),
                        Component.translatable("药水时间"), Component.literal("x"),
                        Component.literal(String.valueOf(outMana))));
            }
            return componentList;
        }
    }
}
