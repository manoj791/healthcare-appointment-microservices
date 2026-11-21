package com.example.PatientMs.Patient_Service;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @PostMapping
    public ResponseEntity<String> createPatient(@Valid @RequestBody Patient patient){
        patientService.savePatient(patient);
        return new ResponseEntity<>("Patient Added Successfully", HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getById(@PathVariable Long id){
        Patient patient=patientService.getPatientById(id);
        if(patient!=null){
            return new ResponseEntity<>(patient,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping
    public ResponseEntity<List<Patient>> getAllpatients(){
        return new ResponseEntity<>(patientService.getAllPatients(),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updatePatient(@PathVariable Long id,@Valid @RequestBody Patient updatedPatient){
         patientService.getUpdatedById(id,updatedPatient);
         return new ResponseEntity<>("Patient uodated SuccsessFully",HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id){
          boolean isDeleted=patientService.deletePatientById(id);
          if(isDeleted){
              return new ResponseEntity<>("Deleted SuccessFully",HttpStatus.OK);
          }
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
