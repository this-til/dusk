package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author til
 */
public class ExtractManaShapedType extends ShapedType {

    public ExtractManaShapedType() {
        super("extract_mana", () -> ManaLevelBlock.extractMana);
    }

    @Override
    public void registerRuleShaped(Consumer<Shaped> shapedConsumer) {
    }

    @Override
    public void defaultConfig() {
        relevantShaped = new Delayed.ListShapedDelayed(() -> List.of(
                new ShapedOre(name, this, ShapedDrive.get(0), ManaLevel.t1)
                        .addMultipleSurplusTime(1024)
                        .addMultipleOutMana(20000)
        ));
    }
}
