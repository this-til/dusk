package com.til.dusk.common.register.skill;

import com.til.dusk.Dusk;
import com.til.dusk.common.register.RegisterBasics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Skill extends RegisterBasics<Skill> {

    public static Supplier<IForgeRegistry<Skill>> SKILL;
    public static Skill empty;

    /***
     * 最大灵气扩容
     * 0.2每级
     */
    public static Skill maxManaDilatation;

    /***
     * 灵气流速扩容
     * 0.2每级
     */
    public static Skill rateDilatation;

    /***
     * 提升生命
     * 4每级
     */
    public static AttributeSkill life;

    /***
     * 攻击力
     * 1每级
     */
    public static AttributeSkill attack;

    /***
     * 防御力
     * 1每级
     */
    public static AttributeSkill armor;

    /***
     * 韧性
     * 0.1每级
     */
    public static AttributeSkill armorToughness;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        SKILL = event.create(new RegistryBuilder<Skill>().setName(new ResourceLocation(Dusk.MOD_ID, "skill")));
        empty = new Skill("empty").setOnlineDelivery(false);
        maxManaDilatation = new Skill("max_mana_dilatation").setOnlineDelivery(false);
        rateDilatation = new Skill("rate_dilatation").setOnlineDelivery(false);
        life = new AttributeSkill("life", Attributes.MAX_HEALTH, AttributeModifier.Operation.ADDITION, 4);
        attack = new AttributeSkill("attack", Attributes.ATTACK_DAMAGE, AttributeModifier.Operation.ADDITION, 1);
        armor = new AttributeSkill("armor", Attributes.ARMOR, AttributeModifier.Operation.ADDITION, 1);
        armorToughness = new AttributeSkill("armor_toughness", Attributes.ARMOR_TOUGHNESS, AttributeModifier.Operation.ADDITION, 0.1);
    }

    /***
     * 向上传递，如果为假在玩家获取装备的技能时阻断获取
     */
    public boolean isOnlineDelivery = true;

    public Skill(ResourceLocation name) {
        super(name, SKILL);
    }

    public Skill setOnlineDelivery(boolean onlineDelivery) {
        isOnlineDelivery = onlineDelivery;
        return this;
    }

    public Skill(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    public static class AttributeSkill extends Skill {
        public final Attribute attribute;
        public final AttributeModifier.Operation operation;

        /***
         * 每级的操作数值
         */
        public final double amountBasics;

        public AttributeSkill(ResourceLocation name, Attribute attribute, AttributeModifier.Operation operation, double amountBasics) {
            super(name);
            this.attribute = attribute;
            this.operation = operation;
            this.amountBasics = amountBasics;
        }

        public AttributeSkill(String name, Attribute attribute, AttributeModifier.Operation operation, double amountBasics) {
            this(new ResourceLocation(Dusk.MOD_ID, name), attribute, operation, amountBasics);
        }
    }

}
