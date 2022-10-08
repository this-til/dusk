package com.til.dusk.common.capability.skill;

import com.til.dusk.common.capability.ITooltipCapability;
import com.til.dusk.common.register.other.CapabilityRegister;
import com.til.dusk.common.register.skill.Skill;
import com.til.dusk.util.Lang;
import com.til.dusk.util.nbt.pack.AllNBTPack;
import com.til.dusk.util.tooltip_pack.IComponentPack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author til
 */
public abstract class SkillBasics implements ISkill, ITooltipCapability {

    public Map<Skill, SkillCell> map = new HashMap<>();

    @Override
    public Map<Skill, SkillCell> getSkillMap() {
        return map;
    }

    @Override
    public @NotNull SkillCell getSkill(Skill skill) {
        if (map.containsKey(skill)) {
            return map.get(skill);
        } else {
            SkillCell skillCell = new SkillCell();
            map.put(skill, skillCell);
            return skillCell;
        }
    }

    /***
     * 重置
     */
    @Override
    public void reset() {
        map.values().forEach(SkillCell::reset);
    }

    @Override
    public CompoundTag serializeNBT() {
        up();
        CompoundTag compoundTag = new CompoundTag();
        AllNBTPack.SKILL_SKILL_CELL_MAP.set(compoundTag, map);
        return compoundTag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        map = AllNBTPack.SKILL_SKILL_CELL_MAP.get(nbt);
        up();
    }

    @Nullable
    @Override
    public CompoundTag appendServerData() {
        return serializeNBT();
    }

    @Override
    public void appendTooltip(IComponentPack iTooltip, CompoundTag compoundTag) {
        Map<Skill, SkillCell> map = AllNBTPack.SKILL_SKILL_CELL_MAP.get(compoundTag);
        if (map.isEmpty()) {
            return;
        }
        Map<Skill, Integer> sMap = new HashMap<>();
        for (Map.Entry<Skill, SkillCell> entry : map.entrySet()) {
            if (entry.getValue().level <= 0) {
                continue;
            }
            sMap.put(entry.getKey(), entry.getValue().level);
        }
        if (sMap.isEmpty()) {
            return;
        }
        iTooltip.add(Lang.getLang(Lang.getLang(CapabilityRegister.iSkill)));
        iTooltip.indent();
        for (Map.Entry<Skill, Integer> entry : sMap.entrySet()) {
            iTooltip.add(Lang.getLang(Component.translatable(Lang.getKey(entry.getKey())), Component.literal("x"), Component.literal(String.valueOf(entry.getValue()))));

        }
    }
}
