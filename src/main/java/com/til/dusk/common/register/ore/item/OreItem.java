package com.til.dusk.common.register.ore.item;

import com.til.dusk.Dusk;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.register.ItemUnitRegister;
import com.til.dusk.common.register.RegisterManage;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.ore.item.items.*;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.util.DuskColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
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
    public static IngotOreItemMetal ingot;

    /***
     * 板
     */
    public static OreItemMetal plate;

    public static Plate2OreItemMetal plate_2;

    public static Plate3OreItemMetal plate_3;

    public static Plate4OreItemMetal plate_4;

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
    public static HammerOreItemTool hammer;

    /***
     * 扳手
     */
    public static WrenchOreItemTool wrench;

    /***
     * 剪刀
     */
    public static WireCutterOreItemTool wireCutter;

    /***
     * 锉刀
     */
    public static FileOreItemTool file;

    /***
     * 储罐
     */
    public static TankOreItem tank;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        ORE_ITEM = RegisterManage.create(OreItem.class, new ResourceLocation(Dusk.MOD_ID, "ore_item"), event);
        ingot = new IngotOreItemMetal();
        plate = new OreItemMetal("plate");
        plate_2 = new Plate2OreItemMetal();
        plate_3 = new Plate3OreItemMetal();
        plate_4 = new Plate4OreItemMetal();
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
        sword = new SwordOreItemArms();
        shovel = new ShovelOreItemArms();
        pickaxe = new PickaxeOreItemArms();
        axe = new AxeOreItemArms();
        hoe = new HoeOreItemArms();

        head = new HeadOreItemArmor();
        chest = new ChestOreItemArmor();
        legs = new LegsOreItemArmor();
        feet = new FeetOreItemArmor();

        hammer = new HammerOreItemTool();
        wrench = new WrenchOreItemTool();
        wireCutter = new WireCutterOreItemTool();
        file = new FileOreItemTool();
        tank = new TankOreItem();
    }


    public OreItem(ResourceLocation name) {
        super(name, ORE_ITEM);
    }

    public OreItem(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    @Override
    public void dyeBlack(Ore ore, ColorProxy.ItemColorPack itemColorPack) {
        DuskColor color = ore.getConfig(Ore.COLOR);
        itemColorPack.addColor(0, itemStack -> color);
    }

    @Override
    public void defaultConfig() {

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
}
