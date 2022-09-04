package com.til.dusk.common.capability.pos;

import com.til.dusk.util.Pos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.function.Supplier;

public record PosTrack(Supplier<Level> level, Supplier<Pos> pos) implements IPosTrack {
    @Override
    public Pos getPos() {
        return pos.get();
    }

    @Override
    public Level getLevel() {
        return level.get();
    }

    public static PosTrack of(BlockEntity blockEntity) {
        return new PosTrack(blockEntity::getLevel, () -> new Pos(blockEntity.getBlockPos()));
    }

    public static PosTrack of(Entity entity) {
        return new PosTrack(() -> entity.level, () -> new Pos(entity));
    }
}
