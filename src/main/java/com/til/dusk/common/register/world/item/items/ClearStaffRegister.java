package com.til.dusk.common.register.world.item.items;

import com.til.dusk.Dusk;
import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.data.ModRecipeProvider;
import com.til.dusk.common.register.other.CapabilityRegister;
import com.til.dusk.common.register.world.item.ItemPackRegister;
import com.til.dusk.common.world.item.DuskItem;
import com.til.dusk.common.world.item.ItemBasics;
import com.til.dusk.util.Lang;
import net.minecraft.core.BlockPos;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.util.LazyOptional;

import java.util.function.Consumer;

/**
 * @author til
 */
public class ClearStaffRegister extends ItemPackRegister {

    public ClearStaffRegister() {
        super("clear_staff");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "清除法杖");
        lang.add(LangType.EN_CH, "Clear Staff");
    }

    @Override
    public void registerRecipe(Consumer<RecipeBuilder> recipeConsumer) {
        recipeConsumer.accept(ShapedRecipeBuilder.shaped(pack.item())
                .define('A', diamondMakeDestruction.pack.itemTag())
                .define('B', Tags.Items.GEMS)
                .pattern("  A")
                .pattern(" B ")
                .pattern("B  ")
                .unlockedBy("has_ores", ModRecipeProvider.has(Tags.Items.ORES)));
    }

    @Override
    public void defaultConfig() {

    }

    @Override
    protected Item createItem() {
        return new ClearStaffItem(new Item.Properties().stacksTo(1).tab(Dusk.TAB));
    }

    /**
     * @author til
     */
    public static class ClearStaffItem extends ItemBasics implements DuskItem.IHasCustomColor {
        public ClearStaffItem(Properties properties) {
            super(properties);
        }

        @Override
        public InteractionResult useOn(UseOnContext useOnContext) {
            Level level = useOnContext.getLevel();
            if (level.isClientSide) {
                return InteractionResult.PASS;
            }
            Player player = useOnContext.getPlayer();
            if (player == null) {
                return InteractionResult.PASS;
            }
            if (!useOnContext.getHand().equals(InteractionHand.MAIN_HAND)) {
                return InteractionResult.PASS;
            }
            BlockPos blockPos = useOnContext.getClickedPos();
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if (blockEntity == null) {
                player.sendSystemMessage(Lang.getLang(Lang.getKey("错误，目标方块没有方块实体")));
                return InteractionResult.SUCCESS;
            }
            LazyOptional<IControl> lazyOptional = blockEntity.getCapability(CapabilityRegister.iControl.capability);
            if (!lazyOptional.isPresent()) {
                player.sendSystemMessage(Lang.getLang(Lang.getKey("错误，目标方块没有控制器")));
                return InteractionResult.SUCCESS;
            }
            lazyOptional.orElse(null).unBundlingAll();
            player.sendSystemMessage(Lang.getLang(Lang.getKey("已经清除控制器数据")));
            return InteractionResult.SUCCESS;
        }
    }
}
