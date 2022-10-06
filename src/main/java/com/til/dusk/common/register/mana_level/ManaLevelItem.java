package com.til.dusk.common.register.mana_level;

import com.til.dusk.Dusk;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.event.DelayTrigger;
import com.til.dusk.common.register.ItemUnitRegister;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.world.item.DuskItem;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.Lang;
import com.til.dusk.util.ResourceLocationUtil;
import com.til.dusk.util.pack.ItemPack;
import com.til.dusk.util.prefab.ColorPrefab;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ManaLevelItem extends ItemUnitRegister<ManaLevelItem, ManaLevel> {

    public static Supplier<IForgeRegistry<ManaLevelItem>> LEVEL_ITEM;

    public static ManaLevelItemPack operationBasics;

    public static ManaLevelItemPack operation;

    /***
     * 成型核心
     */
    public static ManaLevelItemPack forming;

    /***
     * 破坏核心
     */
    public static ManaLevelItemPack destruction;

    /***
     * 聚集核心
     */
    public static ManaLevelItemPack gather;

    /***
     * 扩散核心
     */
    public static ManaLevelItemPack spread;

    /***
     * 动力核心
     */
    public static ManaLevelItemPack power;

    /***
     * 指令核心
     */
    public static ManaLevelItemPack instructions;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        LEVEL_ITEM = event.create(new RegistryBuilder<ManaLevelItem>().setName(new ResourceLocation(Dusk.MOD_ID, "mana_level_item")));
        operationBasics = new ManaLevelItemPack("operation_basics", new DuskColor(25, 25, 25), new DuskColor(25, 25, 25));
        operation = new ManaLevelItemPack("operation", ColorPrefab.MANA_IO, ColorPrefab.MANA_IO);
        forming = new ManaLevelItemPack("forming", new DuskColor(228, 136, 151), ColorPrefab.ITEM_IO);
        destruction = new ManaLevelItemPack("destruction", new DuskColor(141, 116, 130), ColorPrefab.ITEM_IO);
        gather = new ManaLevelItemPack("gather", new DuskColor(107, 197, 191), ColorPrefab.FLUID_IO);
        spread = new ManaLevelItemPack("spread", new DuskColor(136, 209, 142), ColorPrefab.FLUID_IO);
        power = new ManaLevelItemPack("power", new DuskColor(242, 124, 99), ColorPrefab.CONTROL_TAG);
        instructions = new ManaLevelItemPack("instructions", new DuskColor(244, 233, 215), ColorPrefab.CONTROL_TAG);
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

    public static class ManaLevelItemPack {

        public static final String INTEGRATE = "integrate";
        public static final String PROCESSOR = "processor";
        public static final String HOST = "host";

        public static final ResourceLocation INTEGRATE_MODEL = new ResourceLocation(Dusk.MOD_ID, "integrate");
        public static final ResourceLocation PROCESSOR_MODEL = new ResourceLocation(Dusk.MOD_ID, "processor");
        public static final ResourceLocation HOST_MODEL = new ResourceLocation(Dusk.MOD_ID, "host");

        public final ResourceLocation name;

        public ManaLevelItem integrate;
        public ManaLevelItem processor;
        public ManaLevelItem host;

        public final DuskColor strokeColor;
        public final DuskColor coreColor;

        public Map<ManaLevel, TagKey<Item>> itemTag = new HashMap<>();

        public ManaLevelItemPack(ResourceLocation name, DuskColor strokeColor, DuskColor coreColor) {
            this.name = name;
            this.strokeColor = strokeColor;
            this.coreColor = coreColor;
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
                            return Lang.getLang(Lang.getLang(manaLevel), Component.translatable(Lang.getKey(ManaLevelItemPack.this.name)), Lang.getLang(Lang.getKey(INTEGRATE)));
                        }
                    };
                    assert manaLevel.up != null;
                    DelayTrigger.addRun(DelayTrigger.TAG, () -> ItemTag.addTag(getTag(manaLevel.up.get()), item));
                    return item;
                }

                @Override
                public void dyeBlack(ManaLevel manaLevel, ColorProxy.ItemColorPack itemColorPack) {
                    super.dyeBlack(manaLevel, itemColorPack);
                    itemColorPack.addColor(1, itemStack -> coreColor);
                    itemColorPack.addColor(2, itemStack -> strokeColor);
                }

                @Override
                public DuskItem.ICustomModel getItemMoldMapping(ManaLevel manaLevel) {
                    return () -> INTEGRATE_MODEL;
                }
            };
            processor = new ManaLevelItem(new ResourceLocation(name.getNamespace(), name.getPath() + "_" + PROCESSOR)) {
                @Override
                public Item createItem(ManaLevel manaLevel) {
                    Item item = new Item(new Item.Properties().tab(Dusk.TAB)) {
                        @Override
                        public @NotNull Component getName(@NotNull ItemStack stack) {
                            return Lang.getLang(Lang.getLang(manaLevel), Component.translatable(Lang.getKey(ManaLevelItemPack.this.name)), Lang.getLang(Lang.getKey(PROCESSOR)));
                        }
                    };
                    DelayTrigger.addRun(DelayTrigger.TAG, () -> ItemTag.addTag(getTag(manaLevel), item));
                    return item;
                }

                @Override
                public DuskItem.ICustomModel getItemMoldMapping(ManaLevel manaLevel) {
                    return () -> PROCESSOR_MODEL;
                }

                @Override
                public void dyeBlack(ManaLevel manaLevel, ColorProxy.ItemColorPack itemColorPack) {
                    super.dyeBlack(manaLevel, itemColorPack);
                    itemColorPack.addColor(1, itemStack -> coreColor);
                    itemColorPack.addColor(2, itemStack -> strokeColor);
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
                            return Lang.getLang(Lang.getLang(manaLevel), Component.translatable(Lang.getKey(ManaLevelItemPack.this.name)), Lang.getLang(Lang.getKey(HOST)));
                        }
                    };
                    assert manaLevel.next != null;
                    DelayTrigger.addRun(DelayTrigger.TAG, () -> ItemTag.addTag(getTag(manaLevel.next.get()), item));
                    return item;
                }

                @Override
                public void dyeBlack(ManaLevel manaLevel, ColorProxy.ItemColorPack itemColorPack) {
                    super.dyeBlack(manaLevel, itemColorPack);
                    itemColorPack.addColor(1, itemStack -> coreColor);
                    itemColorPack.addColor(2, itemStack -> strokeColor);
                }

                @Override
                public DuskItem.ICustomModel getItemMoldMapping(ManaLevel manaLevel) {
                    return () -> HOST_MODEL;
                }
            };
        }

        public ManaLevelItemPack(String name, DuskColor strokeColor, DuskColor coreColor) {
            this(new ResourceLocation(Dusk.MOD_ID, name), strokeColor, coreColor);
        }

        public TagKey<Item> getTag(ManaLevel manaLevel) {
            if (itemTag.containsKey(manaLevel)) {
                return itemTag.get(manaLevel);
            }
            TagKey<Item> tag = Dusk.instance.ITEM_TAG.createTagKey(ResourceLocationUtil.fuseName("/", manaLevel.name, name));
            itemTag.put(manaLevel, tag);
            return tag;
        }
    }
}
