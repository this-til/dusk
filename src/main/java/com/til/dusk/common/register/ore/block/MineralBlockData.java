package com.til.dusk.common.register.ore.block;

import com.til.dusk.common.register.ore.ore.Ore;
import com.til.dusk.util.pack.DataPack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author til
 */
@Deprecated
public class MineralBlockData {
    public final Ore ore;

    /***
     * 洗矿副产物
     */
    @Nullable
    public DataPack.OreDataPack washByproduct;

    /***
     * 离心副产物
     */
    @Nullable
    public DataPack.OreDataPack centrifugeByproduct;

    /***
     * 筛选副产物
     */
    @Nullable
    public DataPack.OreDataPack screenByproduct;

    public MineralBlockData(Ore ore) {
        this.ore = ore;
    }

    @Nullable
    public List<GenerateData> generateDataList;

    public MineralBlockData addOreGenerateData(GenerateData generateData) {
        if (generateDataList == null) {
            generateDataList = new ArrayList<>();
        }
        generateDataList.add(generateData.setOre(ore).setId(generateData.amount));
        return this;
    }

    /***
     * 添加通用生成数据
     */
    public MineralBlockData addCurrencyGenerateData(int amount, int inChunkAmount) {
        return addOreGenerateData(new GenerateData()
                .setAmount(amount)
                .setInChunkAmount(inChunkAmount)
                .setPlace((blockPos, level, blockState) -> {
                    Block inBlock = blockState.getBlock();
                    if (inBlock.equals(Blocks.STONE)) {
                        return ore.get(OreBlock.lordWorld).block().defaultBlockState();
                    }
                    if (inBlock.equals(Blocks.DEEPSLATE)) {
                        return ore.get(OreBlock.lordWorldDeepslate).block().defaultBlockState();
                    }
                    if (inBlock.equals(Blocks.DIRT)) {
                        return ore.get(OreBlock.lordWorldDirt).block().defaultBlockState();
                    }
                    if (inBlock.equals(Blocks.GRAVEL)) {
                        return ore.get(OreBlock.lordWorldGravel).block().defaultBlockState();
                    }
                    if (inBlock.equals(Blocks.NETHERITE_BLOCK)) {
                        return ore.get(OreBlock.netherWorldNetherrack).block().defaultBlockState();
                    }
                    if (inBlock.equals(Blocks.END_STONE)) {
                        return ore.get(OreBlock.endWorldEndStone).block().defaultBlockState();
                    }
                    return null;

                }));
    }

    @Nullable
    public GenerateData getOreGenerateDataByID(int id) {
        if (generateDataList == null) {
            return null;
        }
        for (GenerateData generateData : generateDataList) {
            if (generateData.id == id) {
                return generateData;
            }
        }
        return null;
    }

    public MineralBlockData setWashByproduct(DataPack.OreDataPack washByproduct) {
        this.washByproduct = washByproduct;
        return this;
    }

    public MineralBlockData setCentrifugeByproduct(DataPack.OreDataPack centrifugeByproduct) {
        this.centrifugeByproduct = centrifugeByproduct;
        return this;
    }

    public MineralBlockData setScreenByproduct(DataPack.OreDataPack screenByproduct) {
        this.screenByproduct = screenByproduct;
        return this;
    }
}
