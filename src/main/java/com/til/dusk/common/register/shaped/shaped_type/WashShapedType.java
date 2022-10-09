package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.ore.item.OreItem;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.util.pack.DataPack;
import com.til.dusk.common.config.util.IShapedCreate;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;

/**
 * @author til
 */
public class WashShapedType extends ShapedType {

    public WashShapedType() {
        super("wash", () -> ManaLevelBlock.wash);
    }

    @Override
    public void registerRuleShaped(Consumer<Shaped> shapedConsumer) {
        for (Ore ore : Ore.screen(Ore.MINERAL_BLOCK_DATA)) {
            new ShapedOre(this, ShapedDrive.get(0), ore.manaLevel)
                    .addInItem(ore.get(OreItem.crushed).itemTag(), 1)
                    .addInFluid(FluidTags.WATER, 1000)
                    .addOutItem(new ItemStack(ore.get(OreItem.crushedPurified).item(), 1), 1d)
                    .runThis(s -> {
                        DataPack.OreDataPack centrifugeByproduct = ore.getSet(Ore.MINERAL_BLOCK_DATA).washByproduct;
                        if (centrifugeByproduct != null) {
                            centrifugeByproduct.run(s, null);
                        }
                    })
                    .addMultipleSurplusTime((long) (1280L * ore.strength))
                    .addMultipleConsumeMana((long) (12L * ore.consume));
        }
    }

    @Override
    public void defaultConfig() {
        wash = new IShapedCreate.OreShapedCreate(name, this, ShapedDrive.get(0))
                .setSurplusTime(1280L)
                .setConsumeMana(12L)
                .addConfig(new ItemO);
    }

    @ConfigField
    public IShapedCreate<Ore> wash;

}
