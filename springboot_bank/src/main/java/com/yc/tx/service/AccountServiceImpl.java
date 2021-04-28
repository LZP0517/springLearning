package com.yc.tx.service;


import com.yc.tx.bean.Accounts;
import com.yc.tx.bean.OpRecord;
import com.yc.tx.bean.OpTypes;
import com.yc.tx.dao.AccountsDao;
import com.yc.tx.dao.OpRecordDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 * @program: testspring
 * @description:
 * @author: 作者
 * @create: 2021-04-17 16:53
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, //传播机制
        isolation = Isolation.DEFAULT, //隔离级别
        readOnly = false, //只读
        timeout = 100, //超时时间
        rollbackForClassName = {"RuntimeException"}) //异常回滚
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountsDao accountsDao;
    @Resource
    private OpRecordDao opRecordDao;

    @Override
    public Integer openAccount(Accounts accounts, double money) {
        accounts.setBalance(money);
        int accountid = accountsDao.saveAccount(accounts);
        OpRecord opRecord = new OpRecord();
        opRecord.setOptime(new Timestamp(System.currentTimeMillis()));
        opRecord.setOpmoney(money);
        opRecord.setOptype(OpTypes.deposite.getName());
        opRecord.setAccountid(accountid);
        opRecordDao.saveOpRecord(opRecord);
        return accountid;
    }

    @Override
    public Accounts deposite(Accounts accounts, double money, String optype, String transferid) {
        Accounts a = accountsDao.findAccount(accounts.getAccountId());
        a.setBalance(a.getBalance() + money);
        accountsDao.updateAccount(a);
        OpRecord opRecord = new OpRecord();
        opRecord.setOptime(new Timestamp(System.currentTimeMillis()));
        opRecord.setOpmoney(money);
        opRecord.setOptype(optype);
        opRecord.setAccountid(a.getAccountId());
        if (transferid == null) {
            opRecord.setTransferid(" ");
        } else {
            opRecord.setTransferid(transferid);
        }
        opRecordDao.saveOpRecord(opRecord);
        return a;
    }

    @Override
    public Accounts withdraw(Accounts accounts, double money, String optype, String transferid) {
        Accounts a = accountsDao.findAccount(accounts.getAccountId());
        a.setBalance(a.getBalance() - money);
        OpRecord opRecord = new OpRecord();
        opRecord.setOptime(new Timestamp(System.currentTimeMillis()));
        opRecord.setOpmoney(money);
        opRecord.setOptype(optype);
        opRecord.setAccountid(a.getAccountId());
        if (transferid == null) {
            opRecord.setTransferid(" ");
        } else {
            opRecord.setTransferid(transferid);
        }
        opRecordDao.saveOpRecord(opRecord);
        accountsDao.updateAccount(a);
        return a;
    }


    @Override
    @Transactional
    public Accounts transfer(Accounts inAccount, Accounts outAccount, double money) {
        String uid = UUID.randomUUID().toString();
        this.deposite(inAccount, money, OpTypes.transfer.getName(), uid);
        Accounts newAccounts = this.withdraw(outAccount, money, OpTypes.transfer.getName(), uid);
        return newAccounts;

    }

    @Override
    @Transactional(readOnly = true)
    public Accounts showBalance(Accounts account) {
        return accountsDao.findAccount(account.getAccountId());
    }

    @Override
    public List<OpRecord> findById(Accounts account) {
        return opRecordDao.findByAccountId(account.getAccountId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Accounts> findAll() {
        return accountsDao.findAll();
    }


}
