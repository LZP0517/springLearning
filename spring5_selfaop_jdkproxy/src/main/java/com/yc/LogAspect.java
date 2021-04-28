package com.yc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;

/**
 * @program: testspring
 * @description:
 * @author: 作者
 * @create: 2021-04-10 20:18
 */
public class LogAspect implements InvocationHandler {
    private Object target; //1.引用目标类的对象

    public LogAspect(Object target) {
        this.target = target;
    }

    //2.生成代理对象
    public Object createProxy() {
        return Proxy.newProxyInstance(this.target.getClass().getClassLoader(), this.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理类对象:" + proxy.getClass());
        System.out.println("目标类的方法:" + method);
        System.out.println("方法中的参数:" + args);

        if (method.getName().startsWith("add")) {
            //前置增强
            log();
        }
        Object returnValue = method.invoke(this.target, args);

        return returnValue;
    }

    private void log() {
        System.out.println("=======before");
        System.out.println(new Date());
        System.out.println("==========");
    }
}
