package com.yc.tx.service;


import com.yc.tx.bean.Accounts;
import com.yc.tx.bean.OpRecord;

import java.util.List;

/**
 * @program: testspring
 * @description:
 * @author: 作者
 * @create: 2021-04-17 16:49
 */
public interface AccountService {
    public Integer openAccount(Accounts accounts, double money);

    public Accounts deposite(Accounts accounts, double money, String optype, String transferid);

    public Accounts withdraw(Accounts accounts, double money, String optype, String transferid);

    public Accounts transfer(Accounts inAccount, Accounts outAccount, double money);

    public Accounts showBalance(Accounts account);

    public List<OpRecord> findById(Accounts account);

    public List<Accounts> findAll();
}
