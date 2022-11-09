package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.shaped.shapeds.Shaped;

import java.util.function.Consumer;

public class MK1ShapedType extends ShapedType{

    public MK1ShapedType(){
        super("mk1");
    }

    @Override
    public void defaultConfig() {
        blockTagKey =new Delayed.BlockDelayed(() ->  ManaLevelBlock.mk1.tagPackSupplier.getTagPack().blockTagKey());
    }

    @Override
    public void registerRuleShaped(Consumer<Shaped> shapedConsumer) {

    }
}
