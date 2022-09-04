package com.til.dusk.common.capability;

import com.til.dusk.common.capability.mana_handle.IManaHandle;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.event.EventIO;
import com.til.dusk.util.Pos;
import com.til.dusk.util.RoutePack;
import com.til.dusk.util.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对灵气处理的一些帮助函数
 *
 * @author til
 */
public class CapabilityHelp {

    /***
     * 加入灵气
     * @param routePack iPosTrack的
     */
    public static long addMana(IPosTrack iPosTrack, @Nullable RoutePack<Long> routePack, Pos manaHandlePos, long mana, IManaHandle iManaHandle, boolean isSimulate) {
        boolean isOriginal = routePack == null;
        if (isOriginal) {
            routePack = new RoutePack<>();
        }
        if (iManaHandle instanceof RoutePack.ISupportRoutePack<?> supportRoutePack) {
            supportRoutePack.set(Util.forcedConversion(routePack.getNext()));
        }
        long inMana = iManaHandle.addMana(mana, isSimulate);
        routePack.add(new RoutePack.RouteCell<>(iPosTrack.getPos(), manaHandlePos, inMana));
        if (!isSimulate && isOriginal) {
            MinecraftForge.EVENT_BUS.post(new EventIO.Mana(iPosTrack.getLevel(), routePack));
        }
        return inMana;
    }

    /***
     * 提取灵气
     * @param routePack iPosTrack的
     */
    public static long extractMana(IPosTrack iPosTrack, @Nullable RoutePack<Long> routePack, Pos manaHandlePos, long mana, IManaHandle iManaHandle, boolean isSimulate) {
        boolean isOriginal = routePack == null;
        if (isOriginal) {
            routePack = new RoutePack<>();
        }
        RoutePack<Long> up = routePack.getUp();
        if (iManaHandle instanceof RoutePack.ISupportRoutePack<?> supportRoutePack) {
            supportRoutePack.set(Util.forcedConversion(up));
        }
        long outMana = iManaHandle.extractMana(mana, isSimulate);
        up.add(new RoutePack.RouteCell<>(manaHandlePos, iPosTrack.getPos(), outMana));
        if (!isSimulate && isOriginal) {
            MinecraftForge.EVENT_BUS.post(new EventIO.Mana(iPosTrack.getLevel(), routePack));
        }
        return outMana;
    }

    /***
     * 添加灵气
     * @param iPosTrack 位置追踪
     * @param routePack 起始路径，如果为空将发布事件
     * @param map 添加进入对象
     * @param mana 要添加的灵气
     * @return 加入了多少
     */
    public static long addMana(IPosTrack iPosTrack, @Nullable RoutePack<Long> routePack, Map<BlockEntity, IManaHandle> map, long mana, boolean isSimulate) {
        boolean isOriginal = routePack == null;
        if (mana <= 0) {
            return 0;
        }
        if (map.isEmpty()) {
            return 0;
        }
        long inMana = 0;
        if (isOriginal) {
            routePack = new RoutePack<>();
        }
        for (Map.Entry<BlockEntity, IManaHandle> entry : map.entrySet()) {
            long in = addMana(iPosTrack, routePack, new Pos(entry.getKey().getBlockPos()), mana, entry.getValue(), isSimulate);
            inMana += in;
            mana -= in;
            if (mana <= 0) {
                break;
            }
        }
        if (!isSimulate && isOriginal) {
            MinecraftForge.EVENT_BUS.post(new EventIO.Mana(iPosTrack.getLevel(), routePack));
        }
        return inMana;
    }

    /***
     * 抽取灵气
     * @param iPosTrack 位置追踪
     * @param routePack 路径点
     * @param map 抽取对象
     * @param mana 要抽取的灵气
     * @return 抽取了多少
     */
    public static long extractMana(IPosTrack iPosTrack, @Nullable RoutePack<Long> routePack, Map<BlockEntity, IManaHandle> map, long mana, boolean isSimulate) {
        boolean isOriginal = routePack == null;
        if (mana <= 0) {
            return 0;
        }
        if (map.isEmpty()) {
            return 0;
        }
        long outMana = 0;
        if (isOriginal) {
            routePack = new RoutePack<>();
        }
        for (Map.Entry<BlockEntity, IManaHandle> entry : map.entrySet()) {
            long out = extractMana(iPosTrack, routePack, new Pos(entry.getKey().getBlockPos()), mana, entry.getValue(), isSimulate);
            outMana += out;
            mana -= out;
            if (mana < 0) {
                break;
            }
        }
        if (!isSimulate && isOriginal) {
            MinecraftForge.EVENT_BUS.post(new EventIO.Mana(iPosTrack.getLevel(), routePack));
        }
        return outMana;
    }

    /***
     * 点对点传递
     */
    @Nullable

    public static RoutePack<Long> manaPointToPointTransmit(IPosTrack iPosTrack, Map<BlockEntity, IManaHandle> inMap, Map<BlockEntity, IManaHandle> outMap, double manaLoss,boolean isSimulate) {
        if (inMap.isEmpty()) {
            return null;
        }
        if (outMap.isEmpty()) {
            return null;
        }
        Map<BlockEntity, IManaHandle> _outMap = new HashMap<>();
        for (Map.Entry<BlockEntity, IManaHandle> entry : outMap.entrySet()) {
            if (!inMap.containsKey(entry.getKey())) {
                _outMap.put(entry.getKey(), entry.getValue());
            }
        }
        if (_outMap.isEmpty()) {
            return null;
        }
        outMap = _outMap;
        RoutePack<Long> thisRoutePack = new RoutePack<>();
        for (Map.Entry<BlockEntity, IManaHandle> entry : inMap.entrySet()) {
            long needOutName = entry.getValue().getOutCurrentRate();
            if (needOutName == 0) {
                break;
            }
            for (Map.Entry<BlockEntity, IManaHandle> _entry : outMap.entrySet()) {
                if (needOutName == 0) {
                    break;
                }
                long needTransferMana = Math.min(needOutName, _entry.getValue().getInCurrentRate());
                if (needTransferMana == 0) {
                    continue;
                }
                long extractMana = extractMana(iPosTrack, thisRoutePack, new Pos(entry.getKey()), needTransferMana, entry.getValue(), isSimulate);
                extractMana = (long) (extractMana * (1 - manaLoss));
                long transferMana = addMana(iPosTrack, thisRoutePack, new Pos(_entry.getKey()), extractMana, _entry.getValue(), isSimulate);
                if (transferMana == 0) {
                    continue;
                }
                needOutName -= needTransferMana;
                if (needOutName < 0) {
                    break;
                }
            }
        }
        if (!isSimulate) {
            MinecraftForge.EVENT_BUS.post(new EventIO.Mana(iPosTrack.getLevel(), thisRoutePack));
        }
        return thisRoutePack;
    }

    /***
     * 使用路径粒子插入物品
     */
    public static ItemStack insertItem(IPosTrack iPosTrack, @Nullable RoutePack<ItemStack> routePack, IItemHandler iItemHandler, Pos itemHandlerPos, int slot, ItemStack stack, boolean isSimulate) {
        boolean isOriginal = routePack == null;
        if (isOriginal) {
            routePack = new RoutePack<>();
        }
        if (iItemHandler instanceof RoutePack.ISupportRoutePack<?> supportRoutePack) {
            supportRoutePack.set(Util.forcedConversion(routePack.getNext()));
        }
        ItemStack inMana = iItemHandler.insertItem(slot, stack, isSimulate);
        routePack.add(new RoutePack.RouteCell<>(iPosTrack.getPos(), itemHandlerPos, inMana));
        if (!isSimulate && isOriginal) {
            MinecraftForge.EVENT_BUS.post(new EventIO.Item(iPosTrack.getLevel(), routePack));
        }
        return inMana;
    }

    /***
     * 使用路径粒子插入物品
     */
    public static ItemStack insertItem(IPosTrack iPosTrack, @Nullable RoutePack<ItemStack> routePack, IItemHandler iItemHandler, Pos itemHandlerPos, ItemStack stack, boolean isSimulate) {
        boolean isOriginal = routePack == null;
        if (isOriginal) {
            routePack = new RoutePack<>();
        }
        if (iItemHandler instanceof RoutePack.ISupportRoutePack<?> supportRoutePack) {
            supportRoutePack.set(Util.forcedConversion(routePack.getNext()));
        }
        ItemStack surplus = ItemHandlerHelper.insertItemStacked(iItemHandler, stack, isSimulate);
        ItemStack in = stack.copy();
        in.setCount(in.getCount() - surplus.getCount());
        routePack.add(new RoutePack.RouteCell<>(iPosTrack.getPos(), itemHandlerPos, in));
        if (!isSimulate && isOriginal) {
            MinecraftForge.EVENT_BUS.post(new EventIO.Item(iPosTrack.getLevel(), routePack));
        }
        return surplus;
    }

    /***
     * 使用路径粒子插入物品
     */
    public static ItemStack insertItem(IPosTrack iPosTrack, @Nullable RoutePack<ItemStack> routePack, Map<BlockEntity, IItemHandler> map, ItemStack stack, boolean isSimulate) {
        boolean isOriginal = routePack == null;
        if (isOriginal) {
            routePack = new RoutePack<>();
        }
        for (Map.Entry<BlockEntity, IItemHandler> entry : map.entrySet()) {
            stack = insertItem(iPosTrack, routePack, entry.getValue(), new Pos(entry.getKey()), stack, isSimulate);
            if (stack.isEmpty()) {
                break;
            }
        }
        if (!isSimulate && isOriginal) {
            MinecraftForge.EVENT_BUS.post(new EventIO.Item(iPosTrack.getLevel(), routePack));
        }
        return stack;
    }

    @Nullable
    public static List<ItemStack> insertItem(IPosTrack iPosTrack, @Nullable RoutePack<ItemStack> routePack, Map<BlockEntity, IItemHandler> map, List<ItemStack> itemStackList, boolean isSimulate) {
        boolean isOriginal = routePack == null;
        if (isOriginal) {
            routePack = new RoutePack<>();
        }
        List<ItemStack> itemStacks = new ArrayList<>();
        for (ItemStack itemStack : itemStackList) {
            ItemStack s = insertItem(iPosTrack, routePack, map, itemStack, isSimulate);
            if (!s.isEmpty()) {
                itemStacks.add(s);
            }
        }
        if (!isSimulate && isOriginal) {
            MinecraftForge.EVENT_BUS.post(new EventIO.Item(iPosTrack.getLevel(), routePack));
        }
        if (itemStacks.isEmpty()) {
            return null;
        }
        return itemStackList;
    }

    /***
     * 提取物品
     */
    public static ItemStack extractItem(IPosTrack iPosTrack, @Nullable RoutePack<ItemStack> routePack, IItemHandler iItemHandler, Pos itemHandlerPos, int slot, int amount, boolean isSimulate) {
        boolean isOriginal = routePack == null;
        if (isOriginal) {
            routePack = new RoutePack<>();
        }
        RoutePack<ItemStack> up = routePack.getUp();
        if (iItemHandler instanceof RoutePack.ISupportRoutePack<?> supportRoutePack) {
            supportRoutePack.set(Util.forcedConversion(up));
        }
        ItemStack extractItem = iItemHandler.extractItem(slot, amount, isSimulate);
        up.add(new RoutePack.RouteCell<>(itemHandlerPos, iPosTrack.getPos(), extractItem));
        if (!isSimulate && isOriginal) {
            MinecraftForge.EVENT_BUS.post(new EventIO.Item(iPosTrack.getLevel(), routePack));
        }
        return extractItem;
    }

    /***
     * 取出物品并放入
     */
    public static void extractAndInsertItem(IPosTrack iPosTrack, IItemHandler iItemHandler, Pos itemHandlerPos, int slot, int amount, Map<BlockEntity, IItemHandler> out, boolean isSimulate) {
        RoutePack<ItemStack> routePack = new RoutePack<>();
        ItemStack itemStack = extractItem(iPosTrack, routePack, iItemHandler, itemHandlerPos, slot, amount, isSimulate);
        insertItem(iPosTrack, routePack, out, itemStack, isSimulate);
        if (!isSimulate) {
            MinecraftForge.EVENT_BUS.post(new EventIO.Item(iPosTrack.getLevel(), routePack));
        }
    }

    /***
     * 填充
     */
    public static int fill(IPosTrack iPosTrack, @Nullable RoutePack<FluidStack> routePack, IFluidHandler fluidHandler, Pos itemHandlerPos, FluidStack fluidStack, boolean isSimulate) {
        boolean isOriginal = routePack == null;
        if (isOriginal) {
            routePack = new RoutePack<>();
        }
        if (fluidHandler instanceof RoutePack.ISupportRoutePack<?> supportRoutePack) {
            supportRoutePack.set(Util.forcedConversion(routePack.getNext()));
        }
        int in = fluidHandler.fill(fluidStack, asFluidAction(isSimulate));
        routePack.add(new RoutePack.RouteCell<>(iPosTrack.getPos(), itemHandlerPos, new FluidStack(fluidStack, in)));
        if (!isSimulate && isOriginal) {
            MinecraftForge.EVENT_BUS.post(new EventIO.Fluid(iPosTrack.getLevel(), routePack));
        }
        return in;
    }

    /***
     * 填充
     */
    public static int fill(IPosTrack iPosTrack, @Nullable RoutePack<FluidStack> routePack, Map<BlockEntity, IFluidHandler> map, FluidStack fluidStack, boolean isSimulate) {
        boolean isOriginal = routePack == null;
        if (isOriginal) {
            routePack = new RoutePack<>();
        }
        int allIn = 0;
        for (Map.Entry<BlockEntity, IFluidHandler> entry : map.entrySet()) {
            int in = fill(iPosTrack, routePack, entry.getValue(), new Pos(entry.getKey()), fluidStack, isSimulate);
            fluidStack = fluidStack.copy();
            fluidStack.setAmount(fluidStack.getAmount() - in);
            allIn += in;
        }
        if (!isSimulate && isOriginal) {
            MinecraftForge.EVENT_BUS.post(new EventIO.Fluid(iPosTrack.getLevel(), routePack));
        }
        return allIn;
    }

    @Nullable
    public static List<FluidStack> fill(IPosTrack iPosTrack, @Nullable RoutePack<FluidStack> routePack, Map<BlockEntity, IFluidHandler> map, List<FluidStack> fluidStack, boolean isSimulate) {
        boolean isOriginal = routePack == null;
        if (isOriginal) {
            routePack = new RoutePack<>();
        }
        List<FluidStack> nFluidStack = new ArrayList<>();
        for (FluidStack stack : fluidStack) {
            int out = fill(iPosTrack, routePack, map, stack, isSimulate);
            if (stack.getAmount() - out > 0) {
                nFluidStack.add(new FluidStack(stack, stack.getAmount() - out));
            }
        }
        if (nFluidStack.isEmpty()) {
            return null;
        }
        if (!isSimulate && isOriginal) {
            MinecraftForge.EVENT_BUS.post(new EventIO.Fluid(iPosTrack.getLevel(), routePack));
        }
        return nFluidStack;
    }

    /***
     * 排出
     */
    public static FluidStack drain(IPosTrack iPosTrack, @Nullable RoutePack<FluidStack> routePack, IFluidHandler fluidHandler, Pos itemHandlerPos, FluidStack fluidStack, boolean isSimulate) {
        boolean isOriginal = routePack == null;
        if (isOriginal) {
            routePack = new RoutePack<>();
        }
        if (fluidHandler instanceof RoutePack.ISupportRoutePack<?> supportRoutePack) {
            supportRoutePack.set(Util.forcedConversion(routePack.getNext()));
        }
        FluidStack d = fluidHandler.drain(fluidStack, asFluidAction(isSimulate));
        routePack.add(new RoutePack.RouteCell<>(itemHandlerPos, iPosTrack.getPos(), d));
        if (!isSimulate && isOriginal) {
            MinecraftForge.EVENT_BUS.post(new EventIO.Fluid(iPosTrack.getLevel(), routePack));
        }
        return d;
    }

    /***
     * 排出
     */
    public static FluidStack drain(IPosTrack iPosTrack, @Nullable RoutePack<FluidStack> routePack, Map<BlockEntity, IFluidHandler> out, FluidStack fluidStack, boolean isSimulate) {
        boolean isOriginal = routePack == null;
        if (isOriginal) {
            routePack = new RoutePack<>();
        }
        int need = fluidStack.getAmount();
        int outA = 0;
        for (Map.Entry<BlockEntity, IFluidHandler> entry : out.entrySet()) {
            FluidStack fluidStack1 = drain(iPosTrack, routePack, entry.getValue(), new Pos(entry.getKey()), new FluidStack(fluidStack, need), isSimulate);
            outA += fluidStack1.getAmount();
            need -= fluidStack1.getAmount();
            if (need <= 0) {
                break;
            }
        }
        if (!isSimulate && isOriginal) {
            MinecraftForge.EVENT_BUS.post(new EventIO.Fluid(iPosTrack.getLevel(), routePack));
        }
        return new FluidStack(fluidStack, outA);
    }

    /***
     * 排出
     */
    public static FluidStack drain(IPosTrack iPosTrack, @Nullable RoutePack<FluidStack> routePack, IFluidHandler fluidHandler, Pos itemHandlerPos, int fluidStack, boolean isSimulate) {
        boolean isOriginal = routePack == null;
        if (isOriginal) {
            routePack = new RoutePack<>();
        }
        if (fluidHandler instanceof RoutePack.ISupportRoutePack<?> supportRoutePack) {
            supportRoutePack.set(Util.forcedConversion(routePack.getNext()));
        }
        FluidStack d = fluidHandler.drain(fluidStack, asFluidAction(isSimulate));
        routePack.add(new RoutePack.RouteCell<>(itemHandlerPos, iPosTrack.getPos(), d));
        if (!isSimulate && isOriginal) {
            MinecraftForge.EVENT_BUS.post(new EventIO.Fluid(iPosTrack.getLevel(), routePack));
        }
        return d;
    }

    public static void drainAndFillFluid(IPosTrack iPosTrack, IFluidHandler fluidHandler, Pos itemHandlerPos, FluidStack fluidStack, Map<BlockEntity, IFluidHandler> out, boolean isSimulate) {
        RoutePack<FluidStack> routePack = new RoutePack<>();
        FluidStack itemStack = drain(iPosTrack, routePack, fluidHandler, itemHandlerPos, fluidStack, isSimulate);
        fill(iPosTrack, routePack, out, itemStack, isSimulate);
        if (!isSimulate) {
            MinecraftForge.EVENT_BUS.post(new EventIO.Fluid(iPosTrack.getLevel(), routePack));
        }
    }

    public static IFluidHandler.FluidAction asFluidAction(boolean isSimulate) {
        return isSimulate ? IFluidHandler.FluidAction.SIMULATE : IFluidHandler.FluidAction.EXECUTE;
    }
}
