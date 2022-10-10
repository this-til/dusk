package com.til.dusk.common.register.ore.item;

import com.til.dusk.Dusk;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.register.ItemUnitRegister;
import com.til.dusk.common.register.RegisterManage;
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
    public static PlateOreItemMetal plate;

    public static Plate2OreItemMetal plate2;

    public static Plate3OreItemMetal plate3;

    public static Plate4OreItemMetal plate4;

    /***
     * 外壳
     */
    public static CasingOreItemMetal casing;

    /***
     * 箔
     */
    public static FoilOreItemMetal foil;

    /***
     * 杆
     */
    public static StickOreItemMetal stick;

    /***
     * 长杆
     */
    public static StickLongOreItemMetal stickLong;

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
        plate = new PlateOreItemMetal();
        plate2 = new Plate2OreItemMetal();
        plate3 = new Plate3OreItemMetal();
        plate4 = new Plate4OreItemMetal();
        foil = new FoilOreItemMetal();
        casing = new CasingOreItemMetal();
        stick = new StickOreItemMetal();
        stickLong = new StickLongOreItemMetal();
        string = new StringOreItemMetal();
        gear = new GearOreItemMetal();
        rotor = new RotorOreItemMetal();
        circularSawBlade = new CircularSawBladeOreItemMetal();
        nuggets = new NuggetsOreItemMetal();
        damagedCrystal = new DamagedCrystalOreItemCrysta();
        crystal = new CrystalOreItemCrysta();
        delicateCrystal = new CrystalDelicateOreItemCrysta();
        perfectCrystal = new CrystalPerfectOreItemCrysta();
        crystalSeed = new CrystalSeedOreItemCrysta();
        crushed = new CrushedOreItemMineralBlock();
        crushedPurified = new CrushedPurifiedOreItemMineralBlock();
        dust = new DustOreItemDust();
        dustTiny = new DustTinyOreItemDust();
        swordBasics = new SwordOreItemArmsBasics();
        shovelBasics = new ShovelOreItemArmsBasics();
        pickaxeBasics = new PickaxeOreItemArmsBasics();
        axeBasics = new AxeOreItemArmsBasics();
        hoeBasics = new HoeOreItemArmsBasics();
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
        itemColorPack.addColor(0, itemStack -> ore.color);
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
