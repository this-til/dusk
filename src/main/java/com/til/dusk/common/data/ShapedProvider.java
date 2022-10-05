package com.til.dusk.common.data;

import com.google.gson.JsonObject;
import com.til.dusk.Dusk;
import com.til.dusk.common.event.DelayTrigger;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.common.register.RegisterManage;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.util.Extension;
import com.til.dusk.util.nbt.pack.AllNBTPack;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluid;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

/**
 * @author til
 */
public class ShapedProvider implements DataProvider {
    @Override
    public void run(@NotNull CachedOutput cachedOutput) throws IOException {
        for (RegisterBasics<?> allRegisterBasic : RegisterManage.ALL_REGISTER_BASICS) {
            allRegisterBasic.registerShaped();
        }
        DelayTrigger.run(DelayTrigger.SHAPED, Extension.Func::func);
        for (Map.Entry<ShapedType, Map<ShapedDrive, List<Shaped>>> e1 : Shaped.MAP.entrySet()) {
            for (Map.Entry<ShapedDrive, List<Shaped>> e2 : e1.getValue().entrySet()) {
                Judge judge = new Judge();
                for (Shaped shaped : e2.getValue()) {
                    if (!(shaped instanceof ShapedOre shapedOre)) {
                        continue;
                    }
                    judge.containsAndDeBug(new JudgeCell(shapedOre));
                    generate(shaped, cachedOutput);
                }
            }
        }

    }

    public void generate(Shaped shaped, CachedOutput cachedOutput) throws IOException {
        JsonObject jsonObject = new JsonObject();
        AllNBTPack.TYPE.set(jsonObject, shaped.getClass());
        shaped.writ(jsonObject);
        Path mainOutput = DuskData.dataGenerator.getOutputFolder();
        String pathSuffix = String.format("data/%s/shaped/%s/%s/%s.json",
                shaped.shapedType.name.getNamespace(),
                shaped.shapedType.name.getPath(),
                shaped.shapedDrive.name.getPath(),
                shaped.name);
        Path outputPath = mainOutput.resolve(pathSuffix);
        DataProvider.saveStable(cachedOutput, jsonObject, outputPath);
    }

    @Override
    public @NotNull String getName() {
        return "shaped";
    }

    /***
     * 判断器
     */
    protected static class Judge {
        public List<JudgeCell> judgeCells = new ArrayList<>();

        /***
         * 判断并且DeBug
         */
        public void containsAndDeBug(JudgeCell judgeCell) {

            for (JudgeCell cell : judgeCells) {
                if (cell.contains(judgeCell)) {
                    Dusk.instance.logger.error("配方{}包含{}", cell.shapedOre, judgeCell.shapedOre);
                }
                if (judgeCell.contains(cell)) {
                    Dusk.instance.logger.error("配方{}包含{}", judgeCell.shapedOre, cell.shapedOre);
                }
            }
            judgeCells.add(judgeCell);

        }

    }

    /***
     * 判断元素
     */
    protected static class JudgeCell {
        public final ShapedOre shapedOre;
        public final Set<TagKey<Item>> item;
        public final Set<TagKey<Fluid>> fluid;

        protected JudgeCell(ShapedOre shapedOre, Set<TagKey<Item>> item, Set<TagKey<Fluid>> fluid) {
            this.shapedOre = shapedOre;
            this.item = item;
            this.fluid = fluid;
        }

        protected JudgeCell(ShapedOre shapedOre) {
            this.shapedOre = shapedOre;
            item = shapedOre.item != null ? shapedOre.item.keySet() : new HashSet<>();
            fluid = shapedOre.fluid != null ? shapedOre.fluid.keySet() : new HashSet<>();
        }

        public boolean contains(JudgeCell judgeCell) {
            Set<TagKey<Item>> item = new HashSet<>(judgeCell.item);
            for (TagKey<Item> itemTagKey : this.item) {
                item.remove(itemTagKey);
            }
            Set<TagKey<Fluid>> fluid = new HashSet<>(judgeCell.fluid);
            for (TagKey<Fluid> fluidTagKey : this.fluid) {
                fluid.remove(fluidTagKey);
            }
            return item.isEmpty() && fluid.isEmpty();
        }
    }


}
