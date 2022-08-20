package com.til.dusk.client;

import com.google.common.collect.ImmutableList;
import com.til.dusk.Dusk;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.register.*;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.mana_level.ManaLevelBlock;
import com.til.dusk.common.register.mana_level.ManaLevelItem;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreBlock;
import com.til.dusk.common.register.ore.OreItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.BlockModelShaper;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
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

    protected final static Map<OreItem, ModelResourceLocation> ITEM_ORE_MAP = new HashMap<>();
    protected final static Map<OreBlock, ModelResourceLocation> ITEM_BLOCK_ORE_MAP = new HashMap<>();
    protected final static Map<ManaLevelItem, ModelResourceLocation> ITEM_MANA_LEVEL_MAP = new HashMap<>();
    protected final static Map<ManaLevelBlock, ModelResourceLocation> ITEM_BLOCK_MANA_LEVEL_MAP = new HashMap<>();
    protected static final Map<OreBlock, ResourceLocation> BLOCK_ORE_MAP = new HashMap<>();
    protected static final Map<ManaLevelBlock, ResourceLocation> BLOCK_MANA_LEVEL_MAP = new HashMap<>();

    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent event) {
        for (OreItem oreItem : OreItem.ORE_ITEM.get()) {
            ITEM_ORE_MAP.put(oreItem, new ModelResourceLocation(oreItem.name, "inventory"));
        }
        for (OreBlock oreBlock : OreBlock.ORE_BLOCK.get()) {
            ITEM_BLOCK_ORE_MAP.put(oreBlock, new ModelResourceLocation(oreBlock.name, "inventory"));
        }

        for (ManaLevelItem manaLevelItem : ManaLevelItem.LEVEL_ITEM.get()) {
            ITEM_MANA_LEVEL_MAP.put(manaLevelItem, new ModelResourceLocation(manaLevelItem.name, "inventory"));
        }
        for (ManaLevelBlock manaLevelBlock : ManaLevelBlock.LEVEL_BLOCK.get()) {
            ITEM_BLOCK_MANA_LEVEL_MAP.put(manaLevelBlock, new ModelResourceLocation(manaLevelBlock.name, "inventory"));
        }

        for (OreBlock oreBlock : OreBlock.ORE_BLOCK.get()) {
            BLOCK_ORE_MAP.put(oreBlock, makeModelName(oreBlock, BLOCK));
        }
        for (ManaLevelBlock manaLevelBlock : ManaLevelBlock.LEVEL_BLOCK.get()) {
            BLOCK_MANA_LEVEL_MAP.put(manaLevelBlock, makeModelName(manaLevelBlock, BLOCK));
        }
    }

    @SubscribeEvent
    public static void registerMadel(ModelEvent.RegisterAdditional event) {
        ITEM_ORE_MAP.values().forEach(event::register);
        ITEM_BLOCK_ORE_MAP.values().forEach(event::register);
        ITEM_MANA_LEVEL_MAP.values().forEach(event::register);
        ITEM_BLOCK_MANA_LEVEL_MAP.values().forEach(event::register);
        BLOCK_ORE_MAP.values().forEach(event::register);
        BLOCK_ORE_MAP.forEach((k, v) -> {
            List<String> variant = new ArrayList<>();
            Ore.ORE.get().forEach(o -> {
                if (o.blockMap.containsKey(k)) {
                    ImmutableList<BlockState> definition = o.blockMap.get(k).getBlock().getStateDefinition().getPossibleStates();
                    if (definition.size() > 1) {
                        definition.forEach(s -> variant.add(BlockModelShaper.statePropertiesToString(s.getValues())));
                    }
                }
            });
            variant.stream().distinct().toList().forEach(s -> event.register(new ModelResourceLocation(v, s)));
        });
        BLOCK_MANA_LEVEL_MAP.values().forEach(event::register);
        BLOCK_MANA_LEVEL_MAP.forEach((k, v) -> {
            List<String> variant = new ArrayList<>();
            ManaLevel.LEVEL.get().forEach(m -> {
                if (m.blockMap.containsKey(k)) {
                    ImmutableList<BlockState> definition = m.blockMap.get(k).getBlock().getStateDefinition().getPossibleStates();
                    if (definition.size() > 1) {
                        definition.forEach(s -> variant.add(BlockModelShaper.statePropertiesToString(s.getValues())));
                    }
                }
            });
            variant.stream().distinct().toList().forEach(s -> event.register(new ModelResourceLocation(v, s)));
        });
    }

    @SubscribeEvent
    public static void registerMadel(ModelEvent.BakingCompleted event) {

        for (Ore ore : Ore.ORE.get()) {
            ore.itemMap.forEach((k, v) -> Minecraft.getInstance().getItemRenderer().getItemModelShaper().register(v, ITEM_ORE_MAP.get(k)));
            ore.blockMap.forEach((k, v) -> Minecraft.getInstance().getItemRenderer().getItemModelShaper().register(v, ITEM_BLOCK_ORE_MAP.get(k)));
        }
        for (ManaLevel manaLevel : ManaLevel.LEVEL.get()) {
            manaLevel.itemMap.forEach((k, v) -> Minecraft.getInstance().getItemRenderer().getItemModelShaper().register(v, ITEM_MANA_LEVEL_MAP.get(k)));
            manaLevel.blockMap.forEach((k, v) -> Minecraft.getInstance().getItemRenderer().getItemModelShaper().register(v, ITEM_BLOCK_MANA_LEVEL_MAP.get(k)));
        }

        for (Ore ore : Ore.ORE.get()) {
            ore.blockMap.forEach((k, v) -> {
                ImmutableList<BlockState> definition = v.getBlock().getStateDefinition().getPossibleStates();
                if (definition.size() == 1) {
                    event.getModels().put(BlockModelShaper.stateToModelLocation(v.getBlock().getStateDefinition().any()), event.getModelManager().getModel(BLOCK_ORE_MAP.get(k)));
                } else {
                    definition.forEach(s -> event.getModels().put(BlockModelShaper.stateToModelLocation(s), event.getModelManager().getModel(
                            new ModelResourceLocation(BLOCK_ORE_MAP.get(k), BlockModelShaper.statePropertiesToString(s.getValues())))));
                }
            });

        }
        for (ManaLevel manaLevel : ManaLevel.LEVEL.get()) {
            manaLevel.blockMap.forEach((k, v) -> {
                ImmutableList<BlockState> definition = v.getBlock().getStateDefinition().getPossibleStates();
                if (definition.size() == 1) {
                    event.getModels().put(BlockModelShaper.stateToModelLocation(v.getBlock().getStateDefinition().any()), event.getModelManager().getModel(BLOCK_MANA_LEVEL_MAP.get(k)));
                } else {
                    definition.forEach(s -> event.getModels().put(BlockModelShaper.stateToModelLocation(s), event.getModelManager().getModel(
                            new ModelResourceLocation(BLOCK_MANA_LEVEL_MAP.get(k), BlockModelShaper.statePropertiesToString(s.getValues())))));
                }
            });
        }
    }

    @SubscribeEvent
    public static void registerMadel(ModelEvent.RegisterGeometryLoaders event) {

    }


    public static ResourceLocation makeModelName(RegisterBasics<?> registerBasics, String prefix) {
        return new ResourceLocation(registerBasics.name.getNamespace(), prefix + "/" + registerBasics.name.getPath());
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
