package com.til.dusk.common.register.ore.item;

import com.til.dusk.common.capability.DuskCapabilityProvider;
import com.til.dusk.common.capability.IItemDefaultCapability;
import com.til.dusk.common.capability.black.Back;
import com.til.dusk.common.capability.black.IBack;
import com.til.dusk.common.capability.mana_handle.VariableManaHandle;
import com.til.dusk.common.capability.skill.ISkill;
import com.til.dusk.common.capability.skill.ItemStackSkill;
import com.til.dusk.common.config.INeedBack;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.other.CapabilityRegister;
import com.til.dusk.common.register.skill.Skill;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.TierSortingRegistry;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

/***
 * 武器数据
 */
@Deprecated
public class ArmsData implements Tier, IItemDefaultCapability, INeedBack {

    public final Supplier<Ore> ore;

    public int level = 5;
    public int uses = 2400;
    public float speed = -3f;
    public int attackDamageBonus = 10;
    public int enchantmentValue = 23;
    public Supplier<Ingredient> repairIngredient;

    public TagKey<Block> tag;

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

    public ArmsData(Supplier<Ore> ore) {
        this.ore = ore;
    }

    @Override
    public void back() {
        repairIngredient = () -> Ingredient.of(ore.get().get(OreItem.ingot).itemTag());
        ResourceLocation oreName = new ResourceLocation(ore.get().name.getNamespace(), "tier." + ore.get().name.getPath());
        tag = BlockTags.create(oreName);
        TierSortingRegistry.registerTier(this, oreName, List.of(Tiers.NETHERITE), List.of());
    }

    public ArmsData setLevel(int level) {
        this.level = level;
        return this;
    }

    public ArmsData setUses(int uses) {
        this.uses = uses;
        return this;
    }

    public ArmsData setSpeed(float speed) {
        this.speed = speed;
        return this;
    }

    public ArmsData setAttackDamageBonus(int attackDamageBonus) {
        this.attackDamageBonus = attackDamageBonus;
        return this;
    }

    public ArmsData setEnchantmentValue(int enchantmentValue) {
        this.enchantmentValue = enchantmentValue;
        return this;
    }

    public ArmsData setMane(long mana, long rate) {
        manaBasics = mana;
        rateBasics = rate;
        return this;
    }

    public ArmsData setDefaultSkill(Supplier<List<Skill>> defaultSkill) {
        this.defaultSkill = defaultSkill;
        return this;
    }


    @Override
    public int getUses() {
        return uses;
    }

    @Override
    public float getSpeed() {
        return 0;
    }

    @Override
    public float getAttackDamageBonus() {
        return 0;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @Override
    public @NotNull Ingredient getRepairIngredient() {
        return repairIngredient.get();
    }

    @Override
    public @Nullable TagKey<Block> getTag() {
        return tag;
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
