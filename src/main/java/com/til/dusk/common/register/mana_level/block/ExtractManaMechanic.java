package com.til.dusk.common.register.mana_level.block;

import com.google.gson.JsonObject;
import com.til.dusk.Dusk;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.ModelJsonUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.Set;
import java.util.function.Supplier;

/**
 * @author til
 */
public abstract class ExtractManaMechanic extends HandleMechanic {

    public static final ResourceLocation MODEL_NAME = new ResourceLocation(Dusk.MOD_ID, "extract_mana");


    public ExtractManaMechanic(ResourceLocation name, Supplier<Set<ShapedType>> getShapedTypeList) {
        super(name);
    }

    public ExtractManaMechanic(String name, Supplier<Set<ShapedType>> getShapedTypeList) {
        this(new ResourceLocation(Dusk.MOD_ID, name), getShapedTypeList);
    }

    @Override
    public void dyeBlack(ManaLevel manaLevel, ColorProxy.ItemColorPack itemColorPack) {
        super.dyeBlack(manaLevel, itemColorPack);
        itemColorPack.addColor(1, itemStack -> extractManaColor);
    }

    @Override
    public void dyeBlack(ManaLevel manaLevel, ColorProxy.BlockColorPack blockColorPack) {
        super.dyeBlack(manaLevel, blockColorPack);
        blockColorPack.addColor(1, (blockState, blockAndTintGetter, blockPos) -> extractManaColor);
    }

    @Override
    public JsonObject createBlockModel(Block block, ManaLevel o) {
        return ModelJsonUtil.createBlockState(MODEL_NAME);
    }

    @Override
    public JsonObject createModel(Item item, ManaLevel o) {
        return ModelJsonUtil.createItemFather(MODEL_NAME);
    }

    @ConfigField
    public DuskColor extractManaColor;

}
