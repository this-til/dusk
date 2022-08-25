package com.til.dusk.common.register.ore;

import com.til.dusk.Dusk;
import com.til.dusk.util.Lang;
import com.til.dusk.util.StaticTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OreItem extends Ore.OreType<OreItem, Item> {

    public static Supplier<IForgeRegistry<OreItem>> ORE_ITEM;


    /***
     * 矿物锭
     */
    public static OreItem ingot;

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
     * 粒
     */
    public static OreItem nuggets;

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

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        ORE_ITEM = event.create(new RegistryBuilder<OreItem>().setName(new ResourceLocation(Dusk.MOD_ID, "ore_item")));
        ingot = new OreItem("ingot", List.of(Ore.IS_METAL));
        damagedCrystal = new OreItem("damaged_crystal", List.of(Ore.IS_CRYSTA));
        crystal = new OreItem("crystal", List.of(Ore.IS_CRYSTA));
        delicateCrystal = new OreItem("perfect_crystal", List.of(Ore.IS_CRYSTA));
        perfectCrystal = new OreItem("perfect_crystal", List.of(Ore.IS_CRYSTA));
        nuggets = new OreItem("nuggets", List.of(Ore.IS_METAL));
        crushed = new OreItem("crushed", List.of(Ore.HAS_CRUSHED));
        crushedPurified = new OreItem("crushed_purified", List.of(Ore.HAS_CRUSHED));
        dust = new OreItem("dust", List.of(Ore.HAS_DUST));
        dustTiny = new OreItem("dust_tiny", List.of(Ore.HAS_DUST));
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
    public Item create(Ore ore) {
        if (!ore.hasTag(oreHasTag)) {
            return null;
        }
        Item item = new Item(new Item.Properties().tab(Dusk.TAB)) {
            @Override
            public @NotNull Component getName(ItemStack stack) {
                return Lang.getLang(ore, OreItem.this);
            }
        };
        ForgeRegistries.ITEMS.register(fuseName(ore, this), item);
        Objects.requireNonNull(ForgeRegistries.ITEMS.tags()).addOptionalTagDefaults(ItemTags.create(fuseName(ore, this)), Set.of(() -> item));
        return item;
    }
}
