package com.tobeto.hotel_reservation.services.dtos.requests;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddHotelRequest {
    @NotNull(message = "User Id cannot be empty.")
    private int userId;
    @NotBlank(message = "Hotel name cannot be empty")
    private String name;
    @NotBlank(message = "Description cannot be empty")
    private String description;
    @NotBlank(message = "Phone number cannot be empty")
    @Pattern(regexp = "\\d+", message = "Phone number must consist only of digits")
    private String phoneNumber;
    @Min(value = 1, message = "Value cannot be less than 1")
    @Max(value = 5, message = "Value cannot be more than 5")
    private int hotelStar;
}
