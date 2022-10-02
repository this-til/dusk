package com.til.dusk.common.register.ore.item;

import com.til.dusk.Dusk;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.util.pack.ItemPack;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class OreItemDust extends OreItem {
    public OreItemDust(ResourceLocation name) {
        super(name);
    }

    public OreItemDust(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    @Override
    public @Nullable ItemPack create(Ore ore) {
        if (ore.hasSet(Ore.HAS_DUST)) {
            return super.create(ore);
        }
        return null;
    }
}
