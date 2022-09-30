package com.til.dusk.common.world.item;

import com.til.dusk.Dusk;
import com.til.dusk.common.data.tag.ItemTag;
import com.til.dusk.common.event.DelayTrigger;
import com.til.dusk.common.register.shaped.shapeds.Shaped;
import com.til.dusk.util.Extension;
import com.til.dusk.util.prefab.JsonPrefab;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author til
 */
public class ItemBasics extends Item {
    protected TagKey<Item> tag;

    public ItemBasics(Properties properties) {
        super(properties);
        DelayTrigger.addRun(DelayTrigger.GATHER_DATA_EVENT, this::tag);
    }

    public ItemBasics addTag(TagKey<Item> tagKey) {
        ItemTag.addTag(tagKey, this);
        return this;
    }

    public ItemBasics addShaped(Extension.Func<Shaped> func) {
        DelayTrigger.addRun(DelayTrigger.SHAPED, func);
        return this;
    }

    public ItemBasics addRecipe(Extension.Func<ShapedRecipeBuilder> func) {
        DelayTrigger.addRun(DelayTrigger.RECIPE, c -> func.func().save(c));
        return this;
    }

    public TagKey<Item> tag() {
        if (tag == null) {
            ResourceLocation resourceLocation = ForgeRegistries.ITEMS.getKey(this);
            assert resourceLocation != null;
            tag = Dusk.instance.ITEM_TAG.createTagKey(resourceLocation);
            ItemTag.addTag(tag, this);
        }
        return tag;
    }

    public static class ItemGenerateModel extends ItemBasics implements DuskItem.ICustomModel {
        public ItemGenerateModel(Properties properties) {
            super(properties);
        }

        @Override
        public ResourceLocation itemModelName() {
            return ForgeRegistries.ITEMS.getKey(this);
        }

        @Override
        public String itemJsonBasics() {
            return JsonPrefab.CURRENCY_ITEM_MODEL;
        }
    }
}
