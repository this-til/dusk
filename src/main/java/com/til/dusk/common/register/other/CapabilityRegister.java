package com.til.dusk.common.register.other;

import com.til.dusk.Dusk;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.capability.ITooltipCapability;
import com.til.dusk.common.capability.black.IBack;
import com.til.dusk.common.capability.block_attribute.IBlockAttribute;
import com.til.dusk.common.capability.block_attribute.IBlockAttributeSupplier;
import com.til.dusk.common.capability.block_scan.IBlockScan;
import com.til.dusk.common.capability.clock.IClock;
import com.til.dusk.common.capability.control.EventControl;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.capability.handle.EventHandle;
import com.til.dusk.common.capability.handle.IHandle;
import com.til.dusk.common.capability.mana_handle.IManaHandle;
import com.til.dusk.common.capability.mana_level.IManaLevel;
import com.til.dusk.common.capability.multi_block.IMultiBlock;
import com.til.dusk.common.capability.pos.IPosTrack;
import com.til.dusk.common.capability.shaped_drive.IShapedDrive;
import com.til.dusk.common.capability.skill.ISkill;
import com.til.dusk.common.event.DelayTrigger;
import com.til.dusk.common.event.EventIO;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.common.register.RegisterManage;
import com.til.dusk.util.Pos;
import com.til.dusk.util.Util;
import com.til.dusk.util.tooltip_pack.ComponentPack;
import com.til.dusk.util.tooltip_pack.IComponentPack;
import net.minecraft.core.SectionPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;

import java.util.function.Consumer;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CapabilityRegister<C> extends RegisterBasics<CapabilityRegister<C>> {

    public static Supplier<IForgeRegistry<CapabilityRegister<?>>> CAPABILITY_REGISTER;
    public static CapabilityRegister<IItemHandler> iItemHandler;
    public static CapabilityRegister<IFluidHandler> iFluidHandler;
    public static CapabilityRegister<IEnergyStorage> iEnergyStorage;
    public static CapabilityRegister<IManaLevel> iManaLevel;

    public static CapabilityRegister<IClock> iClock;
    public static CapabilityRegister<IControl> iControl;
    public static CapabilityRegister<IManaHandle> iManaHandle;
    public static CapabilityRegister<IShapedDrive> iShapedDrive;
    public static CapabilityRegister<IHandle> iHandle;
    public static CapabilityRegister<IPosTrack> iPosTrack;
    public static CapabilityRegister<ISkill> iSkill;
    public static CapabilityRegister<IBack> iBlack;
    public static CapabilityRegister<IBlockScan> iBlockScan;
    public static CapabilityRegister<IMultiBlock> iMultiBlock;
    public static CapabilityRegister<IBlockAttribute> iBlockAttribute;
    public static CapabilityRegister<IBlockAttributeSupplier> iBlockAttributeSupplier;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        CAPABILITY_REGISTER = RegisterManage.create(Util.forcedConversion(CapabilityRegister.class), new ResourceLocation(Dusk.MOD_ID, "capability_register"), event);
        iItemHandler = new CapabilityRegister<>("i_item_handler", IItemHandler.class, ForgeCapabilities.ITEM_HANDLER).addLang(langTool -> {
            langTool.setCache(iItemHandler.name.toLanguageKey());
            langTool.add(LangType.ZH_CN, "????????????");
            langTool.add(LangType.EN_CH, "Item Handler");
        });
        iFluidHandler = new CapabilityRegister<>("i_fluid_handler", IFluidHandler.class, ForgeCapabilities.FLUID_HANDLER).addLang(langTool -> {
            langTool.setCache(iFluidHandler.name.toLanguageKey());
            langTool.add(LangType.ZH_CN, "????????????");
            langTool.add(LangType.EN_CH, "Fluid Handler");
        });
        iEnergyStorage = new CapabilityRegister<>("i_energy_storage", IEnergyStorage.class, ForgeCapabilities.ENERGY).addLang(langTool -> {
            langTool.setCache(iEnergyStorage.name.toLanguageKey());
            langTool.add(LangType.ZH_CN, "FE??????");
            langTool.add(LangType.EN_CH, "FE Handler");
        });
        iManaLevel = new CapabilityRegister<>("i_mana_level", IManaLevel.class, () -> new CapabilityToken<IManaLevel>() {
        }).addLang(langTool -> {
            langTool.setCache(iManaLevel.name.toLanguageKey());
            langTool.add(LangType.ZH_CN, "????????????");
            langTool.add(LangType.EN_CH, "Mana Level");
        });

        iClock = new CapabilityRegister<>("i_clock", IClock.class, () -> new CapabilityToken<IClock>() {
        }).addLang(langTool -> {
            langTool.setCache(iClock.name.toLanguageKey());
            langTool.add(LangType.ZH_CN, "????????????");
            langTool.add(LangType.EN_CH, "Clock");
        });
        iControl = new CapabilityRegister<>("i_control", IControl.class, () -> new CapabilityToken<IControl>() {
        }).addLang(langTool -> {
            langTool.setCache(iControl.name.toLanguageKey());
            langTool.add(LangType.ZH_CN, "?????????");
            langTool.add(LangType.EN_CH, "Control");
        });
        iManaHandle = new CapabilityRegister<>("i_mana_handle", IManaHandle.class, () -> new CapabilityToken<IManaHandle>() {
        }).addLang(langTool -> {
            langTool.setCache(iManaHandle.name.toLanguageKey());
            langTool.add(LangType.ZH_CN, "????????????");
            langTool.add(LangType.EN_CH, "Mana Handler");
        });
        iShapedDrive = new CapabilityRegister<>("i_shaped_drive", IShapedDrive.class, () -> new CapabilityToken<IShapedDrive>() {
        }).addLang(langTool -> {
            langTool.setCache(iShapedDrive.name.toLanguageKey());
            langTool.add(LangType.ZH_CN, "?????????");
            langTool.add(LangType.EN_CH, "Shaped Drive");
        });
        iHandle = new CapabilityRegister<>("i_handle", IHandle.class, () -> new CapabilityToken<IHandle>() {
        }).addLang(langTool -> {
            langTool.setCache(iHandle.name.toLanguageKey());
            langTool.add(LangType.ZH_CN, "?????????");
            langTool.add(LangType.EN_CH, "Handle");
        });
        iPosTrack = new CapabilityRegister<>("i_pos_track", IPosTrack.class, () -> new CapabilityToken<IPosTrack>() {
        });
        iSkill = new CapabilityRegister<>("i_skill", ISkill.class, () -> new CapabilityToken<ISkill>() {
        }).addLang(langTool -> {
            langTool.setCache(iSkill.name.toLanguageKey());
            langTool.add(LangType.ZH_CN, "??????");
            langTool.add(LangType.EN_CH, "Skill");
        });
        iBlack = new CapabilityRegister<>("i_black", IBack.class, () -> new CapabilityToken<IBack>() {
        });
        iBlockScan = new CapabilityRegister<>("i_block_scan", IBlockScan.class, () -> new CapabilityToken<IBlockScan>() {
        }).addLang(langTool -> {
            langTool.setCache(iBlockScan.name.toLanguageKey());
            langTool.add(LangType.ZH_CN, "????????????");
            langTool.add(LangType.EN_CH, "Block Scan");
        });

        iMultiBlock = new CapabilityRegister<>("i_multi_block", IMultiBlock.class, () -> new CapabilityToken<IMultiBlock>() {
        }).addLang(langTool -> {
            langTool.setCache(iMultiBlock.name.toLanguageKey());
            langTool.add(LangType.ZH_CN, "???????????????");
            langTool.add(LangType.EN_CH, "Multi Block");
        });
        iBlockAttribute = new CapabilityRegister<>("i_block_attribute", IBlockAttribute.class, () -> new CapabilityToken<IBlockAttribute>() {
        });
        iBlockAttributeSupplier = new CapabilityRegister<>("i_block_attribute_supplier", IBlockAttributeSupplier.class, () -> new CapabilityToken<IBlockAttributeSupplier>() {
        });

        MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, (Consumer<EventControl>) e -> {
            Level level = e.control.getPosTrack().getLevel();
            if (level != null) {
                Pos pos = e.control.getPosTrack().getPos();
                level.getChunk(SectionPos.blockToSectionCoord(pos.x), SectionPos.blockToSectionCoord(pos.z)).setUnsaved(true);
            }
        });
        MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, (Consumer<EventIO<?>>) e -> {
            if (e.level != null) {
                for (Pos po : e.getPos()) {
                    e.level.getChunk(SectionPos.blockToSectionCoord(po.x), SectionPos.blockToSectionCoord(po.z)).setUnsaved(true);
                }
            }
        });
        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGHEST, (Consumer<EventHandle>) e -> {
            Level level = e.iHandle.getPosTrack().getLevel();
            if (level != null) {
                Pos pos = e.iHandle.getPosTrack().getPos();
                level.getChunk(SectionPos.blockToSectionCoord(pos.x), SectionPos.blockToSectionCoord(pos.z)).setUnsaved(true);
            }
        });
        MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, (Consumer<LivingEvent.LivingTickEvent>) e -> {
            LazyOptional<IBack> black = e.getEntity().getCapability(CapabilityRegister.iBlack.capability);
            if (black.isPresent()) {
                IBack iBack = black.orElse(null);
                iBack.run(IBack.LIVING_TICK_EVENT, e);
                iBack.run(IBack.UP, null);
            }
        });
        MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, (Consumer<LivingEquipmentChangeEvent>) e -> {
            LazyOptional<IBack> black = e.getEntity().getCapability(CapabilityRegister.iBlack.capability);
            if (black.isPresent()) {
                black.orElse(null).run(IBack.LIVING_EQUIPMENT_CHANGE_EVENT, e);
            }
        });
        MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, (Consumer<ItemTooltipEvent>) e -> {
            ItemStack itemStack = e.getItemStack();
            if (itemStack.isEmpty()) {
                return;
            }
            CompoundTag compoundTag = new CompoundTag();
            packCapabilityTooltip(compoundTag, itemStack);
            ComponentPack tooltipPack = new ComponentPack(e.getToolTip());
            unPackCapabilityTooltip(compoundTag, itemStack, tooltipPack);
        });

    }

    public final Class<C> cClass;
    public final Capability<C> capability;
    public boolean needRegister = true;

    public CapabilityRegister(ResourceLocation name, Class<C> cClass, Supplier<CapabilityToken<C>> create) {
        super(name, Util.forcedConversion(CAPABILITY_REGISTER));
        this.cClass = cClass;
        Dusk.instance.modEventBus.addListener(this::registerCapabilities);
        capability = CapabilityManager.get(create.get());
    }

    public CapabilityRegister(String name, Class<C> cClass, Supplier<CapabilityToken<C>> create) {
        this(new ResourceLocation(Dusk.MOD_ID, name), cClass, create);
    }

    public CapabilityRegister(ResourceLocation name, Class<C> cClass, Capability<C> capability) {
        super(name, Util.forcedConversion(CAPABILITY_REGISTER));
        this.cClass = cClass;
        Dusk.instance.modEventBus.addListener(this::registerCapabilities);
        this.capability = capability;
        this.needRegister = false;
    }

    public CapabilityRegister(String name, Class<C> cClass, Capability<C> capability) {
        this(new ResourceLocation(Dusk.MOD_ID, name), cClass, capability);
    }

    public void registerCapabilities(RegisterCapabilitiesEvent event) {
        if (needRegister) {
            event.register(cClass);
        }
    }

    /***
     * ??????????????????
     * @param compoundTag ???????????????NBT
     * @param iCapabilityProvider ???????????????
     */
    public static void packCapabilityTooltip(CompoundTag compoundTag, ICapabilityProvider iCapabilityProvider) {
        for (CapabilityRegister<?> capabilityRegister : CAPABILITY_REGISTER.get()) {
            LazyOptional<?> lazyOptional = iCapabilityProvider.getCapability(capabilityRegister.capability);
            if (lazyOptional.isPresent()) {
                if (lazyOptional.orElse(null) instanceof ITooltipCapability iTooltipCapability) {
                    compoundTag.put(capabilityRegister.capability.getName(), iTooltipCapability.appendServerData());
                }
            }
        }
    }

    public static void unPackCapabilityTooltip(CompoundTag compoundTag, ICapabilityProvider iCapabilityProvider, IComponentPack tooltipPack) {
        for (CapabilityRegister<?> capabilityRegister : CAPABILITY_REGISTER.get()) {
            LazyOptional<?> lazyOptional = iCapabilityProvider.getCapability(capabilityRegister.capability);
            if (lazyOptional.isPresent()) {
                if (lazyOptional.orElse(null) instanceof ITooltipCapability iTooltipCapability) {
                    iTooltipCapability.appendTooltip(tooltipPack, compoundTag.getCompound(capabilityRegister.capability.getName()));
                    tooltipPack.resetIndent();
                }
            }
        }
    }

    @Override
    public void defaultConfig() {

    }
}
