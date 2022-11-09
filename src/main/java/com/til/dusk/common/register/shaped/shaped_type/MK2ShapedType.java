package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.shaped.shapeds.Shaped;

import java.util.function.Consumer;

/**
 * @author til
 */
public class MK2ShapedType extends ShapedType{

    public MK2ShapedType(){
        super("mk2");
    }

    @Override
    public void defaultConfig() {
        blockTagKey = new Delayed.BlockDelayed(() -> ManaLevelBlock.mk2.tagPackSupplier.getTagPack().blockTagKey());
    }

    @Override
    public void registerRuleShaped(Consumer<Shaped> shapedConsumer) {

    }
}
