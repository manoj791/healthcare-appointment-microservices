package com.example.PatientMs.Patient_Service;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Patients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @NotBlank(message="Name is Required")
   private String name;
   @Min(value=0,message="Age must be Positive")
   private int age;
   @NotBlank(message="Disease is Required")
   private String disease;
   @Email(message="Invalid email format")
   private String email;
   @Pattern(regexp = "\\d{10}",message="Phone number Must be 10 numbers")
   private String phone;
}
