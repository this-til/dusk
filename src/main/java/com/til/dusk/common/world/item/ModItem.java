package com.til.dusk.common.world.item;


import com.til.dusk.Dusk;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.data.ModData;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.mana_level.ManaLevelItem;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreItem;
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
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BoneMealItem;
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
public class ModItem {

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

    public static TagKey<Item> resistanceTag;
    public static TagKey<Item> capacitanceTag;
    public static TagKey<Item> inductanceTag;
    public static TagKey<Item> diodeTag;
    public static TagKey<Item> triodeTag;

    /***
     * 废料
     */
    public static RegistryObject<Item> waste;

    static {

        bindStaff = ITEMS.register("bind_staff",
                () -> (BindStaffItem) new BindStaffItem(new Item.Properties().stacksTo(1).tab(Dusk.TAB))
                        .addRecipe(() -> ShapedRecipeBuilder.shaped(bindStaff.get())
                                .define('A', diamondMakeOperation.get().tag())
                                .define('B', Tags.Items.GEMS)
                                .pattern("  A")
                                .pattern(" B ")
                                .pattern("B  ")
                                .unlockedBy("has_ores", ModData.ModRecipeProvider.has(Tags.Items.ORES))));
        copyStaff = ITEMS.register("copy_staff",
                () -> (CopyStaffItem) new CopyStaffItem(new Item.Properties().stacksTo(1).tab(Dusk.TAB))
                        .addRecipe(() -> ShapedRecipeBuilder.shaped(copyStaff.get())
                                .define('A', diamondMakeInstructions.get().tag())
                                .define('B', Tags.Items.GEMS)
                                .pattern("  A")
                                .pattern(" B ")
                                .pattern("B  ")
                                .unlockedBy("has_ores", ModData.ModRecipeProvider.has(Tags.Items.ORES))));
        showStaff = ITEMS.register("show_staff",
                () -> (ShowStaffItem) new ShowStaffItem(new Item.Properties().stacksTo(1).tab(Dusk.TAB))
                        .addRecipe(() -> ShapedRecipeBuilder.shaped(showStaff.get())
                                .define('A', diamondMakeGather.get().tag())
                                .define('B', Tags.Items.GEMS)
                                .pattern("  A")
                                .pattern(" B ")
                                .pattern("B  ")
                                .unlockedBy("has_ores", ModData.ModRecipeProvider.has(Tags.Items.ORES))));
        clearStaff = ITEMS.register("clear_staff",
                () -> (ClearStaffItem) new ClearStaffItem(new Item.Properties().stacksTo(1).tab(Dusk.TAB))
                        .addRecipe(() -> ShapedRecipeBuilder.shaped(clearStaff.get())
                                .define('A', diamondMakeDestruction.get().tag())
                                .define('B', Tags.Items.GEMS)
                                .pattern("  A")
                                .pattern(" B ")
                                .pattern("B  ")
                                .unlockedBy("has_ores", ModData.ModRecipeProvider.has(Tags.Items.ORES))));


        diamondMakeOperationBasics = ITEMS.register("diamond_make_operation_basics",
                () -> (DiamondMakeItem) new DiamondMakeItem(new Item.Properties().tab(Dusk.TAB), ManaLevelItem.operationBasics.strokeColor, ManaLevelItem.operationBasics.coreColor)
                        .addTag(ManaLevel.t1.itemMap.get(ManaLevelItem.operationBasics).itemTag())
                        .addRecipe(() -> ShapedRecipeBuilder.shaped(diamondMakeOperationBasics.get())
                                .define('A', Tags.Items.GEMS_DIAMOND)
                                .define('B', ItemTag.REPEATER.d1())
                                .define('C', ItemTag.COMPARATOR.d1())
                                .define('D', Tags.Items.DUSTS_REDSTONE)
                                .pattern("DBD")
                                .pattern("CAC")
                                .pattern("DBD")
                                .unlockedBy("has_diamond", ModData.ModRecipeProvider.has(Tags.Items.GEMS_DIAMOND))));
        diamondMakeOperation = ITEMS.register("diamond_make_operation",
                () -> (DiamondMakeItem) new DiamondMakeItem(new Item.Properties().tab(Dusk.TAB), ManaLevelItem.operation.strokeColor, ManaLevelItem.operation.coreColor)
                        .addTag(ManaLevel.t1.itemMap.get(ManaLevelItem.operation).itemTag())
                        .addRecipe(() -> ShapedRecipeBuilder.shaped(diamondMakeOperation.get())
                                .define('A', diamondMakeOperation.get().tag())
                                .define('B', Tags.Items.DUSTS_REDSTONE)
                                .define('C', Tags.Items.DUSTS_GLOWSTONE)
                                .pattern("BCB")
                                .pattern("CAC")
                                .pattern("BCB")
                                .unlockedBy("has_diamond_make_operation_basics", ModData.ModRecipeProvider.has(diamondMakeOperation.get().tag()))));
        diamondMakeForming = ITEMS.register("diamond_make_forming",
                () -> (DiamondMakeItem) new DiamondMakeItem(new Item.Properties().tab(Dusk.TAB), ManaLevelItem.forming.strokeColor, ManaLevelItem.forming.coreColor)
                        .addTag(ManaLevel.t1.itemMap.get(ManaLevelItem.forming).itemTag())
                        .addRecipe(() -> ShapedRecipeBuilder.shaped(diamondMakeForming.get())
                                .define('A', diamondMakeOperation.get().tag())
                                .define('B', Tags.Items.GEMS_AMETHYST)
                                .pattern("BAB")
                                .unlockedBy("has_diamond_make_operation", ModData.ModRecipeProvider.has(diamondMakeOperation.get().tag()))));
        diamondMakeDestruction = ITEMS.register("diamond_make_destruction",
                () -> (DiamondMakeItem) new DiamondMakeItem(new Item.Properties().tab(Dusk.TAB), ManaLevelItem.destruction.strokeColor, ManaLevelItem.destruction.coreColor)
                        .addTag(ManaLevel.t1.itemMap.get(ManaLevelItem.destruction).itemTag())
                        .addRecipe(() -> ShapedRecipeBuilder.shaped(diamondMakeDestruction.get())
                                .define('A', diamondMakeOperation.get().tag())
                                .define('B', Tags.Items.GEMS_QUARTZ)
                                .pattern("BAB")
                                .unlockedBy("has_diamond_make_operation", ModData.ModRecipeProvider.has(diamondMakeOperation.get().tag()))));
        diamondMakeGather = ITEMS.register("diamond_make_gather",
                () -> (DiamondMakeItem) new DiamondMakeItem(new Item.Properties().tab(Dusk.TAB), ManaLevelItem.gather.strokeColor, ManaLevelItem.gather.coreColor)
                        .addTag(ManaLevel.t1.itemMap.get(ManaLevelItem.gather).itemTag())
                        .addRecipe(() -> ShapedRecipeBuilder.shaped(diamondMakeGather.get())
                                .define('A', diamondMakeOperation.get().tag())
                                .define('B', Tags.Items.GEMS_LAPIS)
                                .pattern("BAB")
                                .unlockedBy("has_diamond_make_operation", ModData.ModRecipeProvider.has(diamondMakeOperation.get().tag()))));
        diamondMakeSpread = ITEMS.register("diamond_make_spread",
                () -> (DiamondMakeItem) new DiamondMakeItem(new Item.Properties().tab(Dusk.TAB), ManaLevelItem.spread.strokeColor, ManaLevelItem.spread.coreColor)
                        .addTag(ManaLevel.t1.itemMap.get(ManaLevelItem.spread).itemTag())
                        .addRecipe(() -> ShapedRecipeBuilder.shaped(diamondMakeSpread.get())
                                .define('A', diamondMakeOperation.get().tag())
                                .define('B', Tags.Items.GEMS_PRISMARINE)
                                .pattern("BAB")
                                .unlockedBy("has_diamond_make_operation", ModData.ModRecipeProvider.has(diamondMakeOperation.get().tag()))));
        diamondMakePower = ITEMS.register("diamond_make_power",
                () -> (DiamondMakeItem) new DiamondMakeItem(new Item.Properties().tab(Dusk.TAB), ManaLevelItem.power.strokeColor, ManaLevelItem.power.coreColor)
                        .addTag(ManaLevel.t1.itemMap.get(ManaLevelItem.power).itemTag())
                        .addRecipe(() -> ShapedRecipeBuilder.shaped(diamondMakePower.get())
                                .define('A', diamondMakeOperation.get().tag())
                                .define('B', ItemTag.PISTON.d1())
                                .pattern("BAB")
                                .unlockedBy("has_diamond_make_operation", ModData.ModRecipeProvider.has(ManaLevel.t1.itemMap.get(ManaLevelItem.operation).itemTag()))));
        diamondMakeInstructions = ITEMS.register("diamond_make_instructions",
                () -> (DiamondMakeItem) new DiamondMakeItem(new Item.Properties().tab(Dusk.TAB), ManaLevelItem.instructions.strokeColor, ManaLevelItem.instructions.coreColor)
                        .addTag(ManaLevel.t1.itemMap.get(ManaLevelItem.instructions).itemTag())
                        .addRecipe(() -> ShapedRecipeBuilder.shaped(diamondMakeInstructions.get())
                                .define('A', diamondMakeOperation.get().tag())
                                .define('B', Tags.Items.BOOKSHELVES)
                                .pattern("BAB")
                                .unlockedBy("has_diamond_make_operation", ModData.ModRecipeProvider.has(diamondMakeOperation.get().tag()))));
        resistance = ITEMS.register("resistance", () -> new ItemBasics(new Item.Properties().tab(Dusk.TAB))
                .addTag(resistanceTag)
                .addShaped(() -> new ShapedOre(ShapedType.assemble, ShapedDrive.get(12), ManaLevel.t1)
                        .addInItem(Ore.violet.itemMap.get(OreItem.foil).itemTag(), 1)
                        .addInItem(Ore.cinnabar.itemMap.get(OreItem.dust).itemTag(), 1)
                        .addOutItem(new ItemStack(resistance.get(), 4), 1d)
                        .addMultipleSurplusTime(1024L)
                        .addMultipleConsumeMana(12L)));
        capacitance = ITEMS.register("capacitance", () -> new ItemBasics(new Item.Properties().tab(Dusk.TAB))
                .addTag(capacitanceTag)
                .addShaped(() -> new ShapedOre(ShapedType.assemble, ShapedDrive.get(12), ManaLevel.t1)
                        .addInItem(Ore.thistle.itemMap.get(OreItem.foil).itemTag(), 1)
                        .addInItem(Ore.pink.itemMap.get(OreItem.stick).itemTag(), 1)
                        .addOutItem(new ItemStack(capacitance.get(), 4), 1d)
                        .addMultipleSurplusTime(1024L)
                        .addMultipleConsumeMana(12L)));
        inductance = ITEMS.register("inductance", () -> new ItemBasics(new Item.Properties().tab(Dusk.TAB))
                .addTag(inductanceTag)
                .addShaped(() -> new ShapedOre(ShapedType.assemble, ShapedDrive.get(12), ManaLevel.t1)
                        .addInItem(Ore.peru.itemMap.get(OreItem.stick).itemTag(), 1)
                        .addInItem(Ore.goldenrod.itemMap.get(OreItem.string).itemTag(), 8)
                        .addOutItem(new ItemStack(capacitance.get(), 1), 1d)
                        .addMultipleSurplusTime(1024L)
                        .addMultipleConsumeMana(22L)));
        diode = ITEMS.register("diode", () -> new ItemBasics(new Item.Properties().tab(Dusk.TAB))
                .addTag(diodeTag).addShaped(() -> new ShapedOre(ShapedType.assemble, ShapedDrive.get(12), ManaLevel.t1)
                        .addInItem(Ore.tibetanBlue.itemMap.get(OreItem.dust).itemTag(), 2)
                        .addInItem(Ore.spiritSilver.itemMap.get(OreItem.string).itemTag(), 2)
                        .addOutItem(new ItemStack(diode.get(), 4), 1d)
                        .addMultipleSurplusTime(1024L)
                        .addMultipleConsumeMana(22L)));
        triode = ITEMS.register("triode", () -> new ItemBasics(new Item.Properties().tab(Dusk.TAB))
                .addTag(triodeTag).addShaped(() -> new ShapedOre(ShapedType.assemble, ShapedDrive.get(12), ManaLevel.t1)
                        .addInItem(Ore.tibetanBlue.itemMap.get(OreItem.dust).itemTag(), 2)
                        .addInItem(Ore.greenTeal.itemMap.get(OreItem.foil).itemTag(), 1)
                        .addInItem(Ore.spiritSilver.itemMap.get(OreItem.string).itemTag(), 3)
                        .addOutItem(new ItemStack(triode.get(), 4), 1d)
                        .addMultipleSurplusTime(1024L)
                        .addMultipleConsumeMana(22L)));
        patchInductance = ITEMS.register("patch_inductance", () -> new ItemBasics(new Item.Properties().tab(Dusk.TAB))
                .addTag(resistanceTag));
        patchResistance = ITEMS.register("patch_resistance", () -> new ItemBasics(new Item.Properties().tab(Dusk.TAB))
                .addTag(capacitanceTag));
        patchCapacitance = ITEMS.register("patch_capacitance", () -> new ItemBasics(new Item.Properties().tab(Dusk.TAB))
                .addTag(inductanceTag));
        patchDiode = ITEMS.register("patch_diode", () -> new ItemBasics(new Item.Properties().tab(Dusk.TAB))
                .addTag(diodeTag));
        patchTriode = ITEMS.register("patch_triode", () -> new ItemBasics(new Item.Properties().tab(Dusk.TAB))
                .addTag(triodeTag));

        waste = ITEMS.register("waste", () -> new BoneMealItem(new Item.Properties().tab(Dusk.TAB)));
    }

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        ITEMS.register(Dusk.instance.modEventBus);
        resistanceTag = Dusk.instance.ITEM_TAG.createTagKey(new ResourceLocation(Dusk.MOD_ID, "resistance"));
        capacitanceTag = Dusk.instance.ITEM_TAG.createTagKey(new ResourceLocation(Dusk.MOD_ID, "capacitance"));
        inductanceTag = Dusk.instance.ITEM_TAG.createTagKey(new ResourceLocation(Dusk.MOD_ID, "inductance"));
        diodeTag = Dusk.instance.ITEM_TAG.createTagKey(new ResourceLocation(Dusk.MOD_ID, "diode"));
        triodeTag = Dusk.instance.ITEM_TAG.createTagKey(new ResourceLocation(Dusk.MOD_ID, "triode"));
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
