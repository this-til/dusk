package com.til.dusk.common.register.ore.item;

import com.til.dusk.Dusk;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.capability.DuskCapabilityProvider;
import com.til.dusk.common.capability.IItemDefaultCapability;
import com.til.dusk.common.capability.fluid_handler.ItemVoidTankFluidHandler;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.Lang;
import com.til.dusk.util.pack.ItemPack;
import com.til.dusk.util.prefab.ColorPrefab;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 物品储罐
 *
 * @author til
 */
public class OreItemTank extends OreItem {

    public OreItemTank(ResourceLocation name) {
        super(name);
    }

    public OreItemTank(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    @Override
    public @Nullable ItemPack create(Ore ore) {
        if (ore.hasSet(Ore.TOOL_DATA)) {
            return super.create(ore);
        }
        return null;
    }

    @Override
    public Item createItem(Ore ore) {
        return new IItemDefaultCapability.CapabilityItem(new Item.Properties().tab(Dusk.TAB).stacksTo(1)) {

            @Override
            public void initCapability(AttachCapabilitiesEvent<ItemStack> event, DuskCapabilityProvider duskCapabilityProvider) {
                ItemVoidTankFluidHandler itemVoidTankFluidHandler = new ItemVoidTankFluidHandler(ore.getSet(Ore.TOOL_DATA).tankMax, event.getObject());
                duskCapabilityProvider.addCapability(ForgeCapabilities.FLUID_HANDLER, itemVoidTankFluidHandler);
                duskCapabilityProvider.addCapability(ForgeCapabilities.FLUID_HANDLER_ITEM, itemVoidTankFluidHandler);
            }

            @Override
            public @NotNull Component getName(@NotNull ItemStack itemStack) {
                Component basics = Lang.getLang(ore, OreItemTank.this);
                LazyOptional<IFluidHandlerItem> iFluidHandlerItemLazyOptional = itemStack.getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM, null);
                if (!iFluidHandlerItemLazyOptional.isPresent() ||
                    !(iFluidHandlerItemLazyOptional.orElse(null) instanceof ItemVoidTankFluidHandler voidTankFluidHandler) ||
                    voidTankFluidHandler.isEmpty()) {
                    return Lang.getLang(Component.translatable(Lang.getKey("空的")), basics);
                }
                FluidStack fluidStack = voidTankFluidHandler.getFluid();
                return Lang.getLang(Component.translatable(Lang.getKey("装有")),
                        fluidStack.getFluid().getFluidType().getDescription(fluidStack),
                        Component.translatable(Lang.getKey("的")),
                        basics);
            }
        };
    }

    @Override
    public void dyeBlack(Ore ore, ColorProxy.ItemColorPack itemColorPack) {
        super.dyeBlack(ore, itemColorPack);
        itemColorPack.addColor(1, itemStack -> {
            LazyOptional<IFluidHandlerItem> iFluidHandlerItemLazyOptional = itemStack.getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM, null);
            if (!iFluidHandlerItemLazyOptional.isPresent() ||
                !(iFluidHandlerItemLazyOptional.orElse(null) instanceof ItemVoidTankFluidHandler voidTankFluidHandler) ||
                voidTankFluidHandler.isEmpty()) {
                return ColorPrefab.EMPTY_TANK;
            }
            FluidStack fluidStack = voidTankFluidHandler.getFluid();
            IClientFluidTypeExtensions clientFluidTypeExtensions = IClientFluidTypeExtensions.of(fluidStack.getFluid().getFluidType());
            return new DuskColor(clientFluidTypeExtensions.getTintColor());
        });
    }
}