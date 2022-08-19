package com.til.dusk.util.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MessageData {
    public String key;
    public List<String> keys;
    public boolean actionBar;

    public MessageData(boolean actionBar, String key, String... strings) {
        this.key = key;
        keys = new ArrayList<>();
        if (strings != null) {
            keys.addAll(Arrays.asList(strings));
        }
        this.actionBar = actionBar;
    }
}
