package com.til.dusk.common.world;

import com.til.dusk.Dusk;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryObject;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DuskEnchantment {

    public static final DeferredRegister<Enchantment> ENCHANTMENT = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Dusk.MOD_ID);

    public static final RegistryObject<Enchantment> EMPTY = ENCHANTMENT.register("empty", () -> new Enchantment(Enchantment.Rarity.COMMON, null, EquipmentSlot.values()){
        @Override
        public float getDamageBonus(int level, MobType mobType, ItemStack enchantedItem) {
            return 0;
        }

        @Override
        public boolean allowedInCreativeTab(Item book, CreativeModeTab tab) {
            return false;
        }
    });

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        ENCHANTMENT.register(Dusk.instance.modEventBus);
    }


}
