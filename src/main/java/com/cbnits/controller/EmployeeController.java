package com.cbnits.controller;

import com.cbnits.dto.EmployeeDto;
import com.cbnits.service.EmployeeService;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cbnits")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/add")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        System.out.println("Received EmployeeDto: " + employeeDto);
        EmployeeDto savedEmp = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmp, HttpStatus.CREATED);
    }

    @GetMapping("/search/{empId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("empId") Long empId){
        EmployeeDto savedEmp = employeeService.getEmployeeById(empId);
        return ResponseEntity.ok(savedEmp);
    }
}
