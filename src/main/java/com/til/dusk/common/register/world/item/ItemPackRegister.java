package com.til.dusk.common.register.world.item;

import com.til.dusk.Dusk;
import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.config.util.Delayed;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.RegisterPack;
import com.til.dusk.common.register.RegisterManage;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.common.register.world.item.items.*;
import com.til.dusk.common.world.item.DuskItem;
import com.til.dusk.util.ResourceLocationUtil;
import com.til.dusk.util.pack.ItemPack;
import com.til.dusk.util.prefab.JsonPrefab;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public abstract class ItemPackRegister extends RegisterPack<ItemPackRegister, ItemPack> implements DuskItem.ICustomModel {

    public static Supplier<IForgeRegistry<ItemPackRegister>> ITEM_PACK_REGISTER;

    public static BindStaffItemRegister bindStaffItem;
    public static ClearStaffRegister clearStaff;
    public static CopyStaffRegister copyStaffItem;
    public static ShowStaffRegister showStaff;

    public static DiamondMakeOperationBasicsRegister diamondMakeOperationBasics;
    public static DiamondMakeOperationRegister diamondMakeOperation;
    public static DiamondMakeFormingRegister diamondMakeForming;
    public static DiamondMakeDestructionRegister diamondMakeDestruction;
    public static DiamondMakeGatherRegister diamondMakeGather;
    public static DiamondMakeSpreadRegister diamondMakeSpread;
    public static DiamondMakePowerRegister diamondMakePower;
    public static DiamondMakeInstructionsRegister diamondMakeInstructions;

    public static ResistanceRegister resistance;
    public static CapacitanceRegister capacitance;
    public static InductanceRegister inductance;
    public static DiodeRegister diode;
    public static TriodeRegister triode;
    public static PatchInductanceRegister patchInductance;
    public static PatchResistanceRegister patchResistance;
    public static PatchCapacitanceRegister patchCapacitance;
    public static PatchDiodeRegister patchDiode;
    public static PatchTriodeRegister patchTriode;

    public static WasteRegister waste;


    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        ITEM_PACK_REGISTER = RegisterManage.create(ItemPackRegister.class, new ResourceLocation(Dusk.MOD_ID, "item_pack_register"), event);
        bindStaffItem = new BindStaffItemRegister();
        clearStaff = new ClearStaffRegister();
        copyStaffItem = new CopyStaffRegister();
        showStaff = new ShowStaffRegister();
        diamondMakeOperationBasics = new DiamondMakeOperationBasicsRegister();
        diamondMakeOperation = new DiamondMakeOperationRegister();
        diamondMakeForming = new DiamondMakeFormingRegister();
        diamondMakeDestruction = new DiamondMakeDestructionRegister();
        diamondMakeGather = new DiamondMakeGatherRegister();
        diamondMakeSpread = new DiamondMakeSpreadRegister();
        diamondMakePower = new DiamondMakePowerRegister();
        diamondMakeInstructions = new DiamondMakeInstructionsRegister();
        resistance = new ResistanceRegister();
        capacitance = new CapacitanceRegister();
        inductance = new InductanceRegister();
        diode = new DiodeRegister();
        triode = new TriodeRegister();
        patchInductance = new PatchInductanceRegister();
        patchResistance = new PatchResistanceRegister();
        patchCapacitance = new PatchCapacitanceRegister();
        patchDiode = new PatchDiodeRegister();
        patchTriode = new PatchTriodeRegister();
        waste = new WasteRegister();
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

    protected Item createItem() {
        return new Item(new Item.Properties().tab(Dusk.TAB)) {
            @Override
            public @NotNull Component getName(@NotNull ItemStack stack) {
                return Component.translatable(name.toLanguageKey());
            }
        };
    }

    protected TagKey<Item> createItemTag() {
        return tagPackSupplier.getTagPack().itemTagKey();
    }


    @Override
    protected void register(ItemPack itemPack) {
        ForgeRegistries.ITEMS.register(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{"item", name.getPath()}), itemPack.item());
        ItemTag.addTag(itemPack.itemTag(), itemPack.item());
    }

    @Override
    public final void registerShaped(Consumer<Shaped> shapedConsumer) {
        super.registerShaped(shapedConsumer);
        if (defaultShaped != null) {
            for (Shaped shaped : defaultShaped.get()) {
                shapedConsumer.accept(shaped);
            }
        }
    }

    @Nullable
    @ConfigField
    public Delayed<List<Shaped>> defaultShaped;

    @Override
    public ResourceLocation itemModelName() {
        return name;
    }

    @Override
    public String itemJsonBasics() {
        return JsonPrefab.CURRENCY_ITEM_MODEL;
    }
}
