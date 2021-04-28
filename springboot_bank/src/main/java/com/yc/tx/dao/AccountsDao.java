package com.yc.tx.dao;

import com.yc.tx.bean.Accounts;

import java.util.List;

/**
 * @program: testspring
 * @description:
 * @author: 作者
 * @create: 2021-04-15 18:31
 */
public interface AccountsDao {

    public int saveAccount(Accounts account);

    public Accounts updateAccount(Accounts account);

    public Accounts findAccount(int accountId);

    public List<Accounts> findAll();

    public int delete(int accountId);

}
