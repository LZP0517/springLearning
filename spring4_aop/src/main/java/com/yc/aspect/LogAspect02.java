package com.yc.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: testspring
 * @description:
 * @author: 作者
 * @create: 2021-04-09 20:12
 */
@Aspect //切面类：增强功能写这里面
@Component //IOC注解 以实现让spring托管的功能
@Order(1)
public class LogAspect02 {
    //切入点表达式 execution(modifiers-pattern? ret-type-pattern declaring-type-pattern?name-pattern(
    //                     param throws-pattern?))
    // ?:代表出现0次或1次
    // modifiers-pattern: 修饰符
    // ret-type-pattern:返回类型
    // declaring-type-pattern
    // name-pattern:名字模型

    //切入点的声明 pointcut signature
    @Pointcut("execution(* com.yc.biz.StudentBizImpl.add*(..))") //切入点表达式
    private void add() {

    }

    @Pointcut("execution(* com.yc.biz.StudentBizImpl.update*(..))")
    private void update() {

    }

    @Pointcut("execution(* com.yc.biz.StudentBizImpl.find*(..))")
    private void find() {

    }

    @Pointcut("add() || update()")
    private void AddAndUpdate() {

    }

    //增强的声明
    //@Before("execution(* com.yc.biz.StudentBizImpl.add*(..)) || execution(* com.yc.biz.StudentBizImpl.update*(..))")
    //@Before("com.aspect.LogAspect.AddAndUpdate()")
    @Before("AddAndUpdate()")
    public void log() throws InterruptedException {
        System.out.println("==========aspect02前置增强的日志==========");
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dstr = sdf.format(d);
        System.out.println("执行时间:" + dstr);
        Thread.sleep(3000);
        System.out.println("===========增强结束==============");
    }

    //    @After("AddAndUpdate()")
//    public void bye(JoinPoint jp) {//spring容器是个ioc容器，它可以使用di将程序运行的信息注入
//        System.out.println("--------bye----------");
//        //连接点JoinPoint中的所有信息
//        Object target = jp.getTarget();
//        System.out.println("目标类为:" + target);
//
//        Object method = jp.getSignature();
//        System.out.println("方法:" + method);
//        Object[] os = jp.getArgs();
//        if (os != null) {
//            for (Object o : os) {
//                System.out.println("参数:" + o);
//            }
//        }
//
//        System.out.println("===========bye==============");
//    }
//
    @Around("find()")
    public Object compute(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("aspect02进入 环绕增强");

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dstr = sdf.format(d);
        long start = System.currentTimeMillis();
        System.out.println("aspect02时间:" + dstr);
        Thread.sleep(3000);
        Object retVal = pjp.proceed();
        long end = System.currentTimeMillis();
        System.out.println("运行时间:" + (end - start));
        System.out.println("aspect02推出增强...");
        return retVal;
    }
}
