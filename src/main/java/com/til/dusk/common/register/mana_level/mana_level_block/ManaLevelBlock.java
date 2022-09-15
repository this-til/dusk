package com.til.dusk.common.register.mana_level.mana_level_block;

import com.til.dusk.Dusk;
import com.til.dusk.common.capability.black.Back;
import com.til.dusk.common.capability.black.IBack;
import com.til.dusk.common.capability.fluid_handler.VoidTankFluidHandler;
import com.til.dusk.common.capability.item_handler.VoidCaseItemHandler;
import com.til.dusk.common.capability.mana_handle.ManaHandle;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.register.CapabilityRegister;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.capability.DuskCapabilityProvider;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.prefab.ColorPrefab;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.List;
import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public abstract class ManaLevelBlock extends RegisterBasics.BlockUnitRegister<ManaLevelBlock, ManaLevel> {


    public static Supplier<IForgeRegistry<ManaLevelBlock>> LEVEL_BLOCK;

    //基础
    /***
     * 中继器
     * 在提取相应方块能力
     */
    public static RepeaterMechanic repeater;

    /***
     * 基础框架
     */
    public static DefaultCapacityMechanic frameBasic;

    //产物
    /***
     * 日光晶体
     * 太阳能神教
     */
    public static SimilarSolarEnergyMechanic sunlight;

    /***
     * 月光晶体
     */
    public static SimilarSolarEnergyMechanic moonlight;

    /***
     * 雨灵晶体
     */
    public static SimilarSolarEnergyMechanic rain;

    /***
     * 灵气提提取器
     */
    public static HandleMechanic extractMana;

    /***
     * 解咒转灵晶体
     */
    public static ExtractManaMechanic dischantmentMana;

    /***
     *  末影转灵晶体
     */
    public static ExtractManaMechanic enderMana;

    /***
     * 药水转灵晶体
     */
    public static ExtractManaMechanic potionMana;

    /***
     * 爆破转灵晶体
     */
    public static ExtractManaMechanic explosiveMana;

    /***
     * 寒霜转灵晶体
     */
    public static ExtractManaMechanic frostyMana;

    /***
     * 粘液转灵晶体
     */
    public static ExtractManaMechanic slimeyMana;

    /***
     * 口臭转灵晶体
     */
    public static ExtractManaMechanic halitosisMana;

    /***
     * 烈焰转灵晶体
     */
    public static ExtractManaMechanic flameMana;

    /***
     * 植物转灵晶体
     */
    public static ExtractManaMechanic botanyMana;

    /***
     * 食物转灵晶体
     */
    public static ExtractManaMechanic foodMana;

    //处理

    /***
     * 研磨
     */
    public static HandleMechanic grind;

    /***
     * 洗涤
     */
    public static HandleMechanic wash;

    /***
     * 离心
     */
    public static HandleMechanic centrifugal;

    /***
     * 打包
     */
    public static HandleMechanic pack;

    /***
     * 解包
     */
    public static HandleMechanic unpack;

    /***
     * 高炉
     */
    public static HandleMechanic blastFurnace;

    /***
     * 结晶
     */
    public static HandleMechanic crystallizing;

    /***
     * 组装机
     */
    public static HandleMechanic assemble;

    /***
     * 蒸馏
     */
    public static HandleMechanic distillation;

    /***
     * 溶解
     */
    public static HandleMechanic dissolution;

    /***
     * 凝固
     */
    public static HandleMechanic freezing;

    /***
     * 高压融合
     */
    public static HandleMechanic highPressureFuse;

    /***
     * 雕刻
     */
    public static HandleMechanic carving;

    /***
     * 筛选
     */
    public static Mechanic screen;

    /***
     * 熔炉
     */
    public static Mechanic furnace;

    /***
     * 造石机
     */
    public static Mechanic makerStone;

    /***
     * 冲压机
     */
    public static Mechanic stampingMachine;

    /***
     * 车床
     */
    public static Mechanic lathe;

    /***
     * 扎线机
     */
    public static Mechanic tieWire;

    /***
     * 切割
     */
    public static Mechanic cutting;

    /***
     * 压杆
     */
    public static Mechanic pressureStick;

    /***
     * 混合
     */
    public static Mechanic blend;

    /***
     * 分解
     */
    public static Mechanic decompose;

    /***
     * 回收
     */
    public static Mechanic recovery;

    /***
     * 成型
     */
    public static Mechanic forming;

    /***
     * 灵气凝结晶体
     */
    public static Mechanic manaCoagulation;

    //功能

    /***
     * 虚空箱
     * 1280 * l
     */
    public static DefaultCapacityMechanic voidCase;

    /***
     * 虚空缸
     * 1280000mb * l
     */
    public static DefaultCapacityMechanic voidTank;

    /***
     * 聚灵
     */
    public static DefaultCapacityMechanic gatherMana;

    /***
     * 灵气传输
     */
    public static IOMechanic.ManaIO manaIO;

    /***
     * 物品传输
     */
    public static IOMechanic.ItemIO itemIO;

    /***
     * 流体传输
     */
    public static IOMechanic.FluidIO fluidIO;

    /***
     * 收集晶体
     */
    public static CollectMechanic collect;

    /***
     * 回旋升压
     */
    public static WhirlBoostMechanic whirlBoost;

    /***
     * 充能晶体
     */
    public static ChargeMechanic charge;

    /***
     * 泵晶体
     */
    public static PumpMechanic pumpMechanic;

    /***
     * 挖掘晶体
     */
    public static MiningMechanic mining;

    /***
     * 屠杀晶体
     */
    public static MassacreMechanic massacre;

    /***
     * FE转换晶体
     */
    public static TransformationFEMechanic transformationFEMechanic;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        LEVEL_BLOCK = event.create(new RegistryBuilder<ManaLevelBlock>().setName(new ResourceLocation(Dusk.MOD_ID, "mana_level_block")));
        repeater = new RepeaterMechanic("repeater");
        frameBasic = new DefaultCapacityMechanic("frame_basic");
        sunlight = new SimilarSolarEnergyMechanic("sunlight", 1, level -> level.isDay() && !level.isRaining(), ColorPrefab.SUNLIGHT_COLOR);
        moonlight = new SimilarSolarEnergyMechanic("moonlight", 1, level -> level.isNight() && !level.isRaining(), ColorPrefab.MOONLIGHT_COLOR);
        rain = new SimilarSolarEnergyMechanic("rain", 4, Level::isRaining, ColorPrefab.RAIN_COLOR);
        extractMana = new HandleMechanic("extract_mana", () -> List.of(ShapedType.extractMana));
        dischantmentMana = new ExtractManaMechanic("dischantment_mana", () -> List.of(ShapedType.dischantmentMana), new DuskColor(135, 60, 168, 255));
        enderMana = new ExtractManaMechanic("ender_mana", () -> List.of(ShapedType.enderMana), new DuskColor(96, 22, 96));
        potionMana = new ExtractManaMechanic("potion_mana", () -> List.of(ShapedType.potionMana), new DuskColor(243, 138, 255));
        explosiveMana = new ExtractManaMechanic("explosive_mana", () -> List.of(ShapedType.explosiveMana), new DuskColor(178, 25, 25));
        frostyMana = new ExtractManaMechanic("frosty_mana", () -> List.of(ShapedType.frostyMana), new DuskColor(29, 237, 255));
        slimeyMana = new ExtractManaMechanic("slimey_mana", () -> List.of(ShapedType.slimeyMana), new DuskColor(43, 255, 33));
        halitosisMana = new ExtractManaMechanic("halitosis_mana", () -> List.of(ShapedType.halitosisMana), new DuskColor(229, 45, 136));
        flameMana = new ExtractManaMechanic("flame_mana", () -> List.of(ShapedType.flameMana), new DuskColor(255, 0, 0));
        botanyMana = new ExtractManaMechanic("botany_mana", () -> List.of(ShapedType.botanyMana), new DuskColor(7, 140, 0));
        foodMana = new ExtractManaMechanic("food_mana", () -> List.of(ShapedType.foodMana), new DuskColor(255, 184, 66));
        grind = new HandleMechanic("grind", () -> List.of(ShapedType.grind));
        wash = new HandleMechanic("wash", () -> List.of(ShapedType.wash));
        centrifugal = new HandleMechanic("centrifugal", () -> List.of(ShapedType.centrifugal));
        pack = new HandleMechanic("pack", () -> List.of(ShapedType.pack));
        unpack = new HandleMechanic("unpack", () -> List.of(ShapedType.unpack));
        blastFurnace = new HandleMechanic("blast_furnace", () -> List.of(ShapedType.blastFurnace));
        crystallizing = new HandleMechanic("crystallizing", () -> List.of(ShapedType.crystallizing));
        assemble = new HandleMechanic("assemble", () -> List.of(ShapedType.assemble));
        distillation = new HandleMechanic("distillation", () -> List.of(ShapedType.distillation));
        dissolution = new HandleMechanic("dissolution", () -> List.of(ShapedType.dissolution));
        freezing = new HandleMechanic("freezing", () -> List.of(ShapedType.freezing));
        highPressureFuse = new HandleMechanic("high_pressure_fuse", () -> List.of(ShapedType.highPressureFuse));
        carving = new HandleMechanic("carving", () -> List.of(ShapedType.carving));
        screen = new HandleMechanic("screen", () -> List.of(ShapedType.screen));
        furnace = new HandleMechanic("furnace", () -> List.of(ShapedType.furnace));
        makerStone = new HandleMechanic("maker_stone", () -> List.of(ShapedType.makerStone));
        stampingMachine = new HandleMechanic("stamping_machine", () -> List.of(ShapedType.stampingMachine));
        lathe = new HandleMechanic("lathe", () -> List.of(ShapedType.lathe));
        tieWire = new HandleMechanic("tie_wire", () -> List.of(ShapedType.tieWire));
        cutting = new HandleMechanic("cutting", () -> List.of(ShapedType.cutting));
        pressureStick = new HandleMechanic("pressure_stick", () -> List.of(ShapedType.pressureStick));
        blend = new HandleMechanic("blend", () -> List.of(ShapedType.blend));
        decompose = new HandleMechanic("decompose", () -> List.of(ShapedType.decompose));
        recovery = new HandleMechanic("recovery", () -> List.of(ShapedType.recovery));
        forming = new HandleMechanic("forming", () -> List.of(ShapedType.forming));
        manaCoagulation = new HandleMechanic("mana_coagulation", () -> List.of(ShapedType.manaCoagulation));
        voidCase = new DefaultCapacityMechanic("void_case") {
            @Override
            public void addCapability(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, ManaLevel manaLevel, IPosTrack iPosTrack) {
                super.addCapability(event, duskModCapability, manaLevel, iPosTrack);
                duskModCapability.addCapability(ForgeCapabilities.ITEM_HANDLER, new VoidCaseItemHandler(4096L * manaLevel.level));
            }
        };
        voidTank = new DefaultCapacityMechanic("void_tank") {
            @Override
            public void addCapability(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, ManaLevel manaLevel, IPosTrack iPosTrack) {
                super.addCapability(event, duskModCapability, manaLevel, iPosTrack);
                duskModCapability.addCapability(ForgeCapabilities.FLUID_HANDLER, new VoidTankFluidHandler(12800000 * manaLevel.level));
            }
        };
        gatherMana = new DefaultCapacityMechanic("gather_mana") {
            @Override
            public void addCapability(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, ManaLevel manaLevel, IPosTrack iPosTrack) {
                super.addCapability(event, duskModCapability, manaLevel, iPosTrack);
                IBack iBack = duskModCapability.addCapability(CapabilityRegister.iBlack.capability, new Back());
                duskModCapability.addCapability(CapabilityRegister.iManaHandle.capability, new ManaHandle(5120000L * manaLevel.level, 32L * manaLevel.level, iBack));
            }
        };
        manaIO = new IOMechanic.ManaIO();
        itemIO = new IOMechanic.ItemIO();
        fluidIO = new IOMechanic.FluidIO();
        collect = new CollectMechanic();
        whirlBoost = new WhirlBoostMechanic();
        charge = new ChargeMechanic();
        pumpMechanic = new PumpMechanic();
        mining = new MiningMechanic();
        massacre = new MassacreMechanic();
        transformationFEMechanic = new TransformationFEMechanic();
    }

    public ManaLevelBlock(ResourceLocation name) {
        super(name, LEVEL_BLOCK);
    }

    public ManaLevelBlock(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

}
