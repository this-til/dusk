package com.til.dusk.common.register.shaped;

import com.til.dusk.Dusk;
import com.til.dusk.common.capability.mana_level.IManaLevel;
import com.til.dusk.common.capability.tile_entity.DuskCapabilityProvider;
import com.til.dusk.common.register.CapabilityRegister;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.world.ModBlock;
import com.til.dusk.util.Lang;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ShapedDrive extends RegisterBasics<ShapedDrive> {

    public static Supplier<IForgeRegistry<ShapedDrive>> SHAPED_DRIVE;
    public final static Map<Integer, ShapedDrive> SHAPED_DRIVE_MAP = new HashMap<>();

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        SHAPED_DRIVE = event.create(new RegistryBuilder<ShapedDrive>().setName(new ResourceLocation(Dusk.MOD_ID, "shaped_drive")));
        for (int i = 0; i < 16; i++) {
            new ShapedDrive(i);
        }
    }

    public static ShapedDrive get(int i) {
        if (SHAPED_DRIVE_MAP.containsKey(i)) {
            return SHAPED_DRIVE_MAP.get(i);
        }
        return SHAPED_DRIVE_MAP.get(0);
    }

    public BlockItem blockItem;
    public final List<ShapedDrive> thisShapedDriveList;

    public ShapedDrive(int id) {
        this(new ResourceLocation(Dusk.MOD_ID, ((Integer) id).toString()), id);
    }

    public ShapedDrive(ResourceLocation name, int id) {
        super(name, SHAPED_DRIVE);
        SHAPED_DRIVE_MAP.put(id, this);
        thisShapedDriveList = List.of(this);
    }

    @Override
    public void registerSubsidiaryBlack() {
        Block block = new ModBlock.MechanicBlock(ManaLevel.t1) {
            @Override
            public void add(AttachCapabilitiesEvent<BlockEntity> event, DuskCapabilityProvider duskModCapability) {
                duskModCapability.addCapability(CapabilityRegister.iManaLevel.capability, () -> ManaLevel.t1);
                duskModCapability.addCapability(CapabilityRegister.iShapedDrive.capability, () -> thisShapedDriveList);
            }
        };
        Objects.requireNonNull(ForgeRegistries.BLOCKS.tags()).addOptionalTagDefaults(BlockTags.create(SHAPED_DRIVE.get().getRegistryName()), Set.of(() -> block));
        Objects.requireNonNull(ForgeRegistries.BLOCKS.tags()).addOptionalTagDefaults(BlockTags.create(name), Set.of(() -> block));
        ForgeRegistries.BLOCKS.register(name, block);

        blockItem = new BlockItem(block, new Item.Properties().tab(Dusk.TAB)){
            @Override
            public @NotNull Component getName(@NotNull ItemStack itemStack) {
                return Component.translatable(Lang.getKey("shaped_drive"), Component.literal(name.getPath()));
            }
        };
        Objects.requireNonNull(ForgeRegistries.ITEMS.tags()).addOptionalTagDefaults(ItemTags.create(SHAPED_DRIVE.get().getRegistryName()), Set.of(() -> blockItem));
        Objects.requireNonNull(ForgeRegistries.ITEMS.tags()).addOptionalTagDefaults(ItemTags.create(name), Set.of(() -> blockItem));
        ForgeRegistries.ITEMS.register(name, blockItem);
    }
}
