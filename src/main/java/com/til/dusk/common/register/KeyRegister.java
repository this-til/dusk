package com.til.dusk.common.register;

import com.til.dusk.Dusk;
import com.til.dusk.common.capability.control.IControl;
import com.til.dusk.common.register.tag_tool.TagTool;
import com.til.dusk.common.world.ModItem;
import com.til.dusk.util.Lang;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;

import java.awt.*;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public abstract class KeyRegister extends RegisterBasics<KeyRegister> {

    public static Supplier<IForgeRegistry<KeyRegister>> KEY_REGISTER;

    public static KeyRegister switchBindType;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        KEY_REGISTER = event.create(new RegistryBuilder<KeyRegister>().setName(new ResourceLocation(Dusk.MOD_ID, "key_register")));
        switchBindType = new KeyRegister("switch_bind_type") {

            final Random random = new Random();

            @Override
            public void run(Supplier<NetworkEvent.Context> supplier) {
                ServerPlayer serverPlayer = supplier.get().getSender();
                if (serverPlayer == null) {
                    return;
                }
                supplier.get().enqueueWork(() -> {
                    ItemStack itemStack = serverPlayer.getMainHandItem();
                    if (itemStack.isEmpty()) {
                        return;
                    }
                    if (ModItem.bindStaff.equals(itemStack.getItem())) {
                        CompoundTag compoundTag = itemStack.getTag();
                        if (compoundTag == null) {
                            compoundTag = new CompoundTag();
                            itemStack.setTag(compoundTag);
                        }
                        if (serverPlayer.isShiftKeyDown()) {
                            serverPlayer.sendSystemMessage(Component.translatable(Lang.getKey("已经清除坐标数据")));
                            compoundTag = new CompoundTag();
                            itemStack.setTag(compoundTag);
                            TagTool.colorTag.set(compoundTag, new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)).getRGB());
                        } else {
                            BlockPos controlBlockPos = TagTool.blockPosTag.get(compoundTag);
                            BlockEntity blockEntity = serverPlayer.getLevel().getBlockEntity(controlBlockPos);
                            if (blockEntity == null) {
                                serverPlayer.sendSystemMessage(Component.translatable(Lang.getKey("绑定控制器丢失，请重新绑定")));
                                return;
                            }
                            LazyOptional<IControl> lazyOptional = blockEntity.getCapability(CapabilityRegister.iControl.capability);
                            if (!lazyOptional.isPresent()) {
                                serverPlayer.sendSystemMessage(Component.translatable(Lang.getKey("绑定控制器丢失，请重新绑定")));
                                return;
                            }
                            IControl iControl = lazyOptional.orElse(null);
                            List<BindType> bindTypeList = iControl.getCanBindType();
                            if (bindTypeList.isEmpty()) {
                                serverPlayer.sendSystemMessage(Component.translatable(Lang.getKey("绑定控制器不接受绑定类型")));
                                return;
                            }
                            BindType bindType = bindTypeList.get(0);
                            BindType nowBindType = TagTool.bindTypeTag.get(compoundTag);
                            boolean trigger = false;
                            for (BindType type : bindTypeList) {
                                if (type.equals(nowBindType)) {
                                    trigger = true;
                                    continue;
                                }
                                if (trigger) {
                                    bindType = type;
                                    break;
                                }
                            }
                            TagTool.bindTypeTag.set(compoundTag, bindType);
                            TagTool.colorTag.set(compoundTag, new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)).getRGB());
                            serverPlayer.sendSystemMessage(Component.translatable(Lang.getKey("已经绑定类型切换至[%s]"), Lang.getLang(bindType)));
                        }
                    }
                });

            }
        };
    }


    public KeyRegister(ResourceLocation name) {
        super(name, KEY_REGISTER);
    }

    public KeyRegister(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    public abstract void run(Supplier<NetworkEvent.Context> supplier);

    public static class KeyData {
        public String keyName = "";

        public KeyData() {
        }

        public KeyData(String keyName) {
            this.keyName = keyName;
        }

        public KeyData(ResourceLocation resourceLocation) {
            this(resourceLocation.toString());
        }

    }

}
