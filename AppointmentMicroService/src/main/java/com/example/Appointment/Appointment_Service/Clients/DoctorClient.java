package com.example.Appointment.Appointment_Service.Clients;

import com.example.Appointment.Appointment_Service.Dto.DoctorDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="DOCTOR-SERVICE",url="${doctor-service.url}")
public interface DoctorClient {
    @GetMapping("doctors/{id}")
    DoctorDto getdoctorById(@PathVariable("id") Long id);
}
