package com.til.dusk.common.config;

import java.util.ArrayList;

/**
 * 配置列表，方便生产默认配置
 *
 * @author til
 */
public class ConfigList<E> extends ArrayList<E> {

    /***
     * 链式添加
     */
    public ConfigList<E> addChain(E e) {
        super.add(e);
        return this;
    }
}
