package com.module.base;

/**
 * Created by ShiShow_xk on 2017/8/15.
 */

public interface BaseFunction {
    /**
     * 初始化UI控件
     */
    void findView();

    /**
     * 初始化数据
     */
    void initView();

    /**
     * 初始化监听事件
     */
    void addListener();
}
