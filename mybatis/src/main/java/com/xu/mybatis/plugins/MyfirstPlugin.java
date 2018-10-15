package com.xu.mybatis.plugins;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Statement;
import java.util.Properties;

/**
 * Created by xj on 2018/10/14.
 */

/*
*
* 完成插件签名，告诉mybatis当前插件用来拦截哪个对象的哪个方法
*
* */
@Intercepts({
        @Signature(type = StatementHandler.class,method = "parameterize",args = Statement.class)
})
public class MyfirstPlugin implements Interceptor {
    /*
    * 拦截目标对象的目标方法的执行
    * */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("MyfirstPlugin.........intercept:"+invocation.getMethod());
        //需求：动态的改变一下运行的参数，以前1号员工，实际从数据库查询5号员工
        //当前拦截到的对象：
        Object target = invocation.getTarget();
        System.out.println("当前；拦截到的对象："+target);
        //拿到拦截对象的元数据
        MetaObject metaObject = SystemMetaObject.forObject(target);
        //拿到元素据中某个属性的值
        Object metaObjectValue = metaObject.getValue("parameterHandler.parameterObject");
        System.out.println("sql语句用的参数："+metaObjectValue);
        //修改sql语句要用的参数
//        metaObject.setValue("parameterHandler.parameterObject",5);
//        执行目标方法
        Object proceed = invocation.proceed();
//        返回执行后的返回值
        return proceed;
    }

    /*
    * 包装目标对象，为目标对象创建一个代理对象
    * */
    @Override
    public Object plugin(Object o) {
        System.out.println("MyfirstPlugin...plugin:mybatis将要包装的对象"+o);
        //我们可以借助Plugin的wrap方法来使用当前Interceptor包装我们目标对象
        Object wrap = Plugin.wrap(o, this);
        //返回当前target创建的动态代理
        return wrap;
    }

    /*
    *
    * 将插件注册时，properties属性注册进来，
    *
    * */
    @Override
    public void setProperties(Properties properties) {
        System.out.println("插件配置信息"+properties);
    }
}
