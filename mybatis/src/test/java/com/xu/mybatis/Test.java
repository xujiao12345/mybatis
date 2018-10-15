package com.xu.mybatis;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xu.mybatis.bean.Department;
import com.xu.mybatis.bean.Employee;
import com.xu.mybatis.bean.EmployeeStatus;
import com.xu.mybatis.mapper.DepartmentMapper;
import com.xu.mybatis.mapper.EmployeeDynamicSqlMapper;
import com.xu.mybatis.mapper.EmployeeMapper;
import com.xu.mybatis.mapper.EmployeeResultMapMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by xj on 2018/10/10.
 */
public class Test {

    /*
    * mybatis数据库操作的方法一
    * 1.根据mybatis的全局配置文件创建sqlSessionFactory
    * 2.sqlSessionFactory.openSession拿到session
    * 3.session.selectOne("namespace.id找到单个mapper配置文件具体的sql语句",sql语句的参数)
    * */
    @org.junit.Test
    public void test01() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        System.out.println(sqlSessionFactory);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Employee employee = (Employee) sqlSession.selectOne("com.xu.mybatis.mapperxml.mapper.EmployeeMapper.selectEmployee", 1);
        System.out.println(employee);
    }

    /*
    * mybatis数据库操作的方法二：
    * 得到sqlsesion过后获得mapper对象
    * mapper对象的全类名和单个配置文件的mapper的namespace相同。方法名和id相同，这样就能够通过代理找到配置文件中的sql语句。
    * */
    @org.junit.Test
    public void test02() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        System.out.println(sqlSessionFactory);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = mapper.selectEmployee(1);
        System.out.println(employee);


    }

    /*
    * mybatis支持以下返回值：
    * int,long,boolean
    * */
    @org.junit.Test
    public void test03() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //这个sqlsession不会自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
//        Employee employee = new Employee(null, "xiaohong", 1, "xiaohong@aliyun.com", 2);
//        mapper.addEmployee(employee);
//        Employee employee = new Employee(2, "xiaom", 1, "xiaohong@aliyun.com", 2);
//        boolean b = mapper.updateEmployee(employee);
//        System.out.println(b);

        List<Employee> employeeList = mapper.selectEmployees("%a%");
        for (Employee e:employeeList) {
            System.out.println(e);
        }

//        mapper.deleteEmployee(3);
        //手动提交数据
        sqlSession.commit();
        sqlSession.close();
    }

    @org.junit.Test
    public void test04() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //这个sqlsession不会自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
//        Map<String, Object> map = mapper.getEmpByIdReturnMap(1);
        Map<Integer, Employee> map = mapper.getEmpBylastNameReturnMap("%a%");
        System.out.println(map);
    }

    @org.junit.Test
    public void test05() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //这个sqlsession不会自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeResultMapMapper mapper = sqlSession.getMapper(EmployeeResultMapMapper.class);
//        Employee employee = mapper.getEmployeeById(1);
//        System.out.println(employee);
//        Employee empAndDept = mapper.getEmpAndDept(1);
//        System.out.println(empAndDept);
        Employee empByIdStep = mapper.getEmpByIdStep(1);
        System.out.println(empByIdStep);
        System.out.println(empByIdStep.getDepartment());
    }

    @org.junit.Test
    public void test06() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //这个sqlsession不会自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
       /* Department deptByIdPlus = mapper.getDeptByIdPlus(1);
        System.out.println(deptByIdPlus);
        System.out.println(deptByIdPlus.getEmps());*/
        Department deptByIdStep = mapper.getDeptByIdStep(2);
        System.out.println(deptByIdStep);
        System.out.println(deptByIdStep.getEmps());

    }

    @org.junit.Test
    public void test07() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //这个sqlsession不会自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeDynamicSqlMapper mapper = sqlSession.getMapper(EmployeeDynamicSqlMapper.class);
        Employee employee=new Employee(null,"%a%",null,null,null);
//        List<Employee> empsByDyncSql = mapper.getEmpsByDyncSql(employee);
//        List<Employee> empsByDyncSql = mapper.getEmpsByTrim(employee);
        List<Employee> empsByDyncSql = mapper.getEmpsByChoose(employee);
        for (Employee e:empsByDyncSql) {
            System.out.println(e);
        }

    }

    @org.junit.Test
    public void test08() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //这个sqlsession会自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        EmployeeDynamicSqlMapper mapper = sqlSession.getMapper(EmployeeDynamicSqlMapper.class);
        Employee employee=new Employee(1,"admin",null,null,null);
//        mapper.updateEmp(employee);
        List<Employee> empsByConditionForeach = mapper.getEmpsByConditionForeach(Arrays.asList(1, 2));
        for (Employee e:empsByConditionForeach) {
            System.out.println(e);
        }
        sqlSession.close();
    }

    @org.junit.Test
    public  void testBatchSave() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //这个sqlsession会自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        EmployeeDynamicSqlMapper mapper = sqlSession.getMapper(EmployeeDynamicSqlMapper.class);
        Employee employee=new Employee(null,"xiaomi",0,"xiaohua@qq.com",1);
        Employee employee2=new Employee(null,"xiaohua",0,"xiaohua@qq.com",2);

        List<Employee> emps=new ArrayList<>();
        emps.add(employee);
        emps.add(employee2);
        mapper.addEmps(emps);
        sqlSession.close();
    }

    @org.junit.Test
    public  void testInnerParameter() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //这个sqlsession会自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        EmployeeDynamicSqlMapper mapper = sqlSession.getMapper(EmployeeDynamicSqlMapper.class);
        List<Employee> empsTestInnerParameter = mapper.getEmpsTestInnerParameter(new Employee(1, "%a%", null, null, null));

        for (Employee e:empsTestInnerParameter) {
            System.out.println(e);
        }
        sqlSession.close();
    }

    @org.junit.Test
    public void test09() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //这个sqlsession会自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession();
//指定批量sqlsession批量操作sql语句
//        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        //使用PageHelper进行分页
        Page<Object> page = PageHelper.startPage(4, 1);
        List<Employee> emps = mapper.getEmps();
        //pageinfo传入要连续显示多少页
        PageInfo<Employee> info=new PageInfo<>(emps,5);
        for (Employee employee:emps) {
            System.out.println(employee);
        }
        System.out.println("当前页码"+page.getPageNum());
        System.out.println("每页的大小"+page.getPageSize());
        System.out.println("总页数"+page.getPages());
        System.out.println("总记录数"+page.getTotal());
        //连续显示的页码
        System.out.println("连续显示的页码");
        int[] navigatepageNums = info.getNavigatepageNums();
        for (int i=0;i<navigatepageNums.length;i++){
            System.out.println(navigatepageNums[i]);
        }
    }

    @org.junit.Test
    public void testEnumUse(){
        EmployeeStatus login=EmployeeStatus.LOGIN;
        System.out.println("枚举的索引："+login.ordinal());
        System.out.println("枚举的名字："+login.name());
        System.out.println("枚举的自定义的状态码:"+login.getCode());
        System.out.println("枚举的提示消息："+login.getMsg());
    }


    @org.junit.Test
    public  void testEnum() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //这个sqlsession会自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee=new Employee(null,"test_enum",1,"enum@jfdh.com",2);
        Integer integer = mapper.addEmployee(employee);
        sqlSession.commit();
        System.out.println("保存成功："+employee.getId());
        sqlSession.close();
    }

}
