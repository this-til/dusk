package com.til.dusk.common.world.item;

import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.register.BindType;
import com.til.dusk.common.register.CapabilityRegister;
import com.til.dusk.common.register.ParticleRegister;
import com.til.dusk.common.register.key.EventKey;
import com.til.dusk.common.register.key.KeyRegister;
import com.til.dusk.util.Pos;
import com.til.dusk.util.prefab.ColorPrefab;
import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
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
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.eventbus.api.EventPriority;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ShowStaffItem extends ItemBasics implements ModItem.IHasCustomColor {
    public ShowStaffItem() {
        super(new Item.Properties().stacksTo(1));
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
    public InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, InteractionHand interactionHand) {
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
        for (Map.Entry<BindType, List<BlockPos>> bindTypeListEntry : control.getAllBind().entrySet()) {
            for (BlockPos blockPos : bindTypeListEntry.getValue()) {
                ParticleRegister.line.add(player, bindTypeListEntry.getKey().color, 10, null, control.getPosTrack().getPos(), new Pos(blockPos));
            }
        }
    }

}
