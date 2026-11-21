package com.example.PatientMs.Patient_Service;

import jakarta.validation.Valid;

import java.util.List;

public interface PatientService {
    void savePatient(Patient patient);
    Patient getPatientById(Long id);
    List<Patient> getAllPatients();
    Patient getUpdatedById(Long id, @Valid Patient updatedPatient);
    boolean deletePatientById(Long id);
}
