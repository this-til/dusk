package com.til.dusk.common.capability.black;

import com.til.dusk.util.Extension;
import com.til.dusk.util.Util;

import javax.annotation.Nullable;
import java.util.*;

/**
 * @author til
 */
public class Back implements IBack {

    public final Map<BlackType<?>, List<Extension.Action_1V<?>>> map = new HashMap<>();
    @Nullable
    public List<IBack> sonMap;

    @Override
    public <T> void add(BlackType<T> blackType, Extension.Action_1V<T> run) {
        List<Extension.Action_1V<?>> runMap = map.computeIfAbsent(blackType, k -> new ArrayList<>());
        runMap.add(run);
    }

    @Override
    public <T> void run(BlackType<T> blackType, T t0) {
        if (sonMap != null) {
            for (IBack value : sonMap) {
                value.run(blackType, t0);
            }
        }
        List<Extension.Action_1V<?>> runMap = map.get(blackType);
        if (runMap == null || runMap.isEmpty()) {
            return;
        }
        for (Extension.Action_1V<?> action_1V : runMap) {
            action_1V.action(Util.forcedConversion(t0));
        }

    }

    @Override
    public void add(IBack back) {
        if (sonMap == null) {
            sonMap = new ArrayList<>();
        }
        sonMap.add(back);
    }

    @Override
    public void remove(IBack back) {
        if (sonMap == null) {
            return;
        }
        sonMap.remove(back);
        if (sonMap.isEmpty()) {
            sonMap = null;
        }
    }
}
