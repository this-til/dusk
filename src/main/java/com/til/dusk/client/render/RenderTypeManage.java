package com.til.dusk.client.render;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.til.dusk.Dusk;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.resources.ResourceLocation;

/**
 * @author til
 */
public class RenderTypeManage {

    public static final RenderType MULTI_BLOCK_RENDER = new BlockRenderLayer(new ResourceLocation(Dusk.MOD_ID, "multi_block_render"));

    public static class BlockRenderLayer extends RenderType{
        public BlockRenderLayer(ResourceLocation name){
            super(name.toString(), DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 256, true, true,
                    () -> {
                        Sheets.translucentCullBlockSheet().setupRenderState();
                        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 0.4F);
                    }, () -> {
                        Sheets.translucentCullBlockSheet().clearRenderState();
                    });
        }

    }
}
