package com.til.dusk.common.register.shaped.shapeds;

import com.til.dusk.Dusk;
import com.til.dusk.common.capability.handle.IHandle;
import com.til.dusk.common.capability.handle.ShapedHandle;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.config.ConfigKey;
import com.til.dusk.common.config.ConfigMap;
import com.til.dusk.common.config.IAcceptConfig;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.util.nbt.cell.AllNBTCell;
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
public abstract class Shaped implements IAcceptConfig {


    public static final ConfigKey<ResourceLocation> NAME = new ConfigKey<>("shaped.name", AllNBTCell.RESOURCE_LOCATION, () -> new ResourceLocation(Dusk.MOD_ID, "null"));
    public static final ConfigKey<ShapedType> SHAPED_TYPE = new ConfigKey<>("shaped.shaped_type", AllNBTCell.SHAPED_TYPE, () -> ShapedType.empty);
    public static final ConfigKey<ShapedDrive> SHAPED_DRIVE = new ConfigKey<>("shaped.shaped_drive", AllNBTCell.SHAPED_DRIVE, () -> ShapedDrive.get(0));
    public static final ConfigKey<ManaLevel> MANA_LEVEL = new ConfigKey<>("shaped.mana_level", AllNBTCell.MANA_LEVEL, () -> ManaLevel.t1);
    public static final ConfigKey<Boolean> IS_SHOW = new ConfigKey<>("shaped.is_show", AllNBTCell.BOOLEAN, () -> true);

    /***
     * 配方名称
     */
    public ResourceLocation name;

    /***
     * 配方类型
     */
    public ShapedType shapedType;

    /***
     * 配方序列
     */
    public ShapedDrive shapedDrive;

    /***
     * 配方加工等级
     */
    public ManaLevel manaLevel;

    /***
     * 显示
     */
    public boolean isShow = true;

    public Shaped(){}

    public Shaped(ResourceLocation name, ShapedType shapedType, ShapedDrive shapedDrive, ManaLevel manaLevel) {
        this.name = name;
        this.shapedType = shapedType;
        this.shapedDrive = shapedDrive;
        this.manaLevel = manaLevel;
    }

    @Override
    public void init(ConfigMap configMap) {
        name = configMap.get(NAME);
        shapedType = configMap.get(SHAPED_TYPE);
        manaLevel = configMap.get(MANA_LEVEL);
        shapedDrive = configMap.get(SHAPED_DRIVE);
        isShow = configMap.get(IS_SHOW);
    }

    @Override
    public ConfigMap defaultConfigMap() {
        return new ConfigMap()
                .setConfigOfV(NAME, name)
                .setConfigOfV(SHAPED_TYPE, shapedType)
                .setConfigOfV(MANA_LEVEL, manaLevel)
                .setConfigOfV(SHAPED_DRIVE, shapedDrive)
                .setConfigOfV(IS_SHOW, isShow);
    }

    /***
     * 通过物品筛选，通过筛选将进入全匹配
     */
    public abstract boolean screenOfItem(ItemStack itemStack);

    /***
     * 通过流体筛选，优先于物品
     */
    public abstract boolean screenOfFluid(FluidStack fluidStack);

    @Nullable
    public abstract ShapedHandle get(IHandle iHandle, @Nullable Map.Entry<IPosTrack, IItemHandler> items, Map<IPosTrack, IFluidHandler> fluids);

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
        componentList.add(Lang.getLang(Component.translatable(Lang.getKey("需要灵压等级")), Component.translatable(manaLevel.name.toLanguageKey())));
        componentList.add(Lang.getLang(Component.translatable(Lang.getKey("需要配方集")), Component.literal(shapedDrive.name.getPath())));
        return componentList;
    }

    /***
     * 在JEI中显示
     */
    public boolean isJEIShow() {
        return isShow;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Shaped shaped = (Shaped) o;
        return Objects.equals(name, shaped.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public Shaped setShow(boolean show) {
        isShow = show;
        return this;
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
        return "Shaped{" +
               "name=" + name +
               ", shapedType=" + shapedType +
               ", shapedDrive=" + shapedDrive +
               ", manaLevel=" + manaLevel +
               '}';
    }

    public static Set<Shaped> get(ShapedType... s) {
        Set<Shaped> list = new HashSet<>();
        for (ShapedType shapedType : s) {
            if (MAP.containsKey(shapedType)) {
                MAP.get(shapedType).values().forEach(list::addAll);
            }
        }
        return list;
    }
}
