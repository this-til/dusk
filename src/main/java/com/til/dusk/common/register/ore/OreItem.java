package com.til.dusk.common.register.ore;

import com.til.dusk.Dusk;
import com.til.dusk.util.Lang;
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
     * 晶体
     */
    public static OreItem crystal;

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
        ingot = new OreItem("ingot") {
            @Override
            public Item create(Ore ore) {
                if (!ore.hasMineral) {
                    return null;
                }
                if (ore.isCrystal) {
                    return null;
                }
                return super.create(ore);
            }
        };
        crystal = new OreItem("crystal") {
            @Override
            public Item create(Ore ore) {
                if (!ore.hasMineral) {
                    return null;
                }
                if (!ore.isCrystal) {
                    return null;
                }
                return super.create(ore);
            }
        };
        nuggets = new OreItem("nuggets") {
            @Override
            public Item create(Ore ore) {
                if (!ore.hasMineral) {
                    return null;
                }
                if (ore.isCrystal) {
                    return null;
                }
                return super.create(ore);
            }
        };
        crushed = new OreItem("crushed") {
            @Override
            public Item create(Ore ore) {
                if (!ore.hasMineral) {
                    return null;
                }
                return super.create(ore);
            }
        };
        crushedPurified = new OreItem("crushed_purified") {
            @Override
            public Item create(Ore ore) {
                if (!ore.hasMineral) {
                    return null;
                }
                return super.create(ore);
            }
        };
        dust = new OreItem("dust") {
            @Override
            public Item create(Ore ore) {

                if (!ore.hasMineral) {
                    return null;
                }
                return super.create(ore);
            }
        };
        dustTiny = new OreItem("dust_tiny") {
            @Override
            public Item create(Ore ore) {
                if (!ore.hasMineral) {
                    return null;
                }
                return super.create(ore);
            }
        };
    }

    public OreItem(ResourceLocation name) {
        super(name, ORE_ITEM);
    }

    public OreItem(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    @Override
    public Item create(Ore ore) {
        OreItem oreItem = this;
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
