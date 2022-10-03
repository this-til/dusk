package com.til.dusk.common.register.mana_level.block;

import com.til.dusk.Dusk;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.mana_level.block.HandleMechanic;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.common.world.block.ModBlock;
import com.til.dusk.util.DuskColor;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.function.Supplier;

/**
 * @author til
 */
public class ExtractManaMechanic extends HandleMechanic {

    public static final ResourceLocation MODEL_NAME = new ResourceLocation(Dusk.MOD_ID, "extract_mana");

    public final DuskColor color;

    public ExtractManaMechanic(ResourceLocation name, Supplier<List<ShapedType>> getShapedTypeList, DuskColor color) {
        super(name, getShapedTypeList);
        this.color = color;
    }

    public ExtractManaMechanic(String name, Supplier<List<ShapedType>> getShapedTypeList, DuskColor color) {
        this(new ResourceLocation(Dusk.MOD_ID, name), getShapedTypeList, color);
    }

    @Override
    public void dyeBlack(ManaLevel manaLevel, ColorProxy.ItemColorPack itemColorPack) {
        super.dyeBlack(manaLevel, itemColorPack);
        itemColorPack.addColor(1, itemStack -> color);
    }

    @Override
    public void dyeBlack(ManaLevel manaLevel, ColorProxy.BlockColorPack blockColorPack) {
        super.dyeBlack(manaLevel, blockColorPack);
        blockColorPack.addColor(1, (blockState, blockAndTintGetter, blockPos) -> color);
    }

    @Override
    public ModBlock.ICustomModel getBlockModelMapping(ManaLevel manaLevel) {
        return () -> MODEL_NAME;
    }
}
