package com.yc.springframework.context;

import java.lang.reflect.InvocationTargetException;

/**
 * @program: testspring
 * @description:
 * @author: 作者
 * @create: 2021-04-05 14:46
 */
public interface MyApplicationContext {
    public Object getBean(String id) throws IllegalAccessException, InstantiationException, InvocationTargetException;
}
