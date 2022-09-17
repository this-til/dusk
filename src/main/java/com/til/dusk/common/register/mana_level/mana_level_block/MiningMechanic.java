package com.til.dusk.common.register.mana_level.mana_level_block;

import com.til.dusk.common.capability.CapabilityHelp;
import com.til.dusk.common.capability.black.IBack;
import com.til.dusk.common.capability.clock.IClock;
import com.til.dusk.common.capability.clock.ManaClock;
import com.til.dusk.common.capability.control.Control;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.event.EventIO;
import com.til.dusk.common.register.BindType;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.util.Extension;
import com.til.dusk.util.Pos;
import com.til.dusk.util.RoutePack;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Tags;
import net.minecraftforge.items.IItemHandler;

import java.util.List;
import java.util.Map;

/***
 * 挖掘
 */
public class MiningMechanic extends PosImplementMechanic {
    public MiningMechanic() {
        super("mining");
    }


    @Override
    public IControl createControl(ManaLevel manaLevel, IPosTrack iPosTrack) {
        return new Control(iPosTrack, List.of(BindType.manaIn, BindType.itemOut, BindType.posTrack, BindType.modelStore), manaLevel);
    }

    @Override
    public IClock createClock(ManaLevel manaLevel, IBack back, IControl iControl, IPosTrack iPosTrack) {
        return new ManaClock(back, manaLevel.clock / 10, iControl, 32L * manaLevel.level);
    }

    @Override
    public Extension.Action_3V<BlockPos, IControl, IPosTrack> createBlockBlack() {
        return (blockPos, iControl, iPosTrack) -> {
            Level level = iPosTrack.getLevel();
            if (!(level instanceof ServerLevel serverLevel)) {
                return;
            }
            Map<IPosTrack, IItemHandler> outItemMap = iControl.getCapability(BindType.itemOut);
            if (outItemMap.isEmpty()) {
                return;
            }
            List<ShapedDrive> shapedDriveList = CapabilityHelp.getShapedDrive(iControl);
            if (!shapedDriveList.contains(ShapedDrive.get(0))) {
                return;
            }
            BlockState blockState = level.getBlockState(blockPos);
            if (blockState.isAir()) {
                return;
            }
            if (blockState.getBlock().defaultDestroyTime() < 0) {
                return;
            }
            if (shapedDriveList.contains(ShapedDrive.get(1)) && !blockState.is(Tags.Blocks.ORES)) {
                return;
            }
            BlockEntity tileEntity = level.getBlockEntity(blockPos);
            if (tileEntity != null) {
                return;
            }
            List<ItemStack> outItem = Block.getDrops(blockState, serverLevel, blockPos, tileEntity);
            if (outItem.isEmpty()) {
                return;
            }
            if (CapabilityHelp.insertItem(iPosTrack, null, outItemMap, outItem, true).isEmpty()) {
                RoutePack<ItemStack> routePack = new RoutePack<>();
                CapabilityHelp.insertItem(iPosTrack, routePack, outItemMap, outItem, false);
                level.removeBlock(blockPos, false);
                for (ItemStack itemStack : outItem) {
                    routePack.add(new RoutePack.RouteCell<>(new Pos(blockPos), iPosTrack.getPos(), itemStack));
                }
                MinecraftForge.EVENT_BUS.post(new EventIO.Item(iPosTrack.getLevel(), routePack));
            }
        };
    }
}
