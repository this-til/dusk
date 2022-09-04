package com.til.dusk.common.register.mana_level.mana_level_block;

import com.til.dusk.Dusk;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author til
 */
public class ExtractManaMechanic extends HandleMechanic {

    public static final ResourceLocation MODEL_NAME = new ResourceLocation(Dusk.MOD_ID, "extract_mana");

    public final Color color;

    public ExtractManaMechanic(ResourceLocation name, Supplier<List<ShapedType>> getShapedTypeList, Color color) {
        super(name, getShapedTypeList);
        this.color = color;
    }

    public ExtractManaMechanic(String name, Supplier<List<ShapedType>> getShapedTypeList, Color color) {
        this(new ResourceLocation(Dusk.MOD_ID, name), getShapedTypeList, color);
    }

    @Override
    public void dyeBlack(ManaLevel manaLevel, ColorProxy.ItemColorPack itemColorPack) {
        super.dyeBlack(manaLevel, itemColorPack);
        itemColorPack.addClock(1, itemStack -> color);
    }

    @Override
    public void dyeBlack(ManaLevel manaLevel, ColorProxy.BlockColorPack blockColorPack) {
        super.dyeBlack(manaLevel, blockColorPack);
        blockColorPack.addClock(1, (blockState, blockAndTintGetter, blockPos) -> color);
    }

    @Override
    public ResourceLocation getBlockModelMapping(ManaLevel manaLevel) {
        return MODEL_NAME;
    }
}
