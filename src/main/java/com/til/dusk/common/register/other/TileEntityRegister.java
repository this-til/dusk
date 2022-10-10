package com.til.dusk.common.register.other;

import com.mojang.datafixers.types.Type;
import com.til.dusk.Dusk;
import com.til.dusk.common.capability.tile_entity.DefaultTileEntity;
import com.til.dusk.common.capability.tile_entity.ITileEntityType;
import com.til.dusk.common.capability.tile_entity.RepeaterTileEntity;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.common.register.RegisterManage;
import com.til.dusk.common.register.mana_level.block.ManaLevelBlock;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.world.block.RepeaterBlock;
import com.til.dusk.util.ResourceLocationUtil;
import com.til.dusk.util.Util;
import com.til.dusk.util.pack.BlockPack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;

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
        TILE_ENTITY_REGISTER = RegisterManage.create(Util.forcedConversion(TileEntityRegister.class), new ResourceLocation(Dusk.MOD_ID, "tile_entity_register"), event);
        defaultTileEntityRegister = new TileEntityRegister<>("default_tile_entity_register") {
            @Override
            protected void registerBlackToBack() {
                for (ManaLevel manaLevel : ManaLevel.MANA_LEVEL.get()) {
                    for (Map.Entry<ManaLevelBlock, BlockPack> e : manaLevel.blockEntrySet()) {
                        if (e.getValue().block() instanceof ITileEntityType iTileEntityType) {
                            BlockEntityType.Builder<DefaultTileEntity> builder = BlockEntityType.Builder.of((blockPos, blockState) ->
                                    new DefaultTileEntity(tileEntityTypeBlockEntityTypeMap.get(iTileEntityType), blockPos, blockState), e.getValue().block());
                            Type<?> type = net.minecraft.Util.fetchChoiceType(References.BLOCK_ENTITY, ResourceLocationUtil.fuseName("_", this, manaLevel, e.getKey()).toString());
                            BlockEntityType<DefaultTileEntity> blockEntityType = builder.build(type);
                            tileEntityTypeBlockEntityTypeMap.put(iTileEntityType, blockEntityType);
                            blockBlockEntityTypeMap.put(e.getValue().block(), blockEntityType);
                            ForgeRegistries.BLOCK_ENTITY_TYPES.register(ResourceLocationUtil.fuseName("_", this, manaLevel, e.getKey()), blockEntityType);
                        }
                    }
                }
                for (ShapedDrive shapedDrive : ShapedDrive.SHAPED_DRIVE.get()) {
                    if (shapedDrive.blockPack.block() instanceof ITileEntityType iTileEntityType) {
                        BlockEntityType.Builder<DefaultTileEntity> builder = BlockEntityType.Builder.of((blockPos, blockState) ->
                                new DefaultTileEntity(tileEntityTypeBlockEntityTypeMap.get(iTileEntityType), blockPos, blockState), shapedDrive.blockPack.block());
                        Type<?> type = net.minecraft.Util.fetchChoiceType(References.BLOCK_ENTITY, ResourceLocationUtil.fuseName("_", this, shapedDrive).toString());
                        BlockEntityType<DefaultTileEntity> blockEntityType = builder.build(type);
                        tileEntityTypeBlockEntityTypeMap.put(iTileEntityType, blockEntityType);
                        blockBlockEntityTypeMap.put(shapedDrive.blockPack.block(), blockEntityType);
                        ForgeRegistries.BLOCK_ENTITY_TYPES.register(ResourceLocationUtil.fuseName("_", this, shapedDrive), blockEntityType);
                    }
                }
            }
        };
        repeaterTileEntityRegister = new TileEntityRegister<>("repeater_tile_entity_register") {
            @Override
            protected void registerBlackToBack() {
                for (ManaLevel manaLevel : ManaLevel.MANA_LEVEL.get()) {
                    for (Map.Entry<ManaLevelBlock, BlockPack> e : manaLevel.blockEntrySet()) {
                        if (e.getValue().block() instanceof RepeaterBlock repeaterBlock) {
                            BlockEntityType.Builder<RepeaterTileEntity> builder = BlockEntityType.Builder.of((blockPos, blockState) ->
                                    new RepeaterTileEntity(blockBlockEntityTypeMap.get(e.getValue().block()), blockPos, blockState), e.getValue().block());
                            Type<?> type = net.minecraft.Util.fetchChoiceType(References.BLOCK_ENTITY, ResourceLocationUtil.fuseName("_", this, manaLevel).toString());
                            BlockEntityType<RepeaterTileEntity> blockEntityType = builder.build(type);
                            ForgeRegistries.BLOCK_ENTITY_TYPES.register(ResourceLocationUtil.fuseName("_", this, manaLevel), blockEntityType);
                            blockBlockEntityTypeMap.put(repeaterBlock, blockEntityType);
                        }
                    }
                }
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
    protected abstract void registerBlackToBack();

    /*@Override
    public EventPriority getRegisterBlackPriority() {
        return EventPriority.LOWEST;
    }*/

    @Override
    public void defaultConfig() {

    }
}
