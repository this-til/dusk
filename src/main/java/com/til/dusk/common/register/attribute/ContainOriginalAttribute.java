package com.til.dusk.common.register.attribute;

import com.til.dusk.Dusk;
import net.minecraft.resources.ResourceLocation;

/**
 * 改属性包含原版
 *
 * @author til
 */
public abstract class ContainOriginalAttribute extends Attribute {
    public final net.minecraft.world.entity.ai.attributes.Attribute originalAttribute;

    public ContainOriginalAttribute(ResourceLocation name, net.minecraft.world.entity.ai.attributes.Attribute originalAttribute) {
        super(name);
        this.originalAttribute = originalAttribute;
    }

    public ContainOriginalAttribute(String name, net.minecraft.world.entity.ai.attributes.Attribute originalAttribute) {
        this(new ResourceLocation(Dusk.MOD_ID, name), originalAttribute);
    }

    @Override
    protected void registerBack() {
        //TODO 添加实体属性拦截事件
    }
}
