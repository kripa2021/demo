package com.example.di;

import com.example.exception.InvalidEmployeeStateException;

//SOLID -> open/closed principle
public class EmployeeIdManger {

    private IDGenerator idGenerator;

    public EmployeeIdManger(IDGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    // testing in isolation
    public void addId(Employee employee) {
        if (null == employee) {
            throw new InvalidEmployeeStateException("employee cannot be null");
        }
        employee.setId(idGenerator.generate().toString());
    }

}
