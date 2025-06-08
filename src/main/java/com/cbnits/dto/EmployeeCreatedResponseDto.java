package com.cbnits.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeCreatedResponseDto<T> {
    private int status;
    private String message;
    private T data;
    private LocalDateTime timeStamp;
}
