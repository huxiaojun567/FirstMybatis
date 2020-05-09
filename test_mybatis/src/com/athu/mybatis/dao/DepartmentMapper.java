package com.athu.mybatis.dao;

import com.athu.mybatis.bean.Department;

public interface DepartmentMapper {

    public Department selectDeptById(Integer id);

    //通过部门id查找员工信息（利用 resultMap（collection） 多表查询(分步) List集合）
    public Department selectDeptByIdStep(Integer id);

}
