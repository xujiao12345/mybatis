package com.xu.mybatis.handler;

import com.xu.mybatis.bean.EmployeeStatus;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by xj on 2018/10/15.
 */
public class MyEnumSatusTypeHandler implements TypeHandler<EmployeeStatus> {
   /*
   * 定义当前数据如何保存到数据库中
   * */
    @Override
    public void setParameter(PreparedStatement ps, int i, EmployeeStatus employeeStatus, JdbcType jdbcType) throws SQLException {
        System.out.println("要保存的状态码："+employeeStatus.getCode().toString());
        ps.setString(i,employeeStatus.getCode().toString());
    }

    @Override
    public EmployeeStatus getResult(ResultSet rs, String columnName) throws SQLException {
        //根据从数据库中拿到枚举的状态码，返回一个枚举对象
        int code = rs.getInt(columnName);
        System.out.println("从数据库中获取的状态码："+code);
        EmployeeStatus empStatus= EmployeeStatus.getEmpStatusBycode(code);
        return empStatus;
    }

    @Override
    public EmployeeStatus getResult(ResultSet rs, int columnIndex) throws SQLException {
        int code = rs.getInt(columnIndex);
        System.out.println("从数据库中获取的状态码："+code);
        EmployeeStatus empStatus= EmployeeStatus.getEmpStatusBycode(code);
        return empStatus;
    }

    @Override
    public EmployeeStatus getResult(CallableStatement cs, int i) throws SQLException {
        int code = cs.getInt(i);
        System.out.println("从数据库中获取的状态码："+code);
        EmployeeStatus empStatus= EmployeeStatus.getEmpStatusBycode(code);
        return empStatus;
    }
}
