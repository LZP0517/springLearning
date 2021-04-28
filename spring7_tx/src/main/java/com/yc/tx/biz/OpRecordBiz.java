package com.yc.tx.biz;

import com.yc.tx.bean.OpRecord;

/**
 * @program: testspring
 * @description:
 * @author: 作者
 * @create: 2021-04-15 20:35
 */
public interface OpRecordBiz {
    public int withdraw(OpRecord opRecord);

    public int deposite(OpRecord opRecord);
}
