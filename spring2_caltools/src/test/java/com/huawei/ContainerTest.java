package com.huawei;

import com.AppConfig;
import com.mimi.PersonBmiTool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.PostConstruct;
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class) //结合junit4测试
@ContextConfiguration(classes = {AppConfig.class}) //加载配值文件
public class ContainerTest implements ApplicationContextAware {
    @Autowired
    private Random r;
    @Autowired
    private Container c;
    @Autowired
    private PersonBmiTool p;

    private ApplicationContext ac;

    @PostConstruct
    public void setUp() {
        Environment an = ac.getEnvironment();
        System.out.println(an.getProperty("user.home"));
        System.out.println(an.getProperty("user.dir"));

    }

    @Test
    public void testSave() {
        System.out.println(r.nextInt());
        c.save();
        p.send("zhangsan");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ac = applicationContext;
    }
}