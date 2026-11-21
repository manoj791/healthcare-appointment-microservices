package com.example.Appointment.Appointment_Service;

import com.example.Appointment.Appointment_Service.Dto.DoctorDto;
import com.example.Appointment.Appointment_Service.Dto.PatientDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long patientId;
    private Long doctorId;
    private String slot;
    private String status;
 @Transient
 private DoctorDto doctor;

 @Transient
 private PatientDto patient;
}
