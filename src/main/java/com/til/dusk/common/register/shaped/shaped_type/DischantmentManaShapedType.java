package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.capability.handle.ShapedHandle;
import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.common.register.shaped.shapeds.ShapedMiddleExtend;
import com.til.dusk.common.world.DuskEnchantment;
import com.til.dusk.util.Lang;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author til
 */
public class DischantmentManaShapedType extends ShapedType {
    public DischantmentManaShapedType() {
        super("dischantment_mana");
    }

    @Override
    public void defaultConfig() {
        blockTagKey = new Delayed.BlockDelayed(() -> ManaLevelBlock.dischantmentMana.tagPackSupplier.getTagPack().blockTagKey());
        relevantShaped = new Delayed.ListShapedDelayed(() -> List.of(
                new DischantmentManaShaped(name, this, ShapedDrive.get(0), ManaLevel.t1)
                        .addMultipleSurplusTime(1024L)
                        .addMultipleConsumeMana(4096L)));
    }

    @Override
    public void registerRuleShaped(Consumer<Shaped> shapedConsumer) {

    }

    public static class DischantmentManaShaped extends ShapedMiddleExtend {

        public DischantmentManaShaped() {
        }

        public DischantmentManaShaped(ResourceLocation name, ShapedType shapedType, ShapedDrive shapedDrive, ManaLevel manaLevel) {
            super(name, shapedType, shapedDrive, manaLevel);
        }


        @Override
        protected boolean isItem(ItemStack itemStack) {
            return !EnchantmentHelper.getEnchantments(itemStack).isEmpty();
        }

        @Override
        protected ShapedHandle create(ItemStack itemStack) {
            Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(itemStack);
            if (map.isEmpty()) {
                return null;
            }
            int l = 0;
            for (Map.Entry<Enchantment, Integer> entry : map.entrySet()) {
                l += entry.getValue();
            }
            ItemStack outItemStack = itemStack.copy();
            if (outItemStack.is(Items.ENCHANTED_BOOK)) {
                outItemStack.removeTagKey("StoredEnchantments");
            }
            outItemStack.removeTagKey("Enchantments");
            return new ShapedHandle(surplusTime, 0, outMana * l, List.of(outItemStack), null);
        }

        @Override
        public IJEIShaped getJEIShaped() {
            return new IJEIShaped() {
                @Override
                public @NotNull List<List<ItemStack>> getItemIn() {
                    ItemStack itemStack = new ItemStack(Items.ENCHANTED_BOOK);
                    EnchantmentHelper.setEnchantments(Map.of(DuskEnchantment.EMPTY.get(), 1), itemStack);
                    return List.of(List.of(itemStack));
                }

                @Override
                public @NotNull List<List<ItemStack>> getItemOut() {
                    return List.of(List.of(new ItemStack(Items.ENCHANTED_BOOK)));
                }
            };
        }

        @Override
        public List<Component> getComponent() {
            List<Component> componentList = new ArrayList<>();
            componentList.add(Component.literal("message"));
            componentList.add(Lang.getLang(Component.translatable(Lang.getKey("??????????????????")), Component.translatable(manaLevel.name.toLanguageKey())));
            componentList.add(Lang.getLang(Component.translatable(Lang.getKey("???????????????")), Component.literal(shapedDrive.name.getPath())));
            if (consumeMana > 0) {
                componentList.add(Lang.getLang(Component.translatable(Lang.getKey("????????????")), Component.literal(String.valueOf(consumeMana))));
            }
            if (surplusTime > 0) {
                componentList.add(Lang.getLang(Component.translatable(Lang.getKey("????????????")), Component.literal(String.valueOf(surplusTime))));
            }
            if (outMana > 0) {
                componentList.add(Lang.getLang(Component.translatable(Lang.getKey("????????????")),
                        Lang.getLang(
                                Component.translatable("???????????????"),
                                Component.literal("x"),
                                Component.literal(String.valueOf(outMana)))));
            }
            return componentList;
        }
    }

}
