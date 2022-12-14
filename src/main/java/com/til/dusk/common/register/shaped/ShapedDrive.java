package com.til.dusk.common.register.shaped;

import com.google.gson.JsonObject;
import com.til.dusk.Dusk;
import com.til.dusk.common.capability.DuskCapabilityProvider;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.data.tag.BlockTag;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.common.register.RegisterManage;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.other.CapabilityRegister;
import com.til.dusk.common.world.block.MechanicBlock;
import com.til.dusk.common.world.block.DuskBlock;
import com.til.dusk.util.Lang;
import com.til.dusk.util.ModelJsonUtil;
import com.til.dusk.util.pack.BlockPack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ShapedDrive extends RegisterBasics<ShapedDrive> implements DuskBlock.ICustomModel<Void> {

    public static final ResourceLocation MODEL_NAME = new ResourceLocation(Dusk.MOD_ID, "shaped_drive");
    public static Supplier<IForgeRegistry<ShapedDrive>> SHAPED_DRIVE;
    public final static Map<Integer, ShapedDrive> SHAPED_DRIVE_MAP = new HashMap<>();

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        SHAPED_DRIVE = RegisterManage.create(ShapedDrive.class, new ResourceLocation(Dusk.MOD_ID, "shaped_drive"), event);
        for (int i = 0; i < 32; i++) {
            new ShapedDrive(i);
        }
    }

    public static ShapedDrive get(int i) {
        if (SHAPED_DRIVE_MAP.containsKey(i)) {
            return SHAPED_DRIVE_MAP.get(i);
        }
        return SHAPED_DRIVE_MAP.get(0);
    }

    public BlockPack blockPack;
    public final List<ShapedDrive> thisShapedDriveList;

    protected ShapedDrive(int id) {
        super(new ResourceLocation(Dusk.MOD_ID, String.valueOf(id)), SHAPED_DRIVE);
        SHAPED_DRIVE_MAP.put(id, this);
        thisShapedDriveList = List.of(this);
    }

    @Override
    protected void registerBack() {
        Block block = new MechanicBlock(ManaLevel.t1) {
            @Override
            public void add(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability, IPosTrack iPosTrack) {
                duskModCapability.addCapability(CapabilityRegister.iManaLevel.capability, () -> ManaLevel.t1);
                duskModCapability.addCapability(CapabilityRegister.iShapedDrive.capability, () -> thisShapedDriveList);
            }
        };
        TagKey<Block> blockTag = BlockTags.create(name);
        BlockTag.addTag(blockTag, block);
        BlockTag.addTag(BlockTags.create(SHAPED_DRIVE.get().getRegistryName()), block);
        ForgeRegistries.BLOCKS.register(name, block);

        BlockItem blockItem = new BlockItem(block, new Item.Properties().tab(Dusk.TAB)) {
            @Override
            public @NotNull Component getName(@NotNull ItemStack itemStack) {
                return Component.translatable(Lang.getKey("shaped_drive"), Component.literal(name.getPath()));
            }
        };
        TagKey<Item> itemTag = ItemTags.create(name);
        ItemTag.addTag(itemTag, blockItem);
        ItemTag.addTag(ItemTags.create(SHAPED_DRIVE.get().getRegistryName()), blockItem);
        ForgeRegistries.ITEMS.register(name, blockItem);
        blockPack = new BlockPack(block, blockTag, blockItem, itemTag);
    }

    @Override
    public JsonObject createBlockModel(Block block, Void unused) {
        return ModelJsonUtil.createBlockState(MODEL_NAME);
    }

    @Override
    public JsonObject createModel(Item item, Void unused) {
        return ModelJsonUtil.createItemFather(MODEL_NAME);
    }

    @Override
    public void defaultConfig() {

    }
}
