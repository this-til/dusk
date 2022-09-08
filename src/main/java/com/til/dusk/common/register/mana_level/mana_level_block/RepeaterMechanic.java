package com.til.dusk.common.register.mana_level.mana_level_block;

import com.til.dusk.Dusk;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.world.block.RepeaterBlock;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Material;
import org.jetbrains.annotations.NotNull;

public class RepeaterMechanic extends Mechanic{

    public RepeaterMechanic(ResourceLocation name) {
        super(name);
        removeTag(NEED_FRAME_UP);
    }

    public RepeaterMechanic(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    @Override
    public Block createBlock(ManaLevel manaLevel) {
        return new RepeaterBlock(manaLevel);
    }

    @Override
    public String getBlockStateJson() {
        return BLOCK_STATE_JSON;
    }

    public static final String BLOCK_STATE_JSON =
            """
                    {
                      "variants": {
                        "facing=down": {
                          "model": "dusk:block/repeater",
                          "x": 180
                        },
                        "facing=east": {
                          "model": "dusk:block/repeater",
                          "x": 90,
                          "y": 90
                        },
                        "facing=north": {
                          "model": "dusk:block/repeater",
                          "x": 90
                        },
                        "facing=south": {
                          "model": "dusk:block/repeater",
                          "x": 90,
                          "y": 180
                        },
                        "facing=up": {
                          "model": "dusk:block/repeater"
                        },
                        "facing=west": {
                          "model": "dusk:block/repeater",
                          "x": 90,
                          "y": 270
                        }
                      }
                    }
                    """;
}
