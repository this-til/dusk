package com.til.dusk.common.register.mana_level.mana_level_block;

import com.til.dusk.Dusk;
import com.til.dusk.common.capability.fluid_handler.VoidTankFluidHandler;
import com.til.dusk.common.capability.item_handler.VoidCaseItemHandler;
import com.til.dusk.common.capability.mana_handle.ManaHandle;
import com.til.dusk.common.register.CapabilityRegister;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.capability.tile_entity.DuskCapabilityProvider;
import com.til.dusk.common.capability.up.IUp;
import com.til.dusk.common.capability.up.Up;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.util.StaticTag;
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

    /***
     * 需要框架升级
     */
    public static final StaticTag NEED_FRAME_UP = new StaticTag("NEED_FRAME_UP", List.of());

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
    public static HandleMechanic dischantmentMana;

    /***
     * 解咒转灵晶体
     */
    public static HandleMechanic enderMana;

    /***
     * 药水转灵晶体
     */
    public static HandleMechanic potionMana;

    /***
     * 爆破转灵晶体
     */
    public static HandleMechanic explosiveMana;

    /***
     * 寒霜转灵晶体
     */
    public static HandleMechanic frostyMana;

    /***
     * 粘液转灵晶体
     */
    public static HandleMechanic slimeyMana;

    /***
     * 口臭转灵晶体
     */
    public static HandleMechanic halitosisMana;

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

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        LEVEL_BLOCK = event.create(new RegistryBuilder<ManaLevelBlock>().setName(new ResourceLocation(Dusk.MOD_ID, "mana_level_block")));
        repeater = new RepeaterMechanic("repeater");
        frameBasic = (DefaultCapacityMechanic) new DefaultCapacityMechanic("frame_basic").removeTag(NEED_FRAME_UP);
        sunlight = new SimilarSolarEnergyMechanic("sunlight", 1, level -> level.isDay() && !level.isRaining(), ColorPrefab.SUNLIGHT_COLOR);
        moonlight = new SimilarSolarEnergyMechanic("moonlight", 1, level -> level.isNight() && !level.isRaining(), ColorPrefab.MOONLIGHT_COLOR);
        rain = new SimilarSolarEnergyMechanic("rain", 4, Level::isRaining, ColorPrefab.RAIN_COLOR);
        extractMana = new HandleMechanic("extract_mana", () -> List.of(ShapedType.extractMana));
        dischantmentMana = new HandleMechanic("dischantment_mana",  () ->List.of(ShapedType.dischantmentMana));
        enderMana = new HandleMechanic("ender_mana", () -> List.of(ShapedType.enderMana));
        potionMana = new HandleMechanic("potion_mana",  () ->List.of(ShapedType.potionMana));
        explosiveMana = new HandleMechanic("explosive_mana",  () ->List.of(ShapedType.explosiveMana));
        frostyMana = new HandleMechanic("frosty_mana", () -> List.of(ShapedType.frostyMana));
        slimeyMana = new HandleMechanic("slimey_mana",  () ->List.of(ShapedType.slimeyMana));
        halitosisMana = new HandleMechanic("halitosis_mana", () -> List.of(ShapedType.halitosisMana));
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
        manaCoagulation = new HandleMechanic("mana_coagulation", () -> List.of(ShapedType.manaCoagulation));
        voidCase = new DefaultCapacityMechanic("void_case") {
            @Override
            public void addCapability(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, ManaLevel manaLevel) {
                super.addCapability(event, duskModCapability, manaLevel);
                duskModCapability.addCapability(ForgeCapabilities.ITEM_HANDLER, new VoidCaseItemHandler(1280L * manaLevel.level));
            }
        };
        voidTank = new DefaultCapacityMechanic("void_tank") {
            @Override
            public void addCapability(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, ManaLevel manaLevel) {
                super.addCapability(event, duskModCapability, manaLevel);
                duskModCapability.addCapability(ForgeCapabilities.FLUID_HANDLER, new VoidTankFluidHandler(1280000 * manaLevel.level));
            }
        };
        gatherMana = new DefaultCapacityMechanic("gather_mana") {
            @Override
            public void addCapability(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, ManaLevel manaLevel) {
                super.addCapability(event, duskModCapability, manaLevel);
                IUp iUp = duskModCapability.addCapability(CapabilityRegister.iUp.capability, new Up());
                duskModCapability.addCapability(CapabilityRegister.iManaHandle.capability, new ManaHandle(5120000L * manaLevel.level, 32L * manaLevel.level, iUp));
            }
        };
        manaIO = new IOMechanic.ManaIO("mana_io", ColorPrefab.MANA_IO);
        itemIO = new IOMechanic.ItemIO("item_io", ColorPrefab.ITEM_IO);
        fluidIO = new IOMechanic.FluidIO("fluid_io", ColorPrefab.FLUID_IO);
        collect = new CollectMechanic("collect");
    }

    public ManaLevelBlock(ResourceLocation name) {
        super(name, LEVEL_BLOCK);
    }

    public ManaLevelBlock(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

}