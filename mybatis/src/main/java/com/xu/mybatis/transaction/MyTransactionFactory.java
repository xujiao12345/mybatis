package com.xu.mybatis.transaction;

import org.apache.ibatis.session.TransactionIsolationLevel;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.TransactionFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Properties;

/**
 * 自定义事物管理
 * Created by xj on 2018/10/10.
 */
public class MyTransactionFactory implements TransactionFactory {
    @Override
    public void setProperties(Properties properties) {

    }

    @Override
    public Transaction newTransaction(Connection connection) {
        return null;
    }

    @Override
    public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel transactionIsolationLevel, boolean b) {
        return null;
    }
}
