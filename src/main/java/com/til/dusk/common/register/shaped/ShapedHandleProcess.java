package com.til.dusk.common.register.shaped;

import com.til.dusk.Dusk;
import com.til.dusk.common.capability.clock_time.EventClockTime;
import com.til.dusk.common.capability.handle.EventHandle;
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

            @SubscribeEvent
            public void onEvent(EventHandle.EventShapedHandle.Up event) {
                if (!event.shapedHandle.process.equals(this)) {
                    return;
                }
                long needGetMana = event.shapedHandle.consumeMana;
                for (Map.Entry<BlockEntity, IManaHandle> tileEntityIManaHandleEntry : event.manaIn.entrySet()) {
                    long mana = tileEntityIManaHandleEntry.getValue().extractMana(needGetMana);
                    needGetMana = needGetMana - mana;
                    if (rand.nextFloat() < mana / 320f) {
                        CommonParticle.MANA_TRANSFER.add(event.iHandle.getThis().getLevel(),
                                new Pos(tileEntityIManaHandleEntry.getKey().getBlockPos()),
                                new Pos(event.iHandle.getThis().getBlockPos()),
                                ColorPrefab.MANA_IO,
                                1);
                    }
                }
                if (needGetMana <= 0) {
                    event.shapedHandle._surplusTime--;
                    if (event.shapedHandle._surplusTime <= 0) {
                        event.shapedHandle.process = out;
                    }
                } else {
                    event.shapedHandle.process = trippingOperation;
                    event.shapedHandle._surplusTime = event.shapedHandle.surplusTime;
                }
            }


        };
        out = new ShapedHandleProcess("out") {

            @SubscribeEvent
            public void onEvent(EventHandle.EventShapedHandle.Up event) {
                if (!event.shapedHandle.process.equals(this)) {
                    return;
                }
                for (Map.Entry<BlockEntity, IManaHandle> tileEntityIManaHandleEntry : event.manaOut.entrySet()) {
                    long mana = tileEntityIManaHandleEntry.getValue().addMana(event.shapedHandle.outMana);
                    event.shapedHandle.outMana = event.shapedHandle.outMana - mana;
                    if (rand.nextFloat() < mana / 320f) {
                        CommonParticle.MANA_TRANSFER.add(event.iHandle.getThis().getLevel(),
                                new Pos(event.iHandle.getThis().getBlockPos()),
                                new Pos(tileEntityIManaHandleEntry.getKey().getBlockPos()),
                                ColorPrefab.MANA_IO,
                                1);
                    }
                }
            }

            @SubscribeEvent
            public void onEvent(EventHandle.EventShapedHandle.Clock event) {
                if (!event.shapedHandle.process.equals(this)) {
                    return;
                }
                if (event.shapedHandle.outItem != null && !event.shapedHandle.outItem.isEmpty()) {
                    List<ItemStack> nItemStack = new ArrayList<>();
                    for (ItemStack itemStack : event.shapedHandle.outItem) {
                        ItemStack needOut = itemStack;
                        for (Map.Entry<BlockEntity, IItemHandler> tileEntityIItemHandlerEntry : event.itemOut.entrySet()) {
                            ItemStack out = ItemHandlerHelper.insertItemStacked(tileEntityIItemHandlerEntry.getValue(), needOut, false);
                            if (out.getCount() < needOut.getCount()) {
                                CommonParticle.ITEM_TRANSFER.add(
                                        event.iHandle.getThis().getLevel(),
                                        new Pos(event.iHandle.getThis().getBlockPos()),
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
                    event.shapedHandle.outItem = nItemStack;
                }

                if (event.shapedHandle.outFluid != null && !event.shapedHandle.outFluid.isEmpty()) {
                    List<FluidStack> nFluidStack = new ArrayList<>();
                    for (FluidStack fluidStack : event.shapedHandle.outFluid) {
                        FluidStack needOut = fluidStack.copy();
                        for (java.util.Map.Entry<BlockEntity, IFluidHandler> tileEntityIFluidHandlerEntry : event.fluidOut.entrySet()) {
                            int surplus = tileEntityIFluidHandlerEntry.getValue().fill(needOut, IFluidHandler.FluidAction.EXECUTE);
                            if (surplus > 0) {
                                CommonParticle.FLUID_TRANSFER.add(event.iHandle.getThis().getLevel(),
                                        new Pos(event.iHandle.getThis().getBlockPos()),
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
                    event.shapedHandle.outFluid = nFluidStack;
                }
            }


        };
        trippingOperation = new ShapedHandleProcess("tripping_operation") {

            @SubscribeEvent
            public void onEvent(EventHandle.EventShapedHandle.Clock event) {
                if (!event.shapedHandle.process.equals(this)) {
                    return;
                }
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


}
