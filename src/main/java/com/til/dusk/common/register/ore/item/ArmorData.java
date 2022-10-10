package com.til.dusk.common.register.ore.item;

import com.til.dusk.common.config.AcceptTypeJson;
import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.skill.Skill;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@AcceptTypeJson
public class ArmorData implements ArmorMaterial {

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
    public String name;

    public int[] durability = Arrays.copyOf(DEFAULT_DURABILITY, DEFAULT_DURABILITY.length);
    public int[] defense = Arrays.copyOf(DEFAULT_DURABILITY, DEFAULT_DURABILITY.length);
    public float toughness = 3;
    public float knockBackResistance = 0.25f;

    /***
     * 附魔值
     */
    public int enchantmentValue =23;

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
    @Nullable
    public Map<Skill, Integer> defaultSkill;


    /***
     * 修复材料
     */
    @Nullable
    public Delayed<TagKey<Item>> repairIngredient;

    private Ingredient ingredient;

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

    public ArmorData setOfOre(Ore ore) {
        name = ore.name.toString();
        repairIngredient = new Delayed<>(() -> ore.get(OreItem.ingot).itemTag());
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


    public ArmorData setMane(long mana, long rate) {
        manaBasics = mana;
        rateBasics = rate;
        return this;
    }

    public ArmorData putSkill(Skill skill, int g) {
        if (defaultSkill == null) {
            defaultSkill = new HashMap<>(8);
        }
        defaultSkill.put(skill, g);
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
        if (ingredient == null) {
            ingredient = repairIngredient == null ? Ingredient.of() : Ingredient.of(repairIngredient.get());
        }
        return ingredient;
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    @Override
    public float getToughness() {
        return toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return knockBackResistance;
    }

}
