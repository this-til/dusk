package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.capability.handle.ShapedHandle;
import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.fluid.OreFluid;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.common.register.shaped.shapeds.ShapedMiddleExtend;
import com.til.dusk.util.Lang;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author til
 */
public class DecomposeShapedType extends ShapedType {

    public DecomposeShapedType() {
        super("decompose");
    }

    @Override
    public void defaultConfig() {
        blockTagKey = new Delayed.BlockDelayed(() -> ManaLevelBlock.decompose.tagPackSupplier.getTagPack().blockTagKey());
        relevantShaped = new Delayed.ListShapedDelayed(() -> List.of(
                new FoolDecomposeShaped(name, this, ShapedDrive.get(0), ManaLevel.t1, 20)
                        .addMultipleSurplusTime(752L)
                        .addMultipleConsumeMana(18L)));
    }

    @Override
    public void registerRuleShaped(Consumer<Shaped> shapedConsumer) {

    }

    public static class FoolDecomposeShaped extends ShapedMiddleExtend {

        /***
         * 基础提取
         */
        public int basicsOut;

        public FoolDecomposeShaped() {
        }

        public FoolDecomposeShaped(ResourceLocation name, ShapedType shapedType, ShapedDrive shapedDrive, ManaLevel manaLevel, int basicsOut) {
            super(name, shapedType, shapedDrive, manaLevel);
            this.basicsOut = basicsOut;
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

                @Override
                public @NotNull List<List<FluidStack>> getFluidOut() {
                    FluidStack fluidStack = new FluidStack(Ore.nutrient.get(OreFluid.solution).source(), basicsOut);
                    return List.of(List.of(fluidStack));
                }
            };
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
            return new ShapedHandle(surplusTime, consumeMana, outMana, null, List.of(new FluidStack(Ore.nutrient.get(OreFluid.solution).source(), basicsOut * foodProperties.getNutrition())));
        }

        @Override
        public List<Component> getComponent() {
            List<Component> list = super.getComponent();
            list.add(Lang.getLang(Component.translatable(Lang.getKey("输出营养液")),
                    Component.translatable("消耗食物饱食度"),
                    Component.literal("x"),
                    Component.literal(String.valueOf(basicsOut))));
            return list;
        }
    }
}
