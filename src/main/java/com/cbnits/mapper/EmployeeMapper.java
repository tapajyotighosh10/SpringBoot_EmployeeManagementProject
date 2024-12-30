package com.cbnits.mapper;

import com.cbnits.dto.EmployeeDto;
import com.cbnits.entity.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    @Autowired
    private ModelMapper modelMapper;

    public Employee convertDtoToEntity(EmployeeDto employeeDto){
        Employee employee = this.modelMapper.map(employeeDto,Employee.class);
        return employee;

    }

    public EmployeeDto convertEntityToDto(Employee employee) {

        EmployeeDto employeeDto = this.modelMapper.map(employee,EmployeeDto.class);
        return employeeDto;
    }

}
