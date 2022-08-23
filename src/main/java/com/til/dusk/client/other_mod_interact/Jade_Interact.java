package com.til.dusk.client.other_mod_interact;

import com.til.dusk.Dusk;
import com.til.dusk.common.capability.tile_entity.ITileEntityType;
import com.til.dusk.common.register.CapabilityRegister;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.mana_level.ManaLevelBlock;
import com.til.dusk.common.world.ModBlock;
import com.til.dusk.util.Lang;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
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

    public static class TooltipPack {

        public final ITooltip iTooltip;
        public int indent;

        public TooltipPack(ITooltip iTooltip) {
            this.iTooltip = iTooltip;
        }

        public void indent() {
            indent++;
        }

        public void returnIndent() {
            indent--;
            if (indent < 0) {
                indent = 0;
            }
        }

        public void add(Component component) {
            iTooltip.add(Component.translatable("%s%s", Component.literal("  ".repeat(Math.max(0, indent))), component));
        }

    }
}
