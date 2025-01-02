package com.cbnits.service.Impl;

import com.cbnits.dto.EmployeeDto;
import com.cbnits.entity.Employee;
import com.cbnits.mapper.EmployeeMapper;
import com.cbnits.repository.EmployeeRepository;
import com.cbnits.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)  // This annotation is used to initialize mock objects
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;  // Mock the EmployeeRepository

    @Mock
    private EmployeeMapper employeeMapper;  // Mock the EmployeeMapper

    @InjectMocks
    private EmployeeServiceImpl employeeService;  // Inject mocks into the EmployeeServiceImpl

    private Employee employee;
    private Employee employee1;
    private Employee employee2;

    private EmployeeDto employeeDto;
    private EmployeeDto employeeDto1;
    private EmployeeDto employeeDto2;

    @BeforeEach
    public void setUp() {
        // Initialize data for testing
        employeeDto = new EmployeeDto();
        employeeDto.setEmpId(1L);
        employeeDto.setEmpName("John Doe");
        employeeDto.setEmail("john.doe@example.com");
        employeeDto.setMobileNo("1234567890");
        employeeDto.setSalary("50000");
        employeeDto.setProject("Project A");

        employee = new Employee();
        employee.setEmpId(1L);
        employee.setEmpName("John Doe");
        employee.setEmail("john.doe@example.com");
        employee.setMobileNo("1234567890");
        employee.setSalary("50000");
        employee.setProject("Project A");


        // Initializing Employee entities and EmployeeDto objects for testing
        employee1 = new Employee();
        employee1.setEmpId(1L);
        employee1.setEmpName("John Doe");
        employee1.setEmail("john.doe@example.com");
        employee1.setMobileNo("1234567890");
        employee1.setSalary("50000");
        employee1.setProject("Project A");

        employee2 = new Employee();
        employee2.setEmpId(2L);
        employee2.setEmpName("Jane Smith");
        employee2.setEmail("jane.smith@example.com");
        employee2.setMobileNo("0987654321");
        employee2.setSalary("60000");
        employee2.setProject("Project B");

        employeeDto1 = new EmployeeDto();
        employeeDto1.setEmpId(1L);
        employeeDto1.setEmpName("John Doe");
        employeeDto1.setEmail("john.doe@example.com");
        employeeDto1.setMobileNo("1234567890");
        employeeDto1.setSalary("50000");
        employeeDto1.setProject("Project A");

        employeeDto2 = new EmployeeDto();
        employeeDto2.setEmpId(2L);
        employeeDto2.setEmpName("Jane Smith");
        employeeDto2.setEmail("jane.smith@example.com");
        employeeDto2.setMobileNo("0987654321");
        employeeDto2.setSalary("60000");
        employeeDto2.setProject("Project B");
    }

    @Test
    public void testCreateEmployee_Success() {
        // Mock the behavior of the mapper and repository
        when(employeeMapper.convertDtoToEntity(any(EmployeeDto.class))).thenReturn(employee);
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        when(employeeMapper.convertEntityToDto(any(Employee.class))).thenReturn(employeeDto);

        // Call the method under test
        EmployeeDto createdEmployee = employeeService.createEmployee(employeeDto);

        // Assert the result
        assertNotNull(createdEmployee);
        assertEquals("John Doe", createdEmployee.getEmpName());
        assertEquals("john.doe@example.com", createdEmployee.getEmail());
        assertEquals("50000", createdEmployee.getSalary());
        assertEquals("Project A", createdEmployee.getProject());

        // Verify that the repository save method was called once
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    public void testCreateEmployee_Failure() {
        // Test behavior if there's an exception, for example, employee creation failure due to a repository error.
        when(employeeMapper.convertDtoToEntity(any(EmployeeDto.class))).thenReturn(employee);
        when(employeeRepository.save(any(Employee.class))).thenThrow(new RuntimeException("Database error"));

        // Call the method under test and assert that it throws a RuntimeException
        assertThrows(RuntimeException.class, () -> {
            employeeService.createEmployee(employeeDto);
        });

        // Verify that the repository save method was called once
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    public void testGetEmployeeById_Success() {
        // Arrange: Mocking the behavior
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee)); // Simulating the employee is found
        when(employeeMapper.convertEntityToDto(employee)).thenReturn(employeeDto); // Mapping entity to DTO

        // Act: Calling the method to be tested
        EmployeeDto result = employeeService.getEmployeeById(1L);

        // Assert: Verifying the returned DTO
        assertNotNull(result);  // Ensure the result is not null
        assertEquals("John Doe", result.getEmpName());  // Check if employee name matches
        assertEquals("john.doe@example.com", result.getEmail());  // Verify email
        assertEquals("50000", result.getSalary());  // Ensure salary matches
        assertEquals("Project A", result.getProject());  // Check the project name

        // Verify that findById was called once with the correct argument
        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeMapper, times(1)).convertEntityToDto(employee); // Verify the mapper was called
    }

    @Test
    public void testGetEmployeeById_NotFound() {
        // Arrange: Mocking the behavior to simulate that the employee is not found
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty()); // Simulating the employee is not found

        // Act & Assert: Calling the method and verifying that the exception is thrown
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.getEmployeeById(1L); // Call method with non-existing employee ID
        });

        // Verify that the exception message is correct
        assertEquals("Employee is not exit with this given id1", exception.getMessage());

        // Verify that findById was called once with the correct argument
        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeMapper, times(0)).convertEntityToDto(any()); // No conversion should happen
    }

    @Test
    public void testGetAllEmployees_Success() {
        // Arrange: Mocking the behavior
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee1, employee2)); // Simulate fetching two employees
        when(employeeMapper.convertEntityToDto(employee1)).thenReturn(employeeDto1); // Map Employee 1 to EmployeeDto
        when(employeeMapper.convertEntityToDto(employee2)).thenReturn(employeeDto2); // Map Employee 2 to EmployeeDto

        // Act: Calling the method to be tested
        List<EmployeeDto> result = employeeService.getAllEmployees();

        // Assert: Verifying the returned list of EmployeeDto
        assertNotNull(result);  // Ensure the result is not null
        assertEquals(2, result.size());  // Ensure there are 2 EmployeeDto objects
        assertEquals("John Doe", result.get(0).getEmpName());  // Check if the first employee's name matches
        assertEquals("Jane Smith", result.get(1).getEmpName());  // Check if the second employee's name matches

        // Verify that findAll was called once
        verify(employeeRepository, times(1)).findAll();
        verify(employeeMapper, times(1)).convertEntityToDto(employee1); // Verify conversion for Employee 1
        verify(employeeMapper, times(1)).convertEntityToDto(employee2); // Verify conversion for Employee 2
    }

    @Test
    public void testGetAllEmployees_EmptyList() {
        // Arrange: Mocking the behavior
        when(employeeRepository.findAll()).thenReturn(Arrays.asList()); // Simulating an empty list of employees

        // Act: Calling the method to be tested
        List<EmployeeDto> result = employeeService.getAllEmployees();

        // Assert: Verifying the returned list is empty
        assertNotNull(result);  // Ensure the result is not null
        assertTrue(result.isEmpty());  // Ensure the list is empty

        // Verify that findAll was called once
        verify(employeeRepository, times(1)).findAll();
        verify(employeeMapper, times(0)).convertEntityToDto(any()); // No conversion should happen
    }
}
