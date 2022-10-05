package com.til.dusk.common.register.ore.item;

import com.til.dusk.Dusk;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.util.pack.ItemPack;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

/**
 * @author til
 */
public class OreItemArmsBasics extends OreItem {
    public OreItemArmsBasics(ResourceLocation name) {
        super(name);
        setConfig(IS_ARMOR, null);
    }

    public OreItemArmsBasics(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    @Override
    public @Nullable ItemPack create(Ore ore) {
        if (ore.hasSet(Ore.ARMS_DATA)) {
            return super.create(ore);
        }
        return null;
    }

}
