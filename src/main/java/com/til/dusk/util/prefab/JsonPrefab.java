package com.til.dusk.util.prefab;

public class JsonPrefab {

    public static final String CURRENCY_ITEM_MODEL =
            """
                    '{'
                      "parent": "item/generated",
                      "textures": '{'
                        "layer0": "{0}:item/{1}"
                      '}'
                    '}'
                    """;

    public static final String BLOCK_STATE_MODEL =
            """
                    '{'
                      "variants": '{'
                        "": '{'
                          "model": "{0}:block/{1}"
                        '}'
                      '}'
                    '}'
                    """;

    public static final String FACING_BLOCK_STATE_JSON =
            """
                    '{'
                      "variants": '{'
                        "facing=down,waterlogged=true": '{'
                          "model": "{0}:block/{1}",
                          "x": 180
                        '}',
                        "facing=east,waterlogged=true": '{'
                          "model": "{0}:block/{1}",
                          "x": 90,
                          "y": 90
                        '}',
                        "facing=north,waterlogged=true": '{'
                          "model": "{0}:block/{1}",
                          "x": 90
                        '}',
                        "facing=south,waterlogged=true": '{'
                          "model": "{0}:block/{1}",
                          "x": 90,
                          "y": 180
                        '}',
                        "facing=up,waterlogged=true": '{'
                          "model": "{0}:block/{1}"
                        '}',
                        "facing=west,waterlogged=true": '{'
                          "model": "{0}:block/{1}",
                          "x": 90,
                          "y": 270
                       '}',
                       "facing=down,waterlogged=false": '{'
                          "model": "{0}:block/{1}",
                          "x": 180
                        '}',
                        "facing=east,waterlogged=false": '{'
                          "model": "{0}:block/{1}",
                          "x": 90,
                          "y": 90
                        '}',
                        "facing=north,waterlogged=false": '{'
                          "model": "{0}:block/{1}",
                          "x": 90
                        '}',
                        "facing=south,waterlogged=false": '{'
                          "model": "{0}:block/{1}",
                          "x": 90,
                          "y": 180
                        '}',
                        "facing=up,waterlogged=false": '{'
                          "model": "{0}:block/{1}"
                        '}',
                        "facing=west,waterlogged=false": '{'
                          "model": "{0}:block/{1}",
                          "x": 90,
                          "y": 270
                       '}'
                      '}'
                    '}'
                    """;

    public static final String BRICK_SLAB =
            """
                    '{'
                      "variants": '{'
                        "type=bottom": '{'
                          "model": "{0}:block/slab/{1}"
                        '}',
                        "type=double": '{'
                          "model": "{0}:block/slab/{1}_bricks"
                        '}',
                        "type=top": '{'
                          "model": "{0}:block/slab/{1}_top"
                        '}'
                      '}'
                    '}'
                    """;

    public static final String BRICK_STAIRS =
            """
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

    public static final String ITEM_FATHER =
            """
                    '{'
                      "parent": "{0}:item/{1}"
                    '}'
                    """;

    public static final String ITEM_BLOCK_FATHER =
            """
                     '{'
                       "parent": "{0}:block/{1}"
                    '}'
                     """;

    public static final String FLUID_BLOCK_STATE =
            """
                    '{'
                      "variants": '{'
                        "": '{'
                          "model": "minecraft:block/water"
                        '}'
                      '}'
                    '}'
                    """;

    public static final String WALL =
            """
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
