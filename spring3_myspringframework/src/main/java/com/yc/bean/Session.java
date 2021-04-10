package com.yc.bean;

import com.yc.springframework.stereotype.MyService;

/**
 * @program: testspring
 * @description:
 * @author: 作者
 * @create: 2021-04-05 16:22
 */
@MyService
public class Session implements MySession {
    private String name = "张三";

    public String getSession() {
        System.out.println("session");
        return "Session";
    }

    public Session() {
        System.out.println("Session构造......");
    }

    @Override
    public String toString() {
        return "Session:" + this.name;
    }
}
