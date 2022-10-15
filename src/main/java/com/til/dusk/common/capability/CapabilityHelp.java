package com.til.dusk.common.capability;

import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.mana_handle.IManaHandle;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.capability.shaped_drive.IShapedDrive;
import com.til.dusk.common.event.EventIO;
import com.til.dusk.common.register.other.BindType;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.util.Pos;
import com.til.dusk.util.RoutePack;
import com.til.dusk.util.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

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
     * @param manaHandle
     */
    public static long addMana(IPosTrack iPosTrack, @Nullable RoutePack<Long> routePack, Map.Entry<IPosTrack, IManaHandle> manaHandle, long mana, boolean isSimulate) {
        boolean isOriginal = routePack == null;
        if (isOriginal) {
            routePack = new RoutePack<>();
        }
        if (manaHandle.getValue() instanceof RoutePack.ISupportRoutePack<?> supportRoutePack) {
            supportRoutePack.set(Util.forcedConversion(routePack.getNext()));
        }
        long inMana = manaHandle.getValue().addMana(mana, isSimulate);
        routePack.add(new RoutePack.RouteCell<>(iPosTrack.getPos(), manaHandle.getKey().getPos(), inMana));
        if (!isSimulate && isOriginal) {
            MinecraftForge.EVENT_BUS.post(new EventIO.Mana(iPosTrack.getLevel(), routePack));
        }
        return inMana;
    }

    /***
     * 提取灵气
     * @param routePack iPosTrack的
     * @param manaHandle
     */
    public static long extractMana(IPosTrack iPosTrack, @Nullable RoutePack<Long> routePack, Map.Entry<IPosTrack, IManaHandle> manaHandle, long mana, boolean isSimulate) {
        boolean isOriginal = routePack == null;
        if (isOriginal) {
            routePack = new RoutePack<>();
        }
        RoutePack<Long> up = routePack.getUp();
        if (manaHandle.getValue() instanceof RoutePack.ISupportRoutePack<?> supportRoutePack) {
            supportRoutePack.set(Util.forcedConversion(up));
        }
        long outMana = manaHandle.getValue().extractMana(mana, isSimulate);
        up.add(new RoutePack.RouteCell<>(manaHandle.getKey().getPos(), iPosTrack.getPos(), outMana));
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
    public static long addMana(IPosTrack iPosTrack, @Nullable RoutePack<Long> routePack, Map<IPosTrack, IManaHandle> map, long mana, boolean isSimulate) {
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
        for (Map.Entry<IPosTrack, IManaHandle> entry : map.entrySet()) {
            long in = addMana(iPosTrack, routePack, entry, mana, isSimulate);
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
    public static long extractMana(IPosTrack iPosTrack, @Nullable RoutePack<Long> routePack, Map<IPosTrack, IManaHandle> map, long mana, boolean isSimulate) {
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
        for (Map.Entry<IPosTrack, IManaHandle> entry : map.entrySet()) {
            long out = extractMana(iPosTrack, routePack, entry, mana, isSimulate);
            outMana += out;
            mana -= out;
            if (mana <= 0) {
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
    public static RoutePack<Long> manaPointToPointTransmit(IPosTrack iPosTrack, Map<IPosTrack, IManaHandle> inMap, Map<IPosTrack, IManaHandle> outMap, long max, double manaLoss, boolean isSimulate) {
        if (inMap.isEmpty()) {
            return null;
        }
        if (outMap.isEmpty()) {
            return null;
        }
        if (max == 0) {
            return null;
        }
        Map<IPosTrack, IManaHandle> _outMap = new HashMap<>();
        for (Map.Entry<IPosTrack, IManaHandle> entry : outMap.entrySet()) {
            if (!inMap.containsKey(entry.getKey())) {
                _outMap.put(entry.getKey(), entry.getValue());
            }
        }
        if (_outMap.isEmpty()) {
            return null;
        }
        outMap = _outMap;
        RoutePack<Long> thisRoutePack = new RoutePack<>();
        for (Map.Entry<IPosTrack, IManaHandle> entry : inMap.entrySet()) {
            long needOutName = entry.getValue().getOutCurrentRate();
            if (needOutName == 0) {
                break;
            }
            for (Map.Entry<IPosTrack, IManaHandle> _entry : outMap.entrySet()) {
                if (needOutName == 0) {
                    break;
                }
                long needTransferMana = Math.min(Math.min(needOutName, max), Math.min(_entry.getValue().getInCurrentRate(), _entry.getValue().getRemainMana()));
                if (needTransferMana == 0) {
                    continue;
                }
                long extractMana = extractMana(iPosTrack, thisRoutePack, entry, needTransferMana, isSimulate);
                extractMana = (long) (extractMana * (1 - manaLoss));
                long transferMana = addMana(iPosTrack, thisRoutePack, _entry, extractMana, isSimulate);
                if (transferMana == 0) {
                    continue;
                }
                needOutName -= needTransferMana;
                max -= needTransferMana;
                if (max <= 0) {
                    break;
                }
                if (needOutName < 0) {
                    break;
                }
            }
            if (max <= 0) {
                break;
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
    public static ItemStack insertItem(IPosTrack iPosTrack, @Nullable RoutePack<ItemStack> routePack, Map.Entry<IPosTrack, IItemHandler> iItemHandler, int slot, ItemStack stack, boolean isSimulate) {
        boolean isOriginal = routePack == null;
        if (isOriginal) {
            routePack = new RoutePack<>();
        }
        if (iItemHandler instanceof RoutePack.ISupportRoutePack<?> supportRoutePack) {
            supportRoutePack.set(Util.forcedConversion(routePack.getNext()));
        }
        ItemStack inMana = iItemHandler.getValue().insertItem(slot, stack, isSimulate);
        routePack.add(new RoutePack.RouteCell<>(iPosTrack.getPos(), iItemHandler.getKey().getPos(), inMana));
        if (!isSimulate && isOriginal) {
            MinecraftForge.EVENT_BUS.post(new EventIO.Item(iPosTrack.getLevel(), routePack));
        }
        return inMana;
    }

    /***
     * 使用路径粒子插入物品
     */
    public static ItemStack insertItem(IPosTrack iPosTrack, @Nullable RoutePack<ItemStack> routePack, Map.Entry<IPosTrack, IItemHandler> iItemHandler, ItemStack stack, boolean isSimulate) {
        boolean isOriginal = routePack == null;
        if (isOriginal) {
            routePack = new RoutePack<>();
        }
        if (iItemHandler instanceof RoutePack.ISupportRoutePack<?> supportRoutePack) {
            supportRoutePack.set(Util.forcedConversion(routePack.getNext()));
        }
        ItemStack surplus = ItemHandlerHelper.insertItemStacked(iItemHandler.getValue(), stack, isSimulate);
        ItemStack in = stack.copy();
        in.setCount(in.getCount() - surplus.getCount());
        routePack.add(new RoutePack.RouteCell<>(iPosTrack.getPos(), iItemHandler.getKey().getPos(), in));
        if (!isSimulate && isOriginal) {
            MinecraftForge.EVENT_BUS.post(new EventIO.Item(iPosTrack.getLevel(), routePack));
        }
        return surplus;
    }

    /***
     * 使用路径粒子插入物品
     */
    public static ItemStack insertItem(IPosTrack iPosTrack, @Nullable RoutePack<ItemStack> routePack, Map<IPosTrack, IItemHandler> map, ItemStack stack, boolean isSimulate) {
        boolean isOriginal = routePack == null;
        if (isOriginal) {
            routePack = new RoutePack<>();
        }
        for (Map.Entry<IPosTrack, IItemHandler> entry : map.entrySet()) {
            stack = insertItem(iPosTrack, routePack, entry, stack, isSimulate);
            if (stack.isEmpty()) {
                break;
            }
        }
        if (!isSimulate && isOriginal) {
            MinecraftForge.EVENT_BUS.post(new EventIO.Item(iPosTrack.getLevel(), routePack));
        }
        return stack;
    }

    public static List<ItemStack> insertItem(IPosTrack iPosTrack, @Nullable RoutePack<ItemStack> routePack, Map<IPosTrack, IItemHandler> map, List<ItemStack> itemStackList, boolean isSimulate) {
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
        return itemStacks;
    }

    /***
     * 提取物品
     */
    public static ItemStack extractItem(IPosTrack iPosTrack, @Nullable RoutePack<ItemStack> routePack, Map.Entry<IPosTrack, IItemHandler> iItemHandler, int slot, int amount, boolean isSimulate) {
        boolean isOriginal = routePack == null;
        if (isOriginal) {
            routePack = new RoutePack<>();
        }
        RoutePack<ItemStack> up = routePack.getUp();
        if (iItemHandler instanceof RoutePack.ISupportRoutePack<?> supportRoutePack) {
            supportRoutePack.set(Util.forcedConversion(up));
        }
        ItemStack extractItem = iItemHandler.getValue().extractItem(slot, amount, isSimulate);
        up.add(new RoutePack.RouteCell<>(iItemHandler.getKey().getPos(), iPosTrack.getPos(), extractItem));
        if (!isSimulate && isOriginal) {
            MinecraftForge.EVENT_BUS.post(new EventIO.Item(iPosTrack.getLevel(), routePack));
        }
        return extractItem;
    }

    /***
     * 取出物品并放入
     */
    public static void extractAndInsertItem(IPosTrack iPosTrack, Map.Entry<IPosTrack, IItemHandler> iItemHandler, int slot, int amount, Map<IPosTrack, IItemHandler> out, boolean isSimulate) {
        RoutePack<ItemStack> routePack = new RoutePack<>();
        ItemStack itemStack = extractItem(iPosTrack, routePack, iItemHandler, slot, amount, isSimulate);
        insertItem(iPosTrack, routePack, out, itemStack, isSimulate);
        if (!isSimulate) {
            MinecraftForge.EVENT_BUS.post(new EventIO.Item(iPosTrack.getLevel(), routePack));
        }
    }

    /***
     * 取出物品并放入
     */
    public static void extractAndInsertItem(IPosTrack iPosTrack, Map.Entry<IPosTrack, IItemHandler> iItemHandler, int slot, int amount, Map.Entry<IPosTrack, IItemHandler> out, boolean isSimulate) {
        RoutePack<ItemStack> routePack = new RoutePack<>();
        ItemStack itemStack = extractItem(iPosTrack, routePack, iItemHandler, slot, amount, isSimulate);
        insertItem(iPosTrack, routePack, out, itemStack, isSimulate);
        if (!isSimulate) {
            MinecraftForge.EVENT_BUS.post(new EventIO.Item(iPosTrack.getLevel(), routePack));
        }
    }

    /***
     * 填充
     */
    public static int fill(IPosTrack iPosTrack, @Nullable RoutePack<FluidStack> routePack, Map.Entry<IPosTrack, IFluidHandler> fluidHandler, FluidStack fluidStack, boolean isSimulate) {
        boolean isOriginal = routePack == null;
        if (isOriginal) {
            routePack = new RoutePack<>();
        }
        if (fluidHandler instanceof RoutePack.ISupportRoutePack<?> supportRoutePack) {
            supportRoutePack.set(Util.forcedConversion(routePack.getNext()));
        }
        int in = fluidHandler.getValue().fill(fluidStack, asFluidAction(isSimulate));
        routePack.add(new RoutePack.RouteCell<>(iPosTrack.getPos(), fluidHandler.getKey().getPos(), new FluidStack(fluidStack, in)));
        if (!isSimulate && isOriginal) {
            MinecraftForge.EVENT_BUS.post(new EventIO.Fluid(iPosTrack.getLevel(), routePack));
        }
        return in;
    }

    /***
     * 填充
     */
    public static int fill(IPosTrack iPosTrack, @Nullable RoutePack<FluidStack> routePack, Map<IPosTrack, IFluidHandler> map, FluidStack fluidStack, boolean isSimulate) {
        boolean isOriginal = routePack == null;
        if (isOriginal) {
            routePack = new RoutePack<>();
        }
        int allIn = 0;
        for (Map.Entry<IPosTrack, IFluidHandler> entry : map.entrySet()) {
            int in = fill(iPosTrack, routePack, entry, fluidStack, isSimulate);
            fluidStack = fluidStack.copy();
            fluidStack.setAmount(fluidStack.getAmount() - in);
            allIn += in;
            if (fluidStack.isEmpty()) {
                break;
            }
        }
        if (!isSimulate && isOriginal) {
            MinecraftForge.EVENT_BUS.post(new EventIO.Fluid(iPosTrack.getLevel(), routePack));
        }
        return allIn;
    }

    @Nullable
    public static List<FluidStack> fill(IPosTrack iPosTrack, @Nullable RoutePack<FluidStack> routePack, Map<IPosTrack, IFluidHandler> map, List<FluidStack> fluidStack, boolean isSimulate) {
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
        if (!isSimulate && isOriginal) {
            MinecraftForge.EVENT_BUS.post(new EventIO.Fluid(iPosTrack.getLevel(), routePack));
        }
        if (nFluidStack.isEmpty()) {
            return null;
        }
        return nFluidStack;
    }

    /***
     * 排出
     */
    public static FluidStack drain(IPosTrack iPosTrack, @Nullable RoutePack<FluidStack> routePack, Map.Entry<IPosTrack, IFluidHandler> fluidHandler, FluidStack fluidStack, boolean isSimulate) {
        boolean isOriginal = routePack == null;
        if (isOriginal) {
            routePack = new RoutePack<>();
        }
        RoutePack<FluidStack> up = routePack.getUp();
        if (fluidHandler instanceof RoutePack.ISupportRoutePack<?> supportRoutePack) {
            supportRoutePack.set(Util.forcedConversion(up));
        }
        FluidStack d = fluidHandler.getValue().drain(fluidStack, asFluidAction(isSimulate));
        up.add(new RoutePack.RouteCell<>(fluidHandler.getKey().getPos(), iPosTrack.getPos(), d));
        if (!isSimulate && isOriginal) {
            MinecraftForge.EVENT_BUS.post(new EventIO.Fluid(iPosTrack.getLevel(), routePack));
        }
        return d;
    }

    /***
     * 排出
     */
    public static FluidStack drain(IPosTrack iPosTrack, @Nullable RoutePack<FluidStack> routePack, Map<IPosTrack, IFluidHandler> out, FluidStack fluidStack, boolean isSimulate) {
        boolean isOriginal = routePack == null;
        if (isOriginal) {
            routePack = new RoutePack<>();
        }
        int need = fluidStack.getAmount();
        int outA = 0;
        for (Map.Entry<IPosTrack, IFluidHandler> entry : out.entrySet()) {
            FluidStack fluidStack1 = drain(iPosTrack, routePack, entry, new FluidStack(fluidStack, need), isSimulate);
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
    public static FluidStack drain(IPosTrack iPosTrack, @Nullable RoutePack<FluidStack> routePack, IFluidHandler fluidHandler, IPosTrack itemHandlerPos, int fluidStack, boolean isSimulate) {
        boolean isOriginal = routePack == null;
        if (isOriginal) {
            routePack = new RoutePack<>();
        }
        if (fluidHandler instanceof RoutePack.ISupportRoutePack<?> supportRoutePack) {
            supportRoutePack.set(Util.forcedConversion(routePack.getNext()));
        }
        FluidStack d = fluidHandler.drain(fluidStack, asFluidAction(isSimulate));
        routePack.add(new RoutePack.RouteCell<>(itemHandlerPos.getPos(), iPosTrack.getPos(), d));
        if (!isSimulate && isOriginal) {
            MinecraftForge.EVENT_BUS.post(new EventIO.Fluid(iPosTrack.getLevel(), routePack));
        }
        return d;
    }

    public static void drainAndFillFluid(IPosTrack iPosTrack, Map.Entry<IPosTrack, IFluidHandler> fluidHandler, FluidStack fluidStack, Map<IPosTrack, IFluidHandler> out, boolean isSimulate) {
        RoutePack<FluidStack> routePack = new RoutePack<>();
        FluidStack itemStack = drain(iPosTrack, routePack, fluidHandler, fluidStack, isSimulate);
        fill(iPosTrack, routePack, out, itemStack, isSimulate);
        if (!isSimulate) {
            MinecraftForge.EVENT_BUS.post(new EventIO.Fluid(iPosTrack.getLevel(), routePack));
        }
    }

    public static void drainAndFillFluid(IPosTrack iPosTrack, Map.Entry<IPosTrack, IFluidHandler> fluidHandler, FluidStack fluidStack, Map.Entry<IPosTrack, IFluidHandler> out, boolean isSimulate) {
        RoutePack<FluidStack> routePack = new RoutePack<>();
        FluidStack itemStack = drain(iPosTrack, routePack, fluidHandler, fluidStack, isSimulate);
        fill(iPosTrack, routePack, out, itemStack, isSimulate);
        if (!isSimulate) {
            MinecraftForge.EVENT_BUS.post(new EventIO.Fluid(iPosTrack.getLevel(), routePack));
        }
    }

    public static IFluidHandler.FluidAction asFluidAction(boolean isSimulate) {
        return isSimulate ? IFluidHandler.FluidAction.SIMULATE : IFluidHandler.FluidAction.EXECUTE;
    }

    /***
     * 返回实体携带的物品
     * @return
     */
    public static <C> List<C> getLivingEntityCapability(Capability<C> c, LivingEntity livingEntity) {
        List<C> iManaHandles = new ArrayList<>();
        for (Direction direction : DIRECTIONS) {
            LazyOptional<C> iItemHandlerLazyOptional = livingEntity.getCapability(c, direction);
            if (iItemHandlerLazyOptional.isPresent()) {
                iManaHandles.add(iItemHandlerLazyOptional.orElse(null));
            }
        }
        return iManaHandles;
    }

    public static final Direction[] DIRECTIONS = new Direction[]{
            null,
            Direction.UP,
            Direction.NORTH
    };

    public static AABB getAABB(IControl iControl, double defaultRange) {
        Pos pos = iControl.getPosTrack().getPos();
        double xMax = 0;
        double yMax = 0;
        double zMax = 0;
        double xMin = 0;
        double yMin = 0;
        double zMin = 0;
        Map<IPosTrack, IPosTrack> map = iControl.getCapability(BindType.posTrack);
        if (map.size() < 2) {
            xMax = pos.x;
            yMax = pos.y;
            zMax = pos.z;
            xMin = pos.x;
            yMin = pos.y;
            zMin = pos.z;
        }
        if (!map.isEmpty()) {
            boolean isOne = map.size() > 1;
            for (Map.Entry<IPosTrack, IPosTrack> entry : map.entrySet()) {
                Pos blockPos = entry.getValue().getPos();
                if (isOne) {
                    xMax = blockPos.x;
                    yMax = blockPos.y;
                    zMax = blockPos.z;
                    xMin = blockPos.x;
                    yMin = blockPos.y;
                    zMin = blockPos.z;
                    isOne = false;
                    continue;
                }
                if (blockPos.x > xMax) {
                    xMax = blockPos.x;
                }

                if (blockPos.y > yMax) {
                    yMax = blockPos.y;
                }

                if (blockPos.z > zMax) {
                    zMax = blockPos.z;
                }

                if (blockPos.x < xMin) {
                    xMin = blockPos.x;
                }

                if (blockPos.y < yMin) {
                    yMin = blockPos.y;
                }

                if (blockPos.z < zMin) {
                    zMin = blockPos.z;
                }
            }
        } else {
            xMax += defaultRange;
            yMax += defaultRange;
            zMax += defaultRange;
            xMin -= defaultRange;
            yMin -= defaultRange;
            zMin -= defaultRange;
        }
        return Pos.asAABB(new Pos(xMax, yMax, zMax).move(0.5, 0.5, 0.5), new Pos(xMin, yMin, zMin).move(-0.5, -0.5, -0.5));
    }

    public static boolean isInAABB(AABB aabb, BlockPos blockPos) {
        return aabb.maxX > blockPos.getX() && aabb.minX <= blockPos.getX() &&
               aabb.maxY > blockPos.getY() && aabb.minY <= blockPos.getY() &&
               aabb.maxZ > blockPos.getZ() && aabb.minZ <= blockPos.getZ();
    }

    public static List<ShapedDrive> getShapedDrive(IControl iControl) {
        Map<IPosTrack, IShapedDrive> map = iControl.getCapability(BindType.modelStore);
        if (map.isEmpty()) {
            return List.of();
        }
        List<ShapedDrive> shapedDriveList = new ArrayList<>();
        for (Map.Entry<IPosTrack, IShapedDrive> entry : map.entrySet()) {
            for (ShapedDrive shapedDrive : entry.getValue().get()) {
                if (shapedDriveList.contains(shapedDrive)) {
                    continue;
                }
                shapedDriveList.add(shapedDrive);
            }
        }
        return shapedDriveList;
    }
}
