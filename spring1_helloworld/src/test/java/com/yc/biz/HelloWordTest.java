package com.yc.biz;

import com.yc.AppConfig;
import junit.framework.TestCase;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HelloWordTest extends TestCase {
    private ApplicationContext as; //spring 容器

    @Override
    @Before
    public void setUp() {
        as = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    public void testHello() {
        HelloWord hw = (HelloWord) as.getBean("helloWord");
        hw.hello();

        HelloWord hw2 = (HelloWord) as.getBean("helloWord");
        hw2.hello();
        //spring 是单例模式
    }


}