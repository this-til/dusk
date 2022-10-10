package com.til.dusk.common.register.ore.block;

import com.til.dusk.Dusk;
import com.til.dusk.client.data.lang.LangProvider;
import com.til.dusk.client.data.lang.LangType;
import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.util.pack.BlockPack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

/**
 * @author til
 */
public abstract class OreBlockMineral extends OreBlock {

    public static final ResourceLocation MINERAL_NAME =new ResourceLocation(Dusk.MOD_ID, "mineral");

    /***
     * 作为矿物生成时替换基础方块
     */
    @Nullable
    public Supplier<Block> replaceBasicsBlock;

    public OreBlockMineral(ResourceLocation name) {
        super(name);
    }

    public OreBlockMineral(String name) {
        this(new ResourceLocation(Dusk.MOD_ID, name));
    }

    @Override
    public @Nullable BlockPack create(Ore ore) {
        if (ore.mineralBlockData != null) {
            return super.create(ore);
        }
        return null;
    }

    public OreBlockMineral setReplaceBasicsBlock(Supplier<Block> replaceBasicsBlock) {
        this.replaceBasicsBlock = replaceBasicsBlock;
        return this;
    }

    @Override
    public void registerLang(LangProvider.LangTool lang) {
        lang.setCache(name.toLanguageKey());
        lang.add(LangType.ZH_CN,"矿");
        lang.add(LangType.EN_CH, "Ore");
    }
}
