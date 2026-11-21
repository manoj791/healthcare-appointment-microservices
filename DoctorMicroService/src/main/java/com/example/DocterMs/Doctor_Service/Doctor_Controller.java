package com.example.DocterMs.Doctor_Service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class Doctor_Controller {
    private final Docter_Service docterService;

    public Doctor_Controller(Docter_Service docterService) {
        this.docterService = docterService;
    }
    @PostMapping
    public ResponseEntity<String> createDoctor(@RequestBody Doctor doctor){
        docterService.createDoctor(doctor);
        return new ResponseEntity<>("Successfully created Doctor", HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Doctor>> getDoctors(){
        return new ResponseEntity<>(docterService.getallDoctors(),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id){
        Doctor doctor=docterService.getdoctorById(id);
        if(doctor!=null){
            return new ResponseEntity<>(doctor,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping(params = "specialization")
    public ResponseEntity<List<Doctor>> getDoctorBySpecialization(@RequestParam String specialization){
        return new ResponseEntity<>(docterService.getBySpecialization(specialization),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateDoctor(@PathVariable Long id,@RequestBody Doctor updated){
        Doctor doctor=docterService.updateById(id,updated);
        return new ResponseEntity<>("Doctor updated SuccessFully",HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable Long id){
        boolean res=docterService.deleteDoctor(id);
        if(res){
            return new ResponseEntity<>("Deleted SuccessFully",HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
