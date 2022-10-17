package com.til.dusk.common.register.shaped.shapeds;

import com.til.dusk.Dusk;
import com.til.dusk.common.event.DelayTrigger;
import com.til.dusk.common.event.RegisterShapedEvent;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.common.register.RegisterManage;
import com.til.dusk.common.register.shaped.ShapedDrive;
import com.til.dusk.common.register.shaped.shaped_type.ShapedType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import java.util.*;
import java.util.function.Consumer;

/**
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AllShaped {

    public final static Map<ShapedType, Map<ShapedDrive, List<Shaped>>> MAP = new HashMap<>();
    public static final Map<ResourceLocation, Shaped> ID_MAP = new HashMap<>();

    @SubscribeEvent
    public static void onEvent(FMLCommonSetupEvent event) {
        Map<ShapedType, Map<ShapedDrive, List<Shaped>>> map = new HashMap<>();
        Consumer<Shaped> shapedConsumer = s -> {
            if (s == null) {
                return;
            }
            if (ID_MAP.containsKey(s.name)) {
                ResourceLocation oldName = s.name;
                s.name = new ResourceLocation(s.name.getNamespace(), s.name.getPath() + "_");
                Dusk.instance.logger.error("配方出现重复[{}]的ID，已经修正为[{}]", oldName, s.name);
            }
            ID_MAP.put(s.name, s);
            Map<ShapedDrive, List<Shaped>> shapedDriveListMap;
            if (map.containsKey(s.shapedType)) {
                shapedDriveListMap = map.get(s.shapedType);
            } else {
                shapedDriveListMap = new HashMap<>(8);
                map.put(s.shapedType, shapedDriveListMap);
            }
            List<Shaped> list;
            if (shapedDriveListMap.containsKey(s.shapedDrive)) {
                list = shapedDriveListMap.get(s.shapedDrive);
            } else {
                list = new ArrayList<>(8);
                shapedDriveListMap.put(s.shapedDrive, list);
            }
            list.add(s);
        };
        for (RegisterBasics<?> allRegisterBasic : RegisterManage.ALL_REGISTER_BASICS) {
            allRegisterBasic.registerShaped(shapedConsumer);
        }
        Dusk.instance.modEventBus.post(new RegisterShapedEvent(shapedConsumer));
        DelayTrigger.run(DelayTrigger.SHAPED, r -> shapedConsumer.accept(r.func()));
        for (Map.Entry<ShapedType, Map<ShapedDrive, List<Shaped>>> e1 : map.entrySet()) {
            for (Map.Entry<ShapedDrive, List<Shaped>> e2 : e1.getValue().entrySet()) {
                AllShaped.Judge judge = new AllShaped.Judge();
                for (Shaped shaped : e2.getValue()) {
                    if (!(shaped instanceof ShapedOre shapedOre)) {
                        continue;
                    }
                    judge.containsAndDeBug(new AllShaped.JudgeCell(shapedOre));
                }
            }
        }
        MAP.putAll(map);
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

        public JudgeCell(ShapedOre shapedOre) {
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
}
