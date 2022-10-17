package com.til.dusk.common.mixin;

import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.asm.mixin.connect.IMixinConnector;

/**
 * @author til
 */
public class DuskCommonMixinConnector implements IMixinConnector {
    @Override
    public void connect() {
        Mixins.addConfiguration("dusk.client.mixins.json");
        Mixins.addConfiguration("dusk.common.mixins.json");
    }
}
