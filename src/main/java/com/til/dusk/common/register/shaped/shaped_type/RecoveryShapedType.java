package com.til.dusk.common.register.shaped.shaped_type;

import com.google.gson.JsonObject;
import com.til.dusk.common.capability.handle.ShapedHandle;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.common.register.shaped.shapeds.ShapedMiddleExtend;
import com.til.dusk.common.world.item.DuskItem;
import com.til.dusk.util.Lang;
import com.til.dusk.util.nbt.pack.AllNBTPack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

/**
 * @author til
 */
public class RecoveryShapedType extends ShapedType {

    public RecoveryShapedType() {
        super("recovery", () -> ManaLevelBlock.recovery);
    }

    @Override
    public void registerRuleShaped(Consumer<Shaped> shapedConsumer) {
        new RecoveryShaped(this, ShapedDrive.get(0), ManaLevel.t1, 0.2f)
                .addMultipleConsumeMana(128L)
                .addMultipleConsumeMana(8L);
    }

    public static class RecoveryShaped extends ShapedMiddleExtend {
        public final Random random = new Random();
        public final double probability;

        public RecoveryShaped(ShapedType shapedType, ShapedDrive shapedDrive, ManaLevel manaLevel, double probability) {
            super(shapedType, shapedDrive, manaLevel);
            this.probability = probability;
        }

        public RecoveryShaped(ResourceLocation name, JsonObject jsonObject) {
            super(name, jsonObject);
            probability = AllNBTPack.PROBABILITY.get(jsonObject);
        }

        @Override
        public JsonObject writ(JsonObject jsonObject) {
            AllNBTPack.PROBABILITY.set(jsonObject, probability);
            return super.writ(jsonObject);
        }


        @Override
        protected boolean isItem(ItemStack itemStack) {
            return true;
        }

        @Override
        protected ShapedHandle create(ItemStack itemStack) {
            List<ItemStack> itemStackList = new ArrayList<>();
            if (random.nextDouble() < probability) {
                itemStackList.add(new ItemStack(DuskItem.waste.get()));
            }
            return new ShapedHandle(surplusTime, consumeMana, outMana, itemStackList, null);
        }

        @Override
        public IJEIShaped getJEIShaped() {
            return new IJEIShaped() {
                @Override
                public @NotNull List<List<ItemStack>> getItemIn() {
                    return List.of(List.of(new ItemStack(Items.STONE)));
                }

                @Override
                public @NotNull List<List<ItemStack>> getItemOut() {
                    ItemStack itemStack = new ItemStack(DuskItem.waste.get());
                    CompoundTag compoundTag = new CompoundTag();
                    AllNBTPack.PROBABILITY.set(compoundTag, 0.2);
                    itemStack.setTag(compoundTag);
                    return List.of(List.of(itemStack));
                }
            };
        }

        @Override
        public List<Component> getComponent() {
            List<Component> componentList = super.getComponent();
            componentList.add(Component.translatable(Lang.getKey("接受任何物品的输入")));
            return componentList;
        }
    }
}
