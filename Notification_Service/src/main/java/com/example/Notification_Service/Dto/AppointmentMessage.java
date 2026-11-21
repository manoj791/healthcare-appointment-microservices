package com.example.Notification_Service.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentMessage {
    private Long appointmentId;
    private Long patientId;
    private Long doctorId;
    private String status;
}
