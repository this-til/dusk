package com.til.dusk.common.world.item;


import com.til.dusk.Dusk;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.common.data.ModRecipeProvider;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.mana_level.item.ManaLevelItem;
import com.til.dusk.common.register.mana_level.item.ManaLevelItemPack;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
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

    public static RegistryObject<BindStaffItem> bindStaff;
    public static RegistryObject<CopyStaffItem> copyStaff;
    public static RegistryObject<ShowStaffItem> showStaff;
    public static RegistryObject<ClearStaffItem> clearStaff;

    public static RegistryObject<DiamondMakeItem> diamondMakeOperationBasics;
    public static RegistryObject<DiamondMakeItem> diamondMakeOperation;
    public static RegistryObject<DiamondMakeItem> diamondMakeForming;
    public static RegistryObject<DiamondMakeItem> diamondMakeDestruction;
    public static RegistryObject<DiamondMakeItem> diamondMakeGather;
    public static RegistryObject<DiamondMakeItem> diamondMakeSpread;
    public static RegistryObject<DiamondMakeItem> diamondMakePower;
    public static RegistryObject<DiamondMakeItem> diamondMakeInstructions;

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

        bindStaff = ITEMS.register("bind_staff",
                () -> (BindStaffItem) new BindStaffItem(new Item.Properties().stacksTo(1).tab(Dusk.TAB))
                        .addRecipe(() -> ShapedRecipeBuilder.shaped(bindStaff.get())
                                .define('A', diamondMakeOperation.get().tag())
                                .define('B', Tags.Items.GEMS)
                                .pattern("  A")
                                .pattern(" B ")
                                .pattern("B  ")
                                .unlockedBy("has_ores", ModRecipeProvider.has(Tags.Items.ORES))));
        copyStaff = ITEMS.register("copy_staff",
                () -> (CopyStaffItem) new CopyStaffItem(new Item.Properties().stacksTo(1).tab(Dusk.TAB))
                        .addRecipe(() -> ShapedRecipeBuilder.shaped(copyStaff.get())
                                .define('A', diamondMakeInstructions.get().tag())
                                .define('B', Tags.Items.GEMS)
                                .pattern("  A")
                                .pattern(" B ")
                                .pattern("B  ")
                                .unlockedBy("has_ores", ModRecipeProvider.has(Tags.Items.ORES))));
        showStaff = ITEMS.register("show_staff",
                () -> (ShowStaffItem) new ShowStaffItem(new Item.Properties().stacksTo(1).tab(Dusk.TAB))
                        .addRecipe(() -> ShapedRecipeBuilder.shaped(showStaff.get())
                                .define('A', diamondMakeGather.get().tag())
                                .define('B', Tags.Items.GEMS)
                                .pattern("  A")
                                .pattern(" B ")
                                .pattern("B  ")
                                .unlockedBy("has_ores", ModRecipeProvider.has(Tags.Items.ORES))));
        clearStaff = ITEMS.register("clear_staff",
                () -> (ClearStaffItem) new ClearStaffItem(new Item.Properties().stacksTo(1).tab(Dusk.TAB))
                        .addRecipe(() -> ShapedRecipeBuilder.shaped(clearStaff.get())
                                .define('A', diamondMakeDestruction.get().tag())
                                .define('B', Tags.Items.GEMS)
                                .pattern("  A")
                                .pattern(" B ")
                                .pattern("B  ")
                                .unlockedBy("has_ores", ModRecipeProvider.has(Tags.Items.ORES))));


        diamondMakeOperationBasics = ITEMS.register("diamond_make_operation_basics",
                () -> (DiamondMakeItem) new DiamondMakeItem(new Item.Properties().tab(Dusk.TAB), new Delayed<>(() -> ManaLevelItemPack.operationBasics.strokeColor), new Delayed<>(() -> ManaLevelItemPack.operationBasics.coreColor))
                        .addTag(ManaLevel.t1.acceptableTagPack.getTagPack(ManaLevelItemPack.operationBasics).itemTagKey())
                        .addRecipe(() -> ShapedRecipeBuilder.shaped(diamondMakeOperationBasics.get())
                                .define('A', Tags.Items.GEMS_DIAMOND)
                                .define('B', ItemTag.REPEATER.d1())
                                .define('C', ItemTag.COMPARATOR.d1())
                                .define('D', Tags.Items.DUSTS_REDSTONE)
                                .pattern("DBD")
                                .pattern("CAC")
                                .pattern("DBD")
                                .unlockedBy("has_diamond", ModRecipeProvider.has(Tags.Items.GEMS_DIAMOND))));
        diamondMakeOperation = ITEMS.register("diamond_make_operation",
                () -> (DiamondMakeItem) new DiamondMakeItem(new Item.Properties().tab(Dusk.TAB), new Delayed<>(() -> ManaLevelItemPack.operation.strokeColor), new Delayed<>(() -> ManaLevelItemPack.operation.coreColor))
                        .addTag(ManaLevel.t1.acceptableTagPack.getTagPack(ManaLevelItemPack.operation).itemTagKey())
                        .addRecipe(() -> ShapedRecipeBuilder.shaped(diamondMakeOperation.get())
                                .define('A', diamondMakeOperationBasics.get().tag())
                                .define('B', Tags.Items.DUSTS_REDSTONE)
                                .define('C', Tags.Items.DUSTS_GLOWSTONE)
                                .pattern("BCB")
                                .pattern("CAC")
                                .pattern("BCB")
                                .unlockedBy("has_diamond_make_operation_basics", ModRecipeProvider.has(diamondMakeOperation.get().tag()))));
        diamondMakeForming = ITEMS.register("diamond_make_forming",
                () -> (DiamondMakeItem) new DiamondMakeItem(new Item.Properties().tab(Dusk.TAB), new Delayed<>(() -> ManaLevelItemPack.forming.strokeColor), new Delayed<>(() -> ManaLevelItemPack.forming.coreColor))
                        .addTag(ManaLevel.t1.acceptableTagPack.getTagPack(ManaLevelItemPack.forming).itemTagKey())
                        .addRecipe(() -> ShapedRecipeBuilder.shaped(diamondMakeForming.get())
                                .define('A', diamondMakeOperationBasics.get().tag())
                                .define('B', Tags.Items.GEMS_AMETHYST)
                                .pattern(" B ")
                                .pattern("BAB")
                                .pattern(" B ")
                                .unlockedBy("has_diamond_make_operation", ModRecipeProvider.has(diamondMakeOperation.get().tag()))));
        diamondMakeDestruction = ITEMS.register("diamond_make_destruction",
                () -> (DiamondMakeItem) new DiamondMakeItem(new Item.Properties().tab(Dusk.TAB), new Delayed<>(() -> ManaLevelItemPack.destruction.strokeColor), new Delayed<>(() -> ManaLevelItemPack.destruction.coreColor))
                        .addTag(ManaLevel.t1.acceptableTagPack.getTagPack(ManaLevelItemPack.destruction).itemTagKey())
                        .addRecipe(() -> ShapedRecipeBuilder.shaped(diamondMakeDestruction.get())
                                .define('A', diamondMakeOperationBasics.get().tag())
                                .define('B', Tags.Items.GEMS_QUARTZ)
                                .pattern(" B ")
                                .pattern("BAB")
                                .pattern(" B ")
                                .unlockedBy("has_diamond_make_operation", ModRecipeProvider.has(diamondMakeOperation.get().tag()))));
        diamondMakeGather = ITEMS.register("diamond_make_gather",
                () -> (DiamondMakeItem) new DiamondMakeItem(new Item.Properties().tab(Dusk.TAB), new Delayed<>(() -> ManaLevelItemPack.gather.strokeColor), new Delayed<>(() -> ManaLevelItemPack.gather.coreColor))
                        .addTag(ManaLevel.t1.acceptableTagPack.getTagPack(ManaLevelItemPack.gather).itemTagKey())
                        .addRecipe(() -> ShapedRecipeBuilder.shaped(diamondMakeGather.get())
                                .define('A', diamondMakeOperationBasics.get().tag())
                                .define('B', Tags.Items.GEMS_LAPIS)
                                .pattern(" B ")
                                .pattern("BAB")
                                .pattern(" B ")
                                .unlockedBy("has_diamond_make_operation", ModRecipeProvider.has(diamondMakeOperation.get().tag()))));
        diamondMakeSpread = ITEMS.register("diamond_make_spread",
                () -> (DiamondMakeItem) new DiamondMakeItem(new Item.Properties().tab(Dusk.TAB), new Delayed<>(() -> ManaLevelItemPack.spread.strokeColor), new Delayed<>(() -> ManaLevelItemPack.spread.coreColor))
                        .addTag(ManaLevel.t1.acceptableTagPack.getTagPack(ManaLevelItemPack.spread).itemTagKey())
                        .addRecipe(() -> ShapedRecipeBuilder.shaped(diamondMakeSpread.get())
                                .define('A', diamondMakeOperationBasics.get().tag())
                                .define('B', Tags.Items.GEMS_PRISMARINE)
                                .pattern(" B ")
                                .pattern("BAB")
                                .pattern(" B ")
                                .unlockedBy("has_diamond_make_operation", ModRecipeProvider.has(diamondMakeOperation.get().tag()))));
        diamondMakePower = ITEMS.register("diamond_make_power",
                () -> (DiamondMakeItem) new DiamondMakeItem(new Item.Properties().tab(Dusk.TAB), new Delayed<>(() -> ManaLevelItemPack.power.strokeColor), new Delayed<>(() -> ManaLevelItemPack.power.coreColor))
                        .addTag(ManaLevel.t1.acceptableTagPack.getTagPack(ManaLevelItemPack.power).itemTagKey())
                        .addRecipe(() -> ShapedRecipeBuilder.shaped(diamondMakePower.get())
                                .define('A', diamondMakeOperationBasics.get().tag())
                                .define('B', ItemTag.PISTON.d1())
                                .pattern(" B ")
                                .pattern("BAB")
                                .pattern(" B ")
                                .unlockedBy("has_diamond_make_operation", ModRecipeProvider.has(diamondMakePower.get().tag()))));
        diamondMakeInstructions = ITEMS.register("diamond_make_instructions",
                () -> (DiamondMakeItem) new DiamondMakeItem(new Item.Properties().tab(Dusk.TAB), new Delayed<>(() -> ManaLevelItemPack.instructions.strokeColor), new Delayed<>(() -> ManaLevelItemPack.instructions.coreColor))
                        .addTag(ManaLevel.t1.acceptableTagPack.getTagPack(ManaLevelItemPack.instructions).itemTagKey())
                        .addRecipe(() -> ShapedRecipeBuilder.shaped(diamondMakeInstructions.get())
                                .define('A', diamondMakeOperationBasics.get().tag())
                                .define('B', Tags.Items.BOOKSHELVES)
                                .pattern(" B ")
                                .pattern("BAB")
                                .pattern(" B ")
                                .unlockedBy("has_diamond_make_operation", ModRecipeProvider.has(diamondMakeOperation.get().tag()))));
        resistance = ITEMS.register("resistance", () -> new ItemBasics.ItemGenerateModel(new Item.Properties().tab(Dusk.TAB))
                .addTag(ItemTag.resistanceTag)
                .addShaped(() -> new ShapedOre(resistance.getKey().location(),ShapedType.encapsulation, ShapedDrive.get(0), ManaLevel.t1)
                        .addInItem(Ore.spiritSilver.get(OreItem.casing).itemTag(), 1)
                        .addInItem(Ore.spiritSilver.get(OreItem.string).itemTag(), 2)
                        .addInItem(Ore.cinnabar.get(OreItem.dust).itemTag(), 1)
                        .addOutItem(new ItemStack(resistance.get(), 4), 1d)
                        .addMultipleSurplusTime(1024L)
                        .addMultipleConsumeMana(12L)));
        capacitance = ITEMS.register("capacitance", () -> new ItemBasics.ItemGenerateModel(new Item.Properties().tab(Dusk.TAB))
                .addTag(ItemTag.capacitanceTag)
                .addShaped(() -> new ShapedOre(ShapedType.encapsulation, ShapedDrive.get(0), ManaLevel.t1)
                        .addInItem(Ore.spiritSilver.get(OreItem.casing).itemTag(), 1)
                        .addInItem(Ore.spiritSilver.get(OreItem.string).itemTag(), 2)
                        .addInItem(Ore.pink.get(OreItem.dust).itemTag(), 1)
                        .addOutItem(new ItemStack(capacitance.get(), 4), 1d)
                        .addMultipleSurplusTime(1024L)
                        .addMultipleConsumeMana(12L)));
        inductance = ITEMS.register("inductance", () -> new ItemBasics.ItemGenerateModel(new Item.Properties().tab(Dusk.TAB))
                .addTag(ItemTag.inductanceTag)
                .addShaped(() -> new ShapedOre(ShapedType.encapsulation, ShapedDrive.get(0), ManaLevel.t1)
                        .addInItem(Ore.spiritSilver.get(OreItem.dust).itemTag(), 1)
                        .addInItem(Ore.spiritSilver.get(OreItem.casing).itemTag(), 4)
                        .addInItem(Ore.spiritSilver.get(OreItem.string).itemTag(), 2)
                        .addInItem(Ore.goldenrod.get(OreItem.string).itemTag(), 16)
                        .addOutItem(new ItemStack(inductance.get(), 1), 1d)
                        .addMultipleSurplusTime(1024L)
                        .addMultipleConsumeMana(22L)));
        diode = ITEMS.register("diode", () -> new ItemBasics.ItemGenerateModel(new Item.Properties().tab(Dusk.TAB))
                .addTag(ItemTag.diodeTag)
                .addShaped(() -> new ShapedOre(ShapedType.encapsulation, ShapedDrive.get(0), ManaLevel.t1)
                        .addInItem(Ore.spiritSilver.get(OreItem.casing).itemTag(), 1)
                        .addInItem(Ore.spiritSilver.get(OreItem.string).itemTag(), 2)
                        .addInItem(Ore.tibetanBlue.get(OreItem.dust).itemTag(), 1)
                        .addInItem(Ore.pineCypress.get(OreItem.dust).itemTag(), 1)
                        .addOutItem(new ItemStack(diode.get(), 4), 1d)
                        .addMultipleSurplusTime(1024L)
                        .addMultipleConsumeMana(12L)));
        triode = ITEMS.register("triode", () -> new ItemBasics.ItemGenerateModel(new Item.Properties().tab(Dusk.TAB))
                .addTag(ItemTag.triodeTag)
                .addShaped(() -> new ShapedOre(ShapedType.encapsulation, ShapedDrive.get(0), ManaLevel.t1)
                        .addInItem(Ore.spiritSilver.get(OreItem.casing).itemTag(), 1)
                        .addInItem(Ore.spiritSilver.get(OreItem.string).itemTag(), 3)
                        .addInItem(Ore.pineCypress.get(OreItem.dust).itemTag(), 1)
                        .addInItem(Ore.cotinusCoggygria.get(OreItem.dust).itemTag(), 2)
                        .addOutItem(new ItemStack(triode.get(), 4), 1d)
                        .addMultipleSurplusTime(1024L)
                        .addMultipleConsumeMana(12L)));
        patchResistance = ITEMS.register("patch_resistance", () -> new ItemBasics.ItemGenerateModel(new Item.Properties().tab(Dusk.TAB))
                .addTag(ItemTag.resistanceTag)
                .addShaped(() -> new ShapedOre(ShapedType.encapsulation, ShapedDrive.get(1), ManaLevel.t2)
                        .addInItem(Ore.spiritSilver.get(OreItem.foil).itemTag(), 1)
                        .addInItem(Ore.cinnabar.get(OreItem.dustTiny).itemTag(), 1)
                        .addOutItem(new ItemStack(patchResistance.get(), 4), 1d)
                        .addMultipleSurplusTime(512L)
                        .addMultipleConsumeMana(12L)));
        patchCapacitance = ITEMS.register("patch_capacitance", () -> new ItemBasics.ItemGenerateModel(new Item.Properties().tab(Dusk.TAB))
                .addTag(ItemTag.capacitanceTag)
                .addShaped(() -> new ShapedOre(ShapedType.encapsulation, ShapedDrive.get(1), ManaLevel.t2)
                        .addInItem(Ore.spiritSilver.get(OreItem.foil).itemTag(), 1)
                        .addInItem(Ore.pink.get(OreItem.dustTiny).itemTag(), 1)
                        .addOutItem(new ItemStack(patchCapacitance.get(), 4), 1d)
                        .addMultipleSurplusTime(512L)
                        .addMultipleConsumeMana(12L)));
        patchInductance = ITEMS.register("patch_inductance", () -> new ItemBasics.ItemGenerateModel(new Item.Properties().tab(Dusk.TAB))
                .addTag(ItemTag.inductanceTag)
                .addShaped(() -> new ShapedOre(ShapedType.encapsulation, ShapedDrive.get(1), ManaLevel.t2)
                        .addInItem(Ore.spiritSilver.get(OreItem.nuggets).itemTag(), 1)
                        .addInItem(Ore.spiritSilver.get(OreItem.foil).itemTag(), 4)
                        .addInItem(Ore.goldenrod.get(OreItem.string).itemTag(), 4)
                        .addOutItem(new ItemStack(patchInductance.get(), 1), 1d)
                        .addMultipleSurplusTime(512L)
                        .addMultipleConsumeMana(22L)));
        patchDiode = ITEMS.register("patch_diode", () -> new ItemBasics.ItemGenerateModel(new Item.Properties().tab(Dusk.TAB))
                .addTag(ItemTag.diodeTag)
                .addShaped(() -> new ShapedOre(ShapedType.encapsulation, ShapedDrive.get(1), ManaLevel.t2)
                        .addInItem(Ore.spiritSilver.get(OreItem.foil).itemTag(), 1)
                        .addInItem(Ore.tibetanBlue.get(OreItem.dustTiny).itemTag(), 1)
                        .addInItem(Ore.pineCypress.get(OreItem.dustTiny).itemTag(), 1)
                        .addOutItem(new ItemStack(patchDiode.get(), 4), 1d)
                        .addMultipleSurplusTime(512L)
                        .addMultipleConsumeMana(12L)));
        patchTriode = ITEMS.register("patch_triode", () -> new ItemBasics.ItemGenerateModel(new Item.Properties().tab(Dusk.TAB))
                .addTag(ItemTag.triodeTag)
                .addShaped(() -> new ShapedOre(ShapedType.encapsulation, ShapedDrive.get(1), ManaLevel.t2)
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
