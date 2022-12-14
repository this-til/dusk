package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.Dusk;
import com.til.dusk.common.capability.handle.ShapedHandle;
import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.data.tag.PotionsTag;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
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
import java.util.function.Consumer;

/**
 * @author til
 */
public class PotionManaShapedType extends ShapedType {

    public PotionManaShapedType() {
        super("potion_mana");
    }

    @Override
    public void registerRuleShaped(Consumer<Shaped> shapedConsumer) {

    }

    @Override
    public void defaultConfig() {
        blockTagKey = new Delayed.BlockDelayed(() -> ManaLevelBlock.potionMana.tagPackSupplier.getTagPack().blockTagKey());
        relevantShaped = new Delayed.ListShapedDelayed(() -> List.of(new ShapedPotionMana(name, this, ShapedDrive.get(0), ManaLevel.t1)
                .addMultipleSurplusTime(1024L)
                .addMultipleOutMana(20L)));
    }

    public static class ShapedPotionMana extends ShapedMiddleExtend {
        public ShapedPotionMana() {

        }

        public ShapedPotionMana(ResourceLocation name, ShapedType shapedType, ShapedDrive shapedDrive, ManaLevel manaLevel) {
            super(name, shapedType, shapedDrive, manaLevel);
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
            componentList.add(Lang.getLang(Component.translatable(Lang.getKey("??????????????????")), Component.literal(manaLevel.name.toLanguageKey())));
            componentList.add(Lang.getLang(Component.translatable(Lang.getKey("???????????????")), Component.literal(shapedDrive.name.getPath())));
            if (consumeMana > 0) {
                componentList.add(Lang.getLang(Component.translatable(Lang.getKey("????????????")), Component.literal(String.valueOf(consumeMana))));
            }
            if (surplusTime > 0) {
                componentList.add(Lang.getLang(Component.translatable(Lang.getKey("????????????")), Component.literal(String.valueOf(surplusTime))));
            }
            if (outMana > 0) {
                componentList.add(Lang.getLang(Component.translatable(Lang.getKey("????????????")),
                        Component.translatable(Lang.getKey("????????????")), Component.literal("x"),
                        Component.translatable("????????????"), Component.literal("x"),
                        Component.literal(String.valueOf(outMana))));
            }
            return componentList;
        }
    }
}
