package com.xu.mybatis.mapper;

import com.xu.mybatis.bean.Employee;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

/**
 * Created by xj on 2018/10/10.
 */
public interface EmployeeMapper {
    //多条记录封装成map:map<Integer,Employee>
    @MapKey("id")//告诉mybatis封装这个map的时候使用哪个属性作为map的key
    public Map<Integer,Employee> getEmpBylastNameReturnMap(String lastName);

    //返回一条记录的map，key就是列名，值就是对应的值
    public Map<String,Object> getEmpByIdReturnMap(Integer id);

    public List<Employee> selectEmployees(String lastName);

//    @Select("select * from employee where id = #{id}")
    public Employee selectEmployee(Integer id);

    public Integer addEmployee(Employee employee);

    public boolean updateEmployee(Employee employee);

    public void deleteEmployee(Integer id);

    public List<Employee> getEmpByDeptId(Integer did);

    public List<Employee> getEmps();

}
