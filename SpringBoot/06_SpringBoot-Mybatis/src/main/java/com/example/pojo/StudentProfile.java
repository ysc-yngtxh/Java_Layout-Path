package com.example.pojo;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class StudentProfile {
    private Integer id;
    private Integer studentId;
    private String address;
    private String emergencyContact;
    private String emergencyPhone;
    private LocalDate birthDate;
    private String politicalStatus;
    private String hobby;
    private String bio;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
