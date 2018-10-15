package com.xu.mybatis.mapper;

import com.xu.mybatis.bean.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xj on 2018/10/12.
 */
public interface EmployeeDynamicSqlMapper {

    public List<Employee> getEmpsByDyncSql(Employee employee);

    public List<Employee> getEmpsByTrim(Employee employee);

    public List<Employee> getEmpsByChoose(Employee employee);

    public void updateEmp(Employee employee);

    public List<Employee> getEmpsByConditionForeach(List<Integer> ids);

    public void addEmps(@Param("emps") List<Employee> emps);

    public List<Employee> getEmpsTestInnerParameter(Employee employee);
}
