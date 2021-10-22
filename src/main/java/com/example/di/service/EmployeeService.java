package com.example.di.service;

import com.example.di.EmployeeIdManger;
import com.example.di.NumberIdGenerator;

public class EmployeeService {

    private EmployeeIdManger employeeIdManger;

    public EmployeeService() {
        this.employeeIdManger = new EmployeeIdManger(new NumberIdGenerator());
    }
}
