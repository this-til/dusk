package com.til.dusk.client.other_mod_interact;

import com.til.dusk.Dusk;
import com.til.dusk.common.register.CapabilityRegister;
import com.til.dusk.common.world.ModBlock;
import com.til.dusk.util.TooltipPack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import snownee.jade.api.*;
import snownee.jade.api.config.IPluginConfig;


/***
 * @author til
 */
@OnlyIn(Dist.CLIENT)
public class Jade_Interact {
    public static void registerClient(IWailaClientRegistration registration) {
        registration.registerBlockComponent(BlockComponentProvider.getInstance(), ModBlock.MechanicBlock.class);
        registration.registerBlockComponent(BlockComponentProvider.getInstance(), ModBlock.RepeaterBlock.class);
    }

    public static class BlockComponentProvider implements IBlockComponentProvider {

        public static BlockComponentProvider blockComponentProvider;

        public static BlockComponentProvider getInstance() {
            if (blockComponentProvider == null) {
                blockComponentProvider = new BlockComponentProvider();
            }
            return blockComponentProvider;
        }

        @Override
        public void appendTooltip(ITooltip iTooltip, BlockAccessor blockAccessor, IPluginConfig iPluginConfig) {
            CompoundTag compoundTag = blockAccessor.getServerData();
            TooltipPack tooltipPack = new TooltipPack(iTooltip);
            for (CapabilityRegister<?> capabilityRegister : CapabilityRegister.CAPABILITY_REGISTER.get()) {
                if (compoundTag.contains(capabilityRegister.capability.getName())) {
                    capabilityRegister.appendTooltip(tooltipPack, blockAccessor, iPluginConfig, compoundTag.getCompound(capabilityRegister.capability.getName()));
                }
                tooltipPack.indent = 0;
            }
        }

        @Override
        public ResourceLocation getUid() {
            return new ResourceLocation(Dusk.MOD_ID, "default");
        }
    }

}
