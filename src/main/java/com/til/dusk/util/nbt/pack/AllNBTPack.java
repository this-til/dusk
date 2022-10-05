package com.til.dusk.util.nbt.pack;

import com.til.dusk.common.capability.skill.ISkill;
import com.til.dusk.common.capability.handle.ShapedHandle;
import com.til.dusk.common.register.other.BindType;
import com.til.dusk.common.register.mana_level.ManaLevel;
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
    public static final NBTPack<ItemStack> ITEM_STACK = new NBTPack<>("item_stack", AllNBTCell.ITEM_STACK);
    public static final NBTPack<FluidStack> FLUID_STACK = new NBTPack<>("fluid_stack", AllNBTCell.FLUID_STATE);
    public static final NBTPack<List<ItemStack>> OUT_ITEM = new NBTPack<>("out_item", AllNBTCell.ITEM_STACK.getListNBTCell());
    public static final NBTPack<List<FluidStack>> OUT_FLUID = new NBTPack<>("out_fluid", AllNBTCell.FLUID_STATE.getListNBTCell());
    public static final NBTPack<BlockPos> BLOCK_POS = new NBTPack<>("block_pos", AllNBTCell.BLOCK_POS);
    public static final NBTPack<DuskColor> COLOR_SEPARATE = new NBTPack<>("color_separate", AllNBTCell.COLOR);
    public static final IntPack COLOR = new IntPack("color_tag");
    public static final LongPack SURPLUS_TIME = new LongPack("surplus_time");
    public static final LongPack CONSUME_MANA = new LongPack("consume_mana");
    public static final LongPack _SURPLUS_TIME = new LongPack("_surplus_time");
    public static final LongPack OUT_MANA = new LongPack("out_mana");
    public static final NBTPack<ShapedHandleProcess> PROCESS = new NBTPack<>("process", AllNBTCell.SHAPED_HANDLE_PROCESS);
    public static final NBTPack<ShapedHandle> SHAPED_HANDLE = new NBTPack<>("shaped_handle_tag", AllNBTCell.SHAPED_HANDLE);
    public static final LongPack MANA = new LongPack("mana_tag");
    public static final LongPack MAX_MANA = new LongPack("max_mana");
    public static final LongPack RATE = new LongPack("rate");
    public static final IntPack TIME = new IntPack("time");
    public static final IntPack CYCLE_TIME = new IntPack("cycle_time");
    public static final NBTPack<List<BindType>> BIND_TYPE_LIST = new NBTPack<>("bind_type_list", AllNBTCell.BIND_TYPE.getListNBTCell());
    public static final NBTPack<BindType> BIND_TYPE = new NBTPack<>("bind_type", AllNBTCell.BIND_TYPE);
    public static final NBTPack<List<BlockPos>> BLOCK_POS_LIST = new NBTPack<>("block_pos_list", AllNBTCell.BLOCK_POS.getListNBTCell());
    public static final NBTPack<List<List<BlockPos>>> BLOCK_POS_LIST_LIST = new NBTPack<>("block_pos_list_list", AllNBTCell.BLOCK_POS.getListNBTCell().getListNBTCell());
    public static final IntPack MAX_BIND = new IntPack("max_bind_tag");
    public static final IntPack MAX_RANGE = new IntPack("max_range_tag");
    public static final NBTPack<Map<BindType, List<BlockPos>>> BIND_TYPE_BLOCK_POS_LIST_MAP = new NBTPack<>("bind_type_block_pos_list_map", AllNBTCell.BIND_TYPE_LIST);
    public static final NBTPack<List<ShapedHandle>> SHAPED_HANDLE_LIST = new NBTPack<>("shaped_handle_list", AllNBTCell.SHAPED_HANDLE.getListNBTCell());
    public static final NBTPack<ManaLevel> MANA_LEVEL = new NBTPack<>("mana_level", AllNBTCell.MANA_LEVEL);
    public static final NBTPack<List<ShapedDrive>> SHAPED_DRIVE_LIST = new NBTPack<>("shaped_drive_list", AllNBTCell.SHAPED_DRIVE.getListNBTCell());
    public static final IntPack MAX_PARALLEL = new IntPack("max_parallel");
    public static final DoublePack PROBABILITY = new DoublePack("probability");
    public static final IntPack MB = new IntPack("mb");
    public static final IntPack MAX_AMOUNT = new IntPack("max_amount");
    public static final NBTPack<List<ItemStack>> ITEM_STACK_LIST_TAG = new NBTPack<>("item_stack_list", AllNBTCell.ITEM_STACK.getListNBTCell());
    public static final NBTPack<List<FluidStack>> FLUID_STACK_LIST_TAG = new NBTPack<>("fluid_stack_list", AllNBTCell.FLUID_STATE.getListNBTCell());
    public static final LongPack COUNT = new LongPack("count");
    public static final BooleanPack IS_VOID_CASE_ITEM_HANDLER = new BooleanPack("is_void_case_item_handle");
    public static final NBTPack<Class<?>> TYPE = new NBTPack<>("type", AllNBTCell.CLASS);
    public static final NBTPack<ResourceLocation> NAME = new NBTPack<>("name", AllNBTCell.RESOURCE_LOCATION);
    public static final NBTPack<ShapedType> SHAPED_TYPE = new NBTPack<>("shaped_type", AllNBTCell.SHAPED_TYPE);
    public static final NBTPack<ShapedDrive> SHAPED_DRIVE = new NBTPack<>("shaped_drive", AllNBTCell.SHAPED_DRIVE);
    public static final NBTPack<TagKey<Item>> ITEM_TAG = new NBTPack<>("item_tag", AllNBTCell.ITEM_TAG);
    public static final NBTPack<TagKey<Fluid>> FLUID_TAG = new NBTPack<>("fluid_tag", AllNBTCell.FLUID_TAG);
    public static final NBTPack<List<TagKey<Item>>> ITEM_TAG_LIST = new NBTPack<>("item_tag_list", AllNBTCell.ITEM_TAG.getListNBTCell());
    public static final NBTPack<List<TagKey<Fluid>>> FLUID_TAG_LIST = new NBTPack<>("fluid_tag_list", AllNBTCell.FLUID_TAG.getListNBTCell());
    public static final NBTPack<Integer> AMOUNT= new NBTPack("amount", AllNBTCell.INT);
    public static final NBTPack<Integer> AMOUNT_LIST = new NBTPack("amount_list_tag", AllNBTCell.INT.getListNBTCell());
    public static final NBTPack<List<Double>> PROBABILITY_LIST = new NBTPack("probability_list", AllNBTCell.DOUBLE.getListNBTCell());
    public static final NBTPack<Map<TagKey<Item>, Integer>> ITEM_IN_MAP = new NBTPack<>("item_in_map", AllNBTCell.ITEM_TAG_INT_MAP);
    public static final NBTPack<Map<TagKey<Fluid>, Integer>> FLUID_IN_MAP = new NBTPack<>("fluid_in_map", AllNBTCell.FLUID_TAG_INT_MAP);
    public static final NBTPack<Map<ItemStack, Double>> ITEM_OUT_MAP = new NBTPack<>("item_out_map", AllNBTCell.ITEM_STACK_DOUBLE_MAP);
    public static final NBTPack<Map<FluidStack, Double>> FLUID_OUT_MAP = new NBTPack<>("fluid_out_map", AllNBTCell.FLUID_STACK_DOUBLE_MAP);
    public static final NBTPack<TagKey<Item>> ITEM_SCREEN  = new NBTPack<>("item_screen", AllNBTCell.ITEM_TAG);
    public static final NBTPack<TagKey<Fluid>> FLUID_SCREEN  = new NBTPack<>("fluid_screen", AllNBTCell.FLUID_TAG);
    public static final NBTPack<Map<Skill, ISkill.SkillCell>> SKILL_SKILL_CELL_MAP = new NBTPack<>("skill_skill_cell", AllNBTCell.SKILL_SKILL_CELL_MAP);

    public static final IntPack ORIGINAL_LEVEL = new IntPack("originalLevel");
    public static final IntPack LEVEL = new IntPack("level");
    public static final IntPack CD = new IntPack("CD");
    public static final NBTPack<CompoundTag> NBT = new NBTPack<>("nbt", AllNBTCell.NBT);

    public static final NBTPack<AttributeModifier> ATTRIBUTE_MODIFIER = new NBTPack<>("attribute_modifier", AllNBTCell.ATTRIBUTE_MODIFIER);
    public static final NBTPack<Map<Attribute, List<AttributeModifier>>> ATTRIBUTE_ATTRIBUTE_MODIFIER_LIST_MAP = new NBTPack<>("attribute_attribute_modifier_list_map", AllNBTCell.ATTRIBUTE_LIST_NBT_MAP);
    public static final IntPack ENERGY = new IntPack("energy");
    public static final IntPack MAX_ENERGY = new IntPack("max_energy");
    public static final IntPack RATE_ENERGY = new IntPack("rate_energy");
    public static final IntPack BASICS_OUT = new IntPack("basics_out");

}
