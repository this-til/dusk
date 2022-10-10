package com.til.dusk.client.data.lang;

/**
 * @author til
 */

public enum LangType {

    /***
     * 中文
     */
    ZH_CN("zh_cn") {
        @Override
        public String optimize(String old) {
            return old.trim();
        }
    },

    /***
     * 英文
     */
    EN_CH("en_ch") {
        @Override
        public String optimize(String old) {
            old = old.trim();
            old = old.toUpperCase();
            old += " ";
            return old;
        }
    };

    public final String name;

    LangType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public abstract String optimize(String old);

}
