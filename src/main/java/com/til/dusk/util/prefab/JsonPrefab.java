package com.til.dusk.util.prefab;

public class JsonPrefab {

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

}
