package com.til.dusk.common.register.mana_level.block;

import com.til.dusk.Dusk;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.config.ConfigKey;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.common.world.block.ModBlock;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.nbt.cell.AllNBTCell;
import net.minecraft.resources.ResourceLocation;

import java.util.Set;
import java.util.function.Supplier;

/**
 * @author til
 */
public abstract class ExtractManaMechanic extends HandleMechanic {

    public static final ResourceLocation MODEL_NAME = new ResourceLocation(Dusk.MOD_ID, "extract_mana");


    public ExtractManaMechanic(ResourceLocation name, Supplier<Set<ShapedType>> getShapedTypeList) {
        super(name, getShapedTypeList);
    }

    public ExtractManaMechanic(String name, Supplier<Set<ShapedType>> getShapedTypeList) {
        this(new ResourceLocation(Dusk.MOD_ID, name), getShapedTypeList);
    }

    @Override
    public void dyeBlack(ManaLevel manaLevel, ColorProxy.ItemColorPack itemColorPack) {
        super.dyeBlack(manaLevel, itemColorPack);
        DuskColor color = getConfig(EXTRACT_MANA_COLOR);
        itemColorPack.addColor(1, itemStack -> color);
    }

    @Override
    public void dyeBlack(ManaLevel manaLevel, ColorProxy.BlockColorPack blockColorPack) {
        super.dyeBlack(manaLevel, blockColorPack);
        DuskColor color = getConfig(EXTRACT_MANA_COLOR);
        blockColorPack.addColor(1, (blockState, blockAndTintGetter, blockPos) -> color);
    }

    @Override
    public ModBlock.ICustomModel getBlockModelMapping(ManaLevel manaLevel) {
        return () -> MODEL_NAME;
    }

    public static final ConfigKey<DuskColor> EXTRACT_MANA_COLOR = new ConfigKey<>("mana_level_block.extract_mana.color", AllNBTCell.COLOR, null);
}
