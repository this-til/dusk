package com.til.dusk.common.world.feature;

import com.til.dusk.common.config.util.IOrePlacedFeatureConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.BulkSectionAccess;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * @author til
 */
public class CurrencyOreFeature extends Feature<CurrencyOreFeatureConfiguration> {

    public static final int SIZE = 16;
    public static final float DISCARD_CHANCE_ON_AIR_EXPOSURE = 1;


    public CurrencyOreFeature() {
        super(CurrencyOreFeatureConfiguration.CODEC);

    }

    @Override
    public boolean place(@NotNull FeaturePlaceContext<CurrencyOreFeatureConfiguration> context) {
        RandomSource random = context.random();
        BlockPos pos = context.origin();
        WorldGenLevel world = context.level();
        CurrencyOreFeatureConfiguration config = context.config();
        float angle = random.nextFloat() * (float) Math.PI;
        float adjustedSize = SIZE / 8.0F;
        int i = Mth.ceil((adjustedSize + 1.0F) / 2.0F);
        double sin = Math.sin(angle) * adjustedSize;
        double cos = Math.cos(angle) * adjustedSize;
        double xMin = pos.getX() + sin;
        double xMax = pos.getX() - sin;
        double zMin = pos.getZ() + cos;
        double zMax = pos.getZ() - cos;
        double yMin = pos.getY() + random.nextInt(3) - 2;
        double yMax = pos.getY() + random.nextInt(3) - 2;
        int minXStart = pos.getX() - Mth.ceil(adjustedSize) - i;
        int minYStart = pos.getY() - 2 - i;
        int minZStart = pos.getZ() - Mth.ceil(adjustedSize) - i;
        int width = 2 * (Mth.ceil(adjustedSize) + i);
        int height = 2 * (2 + i);
        for (int x = minXStart; x <= minXStart + width; ++x) {
            for (int z = minZStart; z <= minZStart + width; ++z) {
                if (minYStart <= world.getHeight(Heightmap.Types.OCEAN_FLOOR_WG, x, z)) {
                    return doPlace(world, random, config, xMin, xMax, zMin, zMax, yMin, yMax, minXStart, minYStart, minZStart, width, height);
                }
            }
        }
        return false;
    }

    protected boolean doPlace(WorldGenLevel world, RandomSource random, CurrencyOreFeatureConfiguration config, double xMin, double xMax, double zMin, double zMax, double yMin,
                              double yMax, int minXStart, int minYStart, int minZStart, int width, int height) {
        Level level = world.getLevel();
        BitSet bitset = new BitSet(width * height * width);
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
        int size = SIZE;
        double[] doubles = new double[size * 4];
        for (int k = 0; k < size; ++k) {
            float f = k / (float) size;
            int k4 = k * 4;
            doubles[k4] = Mth.lerp(f, xMin, xMax);
            doubles[k4 + 1] = Mth.lerp(f, yMin, yMax);
            doubles[k4 + 2] = Mth.lerp(f, zMin, zMax);
            double d3 = random.nextDouble() * size / 16D;
            doubles[k4 + 3] = ((double) (Mth.sin((float) Math.PI * f) + 1) * d3 + 1) / 2D;
        }
        for (int i = 0; i < size - 1; ++i) {
            int i4 = i * 4;
            if (doubles[i4 + 3] > 0) {
                for (int j = i + 1; j < size; ++j) {
                    int j4 = j * 4;
                    if (doubles[j4 + 3] > 0) {
                        double d1 = doubles[i4] - doubles[j4];
                        double d2 = doubles[i4 + 1] - doubles[j4 + 1];
                        double d3 = doubles[i4 + 2] - doubles[j4 + 2];
                        double d4 = doubles[i4 + 3] - doubles[j4 + 3];
                        if (d4 * d4 > d1 * d1 + d2 * d2 + d3 * d3) {
                            if (d4 > 0) {
                                doubles[j4 + 3] = -1;
                            } else {
                                doubles[i4 + 3] = -1;
                            }
                        }
                    }
                }
            }
        }
        int i = 0;
        try (BulkSectionAccess bulkSectionAccess = new BulkSectionAccess(world)) {
            for (int j = 0; j < size; ++j) {
                int j4 = j * 4;
                double d1 = doubles[j4 + 3];
                if (d1 >= 0) {
                    double d2 = doubles[j4];
                    double d3 = doubles[j4 + 1];
                    double d4 = doubles[j4 + 2];
                    int xStart = Math.max(Mth.floor(d2 - d1), minXStart);
                    int yStart = Math.max(Mth.floor(d3 - d1), minYStart);
                    int zStart = Math.max(Mth.floor(d4 - d1), minZStart);
                    int xEnd = Math.max(Mth.floor(d2 + d1), xStart);
                    int yEnd = Math.max(Mth.floor(d3 + d1), yStart);
                    int zEnd = Math.max(Mth.floor(d4 + d1), zStart);
                    for (int x = xStart; x <= xEnd; ++x) {
                        double d5 = ((double) x + 0.5D - d2) / d1;
                        double d5_squared = d5 * d5;
                        if (d5_squared < 1) {
                            for (int y = yStart; y <= yEnd; ++y) {
                                double d6 = ((double) y + 0.5D - d3) / d1;
                                double d6_squared = d6 * d6;
                                if (d5_squared + d6_squared < 1) {
                                    for (int z = zStart; z <= zEnd; ++z) {
                                        double d7 = ((double) z + 0.5D - d4) / d1;
                                        if (d5_squared + d6_squared + d7 * d7 < 1.0D && !world.isOutsideBuildHeight(y)) {
                                            int l2 = x - minXStart + (y - minYStart) * width + (z - minZStart) * width * height;
                                            if (!bitset.get(l2)) {
                                                bitset.set(l2);
                                                mutablePos.set(x, y, z);
                                                if (world.ensureCanWrite(mutablePos)) {
                                                    LevelChunkSection section = bulkSectionAccess.getSection(mutablePos);
                                                    if (section != null) {
                                                        int sectionX = SectionPos.sectionRelative(x);
                                                        int sectionY = SectionPos.sectionRelative(y);
                                                        int sectionZ = SectionPos.sectionRelative(z);
                                                        BlockState state = section.getBlockState(sectionX, sectionY, sectionZ);
                                                        IOrePlacedFeatureConfig generateData = config.iOrePlacedFeatureConfig();
                                                        BlockPos blockPos = new BlockPos(sectionX, sectionY, sectionZ);
                                                        BlockState pBlock = generateData.placed(level, state, blockPos);
                                                        if (pBlock != null) {
                                                            section.setBlockState(sectionX, sectionY, sectionZ, pBlock, false);
                                                            ++i;
                                                            break;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return i > 0;
    }


}
