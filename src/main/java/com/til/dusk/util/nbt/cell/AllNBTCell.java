package com.til.dusk.util.nbt.cell;

import com.til.dusk.Dusk;
import com.til.dusk.common.capability.handle.ShapedHandle;
import com.til.dusk.common.register.BindType;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.ShapedHandleProcess;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.util.nbt.pack.AllNBTPack;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class AllNBTCell {

    public static final NBTCell<NumericTag, Integer> INT = new NBTCell<>() {
        @Override
        public NumericTag as(Integer integer) {
            return IntTag.valueOf(integer);
        }

        @Override
        public Integer from(NumericTag numericTag) {
            return numericTag.getAsInt();
        }

        @Override
        public byte getaNBTId() {
            return 3;
        }
    };
    public static final NBTCell<NumericTag, Double> DOUBLE = new NBTCell<>() {
        @Override
        public NumericTag as(Double aDouble) {
            return DoubleTag.valueOf(aDouble);
        }

        @Override
        public Double from(NumericTag numericTag) {
            return numericTag.getAsDouble();
        }

        @Override
        public byte getaNBTId() {
            return 6;
        }
    };
    public static final NBTCell<NumericTag, Float> FLOAT = new NBTCell<>() {
        @Override
        public NumericTag as(Float aFloat) {
            return FloatTag.valueOf(aFloat);
        }

        @Override
        public Float from(NumericTag numericTag) {
            return numericTag.getAsFloat();
        }

        @Override
        public byte getaNBTId() {
            return 5;
        }
    };
    public static final NBTCell<NumericTag, Long> LONG = new NBTCell<>() {
        @Override
        public NumericTag as(Long aLong) {
            return LongTag.valueOf(aLong);
        }

        @Override
        public Long from(NumericTag numericTag) {
            return numericTag.getAsLong();
        }

        @Override
        public byte getaNBTId() {
            return 4;
        }
    };
    public static final NBTCell<StringTag, String> STRING = new NBTCell<>() {
        @Override
        public StringTag as(String s) {
            return StringTag.valueOf(s);
        }

        @Override
        public String from(StringTag stringTag) {
            return stringTag.getAsString();
        }

        @Override
        public byte getaNBTId() {
            return 8;
        }
    };
    public static final NBTCell<NumericTag, Boolean> BOOLEAN = new NBTCell<>() {
        @Override
        public NumericTag as(Boolean aBoolean) {
            return ByteTag.valueOf(aBoolean);
        }

        @Override
        public Boolean from(NumericTag numericTag) {
            return numericTag.getAsByte() != 0;
        }

        @Override
        public byte getaNBTId() {
            return 1;
        }
    };
    public static final NBTCell<StringTag, Class<?>> CLASS = new NBTCell<>() {
        @Override
        public StringTag as(Class<?> aClass) {
            return StringTag.valueOf(aClass.getName());
        }

        @Override
        public Class<?> from(StringTag stringTag) {
            try {
                return Class.forName(stringTag.getAsString());
            } catch (ClassNotFoundException e) {
                return Object.class;
            }
        }

        @Override
        public byte getaNBTId() {
            return 8;
        }
    };
    public static final NBTCell<StringTag, ResourceLocation> RESOURCE_LOCATION = new NBTCell<>() {
        @Override
        public StringTag as(ResourceLocation resourceLocation) {
            return StringTag.valueOf(resourceLocation.toString());
        }

        @Override
        public ResourceLocation from(StringTag stringTag) {
            try {
                return new ResourceLocation(stringTag.getAsString());
            } catch (Exception e) {
                return new ResourceLocation(Dusk.MOD_ID, "null");
            }
        }

        @Override
        public byte getaNBTId() {
            return 8;
        }
    };

    public static final RegisterItemNBTCell<Item> ITEM = new RegisterItemNBTCell<>(() -> ForgeRegistries.ITEMS, () -> Items.AIR);
    public static final RegisterItemNBTCell<Block> BLOCK = new RegisterItemNBTCell<>(() -> ForgeRegistries.BLOCKS, () -> Blocks.AIR);
    public static final RegisterItemNBTCell<Fluid> FLUID = new RegisterItemNBTCell<>(() -> ForgeRegistries.FLUIDS, () -> Fluids.EMPTY);

    public static final RegisterItemNBTCell<ManaLevel> MANA_LEVEL = new RegisterItemNBTCell<>(() -> ManaLevel.LEVEL.get(), () -> ManaLevel.t1);
    public static final RegisterItemNBTCell<ShapedType> SHAPED_TYPE = new RegisterItemNBTCell<>(() -> ShapedType.SHAPED_TYPE.get(), () -> ShapedType.empty);
    public static final RegisterItemNBTCell<ShapedDrive> SHAPED_DRIVE = new RegisterItemNBTCell<>(() -> ShapedDrive.SHAPED_DRIVE.get(), () -> ShapedDrive.get(0));
    public static final RegisterItemNBTCell<BindType> BIND_TYPE = new RegisterItemNBTCell<>(() -> BindType.BIND_TYPE.get(), () -> BindType.itemOut);
    public static final RegisterItemNBTCell<ShapedHandleProcess> SHAPED_HANDLE_PROCESS = new RegisterItemNBTCell<>(() -> ShapedHandleProcess.SHAPED_TYPE_PROCESS.get(), () -> ShapedHandleProcess.trippingOperation);

    public static final TagKeyNBTCell<Item> ITEM_TAG = new TagKeyNBTCell<>(ForgeRegistries.ITEMS::tags);
    public static final TagKeyNBTCell<Block> BLOCK_TAG = new TagKeyNBTCell<>(ForgeRegistries.BLOCKS::tags);
    public static final TagKeyNBTCell<Fluid> FLUID_TAG = new TagKeyNBTCell<>(ForgeRegistries.FLUIDS::tags);

    public static final NBTCell<CompoundTag, ItemStack> ITEM_STACK = new NBTCell<>() {
        @Override
        public CompoundTag as(ItemStack itemStack) {
            return itemStack.serializeNBT();
        }

        @Override
        public ItemStack from(CompoundTag compoundTag) {
            return ItemStack.of(compoundTag);
        }

        @Override
        public byte getaNBTId() {
            return 10;
        }
    };
    public static final NBTCell<CompoundTag, FluidStack> FLUID_STATE = new NBTCell<>() {

        @Override
        public CompoundTag as(FluidStack fluidStack) {
            return fluidStack.writeToNBT(new CompoundTag());
        }

        @Override
        public FluidStack from(CompoundTag compoundTag) {
            return FluidStack.loadFluidStackFromNBT(compoundTag);
        }

        @Override
        public byte getaNBTId() {
            return 10;
        }
    };
    public static final NBTCell<CompoundTag, BlockPos> BLOCK_POS = new NBTCell<>() {

        final String X = "x";
        final String Y = "y";
        final String Z = "z";

        @Override
        public CompoundTag as(BlockPos blockPos) {
            CompoundTag compoundTag = new CompoundTag();
            compoundTag.putInt(X, blockPos.getX());
            compoundTag.putInt(Y, blockPos.getY());
            compoundTag.putInt(Z, blockPos.getZ());
            return compoundTag;
        }

        @Override
        public BlockPos from(CompoundTag compoundTag) {
            return new BlockPos(compoundTag.getInt(X), compoundTag.getInt(Y), compoundTag.getInt(Z));
        }

        @Override
        public byte getaNBTId() {
            return 10;
        }
    };
    public static final NBTCell<CompoundTag, Color> COLOR = new NBTCell<>() {

        final String r = "r";
        final String g = "g";
        final String b = "b";
        final String a = "a";

        @Override
        public CompoundTag as(Color color) {
            CompoundTag compoundTag = new CompoundTag();
            compoundTag.putInt(r, color.getRed());
            compoundTag.putInt(g, color.getGreen());
            compoundTag.putInt(b, color.getBlue());
            compoundTag.putInt(a, color.getAlpha());
            return compoundTag;
        }

        @Override
        public Color from(CompoundTag compoundTag) {
            return new Color(compoundTag.getInt(r), compoundTag.getInt(g), compoundTag.getInt(b), compoundTag.getInt(a));
        }

        @Override
        public byte getaNBTId() {
            return 10;
        }
    };
    public static final NBTCell<CompoundTag, ShapedHandle> SHAPED_HANDLE = new NBTCell<>() {

        @Override
        public CompoundTag as(ShapedHandle shapedHandle) {
            CompoundTag compoundTag = new CompoundTag();
            AllNBTPack.SURPLUS_TIME.set(compoundTag, shapedHandle.surplusTime);
            AllNBTPack.CONSUME_MANA.set(compoundTag, shapedHandle.consumeMana);
            if (shapedHandle.outItem != null) {
                AllNBTPack.OUT_ITEM.set(compoundTag, shapedHandle.outItem);
            }
            if (shapedHandle.outFluid != null) {
                AllNBTPack.OUT_FLUID.set(compoundTag, shapedHandle.outFluid);
            }
            AllNBTPack.OUT_MANA.set(compoundTag, shapedHandle.outMana);
            AllNBTPack.PROCESS.set(compoundTag, shapedHandle.process);
            AllNBTPack._SURPLUS_TIME.set(compoundTag, shapedHandle._surplusTime);
            return compoundTag;
        }

        @Override
        public ShapedHandle from(CompoundTag compoundTag) {
            ShapedHandle shapedHandle = new ShapedHandle(AllNBTPack.SURPLUS_TIME.get(compoundTag),
                    AllNBTPack.CONSUME_MANA.get(compoundTag), AllNBTPack.OUT_MANA.get(compoundTag),
                    AllNBTPack.OUT_ITEM.get(compoundTag), AllNBTPack.OUT_FLUID.get(compoundTag));
            shapedHandle._surplusTime = AllNBTPack._SURPLUS_TIME.get(compoundTag);
            shapedHandle.process = AllNBTPack.PROCESS.get(compoundTag);
            if (shapedHandle.process == null) {
                shapedHandle.process = ShapedHandleProcess.trippingOperation;
            }
            return shapedHandle;
        }


        @Override
        public byte getaNBTId() {
            return 10;
        }
    };

    public static final NBTMapCell<BindType, List<BlockPos>> BIND_TYPE_LIST = new NBTMapCell<>(BIND_TYPE.getListNBTCell(), BLOCK_POS.getListNBTCell().getListNBTCell());
    public static final NBTMapCell<TagKey<Item>, Integer> ITEM_TAG_INT_MAP = new NBTMapCell<>(ITEM_TAG.getListNBTCell(), INT.getListNBTCell());
    public static final NBTMapCell<TagKey<Fluid>, Integer> FLUID_TAG_INT_MAP = new NBTMapCell<>(FLUID_TAG.getListNBTCell(), INT.getListNBTCell());
    public static final NBTMapCell<ItemStack, Double> ITEM_STACK_DOUBLE_MAP = new NBTMapCell<>(ITEM_STACK.getListNBTCell(), DOUBLE.getListNBTCell());
    public static final NBTMapCell<FluidStack, Double> FLUID_STACK_DOUBLE_MAP = new NBTMapCell<>(FLUID_STATE.getListNBTCell(), DOUBLE.getListNBTCell());

}
