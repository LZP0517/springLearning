package com.yc.tx.service;


import com.yc.tx.bean.OpRecord;

import java.util.List;

/**
 * @program: spring_bank
 * @description:
 * @author: 作者
 * @create: 2021-04-20 19:20
 */
public interface OpRecordService {
    public List<OpRecord> find(OpRecord opRecord);
}
