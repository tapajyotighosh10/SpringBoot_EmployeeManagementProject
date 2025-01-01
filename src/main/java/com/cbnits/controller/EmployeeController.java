package com.cbnits.controller;

import com.cbnits.dto.EmployeeDto;
import com.cbnits.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
// Build Rest API for get employee by ID
    @GetMapping("/search/{empId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("empId") Long empId){
        EmployeeDto savedEmp = employeeService.getEmployeeById(empId);
        return ResponseEntity.ok(savedEmp);
    }

    // Build Get all employees REST API

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        List<EmployeeDto> allEmp=employeeService.getAllEmployees();
        return ResponseEntity.ok(allEmp);
    }

    // Build update employee REST API

    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long empId,@RequestBody EmployeeDto updatedEmployee){
      EmployeeDto empDto =  employeeService.updateEmployee(empId,updatedEmployee);
     return ResponseEntity.ok(empDto);
    }

    @DeleteMapping("/delete/{empId}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable Long empId) {
        // Call the service method to delete the employee
        String resultMessage = employeeService.deleteEmployeeById(empId);

        // Return response with a message
        return ResponseEntity.ok(resultMessage);  // 200 OK response with success message
    }
}
