package com.til.dusk.common.capability.control;

import com.til.dusk.common.register.BindType;
import com.til.dusk.common.register.CapabilityRegister;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.util.TooltipPack;
import com.til.dusk.util.nbt.pack.AllNBTPack;
import com.til.dusk.util.Lang;
import com.til.dusk.util.Pos;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Control implements IControl {

    public final BlockEntity tileEntity;
    public final int maxBind;
    public final int maxRange;
    public final List<BindType> bindTypes;
    public Map<BindType, List<BlockPos>> tile = new HashMap<>();

    public Control(BlockEntity tileEntity, List<BindType> bindTypes, int maxBind, int maxRange) {
        this.tileEntity = tileEntity;
        this.bindTypes = bindTypes;
        this.maxBind = maxBind;
        this.maxRange = maxRange;
    }

    public Control(BlockEntity tileEntity, List<BindType> bindTypes, ManaLevel iManaLevel) {
        this(tileEntity, bindTypes, iManaLevel.maxBind, 16);
    }

    /***
     * 返回自己
     */
    @Override
    public BlockEntity getThis() {
        return tileEntity;
    }

    @Override
    public List<BlockEntity> getAllTileEntity(BindType iBindType) {
        Level level = getThis().getLevel();
        if (level == null) {
            return new ArrayList<>();
        }
        List<BlockEntity> list = new ArrayList<>();
        List<BlockPos> rList = null;
        List<BlockPos> blockPosList = tile.computeIfAbsent(iBindType, k -> new ArrayList<>());
        for (BlockPos blockPos : blockPosList) {
            BlockEntity tile = level.getBlockEntity(blockPos);
            if (tile == null) {
                if (rList == null) {
                    rList = new ArrayList<>();
                }
                rList.add(blockPos);
            } else {
                list.add(tile);
            }
        }
        if (rList != null) {
            for (BlockPos blockPos : rList) {
                blockPosList.remove(blockPos);
            }
        }
        return list;
    }

    @Override
    public Component bind(BlockEntity tileEntity, BindType iBindType) {
        List<BlockPos> list = tile.computeIfAbsent(iBindType, k -> new ArrayList<>());
        if (tileEntity.getLevel() != tileEntity.getLevel()) {
            return Lang.getLang(Lang.getKey("错误，方块不属于同一个世界"));
        }
        if (!getCanBindType().contains(iBindType)) {
            return Component.translatable(Lang.getKey("绑定失败，不支持类型为[%s]的绑定"), Lang.getLang(iBindType));
        }
        if (new Pos(tileEntity.getBlockPos()).distance(new Pos(tileEntity.getBlockPos())) > getMaxRange()) {
            return Lang.getLang(Lang.getKey("绑定失败，方块距离超过限制"));
        }
        if (list.size() >= getMaxBind()) {
            return Component.translatable(Lang.getKey("绑定失败，已达到绑定类型[%s]的最大绑定数量"), Lang.getLang(iBindType));
        }
        if (this.getAllTileEntity(iBindType).contains(tileEntity)) {
            return Lang.getLang(Lang.getKey("绑定失败，该方块已经被绑定过了"));
        }
        Component component = iBindType.filter(this, tileEntity);
        if (component != null) {
            return component;
        }
        list.add(tileEntity.getBlockPos());
        MinecraftForge.EVENT_BUS.post(new EventControl.Binding(this, tileEntity, iBindType));
        return Lang.getLang(Lang.getKey("绑定成功"));
    }

    @Override
    public Map<BindType, List<BlockPos>> getAllBind() {
        return tile;
    }

    @Override
    public boolean hasBind(BlockEntity tileEntity, BindType bindType) {
        return tile.computeIfAbsent(bindType, k -> new ArrayList<>()).contains(tileEntity.getBlockPos());
    }

    @Override
    public Component unBind(BlockEntity tileEntity, BindType iBindType) {
        if (tileEntity.getLevel() != tileEntity.getLevel()) {
            return Lang.getLang(Lang.getKey("错误，方块不属于同一个世界"));
        }
        if (!this.getAllTileEntity(iBindType).contains(tileEntity)) {
            return Lang.getLang(Lang.getKey("解绑失败，方块没有被绑定"));
        }
        tile.computeIfAbsent(iBindType, k -> new ArrayList<>()).remove(tileEntity.getBlockPos());
        MinecraftForge.EVENT_BUS.post(new EventControl.UnBinding(this, tileEntity, iBindType));
        return Lang.getLang(Lang.getKey("解绑成功"));
    }

    @Override
    public void unBundlingAll() {
        MinecraftForge.EVENT_BUS.post(new EventControl.UnBundlingAll(this));
        tile.clear();
    }

    @Override
    public <C> Map<BlockEntity, C> getCapability(Capability<C> capability, BindType iBindType) {
        Map<BlockEntity, C> map = new HashMap<>();
        for (BlockEntity entity : getAllTileEntity(iBindType)) {
            LazyOptional<C> c = entity.getCapability(capability, null);
            if (c.isPresent()) {
                map.put(entity, c.orElse(null));
            }
        }
        return map;
    }

    @Override
    public <C> Map<BlockEntity, C> getCapability(BindType.BindTypeBindCapability<C> bundTypeBindCapability) {
        return getCapability(bundTypeBindCapability.getCapability(), bundTypeBindCapability);
    }

    /***
     * 获取可以绑定实体方块的最大范围
     */
    @Override
    public int getMaxRange() {
        return maxRange;
    }

    /***
     * 获取最大绑定数量
     */
    @Override
    public int getMaxBind() {
        return maxBind;
    }

    /***
     * 获得可以绑定的类型
     */
    @Override
    public List<BindType> getCanBindType() {
        return bindTypes;
    }


    @Override
    public void deserializeNBT(CompoundTag nbt) {
        tile = AllNBTPack.BIND_TYPE_BLOCK_POS_LIST_MAP.get(nbt);
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbtTagCompound = new CompoundTag();
        AllNBTPack.BIND_TYPE_BLOCK_POS_LIST_MAP.set(nbtTagCompound, tile);
        return nbtTagCompound;
    }

    @Nullable
    @Override
    public CompoundTag appendServerData(ServerPlayer serverPlayer, Level level, BlockEntity blockEntity, boolean detailed) {
        CompoundTag compoundTag = serializeNBT();
        AllNBTPack.MAX_BIND.set(compoundTag, getMaxBind());
        AllNBTPack.MAX_RANGE.set(compoundTag, getMaxRange());
        AllNBTPack.BIND_TYPE_LIST.set(compoundTag, getCanBindType());
        return compoundTag;
    }

    @Override
    public void appendTooltip(TooltipPack iTooltip, CompoundTag compoundTag) {
        iTooltip.add(Lang.getLang(CapabilityRegister.iControl));
        iTooltip.indent();
        iTooltip.add(Lang.getLang(Component.translatable(Lang.getKey("最大绑定数量")), Component.literal(String.valueOf(AllNBTPack.MAX_BIND.get(compoundTag)))));
        iTooltip.add(Lang.getLang(Component.translatable(Lang.getKey("最大范围")), Component.literal(String.valueOf(AllNBTPack.MAX_RANGE.get(compoundTag)))));

        Map<BindType, List<BlockPos>> bindTypeListMap = AllNBTPack.BIND_TYPE_BLOCK_POS_LIST_MAP.get(compoundTag);
        List<BindType> bindTypeList = AllNBTPack.BIND_TYPE_LIST.get(compoundTag);
        iTooltip.add(Component.translatable(Lang.getKey("绑定类型")));
        iTooltip.indent();
        for (BindType bindType : bindTypeList) {
            iTooltip.add(Lang.getLang(Lang.getLang(bindType), Component.literal(":")));
            iTooltip.indent();
            if (bindTypeListMap.containsKey(bindType)) {
                for (BlockPos blockPos : bindTypeListMap.get(bindType)) {
                    iTooltip.add(Component.translatable("[x:%s,y:%s,z:%s]",
                            Component.literal(String.valueOf(blockPos.getX())),
                            Component.literal(String.valueOf(blockPos.getY())),
                            Component.literal(String.valueOf(blockPos.getZ()))));
                }

            }
            iTooltip.returnIndent();
        }
        iTooltip.returnIndent();
    }
}
