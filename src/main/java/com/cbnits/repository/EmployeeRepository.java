package com.cbnits.repository;

import com.cbnits.dto.EmployeeDto;
import com.cbnits.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Optional<Employee> findEmployeeByEmpName(String empName);

    Optional<Employee> findByEmail(String email);


    @Query("SELECT e FROM Employee e WHERE CAST(e.salary AS double) BETWEEN :min AND :max")
    List<Employee> findBySalaryBetween(@Param("min") double min, @Param("max") double max);

}
