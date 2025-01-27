package com.tobeto.hotel_reservation.services.dtos.requests;

import com.tobeto.hotel_reservation.entities.Gender;
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
public class AddGuestInformationRequest {

    @NotNull(message = "Reservation Id cannot be empty")
    private int reservationId;
    @NotBlank(message = "Name field cannot be empty")
    private String name;
    @NotBlank(message = "Surname field cannot be empty")
    private String surname;
    @NotNull(message = "Identification number cannot be empty")
    @Pattern(regexp = "\\d{10,11}", message = "Identification number must be 10 or 11 digits long and consist of numbers")
    private String identificationNumber;
    @NotNull(message = "Gender field cannot be empty")
    private Gender gender;

}
