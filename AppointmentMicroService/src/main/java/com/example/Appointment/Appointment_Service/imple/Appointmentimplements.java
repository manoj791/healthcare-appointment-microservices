package com.example.Appointment.Appointment_Service.imple;

import com.example.Appointment.Appointment_Service.Appointment;
import com.example.Appointment.Appointment_Service.AppointmentRepository;
import com.example.Appointment.Appointment_Service.AppointmentService;
import com.example.Appointment.Appointment_Service.Clients.DoctorClient;
import com.example.Appointment.Appointment_Service.Clients.PatientClient;
import com.example.Appointment.Appointment_Service.Dto.AppointmentMessage;
import com.example.Appointment.Appointment_Service.Dto.DoctorDto;
import com.example.Appointment.Appointment_Service.Dto.PatientDto;
import com.example.Appointment.Appointment_Service.Messaging.RabbitMqProducer;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class Appointmentimplements implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorClient doctorClient;
    private final PatientClient patientClient;
    @Autowired
    private RestTemplate restTemplate;

    private static final String DOCTOR_SERVICE = "doctorService";
    public Appointmentimplements(AppointmentRepository appointmentRepository,DoctorClient doctorClient,PatientClient patientClient) {
        this.appointmentRepository = appointmentRepository;
        this.doctorClient=doctorClient;
        this.patientClient=patientClient;
    }
    @Autowired
    RabbitMqProducer producer;
    @Override
    public void createAppointment(Appointment appointment) {
        DoctorDto doctorDto = doctorClient.getdoctorById(appointment.getDoctorId());
        if(doctorDto==null){
            throw new RuntimeException(("Doctor not Found"));
        }
        if(!doctorDto.getAvailableSlots().contains(appointment.getSlot())){
            throw new RuntimeException("Slot Not Available");
        }
        appointment.setStatus("Confirmed");
        Appointment savedAppointment = appointmentRepository.save(appointment);
        AppointmentMessage appointmentMessage = new AppointmentMessage(
                savedAppointment.getId(),
                savedAppointment.getPatientId(),
                savedAppointment.getDoctorId(),
                savedAppointment.getStatus()
        );
        producer.sendMessage(appointmentMessage);

    }
    @Override
    public Appointment getAppointment(Long id) {
        Appointment appointment=appointmentRepository.findById(id).orElse(null);
        DoctorDto doctor = doctorClient.getdoctorById(appointment.getDoctorId());
        PatientDto patient = patientClient.getPatient(appointment.getPatientId());
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        return appointment;
    }

    @Override
    public Appointment updateAppointment(Long id, Appointment appointment) {
        return appointmentRepository.findById(id).map(appointment1 -> {
            appointment1.setPatientId(appointment.getPatientId());
            appointment1.setDoctorId(appointment.getDoctorId());
            appointment1.setSlot(appointment.getSlot());
            appointment1.setStatus(appointment.getStatus());
            return appointmentRepository.save(appointment1);
        }).orElseThrow(() -> new RuntimeException("AppointMent not Found with"+id));
    }

    @Override
    public boolean deleteAppointment(Long id) {
        if(appointmentRepository.existsById(id)){
            appointmentRepository.deleteById(id);
            return true;
        }
        return false;
    }
    @CircuitBreaker(name="DOCTOR_SERVICE",fallbackMethod ="doctorFallBack")
    public String getDoctorDetails(){
        return restTemplate.getForObject("http://DOCTOR-SERVICE/doctors",String.class);
    }

    @Override
    public List<Appointment> FindAll() {
        return appointmentRepository.findAll();
    }

    public String doctorFallBack(Exception ex){
        return "Doctor service unavailable. Please try later.";
    }
    //public String rateLimiterFallBack(Exception ex){
      //  return "Too many Requests.Please try again later1";
    //}
}
