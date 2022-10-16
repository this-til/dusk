package com.til.dusk.util.nbt.pack;

import com.til.dusk.common.capability.handle.ShapedHandle;
import com.til.dusk.common.capability.skill.ISkill;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.bind_type.BindType;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.ShapedHandleProcess;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.common.register.skill.Skill;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.nbt.cell.AllNBTCell;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;
import java.util.Map;

/**
 * Tag操作工具
 *
 * @author til
 */
public class AllNBTPack {

    public static final IntPack ID = new IntPack("id");

    public static final NBTPack<Item> ITEM = new NBTPack<>("item", AllNBTCell.ITEM);
    public static final NBTPack<Block> BLOCK = new NBTPack<>("block", AllNBTCell.BLOCK);
    public static final NBTPack<Fluid> FLUID = new NBTPack<>("fluid", AllNBTCell.FLUID);
    public static final NBTPack<ItemStack> ITEM_STACK = new NBTPack<>("itemStack", AllNBTCell.ITEM_STACK);
    public static final NBTPack<FluidStack> FLUID_STACK = new NBTPack<>("fluidStack", AllNBTCell.FLUID_STATE);
    public static final NBTPack<List<ItemStack>> OUT_ITEM = new NBTPack<>("outItem", AllNBTCell.ITEM_STACK.getListNBTCell());
    public static final NBTPack<List<FluidStack>> OUT_FLUID = new NBTPack<>("outFluid", AllNBTCell.FLUID_STATE.getListNBTCell());
    public static final NBTPack<BlockPos> BLOCK_POS = new NBTPack<>("blockPos", AllNBTCell.BLOCK_POS);
    public static final NBTPack<DuskColor> COLOR_SEPARATE = new NBTPack<>("colorSeparate", AllNBTCell.COLOR);
    public static final IntPack COLOR = new IntPack("colorTag");
    public static final LongPack SURPLUS_TIME = new LongPack("surplusTime");
    public static final LongPack CONSUME_MANA = new LongPack("consumeMana");
    public static final LongPack _SURPLUS_TIME = new LongPack("_surplusTime");
    public static final LongPack OUT_MANA = new LongPack("outMana");
    public static final NBTPack<ShapedHandleProcess> PROCESS = new NBTPack<>("process", AllNBTCell.SHAPED_HANDLE_PROCESS);
    public static final NBTPack<ShapedHandle> SHAPED_HANDLE = new NBTPack<>("shapedHandleTag", AllNBTCell.SHAPED_HANDLE);
    public static final LongPack MANA = new LongPack("manaTag");
    public static final LongPack MAX_MANA = new LongPack("maxMana");
    public static final LongPack RATE = new LongPack("rate");
    public static final IntPack TIME = new IntPack("time");
    public static final IntPack CYCLE_TIME = new IntPack("cycleTime");
    public static final NBTPack<List<BindType>> BIND_TYPE_LIST = new NBTPack<>("bindTypeList", AllNBTCell.BIND_TYPE.getListNBTCell());
    public static final NBTPack<BindType> BIND_TYPE = new NBTPack<>("bindType", AllNBTCell.BIND_TYPE);
    public static final NBTPack<List<BlockPos>> BLOCK_POS_LIST = new NBTPack<>("blockPosList", AllNBTCell.BLOCK_POS.getListNBTCell());
    public static final NBTPack<List<List<BlockPos>>> BLOCK_POS_LIST_LIST = new NBTPack<>("blockPosListList", AllNBTCell.BLOCK_POS.getListNBTCell().getListNBTCell());
    public static final IntPack MAX_BIND = new IntPack("maxBindTag");
    public static final IntPack MAX_RANGE = new IntPack("maxRangeTag");
    public static final NBTPack<Map<BindType, List<BlockPos>>> BIND_TYPE_BLOCK_POS_LIST_MAP = new NBTPack<>("bindTypeBlockPosListMap", AllNBTCell.BIND_TYPE_LIST);
    public static final NBTPack<List<ShapedHandle>> SHAPED_HANDLE_LIST = new NBTPack<>("shapedHandleList", AllNBTCell.SHAPED_HANDLE.getListNBTCell());
    public static final NBTPack<ManaLevel> MANA_LEVEL = new NBTPack<>("manaLevel", AllNBTCell.MANA_LEVEL);
    public static final NBTPack<Boolean> IS_SHOW = new NBTPack<>("isShow", AllNBTCell.BOOLEAN);
    public static final NBTPack<List<ShapedDrive>> SHAPED_DRIVE_LIST = new NBTPack<>("shapedDriveList", AllNBTCell.SHAPED_DRIVE.getListNBTCell());
    public static final IntPack MAX_PARALLEL = new IntPack("maxParallel");
    public static final DoublePack PROBABILITY = new DoublePack("probability");
    public static final IntPack MB = new IntPack("mb");
    public static final IntPack MAX_AMOUNT = new IntPack("maxAmount");
    public static final NBTPack<List<ItemStack>> ITEM_STACK_LIST_TAG = new NBTPack<>("itemStackList", AllNBTCell.ITEM_STACK.getListNBTCell());
    public static final NBTPack<List<FluidStack>> FLUID_STACK_LIST_TAG = new NBTPack<>("fluidStackList", AllNBTCell.FLUID_STATE.getListNBTCell());
    public static final LongPack COUNT = new LongPack("count");
    public static final BooleanPack IS_VOID_CASE_ITEM_HANDLER = new BooleanPack("isVoidCaseItemHandle");
    public static final NBTPack<Class<?>> TYPE = new NBTPack<>("type", AllNBTCell.CLASS);
    public static final NBTPack<ResourceLocation> NAME = new NBTPack<>("name", AllNBTCell.RESOURCE_LOCATION);
    public static final NBTPack<ShapedType> SHAPED_TYPE = new NBTPack<>("shapedType", AllNBTCell.SHAPED_TYPE);
    public static final NBTPack<ShapedDrive> SHAPED_DRIVE = new NBTPack<>("shapedDrive", AllNBTCell.SHAPED_DRIVE);
    public static final NBTPack<TagKey<Item>> ITEM_TAG = new NBTPack<>("itemTag", AllNBTCell.ITEM_TAG);
    public static final NBTPack<TagKey<Fluid>> FLUID_TAG = new NBTPack<>("fluidTag", AllNBTCell.FLUID_TAG);
    public static final NBTPack<List<TagKey<Item>>> ITEM_TAG_LIST = new NBTPack<>("itemTagList", AllNBTCell.ITEM_TAG.getListNBTCell());
    public static final NBTPack<List<TagKey<Fluid>>> FLUID_TAG_LIST = new NBTPack<>("fluidTagList", AllNBTCell.FLUID_TAG.getListNBTCell());
    public static final NBTPack<Integer> AMOUNT = new NBTPack("amount", AllNBTCell.INT);
    public static final NBTPack<Integer> AMOUNT_LIST = new NBTPack("amountListTag", AllNBTCell.INT.getListNBTCell());
    public static final NBTPack<List<Double>> PROBABILITY_LIST = new NBTPack("probabilityList", AllNBTCell.DOUBLE.getListNBTCell());
    public static final NBTPack<Map<TagKey<Item>, Integer>> ITEM_IN_MAP = new NBTPack<>("itemInMap", AllNBTCell.ITEM_TAG_INT_MAP);
    public static final NBTPack<Map<TagKey<Fluid>, Integer>> FLUID_IN_MAP = new NBTPack<>("fluidInMap", AllNBTCell.FLUID_TAG_INT_MAP);
    public static final NBTPack<Map<ItemStack, Double>> ITEM_OUT_MAP = new NBTPack<>("itemOutMap", AllNBTCell.ITEM_STACK_DOUBLE_MAP);
    public static final NBTPack<Map<FluidStack, Double>> FLUID_OUT_MAP = new NBTPack<>("fluidOutMap", AllNBTCell.FLUID_STACK_DOUBLE_MAP);
    public static final NBTPack<TagKey<Item>> ITEM_SCREEN = new NBTPack<>("itemScreen", AllNBTCell.ITEM_TAG);
    public static final NBTPack<TagKey<Fluid>> FLUID_SCREEN = new NBTPack<>("fluidScreen", AllNBTCell.FLUID_TAG);
    public static final NBTPack<Map<Skill, ISkill.SkillCell>> SKILL_SKILL_CELL_MAP = new NBTPack<>("skillSkillSell", AllNBTCell.SKILL_SKILL_CELL_MAP);

    public static final IntPack ORIGINAL_LEVEL = new IntPack("originalLevel");
    public static final IntPack LEVEL = new IntPack("level");
    public static final IntPack CD = new IntPack("CD");
    public static final NBTPack<CompoundTag> NBT = new NBTPack<>("nbt", AllNBTCell.NBT);

    public static final NBTPack<AttributeModifier> ATTRIBUTE_MODIFIER = new NBTPack<>("attributeModifier", AllNBTCell.ATTRIBUTE_MODIFIER);
    public static final NBTPack<Map<Attribute, List<AttributeModifier>>> ATTRIBUTE_ATTRIBUTE_MODIFIER_LIST_MAP = new NBTPack<>("attributeAttributeModifierListMap", AllNBTCell.ATTRIBUTE_LIST_NBT_MAP);
    public static final IntPack ENERGY = new IntPack("energy");
    public static final IntPack MAX_ENERGY = new IntPack("maxEnergy");
    public static final IntPack RATE_ENERGY = new IntPack("rateEnergy");
    public static final IntPack BASICS_OUT = new IntPack("basicsOut");

}
