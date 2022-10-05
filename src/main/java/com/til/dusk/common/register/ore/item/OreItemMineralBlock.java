package com.til.dusk.common.register.ore.item;

import com.til.dusk.Dusk;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.util.pack.ItemPack;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

/**
 * @author til
 */
public class OreItemMineralBlock extends OreItem {
    public OreItemMineralBlock(ResourceLocation name) {
        super(name);
    }

    public OreItemMineralBlock(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    @Override
    public @Nullable ItemPack create(Ore ore) {
        if (ore.getSet(Ore.MINERAL_BLOCK_DATA) != null) {
            return super.create(ore);
        }
        return null;
    }
}
