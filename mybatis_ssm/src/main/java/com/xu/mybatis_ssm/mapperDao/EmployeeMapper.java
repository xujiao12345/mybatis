package com.xu.mybatis_ssm.mapperDao;


import com.xu.mybatis_ssm.bean.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xj on 2018/10/10.
 */
@Repository
public interface EmployeeMapper {

    public Employee selectEmployee(Integer id);

    public List<Employee> getEmps();
}
