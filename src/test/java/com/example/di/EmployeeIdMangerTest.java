package com.example.di;

import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.exception.InvalidEmployeeStateException;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

//Mockito
@ExtendWith(MockitoExtension.class)
class EmployeeIdMangerTest {

    @Mock
    private IDGenerator idGeneratorMock;

    @InjectMocks
    private EmployeeIdManger idMangerUnderTest;

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

    // stack - LIFO vs queue FIFO
    @Test
    void whenEmployeeIdIsSet_thenIdGeneratorIsCalled() {
        String myTestId = "my random string id";
        when(idGeneratorMock.generate()).thenReturn(myTestId);
        Employee employee = new Employee();

        idMangerUnderTest.addId(employee);

        verify(idGeneratorMock, atMostOnce()).generate();
        org.assertj.core.api.Assertions.assertThat(employee.getId())
            .isEqualTo(myTestId);
    }
}