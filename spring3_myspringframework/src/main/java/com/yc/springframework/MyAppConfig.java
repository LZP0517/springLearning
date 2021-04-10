package com.yc.springframework;

import com.yc.bean.HelloWorld;
import com.yc.springframework.stereotype.MyBean;
import com.yc.springframework.stereotype.MyComponentScan;
import com.yc.springframework.stereotype.MyConfiguration;

/**
 * @program: testspring
 * @description:
 * @author: 作者
 * @create: 2021-04-05 15:00
 */
@MyConfiguration
@MyComponentScan(basePackages = {"com.yc.dao", "com.yc.biz"})
public class MyAppConfig {

    @MyBean
    public HelloWorld hw() {
        return new HelloWorld();
    }
}
