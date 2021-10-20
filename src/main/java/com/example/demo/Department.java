package com.example.demo;

import com.example.di.Employee;

import java.util.ArrayList;

public class Department {  //POJO Class
    private int departmentNumber;
    private String departmentName;
    private String departmentLocation;

    ArrayList<Employee> allEmployee;

    public int getDepartmentNumber() {
        return departmentNumber;
    }

    public void setDepartmentNumber(int departmentNumber) {
        this.departmentNumber = departmentNumber;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentLocation() {
        return departmentLocation;
    }

    public void setDepartmentLocation(String departmentLocation) {
        this.departmentLocation = departmentLocation;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentNumber=" + departmentNumber +
                ", departmentName='" + departmentName + '\'' +
                ", departmentLocation='" + departmentLocation + '\'' +
                '}';
    }
}
