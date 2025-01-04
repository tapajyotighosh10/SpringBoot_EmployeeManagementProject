package com.cbnits.service.Impl;


import com.cbnits.dto.EmployeeDto;
import com.cbnits.entity.Employee;
import com.cbnits.exception.ResourceNotFoundException;
import com.cbnits.mapper.EmployeeMapper;
import com.cbnits.repository.EmployeeRepository;
import com.cbnits.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        // Map DTO to entity
        Employee employee = employeeMapper.convertDtoToEntity(employeeDto);
        System.out.println("Mapped Employee entity: " + employee);

        // Save the entity to the database
        Employee savedEmployee = employeeRepository.save(employee);
        System.out.println("Saved Employee: " + savedEmployee); // Check if empId and other fields are populated

        // Map saved entity back to DTO
        EmployeeDto savedEmployeeDto = employeeMapper.convertEntityToDto(savedEmployee);
        System.out.println("Mapped back to DTO: " + savedEmployeeDto);
        return savedEmployeeDto;
    }

    @Override
    public EmployeeDto getEmployeeById(Long empId) {
     Employee employee=   employeeRepository.findById(empId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Employee is not exit with this given id"+empId));
        return employeeMapper.convertEntityToDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> empList= employeeRepository.findAll();
        return empList.stream().map((emp) -> employeeMapper.convertEntityToDto(emp))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long empId, EmployeeDto updatedEmployee) {
        Employee employee=   employeeRepository.findById(empId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Employee is not exit with this given id"+empId));

        // Update only if the value is non-null
        if (updatedEmployee.getEmpName() != null) {
            employee.setEmpName(updatedEmployee.getEmpName());
        }
        if (updatedEmployee.getMobileNo() != null) {
            employee.setMobileNo(updatedEmployee.getMobileNo());
        }
        if (updatedEmployee.getEmail() != null) {
            employee.setEmail(updatedEmployee.getEmail());
        }
        if (updatedEmployee.getSalary() != null) {
            employee.setSalary(updatedEmployee.getSalary());
        }
        if (updatedEmployee.getProject() != null) {
            employee.setProject(updatedEmployee.getProject());
        }

        Employee updatedEmployeeObj =  employeeRepository.save(employee);

        return employeeMapper.convertEntityToDto(updatedEmployeeObj);
    }

    @Override
    public String deleteEmployeeById(Long empId) {
        Employee employee=   employeeRepository.findById(empId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Employee is not exit with this given id: "+empId));
                employeeRepository.deleteById(empId);
                return "Employee deleted with id: "+empId;
    }
}
