package com.til.dusk.client;

import com.google.common.collect.ImmutableList;
import com.til.dusk.Dusk;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.mana_level.ManaLevelBlock;
import com.til.dusk.common.register.mana_level.ManaLevelItem;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreBlock;
import com.til.dusk.common.register.ore.OreItem;
import com.til.dusk.common.register.shaped.ShapedDrive;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelShaper;
import net.minecraft.client.renderer.block.BlockModelShaper;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.*;

/**
 * @author til
 */
@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModelProxy {

    public final static String ITEM = "item";
    public final static String BLOCK = "block";
    public static final String BLOCK_STATES = "blockstates";

    protected final static Map<Item, ModelResourceLocation> ITEM_MODEL_MAP = new HashMap<>();
    protected final static Map<BlockState, ResourceLocation> BLOCK_STATE_MAP = new HashMap<>();

    protected static ModelResourceLocation shapedDriveItemName;

    protected static ResourceLocation shapedDriveBlockName;

    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent event) {
        for (Ore ore : Ore.ORE.get()) {
            for (Map.Entry<OreItem, Item> entry : ore.itemMap.entrySet()) {
                ITEM_MODEL_MAP.put(entry.getValue(), new ModelResourceLocation(entry.getKey().name, "inventory"));
            }
            for (Map.Entry<OreBlock, BlockItem> entry : ore.blockMap.entrySet()) {
                ITEM_MODEL_MAP.put(entry.getValue(), new ModelResourceLocation(entry.getKey().name, "inventory"));
                ImmutableList<BlockState> definition = entry.getValue().getBlock().getStateDefinition().getPossibleStates();
                if (definition.size() == 1) {
                    BLOCK_STATE_MAP.put(definition.get(0), makeModelName(entry.getKey(), BLOCK));
                } else {
                    for (BlockState blockState : definition) {
                        BLOCK_STATE_MAP.put(blockState, new ModelResourceLocation(entry.getKey().name, BlockModelShaper.statePropertiesToString(blockState.getValues())));
                    }
                }
            }
        }

        for (ManaLevel manaLevel : ManaLevel.LEVEL.get()) {
            for (Map.Entry<ManaLevelItem, Item> entry : manaLevel.itemMap.entrySet()) {
                ITEM_MODEL_MAP.put(entry.getValue(), new ModelResourceLocation(entry.getKey().name, "inventory"));
            }
            for (Map.Entry<ManaLevelBlock, BlockItem> entry : manaLevel.blockMap.entrySet()) {
                ITEM_MODEL_MAP.put(entry.getValue(), new ModelResourceLocation(entry.getKey().name, "inventory"));
                ImmutableList<BlockState> definition = entry.getValue().getBlock().getStateDefinition().getPossibleStates();
                if (definition.size() == 1) {
                    BLOCK_STATE_MAP.put(definition.get(0), makeModelName(entry.getKey(), BLOCK));
                } else {
                    for (BlockState blockState : definition) {
                        BLOCK_STATE_MAP.put(blockState, new ModelResourceLocation(entry.getKey().name, BlockModelShaper.statePropertiesToString(blockState.getValues())));
                    }
                }
            }
        }

        ResourceLocation shapedDriveName = new ResourceLocation(Dusk.MOD_ID, "shaped_drive");
        for (ShapedDrive shapedDrive : ShapedDrive.SHAPED_DRIVE.get()) {
            ITEM_MODEL_MAP.put(shapedDrive.blockItem, new ModelResourceLocation(shapedDriveName, "inventory"));
            BlockState blockState = shapedDrive.blockItem.getBlock().defaultBlockState();
            BLOCK_STATE_MAP.put(blockState, makeModelName(shapedDriveName, BLOCK));
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

   /* @SubscribeEvent
    public void registerMadel(ModelEvent event) {

        for (Ore ore : Ore.register) {
            ore.item.forEach((oreType, item) -> ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Objects.requireNonNull(oreType.getRegistryName()), "inventory")));
            ore.itemBlock.forEach((oreBlock, itemBlock) -> ModelLoader.setCustomModelResourceLocation(itemBlock, 0, new ModelResourceLocation(Objects.requireNonNull(oreBlock.getRegistryName()), "inventory")));
            ore.fluidItem.forEach((oreFluid, item) -> ModelBakery.registerItemVariants(item));
            ore.fluidItem.forEach((oreFluid, item) -> {
                ModelLoader.setCustomMeshDefinition(item, stack -> {
                    ResourceLocation fileType = Objects.requireNonNull(oreFluid.getRegistryName());
                    return new ModelResourceLocation(fileType, fileType.getResourcePath());
                });
            });
            ore.fluidBlock.forEach((oreFluid, blockFluidClassic) -> {
                ResourceLocation fluidName = Objects.requireNonNull(blockFluidClassic.getRegistryName());
                ModelLoader.setCustomStateMapper(blockFluidClassic, new StateMapperBase() {
                    @Override
                    protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                        ResourceLocation fileType = Objects.requireNonNull(oreFluid.getRegistryName());
                        return new ModelResourceLocation(fileType, fileType.getResourcePath());
                    }
                });
            });
        }
        for (ManaLevel manaLevel : ManaLevel.register) {
            manaLevel.item.forEach((manaLevelItem, item) -> ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Objects.requireNonNull(manaLevelItem.getRegistryName()), "inventory")));
            manaLevel.itemBlock.forEach((oreBlock, itemBlock) -> ModelLoader.setCustomModelResourceLocation(itemBlock, 0, new ModelResourceLocation(Objects.requireNonNull(oreBlock.getRegistryName()), "inventory")));
        }
        ShapedDrive.register.forEach(d -> ModelLoader.setCustomModelResourceLocation(d.itemBlock, 0, new ModelResourceLocation(AbyssMana2.MODID + ":" + "shaped_drive", "inventory")));
        AllItem.independentItem.forEach(i -> ModelLoader.setCustomModelResourceLocation(i, 0, new ModelResourceLocation(Objects.requireNonNull(i.getRegistryName()), "inventory")));
    }*/
}
