package com.til.dusk.common.register.world.item;

import com.til.dusk.Dusk;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.RegisterPack;
import com.til.dusk.common.register.RegisterManage;
import com.til.dusk.common.register.world.item.items.*;
import com.til.dusk.util.ResourceLocationUtil;
import com.til.dusk.util.pack.ItemPack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;

import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public abstract class ItemPackRegister extends RegisterPack<ItemPackRegister, ItemPack> {

    public static Supplier<IForgeRegistry<ItemPackRegister>> ITEM_PACK_REGISTER;

    public static BindStaffItemRegister bindStaffItem;
    public static ClearStaffRegister clearStaff;
    public static CopyStaffItemRegister copyStaffItem;
    public static ShowStaffRegister showStaff;

    public static DiamondMakeOperationBasicsRegister diamondMakeOperationBasics;
    public static DiamondMakeOperationRegister diamondMakeOperation;
    public static DiamondMakeFormingRegister diamondMakeForming;
    public static DiamondMakeDestructionRegister diamondMakeDestruction;
    public static DiamondMakeGatherRegister diamondMakeGather;
    public static DiamondMakeSpreadRegister diamondMakeSpread;
    public static DiamondMakePowerRegister diamondMakePower;
    public static DiamondMakeInstructionsRegister diamondMakeInstructions;


    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        ITEM_PACK_REGISTER = RegisterManage.create(ItemPackRegister.class, new ResourceLocation(Dusk.MOD_ID, "item_pack_register"), event);
        bindStaffItem = new BindStaffItemRegister();
        clearStaff = new ClearStaffRegister();
        copyStaffItem = new CopyStaffItemRegister();
        showStaff = new ShowStaffRegister();
        diamondMakeOperationBasics = new DiamondMakeOperationBasicsRegister();
        diamondMakeOperation = new DiamondMakeOperationRegister();
        diamondMakeForming = new DiamondMakeFormingRegister();
        diamondMakeDestruction = new DiamondMakeDestructionRegister();
        diamondMakeGather = new DiamondMakeGatherRegister();
        diamondMakeSpread = new DiamondMakeSpreadRegister();
        diamondMakePower = new DiamondMakePowerRegister();
        diamondMakeInstructions = new DiamondMakeInstructionsRegister();
    }

    public ItemPackRegister(ResourceLocation name) {
        super(name, ITEM_PACK_REGISTER);
    }

    public ItemPackRegister(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    @Override
    protected ItemPack create() {
        Item item = createItem();
        if (item == null) {
            return null;
        }
        return new ItemPack(item, createItemTag());
    }

    protected abstract Item createItem();

    protected TagKey<Item> createItemTag() {
        return tagPackSupplier.getTagPack().itemTagKey();
    }

    @Override
    protected void register(ItemPack itemPack) {
        ForgeRegistries.ITEMS.register(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{"item", name.getPath()}), itemPack.item());
        ItemTag.addTag(itemPack.itemTag(), itemPack.item());
    }
}
