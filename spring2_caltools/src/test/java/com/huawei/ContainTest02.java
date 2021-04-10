package com.huawei;

import com.AppConfig;
import com.mimi.PersonBmiTool;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Random;

/**
 * @program: testspring
 * @description:
 * @author: 作者
 * @create: 2021-04-05 10:20
 */

public class ContainTest02 {
    private ApplicationContext ac;
    private Random r;
    private Container c;
    private PersonBmiTool p;

    @Before
    public void SetUp() {
        this.ac = new AnnotationConfigApplicationContext(AppConfig.class);
        this.r = (Random) ac.getBean("r");
        this.c = (Container) ac.getBean("container");
        this.p = (PersonBmiTool) ac.getBean("personBmiTool");
    }

    @Test
    public void testCon() {
        System.out.println(r.nextInt());
        c.save();
        p.send("zhangsan");
    }
}
