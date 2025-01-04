package com.cbnits.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
@Entity
@Table(name = "employees_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empId;
    @Column(name = "employee_name")
    private String empName;
//    @Column(name="email_id")
    private String email;
    @Column(name="mobile_no")
    private String mobileNo;
    private String salary;
    private String project;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

//    public Employee(Long empId, String empName, String email, String mobileNo, String salary, String project, LocalDateTime createdAt, LocalDateTime updatedAt) {
//        this.empId = empId;
//        this.empName = empName;
//        this.email = email;
//        this.mobileNo = mobileNo;
//        this.salary = salary;
//        this.project = project;
//        this.createdAt = createdAt;
//        this.updatedAt = updatedAt;
//    }
//
//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public LocalDateTime getUpdatedAt() {
//        return updatedAt;
//    }
//
//    public void setUpdatedAt(LocalDateTime updatedAt) {
//        this.updatedAt = updatedAt;
//    }
//
//    public Long getEmpId() {
//        return empId;
//    }
//
//    public void setEmpId(Long empId) {
//        this.empId = empId;
//    }
//
//    public String getEmpName() {
//        return empName;
//    }
//
//    public void setEmpName(String empName) {
//        this.empName = empName;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getMobileNo() {
//        return mobileNo;
//    }
//
//    public void setMobileNo(String mobileNo) {
//        this.mobileNo = mobileNo;
//    }
//
//    public String getSalary() {
//        return salary;
//    }
//
//    public void setSalary(String salary) {
//        this.salary = salary;
//    }
//
//    public String getProject() {
//        return project;
//    }
//
//    public void setProject(String project) {
//        this.project = project;
//    }
//
//
//    public Employee() {
//    }
//
//    @Override
//    public String toString() {
//        return "Employee{" +
//                "empId=" + empId +
//                ", empName='" + empName + '\'' +
//                ", email='" + email + '\'' +
//                ", mobileNo='" + mobileNo + '\'' +
//                ", salary='" + salary + '\'' +
//                ", project='" + project + '\'' +
//                ", createdAt=" + createdAt +
//                ", updatedAt=" + updatedAt +
//                '}';
//    }


}
