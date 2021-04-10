package com.yc.bean;

import com.yc.springframework.stereotype.MyAutowired;
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
public class HelloWorld {
    private Session session1;
    //    private Session session2;
    private MySession mySession;


    @MyPostConstruct
    public void setUp() {
        System.out.println("MyPostConstruct");
    }

    @MyPreDestroy
    public void destroy() {
        System.out.println("MyPreDestroy");
    }

    public HelloWorld() {
        System.out.println("hello world 构造");
    }

    @MyAutowired
    public void setSession(Session session) {
        this.session1 = session;
    }

//    @MyAutowired
//    public void setSession(MySession mySession) {
//        this.mySession = mySession;
//    }

//    @MyResource
//    public void setSession2(Session session) {
//        this.session2 = session;
//    }

    public void show() {
        System.out.println("show" + "\t" + "\t" + this.session1.toString());
    }


}
