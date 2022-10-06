package com.til.dusk.common.register.ore.item;

import com.til.dusk.Dusk;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.config.ConfigMap;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.ore.ore.OreConfig;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.pack.ItemPack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.Nullable;

/**
 * @author til
 */
public abstract class OreItemArms extends OreItem {
    public OreItemArms(ResourceLocation name) {
        super(name);
        setConfig(IS_ARMS, null);
    }

    public OreItemArms(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    @Override
    public @Nullable ItemPack create(Ore ore) {
        if (ore.hasConfig(OreConfig.ArmsConfig.ARMS_CONFIG)) {
            return super.create(ore);
        }
        return null;
    }

    @Override
    public Item createItem(Ore ore) {
        return createArmsItem(ore, ore.getConfig(OreConfig.ArmsConfig.ARMS_CONFIG));
    }

    /***
     * 创建武器物品
     * @param ore 矿物
     * @param armsData 武器数据
     * @return 武器
     */
    public abstract Item createArmsItem(Ore ore, ConfigMap armsData);

    @Override
    public void dyeBlack(Ore ore, ColorProxy.ItemColorPack itemColorPack) {
        super.dyeBlack(ore, itemColorPack);
        DuskColor color = ore.getConfig(OreConfig.COLOR);
        itemColorPack.addColor(1, itemStack -> color);
    }
}
