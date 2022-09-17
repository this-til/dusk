package com.til.dusk.common.register.shaped.shaped_type;

import com.google.gson.JsonObject;
import com.til.dusk.Dusk;
import com.til.dusk.common.capability.handle.ShapedHandle;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.data.tag.PotionsTag;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedMiddleExtend;
import com.til.dusk.util.Lang;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.IReverseTag;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author til
 */
public class PotionManaShapedType extends ShapedType {

    public PotionManaShapedType() {
        super("potion_mana", () -> ManaLevelBlock.potionMana);
    }

    @Override
    public void registerShaped() {
        new ShapedPotionMana(this, ShapedDrive.get(0), ManaLevel.t1, 1024, 0, 20);
    }

    public static class ShapedPotionMana extends ShapedMiddleExtend {

        public ShapedPotionMana(ShapedType shapedType, ShapedDrive shapedDrive, ManaLevel manaLevel, long surplusTime, long consumeMana, long outMana) {
            super(shapedType, shapedDrive, manaLevel, surplusTime, consumeMana, outMana);
        }

        public ShapedPotionMana(ResourceLocation name, JsonObject jsonObject) throws Exception {
            super(name, jsonObject);
        }

        @Override
        protected boolean isItem(ItemStack itemStack) {
            if (!itemStack.is(ItemTag.POTIONS)) {
                return false;
            }
            Potion potion = PotionUtils.getPotion(itemStack);
            Optional<IReverseTag<Potion>> optional = Dusk.instance.POTION_TAG.getReverseTag(potion);
            if (optional.isEmpty()) {
                return false;
            }
            return !optional.get().containsTag(PotionsTag.NO_EFFECT);
        }

        @Override
        protected ShapedHandle create(ItemStack outItemStack) {
            Potion potion = PotionUtils.getPotion(outItemStack);
            long outMana = 0;
            for (MobEffectInstance effect : potion.getEffects()) {
                long time = effect.getEffect().isInstantenous() ? 180 : effect.getDuration();
                outMana += time * (effect.getAmplifier() + 1);
            }
            ItemStack itemStack = outItemStack.copy();
            PotionUtils.setPotion(itemStack, Potions.WATER);
            return new ShapedHandle(surplusTime, consumeMana, outMana * this.outMana, List.of(itemStack), null);
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
            componentList.add(Lang.getLang(Component.translatable(Lang.getKey("需要灵压等级")), Component.literal(Lang.getKey(manaLevel))));
            componentList.add(Lang.getLang(Component.translatable(Lang.getKey("需要配方集")), Component.literal(shapedDrive.getLangKey())));
            if (consumeMana > 0) {
                componentList.add(Lang.getLang(Component.translatable(Lang.getKey("消耗灵气")), Component.literal(String.valueOf(consumeMana))));
            }
            if (surplusTime > 0) {
                componentList.add(Lang.getLang(Component.translatable(Lang.getKey("消耗时间")), Component.literal(String.valueOf(surplusTime))));
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
