package com.til.dusk.particle;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.til.dusk.util.Pos;
import net.minecraft.world.level.Level;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum CommonParticle implements IParticleRegister {
    AIR,
    MANA_TRANSFER,
    ITEM_TRANSFER,
    FLUID_TRANSFER;


    public static final Map<Level, List<Data>> MAP = new HashMap<>();

    @Override
    public String type() {
        return toString();
    }

    public static class Data {

        public String type = "";
        public Pos start = new Pos();
        public Pos end = new Pos();
        public JsonObject old;

        public Data(String type, Pos start, Pos end, JsonObject old) {
            this.type = type;
            this.start = start;
            this.end = end;
            this.old = old;
        }

        public Data(JsonObject jsonObject) {
            if (jsonObject.has("start")) {
                start = new Pos(jsonObject.getAsJsonObject("start"));
            }
            if (jsonObject.has("end")) {
                end = new Pos(jsonObject.getAsJsonObject("end"));
            }
            if (jsonObject.has("old")) {
                old = jsonObject.getAsJsonObject("old");
            }
            if (jsonObject.has("type")) {
                type = jsonObject.get("type").getAsString();
            }
        }

        public JsonObject getJson() {
            JsonObject jsonObject = new JsonObject();

            jsonObject.add("type", new JsonPrimitive(type));

            JsonObject _start = start.getJson();
            jsonObject.add("start", _start);

            JsonObject _end = end.getJson();
            jsonObject.add("end", _end);

            jsonObject.add("old", old);

            return jsonObject;
        }


    }
}
