package com.til.dusk.util;

import com.til.dusk.Dusk;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

/**
 * 静态标签
 *
 * @author til
 */
public class StaticTag {

    /***
     * 该标签有效需要父类有效
     */
    public final List<StaticTag> father;

    public final String name;

    public StaticTag(ResourceLocation name, List<StaticTag> father) {
        this(name.toString(), father);
    }

    public StaticTag( String name,List<StaticTag> father) {
        this.father = father;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
