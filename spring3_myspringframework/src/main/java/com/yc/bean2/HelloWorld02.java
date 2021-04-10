package com.yc.bean2;

import com.yc.springframework.stereotype.MyComponent;
import com.yc.springframework.stereotype.MyPostConstruct;
import com.yc.springframework.stereotype.MyPreDestroy;

/**
 * @program: testspring
 * @description:
 * @author: 作者
 * @create: 2021-04-05 14:57
 */
@MyComponent
public class HelloWorld02 {

    @MyPostConstruct
    public void setUp() {
        System.out.println("MyPostConstruct");
    }

    @MyPreDestroy
    public void destroy() {
        System.out.println("MyPreDestroy");
    }

    public HelloWorld02() {
        System.out.println("hello world02 构造");
    }

    public void show() {
        System.out.println("show02");
    }
}
