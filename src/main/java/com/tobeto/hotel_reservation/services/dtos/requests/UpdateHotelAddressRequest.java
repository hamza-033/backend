package com.tobeto.hotel_reservation.services.dtos.requests;

import com.tobeto.hotel_reservation.entities.District;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateHotelAddressRequest {
    @NotNull(message = "Hotel Id cannot be empty")
    private int id;
    @NotBlank(message = "Address cannot be empty")
    private String addressText;
    @NotNull(message = "District Id field cannot be empty")
    private int districtId;
}
