package yc;

import yc.biz.StudentBizImpl;

/**
 * @program: testspring
 * @description:
 * @author: 作者
 * @create: 2021-04-10 20:19
 */
public class Test {
    public static void main(String[] args) {
        StudentBizImpl stu = new StudentBizImpl();
        LogCglib lc = new LogCglib(stu);
        Object o = lc.createProxy(); //o就是代理对象
        System.out.println(o);
        if (o instanceof StudentBizImpl) {
            StudentBizImpl sb = (StudentBizImpl) o;
            sb.find("zzz");
            sb.add("aaa");
            sb.update("ccc");
        }
    }
}
