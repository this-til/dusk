package com.til.dusk.common.register.mana_level;

import com.til.dusk.Dusk;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreBlock;
import com.til.dusk.common.register.ore.OreFluid;
import com.til.dusk.common.register.ore.OreItem;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.GenericMap;
import com.til.dusk.util.pack.DataPack;
import com.til.dusk.util.pack.RegistryPack;
import com.til.dusk.util.pack.TagPack;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.*;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ManaLevel extends RegisterBasics.UnitRegister<ManaLevel, ManaLevelItem, ManaLevelBlock, ManaLevelFluid> {

    public static Supplier<IForgeRegistry<ManaLevel>> LEVEL;

    public static ManaLevel t1;
    public static ManaLevel t2;
    public static ManaLevel t3;
    public static ManaLevel t4;
    public static ManaLevel t5;
    public static ManaLevel t6;
    public static ManaLevel t7;
    public static ManaLevel t8;


    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onEvent(NewRegistryEvent event) {
        LEVEL = event.create(new RegistryBuilder<ManaLevel>().setName(new ResourceLocation(Dusk.MOD_ID, "mana_level")));
        t1 = new ManaLevel(1, 2560, 1, 2, 0.1, 16, new DuskColor(50, 255, 255), null, () -> t2)
                .setSet(OPERATION_BASICS, () -> new DataPack.ManaLevelDataPack()
                        .addInFluid(Ore.highEnergyRedStone.fluidMap.get(OreFluid.solution).fluidTag(), 72))
                .setSet(OPERATION, () -> new DataPack.ManaLevelDataPack()
                        .addInItem(ItemTag.resistanceTag, 4)
                        .addInItem(ItemTag.capacitanceTag, 4)
                        .addInItem(ItemTag.diodeTag, 4)
                        .addInItem(ItemTag.triodeTag, 4))
                .setSet(FORMING, () -> new DataPack.ManaLevelDataPack()
                        .addInItem(Tags.Items.GEMS_AMETHYST, 1))
                .setSet(DESTRUCTION, () -> new DataPack.ManaLevelDataPack()
                        .addInItem(Tags.Items.GEMS_QUARTZ, 1))
                .setSet(GATHER, () -> new DataPack.ManaLevelDataPack()
                        .addInItem(Tags.Items.GEMS_LAPIS, 1))
                .setSet(SPREAD, () -> new DataPack.ManaLevelDataPack()
                        .addInItem(Tags.Items.GEMS_PRISMARINE, 1))
                .setSet(POWER, () -> new DataPack.ManaLevelDataPack()
                        .addRun((s, m) -> s.addInItem(m.getRelationTagPack(OreItem.rotor).itemTagKey(), 1))
                        .addRun((s, m) -> s.addInItem(m.getRelationTagPack(OreBlock.coil).itemTagKey(), 1)))
                .setSet(INSTRUCTIONS, () -> new DataPack.ManaLevelDataPack()
                        .addInItem(ItemTag.inductanceTag, 2)
                        .addInItem(ItemTag.capacitanceTag, 4)
                        .addRun((s, m) -> s.addInItem(m.getRelationTagPack(OreItem.foil).itemTagKey(), 16)));
        t2 = new ManaLevel(2, 1280, 2, 2, 0.09, 18, new DuskColor(100, 200, 225), () -> t1, () -> t3)
                .setSet(OPERATION_BASICS, () -> new DataPack.ManaLevelDataPack()
                        .addInFluid(Ore.highEnergyRedStone.fluidMap.get(OreFluid.solution).fluidTag(), 72)
                        .addInFluid(Ore.coolant.fluidMap.get(OreFluid.solution).fluidTag(), 32))
                .setSet(OPERATION, () -> new DataPack.ManaLevelDataPack()
                        .addInItem(ItemTag.resistanceTag, 8)
                        .addInItem(ItemTag.capacitanceTag, 8)
                        .addInItem(ItemTag.diodeTag, 8)
                        .addInItem(ItemTag.triodeTag, 8))
                .setSet(FORMING, () -> new DataPack.ManaLevelDataPack()
                        .addInItem(Tags.Items.GEMS_AMETHYST, 2))
                .setSet(DESTRUCTION, () -> new DataPack.ManaLevelDataPack()
                        .addInItem(Tags.Items.GEMS_QUARTZ, 2))
                .setSet(GATHER, () -> new DataPack.ManaLevelDataPack()
                        .addInItem(Tags.Items.GEMS_LAPIS, 2))
                .setSet(SPREAD, () -> new DataPack.ManaLevelDataPack()
                        .addInItem(Tags.Items.GEMS_PRISMARINE, 2))
                .setSet(POWER, () -> new DataPack.ManaLevelDataPack()
                        .addRun((s, m) -> s.addInItem(m.getRelationTagPack(OreItem.rotor).itemTagKey(), 2))
                        .addRun((s, m) -> s.addInItem(m.getRelationTagPack(OreBlock.coil).itemTagKey(), 1)))
                .setSet(INSTRUCTIONS, () -> new DataPack.ManaLevelDataPack()
                        .addInItem(ItemTag.inductanceTag, 4)
                        .addInItem(ItemTag.capacitanceTag, 8)
                        .addRun((s, m) -> s.addInItem(m.getRelationTagPack(OreItem.foil).itemTagKey(), 32)));
        t3 = new ManaLevel(3, 640, 3, 4, 0.08, 20, new DuskColor(125, 150, 200), () -> t2, () -> t4)
                .setSet(OPERATION_BASICS, () -> new DataPack.ManaLevelDataPack()
                        .addInFluid(Ore.highEnergyRedStone.fluidMap.get(OreFluid.solution).fluidTag(), 72)
                        .addInFluid(Ore.coolant.fluidMap.get(OreFluid.solution).fluidTag(), 32)
                        .addInFluid(Ore.dissolutionMana.fluidMap.get(OreFluid.solution).fluidTag(), 24))
                .setSet(OPERATION, () -> new DataPack.ManaLevelDataPack()
                        .addInItem(ItemTag.resistanceTag, 16)
                        .addInItem(ItemTag.capacitanceTag, 16)
                        .addInItem(ItemTag.diodeTag, 16)
                        .addInItem(ItemTag.triodeTag, 16))
                .setSet(FORMING, () -> new DataPack.ManaLevelDataPack()
                        .addInItem(Tags.Items.GEMS_AMETHYST, 4))
                .setSet(DESTRUCTION, () -> new DataPack.ManaLevelDataPack()
                        .addInItem(Tags.Items.GEMS_QUARTZ, 4))
                .setSet(GATHER, () -> new DataPack.ManaLevelDataPack()
                        .addInItem(Tags.Items.GEMS_LAPIS, 4))
                .setSet(SPREAD, () -> new DataPack.ManaLevelDataPack()
                        .addInItem(Tags.Items.GEMS_PRISMARINE, 4))
                .setSet(POWER, () -> new DataPack.ManaLevelDataPack()
                        .addRun((s, m) -> s.addInItem(m.getRelationTagPack(OreItem.rotor).itemTagKey(), 4))
                        .addRun((s, m) -> s.addInItem(m.getRelationTagPack(OreBlock.coil).itemTagKey(), 1)))
                .setSet(INSTRUCTIONS, () -> new DataPack.ManaLevelDataPack()
                        .addInItem(ItemTag.inductanceTag, 8)
                        .addInItem(ItemTag.capacitanceTag, 16)
                        .addRun((s, m) -> s.addInItem(m.getRelationTagPack(OreItem.foil).itemTagKey(), 64)));
        t4 = new ManaLevel(4, 320, 4, 4, 0.07, 22, new DuskColor(150, 100, 175), () -> t3, () -> t5)
                .setSet(OPERATION_BASICS, () -> new DataPack.ManaLevelDataPack()
                        .addInFluid(Ore.coolant.fluidMap.get(OreFluid.solution).fluidTag(), 128)
                        .addInFluid(Ore.culture.fluidMap.get(OreFluid.solution).fluidTag(), 32)
                        .addInFluid(Ore.nutrient.fluidMap.get(OreFluid.solution).fluidTag(), 256)
                        .addInFluid(Ore.supportCell.fluidMap.get(OreFluid.solution).fluidTag(), 256))
                .setSet(OPERATION, () -> new DataPack.ManaLevelDataPack()
                        .addInFluid(Ore.neuronCell.fluidMap.get(OreFluid.solution).fluidTag(), 144));
        t5 = new ManaLevel(5, 160, 5, 8, 0.06, 24, new DuskColor(175, 100, 150), () -> t4, () -> t6)
                .setSet(OPERATION_BASICS, () -> new DataPack.ManaLevelDataPack()
                        .addInFluid(Ore.coolant.fluidMap.get(OreFluid.solution).fluidTag(), 128)
                        .addInFluid(Ore.culture.fluidMap.get(OreFluid.solution).fluidTag(), 32)
                        .addInFluid(Ore.nutrient.fluidMap.get(OreFluid.solution).fluidTag(), 1024)
                        .addInFluid(Ore.supportCell.fluidMap.get(OreFluid.solution).fluidTag(), 1024))
                .setSet(OPERATION, () -> new DataPack.ManaLevelDataPack()
                        .addInFluid(Ore.neuronCell.fluidMap.get(OreFluid.solution).fluidTag(), 144));
        t6 = new ManaLevel(6, 80, 6, 8, 0.05, 26, new DuskColor(200, 150, 120), () -> t5, () -> t7)
                .setSet(OPERATION_BASICS, () -> new DataPack.ManaLevelDataPack()
                        .addInFluid(Ore.coolant.fluidMap.get(OreFluid.solution).fluidTag(), 128)
                        .addInFluid(Ore.culture.fluidMap.get(OreFluid.solution).fluidTag(), 32)
                        .addInFluid(Ore.nutrient.fluidMap.get(OreFluid.solution).fluidTag(), 2048)
                        .addInFluid(Ore.supportCell.fluidMap.get(OreFluid.solution).fluidTag(), 2048))
                .setSet(OPERATION, () -> new DataPack.ManaLevelDataPack()
                        .addInFluid(Ore.neuronCell.fluidMap.get(OreFluid.solution).fluidTag(), 144));
        t7 = new ManaLevel(7, 40, 7, 16, 0.04, 28, new DuskColor(225, 200, 100), () -> t6, () -> t8);
        t8 = new ManaLevel(8, 20, 8, 16, 0.03, 30, new DuskColor(255, 255, 50), () -> t7, null);
    }

    /***
     * 等级序列
     */
    public final int level;

    /***
     * 机器运行时的时钟时间
     */
    public final int clock;

    /***
     * 机器并行次数
     */
    public final int parallel;

    /***
     * 最大绑定数量
     */
    public final int maxBind;

    /***
     * 灵气损耗
     */
    public final double manaLoss;

    /***
     * 机器的最大范围
     */
    public final int maxRange;

    /***
     * 机器颜色
     */
    public final DuskColor color;

    /***
     * 上一级
     */
    @Nullable
    public final Supplier<ManaLevel> up;

    /***
     * 下一级
     */
    @Nullable
    public final Supplier<ManaLevel> next;

    public final Map<RegisterBasics<?>, TagPack> relationTagMap = new HashMap<>();

    public ManaLevel(int level, int clock, int parallel, int maxBind, double manaLoss, int maxRange, DuskColor color, @Nullable Supplier<ManaLevel> up, @Nullable Supplier<ManaLevel> next) {
        this(new ResourceLocation(Dusk.MOD_ID, "t" + level), level, clock, parallel, maxBind, manaLoss, maxRange, color, up, next);
    }

    public ManaLevel(ResourceLocation name, int level, int clock, int parallel, int maxBind, double manaLoss, int maxRange, DuskColor color, @Nullable Supplier<ManaLevel> up, @Nullable Supplier<ManaLevel> next) {
        super(name, LEVEL);
        this.level = level;
        this.clock = clock;
        this.parallel = parallel;
        this.maxBind = maxBind;
        this.manaLoss = manaLoss;
        this.maxRange = maxRange;
        this.color = color;
        this.up = up;
        this.next = next;
    }

    public TagPack getRelationTagPack(RegisterBasics<?> registerBasics) {
        if (relationTagMap.containsKey(registerBasics)) {
            return relationTagMap.get(registerBasics);
        }
        TagPack tagPack = new TagPack(
                Dusk.instance.ITEM_TAG.createTagKey(fuseName("/", new String[]{name.getPath(), "acceptable", registerBasics.name.getPath()})),
                Dusk.instance.BLOCK_TAG.createTagKey(fuseName("/", new String[]{name.getPath(), "acceptable", registerBasics.name.getPath()})),
                Dusk.instance.FLUID_TAG.createTagKey(fuseName("/", new String[]{name.getPath(), "acceptable", registerBasics.name.getPath()}))
        );
        relationTagMap.put(registerBasics, tagPack);
        return tagPack;
    }

    @Override
    public RegistryPack<ManaLevel, ManaLevelItem, ManaLevelBlock, ManaLevelFluid> getCellRegistry() {
        if (cellRegistry == null) {
            cellRegistry = new RegistryPack<>(ManaLevelItem.LEVEL_ITEM, ManaLevelBlock.LEVEL_BLOCK, ManaLevelFluid.LEVEL_FLUID);
        }
        return cellRegistry;
    }

    public static RegistryPack<ManaLevel, ManaLevelItem, ManaLevelBlock, ManaLevelFluid> cellRegistry;

    public static final GenericMap.IKey<DataPack.ManaLevelDataPack> OPERATION_BASICS = new GenericMap.IKey.Key<>();
    public static final GenericMap.IKey<DataPack.ManaLevelDataPack> OPERATION = new GenericMap.IKey.Key<>();
    public static final GenericMap.IKey<DataPack.ManaLevelDataPack> FORMING = new GenericMap.IKey.Key<>();
    public static final GenericMap.IKey<DataPack.ManaLevelDataPack> DESTRUCTION = new GenericMap.IKey.Key<>();
    public static final GenericMap.IKey<DataPack.ManaLevelDataPack> GATHER = new GenericMap.IKey.Key<>();
    public static final GenericMap.IKey<DataPack.ManaLevelDataPack> SPREAD = new GenericMap.IKey.Key<>();
    public static final GenericMap.IKey<DataPack.ManaLevelDataPack> POWER = new GenericMap.IKey.Key<>();
    public static final GenericMap.IKey<DataPack.ManaLevelDataPack> INSTRUCTIONS = new GenericMap.IKey.Key<>();

}
