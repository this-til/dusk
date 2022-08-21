package com.til.dusk.client.other_mod_interact;

import com.til.dusk.Dusk;
import com.til.dusk.client.util.Lang;
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
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.registration.IModIngredientRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.ISubtypeRegistration;
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
import java.util.Objects;
import java.util.Optional;

@JeiPlugin
public class JEI_interact implements IModPlugin {
    @NotNull
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Dusk.MOD_ID, "main");
    }


    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        Shaped.MAP.forEach((k, v) -> {
            RecipeType<Shaped> shapedRecipeType = new RecipeType<>(k.name, Shaped.class);
            v.values().forEach(sl -> registration.addRecipes(shapedRecipeType, sl));
        });
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
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

        public static final int width = 144;
        public static final int height = 54;

        public final ShapedType shapedType;
        public final IDrawable background;
        public final IDrawable icon;

        public CurrencyCategory(IGuiHelper guiHelper, ShapedType shapedType) {
            this.shapedType = shapedType;
            background = guiHelper.createDrawable(new ResourceLocation(Dusk.MOD_ID, "textures/gui/currency_category.png"), 0, 0, width, height);
            icon = guiHelper.createDrawableItemStack(new ItemStack(ManaLevel.t8.blockMap.get(shapedType.manaLevelBlock)));
        }

        @Override
        public @NotNull RecipeType<Shaped> getRecipeType() {
            return new RecipeType<>(shapedType.name, Shaped.class);
        }

        @Override
        public @NotNull Component getTitle() {
            return Component.translatable(Lang.getLang(shapedType));
        }

        @Override
        public void setRecipe(@NotNull IRecipeLayoutBuilder iRecipeLayoutBuilder, Shaped shaped, IFocusGroup iFocusGroup) {
            Shaped.IJEIShaped jeiShaped = shaped.getJEIShaped();
            List<List<ItemStack>> inputs = jeiShaped.getItemIn();
            List<List<ItemStack>> outputs = jeiShaped.getItemOut();
            List<List<FluidStack>> inputFluidStacks = jeiShaped.getFluidIn();
            List<List<FluidStack>> outputFluidStacks = jeiShaped.getFluidOut();

            int sID = 0;
            for (int y = 0; y < 3; ++y) {
                for (int x = 0; x < 3; ++x) {
                    if (inputs != null && sID < inputs.size()) {
                        IRecipeSlotBuilder iRecipeSlotBuilder = iRecipeLayoutBuilder.addSlot(RecipeIngredientRole.INPUT, x * 18, y * 18);
                        inputs.get(sID).forEach(iRecipeSlotBuilder::addItemStack);
                        iRecipeSlotBuilder.addTooltipCallback(this::itemToolBlack);
                    } else {
                        if (inputFluidStacks != null && sID - inputFluidStacks.size() < inputFluidStacks.size()) {
                            IRecipeSlotBuilder iRecipeSlotBuilder = iRecipeLayoutBuilder.addSlot(RecipeIngredientRole.INPUT, x * 18, y * 18);
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
                    if (outputs != null && sID < outputs.size()) {
                        IRecipeSlotBuilder iRecipeSlotBuilder = iRecipeLayoutBuilder.addSlot(RecipeIngredientRole.OUTPUT, 90 + x * 18, y * 18);
                        outputs.get(sID).forEach(iRecipeSlotBuilder::addItemStack);
                        iRecipeSlotBuilder.addTooltipCallback(this::itemToolBlack);
                    } else {
                        if (outputFluidStacks != null && sID - outputFluidStacks.size() < outputFluidStacks.size()) {
                            IRecipeSlotBuilder iRecipeSlotBuilder = iRecipeLayoutBuilder.addSlot(RecipeIngredientRole.OUTPUT, 90 + x * 18, y * 18);
                            outputFluidStacks.get(sID - inputs.size()).forEach(f -> iRecipeSlotBuilder.addFluidStack(f.getFluid(), 1000, f.getTag()));
                            iRecipeSlotBuilder.addTooltipCallback(this::fluidToolBlack);
                        }
                    }
                    sID++;
                }
            }


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
                if (compoundTag.contains(Shaped.RandOutOreShaped.PROBABILITY)) {
                    DecimalFormat df = new DecimalFormat("0.00%");
                    listComponent.add(Component.nullToEmpty(Lang.getLang("probability") + df.format(compoundTag.getDouble("probability"))));
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
                if (compoundTag.contains(Shaped.RandOutOreShaped.PROBABILITY)) {
                    DecimalFormat df = new DecimalFormat("0.00%");
                    listComponent.add(Component.nullToEmpty(Lang.getLang("probability") + df.format(compoundTag.getDouble("probability"))));
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


    /*public static class ShapedRecipeWrapper implements IRecipeWrapper {

        public final HoverChecker buttonHoverChecker;
        public final Map<String, HoverChecker> hoverCheckerMap = new Map<>();
        public final Shaped shaped;

        public ShapedRecipeWrapper(Shaped shaped) {
            GuiButton guiButton = new GuiButton(0, 61, 19, 22, 15, "");
            buttonHoverChecker = new HoverChecker(guiButton, -1);
            this.shaped = shaped;
        }

        @Override
        public void getIngredients(IIngredients ingredients) {
            hoverCheckerMap.clear();
            Shaped.IJEIShaped ijeiShaped = shaped.getIJEIShaped();
            List<List<ItemStack>> itemIn = ijeiShaped.getItemIn();
            if (itemIn != null) {
                ingredients.setInputLists(VanillaTypes.ITEM, itemIn);
            }
            List<List<FluidStack>> fluidIn = ijeiShaped.getFluidIn();
            if (fluidIn != null) {
                ingredients.setInputLists(VanillaTypes.FLUID, fluidIn);
            }
            List<List<FluidStack>> fluidOut = ijeiShaped.getFluidOut();
            if (fluidOut != null) {
                ingredients.setOutputLists(VanillaTypes.FLUID, fluidOut);
            }
            List<List<ItemStack>> itemOut = ijeiShaped.getItemOut();
            if (itemOut != null) {
                ingredients.setOutputLists(VanillaTypes.ITEM, itemOut);
            }
        }

        @Override
        public List<String> getTooltipStrings(int mouseX, int mouseY) {
            List<String> tooltipStrings = new ArrayList<>();
            if (buttonHoverChecker.checkHover(mouseX, mouseY)) {
                tooltipStrings.add(Lang.getLang("message"));

                tooltipStrings.add(Lang.getLang("use.mana.level") + Lang.getLang(shaped.getManaLevel()));
                tooltipStrings.add(Lang.getLang("use.shaped.drive") + ShapedDrive.map.getKey(shaped.getShapedDrive()));
                if (shaped.consumeMana() > 0) {
                    tooltipStrings.add(Lang.getLang("consume.mana") + shaped.consumeMana());
                }
                if (shaped.surplusTiem() > 0) {
                    tooltipStrings.add(Lang.getLang("consume.time") + shaped.surplusTiem());
                }
                if (shaped.outMana() > 0) {
                    tooltipStrings.add(Lang.getLang("out.mana") + shaped.outMana());
                }
            }
            return tooltipStrings;
        }
    }*/

}
