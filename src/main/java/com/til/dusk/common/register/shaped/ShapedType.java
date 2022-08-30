package com.til.dusk.common.register.shaped;

import com.til.dusk.Dusk;
import com.til.dusk.common.capability.handle.IHandle;
import com.til.dusk.common.capability.handle.ShapedHandle;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.mana_level.mana_level_block.ManaLevelBlock;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreBlock;
import com.til.dusk.common.register.ore.OreFluid;
import com.til.dusk.common.register.ore.OreItem;
import com.til.dusk.util.Extension;
import com.til.dusk.util.pack.BlockPack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public abstract class ShapedType extends RegisterBasics<ShapedType> {

    public static Supplier<IForgeRegistry<ShapedType>> SHAPED_TYPE;

    /***
     * 灵气提取
     */
    public static ShapedType extractMana;

    /***
     * 研磨
     */
    public static ShapedType grind;

    /***
     * 洗涤
     */
    public static ShapedType wash;

    /***
     * 离心
     */
    public static ShapedType centrifugal;

    /***
     * 打包
     */
    public static ShapedType pack;

    /***
     * 解包
     */
    public static ShapedType unpack;

    /***
     * 高炉
     */
    public static ShapedType blastFurnace;

    /***
     * 结晶
     */
    public static ShapedType crystallizing;

    /***
     * 组装
     */
    public static ShapedType assemble;

    /***
     * 蒸馏
     */
    public static ShapedType distillation;

    /***
     * 溶解
     */
    public static ShapedType dissolution;

    /***
     * 凝固
     */
    public static ShapedType freezing;

    /***
     * 高压融合
     */
    public static ShapedType highPressureFuse;

    /***
     * 雕刻
     */
    public static ShapedType carving;

    /***
     * 筛选
     */
    public static ShapedType screen;

    /***
     * 熔炉
     */
    public static ShapedType furnace;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        SHAPED_TYPE = event.create(new RegistryBuilder<ShapedType>().setName(new ResourceLocation(Dusk.MOD_ID, "shaped_type")));
        extractMana = new ShapedType("extract_mana", () -> ManaLevelBlock.grind) {
            @Override
            public void registerSubsidiaryBlack() {

            }
        };
        grind = new ShapedType("grind", () -> ManaLevelBlock.grind) {
            @Override
            public void registerSubsidiaryBlack() {
                for (Ore ore : Ore.screen(Ore.HAS_MINERAL_BLOCK, Ore.HAS_CRUSHED)) {
                    List<Extension.Data_2<ItemStack, Double>> grindOut = ore.grind();
                    for (Map.Entry<OreBlock, BlockPack> entry : ore.blockMap.entrySet()) {
                        if (!entry.getKey().hasTag(OreBlock.IS_MINERAL)) {
                            continue;
                        }
                        List<Extension.Data_2<ItemStack, Double>> outItem = new ArrayList<>();
                        outItem.add(new Extension.Data_2<>(new ItemStack(ore.itemMap.get(OreItem.crushed).item(), 2), 1d));
                        if (grindOut != null) {
                            outItem.addAll(grindOut);
                        }
                        new Shaped.ShapedOre.RandOutOreShaped(
                                fuseName(this, ore, entry.getKey()),
                                this,
                                ShapedDrive.get(0),
                                ore.manaLevel,
                                Map.of(ore.blockMap.get(entry.getKey()).blockItemTag(), 1),
                                null,
                                (long) (ore.strength * 640L),
                                (long) (ore.consume * 16L),
                                0,
                                outItem,
                                null);
                    }
                }

                for (Ore ore : Ore.screen(Ore.IS_METAL, Ore.HAS_DUST)) {
                    new Shaped.ShapedOre(
                            fuseName(this, ore, OreItem.dust),
                            this,
                            ShapedDrive.get(1),
                            ore.manaLevel,
                            Map.of(ItemTags.create(fuseName(ore, OreItem.ingot)), 1),
                            null,
                            (long) (ore.strength * 320L),
                            (long) (ore.consume * 8L),
                            0,
                            List.of(new ItemStack(ore.itemMap.get(OreItem.dust).item(), 1)),
                            null
                    );
                }

                for (Ore ore : Ore.screen(Ore.IS_CRYSTA, Ore.HAS_DUST)) {
                    new Shaped.ShapedOre(
                            fuseName("__", this, ore, OreItem.dust),
                            this,
                            ShapedDrive.get(2),
                            ore.manaLevel,
                            Map.of(ItemTags.create(fuseName(ore, OreItem.crystal)), 1),
                            null,
                            (long) (ore.strength * 320L),
                            (long) (ore.consume * 12L),
                            0,
                            List.of(new ItemStack(ore.itemMap.get(OreItem.dust).item(), 1)),
                            null
                    );
                    new Shaped.ShapedOre(
                            fuseName("___", this, ore, OreItem.dust),
                            this,
                            ShapedDrive.get(3),
                            ore.manaLevel,
                            Map.of(ItemTags.create(fuseName(ore, OreItem.damagedCrystal)), 1),
                            null,
                            (long) (ore.strength * 640L),
                            (long) (ore.consume * 18L),
                            0,
                            List.of(new ItemStack(ore.itemMap.get(OreItem.dust).item(), 1)),
                            null
                    );
                    new Shaped.ShapedOre(
                            fuseName("____", this, ore, OreItem.dust),
                            this,
                            ShapedDrive.get(4),
                            ore.manaLevel,
                            Map.of(ItemTags.create(fuseName(ore, OreItem.delicateCrystal)), 1),
                            null,
                            (long) (ore.strength * 640L),
                            (long) (ore.consume * 24L),
                            0,
                            List.of(new ItemStack(ore.itemMap.get(OreItem.dust).item(), 2)),
                            null
                    );
                    new Shaped.ShapedOre(
                            fuseName("_____", this, ore, OreItem.dust),
                            this,
                            ShapedDrive.get(5),
                            ore.manaLevel,
                            Map.of(ItemTags.create(fuseName(ore, OreItem.perfectCrystal)), 1),
                            null,
                            (long) (ore.strength * 640L),
                            (long) (ore.consume * 24L),
                            0,
                            List.of(new ItemStack(ore.itemMap.get(OreItem.dust).item(), 3)),
                            null
                    );
                }
            }
        };
        wash = new ShapedType("wash", () -> ManaLevelBlock.wash) {
            @Override
            public void registerSubsidiaryBlack() {
                for (Ore ore : Ore.screen(Ore.HAS_CRUSHED)) {
                    List<Extension.Data_2<ItemStack, Double>> grindOut = ore.wash();
                    List<Extension.Data_2<ItemStack, Double>> out = new ArrayList<>();
                    out.add(new Extension.Data_2<>(
                            new ItemStack(ore.itemMap.get(OreItem.crushedPurified).item(), 1),
                            1d
                    ));
                    if (grindOut != null) {
                        out.addAll(grindOut);
                    }
                    new Shaped.RandOutOreShaped(
                            fuseName(this, ore, OreItem.crushedPurified),
                            this,
                            ShapedDrive.get(0),
                            ore.manaLevel,
                            Map.of(ItemTags.create(fuseName(ore, OreItem.crushed)), 1),
                            Map.of(FluidTags.WATER, 1000),
                            (long) (ore.strength * 1280L),
                            (long) (ore.consume * 12L),
                            0,
                            out,
                            null);
                }
            }


        };
        centrifugal = new ShapedType("centrifugal", () -> ManaLevelBlock.centrifugal) {
            @Override
            public void registerSubsidiaryBlack() {
                for (Ore ore : Ore.screen(Ore.HAS_CRUSHED, Ore.HAS_DUST)) {
                    List<Extension.Data_2<ItemStack, Double>> grindOut = ore.wash();
                    List<Extension.Data_2<ItemStack, Double>> out = new ArrayList<>();
                    out.add(new Extension.Data_2<>(
                            new ItemStack(ore.itemMap.get(OreItem.dust).item(), 1),
                            1d
                    ));
                    if (grindOut != null) {
                        out.addAll(grindOut);
                    }
                    new Shaped.RandOutOreShaped(
                            fuseName(this, ore, OreItem.dust),
                            this,
                            ShapedDrive.get(0),
                            ore.manaLevel,
                            Map.of(ItemTags.create(fuseName(ore, OreItem.crushedPurified)), 1),
                            null,
                            (long) (ore.strength * 1280L),
                            (long) (ore.consume * 48L),
                            0,
                            out,
                            null);
                }
            }
        };
        pack = new ShapedType("pack", () -> ManaLevelBlock.pack) {
            @Override
            public void registerSubsidiaryBlack() {
                for (Ore ore : Ore.screen(Ore.HAS_BLOCK, Ore.IS_METAL)) {
                    new Shaped.ShapedOre(
                            fuseName(this, ore, OreBlock.block),
                            this,
                            ShapedDrive.get(0),
                            ore.manaLevel,
                            Map.of(ItemTags.create(fuseName(ore, OreItem.ingot)), 9),
                            null,
                            (long) (ore.strength * 128L),
                            (long) (ore.consume * 4L),
                            0,
                            List.of(new ItemStack(ore.blockMap.get(OreBlock.block).blockItem())),
                            null);
                }

                for (Ore ore : Ore.screen(Ore.HAS_BLOCK, Ore.IS_CRYSTA)) {
                    new Shaped.ShapedOre(
                            fuseName("__", this, ore, OreBlock.block),
                            this,
                            ShapedDrive.get(1),
                            ore.manaLevel,
                            Map.of(ItemTags.create(fuseName(ore, OreItem.crystal)), 9),
                            null,
                            (long) (ore.strength * 128L),
                            (long) (ore.consume * 4L),
                            0,
                            List.of(new ItemStack(ore.blockMap.get(OreBlock.block).blockItem())),
                            null);
                }

                for (Ore ore : Ore.screen(Ore.IS_METAL)) {
                    new Shaped.ShapedOre(
                            fuseName(this, ore, OreItem.ingot),
                            this,
                            ShapedDrive.get(2),
                            ore.manaLevel,
                            Map.of(ItemTags.create(fuseName(ore, OreItem.nuggets)), 9),
                            null,
                            (long) (ore.strength * 128L),
                            (long) (ore.consume * 4L),
                            0,
                            List.of(new ItemStack(ore.itemMap.get(OreItem.ingot).item())),
                            null);
                }

                for (Ore ore : Ore.screen(Ore.HAS_DUST)) {
                    new Shaped.ShapedOre(
                            fuseName(this, ore, OreItem.dust),
                            this,
                            ShapedDrive.get(3),
                            ore.manaLevel,
                            Map.of(ItemTags.create(fuseName(ore, OreItem.dustTiny)), 9),
                            null,
                            (long) (ore.strength * 128L),
                            (long) (ore.consume * 4L),
                            0,
                            List.of(new ItemStack(ore.itemMap.get(OreItem.dust).item())),
                            null);
                }
            }

        };
        unpack = new ShapedType("unpack", () -> ManaLevelBlock.unpack) {
            @Override
            public void registerSubsidiaryBlack() {
                for (Ore ore : Ore.screen(Ore.HAS_BLOCK, Ore.IS_METAL)) {
                    new Shaped.ShapedOre(
                            fuseName(this, ore, OreItem.ingot),
                            this,
                            ShapedDrive.get(0),
                            ore.manaLevel,
                            Map.of(ItemTags.create(fuseName(ore, OreBlock.block)), 1),
                            null,
                            (long) (ore.strength * 128L),
                            (long) (ore.consume * 4L),
                            0,
                            List.of(new ItemStack(ore.itemMap.get(OreItem.ingot).item(), 9)),
                            null);
                }

                for (Ore ore : Ore.screen(Ore.HAS_BLOCK, Ore.IS_CRYSTA)) {
                    new Shaped.ShapedOre(
                            fuseName("__", this, ore, OreItem.ingot),
                            this,
                            ShapedDrive.get(1),
                            ore.manaLevel,
                            Map.of(ItemTags.create(fuseName(ore, OreBlock.block)), 1),
                            null,
                            (long) (ore.strength * 128L),
                            (long) (ore.consume * 4L),
                            0,
                            List.of(new ItemStack(ore.itemMap.get(OreItem.crystal).item(), 9)),
                            null);
                }

                for (Ore ore : Ore.screen(Ore.IS_METAL)) {
                    new Shaped.ShapedOre(
                            fuseName(this, ore, OreItem.nuggets),
                            this,
                            ShapedDrive.get(2),
                            ore.manaLevel,
                            Map.of(ItemTags.create(fuseName(ore, OreItem.ingot)), 1),
                            null,
                            (long) (ore.strength * 128L),
                            (long) (ore.consume * 4L),
                            0,
                            List.of(new ItemStack(ore.itemMap.get(OreItem.nuggets).item(), 9)),
                            null);
                }

                for (Ore ore : Ore.screen(Ore.HAS_DUST)) {
                    new Shaped.ShapedOre(
                            fuseName(this, ore, OreItem.dustTiny),
                            this,
                            ShapedDrive.get(3),
                            ore.manaLevel,
                            Map.of(ItemTags.create(fuseName(ore, OreItem.dust)), 1),
                            null,
                            (long) (ore.strength * 128L),
                            (long) (ore.consume * 4L),
                            0,
                            List.of(new ItemStack(ore.itemMap.get(OreItem.dustTiny).item(), 9)),
                            null);
                }


            }
        };
        blastFurnace = new ShapedType("blast_furnace", () -> ManaLevelBlock.blastFurnace) {
            @Override
            public void registerSubsidiaryBlack() {
                for (Ore ore : Ore.screen(Ore.IS_METAL, Ore.HAS_DUST)) {
                    new Shaped.ShapedOre(
                            fuseName(this, ore, OreItem.ingot),
                            this,
                            ShapedDrive.get(0),
                            ore.manaLevel,
                            Map.of(ItemTags.create(fuseName(ore, OreItem.dust)), 1),
                            null,
                            (long) (ore.strength * 1024L),
                            (long) (ore.consume * 32L),
                            0,
                            List.of(new ItemStack(ore.itemMap.get(OreItem.ingot).item(), 1)),
                            null
                    );
                }
            }
        };
        crystallizing = new ShapedType("crystallizing", () -> ManaLevelBlock.crystallizing) {

            @Override
            public void registerSubsidiaryBlack() {

                for (Ore ore : Ore.screen(Ore.CAN_PLANT, Ore.HAS_FLUID)) {
                    new Shaped.ShapedOre(
                            fuseName(this, ore, OreItem.crystal),
                            this,
                            ShapedDrive.get(0),
                            ore.manaLevel,
                            Map.of(ore.itemMap.get(OreItem.crystalSeed).itemTag(), 1),
                            Map.of(ore.fluidMap.get(OreFluid.solution).fluidTag(), 288),
                            (long) (ore.strength * 2048L),
                            (long) (ore.consume * 8L),
                            0,
                            List.of(new ItemStack(ore.itemMap.get(OreItem.crystal).item(), 1)),
                            null
                    );

                    new Shaped.ShapedOre(
                            fuseName(this, ore, OreItem.delicateCrystal),
                            this,
                            ShapedDrive.get(1),
                            ore.manaLevel,
                            Map.of(ore.itemMap.get(OreItem.crystal).itemTag(), 1),
                            Map.of(ore.fluidMap.get(OreFluid.solution).fluidTag(), 1152),
                            (long) (ore.strength * 4096L),
                            (long) (ore.consume * 8L),
                            0,
                            List.of(new ItemStack(ore.itemMap.get(OreItem.delicateCrystal).item(), 1)),
                            null
                    );

                    new Shaped.ShapedOre(
                            fuseName(this, ore, OreItem.perfectCrystal),
                            this,
                            ShapedDrive.get(2),
                            ore.manaLevel,
                            Map.of(ore.itemMap.get(OreItem.delicateCrystal).itemTag(), 1),
                            Map.of(ore.fluidMap.get(OreFluid.solution).fluidTag(), 4608),
                            (long) (ore.strength * 8192L),
                            (long) (ore.consume * 8L),
                            0,
                            List.of(new ItemStack(ore.itemMap.get(OreItem.perfectCrystal).item(), 1)),
                            null
                    );
                }
            }

        };
        assemble = new ShapedType("assemble", () -> ManaLevelBlock.assemble) {

            @Override
            public void registerSubsidiaryBlack() {
                List<ManaLevelBlock> needUp = new ArrayList<>();
                for (ManaLevelBlock manaLevelBlock : ManaLevelBlock.LEVEL_BLOCK.get()) {
                    if (manaLevelBlock.hasTag(ManaLevelBlock.NEED_FRAME_UP)) {
                        needUp.add(manaLevelBlock);
                    }
                }
                for (ManaLevel manaLevel : ManaLevel.LEVEL.get()) {
                    if (manaLevel.next == null) {
                        continue;
                    }
                    for (ManaLevelBlock manaLevelBlock : needUp) {
                        new Shaped.ShapedOre(
                                fuseName(this, manaLevel.next.get(), manaLevelBlock),
                                this,
                                ShapedDrive.get(0),
                                manaLevel,
                                Map.of(manaLevel.blockMap.get(manaLevelBlock).blockItemTag(), 1, manaLevel.next.get().blockMap.get(ManaLevelBlock.frameBasic).blockItemTag(), 1),
                                null,
                                manaLevel.level * 1024L,
                                manaLevel.level * 32L,
                                0,
                                List.of(new ItemStack(manaLevel.next.get().blockMap.get(manaLevelBlock).blockItem())),
                                null
                        );
                    }

                }
            }
        };
        distillation = new ShapedType("distillation", () -> ManaLevelBlock.distillation) {

            @Override
            public void registerSubsidiaryBlack() {

            }

        };
        dissolution = new ShapedType("dissolution", () -> ManaLevelBlock.dissolution) {
            @Override
            public void registerSubsidiaryBlack() {
                for (Ore ore : Ore.screen(Ore.HAS_FLUID, Ore.IS_METAL)) {
                    new Shaped.ShapedOre(
                            fuseName(this, ore, OreFluid.solution),
                            this,
                            ShapedDrive.get(0),
                            ore.manaLevel,
                            Map.of(ore.itemMap.get(OreItem.ingot).itemTag(), 1),
                            null,
                            (long) (ore.strength * 512),
                            (long) (ore.consume * 128L),
                            0,
                            null,
                            List.of(new FluidStack(ore.fluidMap.get(OreFluid.solution).source(), 144)));
                }
                for (Ore ore : Ore.screen(Ore.HAS_FLUID, Ore.IS_CRYSTA)) {
                    new Shaped.ShapedOre(
                            fuseName("__", this, ore, OreFluid.solution),
                            this,
                            ShapedDrive.get(1),
                            ore.manaLevel,
                            Map.of(ore.itemMap.get(OreItem.crystal).itemTag(), 1),
                            null,
                            (long) (ore.strength * 512),
                            (long) (ore.consume * 128L),
                            0,
                            null,
                            List.of(new FluidStack(ore.fluidMap.get(OreFluid.solution).source(), 144)));
                }
                for (Ore ore : Ore.screen(Ore.HAS_FLUID, Ore.HAS_DUST)) {
                    new Shaped.ShapedOre(
                            fuseName("___", this, ore, OreFluid.solution),
                            this,
                            ShapedDrive.get(2),
                            ore.manaLevel,
                            Map.of(ore.itemMap.get(OreItem.dust).itemTag(), 1),
                            null,
                            (long) (ore.strength * 512),
                            (long) (ore.consume * 128L),
                            0,
                            null,
                            List.of(new FluidStack(ore.fluidMap.get(OreFluid.solution).source(), 144)));
                }
            }
        };
        freezing = new ShapedType("freezing", () -> ManaLevelBlock.freezing) {
            @Override
            public void registerSubsidiaryBlack() {

            }
        };
        highPressureFuse = new ShapedType("high_pressure_fuse", () -> ManaLevelBlock.highPressureFuse) {
            @Override
            public void registerSubsidiaryBlack() {

            }
        };
        carving = new ShapedType("carving", () -> ManaLevelBlock.carving) {
            @Override
            public void registerSubsidiaryBlack() {

            }

        };
        screen = new ShapedType("screen", () -> ManaLevelBlock.screen) {
            @Override
            public void registerSubsidiaryBlack() {
                for (Ore ore : Ore.screen(Ore.IS_CRYSTA, Ore.HAS_CRUSHED)) {
                    List<Extension.Data_2<ItemStack, Double>> out = new ArrayList<>();
                    out.add(new Extension.Data_2<>(new ItemStack(ore.itemMap.get(OreItem.damagedCrystal).item(), 1), 0.4));
                    out.add(new Extension.Data_2<>(new ItemStack(ore.itemMap.get(OreItem.crystal).item(), 1), 0.2));
                    out.add(new Extension.Data_2<>(new ItemStack(ore.itemMap.get(OreItem.delicateCrystal).item(), 1), 0.01));
                    out.add(new Extension.Data_2<>(new ItemStack(ore.itemMap.get(OreItem.perfectCrystal).item(), 1), 0.005));
                    out.add(new Extension.Data_2<>(new ItemStack(ore.itemMap.get(OreItem.crystalSeed).item(), 1), 0.3));
                    if (ore.hasTag(Ore.HAS_DUST)) {
                        out.add(new Extension.Data_2<>(new ItemStack(ore.itemMap.get(OreItem.dustTiny).item(), 1), 0.6));
                        out.add(new Extension.Data_2<>(new ItemStack(ore.itemMap.get(OreItem.dust).item(), 1), 0.2));
                    }
                    new Shaped.RandOutOreShaped(
                            fuseName(this, ore, OreItem.crystal),
                            this,
                            ShapedDrive.get(0),
                            ore.manaLevel,
                            Map.of(ore.itemMap.get(OreItem.crushedPurified).itemTag(), 1),
                            null,
                            (long) (ore.strength * 512),
                            (long) (ore.consume * 16L),
                            0,
                            out,
                            null);
                }
            }
        };
        furnace = new ShapedType("furnace", () -> ManaLevelBlock.furnace) {

            final RecipeManager.CachedCheck<Container, SmeltingRecipe> quickCheck = RecipeManager.createCheck(RecipeType.SMELTING);

            @Override
            public void registerSubsidiaryBlack() {
                Shaped shaped = new Shaped(name, this, ShapedDrive.get(0), ManaLevel.t1) {
                    @Override
                    public ShapedHandle get(IHandle iControl, Map<BlockEntity, IItemHandler> items, Map<BlockEntity, IFluidHandler> fluids) {
                        return null;
                    }

                    @Override
                    public IJEIShaped getJEIShaped() {
                        return null;
                    }

                    @Override
                    public List<Component> getComponent() {
                        return null;
                    }
                };
                shaped.isJEIShow = false;
            }
        };
    }

    public final Supplier<ManaLevelBlock> manaLevelBlockSupplier;

    public ShapedType(ResourceLocation name, Supplier<ManaLevelBlock> manaLevelBlockSupplier) {
        super(name, SHAPED_TYPE);
        this.manaLevelBlockSupplier = manaLevelBlockSupplier;
    }

    public ShapedType(String name, Supplier<ManaLevelBlock> manaLevelBlockSupplier) {
        this(new ResourceLocation(Dusk.MOD_ID, name), manaLevelBlockSupplier);
    }

    @Override
    public EventPriority getRegisterBlackPriority() {
        return EventPriority.LOWEST;
    }

    @Override
    public abstract void registerSubsidiaryBlack();
}
