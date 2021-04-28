package com.yc.tx.bean;

/**
 * @program: testspring
 * @description:
 * @author: 作者
 * @create: 2021-04-17 14:15
 */
public enum OpTypes {
    deposite("deposite", 1), withdraw("withdraw", 2), transfer("transfer", 3);

    //枚举对象成员变量
    private String name;
    private int index;

    private OpTypes(String name, int index) {
        this.name = name;
        this.index = index;
    }

    @Override
    public String toString() {
        return this.index + "-" + this.name;
    }

    public String getName() {
        return this.name;
    }
}
