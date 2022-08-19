package com.til.dusk.capability;

import com.til.dusk.register.ManaLevel;
import net.minecraft.world.level.block.entity.BlockEntity;

/**
 * @author til
 */
public interface IManaLevel extends IThis<BlockEntity> {

    ManaLevel getManaLevel();

    class GetManaLevel implements IManaLevel {

        public final BlockEntity tileEntity;
        public final ManaLevel manaLevel;

        public GetManaLevel(BlockEntity tileEntity, ManaLevel manaLevel) {
            this.tileEntity = tileEntity;
            this.manaLevel = manaLevel;
        }

        @Override
        public ManaLevel getManaLevel() {
            return manaLevel;
        }


        /***
         * 返回自己
         */
        @Override
        public BlockEntity getThis() {
            return tileEntity;
        }
    }

}
