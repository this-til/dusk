package com.til.dusk.common.register.shaped;

import com.til.dusk.Dusk;
import com.til.dusk.common.capability.handle.EventHandle;
import com.til.dusk.common.capability.mana_handle.IManaHandle;
import com.til.dusk.common.capability.mana_handle.ManaHandleHelp;
import com.til.dusk.common.event.EventIO;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.util.Pos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
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
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author til
 */
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
            public void up(EventHandle.EventShapedHandle.Up event) {
                if (ManaHandleHelp.extractMana(event.shapedHandle.consumeMana, event.manaIn, event.iHandle.getThis()) < event.shapedHandle.outMana) {
                    event.shapedHandle.process = trippingOperation;
                    event.shapedHandle._surplusTime = event.shapedHandle.surplusTime;
                } else {
                    event.shapedHandle._surplusTime--;
                    if (event.shapedHandle._surplusTime <= 0) {
                        event.shapedHandle.process = out;
                    }
                }
            }

            @Override
            public void clock(EventHandle.EventShapedHandle.Clock event) {

            }
        };
        out = new ShapedHandleProcess("out") {

            @Override
            public void up(EventHandle.EventShapedHandle.Up event) {
                event.shapedHandle.outMana -= ManaHandleHelp.addMana(event.shapedHandle.outMana, event.manaOut, event.iHandle.getThis());
            }

            @Override
            public void clock(EventHandle.EventShapedHandle.Clock event) {
                if (event.shapedHandle.outItem != null && !event.shapedHandle.outItem.isEmpty()) {
                    List<ItemStack> nItemStack = new ArrayList<>();
                    for (ItemStack itemStack : event.shapedHandle.outItem) {
                        ItemStack needOut = itemStack;
                        for (Map.Entry<BlockEntity, IItemHandler> tileEntityIItemHandlerEntry : event.itemOut.entrySet()) {
                            ItemStack out = ItemHandlerHelper.insertItemStacked(tileEntityIItemHandlerEntry.getValue(), needOut, false);
                            if (out.getCount() < needOut.getCount()) {
                                MinecraftForge.EVENT_BUS.post(new EventIO.Item(event.iHandle.getThis().getLevel(),
                                        new Pos(event.iHandle.getThis().getBlockPos()),
                                        new Pos(tileEntityIItemHandlerEntry.getKey().getBlockPos()),
                                        new ItemStack(needOut.getItem(), needOut.getCount() - out.getCount())));
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
                    event.shapedHandle.outItem = nItemStack;
                }
                if (event.shapedHandle.outFluid != null && !event.shapedHandle.outFluid.isEmpty()) {
                    List<FluidStack> nFluidStack = new ArrayList<>();
                    for (FluidStack fluidStack : event.shapedHandle.outFluid) {
                        FluidStack needOut = fluidStack.copy();
                        for (java.util.Map.Entry<BlockEntity, IFluidHandler> tileEntityIFluidHandlerEntry : event.fluidOut.entrySet()) {
                            int surplus = tileEntityIFluidHandlerEntry.getValue().fill(needOut, IFluidHandler.FluidAction.EXECUTE);
                            if (surplus > 0) {
                                MinecraftForge.EVENT_BUS.post(new EventIO.Fluid(event.iHandle.getThis().getLevel(),
                                        new Pos(event.iHandle.getThis().getBlockPos()),
                                        new Pos(tileEntityIFluidHandlerEntry.getKey().getBlockPos()),
                                        new FluidStack(needOut.getFluid(), surplus)));
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
                    event.shapedHandle.outFluid = nFluidStack;
                }
            }
        };
        trippingOperation = new ShapedHandleProcess("tripping_operation") {

            @Override
            public void up(EventHandle.EventShapedHandle.Up event) {
            }

            @Override
            public void clock(EventHandle.EventShapedHandle.Clock event) {
                event.shapedHandle.process = production;
            }
        };
    }

    public ShapedHandleProcess(ResourceLocation name) {
        super(name, SHAPED_TYPE_PROCESS);
    }

    public ShapedHandleProcess(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    public abstract void up(EventHandle.EventShapedHandle.Up event);

    public abstract void clock(EventHandle.EventShapedHandle.Clock event);
}
