package com.til.dusk.common.capability.handle;

import com.til.dusk.common.capability.mana_handle.IManaHandle;
import com.til.dusk.common.capability.pos.IPosTrack;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;

import java.util.Map;


/**
 * 和配方处理相关是事件
 *
 * @author til
 */
public class EventHandle extends Event {
    public final IHandle iHandle;

    public EventHandle(IHandle iHandle) {
        this.iHandle = iHandle;
    }

    /***
     * 刷新时
     */
    public static class Up extends EventHandle {
        public final Map<IPosTrack, IManaHandle> manaIn;
        public final Map<IPosTrack, IManaHandle> manaOut;

        public Up(IHandle iHandle, Map<IPosTrack, IManaHandle> manaIn, Map<IPosTrack, IManaHandle> manaOut) {
            super(iHandle);
            this.manaIn = manaIn;
            this.manaOut = manaOut;
        }
    }

    /***
     * 进入时钟触发
     */
    public static class Clock extends EventHandle {
        public final Map<IPosTrack, IItemHandler> itemIn;
        public final Map<IPosTrack, IItemHandler> itemOut;
        public final Map<IPosTrack, IFluidHandler> fluidIn;
        public final Map<IPosTrack, IFluidHandler> fluidOut;

        public Clock(IHandle iHandle, Map<IPosTrack, IItemHandler> itemIn, Map<IPosTrack, IItemHandler> itemOut, Map<IPosTrack, IFluidHandler> fluidIn, Map<IPosTrack, IFluidHandler> fluidOut) {
            super(iHandle);
            this.itemIn = itemIn;
            this.itemOut = itemOut;
            this.fluidIn = fluidIn;
            this.fluidOut = fluidOut;
        }
    }

    /***
     * 和配方处理相关的
     */
    public static class EventShapedHandle extends EventHandle {
        public final ShapedHandle shapedHandle;

        public EventShapedHandle(IHandle iHandle, ShapedHandle shapedHandle) {
            super(iHandle);
            this.shapedHandle = shapedHandle;
        }

        /***
         * 添加配方时
         */
        public static class AddShaped extends EventShapedHandle {
            public AddShaped(IHandle iHandle, ShapedHandle shapedHandle) {
                super(iHandle, shapedHandle);
            }
        }

        /***
         * 完成配方时
         */
        public static class Complete extends EventShapedHandle {
            public Complete(IHandle iHandle, ShapedHandle shapedHandle) {
                super(iHandle, shapedHandle);
            }
        }

        /***
         * 配方刷新时
         */
        public static class Up extends EventShapedHandle {
            public final Map<IPosTrack, IManaHandle> manaIn;
            public final Map<IPosTrack, IManaHandle> manaOut;

            public Up(IHandle iHandle, ShapedHandle shapedHandle, Map<IPosTrack, IManaHandle> manaIn, Map<IPosTrack, IManaHandle> manaOut) {
                super(iHandle, shapedHandle);
                this.manaIn = manaIn;
                this.manaOut = manaOut;
            }
        }

        /***
         * 配方进入时钟触发
         */
        public static class Clock extends EventShapedHandle {
            public final Map<IPosTrack, IItemHandler> itemIn;
            public final Map<IPosTrack, IItemHandler> itemOut;
            public final Map<IPosTrack, IFluidHandler> fluidIn;
            public final Map<IPosTrack, IFluidHandler> fluidOut;

            public Clock(IHandle iHandle, ShapedHandle shapedHandle, Map<IPosTrack, IItemHandler> itemIn, Map<IPosTrack, IItemHandler> itemOut, Map<IPosTrack, IFluidHandler> fluidIn, Map<IPosTrack, IFluidHandler> fluidOut) {
                super(iHandle, shapedHandle);
                this.itemIn = itemIn;
                this.itemOut = itemOut;
                this.fluidIn = fluidIn;
                this.fluidOut = fluidOut;
            }
        }
    }
}
