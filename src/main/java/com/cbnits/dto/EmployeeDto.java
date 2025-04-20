package com.cbnits.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @NotNull(message = "Name cannot be null")
    private String empName;
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;
    @Pattern(regexp = "\\d{10}", message = "Mobile number must be 10 digits")
    private String mobileNo;
    @NotBlank(message = "Salary is required")
    private String salary;
    @NotBlank(message = "Project is required")
    private String project;

    private LocalDateTime createdAt; // Include in DTO
    private LocalDateTime updatedAt; // Include in DTO

}
