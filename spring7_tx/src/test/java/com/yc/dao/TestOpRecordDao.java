package com.yc.dao;

import com.yc.tx.AppConfig;
import com.yc.tx.bean.OpRecord;
import com.yc.tx.bean.OpTypes;
import com.yc.tx.dao.OpRecordDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @program: testspring
 * @description:
 * @author: 作者
 * @create: 2021-04-15 20:01
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class TestOpRecordDao {
    @Autowired
    private OpRecordDao dao;

    @Test
    public void testSave() {
        OpRecord o = new OpRecord();
        o.setAccountid(3);
        o.setOpmoney(20.0);
        Timestamp time = new Timestamp(new Date().getTime());
        o.setOptime(time);
        o.setOptype(OpTypes.withdraw.getName()); //用枚举存值（约束值） 不容易出错
        System.out.println(dao.saveOpRecord(o));
    }

    @Test
    public void testFindALl() {
        System.out.println(dao.findAll());
        Assert.assertNotEquals(0, dao.findAll().size());
    }

    @Test
    public void testFindByAccountId() {
        System.out.println(dao.findByAccountId(3));
        Assert.assertNotEquals(0, dao.findByAccountId(3).size());
    }
}
