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
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    private final EmployeeRepository employeeRepository;

    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        // Map DTO to entity
        Employee employee = employeeMapper.convertDtoToEntity(employeeDto);
        // Save the entity to the database
        Employee savedEmployee = employeeRepository.save(employee);

        // Map saved entity back to DTO
        return employeeMapper.convertEntityToDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long empId) {
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee is not exit with this given id" + empId));
        return employeeMapper.convertEntityToDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> empList = employeeRepository.findAll();
        return empList.stream().map(emp -> employeeMapper.convertEntityToDto(emp))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long empId, EmployeeDto updatedEmployee) {
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee is not exit with this given id" + empId));

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

        Employee updatedEmployeeObj = employeeRepository.save(employee);

        return employeeMapper.convertEntityToDto(updatedEmployeeObj);
    }

    @Override
    public String deleteEmployeeById(Long empId) {
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee is not exit with this given id: " + empId));
        employeeRepository.deleteById(empId);
        return "Employee deleted with id: " + empId;
    }

    @Override
    public Optional<EmployeeDto> getEmployeeByName(String name) {
        return employeeRepository.findEmployeeByEmpName(name)
                .map(employeeMapper::convertEntityToDto); // converts Employee → EmployeeDto
    }

    @Override
    public Optional<EmployeeDto> findByEmail(String email) {
        return employeeRepository.findByEmail(email)
                .map(employeeMapper::convertEntityToDto);
    }


    @Override
    public EmployeeDto createOrUpdateEmployee(EmployeeDto employeeDto) {
        Optional<Employee> existingEmployee = employeeRepository.findByEmail(employeeDto.getEmail());

        Employee employee = existingEmployee.orElseGet(Employee::new);

        // If it's a new employee, set the email to establish uniqueness
        employee.setEmail(employeeDto.getEmail());

        // Set/update other fields
        employee.setEmpName(employeeDto.getEmpName());
        employee.setProject(employeeDto.getProject());
        employee.setSalary(employeeDto.getSalary());
        employee.setMobileNo(employeeDto.getMobileNo());

        Employee savedEmployee = employeeRepository.save(employee);
        return employeeMapper.convertEntityToDto(savedEmployee);
    }

    @Override
    public List<EmployeeDto> getEmployeeFromSalaryRange(String min, String max) {

        double minSalary = Double.parseDouble(min);
        double maxSalary = Double.parseDouble(max);

        List<Employee> empRange = employeeRepository.findBySalaryBetween(minSalary, maxSalary);

        return empRange.stream()
                .map(employeeMapper::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, List<EmployeeDto>> getEmployeesGroupedByProject() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::convertEntityToDto)
                .collect(Collectors.groupingBy(EmployeeDto::getProject));
    }

    @Override
    public List<EmployeeDto> saveAllEmployees(List<EmployeeDto> dtoList) {

        List<Employee> entities = dtoList.stream()
                .map(employeeMapper::convertDtoToEntity)
                .toList();

        List<Employee> saved = employeeRepository.saveAll(entities);

        return saved.stream()
                .map(employeeMapper::convertEntityToDto)
                .toList();
    }

}
