package com.til.dusk.common.world.item;


import com.til.dusk.Dusk;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.nbt.pack.AllNBTPack;
import com.til.dusk.util.prefab.JsonPrefab;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.*;

import java.text.MessageFormat;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItem {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Dusk.MOD_ID);

    public static final RegistryObject<BindStaffItem> BIND_STAFF = ITEMS.register("bind_staff", BindStaffItem::new);
    public static final RegistryObject<CopyStaffItem> COPY_STAFF = ITEMS.register("copy_staff", CopyStaffItem::new);
    public static final RegistryObject<ShowStaffItem> SHOW_STAFF = ITEMS.register("show_staff", ShowStaffItem::new);
    public static final RegistryObject<ClearStaffItem> CLEAR_STAFF = ITEMS.register("clear_staff", ClearStaffItem::new);

    /***
     * 废料
     */
    public static final RegistryObject<Item> WASTE = ITEMS.register("waste", () -> new BoneMealItem(new Item.Properties().tab(Dusk.TAB)));

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        ITEMS.register(Dusk.instance.modEventBus);
    }

    @SubscribeEvent
    public static void onEvent(FMLCommonSetupEvent event) {
        for (RegistryObject<Item> entry : ITEMS.getEntries()) {
            ResourceLocation resourceLocation = ForgeRegistries.ITEMS.getKey(entry.get());
            if (resourceLocation == null) {
                continue;
            }
            ItemTag.addTag(ItemTags.create(resourceLocation), entry.get());
        }
    }

    /***
     * 需要自定义颜色
     */
    public interface IHasCustomColor {
        /***
         * 物品颜色的回调
         * @param itemColorPack 物品染色
         */
        default void itemColorBlack(ColorProxy.ItemColorPack itemColorPack) {
            itemColorPack.addColor(0, itemStack -> {
                CompoundTag compoundTag = itemStack.getTag();
                if (compoundTag == null) {
                    return new DuskColor(-1);
                }
                return new DuskColor(AllNBTPack.COLOR.get(compoundTag));
            });
        }
    }

    /***
     * 自定义模型
     */
    public interface ICustomModel {
        /***
         * 物品自定义模型名称
         * @R
         */
        ResourceLocation itemModelName();

        default String itemJson() {
            ResourceLocation resourceLocation = itemModelName();
            return MessageFormat.format(itemJsonBasics(), resourceLocation.getNamespace(), resourceLocation.getPath());
        }

        default String itemJsonBasics() {
            return JsonPrefab.ITEM_FATHER;
        }
    }


}
