package com.til.dusk.common.register.shaped.shaped_type;

import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.shaped.shapeds.Shaped;

import java.util.function.Consumer;

/**
 * @author til
 */
public class UUGenerateShapedType extends ShapedType{

    public UUGenerateShapedType(){
        super("uu_generate");
    }

    @Override
    public void registerRuleShaped(Consumer<Shaped> shapedConsumer) {
    }

    @Override
    public void defaultConfig() {
        blockTagKey =new Delayed.BlockDelayed(() ->  ManaLevelBlock.uuGenerate.tagPackSupplier.getTagPack().blockTagKey());
    }
}
