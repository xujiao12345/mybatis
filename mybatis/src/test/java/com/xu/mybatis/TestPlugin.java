package com.xu.mybatis;

import com.xu.mybatis.bean.Employee;
import com.xu.mybatis.mapper.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by xj on 2018/10/14.
 */
public class TestPlugin {

    /*
    * 插件原理
    * 四大对象创建的时候
    * 1.每个创建出来的队形不是直接返回的，而是
    * parameterHandler = (ParameterHandler)this.interceptorChain.pluginAll(parameterHandler);
    * 2.获取到所有的Interceptor(拦截器)（插件需要实现的接口），
    *   调用interceptor.plugin(target)；返回target包装后的对象
    * 3.插件机制：我们可以使用目标插件创建一个代理对象；与aop(面向切面)的原理类似
    *       我们的插件可以成为四大队向创建代理对象；代理对象就可以拦截到四大对象的每一个执行
    * public Object pluginAll(Object target) {
        Interceptor interceptor;
        for(Iterator var2 = this.interceptors.iterator(); var2.hasNext(); target = interceptor.plugin(target)) {
            interceptor = (Interceptor)var2.next();
        }

        return target;
    }

    * */

    /*
    * 插件编写
    * 1.编写一个实现Interceptor的实现类
    * 2.使用Intercepts完成插件的签名
    * 3，将写好的插件注册到mybatis的全局配置文件
    * ` <plugins>
        <plugin interceptor="com.xu.mybatis_ssm.mapperDao.MyfirstPlugin">
            <property name="username" value="root"></property>
            <property name="password" value="123456"></property>
        </plugin>
    </plugins>
    *
    *
    *
    * */
    @Test
    public void testPlugin() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //这个sqlsession会自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
//        Map<String, Object> map = mapper.getEmpByIdReturnMap(1);
        Employee employee = mapper.selectEmployee(1);
        System.out.println(employee);
    }
}
