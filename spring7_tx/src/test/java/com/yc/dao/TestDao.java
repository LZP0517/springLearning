package com.yc.dao;

import com.yc.tx.AppConfig;
import com.yc.tx.bean.Accounts;
import com.yc.tx.dao.AccountsDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @program: testspring
 * @description:
 * @author: 作者
 * @create: 2021-04-14 21:20
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class TestDao {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private AccountsDao dao;

    @Test
    public void testDataSource() throws SQLException {
        Assert.assertNotNull(dataSource);
        System.out.println(dataSource.getConnection());
    }

    @Test
    public void testAccountDao() throws SQLException {
        Assert.assertNotNull(dao);
        System.out.println(dao.findAccount(1));
    }

    @Test
    public void testSaveAccounts() throws SQLException {
        Assert.assertNotNull(dao);
        Accounts accounts = new Accounts();
        accounts.setBalance(10);
        System.out.println(dao.saveAccount(accounts));
    }

    @Test
    public void testUpdateAccounts() throws SQLException {
        Assert.assertNotNull(dao);
        Accounts accounts = new Accounts();
        accounts.setBalance(2);
        accounts.setAccountId(1);
        System.out.println(dao.updateAccount(accounts));
    }

    @Test
    public void testDeleteAccounts() throws SQLException {
        Assert.assertNotNull(dao);
        System.out.println(dao.delete(2));
    }

    @Test
    public void testFindAll() throws SQLException {
        Assert.assertNotNull(dao);
        System.out.println(dao.findAll());
    }

    @Test
    public void testFindAccounts() throws SQLException {
        Assert.assertNotNull(dao);
        Accounts accounts = new Accounts();
        System.out.println(dao.findAccount(1));
    }
}
