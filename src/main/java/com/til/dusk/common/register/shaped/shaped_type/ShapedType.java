package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.Dusk;
import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.common.data.tag.BlockTag;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.common.register.RegisterManage;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;
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
     * 烈焰转灵
     */
    public static FlameManaShapedType flameMana;

    /***
     * 植物转灵
     */
    public static BotanyManaShapedType botanyMana;

    /***
     * 食物转灵
     */
    public static FoodManaShapedType foodMana;

    /***
     * 源质转灵
     */
    public static ElementManaShapedType elementMana;


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
     * 晶体种子制造
     */
    public static CrystalSeedMakeShapedType crystalSeedMake;

    /***
     * 组装
     */
    public static AssembleShapedType assemble;

    /***
     * 封装
     */
    public static EncapsulationShapedType encapsulation;

    /***
     * 晶体组装
     */
    public static CrystalAssembleShapedType crystalAssemble;

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
     * 压杆机
     */
    public static PressureStickShapedType pressureStick;

    /***
     * 混合
     */
    public static BlendShapedType blend;

    /***
     * 分解
     */
    public static DecomposeShapedType decompose;

    /***
     * 回收
     */
    public static RecoveryShapedType recovery;

    /***
     * 成型
     */
    public static FormingShapedType forming;

    /***
     * 灵气凝聚
     */
    public static ManaCoagulationShapedType manaCoagulation;

    /***
     * 干细胞提
     */
    public static StemCellExtractShapedType stemCellExtract;

    /***
     * 细胞培养
     */
    public static CellCultureShapedType cellCulture;

    /***
     * uu生成
     */
    public static UUGenerateShapedType uuGenerate;
    /***
     * 质量发生
     */
    public static QualityGenerateShapedType qualityGenerate;

    /***
     * 透析
     */
    public static DialysisShapedType dialysis;

    /***
     * 裂解
     */
    public static SplittingShapedType splitting;

    public static MK1ShapedType mk1;
    public static MK2ShapedType mk2;
    public static MK3ShapedType mk3;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        SHAPED_TYPE = RegisterManage.create(ShapedType.class, new ResourceLocation(Dusk.MOD_ID, "shaped_type"), event);
        ;
        empty = new ShapedType("empty") {
            @Override
            public void registerRuleShaped(Consumer<Shaped> shapedConsumer) {

            }

            @Override
            public void defaultConfig() {
                blockTagKey = new Delayed.BlockDelayed(() -> BlockTag.AIR);
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
        flameMana = new FlameManaShapedType();
        botanyMana = new BotanyManaShapedType();
        foodMana = new FoodManaShapedType();
        grind = new GrindShapedType();
        wash = new WashShapedType();
        centrifugal = new CentrifugalShapedType();
        pack = new PackShapedType();
        unpack = new UnPackShapedType();
        blastFurnace = new BlastFurnaceShapedType();
        crystallizing = new CrystallizingShapedType();
        crystalSeedMake = new CrystalSeedMakeShapedType();
        assemble = new AssembleShapedType();
        encapsulation = new EncapsulationShapedType();
        crystalAssemble = new CrystalAssembleShapedType();
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
        pressureStick = new PressureStickShapedType();
        blend = new BlendShapedType();
        decompose = new DecomposeShapedType();
        recovery = new RecoveryShapedType();
        forming = new FormingShapedType();
        manaCoagulation = new ManaCoagulationShapedType();
        stemCellExtract = new StemCellExtractShapedType();
        cellCulture = new CellCultureShapedType();
        uuGenerate = new UUGenerateShapedType();
        qualityGenerate = new QualityGenerateShapedType();
        dialysis = new DialysisShapedType();
        splitting = new SplittingShapedType();
        mk1 = new MK1ShapedType();
        mk2 = new MK2ShapedType();
        mk3 = new MK3ShapedType();
    }


    public ShapedType(ResourceLocation name) {
        super(name, SHAPED_TYPE);
    }

    public ShapedType(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    @Override
    public void registerShaped(Consumer<Shaped> shapedConsumer) {
        super.registerShaped(shapedConsumer);
        if (relevantShaped != null) {
            for (Shaped shaped : relevantShaped.get()) {
                shapedConsumer.accept(shaped);
            }
        }
        registerRuleShaped(shapedConsumer);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public abstract void registerRuleShaped(Consumer<Shaped> shapedConsumer);

    @Nullable
    @ConfigField
    public Delayed<List<Shaped>> relevantShaped;

    @ConfigField
    public Delayed<TagKey<Block>> blockTagKey;
}
