package com.tobeto.hotel_reservation.services.dtos.requests;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddReservationRequest {
    @NotNull(message = "User Id cannot be empty!")
    private int userId;
    @NotNull(message = "Room Id cannot be empty!")
    private int roomId;
    @NotNull(message = "Entrance Day cannot be empty")
    @FutureOrPresent(message = "Must be a future or present date.")
    private LocalDate enteranceDay;
    @NotNull(message = "Release Day cannot be empty")
    @Future(message = "Must be a future date.")
    private LocalDate releaseDay;
}
