package com.til.dusk.common.data;

import com.til.dusk.Dusk;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.tags.ITag;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.IntFunction;
import java.util.stream.Stream;

/**
 * 标签反射添加
 *
 * @author til
 */
@Mod.EventBusSubscriber(modid = Dusk.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TagAdd {

    public static Field forgeRegistry_tagManager;
    public static Field forgeRegistryTagManager_tags;
    public static Class<?> forgeRegistryTagManagerClass;

    public static Map<IForgeRegistry<?>, Map<TagKey<?>, List<?>>> map = new HashMap<>();


    static {
        try {
            forgeRegistryTagManagerClass = Class.forName("net.minecraftforge.registries.ForgeRegistryTagManager");
        } catch (ClassNotFoundException e) {
            Dusk.instance.logger.error("", e);
        }
        try {
            forgeRegistry_tagManager = ForgeRegistry.class.getDeclaredField("tagManager");
            forgeRegistry_tagManager.setAccessible(true);
            forgeRegistryTagManager_tags = forgeRegistryTagManagerClass.getDeclaredField("tags");
            forgeRegistryTagManager_tags.setAccessible(true);
        } catch (NoSuchFieldException e) {
            Dusk.instance.logger.error("", e);
        }
    }

    @SubscribeEvent
    public static void onEvent(ServerStartingEvent event) {
        try {
            for (Map.Entry<IForgeRegistry<?>, Map<TagKey<?>, List<?>>> iForgeRegistryMapEntry : map.entrySet()) {
                Object forgeRegistryTagManager = forgeRegistry_tagManager.get(iForgeRegistryMapEntry.getKey());
                Map<TagKey<?>, ITag<?>> tagKeyListMap = (Map<TagKey<?>, ITag<?>>) forgeRegistryTagManager_tags.get(forgeRegistryTagManager);
                addTag(tagKeyListMap, iForgeRegistryMapEntry.getValue());
            }
        } catch (IllegalAccessException e) {
            Dusk.instance.logger.error("", e);
        }
    }


    protected static  void addTag(Map<TagKey<?>, ITag<?>> tags, Map<TagKey<?>, List<?>> addTag) {
        for (Map.Entry<TagKey<?>, List<?>> tagKeyListEntry : addTag.entrySet()) {
            TagList<Object> tagList = new TagList<>();
            for (Object t : tags.get(tagKeyListEntry.getKey())) {
                tagList.add(t);
            }
            tagList.addAll(tagKeyListEntry.getValue());
            tags.put(tagKeyListEntry.getKey(), tagList);
        }

    }

    public static <T> void addTag(IForgeRegistry<T> forgeRegistry, TagKey<T> tTagKey, T t) {
        Map<TagKey<?>, List<?>> tagKeyListMap;
        if (map.containsKey(forgeRegistry)) {
            tagKeyListMap = map.get(forgeRegistry);
        } else {
            tagKeyListMap = new HashMap<>();
            map.put(forgeRegistry, tagKeyListMap);
        }
        List<T> tList;
        if (tagKeyListMap.containsKey(tTagKey)) {
            tList = (List<T>) tagKeyListMap.get(tTagKey);
        } else {
            tList = new ArrayList<>();
            tagKeyListMap.put(tTagKey, tList);
        }
        tList.add(t);
    }

    public static class TagList<V> extends ArrayList<V> implements ITag<V> {

        public TagList(int initialCapacity) {
            super(initialCapacity);
        }

        public TagList() {
            super();
        }

        public TagList(@NotNull Collection<? extends V> c) {
            super(c);
        }

        @Override
        public Stream<V> stream() {
            return super.stream();
        }

        @Override
        public Stream<V> parallelStream() {
            return super.parallelStream();
        }

        @Override
        public TagKey<V> getKey() {
            return null;
        }

        @Override
        public Optional<V> getRandomElement(RandomSource random) {
            return Optional.empty();
        }

        @Override
        public boolean isBound() {
            return !isEmpty();
        }
    }

}
