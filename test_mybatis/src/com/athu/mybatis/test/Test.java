package com.athu.mybatis.test;
import com.athu.mybatis.bean.Department;
import com.athu.mybatis.bean.Empolyee;
import com.athu.mybatis.dao.DepartmentMapper;
import com.athu.mybatis.dao.EmployeeMapper;
import com.athu.mybatis.dao.EmployeeMapperDynamicSql;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Test {


    public static SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "resource/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }

    @org.junit.Test
    public void test01() throws Exception{

        String resource = "resource/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            Empolyee empolyee = sqlSession.selectOne("com.athu.mybatis.EmployeeMapper.selectEmployee", 1);
            System.out.println(empolyee);
        }finally {
            sqlSession.close();
        }
    }


    /**
     * mapper接口开发的测试类
     */
    @org.junit.Test
    public void test02() throws IOException {

        String resource = "resource/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Empolyee empolyee = mapper.selectEmployeeById(2);
            System.out.println(empolyee);
        }finally {
            sqlSession.close();
        }

    }

    /**
     * 获取自增主键的方法示例
     * @throws IOException
     */
    @org.junit.Test
    public void test03() throws IOException {

        String resource = "resource/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Empolyee empolyee = new Empolyee(null,"Tim","Tim@athu.com","男");
            Integer i = mapper.addEmployee(empolyee);
            sqlSession.commit();
            System.out.println(i);
            System.out.println(empolyee);

        }finally {
            sqlSession.close();
        }
    }

    /**
     * debug演示使用@Param()的源码过程
     * @throws Exception
     */
    @org.junit.Test
    public void test04() throws Exception{

        String resource = "resource/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Empolyee empolyee = mapper.selectEmployeeByIdAndNameParam(5,"Tim");
            System.out.println(empolyee);
        }finally {
            sqlSession.close();
        }
    }

    /**
     * 保存数据在map中的测试方法
     * @throws IOException
     */
    @org.junit.Test
    public void test05() throws IOException {

        String resource = "resource/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Map<Object, Empolyee> objectEmpolyeeMap = mapper.selectMap("%i%");
            System.out.println(objectEmpolyeeMap);
        }finally {
            sqlSession.close();
        }
    }

    /**
     * 利用resultMap实现多表查询的测试方法
     * @throws IOException
     */
    @org.junit.Test
    public void test06() throws IOException {

        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Empolyee empolyee = mapper.selectEmployeeWithDepartment(3);
            System.out.println(empolyee);
        }finally {
            sqlSession.close();
        }
    }

    /**
     * 利用resultMap实现多表查询的测试方法
     * @throws IOException
     */
    @org.junit.Test
    public void test07() throws IOException {

        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Empolyee empolyee = mapper.selectEmployeeWithDepartmentOfStep(3);
            System.out.println(empolyee);
        }finally {
            sqlSession.close();
        }
    }

    /**
     * 利用resultMap实现多表查询(其中包含有list集合)的测试方法
     * @throws IOException
     */
    @org.junit.Test
    public void test08() throws IOException {

        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
            Department department = mapper.selectDeptByIdStep(2);
            System.out.println(department.getDepartmentName());
        }finally {
            sqlSession.close();
        }
    }

    /**
     * 动态sql的测试方法
     * @throws IOException
     */
    @org.junit.Test
    public void DynamicSqlTest() throws IOException {

        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            EmployeeMapperDynamicSql mapper = sqlSession.getMapper(EmployeeMapperDynamicSql.class);
            Empolyee empolyee = new Empolyee(1,"XFN",null,null);
            //测试trim
            //Empolyee empolyee1 = mapper.selectEmployeeByDynamicSql(empolyee);

            //测试set
            //Integer i = mapper.updateEmployeeByDynamicSql(empolyee);

            List<Empolyee> empolyees = mapper.selectManyEmployeeByDynamicSql(Arrays.asList(1,2,3,4));
            for (Empolyee emp:empolyees) {
                System.out.println(emp);
            }
        }finally {
            sqlSession.close();
        }
    }

}
