package com.xu.mybatis.plugins;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Statement;
import java.util.Properties;

/**
 * Created by xj on 2018/10/14.
 */
/*@Intercepts({
        @Signature(type = StatementHandler.class,method = "parameterize",args = Statement.class)
})
public class MySecondPlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("MysecondPlugin....plugin"+invocation.getMethod());
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        System.out.println("MysecondPlugin...plugin:"+o);
        return Plugin.wrap(o,this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}*/
