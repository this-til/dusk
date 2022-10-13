package com.til.dusk.common.register.mana_level.item.group;

import com.til.dusk.Dusk;
import com.til.dusk.client.ColorProxy;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.RegisterManage;
import com.til.dusk.common.register.TagPackSupplierRegister;
import com.til.dusk.common.register.mana_level.item.ManaLevelItem;
import com.til.dusk.common.register.mana_level.item.group.groups.*;
import com.til.dusk.common.register.mana_level.item.items.HostManaLevelItem;
import com.til.dusk.common.register.mana_level.item.items.IntegrateManaLevelItem;
import com.til.dusk.common.register.mana_level.item.items.ProcessorManaLevelItem;
import com.til.dusk.common.register.mana_level.mana_level.ManaLevel;
import com.til.dusk.common.world.item.DuskItem;
import com.til.dusk.util.Lang;
import com.til.dusk.util.ResourceLocationUtil;
import com.til.dusk.util.pack.ItemPack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;


/***
 * 一个物品群，拥有多个子物品
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public abstract class ManaLevelItemGroup extends TagPackSupplierRegister<ManaLevelItemGroup> {


    public static final ResourceLocation INTEGRATE = new ResourceLocation(Dusk.MOD_ID, "integrate");
    public static final ResourceLocation PROCESSOR = new ResourceLocation(Dusk.MOD_ID, "processor");
    public static final ResourceLocation HOST = new ResourceLocation(Dusk.MOD_ID, "host");

    public static Supplier<IForgeRegistry<ManaLevelItemGroup>> MANA_LEVEL_ITEM_PACK;

    /***
     * 基础
     */
    public static OperationBasicsManaLevelItemGroup operationBasics;

    /***
     * 运算
     */
    public static OperationManaLevelItemGroup operation;

    /***
     * 成型核心
     */
    public static FormingManaLevelItemGroup forming;

    /***
     * 破坏核心
     */
    public static DestructionManaLevelItemGroup destruction;

    /***
     * 聚集核心
     */
    public static GatherManaLevelItemGroup gather;

    /***
     * 扩散核心
     */
    public static SpreadManaLevelItemGroup spread;

    /***
     * 动力核心
     */
    public static PowerManaLevelItemGroup power;

    /***
     * 指令核心
     */
    public static InstructionsManaLevelItemGroup instructions;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        MANA_LEVEL_ITEM_PACK = RegisterManage.create(ManaLevelItemGroup.class, new ResourceLocation(Dusk.MOD_ID, "mana_level_item_pack"), event);
        operationBasics = new OperationBasicsManaLevelItemGroup();
        operation = new OperationManaLevelItemGroup();
        forming = new FormingManaLevelItemGroup();
        destruction = new DestructionManaLevelItemGroup();
        gather = new GatherManaLevelItemGroup();
        spread = new SpreadManaLevelItemGroup();
        power = new PowerManaLevelItemGroup();
        instructions = new InstructionsManaLevelItemGroup();
    }

    public ManaLevelItemGroup(ResourceLocation name) {
        super(name, MANA_LEVEL_ITEM_PACK);
    }

    public ManaLevelItemGroup(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }


}
