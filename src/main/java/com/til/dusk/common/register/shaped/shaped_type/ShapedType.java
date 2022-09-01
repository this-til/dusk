package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.Dusk;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.EventPriority;
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
public abstract class ShapedType extends RegisterBasics<ShapedType> {

    public static Supplier<IForgeRegistry<ShapedType>> SHAPED_TYPE;

    public static ShapedType empty;

    /***
     * 灵气提取
     */
    public static ExtractManaShapedType extractMana;

    /***
     * 解咒转灵
     */
    public static DischantmentManaShapedType dischantmentMana;

    /***
     * 末影转灵
     */
    public static EnderManaShapedType enderMana;

    /***
     * 药水转灵
     */
    public static PotionManaShapedType potionMana;

    /***
     * 爆破转灵
     */
    public static ExplosiveManaShapedType explosiveMana;

    /***
     * 寒霜转灵
     */
    public static FrostyManaShapedType frostyMana;

    /***
     * 粘液转灵
     */
    public static SlimeyManaShapedType slimeyMana;

    /***
     * 口臭转灵
     */
    public static HalitosisManaShapedType halitosisMana;


    /***
     * 研磨
     */
    public static GrindShapedType grind;

    /***
     * 洗涤
     */
    public static WashShapedType wash;

    /***
     * 离心
     */
    public static CentrifugalShapedType centrifugal;

    /***
     * 打包
     */
    public static PackShapedType pack;

    /***
     * 解包
     */
    public static UnPackShapedType unpack;

    /***
     * 高炉
     */
    public static BlastFurnaceShapedType blastFurnace;

    /***
     * 结晶
     */
    public static CrystallizingShapedType crystallizing;

    /***
     * 组装
     */
    public static AssembleShapedType assemble;

    /***
     * 蒸馏
     */
    public static DistillationShapedType distillation;

    /***
     * 溶解
     */
    public static DissolutionShapedType dissolution;

    /***
     * 凝固
     */
    public static FreezingShapedType freezing;

    /***
     * 高压融合
     */
    public static HighPressureFuseShapedType highPressureFuse;

    /***
     * 雕刻
     */
    public static CarvingShapedType carving;

    /***
     * 筛选
     */
    public static ScreenShapedType screen;

    /***
     * 熔炉
     */
    public static FurnaceShapedType furnace;

    /***
     * 造石
     */
    public static MakerStoneShapedType makerStone;

    /***
     * 冲压
     */
    public static StampingMachineShapedType stampingMachine;

    /***
     * 车床
     */
    public static LatheShapedType lathe;

    /***
     * 扎线
     */
    public static TieWireShapedType tieWire;
    /***
     * 切割
     */
    public static CuttingShapedType cutting;

    /***
     * 灵气凝聚
     */
    public static ManaCoagulationShapedType manaCoagulation;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        SHAPED_TYPE = event.create(new RegistryBuilder<ShapedType>().setName(new ResourceLocation(Dusk.MOD_ID, "shaped_type")));
        empty = new ShapedType("empty", () -> ManaLevelBlock.frameBasic) {
            @Override
            public void registerSubsidiaryBlack() {
            }
        };
        extractMana = new ExtractManaShapedType();
        dischantmentMana = new DischantmentManaShapedType();
        enderMana = new EnderManaShapedType();
        potionMana = new PotionManaShapedType();
        explosiveMana = new ExplosiveManaShapedType();
        frostyMana = new FrostyManaShapedType();
        slimeyMana = new SlimeyManaShapedType();
        halitosisMana = new HalitosisManaShapedType();
        grind = new GrindShapedType();
        wash = new WashShapedType();
        centrifugal = new CentrifugalShapedType();
        pack = new PackShapedType();
        unpack = new UnPackShapedType();
        blastFurnace = new BlastFurnaceShapedType();
        crystallizing = new CrystallizingShapedType();
        assemble = new AssembleShapedType();
        distillation = new DistillationShapedType();
        dissolution = new DissolutionShapedType();
        freezing = new FreezingShapedType();
        highPressureFuse = new HighPressureFuseShapedType();
        carving = new CarvingShapedType();
        screen = new ScreenShapedType();
        furnace = new FurnaceShapedType();
        makerStone = new MakerStoneShapedType();
        stampingMachine = new StampingMachineShapedType();
        lathe = new LatheShapedType();
        tieWire = new TieWireShapedType();
        cutting = new CuttingShapedType();
        manaCoagulation = new ManaCoagulationShapedType();
    }

    public final Supplier<ManaLevelBlock> manaLevelBlockSupplier;

    public ShapedType(ResourceLocation name, Supplier<ManaLevelBlock> manaLevelBlockSupplier) {
        super(name, SHAPED_TYPE);
        this.manaLevelBlockSupplier = manaLevelBlockSupplier;
    }

    public ShapedType(String name, Supplier<ManaLevelBlock> manaLevelBlockSupplier) {
        this(new ResourceLocation(Dusk.MOD_ID, name), manaLevelBlockSupplier);
    }

    @Override
    public EventPriority getRegisterBlackPriority() {
        return EventPriority.LOWEST;
    }

    @Override
    public abstract void registerSubsidiaryBlack();
}