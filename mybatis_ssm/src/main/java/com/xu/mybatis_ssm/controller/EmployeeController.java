package com.xu.mybatis_ssm.controller;

import com.xu.mybatis_ssm.bean.Employee;
import com.xu.mybatis_ssm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * Created by xj on 2018/10/12.
 */
@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @RequestMapping("/getemps")
    public String emps(Map<String,Object> map){
        List<Employee> emps = employeeService.getEmps();
        map.put("emps",emps);
        return "list";
    }
}
