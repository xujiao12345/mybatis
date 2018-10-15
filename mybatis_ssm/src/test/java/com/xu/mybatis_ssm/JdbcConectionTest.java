package com.xu.mybatis_ssm;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.Assert;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by xj on 2018/10/13.
 */
public class JdbcConectionTest {

    @Test
    public void test1() throws SQLException {
        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://47.107.70.138:3307/mybatis?useSSL=false&useUnicode=true&characterEncoding=utf-8");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");

        Connection connection = dataSource.getConnection();
        Assert.assertNotNull(connection);
    }

}
