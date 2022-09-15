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
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.CapabilityRegister;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.common.register.skill.Skill;
import com.til.dusk.common.world.item.*;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.GenericMap;
import com.til.dusk.util.IBackRun;
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
import net.minecraftforge.common.Tags;
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

    public static OreItemArmsBasics swordBasics;
    public static OreItemArmsBasics shovelBasics;
    public static OreItemArmsBasics pickaxeBasics;
    public static OreItemArmsBasics axeBasics;
    public static OreItemArmsBasics hoeBasics;

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
        ingot = new MetalOreItem("ingot");
        plate = new MetalOreItem("plate");
        plate_2 = new MetalOreItem("plate_2") {
            @Override
            public ResourceLocation getItemMoldMapping(Ore ore) {
                return plate.name;
            }

            @Override
            public void dyeBlack(Ore ore, ColorProxy.ItemColorPack itemColorPack) {
                DuskColor color = ColorPrefab.GRAYSCALE_REDUCTION_1.blend(ore.color);
                itemColorPack.addColor(0, itemStack -> color);
            }
        };
        plate_3 = new MetalOreItem("plate_3") {
            @Override
            public ResourceLocation getItemMoldMapping(Ore ore) {
                return plate.name;
            }

            @Override
            public void dyeBlack(Ore ore, ColorProxy.ItemColorPack itemColorPack) {
                DuskColor color = ColorPrefab.GRAYSCALE_REDUCTION_2.blend(ore.color);
                itemColorPack.addColor(0, itemStack -> color);
            }
        };
        plate_4 = new MetalOreItem("plate_4") {
            @Override
            public ResourceLocation getItemMoldMapping(Ore ore) {
                return plate.name;
            }

            @Override
            public void dyeBlack(Ore ore, ColorProxy.ItemColorPack itemColorPack) {
                DuskColor color = ColorPrefab.GRAYSCALE_REDUCTION_3.blend(ore.color);
                itemColorPack.addColor(0, itemStack -> color);
            }
        };
        foil = new MetalOreItem("foil");
        casing = new MetalOreItem("casing");
        stick = new MetalOreItem("stick");
        stick_long = new MetalOreItem("stick_long");
        string = new MetalOreItem("string");
        nuggets = new MetalOreItem("nuggets");
        damagedCrystal = new CrystaOreItem("crystal_damaged");
        crystal = new CrystaOreItem("crystal");
        delicateCrystal = new CrystaOreItem("crystal_delicate");
        perfectCrystal = new CrystaOreItem("crystal_perfect");
        crystalSeed = new CrystaOreItem("crystal_seed");
        crushed = new MineralBlockOreItem("crushed");
        crushedPurified = new MineralBlockOreItem("crushed_purified");
        dust = new OreItem("dust");
        dustTiny = new OreItem("dust_tiny");
        swordBasics = new OreItemArmsBasics("sword_basics");
        shovelBasics = new OreItemArmsBasics("shovel_basics");
        pickaxeBasics = new OreItemArmsBasics("pickaxe_basics");
        axeBasics = new OreItemArmsBasics("axe_basics");
        hoeBasics = new OreItemArmsBasics("hoe_basics");
        sword = (OreItemArms) new OreItemArms("sword") {
            @Override
            public Item createArmsItem(Ore ore, ArmsData armsData) {
                CapabilitySwordItem item = new CapabilitySwordItem(ore, this, armsData);
                ItemTag.addTag(Tags.Items.TOOLS, item);
                ItemTag.addTag(Tags.Items.TOOLS_SWORDS, item);
                return item;
            }
        }.setSet(IS_SWORD, null);
        shovel = (OreItemArms) new OreItemArms("shovel") {
            @Override
            public Item createArmsItem(Ore ore, ArmsData armsData) {
                CapabilityShovelItem item = new CapabilityShovelItem(ore, this, armsData);
                ItemTag.addTag(Tags.Items.TOOLS, item);
                ItemTag.addTag(Tags.Items.TOOLS_SHOVELS, item);
                return item;
            }
        }.setSet(IS_SHOVEL, null);
        pickaxe = (OreItemArms) new OreItemArms("pickaxe") {
            @Override
            public Item createArmsItem(Ore ore, ArmsData armsData) {
                CapabilityPickaxeItem item = new CapabilityPickaxeItem(ore, this, armsData);
                ItemTag.addTag(Tags.Items.TOOLS, item);
                ItemTag.addTag(Tags.Items.TOOLS_PICKAXES, item);
                return item;
            }
        }.setSet(IS_PICKAXE, null);
        axe = (OreItemArms) new OreItemArms("axe") {
            @Override
            public Item createArmsItem(Ore ore, ArmsData armsData) {
                CapabilityAxeItem item = new CapabilityAxeItem(ore, this, armsData);
                ItemTag.addTag(Tags.Items.TOOLS, item);
                ItemTag.addTag(Tags.Items.TOOLS_AXES, item);
                return item;
            }
        }.setSet(IS_AXE, null);
        hoe = (OreItemArms) new OreItemArms("hoe") {
            @Override
            public Item createArmsItem(Ore ore, ArmsData armsData) {
                CapabilityHoeItem item = new CapabilityHoeItem(ore, this, armsData);
                ItemTag.addTag(Tags.Items.TOOLS, item);
                ItemTag.addTag(Tags.Items.TOOLS_HOES, item);
                return item;
            }
        }.setSet(IS_HOE, null);

        head = new OreItemArmor("head", EquipmentSlot.HEAD);
        chest = new OreItemArmor("chest", EquipmentSlot.CHEST);
        legs = new OreItemArmor("legs", EquipmentSlot.LEGS);
        feet = new OreItemArmor("feet", EquipmentSlot.FEET);
    }


    public OreItem(ResourceLocation name) {
        super(name, ORE_ITEM);
    }

    public OreItem(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    @Override
    public void dyeBlack(Ore ore, ColorProxy.ItemColorPack itemColorPack) {
        itemColorPack.addColor(0, itemStack -> ore.color);
    }

    public static class MetalOreItem extends OreItem {
        public MetalOreItem(ResourceLocation name) {
            super(name);
        }

        public MetalOreItem(String name) {
            this(new ResourceLocation(Dusk.MOD_ID, name));
        }

        @Override
        public @Nullable ItemPack create(Ore ore) {
            if (ore.hasSet(Ore.IS_METAL)) {
                return super.create(ore);
            }
            return null;
        }
    }

    public static class CrystaOreItem extends OreItem {
        public CrystaOreItem(ResourceLocation name) {
            super(name);
        }

        public CrystaOreItem(String name) {
            this(new ResourceLocation(Dusk.MOD_ID, name));
        }

        @Override
        public @Nullable ItemPack create(Ore ore) {
            if (ore.hasSet(Ore.IS_CRYSTA)) {
                return super.create(ore);
            }
            return null;
        }
    }

    public static class MineralBlockOreItem extends OreItem {
        public MineralBlockOreItem(ResourceLocation name) {
            super(name);
        }

        public MineralBlockOreItem(String name) {
            this(new ResourceLocation(Dusk.MOD_ID, name));
        }

        @Override
        public @Nullable ItemPack create(Ore ore) {
            if (ore.getSet(Ore.MINERAL_BLOCK_DATA) != null) {
                return super.create(ore);
            }
            return null;
        }
    }

    public static class OreItemArmor extends OreItem {

        public final EquipmentSlot equipmentSlot;

        public OreItemArmor(ResourceLocation name, EquipmentSlot equipmentSlot) {
            super(name);
            this.equipmentSlot = equipmentSlot;
            setSet(IS_ARMOR, null);
            setSet(asArmorKey(equipmentSlot), null);
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
            CapabilityArmorItem item = new CapabilityArmorItem(ore.getSet(Ore.ARMOR_DATA), equipmentSlot, new Item.Properties().stacksTo(1).tab(Dusk.TAB), ore, this);
            ItemTag.addTag(Tags.Items.ARMORS, item);
            ItemTag.addTag(asTag(equipmentSlot), item);
            return item;
        }
    }

    public static class OreItemArmsBasics extends OreItem {
        public OreItemArmsBasics(ResourceLocation name) {
            super(name);
            setSet(IS_ARMOR, null);
        }

        public OreItemArmsBasics(String name) {
            this(new ResourceLocation(Dusk.MOD_ID, name));
        }

        @Override
        public @Nullable ItemPack create(Ore ore) {
            if (ore.hasSet(Ore.ARMS_DATA)) {
                return super.create(ore);
            }
            return null;
        }

    }

    public abstract static class OreItemArms extends OreItem {
        public OreItemArms(ResourceLocation name) {
            super(name);
            setSet(IS_ARMOR, null);
        }

        public OreItemArms(String name) {
            this(new ResourceLocation(Dusk.MOD_ID, name));
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
            return Ingredient.of(ore.get().itemMap.get(OreItem.ingot).itemTag());
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

    /***
     * 武器数据
     */
    public static class ArmsData implements Tier, IItemDefaultCapability, IBackRun {

        public final Supplier<Ore> ore;

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

        public ArmsData(Supplier<Ore> ore) {
            this.ore = ore;

        }

        @Override
        public void backRun() {
            repairIngredient = () -> Ingredient.of(ore.get().itemMap.get(OreItem.ingot).itemTag());
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

    /***
     * 是装备
     */
    public static final GenericMap.IKey<Void> IS_ARMOR = new GenericMap.IKey.Key<>();

    public static final GenericMap.IKey<Void> IS_HEAD = new GenericMap.IKey.Key<>();
    public static final GenericMap.IKey<Void> IS_CHEST = new GenericMap.IKey.Key<>();
    public static final GenericMap.IKey<Void> IS_LEGS = new GenericMap.IKey.Key<>();
    public static final GenericMap.IKey<Void> IS_FEET = new GenericMap.IKey.Key<>();

    public static GenericMap.IKey<Void> asArmorKey(EquipmentSlot equipmentSlot) {
        return switch (equipmentSlot) {
            default -> IS_ARMOR;
            case HEAD -> IS_HEAD;
            case CHEST -> IS_CHEST;
            case LEGS -> IS_LEGS;
            case FEET -> IS_FEET;
        };
    }

    public static TagKey<Item> asTag(EquipmentSlot equipmentSlot) {
        return switch (equipmentSlot) {
            default -> Tags.Items.ARMORS;
            case HEAD -> Tags.Items.ARMORS_HELMETS;
            case CHEST -> Tags.Items.ARMORS_CHESTPLATES;
            case LEGS -> Tags.Items.ARMORS_LEGGINGS;
            case FEET -> Tags.Items.ARMORS_BOOTS;
        };
    }


    /***
     * 是武器
     */
    public static final GenericMap.IKey<Void> IS_ARMS = new GenericMap.IKey.Key<>();

    public static final GenericMap.IKey<Void> IS_SWORD = new GenericMap.IKey.Key<>();
    public static final GenericMap.IKey<Void> IS_SHOVEL = new GenericMap.IKey.Key<>();
    public static final GenericMap.IKey<Void> IS_PICKAXE = new GenericMap.IKey.Key<>();
    public static final GenericMap.IKey<Void> IS_AXE = new GenericMap.IKey.Key<>();
    public static final GenericMap.IKey<Void> IS_HOE = new GenericMap.IKey.Key<>();
}
