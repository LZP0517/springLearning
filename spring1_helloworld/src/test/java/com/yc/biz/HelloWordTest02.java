package com.yc.biz;

import com.yc.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@DependsOn("helloWord")
public class HelloWordTest02 {
    @Autowired
    private HelloWord hw1;
    @Autowired
    private HelloWord hw2;

    @Test
    public void testHello() {
        System.out.println("aaa");
        System.out.println(hw1.hashCode() + "\t" + hw2.hashCode());
    }


}