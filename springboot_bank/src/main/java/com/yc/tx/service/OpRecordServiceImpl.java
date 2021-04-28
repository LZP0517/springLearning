package com.yc.tx.service;


import com.yc.tx.bean.OpRecord;
import com.yc.tx.dao.OpRecordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: spring_bank
 * @description:
 * @author: 作者
 * @create: 2021-04-20 19:22
 */
@Service
public class OpRecordServiceImpl implements OpRecordService {
    @Autowired
    private OpRecordDao opRecordDao;

    @Override
    public List<OpRecord> find(OpRecord opRecord) {
        return opRecordDao.findByAccountId(opRecord.getAccountid());
    }
}
