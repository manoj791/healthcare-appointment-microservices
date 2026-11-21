package com.example.Appointment.Appointment_Service.Dto;

import lombok.Data;

import java.util.List;

@Data
public class DoctorDto {
    private long id;
    private String name;
    private String specialization;
    private List<String> availableSlots;
}
