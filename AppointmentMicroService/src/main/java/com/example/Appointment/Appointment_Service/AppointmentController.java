package com.example.Appointment.Appointment_Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<String> createAppointment(@RequestBody Appointment appointment){
        appointmentService.createAppointment(appointment);
        return new ResponseEntity<>("Appointment created Successfully", HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointment(@PathVariable Long id){
        return new ResponseEntity<>(appointmentService.getAppointment(id),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateAppointment(@PathVariable Long id,@RequestBody Appointment appointment){
        Appointment appointment1=appointmentService.updateAppointment(id,appointment);
        return new ResponseEntity<>("Updated SuccessFully",HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Long id){
        boolean res=appointmentService.deleteAppointment(id);
        if(res){
            return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/doctor")
    public String callDoctor() {
        return appointmentService.getDoctorDetails();
    }
    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        return new ResponseEntity<>(appointmentService.FindAll(),HttpStatus.OK);
    }
}
