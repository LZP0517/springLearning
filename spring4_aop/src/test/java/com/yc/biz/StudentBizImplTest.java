package com.yc.biz;

import com.yc.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) //结合junit4测试
@ContextConfiguration(classes = {AppConfig.class}) //加载配值文件
public class StudentBizImplTest {
    @Autowired
    private StudentBiz biz;

    @Test
    public void testAdd() {
        biz.add("张三");
    }

    @Test
    public void testUpdate() {
        biz.update("张三");
    }

    @Test
    public void testFind() throws InterruptedException {
        biz.find("张三");
    }
}