package com.til.dusk.common.other_mod_interact;

import com.til.dusk.Dusk;
import com.til.dusk.common.capability.tile_entity.DefaultTileEntity;
import com.til.dusk.common.register.CapabilityRegister;
import com.til.dusk.util.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.util.LazyOptional;
import snownee.jade.api.*;

/**
 * @author til
 */
@WailaPlugin(Dusk.MOD_ID)
public class Jade_Interact implements IWailaPlugin {
    @Override
    public void register(IWailaCommonRegistration registration) {
        registration.registerBlockDataProvider(new IServerDataProvider<>() {
            @Override
            public void appendServerData(CompoundTag compoundTag, ServerPlayer serverPlayer, Level level, BlockEntity blockEntity, boolean b) {
                for (CapabilityRegister<?> capabilityRegister : CapabilityRegister.CAPABILITY_REGISTER.get()) {
                    LazyOptional<?> lazyOptional = blockEntity.getCapability(capabilityRegister.capability);
                    if (lazyOptional.isPresent()) {
                        CompoundTag sNBT = capabilityRegister.appendServerData(serverPlayer, level, blockEntity, b, Util.forcedConversion(lazyOptional.orElse(null)));
                        if (sNBT != null) {
                            compoundTag.put(capabilityRegister.capability.getName(), sNBT);
                        }
                    }
                }
            }

            @Override
            public ResourceLocation getUid() {
                return new ResourceLocation(Dusk.MOD_ID, "default");
            }
        }, DefaultTileEntity.class);
    }

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        com.til.dusk.client.other_mod_interact.Jade_Interact.registerClient(registration);
    }
}
