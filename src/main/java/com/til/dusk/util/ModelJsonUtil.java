package com.til.dusk.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;

public class ModelJsonUtil {

    public static JsonObject createItemModel(ResourceLocation name) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("parent", new JsonPrimitive("item/generated"));
        JsonObject textures = new JsonObject();
        textures.add("layer0", new JsonPrimitive(name.getNamespace() + ":item/" + name.getPath()));
        jsonObject.add("textures", textures);
        return jsonObject;
    }

    public static JsonObject createBlockState(ResourceLocation name) {
        JsonObject variants = new JsonObject();
        JsonObject model = new JsonObject();
        model.add("model", new JsonPrimitive(name.getNamespace() + ":block/" + name.getPath()));
        variants.add("", model);
        return blockStatePack(variants);
    }

    public static JsonObject createFluidBlockState() {
        JsonObject variants = new JsonObject();
        JsonObject model = new JsonObject();
        model.add("model", new JsonPrimitive("minecraft:block/water"));
        variants.add("", model);
        return blockStatePack(variants);
    }

    public static JsonObject blockStatePack(JsonObject variants) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("variants", variants);
        return jsonObject;
    }

    public static JsonObject createItemFather(ResourceLocation name) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("parent", new JsonPrimitive(name.getNamespace() + ":item/" + name.getPath()));
        return jsonObject;
    }

    public static JsonObject createBlockFather(ResourceLocation name) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("parent", new JsonPrimitive(name.getNamespace() + ":block/" + name.getPath()));
        return jsonObject;
    }

    public static JsonObject fillFacing(JsonObject jsonObject, Direction direction) {
        switch (direction) {
            case UP -> {
            }
            case DOWN -> {
                jsonObject.add("x", new JsonPrimitive(180));
            }
            case EAST -> {
                jsonObject.add("x", new JsonPrimitive(90));
                jsonObject.add("y", new JsonPrimitive(90));
            }
            case NORTH -> {
                jsonObject.add("x", new JsonPrimitive(90));
            }
            case SOUTH -> {
                jsonObject.add("x", new JsonPrimitive(90));
                jsonObject.add("y", new JsonPrimitive(180));
            }
            case WEST -> {
                jsonObject.add("x", new JsonPrimitive(90));
                jsonObject.add("y", new JsonPrimitive(270));
            }
        }
        return jsonObject;
    }

    public static final String BRICK_STAIRS = """
            '{'
              "variants": '{'
                "facing=east,half=bottom,shape=inner_left": '{'
                  "model": "{0}:block/stairs/stairs_inner",
                  "uvlock": true,
                  "y": 270
                '}',
                "facing=east,half=bottom,shape=inner_right": '{'
                  "model": "{0}:block/stairs/stairs_inner"
                '}',
                "facing=east,half=bottom,shape=outer_left": '{'
                  "model": "{0}:block/stairs/stairs_outer",
                  "uvlock": true,
                  "y": 270
                '}',
                "facing=east,half=bottom,shape=outer_right": '{'
                  "model": "{0}:block/stairs/stairs_outer"
                '}',
                "facing=east,half=bottom,shape=straight": '{'
                  "model": "{0}:block/stairs/stairs"
                '}',
                "facing=east,half=top,shape=inner_left": '{'
                  "model": "{0}:block/stairs/stairs_inner",
                  "uvlock": true,
                  "x": 180
                '}',
                "facing=east,half=top,shape=inner_right": '{'
                  "model": "{0}:block/stairs/stairs_inner",
                  "uvlock": true,
                  "x": 180,
                  "y": 90
                '}',
                "facing=east,half=top,shape=outer_left": '{'
                  "model": "{0}:block/stairs/stairs_outer",
                  "uvlock": true,
                  "x": 180
                '}',
                "facing=east,half=top,shape=outer_right": '{'
                  "model": "{0}:block/stairs/stairs_outer",
                  "uvlock": true,
                  "x": 180,
                  "y": 90
                '}',
                "facing=east,half=top,shape=straight": '{'
                  "model": "{0}:block/stairs/stairs",
                  "uvlock": true,
                  "x": 180
                '}',
                "facing=north,half=bottom,shape=inner_left": '{'
                  "model": "{0}:block/stairs/stairs_inner",
                  "uvlock": true,
                  "y": 180
                '}',
                "facing=north,half=bottom,shape=inner_right": '{'
                  "model": "{0}:block/stairs/stairs_inner",
                  "uvlock": true,
                  "y": 270
                '}',
                "facing=north,half=bottom,shape=outer_left": '{'
                  "model": "{0}:block/stairs/stairs_outer",
                  "uvlock": true,
                  "y": 180
                '}',
                "facing=north,half=bottom,shape=outer_right": '{'
                  "model": "{0}:block/stairs/stairs_outer",
                  "uvlock": true,
                  "y": 270
                '}',
                "facing=north,half=bottom,shape=straight": '{'
                  "model": "{0}:block/stairs/stairs",
                  "uvlock": true,
                  "y": 270
                '}',
                "facing=north,half=top,shape=inner_left": '{'
                  "model": "{0}:block/stairs/stairs_inner",
                  "uvlock": true,
                  "x": 180,
                  "y": 270
                '}',
                "facing=north,half=top,shape=inner_right": '{'
                  "model": "{0}:block/stairs/stairs_inner",
                  "uvlock": true,
                  "x": 180
                '}',
                "facing=north,half=top,shape=outer_left": '{'
                  "model": "{0}:block/stairs/stairs_outer",
                  "uvlock": true,
                  "x": 180,
                  "y": 270
                '}',
                "facing=north,half=top,shape=outer_right": '{'
                  "model": "{0}:block/stairs/stairs_outer",
                  "uvlock": true,
                  "x": 180
                '}',
                "facing=north,half=top,shape=straight": '{'
                  "model": "{0}:block/stairs/stairs",
                  "uvlock": true,
                  "x": 180,
                  "y": 270
                '}',
                "facing=south,half=bottom,shape=inner_left": '{'
                  "model": "{0}:block/stairs/stairs_inner"
                '}',
                "facing=south,half=bottom,shape=inner_right": '{'
                  "model": "{0}:block/stairs/stairs_inner",
                  "uvlock": true,
                  "y": 90
                '}',
                "facing=south,half=bottom,shape=outer_left": '{'
                  "model": "{0}:block/stairs/stairs_outer"
                '}',
                "facing=south,half=bottom,shape=outer_right": '{'
                  "model": "{0}:block/stairs/stairs_outer",
                  "uvlock": true,
                  "y": 90
                '}',
                "facing=south,half=bottom,shape=straight": '{'
                  "model": "{0}:block/stairs/stairs",
                  "uvlock": true,
                  "y": 90
                '}',
                "facing=south,half=top,shape=inner_left": '{'
                  "model": "{0}:block/stairs/stairs_inner",
                  "uvlock": true,
                  "x": 180,
                  "y": 90
                '}',
                "facing=south,half=top,shape=inner_right": '{'
                  "model": "{0}:block/stairs/stairs_inner",
                  "uvlock": true,
                  "x": 180,
                  "y": 180
                '}',
                "facing=south,half=top,shape=outer_left": '{'
                  "model": "{0}:block/stairs/stairs_outer",
                  "uvlock": true,
                  "x": 180,
                  "y": 90
                '}',
                "facing=south,half=top,shape=outer_right": '{'
                  "model": "{0}:block/stairs/stairs_outer",
                  "uvlock": true,
                  "x": 180,
                  "y": 180
                '}',
                "facing=south,half=top,shape=straight": '{'
                  "model": "{0}:block/stairs/stairs",
                  "uvlock": true,
                  "x": 180,
                  "y": 90
                '}',
                "facing=west,half=bottom,shape=inner_left": '{'
                  "model": "{0}:block/stairs/stairs_inner",
                  "uvlock": true,
                  "y": 90
                '}',
                "facing=west,half=bottom,shape=inner_right": '{'
                  "model": "{0}:block/stairs/stairs_inner",
                  "uvlock": true,
                  "y": 180
                '}',
                "facing=west,half=bottom,shape=outer_left": '{'
                  "model": "{0}:block/stairs/stairs_outer",
                  "uvlock": true,
                  "y": 90
                '}',
                "facing=west,half=bottom,shape=outer_right": '{'
                  "model": "{0}:block/stairs/stairs_outer",
                  "uvlock": true,
                  "y": 180
                '}',
                "facing=west,half=bottom,shape=straight": '{'
                  "model": "{0}:block/stairs/stairs",
                  "uvlock": true,
                  "y": 180
                '}',
                "facing=west,half=top,shape=inner_left": '{'
                  "model": "{0}:block/stairs/stairs_inner",
                  "uvlock": true,
                  "x": 180,
                  "y": 180
                '}',
                "facing=west,half=top,shape=inner_right": '{'
                  "model": "{0}:block/stairs/stairs_inner",
                  "uvlock": true,
                  "x": 180,
                  "y": 270
                '}',
                "facing=west,half=top,shape=outer_left": '{'
                  "model": "{0}:block/stairs/stairs_outer",
                  "uvlock": true,
                  "x": 180,
                  "y": 180
                '}',
                "facing=west,half=top,shape=outer_right": '{'
                  "model": "{0}:block/stairs/stairs_outer",
                  "uvlock": true,
                  "x": 180,
                  "y": 270
                '}',
                "facing=west,half=top,shape=straight": '{'
                  "model": "{0}:block/stairs/stairs",
                  "uvlock": true,
                  "x": 180,
                  "y": 180
                '}'
              '}'
            '}'
            """;

    public static final String WALL = """
            '{'
              "multipart": [
                '{'
                  "apply": '{'
                    "model": "{0}:block/wall/wall_post"
                  '}',
                  "when": '{'
                    "up": "true"
                  '}'
                '}',
                '{'
                  "apply": '{'
                    "model": "{0}:block/wall/wall_side",
                    "uvlock": true
                  '}',
                  "when": '{'
                    "north": "low"
                  '}'
                '}',
                '{'
                  "apply": '{'
                    "model": "{0}:block/wall/wall_side",
                    "uvlock": true,
                    "y": 90
                  '}',
                  "when": '{'
                    "east": "low"
                  '}'
                '}',
                '{'
                  "apply": '{'
                    "model": "{0}:block/wall/wall_side",
                    "uvlock": true,
                    "y": 180
                  '}',
                  "when": '{'
                    "south": "low"
                  '}'
                '}',
                '{'
                  "apply": '{'
                    "model": "{0}:block/wall/wall_side",
                    "uvlock": true,
                    "y": 270
                  '}',
                  "when": '{'
                    "west": "low"
                  '}'
                '}',
                '{'
                  "apply": '{'
                    "model": "{0}:block/wall/wall_side_tall",
                    "uvlock": true
                  '}',
                  "when": '{'
                    "north": "tall"
                  '}'
                '}',
                '{'
                  "apply": '{'
                    "model": "{0}:block/wall/wall_side_tall",
                    "uvlock": true,
                    "y": 90
                  '}',
                  "when": '{'
                    "east": "tall"
                  '}'
                '}',
                '{'
                  "apply": '{'
                    "model": "{0}:block/wall/wall_side_tall",
                    "uvlock": true,
                    "y": 180
                  '}',
                  "when": '{'
                    "south": "tall"
                  '}'
                '}',
                '{'
                  "apply": '{'
                    "model": "{0}:block/wall/wall_side_tall",
                    "uvlock": true,
                    "y": 270
                  '}',
                  "when": '{'
                    "west": "tall"
                  '}'
                '}'
              ]
            '}'
            """;
}
