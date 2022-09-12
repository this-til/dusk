package com.til.dusk.client.other_mod_interact;

import com.til.dusk.Dusk;
import com.til.dusk.common.register.CapabilityRegister;
import com.til.dusk.common.world.block.MechanicBlock;
import com.til.dusk.common.world.block.RepeaterBlock;
import com.til.dusk.util.tooltip_pack.ComponentPack;
import com.til.dusk.util.tooltip_pack.IComponentPack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import snownee.jade.api.*;
import snownee.jade.api.config.IPluginConfig;

import java.util.List;


/***
 * @author til
 */
@OnlyIn(Dist.CLIENT)
public class Jade_Interact {
    public static void registerClient(IWailaClientRegistration registration) {
        registration.registerBlockComponent(BlockComponentProvider.getInstance(), MechanicBlock.class);
        registration.registerBlockComponent(BlockComponentProvider.getInstance(), RepeaterBlock.class);
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
            BlockEntity blockEntity = blockAccessor.getBlockEntity();
            if (blockEntity == null) {
                return;
            }
            CapabilityRegister.unPackCapabilityTooltip(blockAccessor.getServerData(), blockEntity, new TooltipPack(iTooltip));
        }

        @Override
        public ResourceLocation getUid() {
            return new ResourceLocation(Dusk.MOD_ID, "default");
        }
    }

    public static class TooltipPack implements IComponentPack {
        public final ITooltip iTooltip;
        public int indent;

        public TooltipPack(ITooltip iTooltip) {
            this.iTooltip = iTooltip;
        }

        @Override
        public void indent() {
            indent++;
        }

        @Override
        public void returnIndent() {
            indent--;
            if (indent < 0) {
                indent = 0;
            }
        }

        @Override
        public void resetIndent() {
            indent = 0;
        }

        @Override
        public void add(Component component) {
            iTooltip.add(Component.translatable("%s%s", Component.literal("  ".repeat(Math.max(0, indent))), component));
        }
    }

}
