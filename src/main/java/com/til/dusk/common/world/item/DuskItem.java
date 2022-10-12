package com.til.dusk.common.world.item;


import com.til.dusk.Dusk;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.common.data.ModRecipeProvider;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.mana_level.item.ManaLevelItemPack;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.common.register.world.item.items.BindStaffItemRegister;
import com.til.dusk.common.register.world.item.items.ClearStaffRegister;
import com.til.dusk.common.register.world.item.items.CopyStaffItemRegister;
import com.til.dusk.common.register.world.item.items.ShowStaffRegister;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.nbt.pack.AllNBTPack;
import com.til.dusk.util.prefab.JsonPrefab;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.Tags;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryObject;

import java.text.MessageFormat;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DuskItem {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Dusk.MOD_ID);

    /***
     * 电阻
     */
    public static RegistryObject<ItemBasics> resistance;

    /***
     * 电容
     */
    public static RegistryObject<ItemBasics> capacitance;

    /***
     * 电感
     */
    public static RegistryObject<ItemBasics> inductance;

    /***
     * 二极管
     */
    public static RegistryObject<ItemBasics> diode;

    /***
     * 三极管
     */
    public static RegistryObject<ItemBasics> triode;

    /***
     * 贴片电感
     */
    public static RegistryObject<ItemBasics> patchInductance;

    /***
     * 贴片电阻
     */
    public static RegistryObject<ItemBasics> patchResistance;

    /***
     * 贴片电容
     */
    public static RegistryObject<ItemBasics> patchCapacitance;

    /***
     * 贴片二极管
     */
    public static RegistryObject<ItemBasics> patchDiode;

    /***
     * 贴片三极管
     */
    public static RegistryObject<ItemBasics> patchTriode;

    /***
     * 废料
     */
    public static RegistryObject<WasteItem> waste;

    static {

        resistance = ITEMS.register("resistance", () -> new ItemBasics.ItemGenerateModel(new Item.Properties().tab(Dusk.TAB))
                .addTag(ItemTag.resistanceTag)
                .addShaped(() -> new ShapedOre(resistance.getId(),ShapedType.encapsulation, ShapedDrive.get(0), ManaLevel.t1)
                        .addInItem(Ore.spiritSilver.get(OreItem.casing).itemTag(), 1)
                        .addInItem(Ore.spiritSilver.get(OreItem.string).itemTag(), 2)
                        .addInItem(Ore.cinnabar.get(OreItem.dust).itemTag(), 1)
                        .addOutItem(new ItemStack(resistance.get(), 4), 1d)
                        .addMultipleSurplusTime(1024L)
                        .addMultipleConsumeMana(12L)));
        capacitance = ITEMS.register("capacitance", () -> new ItemBasics.ItemGenerateModel(new Item.Properties().tab(Dusk.TAB))
                .addTag(ItemTag.capacitanceTag)
                .addShaped(() -> new ShapedOre(capacitance.getId(),ShapedType.encapsulation, ShapedDrive.get(0), ManaLevel.t1)
                        .addInItem(Ore.spiritSilver.get(OreItem.casing).itemTag(), 1)
                        .addInItem(Ore.spiritSilver.get(OreItem.string).itemTag(), 2)
                        .addInItem(Ore.pink.get(OreItem.dust).itemTag(), 1)
                        .addOutItem(new ItemStack(capacitance.get(), 4), 1d)
                        .addMultipleSurplusTime(1024L)
                        .addMultipleConsumeMana(12L)));
        inductance = ITEMS.register("inductance", () -> new ItemBasics.ItemGenerateModel(new Item.Properties().tab(Dusk.TAB))
                .addTag(ItemTag.inductanceTag)
                .addShaped(() -> new ShapedOre(inductance.getId(),ShapedType.encapsulation, ShapedDrive.get(0), ManaLevel.t1)
                        .addInItem(Ore.spiritSilver.get(OreItem.dust).itemTag(), 1)
                        .addInItem(Ore.spiritSilver.get(OreItem.casing).itemTag(), 4)
                        .addInItem(Ore.spiritSilver.get(OreItem.string).itemTag(), 2)
                        .addInItem(Ore.goldenrod.get(OreItem.string).itemTag(), 16)
                        .addOutItem(new ItemStack(inductance.get(), 1), 1d)
                        .addMultipleSurplusTime(1024L)
                        .addMultipleConsumeMana(22L)));
        diode = ITEMS.register("diode", () -> new ItemBasics.ItemGenerateModel(new Item.Properties().tab(Dusk.TAB))
                .addTag(ItemTag.diodeTag)
                .addShaped(() -> new ShapedOre(diode.getId(),ShapedType.encapsulation, ShapedDrive.get(0), ManaLevel.t1)
                        .addInItem(Ore.spiritSilver.get(OreItem.casing).itemTag(), 1)
                        .addInItem(Ore.spiritSilver.get(OreItem.string).itemTag(), 2)
                        .addInItem(Ore.tibetanBlue.get(OreItem.dust).itemTag(), 1)
                        .addInItem(Ore.pineCypress.get(OreItem.dust).itemTag(), 1)
                        .addOutItem(new ItemStack(diode.get(), 4), 1d)
                        .addMultipleSurplusTime(1024L)
                        .addMultipleConsumeMana(12L)));
        triode = ITEMS.register("triode", () -> new ItemBasics.ItemGenerateModel(new Item.Properties().tab(Dusk.TAB))
                .addTag(ItemTag.triodeTag)
                .addShaped(() -> new ShapedOre(triode.getId(),ShapedType.encapsulation, ShapedDrive.get(0), ManaLevel.t1)
                        .addInItem(Ore.spiritSilver.get(OreItem.casing).itemTag(), 1)
                        .addInItem(Ore.spiritSilver.get(OreItem.string).itemTag(), 3)
                        .addInItem(Ore.pineCypress.get(OreItem.dust).itemTag(), 1)
                        .addInItem(Ore.cotinusCoggygria.get(OreItem.dust).itemTag(), 2)
                        .addOutItem(new ItemStack(triode.get(), 4), 1d)
                        .addMultipleSurplusTime(1024L)
                        .addMultipleConsumeMana(12L)));
        patchResistance = ITEMS.register("patch_resistance", () -> new ItemBasics.ItemGenerateModel(new Item.Properties().tab(Dusk.TAB))
                .addTag(ItemTag.resistanceTag)
                .addShaped(() -> new ShapedOre(patchResistance.getId(),ShapedType.encapsulation, ShapedDrive.get(1), ManaLevel.t2)
                        .addInItem(Ore.spiritSilver.get(OreItem.foil).itemTag(), 1)
                        .addInItem(Ore.cinnabar.get(OreItem.dustTiny).itemTag(), 1)
                        .addOutItem(new ItemStack(patchResistance.get(), 4), 1d)
                        .addMultipleSurplusTime(512L)
                        .addMultipleConsumeMana(12L)));
        patchCapacitance = ITEMS.register("patch_capacitance", () -> new ItemBasics.ItemGenerateModel(new Item.Properties().tab(Dusk.TAB))
                .addTag(ItemTag.capacitanceTag)
                .addShaped(() -> new ShapedOre(patchCapacitance.getId(),ShapedType.encapsulation, ShapedDrive.get(1), ManaLevel.t2)
                        .addInItem(Ore.spiritSilver.get(OreItem.foil).itemTag(), 1)
                        .addInItem(Ore.pink.get(OreItem.dustTiny).itemTag(), 1)
                        .addOutItem(new ItemStack(patchCapacitance.get(), 4), 1d)
                        .addMultipleSurplusTime(512L)
                        .addMultipleConsumeMana(12L)));
        patchInductance = ITEMS.register("patch_inductance", () -> new ItemBasics.ItemGenerateModel(new Item.Properties().tab(Dusk.TAB))
                .addTag(ItemTag.inductanceTag)
                .addShaped(() -> new ShapedOre(patchInductance.getId(),ShapedType.encapsulation, ShapedDrive.get(1), ManaLevel.t2)
                        .addInItem(Ore.spiritSilver.get(OreItem.nuggets).itemTag(), 1)
                        .addInItem(Ore.spiritSilver.get(OreItem.foil).itemTag(), 4)
                        .addInItem(Ore.goldenrod.get(OreItem.string).itemTag(), 4)
                        .addOutItem(new ItemStack(patchInductance.get(), 1), 1d)
                        .addMultipleSurplusTime(512L)
                        .addMultipleConsumeMana(22L)));
        patchDiode = ITEMS.register("patch_diode", () -> new ItemBasics.ItemGenerateModel(new Item.Properties().tab(Dusk.TAB))
                .addTag(ItemTag.diodeTag)
                .addShaped(() -> new ShapedOre(patchDiode.getId(),ShapedType.encapsulation, ShapedDrive.get(1), ManaLevel.t2)
                        .addInItem(Ore.spiritSilver.get(OreItem.foil).itemTag(), 1)
                        .addInItem(Ore.tibetanBlue.get(OreItem.dustTiny).itemTag(), 1)
                        .addInItem(Ore.pineCypress.get(OreItem.dustTiny).itemTag(), 1)
                        .addOutItem(new ItemStack(patchDiode.get(), 4), 1d)
                        .addMultipleSurplusTime(512L)
                        .addMultipleConsumeMana(12L)));
        patchTriode = ITEMS.register("patch_triode", () -> new ItemBasics.ItemGenerateModel(new Item.Properties().tab(Dusk.TAB))
                .addTag(ItemTag.triodeTag)
                .addShaped(() -> new ShapedOre(patchTriode.getId(),ShapedType.encapsulation, ShapedDrive.get(1), ManaLevel.t2)
                        .addInItem(Ore.spiritSilver.get(OreItem.foil).itemTag(), 2)
                        .addInItem(Ore.pineCypress.get(OreItem.dustTiny).itemTag(), 1)
                        .addInItem(Ore.cotinusCoggygria.get(OreItem.dustTiny).itemTag(), 2)
                        .addOutItem(new ItemStack(patchTriode.get(), 4), 1d)
                        .addMultipleSurplusTime(512L)
                        .addMultipleConsumeMana(12L)));

        waste = ITEMS.register("waste", () -> new WasteItem(new Item.Properties().tab(Dusk.TAB)));
    }

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        ITEMS.register(Dusk.instance.modEventBus);
    }

    @SubscribeEvent
    public static void onEvent(FMLCommonSetupEvent event) {
        for (RegistryObject<Item> entry : ITEMS.getEntries()) {
            ResourceLocation resourceLocation = ForgeRegistries.ITEMS.getKey(entry.get());
            if (resourceLocation == null) {
                continue;
            }
            ItemTag.addTag(ItemTags.create(resourceLocation), entry.get());
        }
    }

    /***
     * 需要自定义颜色
     */
    public interface IHasCustomColor {
        /***
         * 物品颜色的回调
         * @param itemColorPack 物品染色
         */
        default void itemColorBlack(ColorProxy.ItemColorPack itemColorPack) {
            itemColorPack.addColor(0, itemStack -> {
                CompoundTag compoundTag = itemStack.getTag();
                if (compoundTag == null) {
                    return new DuskColor(-1);
                }
                return new DuskColor(AllNBTPack.COLOR.get(compoundTag));
            });
        }
    }

    /***
     * 自定义模型
     */
    public interface ICustomModel {
        /***
         * 物品自定义模型名称
         * @R
         */
        ResourceLocation itemModelName();

        default String itemJson() {
            ResourceLocation resourceLocation = itemModelName();
            return MessageFormat.format(itemJsonBasics(), resourceLocation.getNamespace(), resourceLocation.getPath());
        }

        default String itemJsonBasics() {
            return JsonPrefab.ITEM_FATHER;
        }
    }


}
