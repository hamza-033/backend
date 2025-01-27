package com.tobeto.hotel_reservation.services.dtos.requests;

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
public class UpdateUserRequest {
    @NotNull(message = "Id field cannot be empty!")
    private int id;
    @NotBlank(message = "Please enter your phone number")
    @Pattern(regexp = "\\d+", message = "Phone number must consist of only digits")
    private String phoneNumber;
    @NotBlank(message = "Email field cannot be empty")
    @Email(message = "Please enter a valid email address")
    private String email;
    @NotNull(message = "Password field cannot be empty")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).{6,}$", message = "Password must be at least 6 characters long, contain at least one uppercase letter, and at least one digit.")
    private String password;
}
