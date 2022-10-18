package com.til.dusk.common.register.multiblock;

import com.google.common.collect.ImmutableMap;
import com.til.dusk.Dusk;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.util.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.registries.tags.ITag;

import java.util.*;

/**
 * @author til
 */
public abstract class LevelMultiBlock extends MultiBlock<ManaLevel> {
    public LevelMultiBlock(ResourceLocation name) {
        super(name);
    }

    public LevelMultiBlock(String name) {
        super(name);
    }
}
