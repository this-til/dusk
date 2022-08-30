package com.til.dusk.common.other_mod_interact;

import com.til.dusk.Dusk;
import com.til.dusk.common.capability.tile_entity.DefaultTileEntity;
import com.til.dusk.common.capability.tile_entity.RepeaterTileEntity;
import com.til.dusk.common.register.CapabilityRegister;
import com.til.dusk.util.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.util.LazyOptional;
import snownee.jade.api.*;

/**
 * @author til
 */
@WailaPlugin(Dusk.MOD_ID)
public class Jade_Interact implements IWailaPlugin {
    @Override
    public void register(IWailaCommonRegistration registration) {
        IServerDataProvider<BlockEntity> serverDataProvider = new IServerDataProvider<>() {
            @Override
            public void appendServerData(CompoundTag compoundTag, ServerPlayer serverPlayer, Level level, BlockEntity blockEntity, boolean b) {
               /* for (CapabilityManager value : CapabilityManager.INSTANCE.) {
                    LazyOptional<?> lazyOptional = blockEntity.getCapability(value);
                    if (lazyOptional.isPresent()) {

                    }
                }
                for (CapabilityRegister<?> capabilityRegister : CapabilityRegister.CAPABILITY_REGISTER.get()) {

                }*/
            }

            @Override
            public ResourceLocation getUid() {
                return new ResourceLocation(Dusk.MOD_ID, "default");
            }
        };
        registration.registerBlockDataProvider(serverDataProvider, DefaultTileEntity.class);
        registration.registerBlockDataProvider(serverDataProvider, RepeaterTileEntity.class);
    }

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        com.til.dusk.client.other_mod_interact.Jade_Interact.registerClient(registration);
    }


}
