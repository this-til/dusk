package com.til.dusk.common.register.world.block;

import com.til.dusk.Dusk;
import com.til.dusk.common.data.tag.BlockTag;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.register.RegisterPack;
import com.til.dusk.common.register.RegisterManage;
import com.til.dusk.util.ResourceLocationUtil;
import com.til.dusk.util.pack.BlockPack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;

import java.util.function.Supplier;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public abstract class BlockPackRegister extends RegisterPack<BlockPackRegister, BlockPack> {

    public static Supplier<IForgeRegistry<BlockPackRegister>> BLOCK_PACK_REGISTER;


    @SubscribeEvent
    public static void onEvent(NewRegistryEvent event) {
        BLOCK_PACK_REGISTER = RegisterManage.create(BlockPackRegister.class, new ResourceLocation(Dusk.MOD_ID, "block_pack_register"), event);
    }


    public BlockPackRegister(ResourceLocation name) {
        super(name, BLOCK_PACK_REGISTER);
    }

    public BlockPackRegister(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    @Override
    protected BlockPack create() {
        Block block = createBlock();
        if (block == null) {
            return null;
        }
        return new BlockPack(block, createBlockTag(), createBlockItem(block), createBlockItemTag());
    }

    protected abstract Block createBlock();

    protected TagKey<Block> createBlockTag() {
        return tagPackSupplier.getTagPack().blockTagKey();
    }

    protected abstract BlockItem createBlockItem(Block block);

    protected TagKey<Item> createBlockItemTag() {
        return tagPackSupplier.getTagPack().itemTagKey();
    }

    @Override
    protected void register(BlockPack blockPack) {
        ForgeRegistries.BLOCKS.register(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{"block", name.getPath()}), blockPack.block());
        BlockTag.addTag(blockPack.blockTag(), blockPack.block());
        ForgeRegistries.ITEMS.register(ResourceLocationUtil.fuseName(name.getNamespace(), "/", new String[]{"block", name.getPath()}), blockPack.blockItem());
        ItemTag.addTag(blockPack.blockItemTag(), blockPack.blockItem());

    }
}
