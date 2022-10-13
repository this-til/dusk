package com.til.dusk.common.register.mana_level.item.group;

import com.til.dusk.Dusk;
import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.register.mana_level.item.items.HostManaLevelItem;
import com.til.dusk.common.register.mana_level.item.items.IntegrateManaLevelItem;
import com.til.dusk.common.register.mana_level.item.items.ProcessorManaLevelItem;
import com.til.dusk.util.DuskColor;
import net.minecraft.resources.ResourceLocation;

/**
 * @author til
 */
public abstract class ManaLevelCrystalGroup extends ManaLevelItemGroup {
    public IntegrateManaLevelItem integrate;
    public ProcessorManaLevelItem processor;
    public HostManaLevelItem host;

    public ManaLevelCrystalGroup(ResourceLocation name) {
        super(name);
        integrate = new IntegrateManaLevelItem(this);
        processor = new ProcessorManaLevelItem(this);
        host = new HostManaLevelItem(this);
    }

    public ManaLevelCrystalGroup(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    @ConfigField
    public DuskColor coreColor;
    @ConfigField
    public DuskColor strokeColor;

}
