package com.til.dusk.common.capability.entity_skill;

import com.til.dusk.common.register.CapabilityRegister;
import com.til.dusk.common.register.skill.Skill;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.LazyOptional;

import java.util.Map;

/**
 * @author til
 */
public class LivingEntitySkill extends SkillBasics {

    public final LivingEntity livingEntity;

    public LivingEntitySkill(LivingEntity livingEntity) {
        this.livingEntity = livingEntity;
    }

    @Override
    public void up() {
        reset();
        for (EquipmentSlot value : EquipmentSlot.values()) {
            ItemStack itemStack = livingEntity.getItemBySlot(value);
            if (itemStack.isEmpty()) {
                continue;
            }
            LazyOptional<ISkill> skillLazyOptional = itemStack.getCapability(CapabilityRegister.iSkill.capability);
            if (!skillLazyOptional.isPresent()) {
                continue;
            }
            ISkill iSkill = skillLazyOptional.orElse(null);
            iSkill.up();
            for (Map.Entry<Skill, SkillCell> entry : iSkill.getSkillMap().entrySet()) {
                SkillCell skillCell = entry.getValue();
                skillCell.level += entry.getValue().level;
            }
        }
    }
}
