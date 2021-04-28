package com.yc.tx.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @program: testspring
 * @description:
 * @author: 作者
 * @create: 2021-04-15 20:51
 */
@Aspect
@Component
public class aspect01 {

    private DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Before("execution(* com.yc.tx.biz.OpRecordBizImpl.*(..))")
    public void find(JoinPoint point) {

    }

    //切入点声明
    @Around("execution(* com.yc.tx.biz.OpRecordBizImpl.*(..))")
    public Object withdraw(ProceedingJoinPoint point) throws SQLException {
        Object returnVal = null;
        try {
            Connection connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            System.out.println("数据库自动提交取消");
            returnVal = point.proceed();
            System.out.println("数据commit");
            connection.commit();
        } catch (Exception e) {
            if (e instanceof SQLException) {
                System.out.println("发生sql错误,回滚");
                dataSource.getConnection().rollback();
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            dataSource.getConnection().setAutoCommit(true);
        }
        return returnVal;
    }

}
