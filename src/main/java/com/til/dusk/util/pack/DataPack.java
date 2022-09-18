package com.til.dusk.util.pack;

import com.til.dusk.common.register.mana_level.ManaLevel;
import com.til.dusk.common.register.shaped.shapeds.ShapedOre;
import com.til.dusk.util.Extension;
import com.til.dusk.util.GenericMap;
import com.til.dusk.util.Util;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluid;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class DataPack<T extends DataPack<?, OTHER_DATA>, OTHER_DATA> implements GenericMap.IGenericMapSupplier {

    public final List<Extension.Action_2V<ShapedOre, OTHER_DATA>> run = new ArrayList<>();
    @Nullable
    public GenericMap genericMap;

    public T addInItem(TagKey<Item> itemTagKey, int i) {
        run.add((s, m) -> s.addInItem(itemTagKey, i));
        return Util.forcedConversion(this);
    }


    public T addInFluid(TagKey<Fluid> fluidTagKey, int i) {
        run.add((s, m) -> s.addInFluid(fluidTagKey, i));
        return Util.forcedConversion(this);
    }

    public T addRun(Extension.Action_2V<ShapedOre, OTHER_DATA> run) {
        this.run.add(run);
        return Util.forcedConversion(this);
    }

    public void run(ShapedOre s, OTHER_DATA otherData) {
        for (Extension.Action_2V<ShapedOre, OTHER_DATA> v : run) {
            v.action(s, otherData);
        }
    }

    public <V> T setSet(GenericMap.IKey<V> key, V v) {
        if (genericMap == null) {
            genericMap = new GenericMap();
        }
        genericMap.put(key, v);
        return Util.forcedConversion(this);
    }

    @Override
    public <V> V getSet(GenericMap.IKey<V> key) {
        if (genericMap == null) {
            return null;
        }
        return genericMap.get(key);
    }

    @Override
    public boolean hasSet(GenericMap.IKey<?> key) {
        if (genericMap == null) {
            return false;
        }
        return genericMap.containsKey(key);
    }

    public static class ManaLevelDataPack extends DataPack<ManaLevelDataPack, ManaLevel> {

    }

    public static class MultipleManaLevelDataPack extends DataPack<MultipleManaLevelDataPack, ManaLevel> {

        @Override
        public MultipleManaLevelDataPack addInItem(TagKey<Item> itemTagKey, int i) {
            run.add((s, m) -> s.addInItem(itemTagKey, i * m.level));
            return Util.forcedConversion(this);
        }

        @Override
        public MultipleManaLevelDataPack addInFluid(TagKey<Fluid> fluidTagKey, int i) {
            run.add((s, m) -> s.addInFluid(fluidTagKey, i * m.level));
            return Util.forcedConversion(this);
        }
    }

    public static class OreDataPack extends DataPack<OreDataPack, Void> {
    }

    public static final GenericMap.IKey<Integer> AMOUNT = new GenericMap.IKey.Key<>();
}
