package com.yc.tx.service;

import com.yc.tx.bean.Accounts;
import com.yc.tx.bean.OpTypes;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceImplTest extends TestCase {
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
        accounts.setAccountId(9);
        accountService.deposite(accounts, 100, OpTypes.deposite.getName(), null);
    }

    @Test
    public void testWithdraw() {
        Accounts accounts = new Accounts();
        accounts.setAccountId(8);
        accountService.withdraw(accounts, 400, OpTypes.withdraw.getName(), null);
    }

    @Test
    public void testTransfer() {
        Accounts accounts = new Accounts();
        accounts.setAccountId(9);
        Accounts accounts01 = new Accounts();
        accounts01.setAccountId(8);
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