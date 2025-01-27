package com.tobeto.hotel_reservation.services.dtos.requests;

import com.tobeto.hotel_reservation.entities.RoomStatus;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddRoomRequest {
    @NotNull(message = "Hotel Id cannot be empty")
    private int hotelId;

    @NotBlank(message = "Room Type cannot be empty")
    private String roomType;

    @NotNull(message = "Please enter the capacity")
    @Min(value = 1, message = "Capacity cannot be less than 1 person")
    @Max(value = 8, message = "Capacity cannot exceed 8 people")
    private int capacity;

    @NotNull(message = "Price field cannot be empty")
    @Positive
    private double price;

    @NotNull(message = "Room status cannot be empty")
    private RoomStatus roomStatus;
}
