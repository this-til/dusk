package com.til.dusk.common.register.tag_tool;

import com.til.dusk.Dusk;
import com.til.dusk.common.capability.handle.ShapedHandle;
import com.til.dusk.common.register.BindType;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.ShapedHandleProcess;
import com.til.dusk.util.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Supplier;

/**
 * Tag操作工具
 *
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public abstract class TagTool<V> extends RegisterBasics<TagTool<V>> {
    public static Supplier<IForgeRegistry<TagTool<?>>> TAG_TOOL;

    public static RegisterItemTool<Item> itemTag;
    public static PackTag<Item> itemPackTag;
    public static TagTool<ItemStack> itemStackTag;
    public static RegisterItemTool<Fluid> fluidTag;
    public static PackTag<Fluid> fluidPackTag;
    public static TagTool<FluidStack> fluidStackTag;
    public static TagTool<BlockPos> blockPosTag;

    public static TagTool<Color> colorSeparate;

    public static IntTag colorTag;
    public static LongTag surplusTimeTag;
    public static LongTag consumeManaTag;
    public static LongTag _surplusTimeTag;
    public static LongTag outManaTag;
    public static RegisterItemTool<ShapedHandleProcess> processTag;
    public static ListTag<ItemStack> outItemTag;
    public static ListTag<FluidStack> outFluidTag;
    public static TagTool<ShapedHandle> shapedHandleTag;
    public static LongTag manaTag;
    public static LongTag maxManaTag;
    public static LongTag rateTag;
    public static IntTag timeTag;
    public static IntTag cycleTimeTag;
    public static RegisterItemListTool<BindType> bindTypeListTag;

    public static RegisterItemTool<BindType> bindTypeTag;
    public static ListTag<BlockPos> blockPosListTag;
    public static ListTag<List<BlockPos>> blockPosListListTag;
    public static IntTag maxBindTag;
    public static IntTag maxRangeTag;
    public static MapTag<BindType, List<BlockPos>> bindType_BlockPosListMapTag;
    public static ListTag<ShapedHandle> shapedHandleListTag;
    public static RegisterItemTool<ManaLevel> manaLevelTag;
    public static RegisterItemListTool<ShapedDrive> shapedDriveListTag;
    public static IntTag maxParallelTag;
    public static DoubleTag probabilityTag;
    public static IntTag mBTag;
    public static ListTag<ItemStack> itemStackListTag;
    public static ListTag<FluidStack> fluidStackListTag;
    public static LongTag count;
    public static BooleanTag isVoidCaseItemHandlerTag;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        TAG_TOOL = event.create(new RegistryBuilder<TagTool<?>>().setName(new ResourceLocation(Dusk.MOD_ID, "nbt_tool")));
        itemTag = new RegisterItemTool<>("item_tag", () -> ForgeRegistries.ITEMS);
        itemPackTag = new PackTag<>("item_pack_tag", itemTag);
        itemStackTag = new TagTool<>("item_stack_tag") {
            @Override
            public ItemStack get(CompoundTag nbt) {
                return ItemStack.of(nbt);
            }

            @Override
            public void set(CompoundTag nbt, ItemStack stack) {
                copy(nbt, stack.serializeNBT());
            }
        };
        fluidTag = new RegisterItemTool<>("fluid_tag", () -> ForgeRegistries.FLUIDS);
        fluidPackTag = new PackTag<>("fluid_pack_tag", fluidTag);
        fluidStackTag = new TagTool<>("fluid_stack") {
            @Override
            public FluidStack get(CompoundTag nbt) {
                return FluidStack.loadFluidStackFromNBT(nbt);
            }

            @Override
            public void set(CompoundTag nbt, FluidStack fluidStack) {
                fluidStack.writeToNBT(nbt);
            }
        };
        blockPosTag = new TagTool<>("block_pos_tag") {
            final String x = "x";
            final String y = "y";
            final String z = "z";

            public BlockPos get(CompoundTag nbt) {
                return new BlockPos(nbt.getInt(x), nbt.getInt(y), nbt.getInt(z));
            }

            @Override
            public void set(CompoundTag nbt, BlockPos blockPosTag) {
                nbt.putInt(x, blockPosTag.getX());
                nbt.putInt(y, blockPosTag.getY());
                nbt.putInt(z, blockPosTag.getZ());
            }
        };
        colorSeparate = new TagTool<Color>("color_separate") {

            final String r = "r";
            final String g = "g";
            final String b = "b";
            final String a = "a";

            @Override
            public Color get(CompoundTag nbt) {
                return new Color(nbt.getInt(r), nbt.getInt(g), nbt.getInt(b), nbt.getInt(a));
            }

            @Override
            public void set(CompoundTag nbt, Color color) {
                nbt.putInt(r, color.getRed());
                nbt.putInt(g, color.getGreen());
                nbt.putInt(b, color.getBlue());
                nbt.putInt(a, color.getAlpha());
            }
        };
        colorTag = new IntTag("color_tag");
        surplusTimeTag = new LongTag("surplus_time_tag");
        consumeManaTag = new LongTag("consume_mana_tag");
        _surplusTimeTag = new LongTag("_surplus_time_tag");
        processTag = new RegisterItemTool<>("process_tag", () -> ShapedHandleProcess.SHAPED_TYPE_PROCESS.get());
        outItemTag = new ListTag<>("out_item_tag", itemStackTag);
        outFluidTag = new ListTag<>("out_fluid_tag", fluidStackTag);
        outManaTag = new LongTag("out_mana");
        shapedHandleTag = new TagTool<>("shaped_handle_tag") {
            @Override
            public ShapedHandle get(CompoundTag nbt) {
                ShapedHandle shapedHandle = new ShapedHandle(surplusTimeTag.get(nbt), consumeManaTag.get(nbt), outManaTag.get(nbt), outItemTag.get(nbt), outFluidTag.get(nbt));
                shapedHandle._surplusTime = _surplusTimeTag.get(nbt);
                shapedHandle.process = processTag.get(nbt);
                if (shapedHandle.process == null) {
                    shapedHandle.process = ShapedHandleProcess.trippingOperation;
                }
                return shapedHandle;
            }

            @Override
            public void set(CompoundTag nbt, ShapedHandle shapedHandle) {
                surplusTimeTag.set(nbt, shapedHandle.surplusTime);
                consumeManaTag.set(nbt, shapedHandle.consumeMana);
                if (shapedHandle.outItem != null) {
                    outItemTag.set(nbt, shapedHandle.outItem);
                }
                if (shapedHandle.outFluid != null) {
                    outFluidTag.set(nbt, shapedHandle.outFluid);
                }
                outManaTag.set(nbt, shapedHandle.outMana);
                processTag.set(nbt, shapedHandle.process);
                _surplusTimeTag.set(nbt, shapedHandle._surplusTime);
            }
        };
        manaTag = new LongTag("mana_tag_tag");
        maxManaTag = new LongTag("max_mana_tag");
        rateTag = new LongTag("rate_tag");
        timeTag = new IntTag("time_tag_tag");
        cycleTimeTag = new IntTag("cycle_time_tag");
        bindTypeListTag = new RegisterItemListTool<>("bind_type_list_tag", () -> BindType.BIND_TYPE.get());
        bindTypeTag = new RegisterItemTool<BindType>("bind_type_tag", () -> BindType.BIND_TYPE.get());
        blockPosListTag = new ListTag<>("block_pos_list_tag", blockPosTag);
        blockPosListListTag = new ListTag<>("block_pos_list_list_tag", blockPosListTag);
        maxBindTag = new IntTag("max_bind_tag");
        maxRangeTag = new IntTag("max_range_tag");
        bindType_BlockPosListMapTag = new MapTag<>("bind_type_block_pos_list_map_tag", bindTypeListTag, blockPosListListTag);
        shapedHandleListTag = new ListTag<>("shaped_handle_list_tag", shapedHandleTag);
        manaLevelTag = new RegisterItemTool<>("mana_level_tag", () -> ManaLevel.LEVEL.get());
        shapedDriveListTag = new RegisterItemListTool<>("shaped_drive_list_tag", () -> ShapedDrive.SHAPED_DRIVE.get());
        maxParallelTag = new IntTag("max_parallel_tag");
        mBTag = new IntTag("mb_tag");
        probabilityTag = new DoubleTag("probability_tag");
        itemStackListTag = new ListTag<>("item_stack_list_tag", itemStackTag);
        fluidStackListTag = new ListTag<>("fluid_stack_list_tag", fluidStackTag);
        count = new LongTag("count");
        isVoidCaseItemHandlerTag = new BooleanTag("is_void_case_item_handler_tag");
    }

    public final String tagName;

    public TagTool(ResourceLocation name) {
        super(name, Util.forcedConversion(TAG_TOOL));
        tagName = name.toString();
    }

    public TagTool(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    public boolean contains(CompoundTag compoundTag) {
        return compoundTag.contains(tagName);
    }

    /***
     * 从Tag中获取数据
     * @param nbt nbt
     * @return 获取的数据
     */
    public abstract V get(CompoundTag nbt);

    /***
     * 将数据写入Tag
     * @param nbt 要写入的Tag
     * @param v 数据
     */
    public abstract void set(CompoundTag nbt, V v);


    public static void clear(CompoundTag compoundTag) {
        Set<String> strings = compoundTag.getAllKeys();
        if (strings.isEmpty()) {
            return;
        }
        for (String string : strings) {
            compoundTag.remove(string);
        }
    }

    public static void copy(CompoundTag old, CompoundTag compoundTag) {
        clear(old);
        Set<String> strings = compoundTag.getAllKeys();
        if (strings.isEmpty()) {
            return;
        }
        for (String string : strings) {
            Tag tag = compoundTag.get(string);
            if (tag == null) {
                continue;
            }
            old.put(string, tag);
        }
    }

}
