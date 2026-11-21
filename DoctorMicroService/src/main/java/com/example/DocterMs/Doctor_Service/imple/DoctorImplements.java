package com.example.DocterMs.Doctor_Service.imple;

import com.example.DocterMs.Doctor_Service.Docter_Service;
import com.example.DocterMs.Doctor_Service.Doctor;
import com.example.DocterMs.Doctor_Service.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorImplements implements Docter_Service {

    private final DoctorRepository doctorRepository;

    public DoctorImplements(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public void createDoctor(Doctor doctor) {
      doctorRepository.save(doctor);
    }

    @Override
    public List<Doctor> getallDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor getdoctorById(Long id) {
        return doctorRepository.findById(id).orElseThrow(() ->  new RuntimeException("Dcotor not Found"));
    }
    @Override
    public List<Doctor> getBySpecialization(String specialization) {
        return doctorRepository.findBySpecialization(specialization);
    }

    @Override
    public Doctor updateById(Long id,Doctor updated) {
        return doctorRepository.findById(id).map(doctor -> {
            doctor.setName(updated.getName());
            doctor.setSpecialization(updated.getSpecialization());
            doctor.setAvailableSlots(updated.getAvailableSlots());
            return doctorRepository.save(doctor);
        }).orElseThrow(() -> new RuntimeException("Doctor not Found with"+id));
    }

    @Override
    public boolean deleteDoctor(Long id) {
        if(doctorRepository.existsById(id)){
            doctorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
