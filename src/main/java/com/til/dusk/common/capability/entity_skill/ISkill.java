package com.til.dusk.common.capability.entity_skill;

import com.til.dusk.common.register.skill.Skill;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * @author til
 */
public interface ISkill extends INBTSerializable<CompoundTag> {

    /***
     * 更新
     */
    void up();

    /***
     * 获取技能表
     * 该表不是动态生成的而是在实体更新中生成
     * @return 实体/物品拥有的技能表
     */
    Map<Skill, SkillCell> getSkillMap();

    /***
     * 返回技能元素
     * @param skill 技能注册
     * @return 技能元素
     */
    @NotNull
    SkillCell getSkill(Skill skill) ;

    void reset();

    class SkillCell {

        /***
         * 技能的原始等级
         */
        public int originalLevel;

        /***
         * 等级
         */
        public int level;

        /***
         * 技能的cd
         */
        public int cd;

        /***
         * 技能携带的nbt标签
         */
        public CompoundTag nbt;

        /***
         * 判断技能是不是有效，通过等级和cd判断
         * @return 技能是不是有效
         */
        public boolean isEmpty() {
            return level <= 0 || cd > 0;
        }

        public void reset() {
            level = originalLevel;
        }

    }
}
