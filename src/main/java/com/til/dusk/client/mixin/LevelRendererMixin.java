package com.til.dusk.client.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Matrix4f;
import com.til.dusk.client.render.MultiBlockRender;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.*;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author til
 */
@Mixin(LevelRenderer.class)
public class LevelRendererMixin {
    @Shadow
    @Final
    private RenderBuffers renderBuffers;
    @Shadow
    @Nullable
    private ClientLevel level;

    @Inject(
            method = "renderLevel",
            at = @At(
                    shift = At.Shift.AFTER,
                    value = "INVOKE",
                    target = "Lcom/mojang/blaze3d/systems/RenderSystem;applyModelViewMatrix()V",
                    ordinal = 1 // after debugRenderer, before a long sequence of endBatch calls
            )
    )
    private void renderOverlays(PoseStack ps, float partialTicks, long unknown, boolean drawBlockOutline,
                                Camera camera, GameRenderer gameRenderer, LightTexture lightTexture, Matrix4f projMat, CallbackInfo ci) {
        if (level != null) {
            MultiBlockRender.render(ps, partialTicks, unknown, drawBlockOutline, camera, gameRenderer, lightTexture, projMat, ci, renderBuffers, level);
        }
    }
}
