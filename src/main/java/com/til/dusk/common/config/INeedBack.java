package com.til.dusk.common.config;

/***
 * 表明需要回调
 * 回调在ConfigMap.get()中
 * @author til
 */
public interface INeedBack {

    /***
     * 进行回调
     */
    void back();

}
