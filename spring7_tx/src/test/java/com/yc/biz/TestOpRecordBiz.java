package com.yc.biz;

import com.yc.tx.AppConfig;
import com.yc.tx.bean.OpRecord;
import com.yc.tx.biz.OpRecordBiz;
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
 * @create: 2021-04-15 20:48
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class TestOpRecordBiz {
    @Autowired
    private OpRecordBiz biz;

    @Test
    public void testWithdraw() {
        OpRecord o = new OpRecord();
        o.setAccountid(3);
        o.setOpmoney(20.0);
        Timestamp time = new Timestamp(new Date().getTime());
        o.setOptime(time);
        o.setOptype("withdraw");
        biz.withdraw(o);
    }
}
