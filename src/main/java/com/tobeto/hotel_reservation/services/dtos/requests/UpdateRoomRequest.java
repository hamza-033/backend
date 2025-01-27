package com.tobeto.hotel_reservation.services.dtos.requests;

import com.tobeto.hotel_reservation.entities.RoomStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
public class UpdateRoomRequest {
    @NotNull(message = "Room Id cannot be empty")
    private int id;
    @NotBlank(message = "Room Type cannot be empty")
    private String roomType;
    @NotNull(message = "Capacity cannot be empty")
    @Min(value = 1, message = "Capacity cannot be less than 1 person")
    @Max(value = 8, message = "Capacity cannot exceed 8 people")
    private int capacity;
    @NotNull(message = "Price cannot be empty")
    private double price;
    @NotNull(message = "Room Status cannot be empty")
    private RoomStatus roomStatus;
}
