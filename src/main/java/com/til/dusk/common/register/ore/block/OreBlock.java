package com.til.dusk.common.register.ore.block;

import com.til.dusk.Dusk;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.config.ConfigKey;
import com.til.dusk.common.config.ConfigMap;
import com.til.dusk.common.register.BlockUnitRegister;
import com.til.dusk.common.register.ore.block.blocks.*;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.util.DuskColor;
import com.til.dusk.util.Lang;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public abstract class OreBlock extends BlockUnitRegister<OreBlock, Ore> {
    public static Supplier<IForgeRegistry<OreBlock>> ORE_BLOCK;

    public static LordWorldStoneOreBlockMineral lordWorldStone;
    public static LordWorldDeepslateOreBlockMineral lordWorldDeepslate;
    public static LordWorldSandOreBlockMineral lordWorldSand;
    public static LordWorldDirtOreBlockMineral lordWorldDirt;
    public static LordWorldGravelOreBlockMineral lordWorldGravel;
    public static NetherWorldNetherrackOreBlockMineral netherWorldNetherrack;
    public static EndWorldEndStoneOreBlockMineral endWorldEndStone;

    /***
     * 支架
     */
    public static BracketOreBlockMetal bracket;

    /***
     * 线圈
     */
    public static OreBlock coil;

    /***
     * 快
     */
    public static BlockOreBlockDecorate block;

    /***
     * 半砖
     */
    public static SlabOreBlockDecorate slab;

    /***
     * 楼梯
     */
    public static StairsOreBlockDecorate stairs;

    /***
     * 墙
     */
    public static WallOreBlockDecorate wall;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        ORE_BLOCK = event.create(new RegistryBuilder<OreBlock>().setName(new ResourceLocation(Dusk.MOD_ID, "ore_block")));
        lordWorldStone = new LordWorldStoneOreBlockMineral();
        lordWorldDeepslate = new LordWorldDeepslateOreBlockMineral();
        lordWorldSand = new LordWorldSandOreBlockMineral();
        lordWorldDirt = new LordWorldDirtOreBlockMineral();
        lordWorldGravel = new LordWorldGravelOreBlockMineral();
        netherWorldNetherrack = new NetherWorldNetherrackOreBlockMineral();
        endWorldEndStone = new EndWorldEndStoneOreBlockMineral();
        bracket = new BracketOreBlockMetal();
        coil = new CoilOreBlockMetal();
        block = new BlockOreBlockDecorate();
        slab = new SlabOreBlockDecorate();
        stairs = new StairsOreBlockDecorate();
        wall= new WallOreBlockDecorate();
    }

    public OreBlock(ResourceLocation name) {
        super(name, ORE_BLOCK);
    }

    public OreBlock(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    public BlockItem createBlockItem(Ore ore, Block block) {
        return new BlockItem(block, new Item.Properties().tab(Dusk.TAB)) {
            @Override
            public @NotNull Component getName(@NotNull ItemStack stack) {
                return Lang.getLang(ore, OreBlock.this);
            }
        };
    }


    @Override
    public void dyeBlack(Ore ore, ColorProxy.ItemColorPack itemColorPack) {
        DuskColor color = ore.getConfig(Ore.COLOR);
        itemColorPack.addColor(1, itemStack -> color);
    }

    @Override
    public void dyeBlack(Ore ore, ColorProxy.BlockColorPack itemColorPack) {
        DuskColor color = ore.getConfig(Ore.COLOR);
        itemColorPack.addColor(1, (blockState, blockAndTintGetter, blockPos) -> color);
    }

    @Override
    public ConfigMap defaultConfigMap() {
        return new ConfigMap();
    }

    public static final ConfigKey.VoidConfigKey IS_MINERAL = new ConfigKey.VoidConfigKey("is_mineral");
    public static final ConfigKey.VoidConfigKey IS_DECORATE = new ConfigKey.VoidConfigKey("is_decorate");

}
