package com.til.dusk.client;

import com.google.common.collect.ImmutableList;
import com.til.dusk.Dusk;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.register.mana_level.ManaLevelFluid;
import com.til.dusk.common.register.mana_level.ManaLevelItem;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreBlock;
import com.til.dusk.common.register.ore.OreFluid;
import com.til.dusk.common.register.ore.OreItem;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.world.block.ModBlock;
import com.til.dusk.common.world.item.ModItem;
import com.til.dusk.util.pack.BlockPack;
import com.til.dusk.util.pack.FluidPack;
import com.til.dusk.util.pack.ItemPack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelShaper;
import net.minecraft.client.renderer.block.BlockModelShaper;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.RegistryObject;

import java.util.*;

/**
 * @author til
 */
@OnlyIn(Dist.CLIENT)
@Deprecated
//@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModelProxy {

    public final static String ITEM = "item";
    public final static String BLOCK = "block";
    public static final String BLOCK_STATES = "blockstates";

    protected final static Map<Item, ModelResourceLocation> ITEM_MODEL_MAP = new HashMap<>();
    protected final static Map<BlockState, ResourceLocation> BLOCK_STATE_MAP = new HashMap<>();

    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent event) {
        for (Ore ore : Ore.ORE.get()) {
            for (Map.Entry<OreItem, ItemPack> entry : ore.itemMap.entrySet()) {
                ITEM_MODEL_MAP.put(entry.getValue().item(), new ModelResourceLocation(entry.getKey().getItemMoldMapping(ore), "inventory"));
            }
            for (Map.Entry<OreBlock, BlockPack> entry : ore.blockMap.entrySet()) {
                ITEM_MODEL_MAP.put(entry.getValue().blockItem(), new ModelResourceLocation(entry.getKey().getBlockItemMoldMapping(ore), "inventory"));
                ImmutableList<BlockState> definition = entry.getValue().block().getStateDefinition().getPossibleStates();
                if (definition.size() == 1) {
                    BLOCK_STATE_MAP.put(definition.get(0), makeModelName(entry.getKey().getBlockModelMapping(ore), BLOCK));
                } else {
                    for (BlockState blockState : definition) {
                        BLOCK_STATE_MAP.put(blockState, new ModelResourceLocation(entry.getKey().getBlockItemMoldMapping(ore), BlockModelShaper.statePropertiesToString(blockState.getValues())));
                    }
                }
            }
            for (Map.Entry<OreFluid, FluidPack> entry : ore.fluidMap.entrySet()) {
                if (entry.getValue().bucketItem() != null) {
                    ITEM_MODEL_MAP.put(entry.getValue().bucketItem(), new ModelResourceLocation(entry.getKey().name, "inventory"));
                }
                if (entry.getValue().liquidBlock() != null) {
                    ImmutableList<BlockState> definition = entry.getValue().liquidBlock().getStateDefinition().getPossibleStates();
                    if (definition.size() == 1) {
                        BLOCK_STATE_MAP.put(definition.get(0), makeModelName(entry.getKey(), BLOCK));
                    } else {
                        for (BlockState blockState : definition) {
                            BLOCK_STATE_MAP.put(blockState, new ModelResourceLocation(entry.getKey().name, BlockModelShaper.statePropertiesToString(blockState.getValues())));
                        }
                    }
                }
            }
        }
        for (RegistryObject<Item> entry : ModItem.ITEMS.getEntries()) {
            Item item = entry.get();
            if (item instanceof ModItem.ICustomModel iCustomModel) {
                ITEM_MODEL_MAP.put(item, new ModelResourceLocation(iCustomModel.itemModelName(), "inventory"));
            }
        }

        for (ManaLevel manaLevel : ManaLevel.LEVEL.get()) {
            for (Map.Entry<ManaLevelItem, ItemPack> entry : manaLevel.itemMap.entrySet()) {
                ITEM_MODEL_MAP.put(entry.getValue().item(), new ModelResourceLocation(entry.getKey().getItemMoldMapping(manaLevel), "inventory"));
            }
            for (Map.Entry<ManaLevelBlock, BlockPack> entry : manaLevel.blockMap.entrySet()) {
                ITEM_MODEL_MAP.put(entry.getValue().blockItem(), new ModelResourceLocation(entry.getKey().getBlockItemMoldMapping(manaLevel), "inventory"));
                ImmutableList<BlockState> definition = entry.getValue().block().getStateDefinition().getPossibleStates();
                if (definition.size() == 1) {
                    BLOCK_STATE_MAP.put(definition.get(0), makeModelName(entry.getKey().getBlockModelMapping(manaLevel), BLOCK));
                } else {
                    for (BlockState blockState : definition) {
                        BLOCK_STATE_MAP.put(blockState, new ModelResourceLocation(entry.getKey().getBlockModelMapping(manaLevel), BlockModelShaper.statePropertiesToString(blockState.getValues())));
                    }
                }
            }
            for (Map.Entry<ManaLevelFluid, FluidPack> entry : manaLevel.fluidMap.entrySet()) {
                if (entry.getValue().bucketItem() != null) {
                    ITEM_MODEL_MAP.put(entry.getValue().bucketItem(), new ModelResourceLocation(entry.getKey().name, "inventory"));
                }
                if (entry.getValue().liquidBlock() != null) {
                    ImmutableList<BlockState> definition = entry.getValue().liquidBlock().getStateDefinition().getPossibleStates();
                    if (definition.size() == 1) {
                        BLOCK_STATE_MAP.put(definition.get(0), makeModelName(entry.getKey(), BLOCK));
                    } else {
                        for (BlockState blockState : definition) {
                            BLOCK_STATE_MAP.put(blockState, new ModelResourceLocation(entry.getKey().name, BlockModelShaper.statePropertiesToString(blockState.getValues())));
                        }
                    }
                }
            }
        }
        for (RegistryObject<Block> entry : ModBlock.BLOCKS.getEntries()) {
            Block block = entry.get();
            if (block instanceof ModBlock.ICustomModel iCustomModel) {
                ImmutableList<BlockState> definition = block.getStateDefinition().getPossibleStates();
                if (definition.size() == 1) {
                    BLOCK_STATE_MAP.put(definition.get(0), iCustomModel.blockModelName());
                } else {
                    for (BlockState blockState : definition) {
                        BLOCK_STATE_MAP.put(blockState, new ModelResourceLocation(iCustomModel.blockModelName(), BlockModelShaper.statePropertiesToString(blockState.getValues())));
                    }
                }

            }
        }
    }

    @SubscribeEvent
    public static void registerMadel(ModelEvent.RegisterAdditional event) {
        List<ResourceLocation> list = new ArrayList<>();
        list.addAll(ITEM_MODEL_MAP.values());
        list.addAll(BLOCK_STATE_MAP.values());
        list.stream().distinct().toList().forEach(event::register);
    }

    @SubscribeEvent
    public static void registerMadel(ModelEvent.BakingCompleted event) {
        Map<ResourceLocation, BakedModel> map = event.getModels();
        ModelManager modelManager = event.getModelManager();
        ItemModelShaper itemModelShaper = Minecraft.getInstance().getItemRenderer().getItemModelShaper();

        for (Map.Entry<Item, ModelResourceLocation> entry : ITEM_MODEL_MAP.entrySet()) {
            itemModelShaper.register(entry.getKey(), entry.getValue());
        }

        for (Map.Entry<BlockState, ResourceLocation> entry : BLOCK_STATE_MAP.entrySet()) {
            map.put(BlockModelShaper.stateToModelLocation(entry.getKey()), modelManager.getModel(entry.getValue()));
        }
    }

    @SubscribeEvent
    public static void registerMadel(ModelEvent.RegisterGeometryLoaders event) {

    }

    public static ResourceLocation makeModelName(ResourceLocation resourceLocation, String prefix) {
        return new ResourceLocation(resourceLocation.getNamespace(), prefix + "/" + resourceLocation.getPath());
    }

    public static ResourceLocation makeModelName(RegisterBasics<?> registerBasics, String prefix) {
        return makeModelName(registerBasics.name, prefix);
    }
}
