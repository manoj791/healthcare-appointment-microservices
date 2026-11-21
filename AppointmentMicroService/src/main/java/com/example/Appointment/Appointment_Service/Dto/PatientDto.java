package com.example.Appointment.Appointment_Service.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class PatientDto {
    private Long id;
    private String name;
    private int age;
    private String disease;
    private String email;
    private String phone;
}
