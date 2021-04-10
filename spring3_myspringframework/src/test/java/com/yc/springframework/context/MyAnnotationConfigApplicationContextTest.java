package com.yc.springframework.context;

import com.yc.bean.HelloWorld;
import com.yc.bean2.HelloWorld02;
import com.yc.springframework.MyAppConfig;
import junit.framework.TestCase;

import java.lang.reflect.InvocationTargetException;

public class MyAnnotationConfigApplicationContextTest extends TestCase {

    public void testGetBean() throws InstantiationException, IllegalAccessException, ClassNotFoundException, InvocationTargetException {
        MyApplicationContext ac = new MyAnnotationConfigApplicationContext(MyAppConfig.class);
        HelloWorld hw = (HelloWorld) ac.getBean("helloWorld");
        HelloWorld02 hw2 = (HelloWorld02) ac.getBean("helloWorld02");
        hw.show();
        hw2.show();
    }
}