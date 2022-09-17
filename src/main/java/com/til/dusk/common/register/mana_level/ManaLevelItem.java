package com.til.dusk.common.register.mana_level;

import com.til.dusk.Dusk;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.util.Lang;
import com.til.dusk.util.pack.ItemPack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ManaLevelItem extends RegisterBasics.ItemUnitRegister<ManaLevelItem, ManaLevel> {

    public static Supplier<IForgeRegistry<ManaLevelItem>> LEVEL_ITEM;

    public static ManaLevelItem operationBasics;

    public static ManaLevelItem operation;

    /***
     * 成型核心
     */
    public static ManaLevelItem forming;

    /***
     * 破坏核心
     */
    public static ManaLevelItem destruction;

    /***
     * 聚集核心
     */
    public static ManaLevelItem gather;

    /***
     * 扩散核心
     */
    public static ManaLevelItem spread;

    /***
     * 动力核心
     */
    public static ManaLevelItem power;

    /***
     * 指令核心
     */
    public static ManaLevelItem instructions;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        LEVEL_ITEM = event.create(new RegistryBuilder<ManaLevelItem>().setName(new ResourceLocation(Dusk.MOD_ID, "mana_level_item")));
        operationBasics = new ManaLevelItemPack("operation_basics");
        operation = new ManaLevelItem("operation");
        forming = new ManaLevelItemPack("forming");
        destruction = new ManaLevelItemPack("destruction");
        gather = new ManaLevelItemPack("gather");
        spread = new ManaLevelItemPack("spread");
        power = new ManaLevelItemPack("power");
        instructions = new ManaLevelItemPack("instructions");
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

    public static class ManaLevelItemPack extends ManaLevelItem {

        public static final String INTEGRATE = "integrate";
        public static final String PROCESSOR = "processor";
        public static final String HOST = " host";

        public ManaLevelItem integrate;
        public ManaLevelItem host;

        public ManaLevelItemPack(ResourceLocation name) {
            super(name);
            integrate = new ManaLevelItem(new ResourceLocation(name.getNamespace(), name.getPath() + "_" + INTEGRATE)) {
                @Override
                public @Nullable ItemPack create(ManaLevel manaLevel) {
                    if (manaLevel.up != null) {
                        return super.create(manaLevel);
                    }
                    return null;
                }

                @Override
                public Item createItem(ManaLevel manaLevel) {
                    Item item = new Item(new Item.Properties().tab(Dusk.TAB)) {
                        @Override
                        public @NotNull Component getName(@NotNull ItemStack stack) {
                            return Lang.getLang(Lang.getLang(manaLevel), Lang.getLang(ManaLevelItemPack.this), Lang.getLang(Lang.getKey(INTEGRATE)));
                        }
                    };
                    assert manaLevel.up != null;
                    ItemTag.addTag(ItemTags.create(fuseName("/", manaLevel.up.get(), this)), item);
                    return item;
                }

            };
            host = new ManaLevelItem(new ResourceLocation(name.getNamespace(), name.getPath() + "_" + HOST)) {
                @Override
                public @Nullable ItemPack create(ManaLevel manaLevel) {
                    if (manaLevel.next != null) {
                        return super.create(manaLevel);
                    }
                    return null;
                }

                @Override
                public Item createItem(ManaLevel manaLevel) {
                    Item item = new Item(new Item.Properties().tab(Dusk.TAB)) {
                        @Override
                        public @NotNull Component getName(@NotNull ItemStack stack) {
                            return Lang.getLang(Lang.getLang(manaLevel), Lang.getLang(ManaLevelItemPack.this), Lang.getLang(Lang.getKey(HOST)));
                        }
                    };
                    assert manaLevel.next != null;
                    ItemTag.addTag(ItemTags.create(fuseName("/", manaLevel.next.get(), this)), item);
                    return item;
                }
            };
        }

        public ManaLevelItemPack(String name) {
            super(new ResourceLocation(Dusk.MOD_ID, name));
        }

        @Override
        public Item createItem(ManaLevel manaLevel) {
            return new Item(new Item.Properties().tab(Dusk.TAB)) {
                @Override
                public @NotNull Component getName(@NotNull ItemStack stack) {
                    return Lang.getLang(Lang.getLang(manaLevel), Lang.getLang(ManaLevelItemPack.this), Lang.getLang(Lang.getKey(PROCESSOR)));
                }
            };
        }
    }
}
