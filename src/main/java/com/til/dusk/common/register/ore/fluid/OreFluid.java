package com.til.dusk.common.register.ore.fluid;

import com.til.dusk.Dusk;
import com.til.dusk.common.register.FluidUnitRegister;
import com.til.dusk.common.register.ore.fluid.fluids.*;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.util.pack.FluidPack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OreFluid extends FluidUnitRegister<OreFluid, Ore> {

    public static Supplier<IForgeRegistry<OreFluid>> ORE_FLUID;

    /***
     * 溶液
     */
    public static SolutionOreFluid solution;

    /***
     * 加入uu的溶液
     */
    public static JoinUUSolutionOreFluid joinUUSolution;

    /***
     * 日耀
     */
    public static SplittingSunlightSolutionOreFluid splittingSunlightSolution;

    /***
     * 月耀
     */
    public static SplittingMoonlightSolutionOreFluid splittingMoonlightSolution;

    /***
     * 雨灵
     */
    public static SplittingRainSolutionOreFluid splittingRainSolution;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        ORE_FLUID = event.create(new RegistryBuilder<OreFluid>().setName(new ResourceLocation(Dusk.MOD_ID, "ore_fluid")));
        solution = new SolutionOreFluid();
        joinUUSolution = new JoinUUSolutionOreFluid();
        splittingSunlightSolution = new SplittingSunlightSolutionOreFluid();
        splittingMoonlightSolution = new SplittingMoonlightSolutionOreFluid();
        splittingRainSolution = new SplittingRainSolutionOreFluid();
    }

    public ResourceLocation stillTexture = STILL_TEXTURE;
    public ResourceLocation flowingTexture = FLOWING_TEXTURE;
    public ResourceLocation overlayTexture = OVERLAY_TEXTURE;
    public ResourceLocation renderOverlayTexture = RENDER_OVERLAY_TEXTURE;

    @Override
    public FluidPack create(Ore ore) {
        if (ore.fluidData != null) {
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

    @Override
    public void defaultConfig() {

    }

    public static final ResourceLocation STILL_TEXTURE = new ResourceLocation("block/water_still");
    public static final ResourceLocation FLOWING_TEXTURE = new ResourceLocation("block/water_flow");
    public static final ResourceLocation OVERLAY_TEXTURE = new ResourceLocation("block/water_overlay");
    public static final ResourceLocation RENDER_OVERLAY_TEXTURE = new ResourceLocation("textures/misc/underwater.png");

}
