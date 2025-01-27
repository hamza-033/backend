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
public class UpdateHotelRequest {
    @NotNull(message = "Hotel Id field cannot be empty")
    private int id;
    @NotBlank(message = "Hotel name cannot be empty")
    private String name;
    @NotBlank(message = "Phone number field cannot be empty")
    @Pattern(regexp = "\\d+", message = "Phone number must consist of digits only")
    private String phoneNumber;
    @NotNull(message = "Hotel star field cannot be empty")
    @Min(value = 1, message = "Value cannot be less than 1")
    @Max(value = 5, message = "Value cannot be more than 5")
    private int hotelStar;
}
