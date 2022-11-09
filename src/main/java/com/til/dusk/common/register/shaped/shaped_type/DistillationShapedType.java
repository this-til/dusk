package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.shaped.shapeds.Shaped;

import java.util.function.Consumer;

/**
 * @author til
 */
public class DistillationShapedType extends ShapedType{

    public DistillationShapedType(){
        super("distillation");
    }

    @Override
    public void registerRuleShaped(Consumer<Shaped> shapedConsumer) {

    }

    @Override
    public void defaultConfig() {
        blockTagKey =new Delayed.BlockDelayed(() ->  ManaLevelBlock.distillation.tagPackSupplier.getTagPack().blockTagKey());
    }
}
