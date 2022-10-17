package com.til.dusk.common.register.mana_level.block.mechanic;

import com.til.dusk.common.capability.CapabilityHelp;
import com.til.dusk.common.capability.DuskCapabilityProvider;
import com.til.dusk.common.capability.black.Back;
import com.til.dusk.common.capability.black.IBack;
import com.til.dusk.common.capability.block_scan.BlockScan;
import com.til.dusk.common.capability.block_scan.IBlockScan;
import com.til.dusk.common.capability.clock.IClock;
import com.til.dusk.common.capability.clock.ManaClock;
import com.til.dusk.common.capability.control.Control;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.config.ConfigField;
import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.event.EventIO;
import com.til.dusk.common.register.mana_level.block.DefaultCapacityMechanic;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.bind_type.BindType;
import com.til.dusk.common.register.other.CapabilityRegister;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.util.Pos;
import com.til.dusk.util.RoutePack;
import com.til.dusk.util.math.INumberPack;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.items.IItemHandler;

import java.util.List;
import java.util.Map;

/***
 * 挖掘
 */
public class MiningMechanic extends DefaultCapacityMechanic {
    public MiningMechanic() {
        super("mining");
    }

    @Override
    public void addCapability(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, ManaLevel manaLevel, IPosTrack iPosTrack) {
        super.addCapability(event, duskModCapability, manaLevel, iPosTrack);
        IControl control = duskModCapability.addCapability(CapabilityRegister.iControl.capability, new Control(iPosTrack, List.of(BindType.manaIn, BindType.itemOut, BindType.posTrack, BindType.modelStore), manaLevel));
        IBack iBack = duskModCapability.addCapability(CapabilityRegister.iBlack.capability, new Back());
        IClock clock = duskModCapability.addCapability(CapabilityRegister.iClock.capability, new ManaClock(iBack, manaLevel.clock / transmissionEfficiency.ofValue(manaLevel.level), control, consume.ofValue((long) manaLevel.level)));
        IBlockScan iBlockScan = duskModCapability.addCapability(CapabilityRegister.iBlockScan.capability, new BlockScan(clock, iPosTrack, control) {
            public void run() {
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
            }
        });
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN, "挖掘晶体");
        lang.add(LangType.EN_CH, "Mining Crystal");
    }

    @Override
    public void defaultConfig() {
        consume = new INumberPack.ILongPack.LinearFunction(new INumberPack.ILongPack.Constant(32), new INumberPack.ILongPack.Constant(0));
        transmissionEfficiency = new INumberPack.IIntPack.Constant(10);
    }

    @ConfigField
    public INumberPack<Integer> transmissionEfficiency;
    @ConfigField
    public INumberPack<Long> consume;
}
