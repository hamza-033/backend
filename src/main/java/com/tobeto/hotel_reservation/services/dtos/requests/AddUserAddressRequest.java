package com.tobeto.hotel_reservation.services.dtos.requests;

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
public class AddUserAddressRequest {

    @NotNull(message = "User Id field cannot be empty")
    private int userId;
    @NotNull(message = "District Id field cannot be empty")
    private int districtId;
    @NotBlank(message = "Address field cannot be empty")
    private String addressText;

}
