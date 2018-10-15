package com.xu.mybatis.mapper;

import com.xu.mybatis.bean.Employee;

/**
 * Created by xj on 2018/10/11.
 */
public interface EmployeeResultMapMapper {

    public Employee getEmployeeById(Integer id);

    public Employee getEmpAndDept(Integer id);

    public Employee getEmpByIdStep(Integer id);
}
