package com.til.dusk.common.capability.pos;

import com.til.dusk.util.Pos;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

/***
 * 一个位置跟踪能力
 * @author til
 */
public interface IPosTrack {
    Pos getPos();

    Level getLevel();

    boolean isTileEntity();

    BlockEntity getAsTileEntity();

    BlockPos getAsBlockPos();

    boolean isEntity();

    Entity getAsEntity();

    ICapabilityProvider getAsCapabilityProvider();

    static IPosTrack of(BlockEntity blockEntity) {
        Pos pos = new Pos(blockEntity);
        return new IPosTrack() {
            @Override
            public Pos getPos() {
                return pos;
            }

            @Override
            public Level getLevel() {
                return blockEntity.getLevel();
            }

            @Override
            public boolean isTileEntity() {
                return true;
            }

            @Override
            public BlockEntity getAsTileEntity() {
                return blockEntity;
            }

            @Override
            public BlockPos getAsBlockPos() {
                return blockEntity.getBlockPos();
            }

            @Override
            public boolean isEntity() {
                return false;
            }

            @Override
            public Entity getAsEntity() {
                return null;
            }

            @Override
            public ICapabilityProvider getAsCapabilityProvider() {
                return blockEntity;
            }

            @Override
            public boolean equals(Object obj) {
                return blockEntity.equals(obj);
            }
        };
    }

    static IPosTrack of(Entity entity) {
        return new IPosTrack() {
            @Override
            public Pos getPos() {
                return new Pos(entity);
            }

            @Override
            public Level getLevel() {
                return entity.level;
            }

            @Override
            public boolean isTileEntity() {
                return false;
            }

            @Override
            public BlockEntity getAsTileEntity() {
                return null;
            }

            @Override
            public BlockPos getAsBlockPos() {
                return getPos().blockPos();
            }

            @Override
            public boolean isEntity() {
                return true;
            }

            @Override
            public Entity getAsEntity() {
                return entity;
            }

            @Override
            public ICapabilityProvider getAsCapabilityProvider() {
                return entity;
            }

            @Override
            public boolean equals(Object obj) {
                return entity.equals(obj);
            }
        };
    }

}
