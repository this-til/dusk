package com.til.dusk.common.capability.control;

import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.register.bind_type.BindTypeBindCapability;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.bind_type.BindType;
import com.til.dusk.common.register.other.CapabilityRegister;
import com.til.dusk.util.Lang;
import com.til.dusk.util.nbt.pack.AllNBTPack;
import com.til.dusk.util.tooltip_pack.IComponentPack;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
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

    public final IPosTrack posTrack;
    public final int maxBind;
    public final int maxRange;
    public final List<BindType> bindTypes;
    public Map<BindType, List<BlockPos>> tile = new HashMap<>();

    public Control(IPosTrack posTrack, List<BindType> bindTypes, int maxBind, int maxRange) {
        this.posTrack = posTrack;
        this.bindTypes = bindTypes;
        this.maxBind = maxBind;
        this.maxRange = maxRange;
    }

    public Control(IPosTrack posTrack, List<BindType> bindTypes, ManaLevel iManaLevel) {
        this(posTrack, bindTypes, iManaLevel.maxBind, iManaLevel.maxRange);
    }

    @Override
    public List<IPosTrack> getAllTileEntity(BindType iBindType) {
        Level level = posTrack.getLevel();
        if (level == null) {
            return List.of();
        }
        List<IPosTrack> list = new ArrayList<>();
        List<BlockPos> rList = null;
        List<BlockPos> blockPosList = tile.computeIfAbsent(iBindType, k -> new ArrayList<>());
        if (blockPosList.isEmpty()) {
            return List.of();
        }
        for (BlockPos blockPos : blockPosList) {
            BlockEntity tile = level.getBlockEntity(blockPos);
            if (tile == null) {
                if (rList == null) {
                    rList = new ArrayList<>();
                }
                rList.add(blockPos);
            } else {
                list.add(tile.getCapability(CapabilityRegister.iPosTrack.capability).orElse(null));
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
    public Component bind(IPosTrack iPosTrack, BindType iBindType) {
        List<BlockPos> list = tile.computeIfAbsent(iBindType, k -> new ArrayList<>());
        if (!iPosTrack.isTileEntity()) {
            return Lang.getLang(Lang.getKey("????????????????????????????????????"));
        }
        if (iPosTrack.getLevel() != iPosTrack.getLevel()) {
            return Lang.getLang(Lang.getKey("???????????????????????????????????????"));
        }
        if (!getCanBindType().contains(iBindType)) {
            return Component.translatable(Lang.getKey("?????????????????????????????????[%s]?????????"), Lang.getLang(iBindType));
        }
        if (posTrack.getPos().distance(iPosTrack.getPos()) > getMaxRange()) {
            return Lang.getLang(Lang.getKey("???????????????????????????????????????"));
        }
        if (list.size() >= getMaxBind()) {
            return Component.translatable(Lang.getKey("????????????????????????????????????[%s]?????????????????????"), Lang.getLang(iBindType));
        }
        if (this.getAllTileEntity(iBindType).contains(iPosTrack)) {
            return Lang.getLang(Lang.getKey("?????????????????????????????????????????????"));
        }
        Component component = iBindType.filter(this, iPosTrack);
        if (component != null) {
            return component;
        }
        list.add(iPosTrack.getAsBlockPos());
        MinecraftForge.EVENT_BUS.post(new EventControl.Binding(this, iPosTrack, iBindType));
        return Lang.getLang(Lang.getKey("????????????"));
    }

    @Override
    public Map<BindType, List<IPosTrack>> getAllBind() {
        Map<BindType, List<IPosTrack>> map = new HashMap<>(getCanBindType().size());
        for (BindType bindType : getCanBindType()) {
            map.put(bindType, getAllTileEntity(bindType));
        }
        return map;
    }

    @Override
    public boolean hasBind(IPosTrack iPosTrack, BindType bindType) {
        return tile.computeIfAbsent(bindType, k -> new ArrayList<>()).contains(iPosTrack.getPos().blockPos());
    }

    @Override
    public Component unBind(IPosTrack tileEntity, BindType iBindType) {
        if (tileEntity.getLevel() != tileEntity.getLevel()) {
            return Lang.getLang(Lang.getKey("???????????????????????????????????????"));
        }
        List<BlockPos> list = tile.computeIfAbsent(iBindType, k -> new ArrayList<>());
        if (!list.contains(tileEntity.getAsBlockPos())) {
            return Lang.getLang(Lang.getKey("????????????????????????????????????"));
        }
        tile.computeIfAbsent(iBindType, k -> new ArrayList<>()).remove(tileEntity.getAsBlockPos());
        MinecraftForge.EVENT_BUS.post(new EventControl.UnBinding(this, tileEntity, iBindType));
        return Lang.getLang(Lang.getKey("????????????"));
    }


    @Override
    public IPosTrack getPosTrack() {
        return posTrack;
    }

    @Override
    public void unBundlingAll() {
        MinecraftForge.EVENT_BUS.post(new EventControl.UnBundlingAll(this));
        tile.clear();
    }

    @Override
    public <C> Map<IPosTrack, C> getCapability(Capability<C> capability, BindType iBindType) {
        List<IPosTrack> list = getAllTileEntity(iBindType);
        if (list.isEmpty()) {
            return Map.of();
        }
        Map<IPosTrack, C> map = new HashMap<>(list.size());
        for (IPosTrack entity : getAllTileEntity(iBindType)) {
            if (!entity.isTileEntity()) {
                continue;
            }
            BlockEntity blockEntity = entity.getAsTileEntity();
            LazyOptional<C> c = blockEntity.getCapability(capability, null);
            if (c.isPresent()) {
                map.put(blockEntity.getCapability(CapabilityRegister.iPosTrack.capability).orElse(null), c.orElse(null));
            }
        }
        return map;
    }

    @Override
    public <C> Map<IPosTrack, C> getCapability(BindTypeBindCapability<C> bundTypeBindCapability) {
        return getCapability(bundTypeBindCapability.capability.capability, bundTypeBindCapability);
    }

    /***
     * ?????????????????????????????????????????????
     */
    @Override
    public int getMaxRange() {
        return maxRange;
    }

    /***
     * ????????????????????????
     */
    @Override
    public int getMaxBind() {
        return maxBind;
    }

    /***
     * ???????????????????????????
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
    public CompoundTag appendServerData() {
        CompoundTag compoundTag = serializeNBT();
        AllNBTPack.MAX_BIND.set(compoundTag, getMaxBind());
        AllNBTPack.MAX_RANGE.set(compoundTag, getMaxRange());
        AllNBTPack.BIND_TYPE_LIST.set(compoundTag, getCanBindType());
        return compoundTag;
    }

    @Override
    public void appendTooltip(IComponentPack iTooltip, CompoundTag compoundTag) {
        iTooltip.add(Lang.getLang(
                Lang.getLang(CapabilityRegister.iControl),
                Component.literal(":")));
        iTooltip.indent();
        iTooltip.add(Lang.getLang(Component.translatable(Lang.getKey("??????????????????")), Component.literal(String.valueOf(AllNBTPack.MAX_BIND.get(compoundTag)))));
        iTooltip.add(Lang.getLang(Component.translatable(Lang.getKey("????????????")), Component.literal(String.valueOf(AllNBTPack.MAX_RANGE.get(compoundTag)))));

        Map<BindType, List<BlockPos>> bindTypeListMap = AllNBTPack.BIND_TYPE_BLOCK_POS_LIST_MAP.get(compoundTag);
        List<BindType> bindTypeList = AllNBTPack.BIND_TYPE_LIST.get(compoundTag);
        iTooltip.add(Component.translatable(Lang.getKey("????????????")));
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
