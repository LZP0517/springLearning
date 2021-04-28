package com.yc.tx.service;

import com.yc.tx.AppConfig;
import com.yc.tx.bean.Accounts;
import com.yc.tx.bean.OpTypes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class AccountServiceImplTest {

    @Autowired
    private AccountService accountService;

    @Test
    public void testOpenAccount() {
        Accounts accounts = new Accounts();
        accounts.setBalance(1);
        accountService.openAccount(accounts, 1.0);
    }

    @Test
    public void testDeposite() {
        Accounts accounts = new Accounts();
        accounts.setAccountId(8);
        accountService.deposite(accounts, 400, OpTypes.deposite.getName(), null);
    }

    @Test
    public void testWithdraw() {
        Accounts accounts = new Accounts();
        accounts.setAccountId(8);
        accountService.withdraw(accounts, 400, OpTypes.withdraw.getName(), null);
    }

    @Test
    @Transactional //在junit中使用 在测试后恢复现场
    public void testTransfer() {
        Accounts accounts = new Accounts();
        accounts.setAccountId(8);
        Accounts accounts01 = new Accounts();
        accounts01.setAccountId(9);
        accountService.transfer(accounts, accounts01, 100);
    }

    @Test
    public void testShowBalance() {
        Accounts accounts = new Accounts();
        accounts.setAccountId(9);
        accounts.setBalance(201);
        System.out.println(accountService.showBalance(accounts));
    }

    @Test
    public void testFindById() {
    }
}