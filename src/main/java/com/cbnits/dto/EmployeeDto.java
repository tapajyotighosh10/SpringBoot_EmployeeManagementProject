package com.cbnits.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private Long empId;
    private String empName;
    private String email;
    private String mobileNo;
    private String salary;
    private String project;

    private LocalDateTime createdAt; // Include in DTO
    private LocalDateTime updatedAt; // Include in DTO

}
