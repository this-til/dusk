package com.til.dusk.common.world.tag;

import com.til.dusk.Dusk;
import com.til.dusk.common.register.RegisterBasics;
import com.til.dusk.util.ResourceLocationUtil;
import com.til.dusk.util.pack.TagPack;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

/**
 * 一个TAG的供应商注册项
 *
 * @author til
 */
public class TagPackSupplier {
    public final ResourceLocation name;

    /***
     * 修饰
     */
    @Nullable
    public final ResourceLocation modification;

    protected TagPack tagPack;

    /***
     * 关联的Tag
     */
    protected final Map<ResourceLocation, TagPack> relationTagPack = new HashMap<>();

    public TagPackSupplier(ResourceLocation name, @Nullable String modification) {
        this.name = name;
        this.modification = modification == null ? null : new ResourceLocation(name.getNamespace(), modification);
    }

    /***
     * 返回代表自身的Tag
     */
    public TagPack getTagPack() {
        if (tagPack == null) {
            if (modification == null) {
                tagPack = new TagPack(
                        Dusk.instance.ITEM_TAG.createTagKey(name),
                        Dusk.instance.BLOCK_TAG.createTagKey(name),
                        Dusk.instance.FLUID_TAG.createTagKey(name));
            } else {
                tagPack = new TagPack(
                        Dusk.instance.ITEM_TAG.createTagKey(ResourceLocationUtil.fuseName("/", name, modification)),
                        Dusk.instance.BLOCK_TAG.createTagKey(ResourceLocationUtil.fuseName("/", name, modification)),
                        Dusk.instance.FLUID_TAG.createTagKey(ResourceLocationUtil.fuseName("/", name, modification)));
            }
        }
        return tagPack;
    }

    public TagPack getTagPack(RegisterBasics<?> registerBasics) {
        return getTagPack(registerBasics.name);
    }

    public TagPack getTagPack(ResourceLocation resourceLocation) {
        if (relationTagPack.containsKey(resourceLocation)) {
            return relationTagPack.get(resourceLocation);
        }
        TagPack tagPack;
        if (modification == null) {
            tagPack = new TagPack(
                    Dusk.instance.ITEM_TAG.createTagKey(ResourceLocationUtil.fuseName("/", name, resourceLocation)),
                    Dusk.instance.BLOCK_TAG.createTagKey(ResourceLocationUtil.fuseName("/", name, resourceLocation)),
                    Dusk.instance.FLUID_TAG.createTagKey(ResourceLocationUtil.fuseName("/", name, resourceLocation)));
        } else {
            tagPack = new TagPack(
                    Dusk.instance.ITEM_TAG.createTagKey(ResourceLocationUtil.fuseName("/", name, modification, resourceLocation)),
                    Dusk.instance.BLOCK_TAG.createTagKey(ResourceLocationUtil.fuseName("/", name, modification, resourceLocation)),
                    Dusk.instance.FLUID_TAG.createTagKey(ResourceLocationUtil.fuseName("/", name, modification, resourceLocation)));
        }
        relationTagPack.put(resourceLocation, tagPack);
        return tagPack;
    }

}
