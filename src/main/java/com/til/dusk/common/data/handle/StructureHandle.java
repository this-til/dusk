package com.til.dusk.common.data.handle;

import com.til.dusk.Dusk;
import com.til.dusk.common.register.ore.block.OreBlock;
import com.til.dusk.util.Extension;
import com.til.dusk.util.IOUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtIo;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.ForgeRegistries;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class StructureHandle {

    public static final String BLOCKS = "blocks";
    public static final String POS = "pos";
    public static final String STATE = "state";
    public static final String NAME = "Name";
    public static final String PROPERTIES = "Properties";

    public static final String CENTER_POINT = "center_point";

    public static final String PALETTE = "palette";

    public static final String NBT = ".nbt";

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onEvent(GatherDataEvent event) throws IOException {
        Path gamePath = FMLPaths.GAMEDIR.get();
        String centerPointName = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(Blocks.DIAMOND_BLOCK)).toString();
        String airName = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(Blocks.AIR)).toString();
        BlockPos centerPos = null;
        int centerId = -1;
        List<Map.Entry<OreBlock, String>> tagComparisonTable = new ArrayList<>();
        {
            tagComparisonTable.add(new Extension.VariableData_2<>(OreBlock.coil, "OreBlock.coil.name"));
            tagComparisonTable.add(new Extension.VariableData_2<>(OreBlock.slab, "OreBlock.slab.name"));
            tagComparisonTable.add(new Extension.VariableData_2<>(OreBlock.stairs, "OreBlock.stairs.name"));
            tagComparisonTable.add(new Extension.VariableData_2<>(OreBlock.wall, "OreBlock.wall.name"));
            tagComparisonTable.add(new Extension.VariableData_2<>(OreBlock.partialPressure, "OreBlock.partialPressure.name"));
            tagComparisonTable.add(new Extension.VariableData_2<>(OreBlock.pulse, "OreBlock.pulse.name"));
            tagComparisonTable.add(new Extension.VariableData_2<>(OreBlock.substitution, "OreBlock.substitution.name"));
            tagComparisonTable.add(new Extension.VariableData_2<>(OreBlock.shackles, "OreBlock.shackles.name"));
            tagComparisonTable.add(new Extension.VariableData_2<>(OreBlock.block, "OreBlock.block.name"));
        }
        File structureFile = gamePath.resolve("handle/structures").toFile();
        File[] allFile = structureFile.listFiles((file, name) -> name.endsWith(NBT));
        if (allFile == null) {
            return;
        }
        for (File file : allFile) {

            StringBuilder out = new StringBuilder();
            out.append("blockPosPackList = List.of(").append('\n').append("   ");

            CompoundTag compoundTag = NbtIo.readCompressed(file);
            ListTag blocks = compoundTag.getList(BLOCKS, Tag.TAG_COMPOUND);
            ListTag palette = compoundTag.getList(PALETTE, Tag.TAG_COMPOUND);

            Map<Integer, CompoundTag> paletteMap = new HashMap<>(palette.size());
            int paletteId = -1;
            for (Tag tag : palette) {
                paletteId++;
                if (!(tag instanceof CompoundTag compound)) {
                    continue;
                }
                if (airName.equals(compound.getString(NAME))) {
                    continue;
                }
                if (centerPointName.equals(compound.getString(NAME))) {
                    centerId = paletteMap.size();
                }
                paletteMap.put(paletteId, compound);

            }
            assert centerId != -1;

            Map<Integer, List<BlockPos>> originalIdPos = new HashMap<>(8);
            for (Tag block : blocks) {
                if (!(block instanceof CompoundTag compound)) {
                    continue;
                }
                int id = compound.getInt(STATE);
                ListTag posPack = compound.getList(POS, Tag.TAG_INT);
                BlockPos pos = new BlockPos(posPack.getInt(0), posPack.getInt(1), posPack.getInt(2));

                if (centerId == id) {
                    centerPos = pos;
                    continue;
                }
                if (!paletteMap.containsKey(id)) {
                    continue;
                }
                originalIdPos.computeIfAbsent(id, k -> new ArrayList<>()).add(pos);
            }
            assert centerPos != null;

            Map<Integer, List<BlockPos>> idPos = new HashMap<>(originalIdPos.size());
            for (Map.Entry<Integer, List<BlockPos>> entry : originalIdPos.entrySet()) {
                List<BlockPos> blockPosList = new ArrayList<>(entry.getValue().size());
                //op-oc=cp
                for (BlockPos blockPos : entry.getValue()) {
                    blockPosList.add(blockPos.offset(-centerPos.getX(), -centerPos.getY(), -centerPos.getZ()));
                }
                idPos.put(entry.getKey(), blockPosList);
            }

            boolean isFirst = true;
            for (Map.Entry<Integer, List<BlockPos>> entry : idPos.entrySet()) {
                CompoundTag compound = paletteMap.get(entry.getKey());
                ResourceLocation name = new ResourceLocation(compound.getString(NAME));
                Block block = ForgeRegistries.BLOCKS.getValue(name);
                if (block == null) {
                    continue;
                }
                if (!isFirst) {
                    out.append(',').append('\n').append("    ");
                }
                isFirst = false;
                StringBuilder state = new StringBuilder();
                if (compound.contains(PROPERTIES)) {
                    state.append("Map.of(");
                    boolean _isFirst = true;
                    CompoundTag stateCompoundTag = compound.getCompound(PROPERTIES);
                    for (String allKey : stateCompoundTag.getAllKeys()) {
                        if (!_isFirst) {
                            state.append(',');
                        }
                        _isFirst = false;
                        state.append('"').append(allKey).append('"')
                                .append(",")
                                .append('"').append(stateCompoundTag.getString(allKey)).append('"');
                    }
                    state.append(")");
                } else {
                    state.append("null");
                }
                String outTagName = "";
                for (Map.Entry<OreBlock, String> entry_ : tagComparisonTable) {
                    if (!name.getNamespace().equals(entry_.getKey().name.getNamespace())) {
                        continue;
                    }
                    if (!name.getPath().contains(entry_.getKey().name.getPath())) {
                        continue;
                    }
                    outTagName = entry_.getValue();
                    break;
                }
                if (!outTagName.isEmpty()) {
                    out.append(String.format("new IMultiBlockPack.ManaLevelAcceptableBlockPack(%s,%s)", outTagName, state));
                } else {
                    out.append(String.format("new IMultiBlockPack.BlockStateBlockPack<ManaLevel>(() -> BlockStateUtil.create(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(\"%s\")),%s))", name, !compound.contains(PROPERTIES) ? "(Map<String, String>) null" : state));
                }
                for (BlockPos blockPos : entry.getValue()) {
                    out.append('\n').append("    ").append("    ").append(String.format(".addPos(new BlockPos(%s, %s, %s))", blockPos.getX(), blockPos.getY(), blockPos.getZ()));
                }
            }
            out.append(");");

            File outFile = new File(file.getParentFile(), file.getName() + ".txt");
            IOUtil.writer(outFile, out.toString());
        }
    }
}
