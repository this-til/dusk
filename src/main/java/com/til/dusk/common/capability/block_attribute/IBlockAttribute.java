package com.til.dusk.common.capability.block_attribute;

import com.til.dusk.common.capability.black.IBack;
import com.til.dusk.common.capability.clock.IClock;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.register.attribute.block.BlockAttribute;
import com.til.dusk.common.register.other.CapabilityRegister;
import com.til.dusk.util.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Optional;

/**
 * @author til
 */
public interface IBlockAttribute {

    /***
     * 获取方块属性
     * @param nBlockAttribute 属性名
     * @return 该数据的值
     * @param <N> 数据类型
     */
    <N extends Number> N get(BlockAttribute<N> nBlockAttribute);

    class Pack implements IBlockAttribute {
        public final IPosTrack posTrack;
        public final IClock clock;
        @Nullable
        public Map<BlockAttribute<?>, ?> map;

        public Pack(IPosTrack posTrack, IClock clock) {
            this.posTrack = posTrack;
            this.clock = clock;
            clock.addBlock(this::back);
        }

        public void back() {
            BlockPos blockPos = posTrack.getAsBlockPos().offset(0, -1, 0);
            Level level = posTrack.getLevel();
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if (blockEntity == null) {
                map = null;
                return;
            }
            Optional<IBlockAttributeSupplier> optional = blockEntity.getCapability(CapabilityRegister.iBlockAttributeSupplier.capability, Direction.UP).resolve();
            if (optional.isEmpty()) {
                map = null;
                return;
            }
            map = optional.get().get();
        }

        @Override
        public <N extends Number> N get(BlockAttribute<N> nBlockAttribute) {
            if (map == null || !map.containsKey(nBlockAttribute)) {
                return nBlockAttribute.range.ofValue(1);
            }
            return nBlockAttribute.range.ofValue((Number) map.get(nBlockAttribute));
        }
    }
}
