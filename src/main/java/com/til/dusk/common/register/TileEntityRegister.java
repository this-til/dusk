package com.til.dusk.common.register;

import com.mojang.datafixers.types.Type;
import com.til.dusk.Dusk;
import com.til.dusk.common.capability.tile_entity.DefaultTileEntity;
import com.til.dusk.common.capability.tile_entity.ITileEntityType;
import com.til.dusk.common.capability.tile_entity.RepeaterTileEntity;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.world.ModBlock;
import com.til.dusk.util.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public abstract class TileEntityRegister<T extends BlockEntity> extends RegisterBasics<TileEntityRegister<T>> {

    public static Supplier<IForgeRegistry<TileEntityRegister<?>>> TILE_ENTITY_REGISTER;

    public static TileEntityRegister<DefaultTileEntity> defaultTileEntityRegister;
    public static TileEntityRegister<RepeaterTileEntity> repeaterTileEntityRegister;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        TILE_ENTITY_REGISTER = event.create(new RegistryBuilder<TileEntityRegister<?>>().setName(new ResourceLocation(Dusk.MOD_ID, "tile_entity_register")));
        defaultTileEntityRegister = new TileEntityRegister<>("default_tile_entity_register") {
            @Override
            public void registerSubsidiaryBlack() {
                ManaLevel.LEVEL.get().forEach(l -> l.blockMap.forEach((k, v) -> {
                    if (v.block() instanceof ITileEntityType iTileEntityType) {
                        BlockEntityType.Builder<DefaultTileEntity> builder = BlockEntityType.Builder.of((blockPos, blockState) ->
                                new DefaultTileEntity(tileEntityTypeBlockEntityTypeMap.get(iTileEntityType), blockPos, blockState), v.block());
                        Type<?> type = net.minecraft.Util.fetchChoiceType(References.BLOCK_ENTITY, fuseName("_", this, l, k).toString());
                        BlockEntityType<DefaultTileEntity> blockEntityType = builder.build(type);
                        tileEntityTypeBlockEntityTypeMap.put(iTileEntityType, blockEntityType);
                        blockBlockEntityTypeMap.put(v.block(), blockEntityType);
                        ForgeRegistries.BLOCK_ENTITY_TYPES.register(fuseName("_", this, l, k), blockEntityType);
                    }
                }));
                ShapedDrive.SHAPED_DRIVE.get().forEach(s -> {
                    if (s.blockItem.getBlock() instanceof ITileEntityType iTileEntityType) {
                        BlockEntityType.Builder<DefaultTileEntity> builder = BlockEntityType.Builder.of((blockPos, blockState) ->
                                new DefaultTileEntity(tileEntityTypeBlockEntityTypeMap.get(iTileEntityType), blockPos, blockState), s.blockItem.getBlock());
                        Type<?> type = net.minecraft.Util.fetchChoiceType(References.BLOCK_ENTITY, fuseName("_", this, s).toString());
                        BlockEntityType<DefaultTileEntity> blockEntityType = builder.build(type);
                        tileEntityTypeBlockEntityTypeMap.put(iTileEntityType, blockEntityType);
                        blockBlockEntityTypeMap.put(s.blockItem.getBlock(), blockEntityType);
                        ForgeRegistries.BLOCK_ENTITY_TYPES.register(fuseName("_", this, s), blockEntityType);
                    }
                });
            }
        };
        repeaterTileEntityRegister = new TileEntityRegister<>("repeater_tile_entity_register") {
            @Override
            public void registerSubsidiaryBlack() {
                ManaLevel.LEVEL.get().forEach(l -> l.blockMap.forEach((k, v) -> {
                    if (v.block() instanceof ModBlock.RepeaterBlock repeaterBlock) {
                        BlockEntityType.Builder<RepeaterTileEntity> builder = BlockEntityType.Builder.of((blockPos, blockState) ->
                                new RepeaterTileEntity(blockBlockEntityTypeMap.get(v.block()), blockPos, blockState), v.block());
                        Type<?> type = net.minecraft.Util.fetchChoiceType(References.BLOCK_ENTITY, fuseName("_", this, l).toString());
                        BlockEntityType<RepeaterTileEntity> blockEntityType = builder.build(type);
                        ForgeRegistries.BLOCK_ENTITY_TYPES.register(fuseName("_", this, l), blockEntityType);
                        blockBlockEntityTypeMap.put(repeaterBlock, blockEntityType);
                    }
                }));
            }
        };
    }

    public final Map<ITileEntityType, BlockEntityType<T>> tileEntityTypeBlockEntityTypeMap = new HashMap<>();
    public final Map<Block, BlockEntityType<T>> blockBlockEntityTypeMap = new HashMap<>();

    public TileEntityRegister(ResourceLocation name) {
        super(name, Util.forcedConversion(TILE_ENTITY_REGISTER));
    }

    public TileEntityRegister(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    /***
     * 在此注册实体方块类型
     */
    @Override
    public abstract void registerSubsidiaryBlack();

    @Override
    public EventPriority getRegisterBlackPriority() {
        return EventPriority.LOWEST;
    }
}
