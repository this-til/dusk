package com.til.dusk.common.register.shaped;

import com.til.dusk.Dusk;
import com.til.dusk.common.capability.handle.IHandle;
import com.til.dusk.common.capability.mana_handle.IManaHandle;
import com.til.dusk.common.capability.handle.ShapedHandle;
import com.til.dusk.common.particle.CommonParticle;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.util.Pos;
import com.til.dusk.util.prefab.ColorPrefab;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static com.til.dusk.common.capability.handle.ShapedHandle.rand;

@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public abstract class ShapedHandleProcess extends RegisterBasics<ShapedHandleProcess> {

    public static Supplier<IForgeRegistry<ShapedHandleProcess>> SHAPED_TYPE_PROCESS;

    public static ShapedHandleProcess production;
    public static ShapedHandleProcess out;
    public static ShapedHandleProcess trippingOperation;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        SHAPED_TYPE_PROCESS = event.create(new RegistryBuilder<ShapedHandleProcess>().setName(new ResourceLocation(Dusk.MOD_ID, "shaped_handle_process")));
        production = new ShapedHandleProcess("production") {
            @Override
            public void up(IHandle iHandle, ShapedHandle shapedHandle, Map<BlockEntity, IManaHandle> manaIn, Map<BlockEntity, IManaHandle> manaOut) {
                long needGetMana = shapedHandle.consumeMana;
                for (Map.Entry<BlockEntity, IManaHandle> tileEntityIManaHandleEntry : manaIn.entrySet()) {
                    long mana = tileEntityIManaHandleEntry.getValue().extractMana(needGetMana);
                    needGetMana = needGetMana - mana;
                    if (rand.nextFloat() < mana / 320f) {
                        CommonParticle.MANA_TRANSFER.add(iHandle.getThis().getLevel(),
                                new Pos(tileEntityIManaHandleEntry.getKey().getBlockPos()),
                                new Pos(iHandle.getThis().getBlockPos()),
                                ColorPrefab.MANA_IO,
                                1);
                    }
                }
                if (needGetMana <= 0) {
                    shapedHandle._surplusTime--;
                    if (shapedHandle._surplusTime <= 0) {
                        shapedHandle.process = out;
                    }
                } else {
                    shapedHandle.process = trippingOperation;
                    shapedHandle._surplusTime = shapedHandle.surplusTime;
                }
            }

            @Override
            public void clock(IHandle iHandle, ShapedHandle shapedHandle, Map<BlockEntity, IItemHandler> itemIn, Map<BlockEntity, IItemHandler> itemOut, Map<BlockEntity, IFluidHandler> fluidIn, Map<BlockEntity, IFluidHandler> fluidOut) {

            }
        };
        out = new ShapedHandleProcess("out") {
            @Override
            public void up(IHandle iHandle, ShapedHandle shapedHandle, Map<BlockEntity, IManaHandle> manaIn, Map<BlockEntity, IManaHandle> manaOut) {
                if (shapedHandle.outMana <= 0) {
                    return;
                }
                for (Map.Entry<BlockEntity, IManaHandle> tileEntityIManaHandleEntry : manaOut.entrySet()) {
                    long mana = tileEntityIManaHandleEntry.getValue().addMana(shapedHandle.outMana);
                    shapedHandle.outMana = shapedHandle.outMana - mana;
                    if (rand.nextFloat() < mana / 320f) {
                        CommonParticle.MANA_TRANSFER.add(iHandle.getThis().getLevel(),
                                new Pos(iHandle.getThis().getBlockPos()),
                                new Pos(tileEntityIManaHandleEntry.getKey().getBlockPos()),
                                ColorPrefab.MANA_IO,
                                1);
                    }
                }
            }

            @Override
            public void clock(IHandle iHandle, ShapedHandle shapedHandle, Map<BlockEntity, IItemHandler> itemIn, Map<BlockEntity, IItemHandler> itemOut, Map<BlockEntity, IFluidHandler> fluidIn, Map<BlockEntity, IFluidHandler> fluidOut) {
                if (shapedHandle.outItem != null && !shapedHandle.outItem.isEmpty()) {
                    List<ItemStack> nItemStack = new ArrayList<>();
                    for (ItemStack itemStack : shapedHandle.outItem) {
                        ItemStack needOut = itemStack;
                        for (Map.Entry<BlockEntity, IItemHandler> tileEntityIItemHandlerEntry : itemOut.entrySet()) {
                            ItemStack out = ItemHandlerHelper.insertItemStacked(tileEntityIItemHandlerEntry.getValue(), needOut, false);
                            if (out.getCount() < needOut.getCount()) {
                                CommonParticle.ITEM_TRANSFER.add(
                                        iHandle.getThis().getLevel(),
                                        new Pos(iHandle.getThis().getBlockPos()),
                                        new Pos(tileEntityIItemHandlerEntry.getKey().getBlockPos()),
                                        ColorPrefab.ITEM_IO,
                                        1);
                            }
                            needOut = out;
                            if (needOut.isEmpty()) {
                                break;
                            }
                        }
                        if (!needOut.isEmpty()) {
                            nItemStack.add(needOut);
                        }
                    }
                    shapedHandle.outItem = nItemStack;
                }

                if (shapedHandle.outFluid != null && !shapedHandle.outFluid.isEmpty()) {
                    List<FluidStack> nFluidStack = new ArrayList<>();
                    for (FluidStack fluidStack : shapedHandle.outFluid) {
                        FluidStack needOut = fluidStack.copy();
                        for (java.util.Map.Entry<BlockEntity, IFluidHandler> tileEntityIFluidHandlerEntry : fluidOut.entrySet()) {
                            int surplus = tileEntityIFluidHandlerEntry.getValue().fill(needOut, IFluidHandler.FluidAction.EXECUTE);
                            if (surplus > 0) {
                                CommonParticle.FLUID_TRANSFER.add(iHandle.getThis().getLevel(),
                                        new Pos(iHandle.getThis().getBlockPos()),
                                        new Pos(tileEntityIFluidHandlerEntry.getKey().getBlockPos()),
                                        ColorPrefab.FLUID_IO,
                                        surplus / 32f);
                            }
                            needOut.setAmount(needOut.getAmount() - surplus);
                            if (needOut.getAmount() <= 0) {
                                break;
                            }
                        }
                        if (needOut.getAmount() > 0) {
                            nFluidStack.add(needOut);
                        }
                    }
                    shapedHandle.outFluid = nFluidStack;
                }
            }

        };
        trippingOperation = new ShapedHandleProcess("trippingOperation") {
            @Override
            public void up(IHandle iHandle, ShapedHandle shapedHandle, Map<BlockEntity, IManaHandle> manaIn, Map<BlockEntity, IManaHandle> manaOut) {

            }

            @Override
            public void clock(IHandle iHandle, ShapedHandle shapedHandle, Map<BlockEntity, IItemHandler> itemIn, Map<BlockEntity, IItemHandler> itemOut, Map<BlockEntity, IFluidHandler> fluidIn, Map<BlockEntity, IFluidHandler> fluidOut) {
                shapedHandle.process = production;
            }
        };
    }

    public ShapedHandleProcess(ResourceLocation name) {
        super(name, SHAPED_TYPE_PROCESS);
    }

    public ShapedHandleProcess(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

/*    *//***
     * 在配方更新时触发
     * @param shapedHandle 配方
     * @param iHandle 配方处理器
     * @param manaIn 灵气输入
     * @param manaOut 灵气输出
     *//*
    public abstract void up(IHandle iHandle, IHandle.ShapedHandle shapedHandle, Map<BlockEntity, IManaHandle> manaIn, Map<BlockEntity, IManaHandle> manaOut);*/

/*    *//***
     * 在时钟周期时触发
     * @param iHandle 配方处理器
     * @param shapedHandle 配方
     * @param itemOut 物品输出
     * @param fluidOut 流体输入
     *//*
    public abstract void clock(IHandle iHandle, IHandle.ShapedHandle shapedHandle,
                               Map<BlockEntity, IItemHandler> itemIn,
                               Map<BlockEntity, IItemHandler> itemOut,
                               Map<BlockEntity, IFluidHandler> fluidIn,
                               Map<BlockEntity, IFluidHandler> fluidOut);*/



}
