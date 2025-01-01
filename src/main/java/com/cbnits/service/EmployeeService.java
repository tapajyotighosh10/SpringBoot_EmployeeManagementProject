package com.cbnits.service;

import com.cbnits.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    EmployeeDto getEmployeeById(Long empId);
    List<EmployeeDto> getAllEmployees();
    EmployeeDto updateEmployee(Long empId, EmployeeDto updatedEmployee);
    String deleteEmployeeById(Long empId);
}
