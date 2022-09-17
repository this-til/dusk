package com.til.dusk.common.register.shaped.shapeds;

import com.google.gson.JsonObject;
import com.til.dusk.common.capability.handle.IHandle;
import com.til.dusk.common.capability.handle.ShapedHandle;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.util.nbt.pack.AllNBTPack;
import com.til.dusk.util.Lang;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;
import java.util.*;

/**
 * @author til
 */
public abstract class Shaped {

    /***
     * 类型找寻
     */
    public static final Map<ShapedType, Map<ShapedDrive, List<Shaped>>> MAP = new HashMap<>();

    private static int shapedId = 0;

    public final String name;

    /***
     * 配方类型
     */
    public final ShapedType shapedType;

    /***
     * 配方id
     */
    public final ShapedDrive shapedDrive;

    /***
     * 配方加工等级
     */
    public final ManaLevel manaLevel;


    public Shaped(ShapedType shapedType, ShapedDrive shapedDrive, ManaLevel manaLevel) {
        name = String.valueOf(shapedId++);
        this.shapedType = shapedType;
        this.shapedDrive = shapedDrive;
        this.manaLevel = manaLevel;
        add(this);
    }

    /***
     * 加载数据时调用
     * 此调用不会自动注册
     * @param name json文件限定名
     * @param jsonObject json文件内容
     */
    public Shaped(ResourceLocation name, JsonObject jsonObject) {
        String[] strings = name.getPath().split("/");
        this.shapedType = ShapedType.SHAPED_TYPE.get().getValue(new ResourceLocation(name.getNamespace(), strings[0]));
        this.shapedDrive = ShapedDrive.get(Integer.parseInt(strings[1]));
        manaLevel = AllNBTPack.MANA_LEVEL.get(jsonObject);
        this.name = strings[2];
    }


    /***
     * 将数据写入nbt，此nbt预先写入类等信息
     * 如果返回空间忽视
     * @param jsonObject nbt
     */
    @Nullable
    public JsonObject writ(JsonObject jsonObject) {
        AllNBTPack.MANA_LEVEL.set(jsonObject, manaLevel);
        return jsonObject;
    }

    @Nullable
    public abstract ShapedHandle get(IHandle iControl, Map<IPosTrack, IItemHandler> items, Map<IPosTrack, IFluidHandler> fluids);

    /***
     * 获取JEI配方
     */
    public abstract IJEIShaped getJEIShaped();

    /***
     * 获取JEI描述
     */
    public List<Component> getComponent() {
        List<Component> componentList = new ArrayList<>();
        componentList.add(Component.literal("message"));
        componentList.add(Lang.getLang(Component.translatable(Lang.getKey("需要灵压等级")), Component.translatable(Lang.getKey(manaLevel))));
        componentList.add(Lang.getLang(Component.translatable(Lang.getKey("需要配方集")), Component.literal(shapedDrive.getLangKey())));
        return componentList;
    }

    /***
     * 在JEI中显示
     */
    public boolean isJEIShow() {
        return true;
    }

    public interface IJEIShaped {

        IJEIShaped EMPTY = new IJEIShaped() {
        };

        @Nullable
        default List<List<ItemStack>> getItemIn() {
            return null;
        }

        @Nullable
        default List<List<FluidStack>> getFluidIn() {
            return null;
        }

        @Nullable
        default List<List<ItemStack>> getItemOut() {
            return null;
        }

        @Nullable
        default List<List<FluidStack>> getFluidOut() {
            return null;
        }

    }

    @Override
    public String toString() {
        return String.valueOf(name);
    }

    /***
     * 通过名字找寻
     */
    public static final Map<String, Shaped> ID_MAP = new HashMap<>();

    public static void add(Shaped shaped) {
        MAP.computeIfAbsent(shaped.shapedType, k -> new HashMap<>(8)).computeIfAbsent(shaped.shapedDrive, k -> new ArrayList<>()).add(shaped);
        ID_MAP.put(shaped.name, shaped);
    }

    public static List<Shaped> get(ShapedType... s) {
        List<Shaped> list = new ArrayList<>();
        for (ShapedType shapedType : s) {
            if (MAP.containsKey(shapedType)) {
                MAP.get(shapedType).values().forEach(list::addAll);
            }
        }
        return list;
    }
}
