package com.til.dusk.util;


import com.til.dusk.Dusk;
import com.til.dusk.common.register.BindType;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.*;


public class AllNBT {

    public static final List<IGS<?>> ALL_NBT = new ArrayList<>();
    public static final List<IGS.IGSHashMap<?, ?>> ALL_NBT_HASH_MAP = new ArrayList<>();

    public static final IGS<Long> modMana = new IGS.LongNBT("modMana");
    public static final IGS<ListTag> listBlockPosNBTLong = new IGS.ListNBT("listBlockPosNBTLong");
    public static final IGS<List<BlockPos>> blockPos = new IGS.ListBlockPosNBT("blockPos");

    public static final IGS<Tag> iManaHandleNBT = new IGS.NBTNBT("iManaHandleNBT");
    public static final IGS<Tag> iControlNBT = new IGS.NBTNBT("iControlNBT");
    public static final IGS<Tag> iHandleNBT = new IGS.NBTNBT("iHandleNBT");
    public static final IGS<Tag> iClockTime = new IGS.NBTNBT("iClockTime");
    public static final IGS<Tag> voidCylinderHandler = new IGS.NBTNBT("voidCylinderHandler");
    public static final IGS<Tag> voidCaseHandler = new IGS.NBTNBT("voidCaseHandler");
    public static final IGS<Map<BindType, List<BlockPos>>> controlBindBlock = new IGS.BasicsNBT<>("controlBindBlock") {
        @Override
        public Map<BindType, List<BlockPos>> get(CompoundTag nbt) {
            Map<BindType, List<BlockPos>> map = new HashMap<>();

            for (Tag nbtBase : nbt.getList(getName(), 10)) {
                if (nbtBase instanceof CompoundTag nbtTagCompound) {
                    BindType k = BindType.BIND_TYPE.get().getValue(new ResourceLocation(nbtTagCompound.getString("k")));
                    List<BlockPos> v = new ArrayList<>();
                    for (Tag pos : nbtTagCompound.getList("v", 10)) {
                        if (pos instanceof CompoundTag _pos) {
                            v.add(new BlockPos(_pos.getInt("x"), _pos.getInt("y"), _pos.getInt("z")));
                        }
                    }
                    map.put(k, v);
                }
            }

            return map;
        }

        @Override
        public void set(CompoundTag nbt, Map<BindType, List<BlockPos>> bundTypeListMap) {
            ListTag mList = new ListTag();
            bundTypeListMap.forEach((k, v) -> {
                CompoundTag kv = new CompoundTag();
                kv.putString("k", k.name.toString());
                ListTag vList = new ListTag();
                for (BlockPos pos : v) {
                    CompoundTag nbtTagCompound = new CompoundTag();
                    nbtTagCompound.putInt("x", pos.getX());
                    nbtTagCompound.putInt("y", pos.getY());
                    nbtTagCompound.putInt("z", pos.getZ());
                    vList.add(nbtTagCompound);
                }
                kv.put("v", vList);
                mList.add(kv);
            });
            nbt.put(getName(), mList);

        }
    };

    public interface IGS<V> {

        V get(CompoundTag nbt);

        void set(CompoundTag nbt, V v);

        String getName();

        boolean deathRetainData();

        abstract class BasicsNBT<V> implements IGS<V> {

            String name;

            public BasicsNBT(String name) {
                this.name = Dusk.MOD_ID + "_" + name;
                AllNBT.ALL_NBT.add(this);
            }

            @Override
            public String getName() {
                return this.name;
            }

            @Override
            public boolean deathRetainData() {
                return true;
            }

        }

        interface IGSList<V> extends IGS<List<V>> {

            V getV(CompoundTag nbt, int k);

            void setV(CompoundTag nbt, int k, V v);

            /***
             * 返回改数据的列表长度
             */
            int getLong();

            abstract class BasicsListNBT<V> implements IGSList<V> {

                String name;

                public BasicsListNBT(String name) {
                    this.name = Dusk.MOD_ID + "_" + name;
                    AllNBT.ALL_NBT.add(this);
                }

                @Override
                public String getName() {
                    return this.name;
                }

                @Override
                public boolean deathRetainData() {
                    return true;
                }

            }

        }

        interface IGSHashMap<K, V> extends IGS<Map<K, V>> {

            V getV(CompoundTag nbt, K k);

            void setV(CompoundTag nbt, K k, V v);

            /***
             * 返回改数据的列表长度
             */
            int getLong();

            abstract class BasicsHashMapNBT<K, V> implements IGSHashMap<K, V> {

                String name;

                public BasicsHashMapNBT(String name) {
                    this.name = Dusk.MOD_ID + "_" + name;
                    AllNBT.ALL_NBT.add(this);
                }

                @Override
                public String getName() {
                    return this.name;
                }

                @Override
                public boolean deathRetainData() {
                    return true;
                }

            }

        }

        class IntNBT extends BasicsNBT<Integer> {

            public IntNBT(String name) {
                super(name);
            }

            @Override
            public Integer get(CompoundTag nbt) {
                return nbt.getInt(name);
            }

            @Override
            public void set(CompoundTag nbt, Integer v) {
                nbt.putInt(name, v);
            }
        }

        class LongNBT extends BasicsNBT<Long> {

            public LongNBT(String name) {
                super(name);
            }

            @Override
            public Long get(CompoundTag nbt) {
                return nbt.getLong(name);
            }

            @Override
            public void set(CompoundTag nbt, Long v) {
                nbt.putLong(name, v);
            }
        }

        class StringNBT extends BasicsNBT<String> {

            public StringNBT(String name) {
                super(name);
            }

            @Override
            public String get(CompoundTag nbt) {
                return nbt.getString(name);
            }

            @Override
            public void set(CompoundTag nbt, String s) {
                if (s != null) {
                    nbt.putString(name, s);
                }
            }
        }

        class DoubleNBT extends BasicsNBT<Double> {

            public DoubleNBT(String name) {
                super(name);
            }

            @Override
            public Double get(CompoundTag nbt) {
                return nbt.getDouble(name);
            }

            @Override
            public void set(CompoundTag nbt, Double aDouble) {
                nbt.putDouble(name, aDouble);
            }
        }

        class BooleanNBT extends BasicsNBT<Boolean> {

            public BooleanNBT(String name) {
                super(name);
            }

            @Override
            public Boolean get(CompoundTag nbt) {
                return nbt.getBoolean(name);
            }

            @Override
            public void set(CompoundTag nbt, Boolean v) {
                nbt.putBoolean(name, v);
            }
        }

        class HashMapIntNBT extends IGSHashMap.BasicsHashMapNBT<Integer, Integer> {

            @Override
            public Map<Integer, Integer> get(CompoundTag nbt) {
                Map<Integer, Integer> hashMap = new HashMap<>();
                int l = this.getLong() + 1;
                for (int i = 1; i < l; i++) {
                    hashMap.put(i, nbt.getInt(name + i));
                }
                return hashMap;
            }

            @Override
            public void set(CompoundTag nbt, Map<Integer, Integer> integerIntegerHashMap) {
                int i = 1;
                for (Integer integer : integerIntegerHashMap.keySet()) {
                    nbt.putInt(name + i, integer);
                    i++;
                }
            }

            public HashMapIntNBT(String name) {
                super(name);
                AllNBT.ALL_NBT_HASH_MAP.add(this);
            }

            @Override
            public Integer getV(CompoundTag nbt, Integer integer) {
                return nbt.getInt(name + integer);
            }

            @Override
            public void setV(CompoundTag nbt, Integer integer, Integer integer2) {
                nbt.putInt(name + integer, integer2);
            }

            @Override
            public int getLong() {
                return 10;
            }

        }

        class ListBlockPosNBT extends BasicsNBT<List<BlockPos>> {

            public static final String X = Dusk.MOD_ID + "_x";
            public static final String Y = Dusk.MOD_ID + "_y";
            public static final String Z = Dusk.MOD_ID + "_z";

            public ListBlockPosNBT(String name) {
                super(name);
            }

            @Override
            public List<BlockPos> get(CompoundTag nbt) {
                List<BlockPos> blockPos = new ArrayList<>();
                ListTag nbtTagList = AllNBT.listBlockPosNBTLong.get(nbt);
                for (Tag tag : nbtTagList) {
                    if (tag instanceof CompoundTag blockPosNBT) {
                        blockPos.add(new BlockPos(blockPosNBT.getInt(X), blockPosNBT.getInt(Y), blockPosNBT.getInt(Z)));
                    }

                }
                return blockPos;
            }

            @Override
            public void set(CompoundTag nbt, List<BlockPos> blockPos) {
                ListTag nbtTagList = new ListTag();
                for (BlockPos blockPo : blockPos) {
                    CompoundTag itemNBT = new CompoundTag();
                    itemNBT.putInt(X, blockPo.getX());
                    itemNBT.putInt(Y, blockPo.getY());
                    itemNBT.putInt(Z, blockPo.getZ());
                    nbtTagList.add(itemNBT);
                }
                AllNBT.listBlockPosNBTLong.set(nbt, nbtTagList);
            }
        }

        class ListNBT extends BasicsNBT<ListTag> {

            public ListNBT(String name) {
                super(name);
            }

            @Override
            public ListTag get(CompoundTag nbt) {
                return nbt.getList(name, 10);
            }

            @Override
            public void set(CompoundTag nbt, ListTag nbtBases) {
                nbt.put(name, nbtBases);
            }
        }

        class NBTNBT extends BasicsNBT<Tag> {

            public NBTNBT(String name) {
                super(name);
            }

            @Override
            public Tag get(CompoundTag nbt) {
                return nbt.get(name);
            }

            @Override
            public void set(CompoundTag nbt, Tag nbtBases) {
                nbt.put(name, nbtBases);
            }
        }

        /*class ListItemStackNBT extends BasicsNBT<List<ItemStack>> {

            public ListItemStackNBT(String name) {
                super(name);
            }

            @Override
            public List<ItemStack> get(NBTTagCompound nbt) {
                List<ItemStack> stacks = new List<>();
                NBTTagList nbtTagList = AllNBT.listItemStackNBTLong.get(nbt);
                for (int i = 0; i < nbtTagList.tagCount(); i++) {
                    NBTTagCompound itemNBT = nbtTagList.getCompoundTagAt(i);
                    stacks.add(new ItemStack(itemNBT));
                }
                return stacks;
            }

            @Override
            public void set(NBTTagCompound nbt, List<ItemStack> itemStacks) {
                NBTTagList nbtTagList = new NBTTagList();
                for (ItemStack itemStack : itemStacks) {
                    NBTTagCompound itemNBT = new NBTTagCompound();
                    nbtTagList.appendTag(itemStack.writeToNBT(itemNBT));
                }
                AllNBT.listItemStackNBTLong.set(nbt, nbtTagList);
            }
        }*/

        class ItemNBT extends BasicsNBT<Item> {

            public ItemNBT(String name) {
                super(name);
            }

            @Override
            public Item get(CompoundTag nbt) {
                return Item.byId(nbt.getInt(name));
            }

            @Override
            public void set(CompoundTag nbt, Item item) {
                nbt.putInt(name, Item.getId(item));
            }
        }

        /*class StringName extends BasicsNBT<List<String>> {

            public StringName(String name) {
                super(name);
            }

            @Override
            public List<String> get(NBTTagCompound nbt) {
                List<String> strings = new List<>();
                NBTTagList nbtTagList = AllNBT.stringNBTList.get(nbt);
                for (int i = 0; i < nbtTagList.tagCount(); i++) {
                    NBTTagCompound nbtl = nbtTagList.getCompoundTagAt(i);
                    strings.add(nbtl.getString(name));
                }
                return strings;
            }

            @Override
            public void set(NBTTagCompound nbt, List<String> strings) {
                NBTTagList nbtTagList = new NBTTagList();
                for (String s : strings) {
                    NBTTagCompound nbtl = new NBTTagCompound();
                    nbtl.setString(name, s);
                    nbtTagList.appendTag(nbtl);
                }
                AllNBT.stringNBTList.set(nbt, nbtTagList);
            }

        }*/

    }

}
