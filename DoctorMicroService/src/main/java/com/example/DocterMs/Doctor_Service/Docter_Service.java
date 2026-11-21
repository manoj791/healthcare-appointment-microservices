package com.example.DocterMs.Doctor_Service;

import org.springframework.http.HttpStatusCode;

import java.util.List;

public interface Docter_Service {
    void createDoctor(Doctor doctor);
    List<Doctor> getallDoctors();
    Doctor getdoctorById(Long id);
    List<Doctor> getBySpecialization(String specialization);
    Doctor updateById(Long id,Doctor updated);
    boolean deleteDoctor(Long id);
}
