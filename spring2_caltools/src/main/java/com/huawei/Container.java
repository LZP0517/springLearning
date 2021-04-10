package com.huawei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @program: testspring
 * @description:
 * @author: 作者
 * @create: 2021-04-05 09:42
 */
@Component
public class Container {
    private Measurable measurable;

    @Autowired
    public void setMeasurable(Measurable m) {
        this.measurable = m;
    }

    @PostConstruct
    public void setUp() {
        System.out.println("容器构造后setUp");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("容器销毁前。。。");
    }

    public void save() {
        System.out.println("Aaa");
    }
}
