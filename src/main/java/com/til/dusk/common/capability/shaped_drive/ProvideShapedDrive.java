package com.til.dusk.common.capability.shaped_drive;

import com.til.dusk.common.register.shaped.ShapedDrive;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/***
 *
 * @author til
 */
public class ProvideShapedDrive implements IShapedDrive {

    public final List<ShapedDrive> shapedDrives;

    public ProvideShapedDrive(List<ShapedDrive> shapedDrives) {
        this.shapedDrives = shapedDrives;
    }

    public ProvideShapedDrive(ShapedDrive shapedDrive) {
        this(List.of(shapedDrive));
    }

    @Override
    public List<ShapedDrive> get() {
        return shapedDrives;
    }
}
