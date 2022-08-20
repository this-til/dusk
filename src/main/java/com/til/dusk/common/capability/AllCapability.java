package com.til.dusk.common.capability;

import com.til.dusk.Dusk;
import com.til.dusk.common.capability.clock_time.IClockTime;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.dusk_mod_capability.DuskModCapability;
import com.til.dusk.common.capability.dusk_mod_capability.ITileEntityType;
import com.til.dusk.common.capability.handle.IHandle;
import com.til.dusk.common.capability.mana_handle.IManaHandle;
import com.til.dusk.common.capability.mana_level.IManaLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * @author til
 */
public class AllCapability {
    public static Capability<IControl> I_CONTROL = CapabilityManager.get(new CapabilityToken<>() {
    });

    public static Capability<IManaHandle> I_MANA_HANDEL = CapabilityManager.get(new CapabilityToken<>() {
    });

    public static Capability<IShapedDrive> I_SHAPED_DRIVE = CapabilityManager.get(new CapabilityToken<>() {
    });

    public static Capability<IHandle> I_HANDEL = CapabilityManager.get(new CapabilityToken<>() {
    });

    public static Capability<IManaLevel> I_MANA_LEVEL = CapabilityManager.get(new CapabilityToken<>() {
    });

    public static Capability<IClockTime> I_CLOCK_TIME = CapabilityManager.get(new CapabilityToken<>() {
    });


    public static void register(RegisterCapabilitiesEvent event) {
        event.register(IControl.class);
        event.register(IManaHandle.class);
        event.register(IShapedDrive.class);
        event.register(IHandle.class);
        event.register(IManaLevel.class);
        event.register(IClockTime.class);
    }


    @SubscribeEvent
    public void addTileEntityCapability(AttachCapabilitiesEvent<BlockEntity> event) {
        Map<Capability<?>, Object> map = new HashMap<>();
        Block tileEntity = event.getObject().getBlockState().getBlock();
        if (tileEntity instanceof ITileEntityType iTileEntityType) {
            iTileEntityType.add(event, map);
        }
        BlockEntity blockEntity = event.getObject();
        if (blockEntity instanceof ITileEntityType iTileEntityType) {
            iTileEntityType.add(event, map);
        }
        event.addCapability(new ResourceLocation(Dusk.MOD_ID, "bind"), new DuskModCapability(map));
    }

    @SubscribeEvent
    public void addEntityCapability(AttachCapabilitiesEvent<Entity> event) {
    }
}
