package com.til.dusk.common.capability.skill;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.til.dusk.common.capability.black.IBack;
import com.til.dusk.common.register.CapabilityRegister;
import com.til.dusk.common.register.skill.Skill;
import com.til.dusk.util.nbt.pack.AllNBTPack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nullable;
import java.util.*;

/**
 * @author til
 */
public class LivingEntitySkill extends SkillBasics {

    public final LivingEntity livingEntity;

    @Nullable
    public Multimap<Attribute, AttributeModifier> oldAttribute;

    public LivingEntitySkill(LivingEntity livingEntity, IBack iBack) {
        this.livingEntity = livingEntity;
        iBack.add(IBack.LIVING_EQUIPMENT_CHANGE_EVENT, event -> up());
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
                if (!entry.getKey().isOnlineDelivery) {
                    continue;
                }
                SkillCell skillCell = getSkill(entry.getKey());
                skillCell.level += entry.getValue().level;
            }
        }
        upAttribute();
    }

    public void upAttribute() {
        if (oldAttribute != null) {
            livingEntity.getAttributes().removeAttributeModifiers(oldAttribute);
        }
        oldAttribute = getAttribute();
        if (oldAttribute != null) {
            livingEntity.getAttributes().addTransientAttributeModifiers(oldAttribute);
        }
    }

    @Nullable
    public Multimap<Attribute, AttributeModifier> getAttribute() {
        Multimap<Attribute, AttributeModifier> modifierMultimap = null;
        for (Map.Entry<Skill, SkillCell> entry : getSkillMap().entrySet()) {
            if (entry.getKey() instanceof Skill.AttributeSkill attribute) {
                AttributeModifier attributeModifier = new AttributeModifier(attribute.name.toString(), attribute.amountBasics * entry.getValue().level, attribute.operation);
                if (modifierMultimap == null) {
                    modifierMultimap = HashMultimap.create();
                }
                modifierMultimap.put(attribute.attribute, attributeModifier);
            }
        }
        return modifierMultimap;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag compoundTag = super.serializeNBT();
        if (oldAttribute != null) {
            Map<Attribute, List<AttributeModifier>> map = new HashMap<>();
            for (Map.Entry<Attribute, Collection<AttributeModifier>> entry : oldAttribute.asMap().entrySet()) {
                map.put(entry.getKey(), new ArrayList<>(entry.getValue()));
            }
            AllNBTPack.ATTRIBUTE_ATTRIBUTE_MODIFIER_LIST_MAP.set(compoundTag, map);
        }
        return compoundTag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        if (AllNBTPack.ATTRIBUTE_ATTRIBUTE_MODIFIER_LIST_MAP.contains(nbt)) {
            oldAttribute = HashMultimap.create();
            Map<Attribute, List<AttributeModifier>> map = AllNBTPack.ATTRIBUTE_ATTRIBUTE_MODIFIER_LIST_MAP.get(nbt);
            for (Map.Entry<Attribute, List<AttributeModifier>> attributeListEntry : map.entrySet()) {
                oldAttribute.putAll(attributeListEntry.getKey(), attributeListEntry.getValue());
            }
        }
        super.deserializeNBT(nbt);
    }
}
