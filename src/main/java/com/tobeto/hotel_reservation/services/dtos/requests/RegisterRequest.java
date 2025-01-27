package com.tobeto.hotel_reservation.services.dtos.requests;

import com.tobeto.hotel_reservation.entities.Gender;
import com.tobeto.hotel_reservation.entities.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "First name cannot be empty")
    private String name;
    @NotBlank(message = "Last name cannot be empty")
    private String surname;
    @NotNull(message = "Gender field cannot be empty")
    private Gender gender;
    @NotBlank(message = "Please enter your phone number")
    @Pattern(regexp = "\\d+", message = "Phone number must consist of numbers only")
    private String phoneNumber;
    @NotNull(message = "Identification number cannot be empty")
    @Pattern(regexp = "\\d{10,11}", message = "Identification number must be 10 or 11 digits long and must consist of numbers only")
    private String identificationNumber;
    @NotBlank(message = "Email field cannot be empty")
    @Email(message = "Please enter a valid email address")
    private String email;
    @NotNull(message = "Password field cannot be empty")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).{6,}$", message = "Password must be at least 6 characters long and contain at least one uppercase letter and one number")
    private String password;
    @NotNull(message = "User role field cannot be empty")
    private Role role;
}
