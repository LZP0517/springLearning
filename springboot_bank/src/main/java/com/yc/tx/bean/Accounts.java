package com.yc.tx.bean;

import lombok.Data;

/**
 * @program: testspring
 * @description:
 * @author: 作者
 * @create: 2021-04-15 18:31
 */

@Data //利用lombok插件 自动继承 Get Set toString ...方法
public class Accounts {
    private Integer accountId;
    private double balance;

}
