package com.yc.biz;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @program: testspring
 * @description:
 * @author: 作者
 * @create: 2021-04-04 15:27
 */
@Component //只要加了这个注解 则这个类可以被spring容器托管
//@Lazy //不自动
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE) //可以创建多个实例对象
public class HelloWord {
    public HelloWord() {
        System.out.println("无参构造方法");
    }

    public void hello() {
        System.out.println("hello word");
    }
}
