package com.til.dusk.common.capability.up;


import com.til.dusk.util.Extension;

import java.util.ArrayList;
import java.util.List;

/**
 * 对于实体方块添加更新回调
 * 这个回调只会在服务端触发
 *
 * @author til
 */
public class Up implements IUp {

    public final List<Extension.Action> run = new ArrayList<>();

    @Override
    public void addUpBlack(Extension.Action run) {
        this.run.add(run);
    }

    @Override
    public void up() {
        for (Extension.Action action : run) {
            action.action();
        }
    }
}
