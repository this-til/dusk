package com.til.dusk.common.capability.control;

import com.til.dusk.common.capability.mana_level.IManaLevel;
import com.til.dusk.common.register.BindType;
import com.til.dusk.util.AllNBT;
import com.til.dusk.util.Pos;
import com.til.dusk.util.data.MessageData;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Control implements IControl {

    public final BlockEntity tileEntity;
    public final IManaLevel manaLevel;
    public List<BindType> bindTypes;
    public Map<BindType, List<BlockPos>> tile = new HashMap<>();

    public Control(BlockEntity tileEntity, List<BindType> bindTypes, IManaLevel manaLevel) {
        this.tileEntity = tileEntity;
        this.manaLevel = manaLevel;
        this.bindTypes = bindTypes;
    }

    /***
     * 返回自己
     */
    @Override
    public BlockEntity getThis() {
        return tileEntity;
    }

    @Override
    public AllNBT.IGS<Tag> getNBTBase() {
        return AllNBT.iControlNBT;
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
    public MessageData binding(BlockEntity tileEntity, BindType iBindType) {
        List<BlockPos> list = tile.computeIfAbsent(iBindType, k -> new ArrayList<>());
        if (tileEntity.getLevel() != tileEntity.getLevel()) {
            return new MessageData(true, "错误，方块不属于同一个世界.name");
        }
        if (!getCanBindType().contains(iBindType)) {
            return new MessageData(true, "绑定失败，不支持类型为{0}的绑定", iBindType.name.toString());
        }
        if (new Pos(tileEntity.getBlockPos()).getDistance(new Pos(tileEntity.getBlockPos())) > getMaxRange()) {
            return new MessageData(true, "绑定失败，方块距离超过限制.name");
        }
        if (list.size() >= getMaxBind()) {
            return new MessageData(true, "绑定失败，已达到绑定类型{0}的最大绑定数量.name", iBindType.name.toString());
        }
        if (this.getAllTileEntity(iBindType).contains(tileEntity)) {
            return new MessageData(true, "绑定失败，该方块已经被绑定过了.name");
        }
        list.add(tileEntity.getBlockPos());
        MinecraftForge.EVENT_BUS.post(new EventControl.Binding(this, tileEntity, iBindType));
        return new MessageData(true, "绑定成功.name");
    }

    @Override
    public boolean hasBundling(BlockEntity tileEntity, BindType bindType) {
        return tile.computeIfAbsent(bindType, k -> new ArrayList<>()).contains(tileEntity.getBlockPos());
    }

    @Override
    public MessageData unBindling(BlockEntity tileEntity, BindType iBindType) {
        if (tileEntity.getLevel() != tileEntity.getLevel()) {
            return new MessageData(true, "错误，方块不属于同一个世界.name");
        }
        if (!this.getAllTileEntity(iBindType).contains(tileEntity)) {
            return new MessageData(true, "解绑失败，方块没有被绑定.name");
        }
        tile.computeIfAbsent(iBindType, k -> new ArrayList<>()).remove(tileEntity.getBlockPos());
        MinecraftForge.EVENT_BUS.post(new EventControl.UnBinding(this, tileEntity, iBindType));
        return new MessageData(true, "解绑成功.name");
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
        return 16;
    }

    /***
     * 获取最大绑定数量
     */
    @Override
    public int getMaxBind() {
        return manaLevel.manaLevel().maxBind;
    }

    /***
     * 获得可以绑定的类型
     */
    @Override
    public List<BindType> getCanBindType() {
        return bindTypes;
    }

    @Override
    public IManaLevel getManaLevel() {
        return manaLevel;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        tile = AllNBT.controlBindBlock.get(nbt);
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbtTagCompound = new CompoundTag();
        AllNBT.controlBindBlock.set(nbtTagCompound, tile);
        return nbtTagCompound;
    }
}
