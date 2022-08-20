package com.til.dusk.common.register.mana_level;

import com.til.dusk.Dusk;
import com.til.dusk.client.util.Lang;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ManaLevelItem extends ManaLevel.ManaLevelType<ManaLevelItem, Item> {

    public static Supplier<IForgeRegistry<ManaLevelItem>> LEVEL_ITEM;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        LEVEL_ITEM = event.create(new RegistryBuilder<ManaLevelItem>().setName(new ResourceLocation(Dusk.MOD_ID, "mana_level_item")));
    }

    public ManaLevelItem(ResourceLocation name) {
        super(name, LEVEL_ITEM);
    }

    @Nullable
    @Override
    public Item create(ManaLevel manaLevel) {
        ManaLevelItem manaLevelItem = this;
        Item item = new Item(new Item.Properties().tab(Dusk.TAB)) {
            @Override
            public @NotNull Component getName(@NotNull ItemStack itemStack) {
                return Component.translatable(Lang.getLang(manaLevel, manaLevelItem));
            }
        };
        ForgeRegistries.ITEMS.register(fuseName("_", this, manaLevelItem), item);
        Objects.requireNonNull(ForgeRegistries.ITEMS.tags()).addOptionalTagDefaults(ItemTags.create(fuseName("_", this, manaLevelItem)), Set.of(() -> item));
        return item;
    }

}
