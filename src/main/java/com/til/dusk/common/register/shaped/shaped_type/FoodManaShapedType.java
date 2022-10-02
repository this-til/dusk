package com.til.dusk.common.register.shaped.shaped_type;

import com.google.gson.JsonObject;
import com.til.dusk.common.capability.handle.ShapedHandle;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.ShapedMiddleExtend;
import com.til.dusk.util.Lang;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author til
 */
public class FoodManaShapedType extends ShapedType {

    public FoodManaShapedType() {
        super("food_mana", () -> ManaLevelBlock.foodMana);
    }

    @Override
    public void registerShaped() {
        new FoodShaped(this, ShapedDrive.get(0), ManaLevel.t1)
                .addMultipleSurplusTime(1024)
                .addMultipleOutMana(512);
    }

    public static class FoodShaped extends ShapedMiddleExtend {

        public FoodShaped(ShapedType shapedType, ShapedDrive shapedDrive, ManaLevel manaLevel) {
            super(shapedType, shapedDrive, manaLevel);
        }

        public FoodShaped(ResourceLocation name, JsonObject jsonObject) throws Exception {
            super(name, jsonObject);
        }

        @Override
        protected boolean isItem(ItemStack itemStack) {
            FoodProperties foodProperties = itemStack.getItem().getFoodProperties(itemStack, null);
            if (foodProperties == null) {
                return false;
            }
            return foodProperties.getNutrition() > 0;
        }

        @Override
        protected ShapedHandle create(ItemStack itemStack) {
            FoodProperties foodProperties = itemStack.getItem().getFoodProperties(itemStack, null);
            if (foodProperties == null) {
                return null;
            }
            return new ShapedHandle(surplusTime, consumeMana, foodProperties.getNutrition() * outMana, null, null);
        }

        @Override
        public IJEIShaped getJEIShaped() {
            return new IJEIShaped() {
                @Override
                public @NotNull List<List<ItemStack>> getItemIn() {
                    List<ItemStack> itemStackList = new ArrayList<>();
                    for (Map.Entry<ResourceKey<Item>, Item> entry : ForgeRegistries.ITEMS.getEntries()) {
                        if (entry.getValue().isEdible()) {
                            FoodProperties foodProperties = entry.getValue().getFoodProperties(null, null);
                            if (foodProperties == null || foodProperties.getNutrition() <= 0) {
                                continue;
                            }
                            itemStackList.add(new ItemStack(entry.getValue()));
                        }
                    }
                    return List.of(itemStackList);
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
                componentList.add(Lang.getLang(Component.translatable(Lang.getKey("消耗灵气")), Component.literal(String.valueOf(consumeMana))));
            }
            if (surplusTime > 0) {
                componentList.add(Lang.getLang(Component.translatable(Lang.getKey("消耗时间")), Component.literal(String.valueOf(surplusTime))));
            }
            if (outMana > 0) {
                componentList.add(Lang.getLang(Component.translatable(Lang.getKey("输出灵气")),
                        Component.translatable(Lang.getKey("food")),
                        Component.literal("x"),
                        Component.literal(String.valueOf(outMana))));
            }
            return componentList;
        }
    }
}
