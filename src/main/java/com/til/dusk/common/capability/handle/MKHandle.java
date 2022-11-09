package com.til.dusk.common.capability.handle;

import com.til.dusk.Dusk;
import com.til.dusk.common.capability.CapabilityHelp;
import com.til.dusk.common.capability.black.IBack;
import com.til.dusk.common.capability.block_attribute.IBlockAttribute;
import com.til.dusk.common.capability.clock.IClock;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.multi_block.IMultiBlock;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.register.bind_type.BindType;
import com.til.dusk.common.register.shaped.ShapedHandleProcess;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.util.Extension;
import com.til.dusk.util.RoutePack;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

import java.util.Map;
import java.util.Set;

/**
 * @author til
 */
public class MKHandle extends Handle {
    public final IMultiBlock multiBlock;
    public final TagKey<Fluid> coolantTag;
    public final int basics;
    public final FluidStack out;

    public MKHandle(IPosTrack iPosTrack, Set<Shaped> shapedTypes, IControl iControl, IBlockAttribute iBlockAttribute, IClock iClock, IBack iBack, int maxParallel,
                    IMultiBlock multiBlock, TagKey<Fluid> coolantTag, int basics, FluidStack out) {
        super(iPosTrack, shapedTypes, iControl, iBlockAttribute, iClock, iBack, maxParallel);
        this.multiBlock = multiBlock;
        this.coolantTag = coolantTag;
        this.basics = basics;
        this.out = out;
    }

    @Override
    public void up() {
        super.up();
        if (shapedHandles.isEmpty()) {
            return;
        }
        int needAmount = shapedHandles.size() * basics;
        if (needAmount > CapabilityHelp.drain(getPosTrack(), null, iControl.getCapability(BindType.fluidIn), new Extension.VariableData_2<>(coolantTag, needAmount), false)) {
            //TODO 发生些不好的东西
            return;
        }
        FluidStack out = this.out.copy();
        out.setAmount(needAmount);
        CapabilityHelp.fill(posTrack, null, iControl.getCapability(BindType.fluidOut), out, false);
    }

    @Override
    public void clockTriggerRun() {
        if (multiBlock.isComplete()) {
            super.clockTriggerRun();
        }
    }
}
