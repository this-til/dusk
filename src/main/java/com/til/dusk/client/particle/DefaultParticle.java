package com.til.dusk.client.particle;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import com.til.dusk.Dusk;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.Pos;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;

import java.util.HashMap;
import java.util.Map;

/**
 * @author til
 */
public class DefaultParticle extends Particle {

    @Nullable
    public final ResourceLocation textureName;

    public float particleHalfAge;

    /***
     * 粒子大小
     */
    public float size;

    /***
     * 当前粒子大小
     */
    public float currentScale;

    /***
     * 粒子大小随时间变换值
     */
    public float particleEveryTimeUpScale;

    public DefaultParticle(ClientLevel clientLevel, Pos pos, @Nullable ResourceLocation textureName, DuskColor color, Pos move, float size, int maxAge) {
        super(clientLevel, pos.x, pos.y, pos.z);
        this.textureName = textureName;
        setColor(color);
        setMove(move);
        setSize(size);
        setLifetime(maxAge);
        hasPhysics = false;
    }

    public void setColor(DuskColor color) {
        setColor(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f);
        setAlpha(color.getAlpha() / 255f);
    }

    public void setSize(float size) {
        this.size = size;

    }

    @Override
    public void setLifetime(int lifetime) {
        super.setLifetime(lifetime);
        particleHalfAge = lifetime * 0.5f;
        particleEveryTimeUpScale = size / lifetime;
    }

    public void setMove(Pos pos) {
        xd = pos.x;
        yd = pos.y;
        zd = pos.z;
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ >= this.lifetime) {
            this.remove();
            return;
        }
        this.move(this.xd, this.yd, this.zd);
        if (this.age < particleHalfAge) {
            this.currentScale += this.particleEveryTimeUpScale;
        } else {
            this.currentScale -= this.particleEveryTimeUpScale;
            if (this.currentScale <= 0) {
                this.remove();
            }
        }
    }

    @Override
    public void render(@NotNull VertexConsumer vertexConsumer, Camera camera, float time) {
        Vec3 vec3 = camera.getPosition();
        Vector3f lPos = new Vector3f((float) (Mth.lerp(time, this.xo, this.x) - vec3.x()), (float) (Mth.lerp(time, this.yo, this.y) - vec3.y()), (float) (Mth.lerp(time, this.zo, this.z) - vec3.z()));
        Quaternion quaternion;
        if (this.roll == 0.0F) {
            quaternion = camera.rotation();
        } else {
            quaternion = new Quaternion(camera.rotation());
            float f3 = Mth.lerp(time, this.oRoll, this.roll);
            quaternion.mul(Vector3f.ZP.rotation(f3));
        }

        Vector3f[] avector3f = new Vector3f[]{
                new Vector3f(-1.0F, -1.0F, 0.0F),
                new Vector3f(-1.0F, 1.0F, 0.0F),
                new Vector3f(1.0F, 1.0F, 0.0F),
                new Vector3f(1.0F, -1.0F, 0.0F)
        };

        for (int i = 0; i < 4; ++i) {
            Vector3f vector3f = avector3f[i];
            vector3f.transform(quaternion);
            vector3f.mul(currentScale);
            vector3f.add(lPos);
        }

        int combined = 15 << 20 | 15 << 4;
        vertexConsumer.vertex(avector3f[0].x(), avector3f[0].y(), avector3f[0].z()).uv(0, 0).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(combined).endVertex();
        vertexConsumer.vertex(avector3f[1].x(), avector3f[1].y(), avector3f[1].z()).uv(0, 1).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(combined).endVertex();
        vertexConsumer.vertex(avector3f[2].x(), avector3f[2].y(), avector3f[2].z()).uv(1, 1).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(combined).endVertex();
        vertexConsumer.vertex(avector3f[3].x(), avector3f[3].y(), avector3f[3].z()).uv(1, 0).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(combined).endVertex();


    }

    @Override
    @NotNull
    public ParticleRenderType getRenderType() {
        if (textureName == null) {
            return NULL_TEXTURE;
        }
        if (map.containsKey(textureName)) {
            return map.get(textureName);
        } else {
            ParticleRenderType particleRenderType = new ParticleRenderType() {
                @Override
                public void begin(BufferBuilder bufferBuilder, TextureManager textureManager) {
                    Minecraft.getInstance().gameRenderer.lightTexture().turnOnLightLayer();
                    RenderSystem.depthMask(false);
                    RenderSystem.enableBlend();
                    RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);

                    RenderSystem.setShaderTexture(0, textureName);
                    AbstractTexture tex = textureManager.getTexture(textureName);
                    tex.setBlurMipmap(true, false);
                    bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.PARTICLE);
                }

                @Override
                public void end(Tesselator tesselator) {
                    tesselator.end();
                    @SuppressWarnings("deprecation") AbstractTexture tex = Minecraft.getInstance().getTextureManager().getTexture(TextureAtlas.LOCATION_PARTICLES);
                    tex.restoreLastBlurMipmap();
                    RenderSystem.disableBlend();
                    RenderSystem.depthMask(true);
                }
            };
            map.put(textureName, particleRenderType);
            return particleRenderType;
        }
    }

    public static final Map<ResourceLocation, ParticleRenderType> map = new HashMap<>();

    public static final ParticleRenderType NULL_TEXTURE = new ParticleRenderType() {
        @Override
        public void begin(BufferBuilder bufferBuilder, @NotNull TextureManager textureManager) {
            Minecraft.getInstance().gameRenderer.lightTexture().turnOnLightLayer();
            RenderSystem.depthMask(false);
            RenderSystem.enableBlend();
            RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
            bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.PARTICLE);
        }

        @Override
        public void end(@NotNull Tesselator tesselator) {
            tesselator.end();
            RenderSystem.disableBlend();
            RenderSystem.depthMask(true);
        }

        @Override
        public String toString() {
            return Dusk.MOD_ID + ":" + "null_texture";
        }
    };
}
