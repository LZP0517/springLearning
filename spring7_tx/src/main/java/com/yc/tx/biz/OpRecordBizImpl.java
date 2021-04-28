package com.yc.tx.biz;

import com.yc.tx.bean.Accounts;
import com.yc.tx.bean.OpRecord;
import com.yc.tx.dao.AccountsDao;
import com.yc.tx.dao.OpRecordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: testspring
 * @description:
 * @author: 作者
 * @create: 2021-04-15 20:37
 */
@Service
public class OpRecordBizImpl implements OpRecordBiz {
    private OpRecordDao opRecordDao;
    private AccountsDao accountsDao;

    @Autowired
    public void setOpRecordDao(OpRecordDao opRecordDao) {
        this.opRecordDao = opRecordDao;
    }

    @Autowired
    public void setAccountsDao(AccountsDao accountsDao) {
        this.accountsDao = accountsDao;
    }


    @Override
    public int withdraw(OpRecord opRecord) {
        Accounts account = accountsDao.findAccount(opRecord.getAccountid());
        double money = account.getBalance() + opRecord.getOpmoney(); //存钱后余额
        int result01 = opRecordDao.saveOpRecord(opRecord);
        account.setBalance(money);
        accountsDao.updateAccount(account);
        return result01;
    }

    @Override
    public int deposite(OpRecord opRecord) {
        Accounts account = accountsDao.findAccount(opRecord.getAccountid());
        double money = account.getBalance() - opRecord.getOpmoney(); //存钱后余额
        int result01 = opRecordDao.saveOpRecord(opRecord);
        account.setBalance(money);
        accountsDao.updateAccount(account);
        return result01;
    }
}
