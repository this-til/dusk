package com.til.dusk.common.capability.clock;


import com.til.dusk.common.capability.ITooltipCapability;
import com.til.dusk.common.register.CapabilityRegister;
import com.til.dusk.util.Extension;
import com.til.dusk.util.Lang;
import com.til.dusk.util.TooltipPack;
import com.til.dusk.util.tag_tool.TagTool;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.util.INBTSerializable;
import org.jetbrains.annotations.Nullable;

/***
 * 时钟时间，在周期结束后触发回调
 * @author til
 */
public interface IClock extends INBTSerializable<CompoundTag>, ITooltipCapability {

    /***
     * 在能力初始化时，添加回调
     * @param black 回调函数
     */
    void addBlock(Extension.Action black);

    /***
     * 距触发期还剩的时间
     * @return 时间
     */
    int getTime();

    /***
     * 获取周期触发所需要的时间
     * @return 周期时间
     */
    int getCycleTime();

}
