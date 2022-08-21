package com.til.dusk.common.register.shaped;

import com.til.dusk.Dusk;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.common.register.ore.Ore;
import com.til.dusk.common.register.ore.OreBlock;
import com.til.dusk.common.register.ore.OreItem;
import com.til.dusk.util.Extension;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
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

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        SHAPED_TYPE = event.create(new RegistryBuilder<ShapedType>().setName(new ResourceLocation(Dusk.MOD_ID, "shaped_type")));
        grind = new ShapedType("grind") {
            @Override
            public void registerSubsidiaryBlack() {
                Ore.ORE.get().forEach(o -> {
                    List<Extension.Data_2<ItemStack, Double>> grindOut = o.grind();
                    o.blockMap.forEach((k, v) -> {
                        List<Extension.Data_2<ItemStack, Double>> outItem = new ArrayList<>();
                        outItem.add(new Extension.Data_2<>(new ItemStack(o.itemMap.get(OreItem.crushed), 2), 1d));
                        if (grindOut != null) {
                            outItem.addAll(grindOut);
                        }
                        if (k instanceof OreBlock.OreBlockMineral) {
                            new Shaped.ShapedOre.RandOutOreShaped(
                                    fuseName(this, o, k),
                                    this,
                                    ShapedDrive.get(0),
                                    o.manaLevel,
                                    Map.of(ItemTags.create(fuseName(o, k)), 1),
                                    null,
                                    (long) o.strength * 128L,
                                    (long) o.consume * 16L,
                                    0,
                                    outItem,
                                    null);
                        }
                    });
                });

                Ore.ORE.get().forEach(o -> {
                    new Shaped.ShapedOre(
                            fuseName(this, o, OreItem.ingot),
                            this,
                            ShapedDrive.get(1),
                            o.manaLevel,
                            Map.of(ItemTags.create(fuseName(o, OreItem.ingot)), 1),
                            null,
                            (long) o.strength * 72L,
                            (long) o.consume * 8L,
                            0,
                            List.of(new ItemStack(o.itemMap.get(OreItem.dust), 1)),
                            null
                    );
                });
            }
        };

        wash = new ShapedType("wash") {
            @Override
            public void registerSubsidiaryBlack() {
                Ore.ORE.get().forEach(o -> {
                    List<Extension.Data_2<ItemStack, Double>> grindOut = o.wash();
                    List<Extension.Data_2<ItemStack, Double>> out = new ArrayList<>();
                    out.add(new Extension.Data_2<>(
                            new ItemStack(o.itemMap.get(OreItem.crushedPurified), 1),
                            1d
                    ));
                    if (grindOut != null) {
                        out.addAll(grindOut);
                    }
                    new Shaped.RandOutOreShaped(
                            fuseName(this, o, OreItem.crushedPurified),
                            this,
                            ShapedDrive.get(0),
                            o.manaLevel,
                            Map.of(ItemTags.create(fuseName(o, OreItem.crushed)), 1),
                            Map.of(FluidTags.WATER, 1000),
                            (long) o.strength * 72L,
                            (long) o.consume * 32L,
                            0,
                            out,
                            null);
                });
            }
        };

        centrifugal = new ShapedType("centrifugal") {
            @Override
            public void registerSubsidiaryBlack() {
                Ore.ORE.get().forEach(o -> {
                    List<Extension.Data_2<ItemStack, Double>> grindOut = o.wash();
                    List<Extension.Data_2<ItemStack, Double>> out = new ArrayList<>();
                    out.add(new Extension.Data_2<>(
                            new ItemStack(o.itemMap.get(OreItem.dust), 1),
                            1d
                    ));
                    if (grindOut != null) {
                        out.addAll(grindOut);
                    }
                    new Shaped.RandOutOreShaped(
                            fuseName(this, o, OreItem.dust),
                            this,
                            ShapedDrive.get(0),
                            o.manaLevel,
                            Map.of(ItemTags.create(fuseName(o, OreItem.crushedPurified)), 1),
                            null,
                            (long) o.strength * 128L,
                            (long) o.consume * 48L,
                            0,
                            out,
                            null);
                });
            }
        };

        pack = new ShapedType("pack") {
            @Override
            public void registerSubsidiaryBlack() {
                Ore.ORE.get().forEach(o -> {
                    new Shaped.ShapedOre(
                            fuseName(this, o, OreBlock.block),
                            this,
                            ShapedDrive.get(0),
                            o.manaLevel,
                            Map.of(ItemTags.create(fuseName(o, OreItem.ingot)), 9),
                            null,
                            (long) o.strength * 128L,
                            (long) o.consume * 4L,
                            0,
                            List.of(new ItemStack(o.blockMap.get(OreBlock.block))),
                            null);
                });

                Ore.ORE.get().forEach(o -> {
                    new Shaped.ShapedOre(
                            fuseName(this, o, OreItem.dust),
                            this,
                            ShapedDrive.get(2),
                            o.manaLevel,
                            Map.of(ItemTags.create(fuseName(o, OreItem.dustTiny)), 9),
                            null,
                            (long) o.strength * 128L,
                            (long) o.consume * 48L,
                            0,
                            List.of(new ItemStack(o.itemMap.get(OreItem.dust))),
                            null);
                });
            }
        };

        unpack = new ShapedType("unpack") {
            @Override
            public void registerSubsidiaryBlack() {
                Ore.ORE.get().forEach(o -> {
                    new Shaped.ShapedOre(
                            fuseName(this, o, OreItem.ingot),
                            this,
                            ShapedDrive.get(0),
                            o.manaLevel,
                            Map.of(ItemTags.create(fuseName(o, OreBlock.block)), 1),
                            null,
                            (long) o.strength * 128L,
                            (long) o.consume * 4L,
                            0,
                            List.of(new ItemStack(o.itemMap.get(OreItem.ingot), 9)),
                            null);
                });

                Ore.ORE.get().forEach(o -> {
                    new Shaped.ShapedOre(
                            fuseName(this, o, OreItem.dustTiny),
                            this,
                            ShapedDrive.get(2),
                            o.manaLevel,
                            Map.of(ItemTags.create(fuseName(o, OreItem.dust)), 1),
                            null,
                            (long) o.strength * 128L,
                            (long) o.consume * 48L,
                            0,
                            List.of(new ItemStack(o.itemMap.get(OreItem.dustTiny), 9)),
                            null);
                });
            }
        };


    }

    public ShapedType(ResourceLocation name) {
        super(name, SHAPED_TYPE);
    }

    public ShapedType(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    @Override
    public EventPriority getRegisterBlackPriority() {
        return EventPriority.LOWEST;
    }

    @Override
    public abstract void registerSubsidiaryBlack();
}
