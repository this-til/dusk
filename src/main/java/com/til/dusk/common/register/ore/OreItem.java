package com.til.dusk.common.register.ore;

import com.til.dusk.Dusk;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.capability.black.Back;
import com.til.dusk.common.capability.black.IBack;
import com.til.dusk.common.capability.skill.ISkill;
import com.til.dusk.common.capability.skill.ItemStackSkill;
import com.til.dusk.common.capability.mana_handle.VariableManaHandle;
import com.til.dusk.common.capability.DuskCapabilityProvider;
import com.til.dusk.common.capability.IItemDefaultCapability;
import com.til.dusk.common.register.CapabilityRegister;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.common.register.skill.Skill;
import com.til.dusk.common.world.item.*;
import com.til.dusk.util.StaticTag;
import com.til.dusk.util.pack.ItemPack;
import com.til.dusk.util.prefab.ColorPrefab;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.TierSortingRegistry;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OreItem extends RegisterBasics.ItemUnitRegister<OreItem, Ore> {

    public static Supplier<IForgeRegistry<OreItem>> ORE_ITEM;


    /***
     * 矿物锭
     */
    public static OreItem ingot;

    /***
     * 板
     */
    public static OreItem plate;

    public static OreItem plate_2;

    public static OreItem plate_3;

    public static OreItem plate_4;

    /***
     * 外壳
     */
    public static OreItem casing;

    /***
     * 箔
     */
    public static OreItem foil;

    /***
     * 杆
     */
    public static OreItem stick;

    /***
     * 长杆
     */
    public static OreItem stick_long;

    /***
     * 线
     */
    public static OreItem string;

    /***
     * 粒
     */
    public static OreItem nuggets;

    /***
     * 破损的晶体
     */
    public static OreItem damagedCrystal;

    /***
     * 晶体
     */
    public static OreItem crystal;

    /***
     * 精致的晶体
     */
    public static OreItem delicateCrystal;

    /***
     * 完美的晶体
     */
    public static OreItem perfectCrystal;

    /***
     * 晶体种子
     */
    public static OreItem crystalSeed;

    /***
     * 粉碎矿物
     */
    public static OreItem crushed;

    /***
     * 纯净矿物
     * 由粉碎矿物洗涤而得到
     */
    public static OreItem crushedPurified;

    /***
     * 矿粉
     */
    public static OreItem dust;

    /***
     * 小撮粉
     */
    public static OreItem dustTiny;

    public static OreItemArms sword;
    public static OreItemArms shovel;
    public static OreItemArms pickaxe;
    public static OreItemArms axe;
    public static OreItemArms hoe;


    public static OreItemArmor head;
    public static OreItemArmor chest;
    public static OreItemArmor legs;
    public static OreItemArmor feet;


    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        ORE_ITEM = event.create(new RegistryBuilder<OreItem>().setName(new ResourceLocation(Dusk.MOD_ID, "ore_item")));
        ingot = new OreItem("ingot", List.of(Ore.IS_METAL));
        plate = new OreItem("plate", List.of(Ore.IS_METAL));
        plate_2 = new OreItem("plate_2", List.of(Ore.IS_METAL)) {
            @Override
            public ResourceLocation getItemMoldMapping(Ore ore) {
                return plate.name;
            }

            @Override
            public void dyeBlack(Ore ore, ColorProxy.ItemColorPack itemColorPack) {
                Color color = ColorPrefab.blend(ore.color, ColorPrefab.GRAYSCALE_REDUCTION_1);
                itemColorPack.addColor(0, itemStack -> color);
            }
        };
        plate_3 = new OreItem("plate_3", List.of(Ore.IS_METAL)) {
            @Override
            public ResourceLocation getItemMoldMapping(Ore ore) {
                return plate.name;
            }

            @Override
            public void dyeBlack(Ore ore, ColorProxy.ItemColorPack itemColorPack) {
                Color color = ColorPrefab.blend(ore.color, ColorPrefab.GRAYSCALE_REDUCTION_2);
                itemColorPack.addColor(0, itemStack -> color);
            }
        };
        plate_4 = new OreItem("plate_4", List.of(Ore.IS_METAL)) {
            @Override
            public ResourceLocation getItemMoldMapping(Ore ore) {
                return plate.name;
            }

            @Override
            public void dyeBlack(Ore ore, ColorProxy.ItemColorPack itemColorPack) {
                Color color = ColorPrefab.blend(ore.color, ColorPrefab.GRAYSCALE_REDUCTION_3);
                itemColorPack.addColor(0, itemStack -> color);
            }
        };
        foil = new OreItem("foil", List.of(Ore.IS_METAL));
        casing = new OreItem("casing", List.of(Ore.IS_METAL));
        stick = new OreItem("stick", List.of(Ore.IS_METAL));
        stick_long = new OreItem("stick_long", List.of(Ore.IS_METAL));
        string = new OreItem("string", List.of(Ore.IS_METAL));
        nuggets = new OreItem("nuggets", List.of(Ore.IS_METAL));
        damagedCrystal = new OreItem("crystal_damaged", List.of(Ore.IS_CRYSTA));
        crystal = new OreItem("crystal", List.of(Ore.IS_CRYSTA));
        delicateCrystal = new OreItem("crystal_delicate", List.of(Ore.IS_CRYSTA));
        perfectCrystal = new OreItem("crystal_perfect", List.of(Ore.IS_CRYSTA));
        crystalSeed = new OreItem("crystal_seed", List.of(Ore.IS_CRYSTA));
        crushed = new OreItem("crushed", List.of(Ore.HAS_MINERAL_BLOCK));
        crushedPurified = new OreItem("crushed_purified", List.of(Ore.HAS_MINERAL_BLOCK));
        dust = new OreItem("dust", List.of());
        dustTiny = new OreItem("dust_tiny", List.of());

        sword = new OreItemArms("sword", IS_SWORD) {
            @Override
            public Item createArmsItem(Ore ore, ArmsData armsData) {
                return new CapabilitySwordItem(ore, armsData);
            }
        };
        shovel = new OreItemArms("shovel", IS_SHOVEL) {
            @Override
            public Item createArmsItem(Ore ore, ArmsData armsData) {
                return new CapabilityShovelItem(ore, armsData);
            }
        };
        pickaxe = new OreItemArms("pickaxe", IS_PICKAXE) {
            @Override
            public Item createArmsItem(Ore ore, ArmsData armsData) {
                return new CapabilityPickaxeItem(ore, armsData);
            }
        };
        axe = new OreItemArms("axe", IS_AXE) {
            @Override
            public Item createArmsItem(Ore ore, ArmsData armsData) {
                return new CapabilityAxeItem(ore, armsData);
            }
        };
        hoe = new OreItemArms("hoe", IS_HOE) {
            @Override
            public Item createArmsItem(Ore ore, ArmsData armsData) {
                return new CapabilityHoeItem(ore, armsData);
            }
        };

        head = new OreItemArmor("head", EquipmentSlot.HEAD);
        chest = new OreItemArmor("chest", EquipmentSlot.CHEST);
        legs = new OreItemArmor("legs", EquipmentSlot.LEGS);
        feet = new OreItemArmor("feet", EquipmentSlot.FEET);
    }

    public final List<StaticTag> oreHasTag;

    public OreItem(ResourceLocation name, List<StaticTag> oreHasTag) {
        super(name, ORE_ITEM);
        this.oreHasTag = oreHasTag;
    }

    public OreItem(String name, List<StaticTag> staticTags) {
        this(new ResourceLocation(Dusk.MOD_ID, name), staticTags);
    }

    @Override
    public @Nullable ItemPack create(Ore ore) {
        if (ore.hasTag(oreHasTag)) {
            return super.create(ore);
        }
        return null;
    }

    @Override
    public void dyeBlack(Ore ore, ColorProxy.ItemColorPack itemColorPack) {
        itemColorPack.addColor(0, itemStack -> ore.color);
    }

    /**
     * @author til
     */
    public static class OreItemArmor extends OreItem {

        public final EquipmentSlot equipmentSlot;

        public OreItemArmor(ResourceLocation name, EquipmentSlot equipmentSlot) {
            super(name, List.of());
            this.equipmentSlot = equipmentSlot;
            addTag(as(equipmentSlot));
        }

        public OreItemArmor(String name, EquipmentSlot equipmentSlot) {
            this(new ResourceLocation(Dusk.MOD_ID, name), equipmentSlot);
        }

        @Override
        public @Nullable ItemPack create(Ore ore) {
            if (ore.hasSet(Ore.ARMOR_DATA)) {
                return super.create(ore);
            }
            return null;
        }

        @Override
        public Item createItem(Ore ore) {
            return new CapabilityArmorItem(ore.getSet(Ore.ARMOR_DATA), equipmentSlot, new Item.Properties().stacksTo(1).tab(Dusk.TAB), ore);
        }
    }

    public abstract static class OreItemArms extends OreItem {
        public OreItemArms(ResourceLocation name, StaticTag toolTag) {
            super(name, List.of());
            addTag(IS_ARMS);
            addTag(toolTag);
        }

        public OreItemArms(String name, StaticTag toolTag) {
            this(new ResourceLocation(Dusk.MOD_ID, name), toolTag);

        }

        @Override
        public @Nullable ItemPack create(Ore ore) {
            if (ore.hasSet(Ore.ARMS_DATA)) {
                return super.create(ore);
            }
            return null;
        }

        @Override
        public Item createItem(Ore ore) {
            return createArmsItem(ore, ore.getSet(Ore.ARMS_DATA));
        }

        /***
         * 创建武器物品
         * @param ore 矿物
         * @param armsData 武器数据
         * @return 穿卷的武器
         */
        public abstract Item createArmsItem(Ore ore, ArmsData armsData);

        @Override
        public void dyeBlack(Ore ore, ColorProxy.ItemColorPack itemColorPack) {
            super.dyeBlack(ore, itemColorPack);
            itemColorPack.addColor(1, itemStack -> ore.color);
        }
    }

    public static class ArmorData implements ArmorMaterial, IItemDefaultCapability {

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

        public final Ore ore;
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

        public ArmorData(Ore ore) {
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
            return Ingredient.of(ore.itemMap.get(OreItem.ingot).itemTag());
        }

        @Override
        public @NotNull String getName() {
            return ore.name.toString();
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

    /***
     * 武器数据
     */
    public static class ArmsData implements Tier, IItemDefaultCapability {

        public final Ore ore;

        public int level = 5;
        public int uses = 2400;
        public float speed = -3f;
        public int attackDamageBonus = 10;
        public int enchantmentValue = 23;
        @NotNull
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

        public ArmsData(Ore ore) {
            this.ore = ore;
            repairIngredient = () -> Ingredient.of(ore.itemMap.get(OreItem.ingot).itemTag());
            ResourceLocation oreName = new ResourceLocation(ore.name.getNamespace(), "tier." + ore.name.getPath());
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

    /***
     * 是装备
     */
    public static final StaticTag IS_ARMOR = new StaticTag("IS_ARMOR", List.of());

    public static final StaticTag IS_HEAD = new StaticTag("IS_HEAD", List.of(IS_ARMOR));
    public static final StaticTag IS_CHEST = new StaticTag("IS_CHEST", List.of(IS_ARMOR));
    public static final StaticTag IS_LEGS = new StaticTag("IS_LEGS", List.of(IS_ARMOR));
    public static final StaticTag IS_FEET = new StaticTag("IS_FEET", List.of(IS_ARMOR));

    public static StaticTag as(EquipmentSlot equipmentSlot) {
        return switch (equipmentSlot) {
            default -> IS_ARMOR;
            case HEAD -> IS_HEAD;
            case CHEST -> IS_CHEST;
            case LEGS -> IS_LEGS;
            case FEET -> IS_FEET;
        };
    }

    /***
     * 是武器
     */
    public static final StaticTag IS_ARMS = new StaticTag("IS_ARMS", List.of());

    public static final StaticTag IS_SWORD = new StaticTag("IS_SWORD", List.of(IS_ARMS));
    public static final StaticTag IS_SHOVEL = new StaticTag("IS_SHOVEL", List.of(IS_ARMS));
    public static final StaticTag IS_PICKAXE = new StaticTag("IS_PICKAXE", List.of(IS_ARMS));
    public static final StaticTag IS_AXE = new StaticTag("IS_AXE", List.of(IS_ARMS));
    public static final StaticTag IS_HOE = new StaticTag("IS_HOE", List.of(IS_ARMS));
}
