package com.til.dusk.common.world.item;


import com.google.gson.JsonObject;
import com.til.dusk.Dusk;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.nbt.pack.AllNBTPack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DuskItem {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Dusk.MOD_ID);

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
    public interface ICustomModel<D> {
        @Nullable
        JsonObject createModel(Item item, D d);
    }


}
