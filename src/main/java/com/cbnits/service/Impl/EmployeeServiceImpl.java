package com.cbnits.service.Impl;


import com.cbnits.dto.EmployeeDto;
import com.cbnits.entity.Employee;
import com.cbnits.mapper.EmployeeMapper;
import com.cbnits.repository.EmployeeRepository;
import com.cbnits.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

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

//    private Employee convertDtoToEntity(EmployeeDto employeeDto){
//        Employee employee = this.modelMapper.map(employeeDto,Employee.class);
//        return employee;
//
//    }
//
//    private EmployeeDto convertEntityToDto(Employee employee) {
//
//        EmployeeDto employeeDto = this.modelMapper.map(employee,EmployeeDto.class);
//        return employeeDto;
//        }


    }
