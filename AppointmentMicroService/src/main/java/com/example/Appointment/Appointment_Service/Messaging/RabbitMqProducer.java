package com.example.Appointment.Appointment_Service.Messaging;

import com.example.Appointment.Appointment_Service.Dto.AppointmentMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import static com.example.Appointment.Appointment_Service.Messaging.RabbitMqConfiguration.QUEUE;

@Service
public class RabbitMqProducer {
    private final RabbitTemplate rabbitTemplate;

    public RabbitMqProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    public void sendMessage(AppointmentMessage appointmentMessage){
        rabbitTemplate.convertAndSend(QUEUE,appointmentMessage);
    }
}
