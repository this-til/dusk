package com.til.dusk.common.register.world.item.items;

import com.til.dusk.Dusk;
import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.data.ModRecipeProvider;
import com.til.dusk.common.register.key.EventKey;
import com.til.dusk.common.register.key.KeyRegister;
import com.til.dusk.common.register.bind_type.BindType;
import com.til.dusk.common.register.other.CapabilityRegister;
import com.til.dusk.common.register.particle_register.ParticleRegister;
import com.til.dusk.common.register.world.item.StaffItemRegister;
import com.til.dusk.util.prefab.ColorPrefab;
import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.eventbus.api.EventPriority;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author til
 */
public class ShowStaffRegister extends StaffItemRegister {

    public ShowStaffRegister() {
        super("show_staff");
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "显示法杖");
        lang.add(LangType.EN_CH, "Show Staff");
    }

    @Override
    public void registerRecipe(Consumer<RecipeBuilder> recipeConsumer) {
        recipeConsumer.accept(ShapedRecipeBuilder.shaped(pack.item())
                .define('A', diamondMakeGather.pack.itemTag())
                .define('B', Tags.Items.GEMS)
                .pattern("  A")
                .pattern(" B ")
                .pattern("B  ")
                .unlockedBy("has_ores", ModRecipeProvider.has(Tags.Items.ORES)));
    }

    @Override
    public void defaultConfig() {
        super.defaultConfig();
    }

    @Override
    protected Item createItem() {
        return new ShowStaffItem(new Item.Properties().stacksTo(1).tab(Dusk.TAB)) {
            @Override
            public @NotNull Component getName(@NotNull ItemStack stack) {
                return Component.translatable(name.toLanguageKey());
            }
        };
    }

    public static class ShowStaffItem extends Item {
        public ShowStaffItem(Properties properties) {
            super(properties);
            MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, (Consumer<EventKey>) event -> {
                if (!event.keyRegister.equals(KeyRegister.showBindState)) {
                    return;
                }
                ServerPlayer serverPlayer = event.contextSupplier.get().getSender();
                if (serverPlayer == null) {
                    return;
                }
                event.contextSupplier.get().enqueueWork(() -> {
                    ItemStack itemStack = serverPlayer.getMainHandItem();
                    if (itemStack.isEmpty()) {
                        return;
                    }
                    if (!itemStack.getItem().equals(this)) {
                        return;
                    }
                    display(serverPlayer);
                });
            });
        }

        @Override
        public InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand interactionHand) {
            display(player);
            return super.use(level, player, interactionHand);
        }

        public void display(Player player) {
            Level level = player.level;
            if (level.isClientSide()) {
                return;
            }
            int x = SectionPos.blockToSectionCoord(player.getX());
            int z = SectionPos.blockToSectionCoord(player.getZ());
            ChunkAccess chunkAccess = level.getChunk(x, z);
            for (BlockPos blockEntitiesPo : chunkAccess.getBlockEntitiesPos()) {
                BlockEntity blockEntity = chunkAccess.getBlockEntity(blockEntitiesPo);
                if (blockEntity == null) {
                    continue;
                }
                LazyOptional<IControl> optional = blockEntity.getCapability(CapabilityRegister.iControl.capability);
                if (!optional.isPresent()) {
                    continue;
                }
                showControl(player, optional.orElse(null));
            }

        }

        public static void showControl(Player player, IControl control) {
            ParticleRegister.block.add(player, ColorPrefab.CONTROL_TAG, 10, null, control.getPosTrack().getPos());
            for (Map.Entry<BindType, List<IPosTrack>> bindTypeListEntry : control.getAllBind().entrySet()) {
                for (IPosTrack blockPos : bindTypeListEntry.getValue()) {
                    ParticleRegister.line.add(player, bindTypeListEntry.getKey().color, 10, null, control.getPosTrack().getPos(), blockPos.getPos());
                }
            }
        }

    }
}
