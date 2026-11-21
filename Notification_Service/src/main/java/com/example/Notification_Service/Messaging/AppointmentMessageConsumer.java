package com.example.Notification_Service.Messaging;

import com.example.Notification_Service.Dto.AppointmentMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.Notification_Service.Messaging.RabbitMqConfiguration.QUEUE;

@Service
public class AppointmentMessageConsumer {
    @Autowired
    private EmailService emailService;
    @RabbitListener(queues=QUEUE)
    public void consumeMessage(AppointmentMessage appointmentMessage){
        System.out.println("Notification Confirmed");
        System.out.println("Appointment Id"+appointmentMessage.getAppointmentId());
        System.out.println("Patient Id"+appointmentMessage.getPatientId());
        System.out.println("Doctor Id"+appointmentMessage.getDoctorId());
        System.out.println("Status"+appointmentMessage.getStatus());

        emailService.sendEmail(
                "jangammanojkumar16@gmail.com",
                "AppointMent Confirmed",
                "Doctor ID: " + appointmentMessage.getDoctorId()
        );
    }
}
