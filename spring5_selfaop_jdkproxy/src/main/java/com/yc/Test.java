package com.yc;

import com.yc.biz.StudentBiz;
import com.yc.biz.StudentBizImpl;

/**
 * @program: testspring
 * @description:
 * @author: 作者
 * @create: 2021-04-10 20:19
 */
public class Test {
    public static void main(String[] args) {
        StudentBiz stu = new StudentBizImpl();
        LogAspect lc = new LogAspect(stu);
        Object o = lc.createProxy(); //o就是代理对象
        System.out.println(o);
        if (o instanceof StudentBiz) {
            StudentBiz sb = (StudentBiz) o;
            sb.find("zzz");
            sb.add("aaa");
            sb.update("ccc");
        }
    }
}
