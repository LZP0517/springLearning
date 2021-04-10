package com.yc.biz;

/**
 * @program: testspring
 * @description:
 * @author: 作者
 * @create: 2021-04-09 20:13
 */
public interface StudentBiz {
    int add(String name);

    void update(String name);

    String find(String name) throws InterruptedException;
}
