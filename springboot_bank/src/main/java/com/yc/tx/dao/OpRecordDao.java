package com.yc.tx.dao;

import com.yc.tx.bean.OpRecord;

import java.util.List;

/**
 * @program: testspring
 * @description:
 * @author: 作者
 * @create: 2021-04-15 19:55
 */
public interface OpRecordDao {
    public int saveOpRecord(OpRecord opRecord);

    public List<OpRecord> findAll();

    public List<OpRecord> findByAccountId(int accountid);
}
