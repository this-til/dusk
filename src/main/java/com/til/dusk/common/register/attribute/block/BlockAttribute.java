package com.til.dusk.common.register.attribute.block;

import com.til.dusk.Dusk;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.common.register.RegisterManage;
import com.til.dusk.util.Lang;
import com.til.dusk.util.Util;
import com.til.dusk.util.math.INumberPack;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;

import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public abstract class BlockAttribute<N> extends RegisterBasics<BlockAttribute<N>> {
    public static Supplier<IForgeRegistry<BlockAttribute<?>>> BLOCK_ATTRIBUTE;

    /***
     * 效率，提高基体运行速度
     */
    public static BlockAttribute<Long> efficiency;

    /***
     * 消耗，提高消耗
     */
    public static BlockAttribute<Long> consume;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        BLOCK_ATTRIBUTE = RegisterManage.create(Util.forcedConversion(BlockAttribute.class), new ResourceLocation(Dusk.MOD_ID, "block_attribute"), event);
        efficiency = new BlockAttribute<>("efficiency", new INumberPack.ILongPack.Range(new INumberPack.ILongPack.Constant(1), null)) {
            @Override
            public void defaultConfig() {
                defaultValue = 1L;
            }
        }.addLang(langTool -> {
            langTool.setCache(efficiency.name.toLanguageKey());
            langTool.add(LangType.ZH_CN, "效率");
            langTool.add(LangType.EN_CH, "Efficiency");
        });
        consume = new BlockAttribute<>("consume", new INumberPack.ILongPack.Range(new INumberPack.ILongPack.Constant(1), null)) {
            @Override
            public void defaultConfig() {
                defaultValue = 1L;
            }
        }.addLang(langTool -> {
            langTool.setCache(efficiency.name.toLanguageKey());
            langTool.add(LangType.ZH_CN, "消耗");
            langTool.add(LangType.EN_CH, "Consume");
        });
    }

    public BlockAttribute(ResourceLocation name, INumberPack<N> range) {
        super(name, Util.forcedConversion(BLOCK_ATTRIBUTE));
        this.range = range;
    }

    public BlockAttribute(String name, INumberPack<N> range) {
        this(new ResourceLocation(Dusk.MOD_ID, name), range);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /***
     * 这个属性值存在的范围
     */
    @ConfigField
    public INumberPack<N> range;

    @ConfigField
    public N defaultValue;
}
