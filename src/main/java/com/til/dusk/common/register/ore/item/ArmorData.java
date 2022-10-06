package com.til.dusk.common.register.ore.item;

import com.til.dusk.common.capability.DuskCapabilityProvider;
import com.til.dusk.common.capability.IItemDefaultCapability;
import com.til.dusk.common.capability.black.Back;
import com.til.dusk.common.capability.black.IBack;
import com.til.dusk.common.capability.mana_handle.VariableManaHandle;
import com.til.dusk.common.capability.skill.ISkill;
import com.til.dusk.common.capability.skill.ItemStackSkill;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.other.CapabilityRegister;
import com.til.dusk.common.register.skill.Skill;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

@Deprecated
public class ArmorData implements ArmorMaterial, IItemDefaultCapability {

    public static final int[] DEFAULT_DURABILITY = new int[]{
            300,
            400,
            400,
            300,
    };
    public static final int[] DEFAULT_DEFENSE = new int[]{
            2,
            6,
            5,
            2,
    };

    public final Supplier<Ore> ore;
    public int[] durability = Arrays.copyOf(DEFAULT_DURABILITY, DEFAULT_DURABILITY.length);
    public int[] defense = Arrays.copyOf(DEFAULT_DURABILITY, DEFAULT_DURABILITY.length);
    public float toughness = 3;
    public float knockBackResistance = 0.25f;

    /***
     * 附魔值
     */
    public int enchantmentValue;

    /***
     * 基础灵气
     * 如果为0物品将没有灵气处理的能力
     */
    public long manaBasics;

    /***
     * 流速基础
     */
    public long rateBasics;

    /***
     * 默认技能
     */
    public Supplier<List<Skill>> defaultSkill = List::of;

    public ArmorData(Supplier<Ore> ore) {
        this.ore = ore;
        setDefense(1);
        setDurability(1);
    }

    public ArmorData setDurability(int durability) {
        for (int i = 0; i < DEFAULT_DURABILITY.length; i++) {
            this.durability[i] = DEFAULT_DURABILITY[i] * durability;
        }
        return this;
    }

    public ArmorData setDefense(int defense) {
        for (int i = 0; i < DEFAULT_DEFENSE.length; i++) {
            this.defense[i] = DEFAULT_DEFENSE[i] * defense;
        }
        return this;
    }

    public ArmorData setToughness(float toughness) {
        this.toughness = toughness;
        return this;
    }

    public ArmorData setKnockBackResistance(float knockBackResistance) {
        this.knockBackResistance = knockBackResistance;
        return this;
    }

    public ArmorData setDefaultSkill(Supplier<List<Skill>> defaultSkill) {
        this.defaultSkill = defaultSkill;
        return this;
    }

    public ArmorData setMane(long mana, long rate) {
        manaBasics = mana;
        rateBasics = rate;
        return this;
    }

    @Override
    public int getDurabilityForSlot(EquipmentSlot equipmentSlot) {
        return durability[equipmentSlot.getIndex()];
    }

    @Override
    public int getDefenseForSlot(EquipmentSlot equipmentSlot) {
        return defense[equipmentSlot.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @Override
    public @NotNull SoundEvent getEquipSound() {
        return Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_diamond")));
    }

    @Override
    public @NotNull Ingredient getRepairIngredient() {
        return Ingredient.of(ore.get().get(OreItem.ingot).itemTag());
    }

    @Override
    public @NotNull String getName() {
        return ore.get().name.toString();
    }

    @Override
    public float getToughness() {
        return toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return knockBackResistance;
    }

    @Override
    public void initCapability(AttachCapabilitiesEvent<ItemStack> event, DuskCapabilityProvider duskCapabilityProvider) {
        IBack iBack = duskCapabilityProvider.addCapability(CapabilityRegister.iBlack.capability, new Back());
        ISkill iSkill = duskCapabilityProvider.addCapability(CapabilityRegister.iSkill.capability, new ItemStackSkill());
        List<Skill> skills = defaultSkill.get();
        if (!skills.isEmpty()) {
            for (Skill skill : skills) {
                iSkill.getSkill(skill).originalLevel++;
                if (skill instanceof IItemDefaultCapability iItemDefaultCapability) {
                    iItemDefaultCapability.initCapability(event, duskCapabilityProvider);
                }
            }
        }
        if (manaBasics > 0) {
            duskCapabilityProvider.addCapability(CapabilityRegister.iManaHandle.capability, new VariableManaHandle(manaBasics, rateBasics, iBack,
                    () -> 1 + iSkill.getSkill(Skill.maxManaDilatation).level * 0.2, () -> 1 + iSkill.getSkill(Skill.rateDilatation).level * 0.2));
        }
    }
}
