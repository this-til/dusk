package com.til.dusk.common.register;

import com.til.dusk.Dusk;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;

import java.awt.*;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public abstract class KeyRegister extends RegisterBasics<KeyRegister> {

    public static Supplier<IForgeRegistry<KeyRegister>> KEY_REGISTER;

    public static KeyRegister switchBindType;

    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        KEY_REGISTER = event.create(new RegistryBuilder<KeyRegister>().setName(new ResourceLocation(Dusk.MOD_ID, "key_register")));
        switchBindType = new KeyRegister("switch_bind_type") {
            @Override
            public void run(Supplier<NetworkEvent.Context> supplier) {
                ServerPlayer serverPlayer = supplier.get().getSender();
                if (serverPlayer == null) {
                    return;
                }
                supplier.get().enqueueWork(() -> {

                });
                ItemStack itemStack = serverPlayer.getMainHandItem();

                /*EntityPlayerMP entityPlayerMP = messageContext.getServerHandler().player;
                ItemStack itemStack = entityPlayerMP.getHeldItem(EnumHand.MAIN_HAND);
                if (itemStack.getItem().equals(AllItem.bindStaff)) {
                    if (entityPlayerMP.isSneaking()) {
                        AllNBT.playerMessage.upDataToPlayerCLIENT(new PlayerMessage.MessageData(true, "已经清除坐标数据.name"), entityPlayerMP);
                        NBTTagCompound nbtTagCompound = new NBTTagCompound();
                        nbtTagCompound.setInteger("color", new Color(entityPlayerMP.getRNG().nextInt(255), entityPlayerMP.getRNG().nextInt(255), entityPlayerMP.getRNG().nextInt(255)).getRGB());
                        itemStack.setTagCompound(nbtTagCompound);
                    } else {
                        NBTTagCompound nbtTagCompound = itemStack.getTagCompound();
                        if (nbtTagCompound == null) {
                            nbtTagCompound = new NBTTagCompound();
                            itemStack.setTagCompound(nbtTagCompound);
                        }

                        BlockPos blockPos = new BlockPos(nbtTagCompound.getInteger("x"), nbtTagCompound.getInteger("y"), nbtTagCompound.getInteger("z"));
                        TileEntity _tile = entityPlayerMP.world.getTileEntity(blockPos);
                        if (_tile != null) {
                            IControl iControl = _tile.getCapability(AllCapability.I_CONTROL, null);
                            if (iControl != null) {
                                BindType bindType = BindType.register.getValue(new ResourceLocation(nbtTagCompound.getString("type")));
                                com.til.abyss_mana_2.util.extension.List<BindType> list = iControl.getCanBindType();
                                bindType = list.get(list.getAngleMark(bindType) + 1);
                                nbtTagCompound.setString("type", Objects.requireNonNull(bindType.getRegistryName()).toString());
                                AllNBT.playerMessage.upDataToPlayerCLIENT(
                                        new PlayerMessage.MessageData(true, "已经绑定类型切换至——{0}.name", bindType.getRegistryName().toString()), entityPlayerMP);
                                return;
                            }
                        }
                        AllNBT.playerMessage.upDataToPlayerCLIENT(new PlayerMessage.MessageData(true, "请先绑定主绑定方块.name"), entityPlayerMP);
                    }
                }*/
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
