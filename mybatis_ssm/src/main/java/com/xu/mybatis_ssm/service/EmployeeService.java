package com.xu.mybatis_ssm.service;

import com.xu.mybatis_ssm.bean.Employee;
import com.xu.mybatis_ssm.mapperDao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xj on 2018/10/12.
 */
@Service
public class EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private SqlSession sqlSession;//这个applicationcontext.xml配置文件中配置的可以执行批量操作sql的sqlsession

    public List<Employee> getEmps(){
        return employeeMapper.getEmps();
    }


}
