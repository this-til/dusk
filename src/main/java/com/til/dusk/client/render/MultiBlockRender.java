package com.til.dusk.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix4f;
import com.til.dusk.common.capability.multi_block.IMultiBlock;
import com.til.dusk.common.register.multiblock.DefaultBlockPack;
import com.til.dusk.common.register.multiblock.MultiBlock;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderBuffers;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @author til
 */
public class MultiBlockRender {

    @Nullable
    protected static BlockPos pos;
    @Nullable
    protected static List<DefaultBlockPack> list;

    public static <D> void set(BlockPos pos, MultiBlock<D> iMultiBlock, D d) {
        MultiBlockRender.pos = pos;
        MultiBlockRender.list = iMultiBlock.asDefaultBlockPack(d);
    }

    public static void removeRender() {
        pos = null;
        list = null;
    }

    public static void render(PoseStack ps, float partialTicks, long unknown, boolean drawBlockOutline,
                              Camera camera, GameRenderer gameRenderer, LightTexture lightTexture, Matrix4f projMat, CallbackInfo ci,
                              RenderBuffers renderBuffers, Level level) {
        if (pos == null || list == null) {
            return;
        }
        MultiBufferSource.BufferSource bufferSource = renderBuffers.bufferSource();
        VertexConsumer buffer = bufferSource.getBuffer(RenderTypeManage.MULTI_BLOCK_RENDER);
        long gameTime = level.getGameTime();
        long sequence = (gameTime / 20L);
        for (DefaultBlockPack defaultBlockPack : list) {
            List<BlockState> blockStates = defaultBlockPack.blockState();
            if (blockStates.size() <= 0) {
                continue;
            }
            BlockState blockState = blockStates.get((int) (sequence % blockStates.size()));
            for (BlockPos po : defaultBlockPack.pos()) {
                BlockPos blockPos = pos.offset(po);
                BlockState customary = level.getBlockState(blockPos);
                if (blockStates.contains(customary)) {
                    continue;
                }
                renderBlockAt(ps, buffer, blockState, blockPos);
            }
        }
        bufferSource.endBatch(RenderTypeManage.MULTI_BLOCK_RENDER);
    }

    private static void renderBlockAt(PoseStack ms, VertexConsumer buffer, BlockState state, BlockPos pos) {
        double renderPosX = Minecraft.getInstance().getEntityRenderDispatcher().camera.getPosition().x();
        double renderPosY = Minecraft.getInstance().getEntityRenderDispatcher().camera.getPosition().y();
        double renderPosZ = Minecraft.getInstance().getEntityRenderDispatcher().camera.getPosition().z();

        ms.pushPose();
        ms.translate(-renderPosX, -renderPosY, -renderPosZ);

        BlockRenderDispatcher brd = Minecraft.getInstance().getBlockRenderer();
        ms.translate(pos.getX(), pos.getY(), pos.getZ());
        BakedModel model = brd.getBlockModel(state);
        int color = Minecraft.getInstance().getBlockColors().getColor(state, null, null, 0);
        float r = (float) (color >> 16 & 255) / 255.0F;
        float g = (float) (color >> 8 & 255) / 255.0F;
        float b = (float) (color & 255) / 255.0F;
        brd.getModelRenderer().renderModel(ms.last(), buffer, state, model, r, g, b, 0xF000F0, OverlayTexture.NO_OVERLAY);

        ms.popPose();
    }
}
