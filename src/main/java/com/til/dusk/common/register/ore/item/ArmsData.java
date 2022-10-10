package com.til.dusk.common.register.ore.item;

import com.til.dusk.Dusk;
import com.til.dusk.common.config.AcceptTypeJson;
import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.common.register.skill.Skill;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

/***
 * 武器数据
 * @author til
 */
@AcceptTypeJson
public class ArmsData implements Tier {


    public int level = 5;
    public int uses = 2400;
    public float speed = -3f;
    public int attackDamageBasics = 10;
    public int enchantmentValue = 23;

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
     * 修复材料
     */
    @Nullable
    public Delayed<TagKey<Item>> repairIngredient;

    private Ingredient ingredient;

    public Delayed<TagKey<Block>> destructionBlockTag;

    /***
     * 默认技能
     */
    @Nullable
    public Map<Skill, Integer> defaultSkill;

    public ArmsData setOfOre(Ore ore) {
        repairIngredient = new Delayed<>(() -> ore.get(OreItem.ingot).itemTag());
        destructionBlockTag = new Delayed<>(() -> Dusk.instance.BLOCK_TAG.createTagKey(new ResourceLocation(ore.name.getNamespace(), "tier." + ore.name.getPath())));
        return this;
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

    public ArmsData setAttackDamageBasics(int attackDamageBasics) {
        this.attackDamageBasics = attackDamageBasics;
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
        if (ingredient == null) {
            ingredient = repairIngredient == null ? Ingredient.of() : Ingredient.of(repairIngredient.get());
        }
        return ingredient;
    }

    @Override
    public @Nullable TagKey<Block> getTag() {
        return destructionBlockTag.get();
    }


}
