package com.til.dusk.common.config;

/**
 * @author til
 */
public interface IAcceptConfig<C> {
    void setConfig(C c);
    C defaultConfig();
}
