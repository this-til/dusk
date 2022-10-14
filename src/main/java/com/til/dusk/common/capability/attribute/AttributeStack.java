package com.til.dusk.common.capability.attribute;

import com.til.dusk.common.register.attribute.Attribute;
import com.til.dusk.common.register.attribute.limit.AttributeLimit;

import java.util.HashMap;
import java.util.Map;

/**
 * @author til
 */
public class AttributeStack implements IAttributeStack {
    public Map<Attribute, Double> basics = new HashMap<>();
    public Map<AttributeLimit, Double> limit = new HashMap<>();
    public Map<Attribute, Double> all = new HashMap<>();

    @Override
    public double getBasics(Attribute attribute) {
        if (!basics.containsKey(attribute)) {
            basics.put(attribute, 0d);
            return 0;
        }
        return basics.get(attribute);
    }

    @Override
    public double getBasics(AttributeLimit attributeLimit) {
        if (!this.limit.containsKey(attributeLimit)) {
            this.limit.put(attributeLimit, 0d);
            return 0;
        }
        return this.limit.get(attributeLimit);
    }

    @Override
    public double setBasics(Attribute attribute, double d) {
        return basics.put(attribute, attribute.range.ofValue(d));
    }

    @Override
    public double setBasics(AttributeLimit attributeLimit, double d) {
        return this.limit.put(attributeLimit, attributeLimit.limit(d));
    }

    @Override
    public double addBasics(Attribute attribute, double add) {
        return basics.put(attribute, attribute.range.ofValue(basics.get(attribute) + add));
    }

    @Override
    public double get(Attribute attribute) {
        if (!this.all.containsKey(attribute)) {
            return all.get(attribute);
        }
        return 0;
    }

    @Override
    public double get(AttributeLimit attributeLimit) {
        if (!this.limit.containsKey(attributeLimit)) {
            this.limit.put(attributeLimit, 0d);
            return 0d;
        }
        return getBasics(attributeLimit) * all.get(attributeLimit.attribute);
    }

    @Override
    public double set(AttributeLimit attributeLimit, double d) {
        return this.limit.put(attributeLimit, attributeLimit.limit(d / get(attributeLimit.attribute)));
    }

    @Override
    public double add(AttributeLimit attributeLimit, double d) {
        return this.limit.put(attributeLimit, attributeLimit.limit(get(attributeLimit) + d / get(attributeLimit.attribute)));
    }
}
