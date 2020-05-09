package com.athu.mybatis.dao;

import com.athu.mybatis.bean.Empolyee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapperDynamicSql {

    public Empolyee selectEmployeeByDynamicSql(Empolyee empolyee);

    public Integer updateEmployeeByDynamicSql(Empolyee empolyee);

    public List<Empolyee> selectManyEmployeeByDynamicSql(@Param("ids") List<Integer> ids);

}
