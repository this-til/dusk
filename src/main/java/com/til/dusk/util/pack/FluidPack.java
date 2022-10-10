package com.til.dusk.util.pack;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.Nullable;

/***
 * 流体包
 * 记录流体配置信息
 * @param fluidType 流体类型
 * @param source 静态的
 * @param flowing 流动的
 * @param liquidBlock 流体方块
 * @param bucketItem 流体物品
 */
public record FluidPack(
        FluidType fluidType,
        FlowingFluid source,
        @Deprecated FlowingFluid flowing,
        TagKey<Fluid> fluidTag,
        @Nullable LiquidBlock liquidBlock,
        @Nullable TagKey<Block> liquidBlockTag,
        @Nullable BucketItem bucketItem,
        @Nullable TagKey<Item> bucketItemTag) {

}
