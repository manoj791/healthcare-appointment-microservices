package com.example.Appointment.Appointment_Service;

import org.springframework.http.HttpStatusCode;

import java.util.List;

public interface AppointmentService {

    void createAppointment(Appointment appointment);
    Appointment getAppointment(Long id);
    Appointment updateAppointment(Long id, Appointment appointment);
    boolean deleteAppointment(Long id);
    String getDoctorDetails();
    List<Appointment> FindAll();
}
