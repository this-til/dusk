package com.til.dusk.common.register.ore.block.blocks;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.data.tag.BlockTag;
import com.til.dusk.common.register.ore.block.OreBlockDecorate;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.world.block.DuskBlock;
import com.til.dusk.common.world.block.FacingSlabBlock;
import com.til.dusk.util.ModelJsonUtil;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import org.jetbrains.annotations.Nullable;

/**
 * @author til
 */
public class SlabOreBlockDecorate extends OreBlockDecorate {

    public SlabOreBlockDecorate() {
        super("slab");
    }

    @Override
    public @Nullable Block createBlock(Ore ore) {
        Block block = new FacingSlabBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL)
                .strength((float) (strengthBasics * ore.strength), (float) (explosionProofBasics * ore.strength))
                .requiresCorrectToolForDrops()
                .sound(SoundType.STONE));
        BlockTag.addTag(BlockTags.MINEABLE_WITH_PICKAXE, block);
        BlockTag.addTag(BlockTags.NEEDS_IRON_TOOL, block);
        return block;
    }

    @Override
    public JsonObject createBlockModel(Block block, Ore ore) {
        JsonObject variants = new JsonObject();
        for (Property.Value<Direction> facing : FacingSlabBlock.FACING.getAllValues().toList()) {
            for (Property.Value<Boolean> half : FacingSlabBlock.HALF.getAllValues().toList()) {
                for (Property.Value<Boolean> waterlogged : FacingSlabBlock.WATERLOGGED.getAllValues().toList()) {
                    JsonObject jsonObject = new JsonObject();
                    if (half.value()) {
                        jsonObject.add("model", new JsonPrimitive(name.getNamespace() + ":block/slab/" + name.getPath() + "_half"));
                        ModelJsonUtil.fillFacing(jsonObject, facing.value());
                    } else {
                        jsonObject.add("model", new JsonPrimitive(name.getNamespace() + ":block/slab/" + name.getPath()));
                    }
                    variants.add(FacingSlabBlock.FACING.getName() + "=" + FacingSlabBlock.FACING.getName(facing.value()) + "," +
                                 FacingSlabBlock.HALF.getName() + "=" + FacingSlabBlock.HALF.getName(half.value()) + "," +
                                 FacingSlabBlock.WATERLOGGED.getName() + "=" + FacingSlabBlock.HALF.getName(waterlogged.value()), jsonObject);
                }
            }
        }
        return ModelJsonUtil.blockStatePack(variants);
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "半砖");
        lang.add(LangType.EN_CH, "Slab");
    }

    @Override
    public void defaultConfig() {
        strength(0.3);
    }
}
