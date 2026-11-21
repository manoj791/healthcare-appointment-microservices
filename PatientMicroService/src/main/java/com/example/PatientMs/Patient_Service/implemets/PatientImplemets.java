package com.example.PatientMs.Patient_Service.implemets;

import com.example.PatientMs.Patient_Service.Patient;
import com.example.PatientMs.Patient_Service.PatientService;
import com.example.PatientMs.Patient_Service.Patient_JpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientImplemets implements PatientService {
    @Autowired
    private Patient_JpaRepository patientJpaRepository;

    public PatientImplemets(Patient_JpaRepository patientJpaRepository) {
        this.patientJpaRepository = patientJpaRepository;
    }

    @Override
    public void savePatient(Patient patient) {
      patientJpaRepository.save(patient);
    }

    @Override
    public Patient getPatientById(Long id) {
        Optional<Patient> patient = patientJpaRepository.findById(id);
        return patient.orElse(null);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientJpaRepository.findAll();
    }

    @Override
    public Patient getUpdatedById(Long id, Patient updatedPatient) {
        return patientJpaRepository.findById(id).map(patient -> {
             patient.setName(updatedPatient.getName());
             patient.setAge(updatedPatient.getAge());
             patient.setDisease(updatedPatient.getDisease());
             patient.setEmail(updatedPatient.getEmail());
             patient.setPhone(updatedPatient.getPhone());
             return patientJpaRepository.save(patient);
        }).orElseThrow(() -> new RuntimeException("Patient Not found with"+id));
    }

    @Override
    public boolean deletePatientById(Long id) {
        if(patientJpaRepository.existsById(id)){
            patientJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
