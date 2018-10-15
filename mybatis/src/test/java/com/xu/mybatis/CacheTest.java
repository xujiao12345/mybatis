package com.xu.mybatis;

import com.xu.mybatis.bean.Employee;
import com.xu.mybatis.mapper.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.*;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by xj on 2018/10/12.
 */
public class CacheTest {

    /**
     * 两级缓存：
     * 一级缓存（本地缓存）:sqlsession级别的缓存，一级缓存是一直开启的
     *      与数据库同一次会话期间查到的数据会放在本地缓存中
     *      一级缓存的失效情况：
     *          1.没使用到一级缓存的情况,sqlsession不同的情况
     *          2.sqlsession相同，但查询的数据不一样的情况
     *          3.sqlsesion相同，两次查询中间进行了增删改（原因是这次增删改可能对这条数据有影响）
     *          4.sqlsession相同，手动清除了一级缓存
     *  二级缓存:基于namespce级别的缓存，一个namespace对应一个二级缓存
     *      工作机制：
     *       1.一个会话，查询一条数据，这个数据就会被放在当前会话的一级缓存中
     *       2.如果会话关闭，一级缓存的数据放到二级缓存中（注意，只有会话关闭时，查询的数据才能放在二级缓存中）
     *       3.不同namespace查出的数据会放在自己的缓存中（map）
     *       使用;
     *       1.开启全局缓存配置的规则（默认是开启的）
     *       2.去mapper.xml中配置使用二级缓存
     *          <cache eviction="LRU" readOnly="false"  flushInterval="60000" size=""></cache>
     *       3.po实现序列化接口
     *
     *    和缓存相关的设置/属性：
     *          1.<setting name="cacheEnabled" value="true"></setting>，false关闭缓存（二级缓存关闭，一级缓存一直可用的），
     *          2.每一个select标签都有useCache属性。值为false的时候，不使用二级缓存，一级缓存依然能够使用
     *          3.每一个增删改标签都会有：flushCache="true"。增删改执行完后就会清除一级二级缓存
     *          4.每一个查询标签也有flushCache="false",默认是false.如果flushCache=true，每次查询之后都会清空一级和二级缓存
     *    sqlsession.clearCash()清除当前session的一级缓存
     *
     *    mybatis使用缓存的顺序：先看二级缓存，再看一级缓存（因为要是不同的sqlSession查询时，需要看看二级缓存里有没有数据）
     *
     *
     */
    @org.junit.Test
    public void testFirstCache() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //这个sqlsession会自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = mapper.selectEmployee(1);

        //4.sqlsession清除缓存的情况
        sqlSession.clearCache();

        Employee employee2=mapper.selectEmployee(1);

        System.out.println(employee);
        System.out.println(employee2);
        System.out.println(employee==employee2);

        //1.查询使用的sqlsession不同
        SqlSession sqlSession2=sqlSessionFactory.openSession(true);
        EmployeeMapper mapper2 = sqlSession2.getMapper(EmployeeMapper.class);
        Employee employee3 = mapper2.selectEmployee(1);
        System.out.println(employee==employee3);

        //2.sqlseeion相同，查询条件不同
        Employee employee4=mapper.selectEmployee(2);
        System.out.println(employee==employee4);

        //3.sqlsesion相同，两次查询中间进行了增删改（原因是这次增删改可能对这条数据有影响）
        mapper.addEmployee(new Employee(null,"mi",1,"mi@com",2));
        Employee employee5=mapper.selectEmployee(1);
        System.out.println(employee==employee5);

        sqlSession.close();
        sqlSession2.close();
    }


    @org.junit.Test
    public void testSecondCache() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //这个sqlsession会自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        SqlSession sqlSession2 = sqlSessionFactory.openSession(true);
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = mapper.selectEmployee(1);
        System.out.println(employee);
        sqlSession.close();
        EmployeeMapper mapper1 = sqlSession2.getMapper(EmployeeMapper.class);
        Employee employee1 = mapper1.selectEmployee(1);
        System.out.println(employee1);
        System.out.println(employee==employee1);
        sqlSession2.close();

    }
}
