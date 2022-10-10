package com.til.dusk.common.register.mana_level.item;

import com.til.dusk.Dusk;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.data.lang.LangType;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.event.RegisterLangEvent;
import com.til.dusk.common.register.RegisterManage;
import com.til.dusk.common.register.TagPackSupplierRegister;
import com.til.dusk.common.register.mana_level.item.packs.*;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.world.item.DuskItem;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.Lang;
import com.til.dusk.util.pack.ItemPack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;


@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public abstract class ManaLevelItemPack extends TagPackSupplierRegister<ManaLevelItemPack> {


    public static final ResourceLocation INTEGRATE = new ResourceLocation(Dusk.MOD_ID, "integrate");
    public static final ResourceLocation PROCESSOR = new ResourceLocation(Dusk.MOD_ID, "processor");
    public static final ResourceLocation HOST = new ResourceLocation(Dusk.MOD_ID, "host");

    public static Supplier<IForgeRegistry<ManaLevelItemPack>> MANA_LEVEL_ITEM_PACK;

    /***
     * 基础
     */
    public static OperationBasicsManaLevelItemPack operationBasics;

    /***
     * 运算
     */
    public static OperationManaLevelItemPack operation;

    /***
     * 成型核心
     */
    public static FormingManaLevelItemPack forming;

    /***
     * 破坏核心
     */
    public static DestructionManaLevelItemPack destruction;

    /***
     * 聚集核心
     */
    public static GatherManaLevelItemPack gather;

    /***
     * 扩散核心
     */
    public static SpreadManaLevelItemPack spread;

    /***
     * 动力核心
     */
    public static PowerManaLevelItemPack power;

    /***
     * 指令核心
     */
    public static InstructionsManaLevelItemPack instructions;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        MANA_LEVEL_ITEM_PACK = RegisterManage.create(ManaLevelItemPack.class, new ResourceLocation(Dusk.MOD_ID, "mana_level_item_pack"), event);
        operationBasics = new OperationBasicsManaLevelItemPack();
        operation = new OperationManaLevelItemPack();
        forming = new FormingManaLevelItemPack();
        destruction = new DestructionManaLevelItemPack();
        gather = new GatherManaLevelItemPack();
        spread = new SpreadManaLevelItemPack();
        power = new PowerManaLevelItemPack();
        instructions = new InstructionsManaLevelItemPack();
    }

    public ManaLevelItem integrate;
    public ManaLevelItem processor;
    public ManaLevelItem host;


    public ManaLevelItemPack(ResourceLocation name) {
        super(name, MANA_LEVEL_ITEM_PACK);
        integrate = new ManaLevelItem(new ResourceLocation(name.getNamespace(), name.getPath() + "_" + INTEGRATE)) {
            @Override
            public @Nullable ItemPack create(ManaLevel manaLevel) {
                if (manaLevel.getUp() != null) {
                    return super.create(manaLevel);
                }
                return null;
            }

            @Override
            public Item createItem(ManaLevel manaLevel) {
                Item item = new Item(new Item.Properties().tab(Dusk.TAB)) {
                    @Override
                    public @NotNull Component getName(@NotNull ItemStack stack) {
                        return Lang.getLang(Lang.getLang(manaLevel),
                                Component.translatable(ManaLevelItemPack.this.name.toLanguageKey()),
                                Component.translatable(INTEGRATE.toLanguageKey()));
                    }
                };
                assert manaLevel.getUp() != null;
                ItemTag.addTag(manaLevel.getUp().acceptableTagPack.getTagPack(ManaLevelItemPack.this.name).itemTagKey(), item);
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
                return () -> INTEGRATE;
            }
        };
        processor = new ManaLevelItem(new ResourceLocation(name.getNamespace(), name.getPath() + "_" + PROCESSOR)) {
            @Override
            public Item createItem(ManaLevel manaLevel) {
                Item item = new Item(new Item.Properties().tab(Dusk.TAB)) {
                    @Override
                    public @NotNull Component getName(@NotNull ItemStack stack) {
                        return Lang.getLang(Lang.getLang(manaLevel),
                                Component.translatable(ManaLevelItemPack.this.name.toLanguageKey()),
                                Component.translatable(PROCESSOR.toLanguageKey()));
                    }
                };
                ItemTag.addTag(manaLevel.acceptableTagPack.getTagPack(ManaLevelItemPack.this.name).itemTagKey(), item);
                return item;
            }

            @Override
            public DuskItem.ICustomModel getItemMoldMapping(ManaLevel manaLevel) {
                return () -> PROCESSOR;
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
                if (manaLevel.getNext() != null) {
                    return super.create(manaLevel);
                }
                return null;
            }

            @Override
            public Item createItem(ManaLevel manaLevel) {
                Item item = new Item(new Item.Properties().tab(Dusk.TAB)) {
                    @Override
                    public @NotNull Component getName(@NotNull ItemStack stack) {
                        return Lang.getLang(Lang.getLang(manaLevel),
                                Component.translatable(ManaLevelItemPack.this.name.toLanguageKey()),
                                Component.translatable(HOST.toLanguageKey()));
                    }
                };
                assert manaLevel.getNext() != null;
                ItemTag.addTag(manaLevel.getNext().acceptableTagPack.getTagPack(ManaLevelItemPack.this.name).itemTagKey(), item);
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
                return () -> HOST;
            }
        };
    }

    public ManaLevelItemPack(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    @SubscribeEvent
    public void onEvent(RegisterLangEvent event) {
        event.langTool.setCache(INTEGRATE.toLanguageKey());
        event.langTool.add(LangType.ZH_CN, "集成处理器");
        event.langTool.add(LangType.EN_CH, "Integrate Processor");
        event.langTool.setCache(PROCESSOR.toLanguageKey());
        event.langTool.add(LangType.ZH_CN, "处理器");
        event.langTool.add(LangType.EN_CH, "Processor");
        event.langTool.setCache(HOST.toLanguageKey());
        event.langTool.add(LangType.ZH_CN, "主机");
        event.langTool.add(LangType.EN_CH, "Host Processor");
    }

    @ConfigField public DuskColor coreColor;
    @ConfigField public DuskColor strokeColor;

}
