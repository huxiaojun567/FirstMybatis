package com.athu.mybatis.dao;

import com.athu.mybatis.bean.Empolyee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableInterceptor.ObjectReferenceFactory;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {

    public Empolyee selectEmployeeById(Integer id);
    public Integer addEmployee(Empolyee empolyee);
    public Empolyee selectEmployeeByIdAndNameParam(@Param("id") Integer id,@Param("lastName")String lastName);

    //查询一条数据用map封装
    public Map<String,Object> selectMapOne(Integer id);

    //查询多条数据用map封装
    @MapKey("lastName") //确定map中的key用哪个属性
    public Map<Object,Empolyee> selectMap(String lastName);

    //使用resultMap进行多表查询的方法
    public Empolyee selectEmployeeWithDepartment(Integer id);

    //使用resultMap进行多表查询的方法(分步查询)
    public Empolyee selectEmployeeWithDepartmentOfStep(Integer id);

    //通过部门id查找员工
    public List<Empolyee> selectEmployeeByDid(Integer dID);

}
