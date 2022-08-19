package com.til.dusk.register;

import com.til.dusk.Dusk;
import com.til.dusk.capability.IHandle;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public abstract class Shaped extends RegisterBasics<Shaped> {

    public static Supplier<IForgeRegistry<Shaped>> SHAPED;
    public static final Map<ShapedType, Map<ShapedDrive, List<Shaped>>> MAP = new HashMap<>();

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        SHAPED = event.create(new RegistryBuilder<Shaped>().setName(new ResourceLocation(Dusk.MOD_ID, "shaped")));
    }

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

    public Shaped(ResourceLocation name, ShapedType shapedType, ShapedDrive shapedDrive, ManaLevel manaLevel) {
        super(name, SHAPED);
        this.shapedType = shapedType;
        this.shapedDrive = shapedDrive;
        this.manaLevel = manaLevel;
        MAP.computeIfAbsent(shapedType, k -> new HashMap<>()).computeIfAbsent(shapedDrive, k -> new ArrayList<>()).add(this);
    }


    public abstract IHandle.ShapedHandle get(IHandle iControl, ManaLevel manaLevel, Map<BlockEntity, IItemHandler> items, Map<BlockEntity, IFluidHandler> fluids);

    /***
     * 获取JEI配方
     */
    public abstract IJEIShaped getJEIShaped();

    public interface IJEIShaped {

        IJEIShaped empty = new IJEIShaped() {
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

    public static class ShapedOreOre extends Shaped {

        @Nullable
        Map<String, Integer> item;
        @Nullable
        Map<String, Integer> fluid;
        int surplusTime;
        long consumeMana;
        long outMana;
        @Nullable
        List<ItemStack> outItem;
        @Nullable
        List<FluidStack> outFluid;

        public ShapedOreOre(ResourceLocation name, ShapedType shapedType, ShapedDrive shapedDrive, ManaLevel manaLevel,
                            @Nullable Map<String, Integer> item, @Nullable Map<String, Integer> fluid,
                            int surplusTime, long consumeMana, long outMana,
                            @Nullable List<ItemStack> outItem, @Nullable List<FluidStack> outFluid) {
            super(name, shapedType, shapedDrive, manaLevel);
            this.item = item;
            this.fluid = fluid;
            this.surplusTime = surplusTime;
            this.consumeMana = consumeMana;
            this.outMana = outMana;
            this.outItem = outItem;
            this.outFluid = outFluid;
        }

        @Override
        public IHandle.ShapedHandle get(IHandle iControl, ManaLevel manaLevel, Map<BlockEntity, IItemHandler> items, Map<BlockEntity, IFluidHandler> fluids) {
            return null;
        }

        @Override
        public IJEIShaped getJEIShaped() {
            return null;
        }
    }
}
