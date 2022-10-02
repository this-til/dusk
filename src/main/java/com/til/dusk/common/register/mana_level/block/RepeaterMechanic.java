package com.til.dusk.common.register.mana_level.block;

import com.til.dusk.Dusk;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.world.block.ModBlock;
import com.til.dusk.common.world.block.RepeaterBlock;
import com.til.dusk.util.prefab.JsonPrefab;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

/**
 * @author til
 */
public class RepeaterMechanic extends Mechanic {

    public RepeaterMechanic(ResourceLocation name) {
        super(name);
    }

    public RepeaterMechanic(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    @Override
    public Block createBlock(ManaLevel manaLevel) {
        return new RepeaterBlock(manaLevel);
    }

    @Override
    public ModBlock.ICustomModel getBlockModelMapping(ManaLevel manaLevel) {
        return new ModBlock.ICustomModel() {
            @Override
            public ResourceLocation blockModelName() {
                return name;
            }

            @Override
            public String blockJsonBasics() {
                return JsonPrefab.FACING_BLOCK_STATE_JSON;
            }
        };
    }


}