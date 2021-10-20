package com.example.di;

import com.example.exception.InvalidEmployeeStateException;

public class EmployeeIdManger {

    private IDGenerator idGenerator;

    public EmployeeIdManger(IDGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    public void addId(Employee employee) {
        if (null == employee) {
            throw new InvalidEmployeeStateException("employee cannot be null");
        }
        employee.setId(idGenerator.generate().toString());
    }

}
