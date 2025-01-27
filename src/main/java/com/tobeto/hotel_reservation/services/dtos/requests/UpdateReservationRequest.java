package com.tobeto.hotel_reservation.services.dtos.requests;

import com.tobeto.hotel_reservation.entities.ReservationStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateReservationRequest {

    @NotNull(message = "Reservation Id cannot be empty!")
    private int id;

    @NotNull(message = "ReservationStatus cannot be empty!")
    private ReservationStatus reservationStatus;

}
