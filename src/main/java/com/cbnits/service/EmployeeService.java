package com.cbnits.service;

import com.cbnits.dto.EmployeeDto;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Long empId);

    List<EmployeeDto> getAllEmployees();

    EmployeeDto updateEmployee(Long empId, EmployeeDto updatedEmployee);

    String deleteEmployeeById(Long empId);

    Optional<EmployeeDto> getEmployeeByName(String name);

    Optional<EmployeeDto> findByEmail(String email);

    EmployeeDto createOrUpdateEmployee(EmployeeDto employeeDto);

}
