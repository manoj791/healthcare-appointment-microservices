package com.example.Appointment.Appointment_Service.Clients;

import com.example.Appointment.Appointment_Service.Dto.PatientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="PATIENT-SERVICE",url="${patient-service.url}")
public interface PatientClient {
    @GetMapping("/patients/{id}")
    PatientDto getPatient(@PathVariable("id") Long id);
}
