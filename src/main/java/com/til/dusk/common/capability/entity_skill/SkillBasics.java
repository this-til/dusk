package com.til.dusk.common.capability.entity_skill;

import com.til.dusk.common.register.skill.Skill;
import com.til.dusk.util.nbt.pack.AllNBTPack;
import net.minecraft.nbt.CompoundTag;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * @author til
 */
public abstract class SkillBasics implements ISkill {

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
}
