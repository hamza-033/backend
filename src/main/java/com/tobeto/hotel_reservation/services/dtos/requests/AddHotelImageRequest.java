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
public class AddHotelImageRequest {
    @NotNull(message = "Hotel Id cannot be empty")
    private int hotelId;
    //@NotBlank(message = "ImageUrl cannot be empty")
    private String imageUrl;
}
