package com.xu.mybatis.plugins;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;

import java.util.Properties;

/**
 *
 * MyBatis 允许你在已映射语句执行过程中的某一点进行拦截调用。默认情况下，MyBatis 允许使用插件来拦截的方法调用包括：
 *  Executor (update, query, flushStatements, commit, rollback, getTransaction, close, isClosed)
 *  ParameterHandler (getParameterObject, setParameters)
 *  ResultSetHandler (handleResultSets, handleOutputParameters)
 *  StatementHandler (prepare, parameterize, batch, update, query)
 *
 *
 * Created by xj on 2018/10/10.
 */
public class ExamplePlugin  implements Interceptor{

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        return invocation.proceed();
    }
    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o,this);
    }
    @Override
    public void setProperties(Properties properties) {
    }
}
