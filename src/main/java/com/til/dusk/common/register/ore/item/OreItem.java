package com.til.dusk.common.register.ore.item;

import com.til.dusk.Dusk;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.data.ModRecipeProvider;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.ItemUnitRegister;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.world.item.*;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.GenericMap;
import com.til.dusk.util.prefab.ColorPrefab;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OreItem extends ItemUnitRegister<OreItem, Ore> {

    public static Supplier<IForgeRegistry<OreItem>> ORE_ITEM;


    /***
     * 矿物锭
     */
    public static OreItemMetal ingot;

    /***
     * 板
     */
    public static OreItemMetal plate;

    public static OreItemMetal plate_2;

    public static OreItemMetal plate_3;

    public static OreItemMetal plate_4;

    /***
     * 外壳
     */
    public static OreItemMetal casing;

    /***
     * 箔
     */
    public static OreItemMetal foil;

    /***
     * 杆
     */
    public static OreItemMetal stick;

    /***
     * 长杆
     */
    public static OreItemMetal stick_long;

    /***
     * 线
     */
    public static OreItemMetal string;

    /***
     * 齿轮
     */
    public static OreItemMetal gear;

    /***
     * 转子
     */
    public static OreItemMetal rotor;

    /***
     * 圆锯
     */
    public static OreItemMetal circularSawBlade;

    /***
     * 粒
     */
    public static OreItemMetal nuggets;

    /***
     * 破损的晶体
     */
    public static OreItemCrysta damagedCrystal;

    /***
     * 晶体
     */
    public static OreItemCrysta crystal;

    /***
     * 精致的晶体
     */
    public static OreItemCrysta delicateCrystal;

    /***
     * 完美的晶体
     */
    public static OreItemCrysta perfectCrystal;

    /***
     * 晶体种子
     */
    public static OreItemCrysta crystalSeed;

    /***
     * 粉碎矿物
     */
    public static OreItemMineralBlock crushed;

    /***
     * 纯净矿物
     * 由粉碎矿物洗涤而得到
     */
    public static OreItemMineralBlock crushedPurified;

    /***
     * 矿粉
     */
    public static OreItemDust dust;

    /***
     * 小撮粉
     */
    public static OreItemDust dustTiny;

    public static OreItemArmsBasics swordBasics;
    public static OreItemArmsBasics shovelBasics;
    public static OreItemArmsBasics pickaxeBasics;
    public static OreItemArmsBasics axeBasics;
    public static OreItemArmsBasics hoeBasics;

    public static OreItemArms sword;
    public static OreItemArms shovel;
    public static OreItemArms pickaxe;
    public static OreItemArms axe;
    public static OreItemArms hoe;

    public static OreItemArmor head;
    public static OreItemArmor chest;
    public static OreItemArmor legs;
    public static OreItemArmor feet;

    /***
     * 锤子
     */
    public static OreItemTool hammer;

    /***
     * 扳手
     */
    public static OreItemTool wrench;

    /***
     * 剪刀
     */
    public static OreItemTool wireCutter;

    /***
     * 锉刀
     */
    public static OreItemTool file;

    /***
     * 储罐
     */
    public static OreItemTank tank;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        ORE_ITEM = event.create(new RegistryBuilder<OreItem>().setName(new ResourceLocation(Dusk.MOD_ID, "ore_item")));
        ingot = (OreItemMetal) new OreItemMetal("ingot")
                .addRecipes(list -> {
                    for (Ore ore : Ore.screen(Ore.IS_METAL, Ore.MINERAL_BLOCK_DATA)) {
                        if (!ore.manaLevel.hasSet(ManaLevel.CAN_UET_TOOL_MAKE)) {
                            continue;
                        }
                        list.add(SimpleCookingRecipeBuilder.smelting(Ingredient.of(ore.getMineralBlockTag().itemTagKey()), ore.get(ingot).item(), 0.6F, 200)
                                .unlockedBy("has_ore", ModRecipeProvider.has(ore.getMineralBlockTag().itemTagKey())));


                    }
                });
        plate = new OreItemMetal("plate");
        plate_2 = new OreItemMetal("plate_2") {
            @Override
            public DuskItem.ICustomModel getItemMoldMapping(Ore ore) {
                return () -> plate.name;
            }

            @Override
            public void dyeBlack(Ore ore, ColorProxy.ItemColorPack itemColorPack) {
                DuskColor color = ColorPrefab.GRAYSCALE_REDUCTION_1.blend(ore.color);
                itemColorPack.addColor(0, itemStack -> color);
            }
        };
        plate_3 = new OreItemMetal("plate_3") {
            @Override
            public DuskItem.ICustomModel getItemMoldMapping(Ore ore) {
                return () -> plate.name;
            }

            @Override
            public void dyeBlack(Ore ore, ColorProxy.ItemColorPack itemColorPack) {
                DuskColor color = ColorPrefab.GRAYSCALE_REDUCTION_2.blend(ore.color);
                itemColorPack.addColor(0, itemStack -> color);
            }
        };
        plate_4 = new OreItemMetal("plate_4") {
            @Override
            public DuskItem.ICustomModel getItemMoldMapping(Ore ore) {
                return () -> plate.name;
            }

            @Override
            public void dyeBlack(Ore ore, ColorProxy.ItemColorPack itemColorPack) {
                DuskColor color = ColorPrefab.GRAYSCALE_REDUCTION_3.blend(ore.color);
                itemColorPack.addColor(0, itemStack -> color);
            }
        };
        foil = new OreItemMetal("foil");
        casing = new OreItemMetal("casing");
        stick = new OreItemMetal("stick");
        stick_long = new OreItemMetal("stick_long");
        string = new OreItemMetal("string");
        gear = new OreItemMetal("gear");
        rotor = new OreItemMetal("rotor");
        circularSawBlade = new OreItemMetal("circular_saw_blade");
        nuggets = new OreItemMetal("nuggets");
        damagedCrystal = new OreItemCrysta("crystal_damaged");
        crystal = new OreItemCrysta("crystal");
        delicateCrystal = new OreItemCrysta("crystal_delicate");
        perfectCrystal = new OreItemCrysta("crystal_perfect");
        crystalSeed = new OreItemCrysta("crystal_seed");
        crushed = new OreItemMineralBlock("crushed");
        crushedPurified = new OreItemMineralBlock("crushed_purified");
        dust = new OreItemDust("dust");
        dustTiny = new OreItemDust("dust_tiny");
        swordBasics = new OreItemArmsBasics("sword_basics");
        shovelBasics = new OreItemArmsBasics("shovel_basics");
        pickaxeBasics = new OreItemArmsBasics("pickaxe_basics");
        axeBasics = new OreItemArmsBasics("axe_basics");
        hoeBasics = new OreItemArmsBasics("hoe_basics");
        sword = (OreItemArms) new OreItemArms("sword") {
            @Override
            public Item createArmsItem(Ore ore, ArmsData armsData) {
                CapabilitySwordItem item = new CapabilitySwordItem(ore, this, armsData);
                ItemTag.addTag(Tags.Items.TOOLS, item);
                ItemTag.addTag(Tags.Items.TOOLS_SWORDS, item);
                return item;
            }
        }.setConfig(IS_SWORD, null);
        shovel = (OreItemArms) new OreItemArms("shovel") {
            @Override
            public Item createArmsItem(Ore ore, ArmsData armsData) {
                CapabilityShovelItem item = new CapabilityShovelItem(ore, this, armsData);
                ItemTag.addTag(Tags.Items.TOOLS, item);
                ItemTag.addTag(Tags.Items.TOOLS_SHOVELS, item);
                return item;
            }
        }.setConfig(IS_SHOVEL, null);
        pickaxe = (OreItemArms) new OreItemArms("pickaxe") {
            @Override
            public Item createArmsItem(Ore ore, ArmsData armsData) {
                CapabilityPickaxeItem item = new CapabilityPickaxeItem(ore, this, armsData);
                ItemTag.addTag(Tags.Items.TOOLS, item);
                ItemTag.addTag(Tags.Items.TOOLS_PICKAXES, item);
                return item;
            }
        }.setConfig(IS_PICKAXE, null);
        axe = (OreItemArms) new OreItemArms("axe") {
            @Override
            public Item createArmsItem(Ore ore, ArmsData armsData) {
                CapabilityAxeItem item = new CapabilityAxeItem(ore, this, armsData);
                ItemTag.addTag(Tags.Items.TOOLS, item);
                ItemTag.addTag(Tags.Items.TOOLS_AXES, item);
                return item;
            }
        }.setConfig(IS_AXE, null);
        hoe = (OreItemArms) new OreItemArms("hoe") {
            @Override
            public Item createArmsItem(Ore ore, ArmsData armsData) {
                CapabilityHoeItem item = new CapabilityHoeItem(ore, this, armsData);
                ItemTag.addTag(Tags.Items.TOOLS, item);
                ItemTag.addTag(Tags.Items.TOOLS_HOES, item);
                return item;
            }
        }.setConfig(IS_HOE, null);

        head = new OreItemArmor("head", EquipmentSlot.HEAD);
        chest = new OreItemArmor("chest", EquipmentSlot.CHEST);
        legs = new OreItemArmor("legs", EquipmentSlot.LEGS);
        feet = new OreItemArmor("feet", EquipmentSlot.FEET);

        hammer = (OreItemTool) new OreItemTool("hammer") {
            @Override
            public void dyeBlack(Ore ore, ColorProxy.ItemColorPack itemColorPack) {
                itemColorPack.addColor(1, itemStack -> ore.color);
            }
        }
                .setConfig(IS_HAMMER)
                .addRecipes(list -> {
                    for (Ore ore : Ore.screen(Ore.IS_METAL, Ore.TOOL_DATA)) {
                        list.add(ShapedRecipeBuilder.shaped(ore.get(hammer).item(), 1)
                                .define('A', ore.get(ingot).itemTag())
                                .define('B', Tags.Items.RODS_WOODEN)
                                .pattern("AAA")
                                .pattern("AAA")
                                .pattern(" B ")
                                .unlockedBy("has_ore", ModRecipeProvider.has(ore.get(ingot).itemTag())));
                    }
                })
                .addRecipes(list -> {
                    for (Ore ore : Ore.screen(Ore.IS_METAL)) {
                        if (!ore.manaLevel.hasSet(ManaLevel.CAN_UET_TOOL_MAKE)) {
                            continue;
                        }
                        list.add(ShapedRecipeBuilder.shaped(ore.get(plate).item(), 1)
                                .define('A', hammer.getTagPack().itemTagKey())
                                .define('B', ore.get(ingot).itemTag())
                                .pattern("AB")
                                .unlockedBy("has_hammer", ModRecipeProvider.has(hammer.getTagPack().itemTagKey())));
                        list.add(ShapedRecipeBuilder.shaped(ore.get(casing).item(), 1)
                                .define('A', hammer.getTagPack().itemTagKey())
                                .define('B', ore.get(plate).itemTag())
                                .pattern("A")
                                .pattern("B")
                                .unlockedBy("has_hammer", ModRecipeProvider.has(hammer.getTagPack().itemTagKey())));
                        list.add(ShapedRecipeBuilder.shaped(ore.get(foil).item(), 2)
                                .define('A', hammer.getTagPack().itemTagKey())
                                .define('B', ore.get(plate).itemTag())
                                .pattern("A ")
                                .pattern(" B")
                                .unlockedBy("has_hammer", ModRecipeProvider.has(hammer.getTagPack().itemTagKey())));
                        list.add(ShapedRecipeBuilder.shaped(ore.get(stick_long).item(), 1)
                                .define('A', hammer.getTagPack().itemTagKey())
                                .define('B', ore.get(stick).itemTag())
                                .pattern("A")
                                .pattern("B")
                                .pattern("B")
                                .unlockedBy("has_hammer", ModRecipeProvider.has(hammer.getTagPack().itemTagKey())));
                    }
                });
        wrench = (OreItemTool) new OreItemTool("wrench")
                .setConfig(IS_WRENCH).addRecipes(list -> {
                    for (Ore ore : Ore.screen(Ore.IS_METAL, Ore.TOOL_DATA)) {
                        list.add(ShapedRecipeBuilder.shaped(ore.get(wrench).item(), 1)
                                .define('A', ore.get(ingot).itemTag())
                                .pattern("A A")
                                .pattern(" A ")
                                .pattern(" A ")
                                .unlockedBy("has_ore", ModRecipeProvider.has(ore.get(ingot).itemTag())));
                    }
                })
                .addRecipes(list -> {
                    for (Ore ore : Ore.screen(Ore.IS_METAL)) {
                        if (!ore.manaLevel.hasSet(ManaLevel.CAN_UET_TOOL_MAKE)) {
                            continue;
                        }
                        list.add(ShapedRecipeBuilder.shaped(ore.get(gear).item(), 1)
                                .define('A', wrench.getTagPack().itemTagKey())
                                .define('B', ore.get(plate).itemTag())
                                .define('C', ore.get(ingot).item())
                                .pattern("BCB")
                                .pattern("CAC")
                                .pattern("BCB")
                                .unlockedBy("has_wrench", ModRecipeProvider.has(wrench.getTagPack().itemTagKey())));
                        list.add(ShapedRecipeBuilder.shaped(ore.get(rotor).item(), 1)
                                .define('A', wrench.getTagPack().itemTagKey())
                                .define('B', ore.get(plate).itemTag())
                                .define('C', ore.get(stick_long).item())
                                .pattern("BCB")
                                .pattern("CAC")
                                .pattern("BCB")
                                .unlockedBy("has_wrench", ModRecipeProvider.has(wrench.getTagPack().itemTagKey())));
                    }
                });
        wireCutter = (OreItemTool) new OreItemTool("wire_cutter")
                .setConfig(IS_WRENCH)
                .addRecipes(list -> {
                    for (Ore ore : Ore.screen(Ore.IS_METAL, Ore.TOOL_DATA)) {
                        list.add(ShapedRecipeBuilder.shaped(ore.get(wireCutter).item(), 1)
                                .define('A', ore.get(ingot).itemTag())
                                .define('B', ore.get(plate).itemTag())
                                .pattern("B B")
                                .pattern(" B ")
                                .pattern("A A")
                                .unlockedBy("has_ore", ModRecipeProvider.has(ore.get(ingot).itemTag())));
                    }
                })
                .setConfig(IS_WIRE_CUTTER).addRecipes(list -> {
                    for (Ore ore : Ore.screen(Ore.IS_METAL)) {
                        if (!ore.manaLevel.hasSet(ManaLevel.CAN_UET_TOOL_MAKE)) {
                            continue;
                        }
                        list.add(ShapedRecipeBuilder.shaped(ore.get(string).item(), 1)
                                .define('A', wireCutter.getTagPack().itemTagKey())
                                .define('B', ore.get(plate).itemTag())
                                .pattern("A")
                                .pattern("B")
                                .unlockedBy("has_wire_cutter", ModRecipeProvider.has(wireCutter.getTagPack().itemTagKey())));
                    }
                });
        file = (OreItemTool) new OreItemTool("file")
                .setConfig(IS_FILE)
                .setConfig(IS_WRENCH).addRecipes(list -> {
                    for (Ore ore : Ore.screen(Ore.IS_METAL, Ore.TOOL_DATA)) {
                        list.add(ShapedRecipeBuilder.shaped(ore.get(file).item(), 1)
                                .define('A', ore.get(plate).itemTag())
                                .define('B', ore.get(casing).itemTag())
                                .pattern("A")
                                .pattern("A")
                                .pattern("B")
                                .unlockedBy("has_ore", ModRecipeProvider.has(ore.get(ingot).itemTag())));
                    }
                })
                .addRecipes(list -> {
                    for (Ore ore : Ore.screen(Ore.IS_METAL)) {
                        if (!ore.manaLevel.hasSet(ManaLevel.CAN_UET_TOOL_MAKE)) {
                            continue;
                        }
                        list.add(ShapedRecipeBuilder.shaped(ore.get(stick).item(), 1)
                                .define('A', file.getTagPack().itemTagKey())
                                .define('B', ore.get(ingot).itemTag())
                                .pattern("A")
                                .pattern("B")
                                .unlockedBy("has_file", ModRecipeProvider.has(file.getTagPack().itemTagKey())));
                    }
                });
        tank = new OreItemTank("tank");
    }


    public OreItem(ResourceLocation name) {
        super(name, ORE_ITEM);
    }

    public OreItem(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    @Override
    public void dyeBlack(Ore ore, ColorProxy.ItemColorPack itemColorPack) {
        itemColorPack.addColor(0, itemStack -> ore.color);
    }

    /***
     * 是装备
     */
    public static final GenericMap.IKey<Void> IS_ARMOR = new GenericMap.IKey.Key<>();

    public static final GenericMap.IKey<Void> IS_HEAD = new GenericMap.IKey.Key<>();
    public static final GenericMap.IKey<Void> IS_CHEST = new GenericMap.IKey.Key<>();
    public static final GenericMap.IKey<Void> IS_LEGS = new GenericMap.IKey.Key<>();
    public static final GenericMap.IKey<Void> IS_FEET = new GenericMap.IKey.Key<>();

    public static GenericMap.IKey<Void> asArmorKey(EquipmentSlot equipmentSlot) {
        return switch (equipmentSlot) {
            default -> IS_ARMOR;
            case HEAD -> IS_HEAD;
            case CHEST -> IS_CHEST;
            case LEGS -> IS_LEGS;
            case FEET -> IS_FEET;
        };
    }

    public static TagKey<Item> asTag(EquipmentSlot equipmentSlot) {
        return switch (equipmentSlot) {
            default -> Tags.Items.ARMORS;
            case HEAD -> Tags.Items.ARMORS_HELMETS;
            case CHEST -> Tags.Items.ARMORS_CHESTPLATES;
            case LEGS -> Tags.Items.ARMORS_LEGGINGS;
            case FEET -> Tags.Items.ARMORS_BOOTS;
        };
    }


    /***
     * 是武器
     */
    public static final GenericMap.IKey<Void> IS_ARMS = new GenericMap.IKey.Key<>();

    public static final GenericMap.IKey<Void> IS_SWORD = new GenericMap.IKey.Key<>();
    public static final GenericMap.IKey<Void> IS_SHOVEL = new GenericMap.IKey.Key<>();
    public static final GenericMap.IKey<Void> IS_PICKAXE = new GenericMap.IKey.Key<>();
    public static final GenericMap.IKey<Void> IS_AXE = new GenericMap.IKey.Key<>();
    public static final GenericMap.IKey<Void> IS_HOE = new GenericMap.IKey.Key<>();

    /***
     * 是工具
     */
    public static final GenericMap.IKey<Void> IS_TOOL = new GenericMap.IKey.Key<>();
    public static final GenericMap.IKey<Void> IS_HAMMER = new GenericMap.IKey.Key<>();
    public static final GenericMap.IKey<Void> IS_WRENCH = new GenericMap.IKey.Key<>();
    public static final GenericMap.IKey<Void> IS_WIRE_CUTTER = new GenericMap.IKey.Key<>();
    public static final GenericMap.IKey<Void> IS_FILE = new GenericMap.IKey.Key<>();


}
