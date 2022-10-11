package com.til.dusk.common.register.mana_level.mana_level;

import com.til.dusk.Dusk;
import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.common.config.util.IShapedOreConfig;
import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.data.tag.BlockTag;
import com.til.dusk.common.data.tag.FluidTag;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.event.RegisterManageEvent;
import com.til.dusk.common.register.RegisterManage;
import com.til.dusk.common.register.UnitRegister;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.mana_level.fluid.ManaLevelFluid;
import com.til.dusk.common.register.mana_level.item.ManaLevelItem;
import com.til.dusk.common.register.mana_level.mana_level.mana_levels.*;
import com.til.dusk.common.world.tag.TagPackSupplier;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.pack.BlockPack;
import com.til.dusk.util.pack.FluidPack;
import com.til.dusk.util.pack.ItemPack;
import com.til.dusk.util.pack.RegistryPack;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public abstract class ManaLevel extends UnitRegister<ManaLevel, ManaLevelItem, ManaLevelBlock, ManaLevelFluid> {

    public static Supplier<IForgeRegistry<ManaLevel>> MANA_LEVEL;
    public static Map<Integer, ManaLevel> ID_MAP;
    public static ManaLevel min;
    public static ManaLevel max;

    public static T1ManaLevel t1;
    public static T2ManaLevel t2;
    public static T3ManaLevel t3;
    public static T4ManaLevel t4;
    public static T5ManaLevel t5;
    public static T6ManaLevel t6;
    public static T7ManaLevel t7;
    public static T8ManaLevel t8;


    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onEvent(NewRegistryEvent event) {
        MANA_LEVEL = RegisterManage.create(ManaLevel.class, new ResourceLocation(Dusk.MOD_ID, "mana_level"), event);
        t1 = new T1ManaLevel();
        t2 = new T2ManaLevel();
        t3 = new T3ManaLevel();
        t4 = new T4ManaLevel();
        t5 = new T5ManaLevel();
        t6 = new T6ManaLevel();
        t7 = new T7ManaLevel();
        t8 = new T8ManaLevel();
    }

    public static void sort() {
        ID_MAP = new HashMap<>();
        List<ManaLevel> manaLevels = new ArrayList<>(8);
        for (ManaLevel manaLevel : MANA_LEVEL.get()) {
            manaLevels.add(manaLevel);
        }
        manaLevels = manaLevels.stream().sorted(Comparator.comparing(m -> m.level)).toList();
        if (manaLevels.size() <= 0) {
            return;
        }
        min = manaLevels.get(0);
        max = manaLevels.get(manaLevels.size() - 1);
        ManaLevel old = null;
        int id = 0;
        for (ManaLevel manaLevel : manaLevels) {
            if (old != null) {
                old.next = manaLevel;
            }
            manaLevel.up = old;
            manaLevel.levelId = id;
            old = manaLevel;
            ID_MAP.put(id, manaLevel);
            id++;
        }
    }

    public final TagPackSupplier acceptableTagPack;

    protected int levelId;
    @Nullable
    protected ManaLevel up;
    @Nullable
    protected ManaLevel next;

    public ManaLevel(ResourceLocation name) {
        super(name, MANA_LEVEL);
        acceptableTagPack = new TagPackSupplier(name, "acceptable");
    }

    public ManaLevel(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, name.getPath());
        lang.add(LangType.EN_CH, name.getPath());
    }

    @Override
    public RegistryPack<ManaLevel, ManaLevelItem, ManaLevelBlock, ManaLevelFluid> getCellRegistry() {
        if (cellRegistry == null) {
            cellRegistry = new RegistryPack<>(ManaLevelItem.LEVEL_ITEM, ManaLevelBlock.LEVEL_BLOCK, ManaLevelFluid.LEVEL_FLUID);
        }
        return cellRegistry;
    }

    @Override
    protected void registerItem(ManaLevelItem manaLevelItem, ItemPack itemPack) {
        super.registerItem(manaLevelItem, itemPack);
        ItemTag.addTag(acceptableTagPack.getTagPack(manaLevelItem).itemTagKey(), itemPack.item());
    }

    @Override
    protected void registerBlock(ManaLevelBlock manaLevelBlock, BlockPack blockPack) {
        super.registerBlock(manaLevelBlock, blockPack);
        ItemTag.addTag(acceptableTagPack.getTagPack(manaLevelBlock).itemTagKey(), blockPack.blockItem());
        BlockTag.addTag(acceptableTagPack.getTagPack(manaLevelBlock).blockTagKey(), blockPack.block());
    }

    @Override
    protected void registerFluid(ManaLevelFluid manaLevelFluid, FluidPack fluidPack) {
        super.registerFluid(manaLevelFluid, fluidPack);
        FluidTag.addTag(acceptableTagPack.getTagPack(manaLevelFluid).fluidTagKey(), fluidPack.source());
        if (fluidPack.liquidBlock() != null) {
            BlockTag.addTag(acceptableTagPack.getTagPack(manaLevelFluid).blockTagKey(), fluidPack.liquidBlock());
        }
        if (fluidPack.bucketItem() != null) {
            ItemTag.addTag(acceptableTagPack.getTagPack(manaLevelFluid).itemTagKey(), fluidPack.bucketItem());
        }
    }

    public static RegistryPack<ManaLevel, ManaLevelItem, ManaLevelBlock, ManaLevelFluid> cellRegistry;

    @Nullable
    public ManaLevel getUp() {
        return up;
    }

    @Nullable
    public ManaLevel getNext() {
        return next;
    }

    /***
     * 等级序列
     */
    @ConfigField
    public int level;
    /***
     * 机器运行时的时钟时间
     */
    @ConfigField
    public int clock;
    /***
     * 机器并行次数
     */
    @ConfigField
    public int parallel;
    /***
     * 最大绑定数量
     */
    @ConfigField
    public int maxBind;
    /***
     * 灵气损耗
     */
    @ConfigField
    public double manaLoss;
    /***
     * 机器的最大范围
     */
    @ConfigField
    public int maxRange;
    /***
     * 机器颜色
     */
    @ConfigField
    public DuskColor color;

    @Nullable
    @ConfigField
    public Delayed<List<IShapedOreConfig<ManaLevel>>> operationBasics;
    @Nullable
    @ConfigField
    public Delayed<List<IShapedOreConfig<ManaLevel>>> operation;
    @Nullable
    @ConfigField
    public Delayed<List<IShapedOreConfig<ManaLevel>>> forming;
    @Nullable
    @ConfigField
    public Delayed<? extends List<IShapedOreConfig<ManaLevel>>> destruction;
    @Nullable
    @ConfigField
    public Delayed<? extends List<IShapedOreConfig<ManaLevel>>> gather;
    @Nullable
    @ConfigField
    public Delayed<? extends List<IShapedOreConfig<ManaLevel>>> spread;
    @Nullable
    @ConfigField
    public Delayed<? extends List<IShapedOreConfig<ManaLevel>>> power;
    @Nullable
    @ConfigField
    public Delayed<? extends List<IShapedOreConfig<ManaLevel>>> instructions;
    /***
     * 能使用工具制造
     */
    @ConfigField
    public boolean canUseToolMake;
    /***
     * 可以使用配方制造
     */
    @ConfigField
    public boolean canUseRecipeMake;

    public static ManaLevel get(ManaLevel manaLevel, MakeLevel makeLevel, boolean isMustRegister) {
        return switch (makeLevel) {
            case UP -> manaLevel.getUp() != null ? manaLevel.getUp() : isMustRegister ? ManaLevel.min : null;
            case CURRENT -> manaLevel;
            case NEXT -> manaLevel.getNext() != null ? manaLevel.getNext() : isMustRegister ? ManaLevel.max : null;
        };
    }
}
