package com.xu.mybatis.mapper;

import com.xu.mybatis.bean.Department;

/**
 * Created by xj on 2018/10/11.
 */
public interface DepartmentMapper {

    public Department getDeptById(Integer id);

    public Department  getDeptByIdPlus(Integer id);

    public Department getDeptByIdStep(Integer id);
}
