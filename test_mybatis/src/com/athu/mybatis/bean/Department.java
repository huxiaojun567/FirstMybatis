package com.athu.mybatis.bean;

import java.util.List;

public class Department {
    private Integer id;
    private String departmentName;
    private List<Empolyee> empolyees;

    public Department() {
    }

    public Department(Integer id, String departmentName,List<Empolyee> empolyees) {
        this.id = id;
        this.departmentName = departmentName;
        this.empolyees = empolyees;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<Empolyee> getEmpolyee() {
        return empolyees;
    }

    public void setEmpolyees(List<Empolyee> empolyees) {
        this.empolyees = empolyees;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                ", empolyees=" + empolyees +
                '}';
    }
}
