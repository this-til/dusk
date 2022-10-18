package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.attribute.block.BlockAttribute;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.mana_level.block.ManaLevelMultiBlockMechanic;
import com.til.dusk.common.register.multiblock.MultiBlock;
import com.til.dusk.util.math.INumberPack;

import java.util.List;
import java.util.Map;

/**
 * @author til
 */
public class AccelerateMultiBlockMechanic extends ManaLevelMultiBlockMechanic {

    public AccelerateMultiBlockMechanic() {
        super("accelerate_multi_block");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "超频法阵核心");
        lang.add(LangType.EN_CH, "Accelerate Core");
    }

    @Override
    public void defaultConfig() {
        multiBlock = MultiBlock.accelerate;
        clock = new INumberPack.IIntPack.Constant(30 * 20);
        supplier = Map.of(
                BlockAttribute.efficiency, new INumberPack.IIntPack.PackList(List.of(
                new INumberPack.IIntPack.LinearFunction(new INumberPack.IIntPack.Constant(1), new INumberPack.IIntPack.Constant(1)),
                new INumberPack.IIntPack.ExponentialFunction(new INumberPack.IIntPack.Constant(2)))),
                BlockAttribute.consume, new INumberPack.IIntPack.PackList(List.of(
                        new INumberPack.IIntPack.LinearFunction(new INumberPack.IIntPack.Constant(1), new INumberPack.IIntPack.Constant(1)),
                        new INumberPack.IIntPack.ExponentialFunction(new INumberPack.IIntPack.Constant(2))))
        );
    }
}
