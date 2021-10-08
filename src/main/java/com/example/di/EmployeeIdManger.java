package com.example.di;

public class EmployeeIdManger {

    private IDGenerator idGenerator;

    public EmployeeIdManger(IDGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    public void addId(Employee employee) {
      employee.setId(idGenerator.generate().toString());
    }

}
