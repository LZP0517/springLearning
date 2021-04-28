package yc;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * @program: testspring
 * @description:
 * @author: 作者
 * @create: 2021-04-10 20:18
 */
public class LogCglib implements MethodInterceptor {
    private Object target; //1.引用目标类的对象

    public LogCglib(Object target) {
        this.target = target;
    }

    //2.生成代理对象
    public Object createProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    private void log() {
        System.out.println("=======before");
        System.out.println(new Date());
        System.out.println("==========");
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("代理类对象:" + o.getClass());
        System.out.println("目标类的方法:" + method);
        System.out.println("方法中的参数:" + args);
        System.out.println("要代理的方法:" + method);
        Object returnValue = method.invoke(this.target, args);
        return returnValue;

    }
}
