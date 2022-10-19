package com.til.dusk.common.register.ore.block;

import com.til.dusk.Dusk;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.config.ConfigField;
import com.til.dusk.common.register.BlockUnitRegister;
import com.til.dusk.common.register.RegisterManage;
import com.til.dusk.common.register.ore.block.blocks.*;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.util.Lang;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
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
    public static CoilOreBlockMetal coil;

    /***
     * 分压原件
     */
    public static PartialPressureOreBlockMetal partialPressure;

    /***
     * 脉冲元件
     */
    public static PulseOreBlockMetal pulse;

    /***
     * 束缚元件
     */
    public static ShacklesOreBlockMetal shackles;

    /***
     * 置换元件
     */
    public static SubstitutionOreBlockMetal substitution;

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
        ORE_BLOCK = RegisterManage.create(OreBlock.class, new ResourceLocation(Dusk.MOD_ID, "ore_block"), event);
        lordWorldStone = new LordWorldStoneOreBlockMineral();
        lordWorldDeepslate = new LordWorldDeepslateOreBlockMineral();
        lordWorldSand = new LordWorldSandOreBlockMineral();
        lordWorldDirt = new LordWorldDirtOreBlockMineral();
        lordWorldGravel = new LordWorldGravelOreBlockMineral();
        netherWorldNetherrack = new NetherWorldNetherrackOreBlockMineral();
        endWorldEndStone = new EndWorldEndStoneOreBlockMineral();
        bracket = new BracketOreBlockMetal();
        coil = new CoilOreBlockMetal();
        partialPressure = new PartialPressureOreBlockMetal();
        pulse = new PulseOreBlockMetal();
        shackles = new ShacklesOreBlockMetal();
        substitution = new SubstitutionOreBlockMetal();
        block = new BlockOreBlockDecorate();
        slab = new SlabOreBlockDecorate();
        stairs = new StairsOreBlockDecorate();
        wall = new WallOreBlockDecorate();
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
        itemColorPack.addColor(1, itemStack -> ore.color);
    }

    @Override
    public void dyeBlack(Ore ore, ColorProxy.BlockColorPack itemColorPack) {
        itemColorPack.addColor(1, (blockState, blockAndTintGetter, blockPos) -> ore.color);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /***
     * 强度
     */
    @ConfigField
    public double strengthBasics = 1;

    /***
     * 防爆
     */
    @ConfigField
    public double explosionProofBasics = 1;

    protected void strength(double basics) {
        strengthBasics = basics;
        explosionProofBasics = basics * 2;
    }
}
