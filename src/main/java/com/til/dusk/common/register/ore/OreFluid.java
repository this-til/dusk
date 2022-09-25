package com.til.dusk.common.register.ore;

import com.til.dusk.Dusk;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.Lang;
import com.til.dusk.util.pack.FluidPack;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OreFluid extends RegisterBasics.FluidUnitRegister<OreFluid, Ore> {

    public static Supplier<IForgeRegistry<OreFluid>> ORE_FLUID;

    /***
     * 溶液
     */
    public static OreFluid solution;

    /***
     * 加入uu的溶液
     */
    public static OreFluid joinUUSolution;

    /***
     * 日耀
     */
    public static OreFluidSplitting splittingSunlightSolution;

    /***
     * 月耀
     */
    public static OreFluidSplitting splittingMoonlightSolution;

    /***
     * 雨灵
     */
    public static OreFluidSplitting splittingRainSolution;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        ORE_FLUID = event.create(new RegistryBuilder<OreFluid>().setName(new ResourceLocation(Dusk.MOD_ID, "ore_fluid")));
        solution = new OreFluid("solution");
        joinUUSolution = new OreFluid("join_uu_solution") {
            @Override
            public FluidPack create(Ore ore) {
                if (ore.hasSet(Ore.FLUID_DATA) && ore.getSet(Ore.FLUID_DATA).canCopy) {
                    return super.create(ore);
                }
                return null;
            }
        };
        splittingSunlightSolution = new OreFluidSplitting("splitting_sunlight_solution", () -> Ore.sunlight.color);
        splittingMoonlightSolution = new OreFluidSplitting("splitting_moonlight_solution", () -> Ore.moonlight.color);
        splittingRainSolution = new OreFluidSplitting("splitting_rain_solution", () -> Ore.rain.color);
    }

    public ResourceLocation stillTexture = STILL_TEXTURE;
    public ResourceLocation flowingTexture = FLOWING_TEXTURE;
    public ResourceLocation overlayTexture = OVERLAY_TEXTURE;
    public ResourceLocation renderOverlayTexture = RENDER_OVERLAY_TEXTURE;

    @Override
    public FluidPack create(Ore ore) {
        if (ore.hasSet(Ore.FLUID_DATA)) {
            return super.create(ore);
        }
        return null;
    }

    @Override
    public FluidType createFluidType(Ore ore) {
        return new OreFluidType(FluidType.Properties.create(), ore, this);
    }

    public OreFluid(ResourceLocation name) {
        super(name, ORE_FLUID);
    }

    public OreFluid(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    @Override
    public @Nullable BucketItem createBanner(Ore ore, FlowingFluid source) {
        return null;
    }

    public static class OreFluidSplitting extends OreFluid {
        public final Supplier<DuskColor> color;

        public OreFluidSplitting(ResourceLocation name, Supplier<DuskColor> color) {
            super(name);
            this.color = color;
        }

        public OreFluidSplitting(String name, Supplier<DuskColor> color) {
            this(new ResourceLocation(Dusk.MOD_ID, name), color);
        }

        @Override
        public FluidPack create(Ore ore) {
            if (ore.hasSet(Ore.FLUID_DATA) && ore.getSet(Ore.FLUID_DATA).hasSplitting) {
                return super.create(ore);
            }
            return null;
        }

        @Override
        public FluidType createFluidType(Ore ore) {
            return new OreFluidType(FluidType.Properties.create(), ore, this) {
                @Override
                public DuskColor getColor() {
                    return ore.color.blend(color.get());
                }
            };
        }
    }

    public static class OreFluidType extends FluidType {

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

    public static final ResourceLocation STILL_TEXTURE = new ResourceLocation("block/water_still");
    public static final ResourceLocation FLOWING_TEXTURE = new ResourceLocation("block/water_flow");
    public static final ResourceLocation OVERLAY_TEXTURE = new ResourceLocation("block/water_overlay");
    public static final ResourceLocation RENDER_OVERLAY_TEXTURE = new ResourceLocation("textures/misc/underwater.png");

    public static class FluidData {
        public final Ore ore;

        /***
         * 能不能被uu复制
         */
        public boolean canCopy = false;

        /***
         * 能够裂解
         */
        public boolean hasSplitting = false;

        public FluidData(Ore ore) {
            this.ore = ore;
        }

        public FluidData setCanCopy(boolean canCopy) {
            this.canCopy = canCopy;
            return this;
        }

        public FluidData setHasSplitting(boolean hasSplitting) {
            this.hasSplitting = hasSplitting;
            return this;
        }
    }
}
