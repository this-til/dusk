package com.til.dusk.common.capability;

import com.til.dusk.common.capability.clock_time.IClockTime;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.handle.IHandle;
import com.til.dusk.common.capability.mana_handle.IManaHandle;
import com.til.dusk.common.capability.mana_level.IManaLevel;
import com.til.dusk.common.capability.shaped_drive.IShapedDrive;
import com.til.dusk.common.capability.up.IUp;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;

/**
 * @author til
 */
public class AllCapability {
    public static Capability<IControl> I_CONTROL = CapabilityManager.get(new CapabilityToken<>() {
    });

    public static Capability<IUp> I_UP = CapabilityManager.get(new CapabilityToken<>() {
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
        event.register(IUp.class);
        event.register(IManaHandle.class);
        event.register(IShapedDrive.class);
        event.register(IHandle.class);
        event.register(IManaLevel.class);
        event.register(IClockTime.class);
    }

}
