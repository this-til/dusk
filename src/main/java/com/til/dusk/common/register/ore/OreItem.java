package com.til.dusk.common.register.ore;

import com.til.dusk.Dusk;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.capability.entity_skill.ISkill;
import com.til.dusk.common.capability.entity_skill.ItemStackSkill;
import com.til.dusk.common.capability.mana_handle.VariableManaHandle;
import com.til.dusk.common.capability.tile_entity.DuskCapabilityProvider;
import com.til.dusk.common.capability.tile_entity.IItemDefaultCapability;
import com.til.dusk.common.capability.up.IUp;
import com.til.dusk.common.capability.up.Up;
import com.til.dusk.common.register.CapabilityRegister;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.common.register.skill.Skill;
import com.til.dusk.util.StaticTag;
import com.til.dusk.util.pack.ItemPack;
import com.til.dusk.util.prefab.ColorPrefab;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
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
            super(name, List.of(Ore.HAS_ARMOR));
            this.equipmentSlot = equipmentSlot;
        }

        public OreItemArmor(String name, EquipmentSlot equipmentSlot) {
            this(new ResourceLocation(Dusk.MOD_ID, name), equipmentSlot);
        }

        @Override
        public Item createItem(Ore ore) {
            if (ore.hasTag(Ore.HAS_ARMOR) && ore.armorData != null) {
                return new OreItem.CapabilityArmorItem(ore.armorData, equipmentSlot, new Item.Properties().stacksTo(1).tab(Dusk.TAB), ore);
            }
            return null;
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
            IUp iUp = duskCapabilityProvider.addCapability(CapabilityRegister.iUp.capability, new Up());
            ISkill iSkill = duskCapabilityProvider.addCapability(CapabilityRegister.iSkill.capability, new ItemStackSkill());
            List<Skill> skills = defaultSkill.get();
            if (!skills.isEmpty()) {
                for (Skill skill : skills) {
                    iSkill.getSkill(skill).originalLevel++;
                }
            }
            if (manaBasics > 0) {
                duskCapabilityProvider.addCapability(CapabilityRegister.iManaHandle.capability, new VariableManaHandle(manaBasics, rateBasics, iUp,
                        () -> iSkill.getSkill(Skill.maxManaDilatation).level * 0.2, () -> iSkill.getSkill(Skill.rateDilatation).level * 0.2));
            }
        }
    }

    public static class CapabilityArmorItem extends DyeableArmorItem implements IItemDefaultCapability {
        public final Ore ore;
        public final ArmorData armorData;

        public CapabilityArmorItem(ArmorData armorMaterial, EquipmentSlot equipmentSlot, Properties properties, Ore ore) {
            super(armorMaterial, equipmentSlot, properties);
            this.ore = ore;
            this.armorData = armorMaterial;
        }

        public static final String OVERLAY = "overlay";

        @Override
        public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
            if (OVERLAY.equals(type)) {
                return Dusk.MOD_ID + ":textures/air.png";
            }
            return switch (slot) {
                case HEAD, CHEST, FEET -> Dusk.MOD_ID + ":textures/armor/model_layer_1.png";
                case LEGS -> Dusk.MOD_ID + ":textures/armor/model_layer_2.png";
                default -> null;
            };
        }

        @Override
        public boolean hasCustomColor(@NotNull ItemStack itemStack) {
            return true;
        }

        @Override
        public int getColor(@NotNull ItemStack itemStack) {
            return ore.color.getRGB();
        }

        @Override
        public void clearColor(@NotNull ItemStack itemStack) {
        }

        @Override
        public void setColor(@NotNull ItemStack itemStack, int color) {
        }

        @Override
        public void initCapability(AttachCapabilitiesEvent<ItemStack> event, DuskCapabilityProvider duskCapabilityProvider) {
            armorData.initCapability(event, duskCapabilityProvider);
        }
    }
}
