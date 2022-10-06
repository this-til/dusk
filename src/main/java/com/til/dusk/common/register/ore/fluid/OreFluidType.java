package com.til.dusk.common.register.ore.fluid;

import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.Lang;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class OreFluidType extends FluidType {

    public final Ore ore;
    public final OreFluid oreFluid;

    public OreFluidType(Properties properties, Ore ore, OreFluid oreFluid) {
        super(properties);
        this.ore = ore;
        this.oreFluid = oreFluid;
    }

    @Override
    public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
        consumer.accept(new IClientFluidTypeExtensions() {
            @Override
            public ResourceLocation getStillTexture() {
                return oreFluid.stillTexture;
            }

            @Override
            public ResourceLocation getFlowingTexture() {
                return oreFluid.flowingTexture;
            }

            @Override
            public ResourceLocation getOverlayTexture() {
                return oreFluid.overlayTexture;
            }

            @Nullable
            @Override
            public ResourceLocation getRenderOverlayTexture(Minecraft mc) {
                return oreFluid.renderOverlayTexture;
            }

            @Override
            public int getTintColor() {
                return getColor().getRGB();
            }
        });
    }

    public DuskColor getColor() {
        return ore.color;
    }

    @Override
    public Component getDescription() {
        return Lang.getLang(Lang.getLang(ore), Lang.getLang(oreFluid));
    }

    @Override
    public Component getDescription(FluidStack stack) {
        return getDescription();
    }
}
