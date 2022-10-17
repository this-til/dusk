package com.til.dusk.common.register.world.item.items;

import com.til.dusk.Dusk;
import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.client.render.MultiBlockRender;
import com.til.dusk.common.capability.mana_level.IManaLevel;
import com.til.dusk.common.capability.multi_block.IMultiBlock;
import com.til.dusk.common.data.ModRecipeProvider;
import com.til.dusk.common.register.other.CapabilityRegister;
import com.til.dusk.common.register.world.item.ItemPackRegister;
import com.til.dusk.common.register.world.item.StaffItemRegister;
import com.til.dusk.util.Lang;
import net.minecraft.core.BlockPos;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * @author til
 */
public class MultiBlockStaffRegister extends StaffItemRegister {
    public MultiBlockStaffRegister() {
        super("multi_block_staff");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "阵法法杖");
        lang.add(LangType.EN_CH, "Law Staff");
    }

    @Override
    public void registerRecipe(Consumer<RecipeBuilder> recipeConsumer) {
        recipeConsumer.accept(ShapedRecipeBuilder.shaped(pack.item())
                .define('A', ItemPackRegister.showStaff.pack.itemTag())
                .define('B', Tags.Items.GEMS)
                .pattern("  A")
                .pattern(" B ")
                .pattern("B  ")
                .unlockedBy("has_ores", ModRecipeProvider.has(Tags.Items.ORES)));
    }

    @Override
    protected Item createItem() {
        return new MultiBlockStaffItem(new Item.Properties().tab(Dusk.TAB).stacksTo(1));
    }

    @Override
    public void defaultConfig() {

    }

    public static class MultiBlockStaffItem extends Item {
        public MultiBlockStaffItem(Properties properties) {
            super(properties);
        }

        @Override
        public InteractionResultHolder<ItemStack> use(Level level, Player player, @NotNull InteractionHand interactionHand) {
            ItemStack itemstack = player.getItemInHand(interactionHand);
            if (!level.isClientSide && player.isShiftKeyDown() ) {
                MultiBlockRender.removeRender();
                return InteractionResultHolder.success(itemstack);
            }
            return super.use(level, player, interactionHand);
        }

        @Override
        public @NotNull InteractionResult useOn(@NotNull UseOnContext useOnContext) {
            Level level = useOnContext.getLevel();
            if (!level.isClientSide) {
                return InteractionResult.PASS;
            }
            Player player = useOnContext.getPlayer();
            if (player == null) {
                return InteractionResult.PASS;
            }
            if (!useOnContext.getHand().equals(InteractionHand.MAIN_HAND)) {
                return InteractionResult.PASS;
            }
            if (player.isShiftKeyDown()) {
                return InteractionResult.PASS;

            } else {
                BlockPos blockPos = useOnContext.getClickedPos();
                BlockEntity blockEntity = level.getBlockEntity(blockPos);
                if (blockEntity == null) {
                    player.sendSystemMessage(Lang.getLang(Lang.getKey("错误，目标方块没有方块实体")));
                    return InteractionResult.SUCCESS;
                }
                Optional<IMultiBlock> iMultiBlockOptional = blockEntity.getCapability(CapabilityRegister.iMultiBlock.capability, useOnContext.getHorizontalDirection()).resolve();
                if (iMultiBlockOptional.isEmpty()) {
                    player.sendSystemMessage(Component.translatable(
                            Lang.getKey("错误，目标方块没有[{0}]"),
                            Lang.getLang(CapabilityRegister.iMultiBlock)
                    ));
                    return InteractionResult.SUCCESS;
                }
                IMultiBlock iMultiBlock = iMultiBlockOptional.get();
                Optional<IManaLevel> manaLevelOptional = blockEntity.getCapability(CapabilityRegister.iManaLevel.capability, useOnContext.getHorizontalDirection()).resolve();
                if (manaLevelOptional.isEmpty()) {
                    player.sendSystemMessage(Lang.getLang(Lang.getKey("错误，目标方块灵气等级丢失")));
                    return InteractionResult.SUCCESS;
                }
                MultiBlockRender.set(blockPos, iMultiBlock.getMultiBlock(), manaLevelOptional.get().manaLevel());
            }
            return InteractionResult.SUCCESS;
        }
    }

}
