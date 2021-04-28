package com.yc.tx.dao;

import com.yc.tx.bean.Accounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @program: testspring
 * @description:
 * @author: 作者
 * @create: 2021-04-15 18:31
 */
@Repository
public class AccountsDaoImpl implements AccountsDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int saveAccount(Accounts account) {
        String sql = "insert into accounts(balance) values(?) ";
        //第一种方法 只能返回 1 0
//        int result = this.jdbcTemplate.update(
//                sql,
//                account.getBalance());
//        return result;

        //第二种 可以返回生成键值
        KeyHolder keyHolder = new GeneratedKeyHolder(); //生成键的保存器
//        jdbcTemplate.update(connection -> {  //lambda表达式
//            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"balance"});
//            ps.setObject(1, account.getBalance());
//            return ps;
//        }, keyHolder);
        //正常书写 == lambda表达式书写
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, new String[]{"balance"});
                ps.setObject(1, account.getBalance());
                return ps;
            }
        }, keyHolder);
        return keyHolder.getKey().intValue(); //返回生成键值（accountId值）
    }

    @Override
    public Accounts updateAccount(Accounts account) {
        String sql = "update accounts set balance=? where accountid=? ";
        this.jdbcTemplate.update(sql, account.getBalance(), account.getAccountId());
        return account;
    }

    @Override
    public Accounts findAccount(int accountId) {
        String sql = "select * from accounts where accountid=? ";
        Accounts a = jdbcTemplate.queryForObject(sql, (rs, row) -> {
            Accounts account = new Accounts();
            account.setAccountId(rs.getInt("accountid"));
            account.setBalance(rs.getDouble("balance"));
            return account;
        }, accountId);
        return a;
    }

    @Override
    public List<Accounts> findAll() {
        List<Accounts> list = this.jdbcTemplate.query(
                "select * from accounts   ",
                (rs, rowNum) -> {
                    Accounts a = new Accounts();
                    a.setAccountId(rs.getInt("accountid"));
                    a.setBalance(rs.getDouble("balance"));
                    return a;
                });
        
        return list;
    }

    @Override
    public int delete(int accountId) {
        String sql = "delete from accounts where accountid = ?";
        int result = this.jdbcTemplate.update(
                sql,
                accountId);
        return result;
    }
}
