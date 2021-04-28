package com.yc.biz;

/**
 * @program: testspring
 * @description:
 * @author: 作者
 * @create: 2021-04-11 15:00
 */
public class StudentBizImpl implements StudentBiz {
    @Override
    public int add(String name) {
        System.out.println("调用了StudentBizImpl中的add：" + name);
        return 111;
    }

    @Override
    public void update(String name) {
        System.out.println("调用了StudentBizImpl中的update：" + name);
    }

    @Override
    public String find(String name) {
        System.out.println("调用了StudentBizImpl中的find：" + name);
        return name + "find";
    }
}
