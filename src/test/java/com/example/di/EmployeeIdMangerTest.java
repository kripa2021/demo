package com.example.di;

import com.example.exception.InvalidEmployeeStateException;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;

class EmployeeIdMangerTest {

    private IDGenerator idGenerator = new UUIDGenerator();

    private EmployeeIdManger idMangerUnderTest = new EmployeeIdManger(idGenerator);

    @Test // GIVEN WHEN THEN - gherkin steps
    void whenIdManagerAddId_thenIdIsNotNull() {
        Employee emp = new Employee();
        idMangerUnderTest.addId(emp);

        org.assertj.core.api.Assertions.assertThat(emp.getId())
            .isNotNull();
    }

    @Test
    void whenIdManagerAddId_thenIdIsUUIDFormat() {
        Employee emp = new Employee();
        idMangerUnderTest.addId(emp);

        org.assertj.core.api.Assertions.assertThat(emp.getId())
            .isInstanceOf(String.class);
    }

    @Test
    void whenEmployeeIsNull_thenCustomException(){
        org.assertj.core.api.Assertions.assertThatThrownBy(
            () -> idMangerUnderTest.addId(null)
        ).isInstanceOf(InvalidEmployeeStateException.class)
        .hasMessage("employee cannot be null");
    }
}