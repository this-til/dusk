package com.til.dusk.client.other_mod_interact;

import com.til.dusk.Dusk;
import com.til.dusk.util.Lang;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.shaped.Shaped;
import com.til.dusk.common.register.shaped.ShapedType;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.forge.ForgeTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotView;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.runtime.IJeiRuntime;
import mezz.jei.common.Internal;
import mezz.jei.common.runtime.JeiHelpers;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author til
 */
@JeiPlugin
public class JEI_interact implements IModPlugin {
    @NotNull
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Dusk.MOD_ID, "main");
    }


    @Override
    public void registerRecipes(@NotNull IRecipeRegistration registration) {
        Shaped.MAP.forEach((k, v) -> {
            RecipeType<Shaped> shapedRecipeType = new RecipeType<>(k.name, Shaped.class);
            v.values().forEach(sl -> registration.addRecipes(shapedRecipeType, sl));
        });
    }

    @Override
    public void registerCategories(@NotNull IRecipeCategoryRegistration registry) {
        JeiHelpers jeiHelpers = Internal.getHelpers();
        IGuiHelper guiHelper = jeiHelpers.getGuiHelper();
        for (ShapedType shapedType : ShapedType.SHAPED_TYPE.get()) {
            registry.addRecipeCategories(new CurrencyCategory(guiHelper, shapedType));
        }
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
    }

    public static class CurrencyCategory implements IRecipeCategory<Shaped> {

        public static final int WIDTH = 144;
        public static final int HEIGHT = 54;

        public static final List<List<ItemStack>> INPUTS = new ArrayList<>();
        public static final List<List<ItemStack>> OUTPUTS = new ArrayList<>();
        public static final List<List<FluidStack>> INPUT_FLUID_STACKS = new ArrayList<>();
        public static final List<List<FluidStack>> OUTPUT_FLUID_STACKS = new ArrayList<>();

        public final ShapedType shapedType;
        public final IDrawable background;
        public final IDrawable icon;

        public CurrencyCategory(IGuiHelper guiHelper, ShapedType shapedType) {
            this.shapedType = shapedType;
            background = guiHelper.createDrawable(new ResourceLocation(Dusk.MOD_ID, "textures/gui/currency_category.png"), 0, 0, WIDTH, HEIGHT);
            icon = guiHelper.createDrawableItemStack(new ItemStack(ManaLevel.t8.blockMap.get(shapedType.manaLevelBlock())));
        }

        @Override
        public @NotNull RecipeType<Shaped> getRecipeType() {
            return new RecipeType<>(shapedType.name, Shaped.class);
        }

        @Override
        public @NotNull Component getTitle() {
            return Lang.getLang(shapedType);
        }

        @Override
        public void setRecipe(@NotNull IRecipeLayoutBuilder iRecipeLayoutBuilder, Shaped shaped, IFocusGroup iFocusGroup) {
            Shaped.IJEIShaped jeiShaped = shaped.getJEIShaped();
            List<List<ItemStack>> inputs = jeiShaped.getItemIn();
            List<List<ItemStack>> outputs = jeiShaped.getItemOut();
            List<List<FluidStack>> inputFluidStacks = jeiShaped.getFluidIn();
            List<List<FluidStack>> outputFluidStacks = jeiShaped.getFluidOut();
            if (inputs == null) {
                inputs = INPUTS;
            }
            if (outputs == null) {
                outputs = OUTPUTS;
            }
            if (inputFluidStacks == null) {
                inputFluidStacks = INPUT_FLUID_STACKS;
            }
            if (outputFluidStacks == null) {
                outputFluidStacks = OUTPUT_FLUID_STACKS;
            }

            int sID = 0;
            for (int y = 0; y < 3; ++y) {
                for (int x = 0; x < 3; ++x) {
                    if (sID < inputs.size()) {
                        IRecipeSlotBuilder iRecipeSlotBuilder = iRecipeLayoutBuilder.addSlot(RecipeIngredientRole.INPUT, x * 18 + 1, y * 18 + 1);
                        inputs.get(sID).forEach(iRecipeSlotBuilder::addItemStack);
                        iRecipeSlotBuilder.addTooltipCallback(this::itemToolBlack);
                    } else {
                        if (sID - inputFluidStacks.size() < inputFluidStacks.size()) {
                            IRecipeSlotBuilder iRecipeSlotBuilder = iRecipeLayoutBuilder.addSlot(RecipeIngredientRole.INPUT, x * 18 + 1, y * 18 + 1);
                            inputFluidStacks.get(sID - inputs.size()).forEach(f -> iRecipeSlotBuilder.addFluidStack(f.getFluid(), f.getAmount(), f.getTag()));
                            iRecipeSlotBuilder.addTooltipCallback(this::fluidToolBlack);
                        }
                    }
                    sID++;
                }
            }

            sID = 0;
            for (int y = 0; y < 3; ++y) {
                for (int x = 0; x < 3; ++x) {
                    if (sID < outputs.size()) {
                        IRecipeSlotBuilder iRecipeSlotBuilder = iRecipeLayoutBuilder.addSlot(RecipeIngredientRole.OUTPUT, x * 18 + 91, y * 18 + 1);
                        outputs.get(sID).forEach(iRecipeSlotBuilder::addItemStack);
                        iRecipeSlotBuilder.addTooltipCallback(this::itemToolBlack);
                    } else {
                        if (sID - outputFluidStacks.size() < outputFluidStacks.size()) {
                            IRecipeSlotBuilder iRecipeSlotBuilder = iRecipeLayoutBuilder.addSlot(RecipeIngredientRole.OUTPUT, x + 91, y * 18 + 1);
                            outputFluidStacks.get(sID - inputs.size()).forEach(f -> iRecipeSlotBuilder.addFluidStack(f.getFluid(), 1000, f.getTag()));
                            iRecipeSlotBuilder.addTooltipCallback(this::fluidToolBlack);
                        }
                    }
                    sID++;
                }
            }
        }

        @Override
        public @NotNull List<Component> getTooltipStrings(Shaped shaped, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
            if (mouseX > 54 && mouseX < 90 && mouseY > 18 && mouseY < 36) {
                return shaped.getComponent();
            }
            return List.of();
        }

        public void fluidToolBlack(IRecipeSlotView iRecipeSlotView, List<Component> listComponent) {
            Optional<FluidStack> fluidStackOptional = iRecipeSlotView.getDisplayedIngredient(ForgeTypes.FLUID_STACK);
            if (fluidStackOptional.isPresent()) {
                FluidStack fluidStack = fluidStackOptional.get();
                CompoundTag compoundTag = fluidStack.getTag();
                if (compoundTag == null) {
                    return;
                }
                if (compoundTag.contains(Shaped.RandOutOreShaped.MB)) {
                    listComponent.add(Component.nullToEmpty(compoundTag.getInt(Shaped.RandOutOreShaped.MB) + "mb"));
                }
                if (compoundTag.contains(Shaped.RandOutOreShaped.PROBABILITY) && compoundTag.getDouble(Shaped.RandOutOreShaped.PROBABILITY) < 1) {
                    DecimalFormat df = new DecimalFormat("0.00%");
                    listComponent.add(Component.nullToEmpty(Lang.getLang("probability") + df.format(compoundTag.getDouble(Shaped.RandOutOreShaped.MB))));
                }
            }
        }

        public void itemToolBlack(IRecipeSlotView iRecipeSlotView, List<Component> listComponent) {
            Optional<ItemStack> itemStackOptional = iRecipeSlotView.getDisplayedItemStack();
            if (itemStackOptional.isPresent()) {
                ItemStack itemStack = itemStackOptional.get();
                CompoundTag compoundTag = itemStack.getTag();
                if (compoundTag == null) {
                    return;
                }
                if (compoundTag.contains(Shaped.RandOutOreShaped.PROBABILITY) && compoundTag.getDouble(Shaped.RandOutOreShaped.PROBABILITY) < 1) {
                    DecimalFormat df = new DecimalFormat("0.00%");
                    listComponent.add(Component.nullToEmpty(Lang.getLang(Shaped.RandOutOreShaped.MB) + df.format(compoundTag.getDouble(Shaped.RandOutOreShaped.MB))));
                }
            }
        }


        @Override
        public @NotNull IDrawable getBackground() {
            return background;
        }

        @Override
        public @NotNull IDrawable getIcon() {
            return icon;
        }

    }
}
