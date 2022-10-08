package com.til.dusk.common.capability.shaped_drive;

import com.til.dusk.common.capability.ITooltipCapability;
import com.til.dusk.common.register.other.CapabilityRegister;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.util.Lang;
import com.til.dusk.util.nbt.pack.AllNBTPack;
import com.til.dusk.util.tooltip_pack.IComponentPack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * 返回可以使用的配方
 *
 * @author til
 */
public interface IShapedDrive extends ITooltipCapability {

    List<ShapedDrive> get();

    @Nullable
    @Override
    default CompoundTag appendServerData() {
        CompoundTag compoundTag = new CompoundTag();
        AllNBTPack.SHAPED_DRIVE_LIST.set(compoundTag, get());
        return compoundTag;
    }

    @Override
    default void appendTooltip(IComponentPack iTooltip, CompoundTag compoundTag) {
        List<ShapedDrive> shapedDriveList = AllNBTPack.SHAPED_DRIVE_LIST.get(compoundTag);
        if (shapedDriveList.isEmpty()) {
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        for (ShapedDrive shapedDrive : shapedDriveList) {
            stringBuilder.append(shapedDrive.name.getPath());
            stringBuilder.append(',');
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append(']');
        iTooltip.add(Lang.getLang(Lang.getLang(CapabilityRegister.iShapedDrive), Component.literal(stringBuilder.toString())));
    }
}
