package com.til.dusk.common.register.mana_level;

import com.til.dusk.Dusk;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.util.pack.ItemPack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ManaLevelItem extends RegisterBasics.ItemUnitRegister<ManaLevelItem, ManaLevel> {

    public static Supplier<IForgeRegistry<ManaLevelItem>> LEVEL_ITEM;

    public static ManaLevelItem operationProcessor;
    public static ManaLevelItem operationCrystal;
    public static ManaLevelItem operationHost;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        LEVEL_ITEM = event.create(new RegistryBuilder<ManaLevelItem>().setName(new ResourceLocation(Dusk.MOD_ID, "mana_level_item")));
        operationProcessor = new ManaLevelItem("operation_processor") {

            @Override
            public @Nullable ItemPack create(ManaLevel manaLevel) {
                if (manaLevel.up != null) {
                    return super.create(manaLevel);
                }
                return null;
            }

            @Override
            public TagKey<Item> createItemTag(ManaLevel manaLevel) {
                if (manaLevel.up != null) {
                    return ItemTags.create(fuseName("/", manaLevel.up.get(), operationCrystal));
                }
                return null;
            }

        };
        operationCrystal = new ManaLevelItem("operation_crystal");
        operationHost = new ManaLevelItem("operation_host") {
            @Override
            public @Nullable ItemPack create(ManaLevel manaLevel) {
                if (manaLevel.next != null) {
                    return super.create(manaLevel);
                }
                return null;
            }

            @Override
            public TagKey<Item> createItemTag(ManaLevel manaLevel) {
                if (manaLevel.next != null) {
                    return ItemTags.create(fuseName("/", manaLevel.next.get(), operationCrystal));
                }
                return null;
            }

        };
    }

    public ManaLevelItem(ResourceLocation name) {
        super(name, LEVEL_ITEM);
    }

    public ManaLevelItem(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    @Override
    public void dyeBlack(ManaLevel manaLevel, ColorProxy.ItemColorPack itemColorPack) {
        itemColorPack.addColor(0, itemStack -> manaLevel.color);
    }
}
